package com.feign.controller;

import com.common.entity.User;
import com.feign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String getName(@RequestParam("name") String name) {
        String result = userService.getName(name);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getUser", method={RequestMethod.POST})
    public User getUser(long id){
        User result = userService.getUser(id);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getAdmin", method={RequestMethod.POST})
    public User getAdmin(@RequestBody List<User> userList){
        User result = userService.getAdmin(userList);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserWithHeader", method={RequestMethod.GET})
    public User getUserWithHeader(@RequestHeader("Accept-Encoding") String encoding,
                                                      @RequestHeader("Accept") String accept){
        User result = userService.getUserWithHeader(encoding, accept);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/testAsync", method={RequestMethod.POST})
    public String testAsync(@RequestBody List<User> userList){
        return userService.testAsync(userList);
    }

}
