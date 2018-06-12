package com.test;

import com.common.annotation.ResponseValidate;
import com.common.entity.User;
import com.common.response.FeignResponse;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/06/06.
 */
public class AnnotationTest {

//    @ResponseValidate(clazz = UserResponse)
    public FeignResponse doSomething() { return null; }

//    @ResponseValidate
    public FeignResponse doSomething(User user) { return null; }

    @Resource
    public static void main(String[] args) throws Exception
    {
        Method method = com.test.AnnotationTest.class.getMethod("doSomething",null);
        if(method.isAnnotationPresent(ResponseValidate.class)){
            System.out.println(method.getAnnotation(ResponseValidate.class));
        }
        ReponseValidaterUtil.getFruitInfo(AnnotationTest.class);
    }
}
