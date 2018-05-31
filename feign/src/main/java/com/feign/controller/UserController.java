package com.feign.controller;

import com.feign.client.FeignTestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by sheying on 2018/05/31.
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private FeignTestClient feignTestClient;

    @ResponseBody
    @RequestMapping(value = "/test", method={RequestMethod.GET})
    public String test(@RequestParam("name") String name) {
        return "Hello, SomeBody " + name;
    }

    @ResponseBody
    @RequestMapping(value = "/getName", method={RequestMethod.GET})
    public String getName(@RequestParam("name") String name) {
        String result = feignTestClient.getName(name);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getMap", method={RequestMethod.POST})
    public Map<String, Object> getMap(@RequestBody Map<String, Object> map){
        Map<String, Object> result = feignTestClient.getMap(map);
        return result;
    }
}
