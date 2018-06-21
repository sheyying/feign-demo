package com.common.annotation;

import com.common.response.FeignResponse;

import java.lang.annotation.*;

/**
 * Created by sheying on 2018/06/06.
 * 开启
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseValidate {

    /**
     * FeignResponse的实现类
     *
     * @return
     */
    Class<? extends FeignResponse> value();

}
