package com.feign.client;

import com.common.entity.User;
import feign.Headers;
import feign.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by sheying on 2018/05/31.
 */
@FeignClient(name = "feignTest", path = "/api/user", fallback = HystrixClientFallback.class)
public interface FeignTestClient {

    @RequestMapping(method = RequestMethod.GET, value = "/getName")
//    @Headers({"Content-Type: application/json","Accept: application/json"})
    String getName(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.POST, value = "/getUser?id={id}")
    User getUser(@PathVariable("id") long id);

    @RequestMapping(method = RequestMethod.POST, value = "/getMap"
            , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> getMap(Map<String, Object> map);

    @RequestMapping(method = RequestMethod.POST, value = "/getAdmin")
    User getAdmin(List<User> userList);

}
