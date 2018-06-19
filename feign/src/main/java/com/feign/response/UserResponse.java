package com.feign.response;

import com.common.response.FeignResponse;
import com.common.response.SoaResponse;
import lombok.Data;

import java.util.UUID;

/**
 * Created by sheying on 2018/06/06.
 */
@Data
public class UserResponse<T, ErrT> implements FeignResponse {

    private static final String RETURN_SUCCESS = "000000";

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

    @Override
    public String monitorTrackId() {
        return this.getMonitorTrackId();
    }

    @Override
    public String globalTicket() {
        return this.getGlobalTicket();
    }

    @Override
    public String logBizData() {
        return this.getLogBizData();
    }

    @Override
    public String returnCode() {
        return this.getReturnCode();
    }

    @Override
    public String returnMsg() {
        return this.getReturnMsg();
    }

    @Override
    public T responseVo() {
        return this.getResponseVo();
    }

    @Override
    public ErrT errT() {
        return null;
    }

    @Override
    public boolean responseSuccess() {
        return this.getReturnCode().equals(RETURN_SUCCESS);
    }
}
