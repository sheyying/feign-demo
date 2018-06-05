package com.feign.service.impl;

import com.feign.client.FeignTestClient;
import com.feign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        String result = feignTestClient.getName(name);
        return result;
    }

    @Override
    public Map<String, Object> getMap(Map<String, Object> map){
        Map<String, Object> result = feignTestClient.getMap(map);
        return result;
    }
}
