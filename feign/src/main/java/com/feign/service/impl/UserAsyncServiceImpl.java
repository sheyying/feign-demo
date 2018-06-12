package com.feign.service.impl;

import com.common.entity.User;
import com.feign.client.FeignTestClient;
import com.feign.service.UserAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by sheying on 2018/06/11.
 */

@Component
@Async
public class UserAsyncServiceImpl implements UserAsyncService{

    @Autowired
    private FeignTestClient feignTestClient;

    @Override
    public void insertUser(User user) {
        feignTestClient.insertUser(user);
    }

    @Override
    public String updateUser(long id) {
        String result = feignTestClient.updateUser(id);
        return result;
    }
}
