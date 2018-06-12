package com.common.response;

/**
 * Created by sheying on 2018/06/06.
 * 返回值类型
 */
public interface FeignResponse<T, ErrT> {

    String monitorTrackId();

    String globalTicket();

    String logBizData();

    String returnCode();

    String returnMsg();

    T responseVo();

    ErrT errT();

    // 响应状态
    Boolean responseSuccess();

}
