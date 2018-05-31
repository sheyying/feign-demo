package service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Administrator on 2018/05/31.
 */
@SpringBootApplication
//@EnableDiscoveryClient
@RestController
public class ServiceApplication {

    @ResponseBody
    @RequestMapping(value="/api/user/getName",method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    public String getName(@RequestParam("name") String name) throws InterruptedException{
        System.out.println("============>>>>>" + name);
        return name.toUpperCase();
    }

    @ResponseBody
    @RequestMapping(value="/api/user/getMap", method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    public Map<String, Object> getMap(@RequestBody Map<String, Object> map) throws InterruptedException{
        System.out.println("============>>>>>" + map);
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

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}