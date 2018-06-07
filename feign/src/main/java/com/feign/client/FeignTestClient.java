package com.feign.client;

import com.common.annotation.EnableReponseValidater;
import com.common.config.MyFeignExampleConfig;
import com.common.entity.User;
import com.feign.response.UserResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sheying on 2018/05/31.
 * 当调用失败时返回HystrixClientFallback里面的实现内容
 */
@FeignClient(name = "feignClient1", url = "${serviceUrl}", path = "/api/user",
        fallback = HystrixClientFallback.class, configuration = MyFeignExampleConfig.class)
public interface FeignTestClient {

    @EnableReponseValidater
    @RequestMapping(method = RequestMethod.GET, value = "/getName")
    UserResponse<String, Void> getName(@RequestParam("name") String name);

    @EnableReponseValidater
    @RequestMapping(method = RequestMethod.GET, value = "/getUser/{id}")
    UserResponse<User, Void> getUser(@PathVariable("id") long id);

    @EnableReponseValidater
    @RequestMapping(method = RequestMethod.POST, value = "/getUserWithHeader")
    UserResponse<User, Void> getUserWithHeader(@RequestHeader("Accept-Encoding") String encoding,
                                               @RequestHeader("Accept") String accept);

    @EnableReponseValidater
    @RequestMapping(method = RequestMethod.POST, value = "/getAdmin")
    UserResponse<User, Void> getAdmin(List<User> userList);

}
