package com.feign;

import com.common.annotation.EnableO2OFeignConfig;
import org.springframework.context.annotation.Configuration;

/**
 */
@EnableO2OFeignConfig(basePackages = "com.feign.client")
@Configuration
public class FeignConfig {

}
