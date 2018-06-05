package com.feign.service.impl;

import com.common.entity.User;
import com.feign.client.FeignTestClient;
import com.feign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by sheying on 2018/06/05.
 */
@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private FeignTestClient feignTestClient;

    @Override
    public String getName(String name) {
        return feignTestClient.getName(name);
    }

    @Override
    public Map<String, Object> getMap(Map<String, Object> map){
        return feignTestClient.getMap(map);
    }

    @Override
    public User getUser(long id) {
        return feignTestClient.getUser(id);
    }

    @Override
    public User getAdmin(List<User> userList) {
        return feignTestClient.getAdmin(userList);
    }
}
