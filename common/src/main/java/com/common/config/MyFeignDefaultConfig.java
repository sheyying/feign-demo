package com.common.config;

import com.common.codec.FastJsonDecoder;
import com.common.codec.FastJsonEncoder;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sheying on 2018/06/13.
 */
@Configuration
@Deprecated
public class MyFeignDefaultConfig {

    @Bean
    @ConditionalOnMissingBean
    public Decoder feignDecoder() {
        return new FastJsonDecoder();
    }

    @Bean
    @ConditionalOnMissingBean
    public Encoder feignEncoder() {
        return new FastJsonEncoder();
    }
}
