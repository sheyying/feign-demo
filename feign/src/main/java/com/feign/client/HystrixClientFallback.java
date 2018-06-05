package com.feign.client;

import com.common.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by sheying on 2018/06/05.
 */
@Component
public class HystrixClientFallback implements FeignTestClient{

    @Override
    public String getName(String name) {
        return "出错啦~~~";
    }

    @Override
    public Map<String, Object> getMap(Map<String, Object> map) {
        map.put("message", "出错啦!!!");
        return map;
    }

    @Override
    public User getUser(long id) {
        return null;
    }

    @Override
    public User getAdmin(List<User> userList) {
        return null;
    }
}
