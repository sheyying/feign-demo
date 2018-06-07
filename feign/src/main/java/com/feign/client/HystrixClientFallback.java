package com.feign.client;

import com.common.entity.User;
import com.feign.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sheying on 2018/06/05.
 */
@Component
public class HystrixClientFallback implements FeignTestClient{

    @Override
    public UserResponse<String, Void> getName(String name) {
        UserResponse response = new UserResponse();
        response.setReturnCode("110");
        response.setReturnMsg("faild");
        response.setData("出错啦~~~");
        return response;
    }

    @Override
    public UserResponse<User, Void> getUser(long id) {
        UserResponse response = new UserResponse();
        response.setReturnCode("110");
        response.setReturnMsg("faild");
        response.setData(null);
        return response;
    }

    @Override
    public UserResponse<User, Void> getAdmin(List<User> userList) {
        UserResponse response = new UserResponse();
        response.setReturnCode("110");
        response.setReturnMsg("faild");
        response.setData(null);
        return response;
    }

    @Override
    public UserResponse<User, Void> getUserWithHeader(String encoding, String accept) {
        UserResponse response = new UserResponse();
        response.setReturnCode("110");
        response.setReturnMsg("faild");
        response.setData(null);
        return response;
    }
}
