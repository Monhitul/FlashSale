package cn.moon.dao;

import cn.moon.entity.SuccessSale;
import org.apache.ibatis.annotations.Param;

/**
 * create by Monhitul on 2019/4/8
 */
public interface SuccessSaleDao {

    /**
     * 插入购买明细，可过滤重复
     */
    int insertSuccessSale(@Param("goodsId") long goodsId, @Param("userPhone") long userPhone);

    /**
     * 根据id查询SuccessSale，并携带秒杀产品对象实体，
     * 对应 entity -> SuccessSale -> goods字段
     */
    SuccessSale queryByIdWithGoods(@Param("goodsId") long goodsId, @Param("userPhone") long userPhone);
}
