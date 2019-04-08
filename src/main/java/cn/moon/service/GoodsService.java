package cn.moon.service;

import cn.moon.dto.Exposer;
import cn.moon.dto.GoodsExecution;
import cn.moon.entity.Goods;
import cn.moon.exception.RepeatSaleException;
import cn.moon.exception.SaleCloseException;
import cn.moon.exception.SaleException;

import java.util.List;

/**
 * create by Monhitul on 2019/4/8
 */
public interface GoodsService {

    /**
     * 查询所有商品记录
     */
    List<Goods> getGoodsList();

    /**
     * 查询单个商品记录
     */
    Goods getById(long goodsId);

    /**
     * 抢购开启是输出抢购接口地址，
     * 否则输出系统时间和抢购开始时间
     */
    Exposer exportGoodsUrl(long goodsId);

    /**
     * 执行抢购操作
     */
    GoodsExecution executeGoodsSale(long goodsId, long userPhone, String md5) throws SaleException, RepeatSaleException, SaleCloseException;
}
