package com.common.config;

import com.common.codec.FastJsonDecoder;
import com.common.codec.FastJsonEncoder;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sheying on 2018/06/12.
 */
@Configuration
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

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 重试次数配置
     * @return
     */
   /* @Bean
    public Request.Options feignOptions() {
        // 连接超时时长1000ms, 响应超时时长3500ms
        return new Request.Options(1000, 3500);
    }

    *//**
     * 重试次数配置
     * @return
     *//*
    @Bean
    public Retryer feignRetryer() {
        // 重试间隔为100ms, 最大重试时间为1s, 重试次数为0次。
        return new Retryer.Default(100, 1000, 0);
    }*/
}
