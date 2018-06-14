package com.common.response;

import lombok.Data;

import java.util.UUID;

/**
 * Created by sheying on 2018/06/06.
 */
@Data
public class SoaResponse<T, ErrT>{

    private static final long serialVersionUID = 889695893318362669L;
    private String returnCode = "000000";
    private String returnMsg;
    private String logBizData;
    private Boolean processResult = Boolean.valueOf(true);
    private T responseVo;
    private ErrT errT;
    private String monitorTrackId = UUID.randomUUID().toString();
    private String timestamp = System.currentTimeMillis() + "";
    private String globalTicket;

}
