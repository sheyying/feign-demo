package service.controller;

import com.common.entity.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import service.response.UserResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by sheying on 2018/06/05.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    Random random = new Random();

    @ResponseBody
    @RequestMapping(value="/getName",method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    public UserResponse<String, Void> getName(@RequestParam("name") String name) throws InterruptedException{
        UserResponse response = new UserResponse();
        response.setReturnCode("000000");
        response.setReturnMsg("success");
        response.setResponseVo(name.toUpperCase());

        return response;
    }

    @ResponseBody
    @RequestMapping(value="/getUser/{id}",method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    public UserResponse<User, Void> getUser(@PathVariable("id") long id) throws InterruptedException{
        UserResponse response = new UserResponse();
        User user = new User();
        user.setId(id);
        user.setName("默认名称");
        user.setSex("未知");

        response.setReturnCode("000000");
        response.setReturnMsg("success");
        response.setResponseVo(user);

        if (id > 10000){
            response.setReturnCode("000002");
            response.setReturnMsg("id too big");
            response.setResponseVo(null);
        }

        return response;
    }

    @RequestMapping(value = "/getAdmin", method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    public UserResponse<User, Void> getAdmin(@RequestBody List<User> userList) throws InterruptedException{
        UserResponse response = new UserResponse();
        if (userList == null || userList.size() == 0){
            response.setReturnCode("000001");
            response.setReturnMsg("faild");
            return response;
        }
        for (User user : userList){
            if (user == null || user.getName() == null){
                continue;
            }
            if (user.getName().equals("admin")){
                response.setReturnCode("000000");
                response.setReturnMsg("success");
                response.setResponseVo(user);
                return response;
            }
        }
        response.setReturnCode("000001");
        response.setReturnMsg("faild");

        return response;
    }

    @RequestMapping(value = "/getUserWithHeader", method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    public UserResponse<User, Void> getUserWithHeader(@RequestHeader("Accept-Encoding") String encoding,
                                                      @RequestHeader("Accept") String accept)  {
        System.out.println(" ===== getUserWithHeader ====== " + encoding + " ===== " + accept);
        UserResponse response = new UserResponse();
        User user = new User();
        if (null != user.getName()){
            user.setName(user.getName().toUpperCase());
        }
        if (null == user.getAge()){
            user.setAge(18);
        }
        if (null == user.getSex()){
            user.setSex("--");
        }
        Map<String, Object> headerInfo = new HashMap<String, Object>();
        headerInfo.put("Accept-Encoding", encoding);
        headerInfo.put("Accept", accept);
        user.setHeaderInfo(headerInfo);

        response.setReturnCode("000000");
        response.setReturnMsg("success");
        response.setResponseVo(user);

        return response;
    }

    @RequestMapping(value = "/insertUser", method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    public void insertUser(@RequestBody User user) throws InterruptedException{
        System.out.println(" =======> 开始 insertUser() user = " + user);
        long start = System.currentTimeMillis();

        Thread.sleep(random.nextInt(5000));
        long end = System.currentTimeMillis();
        System.out.println(" =======> 完成 insertUser()，耗时：" + (end - start) + "毫秒 " + user);
    }

    @RequestMapping(value = "/updateUser", method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    public String updateUser(@RequestParam("id") Long id) throws InterruptedException{
        System.out.println(" =======> 开始 updateUser() id = " + id);
        long start = System.currentTimeMillis();

        Thread.sleep(random.nextInt(5000));
        long end = System.currentTimeMillis();
        System.out.println(" =======> 完成 updateUser()，耗时：" + (end - start) + "毫秒");

        return "updateUser 成功";
    }

}
