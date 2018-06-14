package com.feign.client;

import com.common.annotation.ResponseValidate;
import com.common.entity.User;
import com.feign.response.UserResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sheying on 2018/05/31.
 */
@FeignClient(name = "feignClient1", url = "${serviceUrl}", path = "/api/user",
        fallback = HystrixClientFallback.class)
public interface FeignTestClient {

    @ResponseValidate(value = UserResponse.class)
    @RequestMapping(method = RequestMethod.GET, value = "/getName")
    UserResponse<String, Void> getName(@RequestParam("name") String name);

    @ResponseValidate(value = UserResponse.class)
    @RequestMapping(method = RequestMethod.GET, value = "/getUser/{id}")
    UserResponse<User, Void> getUser(@PathVariable("id") long id);

    @RequestMapping(method = RequestMethod.POST, value = "/getUserWithHeader")
    UserResponse<User, Void> getUserWithHeader(@RequestHeader("Accept-Encoding") String encoding,
                                               @RequestHeader("Accept") String accept);

    @RequestMapping(method = RequestMethod.POST, value = "/getAdmin")
    UserResponse<User, Void> getAdmin(List<User> userList);

    @RequestMapping(method = RequestMethod.POST, value = "/insertUser")
    void insertUser(User user);

    @RequestMapping(method = RequestMethod.GET, value = "/updateUser")
    String updateUser(@RequestParam("id") long id);
}
