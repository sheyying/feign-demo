package com.common.filter;

import com.alibaba.fastjson.JSON;
import com.common.annotation.ResponseValidate;
import com.common.entity.ResponseMsg;
import com.common.response.FeignResponse;
import com.common.thread.ThreadLocalMessage;
import com.weimob.cube.exception.BizException;
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
        log.info("around 拦截到了{}方法...", point);

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
                Object obj = JSON.parseObject(responseMsg.getReturnMsg(), valClazz);
                if (valClazz.isInstance(obj)) {
                    FeignResponse resp = (FeignResponse) valClazz.cast(obj);
                    if (null == resp) {
                        log.info("ResponseValidateAspect 拦截器 result is null");
                        throw new BizException("1", "result is null");
                    } else if (!resp.responseSuccess()) {
                        log.info("ResponseValidateAspect 拦截器 result error");
                        throw new BizException(resp.returnCode(), resp.returnMsg(), object);
                    } else {
                        return object;
                    }
                }
            }
        } catch (Exception e){
            throw e;
        } finally {
            ThreadLocalMessage.getInstance().removeMessage();
        }
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

}
