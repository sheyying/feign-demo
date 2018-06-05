package com.feign;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by sheying on 2018/05/31.
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.feign.client"})
public class FeignServerApplication {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    public static void main(String[] args) {
        SpringApplication.run(FeignServerApplication.class, args);
    }

}
