package com.feign.response;

import com.common.response.FeignServiceResponse;
import lombok.Data;

import java.util.UUID;

/**
 * Created by sheying on 2018/06/06.
 */
@Data
public class UserResponse<T, ErrT> implements FeignServiceResponse {

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
        return this.monitorTrackId;
    }

    @Override
    public String globalTicket() {
        return this.globalTicket;
    }

    @Override
    public String logBizData() {
        return this.logBizData;
    }

    @Override
    public String returnCode() {
        return this.returnCode;
    }

    @Override
    public String returnMsg() {
        return this.returnMsg;
    }

    @Override
    public Object responseVo() {
        return this.responseVo;
    }

    @Override
    public Object errT() {
        return this.errT;
    }

    @Override
    public Boolean responseStatus() {
        return this.returnCode.equals(RETURN_SUCCESS);
    }
}
