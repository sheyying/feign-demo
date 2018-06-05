package com.feign.service;

import com.common.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by sheying on 2018/06/05.
 */
public interface UserService {

    String getName(String name);

    User getUser(long id);

    Map<String, Object> getMap(Map<String, Object> map);

    User getAdmin(List<User> userList);
}
