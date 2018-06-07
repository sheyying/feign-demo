package com.common.annotation;

import java.lang.annotation.*;

/**
 * Created by sheying on 2018/06/06.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableReponseValidater {

    /**
     * 返回成功状态码，默认000000
     * @return
     */
    String successCode() default "000000";

}
