package com.common.annotation;

import com.common.config.MyFeignDefaultConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

/**
 * Created by sheying on 2018/06/12.
 */
@Configuration
public @interface O2OFeignClient {

    @AliasFor("name")
    String value() default "";

    /** @deprecated */
    @Deprecated
    String serviceId() default "";

    @AliasFor("value")
    String name() default "";

    String url() default "";

    boolean decode404() default false;

    Class<?>[] configuration() default MyFeignDefaultConfig.class;

    Class<?> fallback() default void.class;

    String path() default "";

}
