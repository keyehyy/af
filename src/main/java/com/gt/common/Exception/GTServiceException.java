package com.gt.common.Exception;

/**
 * @author zhukeyan
 * @description 自定义异常类，解决sevice层抛出Exception 事物不回滚问题
 * @date 2020/1/8
 */
public class GTServiceException extends RuntimeException{
    static final long serialVersionUID = 1L;

    public GTServiceException() {
        super();
    }

    public GTServiceException(String message) {
        super(message);
    }


    public GTServiceException(String message, Throwable cause) {
        super(message, cause);
    }


    public GTServiceException(Throwable cause) {
        super(cause);
    }


    protected GTServiceException(String message, Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
