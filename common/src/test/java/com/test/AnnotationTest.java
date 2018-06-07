package com.test;

import com.common.annotation.EnableReponseValidater;
import com.common.entity.User;
import com.common.response.FeignServiceResponse;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/06/06.
 */
public class AnnotationTest {

//    @EnableReponseValidater(clazz = UserResponse)
    public FeignServiceResponse doSomething() { return null; }

//    @EnableReponseValidater
    public FeignServiceResponse doSomething(User user) { return null; }

    @Resource
    public static void main(String[] args) throws Exception
    {
        /*Method method = com.test.AnnotationTest.class.getMethod("doSomething",null);
        if(method.isAnnotationPresent(EnableReponseValidater.class)){
            System.out.println(method.getAnnotation(EnableReponseValidater.class));
        }*/
        ReponseValidaterUtil.getFruitInfo(AnnotationTest.class);
    }
}
