package com.common.annotation;

import java.lang.annotation.*;

/**
 * Created by sheying on 2018/06/06.
 * 开启
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ResponseValidate {

    /**
     * response的实现类
     *
     * @return
     */
    Class<?> value();

}
