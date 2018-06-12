package com.test;

import com.common.annotation.ResponseValidate;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by sheying on 2018/06/06.
 */
public class ReponseValidaterUtil {

    public static void getFruitInfo(Class<?> clazz){
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Type returnType = method.getGenericReturnType();
            //得到该类下面的RequestMapping注解
            ResponseValidate validater = method.getAnnotation(ResponseValidate.class);
            if (null != validater) {
                System.out.println(returnType + "返回成功状态码：" + validater);
            }
        }
    }
}
