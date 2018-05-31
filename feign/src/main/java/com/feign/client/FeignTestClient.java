package com.feign.client;

import com.feign.config.FastJsonExampleConfig;
import feign.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by Administrator on 2018/05/31.
 */
@FeignClient(name = "feignTest", path = "/api/user", configuration = FastJsonExampleConfig.class)
public interface FeignTestClient {

    @RequestMapping(method = RequestMethod.GET, value = "/getName")
    String getName(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.POST, value = "/getMap",
            headers = {"content-type=application/json", "accept=application/json"})
    Map<String, Object> getMap(Map<String, Object> map);
}
