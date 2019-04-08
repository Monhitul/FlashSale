package cn.moon.dao;

import cn.moon.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * create by Monhitul on 2019/4/8
 */
public interface GoodsDao {

    /**
     * 减库存
     */
    int reduceNumber(@Param("goodsId") long goodsId, @Param("saleTime") Date saleTime);

    /**
     * 根据id查询秒杀对象
     */
    Goods queryById(long goodsId);

    /**
     * 根据偏移量查询秒杀商品列表
     */
    List<Goods> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
