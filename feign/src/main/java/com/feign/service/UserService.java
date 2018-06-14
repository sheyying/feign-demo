package com.feign.service;

import com.common.entity.User;

import java.util.List;

/**
 * Created by sheying on 2018/06/05.
 */
public interface UserService {

    String getName(String name);

    User getUser(long id);

    User getAdmin(List<User> userList);

    User getUserWithHeader(String encoding, String accept);

    String testAsync(List<User> userList);

}
