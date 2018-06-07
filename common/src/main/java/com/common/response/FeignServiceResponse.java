package com.common.response;

/**
 * Created by sheying on 2018/06/06.
 * 返回值类型
 */
public interface FeignServiceResponse<T, ErrT> {
    String monitorTrackId();
    String globalTicket();
    String logBizData();
    /**
     * 返回状态码
     * @return
     */
    String returnCode();
    /**
     * 返回信息
     * @return
     */
    String returnMsg();
    /**
     * 返回对象
     * @return
     */
    T responseVo();
    /**
     * 返回错误信息对象
     * @return
     */
    ErrT errT();
    /**
     * 响应状态
     * @return
     */
    Boolean responseStatus();
}
