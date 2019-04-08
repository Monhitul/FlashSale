package cn.moon.dao;

import cn.moon.entity.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置Spring和junit整合，junit启动时加载IoC容器
 * create by Monhitul on 2019/4/8
 */

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class GoodsDaoTest {

    //注入DAO实现类依赖
    @Resource
    private GoodsDao goodsDao;

    @Test
    public void reduceNumber() {
        Date saleTime = new Date();
        int res = goodsDao.reduceNumber(1000L, saleTime);
        System.out.println(res);
    }

    @Test
    public void queryById() {
        long id = 1000;
        Goods goods = goodsDao.queryById(id);
        System.out.println(goods.getName());
        System.out.println(goods);
    }

    @Test
    public void queryAll() {
        List<Goods> goodsList = goodsDao.queryAll(0,100);
        for(Goods goods : goodsList)
            System.out.println(goods);
    }
}