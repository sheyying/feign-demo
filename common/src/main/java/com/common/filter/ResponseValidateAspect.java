package com.common.filter;

import com.alibaba.fastjson.JSON;
import com.common.annotation.ResponseValidate;
import com.common.entity.ResponseMsg;
import com.common.exception.BizException;
import com.common.response.FeignResponse;
import com.common.thread.ThreadLocalMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by sheying on 2018/06/06.
 */
@Slf4j
public class ResponseValidateAspect {

    public Object around(ProceedingJoinPoint point) throws Throwable{
        log.info(String.format("around 拦截到了%s方法...", point));

        try {
            ResponseValidate responseValidate = this.findResponseValidate(point);
            if (null != responseValidate) {
                ResponseMsg responseMsg = new ResponseMsg();
                responseMsg.setHasAnno(true);
                ThreadLocalMessage.getInstance().setMessage(responseMsg);

                Object object = point.proceed();    //运行doSth()，返回值用一个Object类型来接收
                Class valClazz = responseValidate.value();
                responseMsg = ThreadLocalMessage.getInstance().getMessage();
                if (StringUtils.isBlank(responseMsg.getReturnMsg())) {
                    return object;
                }
                Object obj = JSON.parseObject(responseMsg.getReturnMsg(), responseValidate.value());
                if (valClazz.isInstance(obj)) {
                    FeignResponse resp = (FeignResponse) valClazz.cast(obj);
//                FeignResponse resp = (FeignResponse)object;
                    if (null == resp) {
                        log.info("ResponseValidateAspect 拦截器 result is null");
                        throw new BizException("", "result is null");
                    } else if (!resp.responseSuccess()) {
                        log.info("ResponseValidateAspect 拦截器 result error");
                        throw new BizException(resp.returnCode(), resp.returnMsg(), object);
                    } else {
                        log.info("ResponseValidateAspect return " + resp.responseVo());
                        return object;
                    }
                }
            }
        } catch (Exception e){
            throw e;
        } finally {
            ThreadLocalMessage.getInstance().removeMessage();
        }
        System.out.println("around 拦截 ending----");
        return point.proceed();
    }

    private ResponseValidate findResponseValidate(JoinPoint point){
        MethodSignature methodSignature = ((MethodSignature)point.getSignature());
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ResponseValidate.class)){
            return method.getAnnotation(ResponseValidate.class);
        }
        Class clazz = methodSignature.getDeclaringType();
        if (clazz.isAnnotationPresent(ResponseValidate.class)){
            return (ResponseValidate)clazz.getAnnotation(ResponseValidate.class);
        }
        return null;
    }

   /* public void responseValidate(JoinPoint point, Object result) throws Throwable{
        log.info(String.format("responseValidate 拦截到了%s方法，result: %s", point, result));

        ResponseValidate responseValidate = this.findResponseValidate(point);
        Class valClazz = responseValidate.value();
        if (valClazz.isInstance(result)){
            valClazz.cast(result);
        }
        FeignResponse resp = (FeignResponse)result;
        if (null != responseValidate) {
            log.info("该方法加了ResponseValidate注解...");
            if (null == result) {
                System.out.println("拦截器 result is null");
                throw new BizException("", "result is null");
            } else if (!resp.responseSuccess()) {
                System.out.println("拦截器 result error");
                throw new BizException(resp.returnCode(), resp.returnMsg(), resp.responseVo());
            } else {
                result = resp.responseVo();
            }
        }
        log.info("ResponseValidateAspect responseValidate result: " + result);
    }*/

}
