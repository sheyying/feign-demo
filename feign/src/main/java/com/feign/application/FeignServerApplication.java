package com.feign.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by sheying on 2018/05/31.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.feign"})
@EnableFeignClients(basePackages = {"com.feign.client"})
public class FeignServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignServerApplication.class, args);
    }

}
