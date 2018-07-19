package com.common.annotation;

import com.common.config.AsyncConfig;
import com.common.config.FeignClientConfig;
import com.common.config.ResponseVallidateRegistrar;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

/**
 * Created by sheying on 2018/06/07.
 */
// 开启FeignClients注解
@EnableFeignClients
@Configuration
@Import({FeignClientConfig.class, ResponseVallidateRegistrar.class, AsyncConfig.class})
public @interface EnableO2OFeignClients {

    @AliasFor(annotation = EnableFeignClients.class)
    String[] basePackages();

    @AliasFor(annotation = EnableFeignClients.class)
    String[] value() default {};

    @AliasFor(annotation = EnableFeignClients.class)
    Class<?>[] basePackageClasses() default {};

    @AliasFor(annotation = EnableFeignClients.class)
    Class<?>[] defaultConfiguration() default {};

    @AliasFor(annotation = EnableFeignClients.class)
    Class<?>[] clients() default {};

}
