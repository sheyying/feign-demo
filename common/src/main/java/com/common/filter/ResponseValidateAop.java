package com.common.filter;

import com.common.annotation.ResponseValidate;
import com.common.exception.BizException;
import com.common.response.FeignResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Created by sheying on 2018/06/06.
 */
@Aspect
@Component
public class ResponseValidateAop {

    @Pointcut("execution(* *..client..*(..))")
    public void testPointcut(){}

    /**
     * 拦截规则：拦截所有@ResponseValidate注解的方法或类
     * 拦截不了接口上的注解，故废弃
     */
    @Pointcut("@within(com.common.annotation.ResponseValidate) " +
            "|| @annotation(com.common.annotation.ResponseValidate)")
    public void respValidatePointcut(){}

    @AfterReturning(value = "testPointcut()", returning = "result")
    public void responseValidate(JoinPoint point, FeignResponse result) throws Throwable{
        System.out.println("===== common responseValidate 拦截到了" + point + " 方法... result: " + result);

        MethodSignature methodSignature = ((MethodSignature)point.getSignature());
        boolean methodAnno = methodSignature.getMethod().isAnnotationPresent(ResponseValidate.class);
        boolean clazzAnno = methodSignature.getDeclaringType().isAnnotationPresent(ResponseValidate.class);

        if (methodAnno || clazzAnno){
            if (null == result) {
                System.out.println("拦截器 result is null");
                throw new BizException("", "result is null");
            } else if (!result.responseSuccess()) {
                System.out.println("拦截器 result error");
                throw new BizException(result.returnCode(), result.returnMsg(), result.responseVo());
            }
        }
    }

}
