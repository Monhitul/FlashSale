package cn.moon.service.Impl;

import cn.moon.dao.GoodsDao;
import cn.moon.dao.SuccessSaleDao;
import cn.moon.dto.Exposer;
import cn.moon.dto.GoodsExecution;
import cn.moon.entity.Goods;
import cn.moon.entity.SuccessSale;
import cn.moon.enums.StateEnum;
import cn.moon.exception.RepeatSaleException;
import cn.moon.exception.SaleCloseException;
import cn.moon.exception.SaleException;
import cn.moon.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * create by Monhitul on 2019/4/8
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private SuccessSaleDao successSaleDao;

    private final String slat = "jioagaoiD(;DdV;a5SDG3agf,f124afijnafa09ga9D";     //md5盐值字符串，用于混淆MD5

    public List<Goods> getGoodsList() {
        return goodsDao.queryAll(0, 10);
    }

    public Goods getById(long goodsId) {
        return goodsDao.queryById(goodsId);
    }

    public Exposer exportGoodsUrl(long goodsId) {
        Goods goods = goodsDao.queryById(goodsId);
        if(goods == null){
            //查询不到商品
            return new Exposer(false, goodsId);
        }

        //到这里证明查询到了商品
        Date startTime = goods.getStartTime();
        Date endTime = goods.getEndTime();

        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime()
                || nowTime.getTime() > endTime.getTime()){
            //抢购未开始，或已结束的情况
            return new Exposer(false, goodsId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }

        //到这里证明抢购已经开始
        String md5 = getMD5(goodsId);       //获取md5
        return new Exposer(true, md5, goodsId);     //抢购已开始
    }

    //获取md5，转化特定字符串的过程，不可逆
    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    /**
     * 开启事务
     * 因为存在 减库存 和 记录购买明细，
     * 所以需要事务
     */
    @Transactional
    public GoodsExecution executeGoodsSale(long goodsId, long userPhone, String md5) throws SaleException, RepeatSaleException, SaleCloseException {
        if (md5 == null || !md5.equals(getMD5(goodsId))) {
            //Mmd5为空，或者md5不匹配，证明前端过来的md5被篡改过，不一致
            throw new SaleException("goods data rewrite");
        }
        //执行秒杀逻辑：减库存+记录购买行为
        try {
            //记录购买行为
            int insertCount = successSaleDao.insertSuccessSale(goodsId, userPhone);
            //唯一：seckillId，userPhone
            if (insertCount <= 0) {
                //重复购买，因为联合主键的存在，数据不能插入
                throw new RepeatSaleException("sale repeated");
            } else {
                //减库存，热点商品的竞争在这里，所以交换insert和update顺序，避免行级锁的持有时间
                int updateCount = goodsDao.reduceNumber(goodsId, new Date());
                if (updateCount <= 0) {
                    //没有更新到记录，抢购结束，rollback
                    throw new SaleCloseException("sale closed");
                } else {
                    //抢购成功，commit
                    SuccessSale successSale = successSaleDao.queryByIdWithGoods(goodsId, userPhone);
                    //携带商品，返回抢购成功结果
                    return new GoodsExecution(goodsId, StateEnum.SUCCESS, successSale);
                }
            }
        } catch (SaleCloseException e1) {
            throw e1;
        } catch (RepeatSaleException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //所有编译期异常，转化为运行期异常
            throw new SaleException("sale inner error:" + e.getMessage());
        }
    }
}
