package com.common.annotation;

import com.common.config.FeignClientConfig;
import com.common.filter.ResponseValidateAop;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by sheying on 2018/06/07.
 */
// 开启FeignClients注解
@EnableFeignClients
@Configuration
@Import({FeignClientConfig.class, ResponseValidateAop.class})
public @interface EnableO2OFeignConfig {

}
