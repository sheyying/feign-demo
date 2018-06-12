package com.feign.client;

import com.common.entity.User;
import com.feign.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

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
        response.setResponseVo("出错啦~~~");
        return response;
    }

    @Override
    public UserResponse<User, Void> getUser(long id) {
        UserResponse response = new UserResponse();
        response.setReturnCode("110");
        response.setReturnMsg("faild");
        response.setResponseVo(null);
        return response;
    }

    @Override
    public UserResponse<User, Void> getAdmin(List<User> userList) {
        UserResponse response = new UserResponse();
        response.setReturnCode("110");
        response.setReturnMsg("faild");
        response.setResponseVo(null);
        return response;
    }

    @Override
    public UserResponse<User, Void> getUserWithHeader(String encoding, String accept) {
        UserResponse response = new UserResponse();
        response.setReturnCode("110");
        response.setReturnMsg("faild");
        response.setResponseVo(null);
        return response;
    }

    @Override
    public void insertUser(User user) {
        System.out.println(" -------- insertUser faild !!! " + user);
    }

    @Override
    public String updateUser(long id) {
        System.out.println(" -------- updateUser faild !!! " + id);
        return " -------- updateUser faild !!! " + id;
    }
}
