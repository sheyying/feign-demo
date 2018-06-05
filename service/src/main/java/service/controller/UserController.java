package service.controller;

import com.common.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by sheying on 2018/06/05.
 */
@RestController
public class UserController {

    @ResponseBody
    @RequestMapping(value="/api/user/getName",method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    public String getName(@RequestParam("name") String name) throws InterruptedException{
        return name.toUpperCase();
    }

    @ResponseBody
    @RequestMapping(value="/api/user/getUser",method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    public User getUser(@RequestParam("id") long id) throws InterruptedException{
        User user = new User();
        user.setId(id);
        user.setName("默认名称");
        user.setSex("未知");
        return user;
    }

    @ResponseBody
    @RequestMapping(value="/api/user/getMap", method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    public Map<String, Object> getMap(@RequestBody Map<String, Object> map) throws InterruptedException{
        if (null != map.get("name")){
            map.put("name", map.get("name").toString().toUpperCase());
        }
        if (null == map.get("age")){
            map.put("age", 12);
        }
        if (null == map.get("sex")){
            map.put("sex", "--");
        }
        return map;
    }

    @RequestMapping(value = "/getAdmin", method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    public User getAdmin(@RequestBody List<User> userList){
        if (userList == null || userList.size() == 0){
            return null;
        }
        for (User user : userList){
            if (user == null || user.getName() == null){
                continue;
            }
            if (user.getName().equals("admin")){
                return user;
            }
        }
        return null;
    }
}
