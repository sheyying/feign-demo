package com.common.exception;


import lombok.Getter;

/**
 * 业务异常
 * @author
 */
public class BizExceptrion extends RuntimeException {
    @Getter
    private String code;

    public BizExceptrion(String code, String msg){
        super(msg);
        this.code = code;
    }

    public BizExceptrion(String code, String msg, Throwable t){
        super(msg, t);
        this.code = code;
    }
}
