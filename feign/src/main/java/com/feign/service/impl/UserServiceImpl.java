package com.feign.service.impl;

import com.common.annotation.ResponseValidate;
import com.common.entity.User;
import com.feign.client.FeignTestClient;
import com.feign.response.UserResponse;
import com.feign.service.UserAsyncService;
import com.feign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserAsyncService userAsyncService;

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
    @ResponseValidate(value = UserResponse.class)
    public UserResponse<User, Void> getUserWithHeader(String encoding, String accept) {
        System.out.println(" =======> 开始 getUserWithHeader: " + encoding + "; ||| accept:" + accept);

        UserResponse<User, Void> response = feignTestClient.getUserWithHeader(encoding, accept);

        return response;
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
