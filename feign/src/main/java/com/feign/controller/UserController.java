package com.feign.controller;

import com.common.entity.User;
import com.feign.response.UserResponse;
import com.feign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by sheying on 2018/05/31.
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/test", method={RequestMethod.GET})
    public String test(@RequestParam("name") String name) {
        return "Hello, SomeBody " + name;
    }

    @ResponseBody
    @RequestMapping(value = "/getName", method={RequestMethod.GET})
    public UserResponse<String, Void> getName(@RequestParam("name") String name) {
        UserResponse<String, Void> result = userService.getName(name);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getUser", method={RequestMethod.POST})
    public UserResponse<User, Void> getUser(long id){
        UserResponse<User, Void> result = userService.getUser(id);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getAdmin", method={RequestMethod.POST})
    public UserResponse<User, Void> getAdmin(@RequestBody List<User> userList){
        UserResponse<User, Void> result = userService.getAdmin(userList);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserWithHeader", method={RequestMethod.GET})
    public UserResponse<User, Void> getUserWithHeader(@RequestHeader("Accept-Encoding") String encoding,
                                                      @RequestHeader("Accept") String accept){
        UserResponse<User, Void> result = userService.getUserWithHeader(encoding, accept);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/testAsync", method={RequestMethod.POST})
    public String testAsync(@RequestBody List<User> userList){
        return userService.testAsync(userList);
    }

}
