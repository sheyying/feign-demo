package com.feign.client;

import com.common.annotation.ResponseValidate;
import com.common.entity.User;
import com.feign.response.UserResponse;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sheying on 2018/05/31.
 */
@FeignClient(name = "feignClient1", url = "${serviceUrl}", path = "/api/user")
public interface FeignTestClient {

    @RequestMapping(method = RequestMethod.GET, value = "/getName")
    @ResponseValidate(value = UserResponse.class)
    String getName(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.GET, value = "/getUser/{id}")
    @ResponseValidate(value = UserResponse.class)
    User getUser(@PathVariable("id") long id);

    @RequestMapping(method = RequestMethod.GET, value = "/getAge")
    @ResponseValidate(value = UserResponse.class)
    Integer getAge(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.POST, value = "/getUserWithHeader")
    UserResponse<User, Void> getUserWithHeader(@RequestHeader("Accept-Encoding") String encoding,
                                               @RequestHeader("Accept") String accept);

    @RequestMapping(method = RequestMethod.POST, value = "/getAdmin")
    @ResponseValidate(value = UserResponse.class)
    User getAdmin(List<User> userList);

    @RequestMapping(method = RequestMethod.POST, value = "/insertUser")
    void insertUser(User user);

    @RequestMapping(method = RequestMethod.GET, value = "/updateUser")
    String updateUser(@RequestParam("id") long id);
}
