package com.feign.service.impl;

import com.common.entity.User;
import com.feign.client.FeignTestClient;
import com.feign.response.UserResponse;
import com.feign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * Created by sheying on 2018/06/05.
 */
@Component
public class UserServiceImpl implements UserService{

    Random random = new Random();

    @Autowired
    private FeignTestClient feignTestClient;

    @Override
//    @Async
    public UserResponse<String, Void> getName(String name){
        System.out.println(" =======> 开始 getName()");
        long start = System.currentTimeMillis();

        UserResponse<String, Void> response = feignTestClient.getName(name);

        /*try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        long end = System.currentTimeMillis();
        System.out.println(" =======> 完成 getName()，耗时：" + (end - start) + "毫秒");
        return response;
    }

    @Override
//    @Async
    public UserResponse<User, Void> getUser(long id){
        System.out.println(" =======> 开始 getUser()");
        long start = System.currentTimeMillis();

        UserResponse<User, Void> response = feignTestClient.getUser(id);

       /* try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        long end = System.currentTimeMillis();
        System.out.println(" =======> 完成 getUser()，耗时：" + (end - start) + "毫秒");
        return response;
    }

    @Override
//    @Async
    public UserResponse<User, Void> getAdmin(List<User> userList){
        System.out.println(" =======> 开始 getAdmin()");
        long start = System.currentTimeMillis();

        UserResponse<User, Void> response = feignTestClient.getAdmin(userList);

       /* try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        long end = System.currentTimeMillis();
        System.out.println(" =======> 完成 getAdmin()，耗时：" + (end - start) + "毫秒");
        return response;
    }

    @Override
//    @Async
    public UserResponse<User, Void> getUserWithHeader(String encoding, String accept) {
        System.out.println(" =======> 开始 getUserWithHeader: " + encoding + "; ||| accept:" + accept);

        UserResponse<User, Void> response = feignTestClient.getUserWithHeader(encoding, accept);

        return response;
    }
}
