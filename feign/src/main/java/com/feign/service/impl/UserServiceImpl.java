package com.feign.service.impl;

import com.alibaba.fastjson.JSON;
import com.common.entity.User;
import com.feign.client.FeignTestClient;
import com.feign.response.UserResponse;
import com.feign.service.UserAsyncService;
import com.feign.service.UserService;
import com.weimob.cube.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sheying on 2018/06/05.
 */
@Component
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private FeignTestClient feignTestClient;

    @Autowired
    private UserAsyncService userAsyncService;

    @Override
    public String getName(String name){
        try {
            return feignTestClient.getName(name);
        } catch (BizException e){
            System.out.println("getName() error " + JSON.toJSONString(e));
        }
        return null;
    }

    @Override
    public User getUser(long id){
        try {
            return feignTestClient.getUser(id);
        } catch (BizException e){
            System.out.println("getUser() error " + JSON.toJSONString(e));
        }
        return null;
    }

    @Override
    public User getAdmin(List<User> userList){
        try {
            return feignTestClient.getAdmin(userList);
        } catch (BizException e){
            System.out.println("getAdmin() error " + JSON.toJSONString(e));
        }
        return null;
    }

    @Override
    public Integer getAge(Long id) {
        try {
            return feignTestClient.getAge(id);
        } catch (BizException e){
            System.out.println("getAge() error " + JSON.toJSONString(e));
        }
        return null;
    }

    @Override
    public User getUserWithHeader(String encoding, String accept) {
        try {
            UserResponse<User, Void> response = feignTestClient.getUserWithHeader(encoding, accept);
            return response.responseVo();
        } catch (BizException e){
            System.out.println("getUserWithHeader() error " + JSON.toJSONString(e));
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
//        while (true) {  ///这里使用了循环判断，等待获取结果信息
            if (null != result) {  //判断是否执行完毕
                System.out.println("Result from feignTestClient.updateUser - " + result);
                return result;
            }
//        }
        return null;
    }
}
