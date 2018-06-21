package com.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by sheying on 2018/06/20.
 */
@Configuration
@ImportResource("classpath:META-INF/responseValidateAop.xml")
public class ResponseValidateAspectConfig {
}
