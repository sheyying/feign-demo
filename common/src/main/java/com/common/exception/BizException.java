package com.common.exception;


import lombok.Getter;

/**
 * 业务异常
 * @author
 */
public class BizException extends RuntimeException {
    @Getter
    private String code;
    @Getter
    private Object data;

    public BizException(String code, String msg){
        super(msg);
        this.code = code;
    }

    public BizException(String code, String msg, Throwable e){
        super(msg, e);
        this.code = code;
    }

    public BizException(String code, String msg, Object data){
        super(msg);
        this.code = code;
        this.data = data;
    }

    public BizException(String code, String msg, Object data, Throwable e){
        super(msg, e);
        this.code = code;
        this.data = data;
    }
}
