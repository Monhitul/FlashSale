package cn.moon.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * create by Monhitul on 2019/4/8
 */
@Component
public class SuccessSale {

    private long goodsId;

    private long userPhone;     //用户唯一标识

    private short state;        //状态表示：-1无效，0成功，1已付款，2已发货

    private Date createTime;

    private Goods goods;        //用于包含用户购买的商品

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "SuccessSale{" +
                "goodsId=" + goodsId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                ", goods=" + goods +
                '}';
    }
}
