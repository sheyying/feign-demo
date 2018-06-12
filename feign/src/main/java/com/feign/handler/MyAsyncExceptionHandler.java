package com.feign.handler;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * Created by sheying on 2018/06/11.
 */
public class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    //手动处理捕获的异常
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
        System.out.println("-------------》》》捕获线程异常信息" + throwable.getMessage() + "; " + method.getName());
        for (Object param : obj) {
            System.out.println("Parameter value - " + param);
        }
    }

}
