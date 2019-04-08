package cn.moon.exception;

/**
 * 限时抢购关闭异常
 * create by Monhitul on 2019/4/8
 */
public class SaleCloseException extends SaleException {

    public SaleCloseException(String message) {
        super(message);
    }

    public SaleCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
