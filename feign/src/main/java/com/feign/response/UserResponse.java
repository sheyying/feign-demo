package com.feign.response;

import com.common.entity.User;
import com.common.response.FeignResponse;
import com.common.response.SoaResponse;
import lombok.Data;

/**
 * Created by sheying on 2018/06/06.
 */
@Data
public class UserResponse<T, ErrT> extends SoaResponse<T, ErrT> implements FeignResponse {

    private static final String RETURN_SUCCESS = "000000";

    @Override
    public String monitorTrackId() {
        return super.getMonitorTrackId();
    }

    @Override
    public String globalTicket() {
        return super.getGlobalTicket();
    }

    @Override
    public String logBizData() {
        return super.getLogBizData();
    }

    @Override
    public String returnCode() {
        return super.getReturnCode();
    }

    @Override
    public String returnMsg() {
        return super.getReturnMsg();
    }

    @Override
    public T responseVo() {
        return super.getResponseVo();
    }

    @Override
    public ErrT errT() {
        return null;
    }

    @Override
    public boolean responseSuccess() {
        return super.getReturnCode().equals(RETURN_SUCCESS);
    }
}
