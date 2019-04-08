package cn.moon.exception;

/**
 * 抢购业务相关异常
 * create by Monhitul on 2019/4/8
 */
public class SaleException extends RuntimeException {

    public SaleException(String message) {
        super(message);
    }

    public SaleException(String message, Throwable cause) {
        super(message, cause);
    }
}
