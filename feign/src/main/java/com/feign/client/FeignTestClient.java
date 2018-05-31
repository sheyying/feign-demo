package com.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by sheying on 2018/05/31.
 */
@FeignClient(name = "feignTest", path = "/api/user")
public interface FeignTestClient {

    @RequestMapping(method = RequestMethod.GET, value = "/getName")
    String getName(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.POST, value = "/getMap",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> getMap(Map<String, Object> map);
}
