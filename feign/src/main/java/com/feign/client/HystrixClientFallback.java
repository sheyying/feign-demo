package com.feign.client;

import com.common.entity.User;
import com.feign.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sheying on 2018/06/05.
 */
@Component
public class HystrixClientFallback implements FeignTestClient{

    @Override
    public String getName(String name) {
        /*UserResponse response = new UserResponse();
        response.setReturnCode("110");
        response.setReturnMsg("faild");
        response.setResponseVo("出错啦~~~");
        return response;*/
        return "出错啦~~~";
    }

    @Override
    public User getUser(long id) {
       /* UserResponse response = new UserResponse();
        response.setReturnCode("110");
        response.setReturnMsg("faild");
        response.setResponseVo(null);
        return response;*/
        return null;
    }

    @Override
    public Integer getAge(Long id) {
        return 0;
    }

    @Override
    public User getAdmin(List<User> userList) {
        System.out.println("getAdmin2() error");
        return null;
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
