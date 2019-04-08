package cn.moon.enums;

/**
 * 使用枚举表述常量数据字段，封装抢购结果信息
 * create by Monhitul on 2019/4/8
 */
public enum  StateEnum {
    SUCCESS(1, "购买成功"),
    END(0, "抢购结束"),
    REPEAT_KILL(-1, "重复购买"),
    INNER_ERROR(-2, "系统异常"),
    DATA_REWRITE(-3, "数据篡改");

    private int state;

    private String stateInfo;

    StateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static StateEnum stateOf(int index){
        for(StateEnum state : values()){
            if(state.getState() == index)
                return state;
        }
        return null;
    }
}
