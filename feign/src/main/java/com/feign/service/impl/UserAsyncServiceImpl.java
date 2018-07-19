package com.feign.service.impl;

import com.common.entity.User;
import com.feign.client.FeignTestClient;
import com.feign.service.UserAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * Created by sheying on 2018/06/11.
 */

@Component
@Slf4j
public class UserAsyncServiceImpl implements UserAsyncService{

    @Autowired
    private FeignTestClient feignTestClient;

    @Override
    @Async
    public void insertUser(User user) {
        System.out.println("进入 insertUser 方法...");
        try {
            Thread.sleep(5000 * 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        feignTestClient.insertUser(user);
    }

    @Override
    public String updateUser(long id) {
        String result = feignTestClient.updateUser(id);
        return result;
    }

    @Async
    public Future<String> asyncInvokeReturnFuture(int i) {
        System.out.println("进入 asyncInvokeReturnFuture 方法...");
        Future<String> future;
        try {
            Thread.sleep(5000 * 1);
            future = new AsyncResult<String>("success:" + i);
        } catch (InterruptedException e) {
            future = new AsyncResult<String>("error");
        }
        return future;
    }

}
