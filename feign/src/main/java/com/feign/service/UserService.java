package com.feign.service;

import com.feign.client.FeignTestClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by sheying on 2018/06/05.
 */
public interface UserService {

    String getName(String name);

    Map<String, Object> getMap(Map<String, Object> map);

}
