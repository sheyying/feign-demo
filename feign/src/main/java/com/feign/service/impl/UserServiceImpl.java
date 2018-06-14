package com.feign.service.impl;

import com.alibaba.fastjson.JSON;
import com.common.entity.User;
import com.common.exception.BizException;
import com.feign.client.FeignTestClient;
import com.feign.response.UserResponse;
import com.feign.service.UserAsyncService;
import com.feign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sheying on 2018/06/05.
 */
@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private FeignTestClient feignTestClient;

    @Autowired
    private UserAsyncService userAsyncService;

    @Override
    public String getName(String name){
        try {
            UserResponse<String, Void> response = feignTestClient.getName(name);
            return response.responseVo();
        } catch (BizException e){
            System.out.println("getAdmin2() error " + JSON.toJSONString(e));
        }
        return null;
    }

    @Override
    public User getUser(long id){
        try {
            UserResponse<User, Void> response = feignTestClient.getUser(id);
            return response.responseVo();
        } catch (BizException e){
            System.out.println("getAdmin2() error " + JSON.toJSONString(e));
        }
        return null;
    }

    @Override
    public User getAdmin(List<User> userList){
        try {
            UserResponse<User, Void> response = feignTestClient.getAdmin(userList);
            return response.responseVo();
        } catch (BizException e){
            System.out.println("getAdmin2() error " + JSON.toJSONString(e));
        }
        return null;
    }

    @Override
    public User getUserWithHeader(String encoding, String accept) {
        try {
            UserResponse<User, Void> response = feignTestClient.getUserWithHeader(encoding, accept);
            return response.responseVo();
        } catch (BizException e){
            System.out.println("getAdmin2() error " + JSON.toJSONString(e));
        }
        return null;
    }

    @Override
    public String testAsync(List<User> userList) {
        userAsyncService.insertUser(userList.get(0));
        String result = userAsyncService.updateUser(22l);
        userAsyncService.insertUser(userList.get(1));
        userAsyncService.insertUser(userList.get(2));
        userAsyncService.insertUser(userList.get(3));
        while (true) {  ///这里使用了循环判断，等待获取结果信息
            if (null != result) {  //判断是否执行完毕
                System.out.println("Result from feignTestClient.updateUser - " + result);
                return result;
            }
        }
    }
}
