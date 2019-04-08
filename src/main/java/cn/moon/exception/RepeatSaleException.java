package cn.moon.exception;

/**
 * 重复购买异常
 * create by Monhitul on 2019/4/8
 */
public class RepeatSaleException extends SaleException{

    public RepeatSaleException(String message) {
        super(message);
    }

    public RepeatSaleException(String message, Throwable cause) {
        super(message, cause);
    }
}
