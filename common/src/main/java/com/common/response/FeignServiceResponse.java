package com.common.response;

/**
 * Created by sheying on 2018/06/06.
 * 返回值类型
 */
public interface FeignServiceResponse<T, ErrT> {
    /**
     * 返回状态码
     * @return
     */
    String getReturnCode();
    /**
     * 返回信息
     * @return
     */
    String getReturnMsg();
    /**
     * 返回对象
     * @return
     */
    T getData();
    /**
     * 返回错误信息对象
     * @return
     */
    ErrT getErrT();
    /**
     * 响应状态
     * @return
     */
    Boolean getResponseStatus();
}
