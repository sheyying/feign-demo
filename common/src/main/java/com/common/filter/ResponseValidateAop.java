package com.common.filter;

import com.common.annotation.ResponseValidate;
import com.common.response.FeignResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by sheying on 2018/06/06.
 */
@Aspect
@Component
public class ResponseValidateAop {

    @Pointcut("execution(* *.*(..))")
    public void testPointcut(){}

    /**
     * 拦截规则：拦截所有@ResponseValidate注解的方法或类
     */
    @Pointcut("@annotation(com.common.annotation.ResponseValidate)")
    public void respValidatePointcut(){}

    /**
     *
     * @param point
     * @throws Throwable
     */
    @AfterReturning(value = "@within(respValidate) || @annotation(respValidate)", returning = "result")
    public void responseValidate(JoinPoint point, ResponseValidate respValidate,
                                 FeignResponse result) throws Throwable{
        System.out.println("===== 拦截到了" + point +" 方法... result: " + result
                + ", respValidate: " + respValidate.value());

        //当前拦截的类和方法：
        Class clazz = point.getTarget().getClass();
        Method targetMethod = ((MethodSignature) point.getSignature()).getMethod();

    }
}
