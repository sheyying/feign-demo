package com.feign.service;

import com.common.entity.User;

import java.util.concurrent.Future;

/**
 * Created by sheying on 2018/06/11.
 */
public interface UserAsyncService {

    void insertUser(User user);

    String updateUser(long id);

    Future<String> asyncInvokeReturnFuture(int i);
}
