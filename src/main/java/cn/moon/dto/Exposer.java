package cn.moon.dto;

/**
 * 暴露商品抢购地址，DTO，数据传输层
 * create by Monhitul on 2019/4/8
 */
public class Exposer {

    private boolean exposed;    //购买是否开启

    private String md5;     //一种加密措施

    private long goodsId;

    private long now;       //系统当前时间（毫秒）

    private long start;

    private long end;

    /**
     * 抢购未开始，或已结束的情况
     */
    public Exposer(boolean exposed, long goodsId, long now, long start, long end) {
        this.exposed = exposed;
        this.goodsId = goodsId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    /**
     * 抢购开始，可以暴露md5
     */
    public Exposer(boolean exposed, String md5, long goodsId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.goodsId = goodsId;
    }

    /**
     * 查询不到商品，不能暴露的情况
     */
    public Exposer(boolean exposed, long goodsId) {
        this.exposed = exposed;
        this.goodsId = goodsId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
