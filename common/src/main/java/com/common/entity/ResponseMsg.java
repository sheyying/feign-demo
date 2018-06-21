package com.common.entity;

import lombok.Data;

/**
 * Created by sheying on 2018/06/20.
 */
@Data
public class ResponseMsg {

    // 返回值，json串
    private String returnMsg;

    // 是否有加校验的注解
    private boolean hasAnno;

}
