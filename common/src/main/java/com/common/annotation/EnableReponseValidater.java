package com.common.annotation;

import java.lang.annotation.*;

/**
 * Created by sheying on 2018/06/06.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableReponseValidater {



}
