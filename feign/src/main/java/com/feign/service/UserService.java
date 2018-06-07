package com.feign.service;

import com.common.entity.User;
import com.feign.response.UserResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by sheying on 2018/06/05.
 */
public interface UserService {

    UserResponse<String, Void> getName(String name);

    UserResponse<User, Void> getUser(long id);

    UserResponse<User, Void> getAdmin(List<User> userList);

    UserResponse<User, Void> getUserWithHeader(String encoding, String accept);
}
