package com.feign.response;

import com.common.response.FeignServiceResponse;
import lombok.Data;

/**
 * Created by sheying on 2018/06/06.
 */
@Data
public class UserResponse<T, ErrT> implements FeignServiceResponse {

    private String returnCode = "000000";
    private String returnMsg = "success";
    private Boolean responseStatus = Boolean.valueOf(true);
    private T data;
    private ErrT errT;

}
