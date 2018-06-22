package com.feign;

import com.common.annotation.EnableO2OFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 */
@EnableO2OFeignClients(basePackages = "com.feign.client")
@Configuration
public class FeignConfig {

}
