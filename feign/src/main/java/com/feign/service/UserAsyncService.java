package com.feign.service;

import com.common.entity.User;

/**
 * Created by sheying on 2018/06/11.
 */
public interface UserAsyncService {

    void insertUser(User user);

    String updateUser(long id);
}
