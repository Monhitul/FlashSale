package cn.moon.dto;

import cn.moon.entity.SuccessSale;
import cn.moon.enums.StateEnum;

/**
 * 封装抢购执行后结果
 * create by Monhitul on 2019/4/8
 */
public class GoodsExecution {

    private long goodsId;

    private int state;      //抢购执行结果状态

    private String stateInfo;       //状态信息

    private SuccessSale successSale;        //抢购成功商品对象

    //购买成功
    public GoodsExecution(long goodsId, StateEnum stateEnum, SuccessSale successSale) {
        this.goodsId = goodsId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successSale = successSale;
    }

    //购买失败
    public GoodsExecution(long goodsId, StateEnum stateEnum) {
        this.goodsId = goodsId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessSale getSuccessSale() {
        return successSale;
    }

    public void setSuccessSale(SuccessSale successSale) {
        this.successSale = successSale;
    }
}
