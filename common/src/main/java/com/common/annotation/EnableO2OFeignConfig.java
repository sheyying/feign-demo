package com.common.annotation;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sheying on 2018/06/07.
 */
// 开启FeignClients注解
@EnableFeignClients
@Configuration
public @interface EnableO2OFeignConfig {

}
