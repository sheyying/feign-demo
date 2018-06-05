package com.feign.client;

import org.springframework.stereotype.Component;

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
}
