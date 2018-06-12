package com.common.filter;

import com.common.annotation.ResponseValidate;
import com.common.response.FeignResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by sheying on 2018/06/06.
 */
@Aspect
@Component
public class WebRequestAroundAdvice implements ApplicationContextAware{

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    private volatile ApplicationContext applicationContext;

    @Pointcut("@annotation(org.springframework.cloud.netflix.feign.FeignClient)")
    public void cutIn(){
        System.out.println("========== cutIn() =========");
    }

    @AfterReturning(value = "cutIn()", returning = "result")
    public void introcepter(ProceedingJoinPoint pjp, Object result) throws Throwable{
        System.out.println("===== 拦截到了" + pjp.getSignature().getName() +"方法..." + result);

        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        Class clazz = targetMethod.getClass();

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Type returnType = method.getGenericReturnType();
            //得到该类下面的RequestMapping注解
            ResponseValidate validater = method.getAnnotation(ResponseValidate.class);
            if (null != validater) {
                System.out.println(returnType.getTypeName() + "返回成功状态码：" + validater);
                if (result instanceof FeignResponse){
                    System.out.println("+++++++++++ returnType instanceof FeignResponse +++++++++++++");

                    Collection<FeignResponse> jobList = new LinkedList<FeignResponse>(
                            this.applicationContext.getBeansOfType(FeignResponse.class).values());
                    for (FeignResponse response : jobList){
                        System.out.println(response.getClass().getName());
                        if (response.getClass().getName().equals(returnType.getTypeName())){
                            System.out.println("匹配成功!! " + response.getClass().getName());
                            Method m = response.getClass().getMethod("getReturnCode");
                        }
                    }

                }
            }
        }
    }
}
