package com.common.codec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.entity.ResponseMsg;
import com.common.response.FeignResponse;
import com.common.thread.ThreadLocalMessage;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;

import java.io.*;
import java.lang.reflect.Type;

/**
 * Created by sheying on 2018/06/07.
 * fastJson解码器
 */
public class FastJsonDecoder implements Decoder{
    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        ResponseMsg responseMsg = ThreadLocalMessage.getInstance().getMessage();
        if(response.status() == 404) {
            return Util.emptyValueOf(type);
        } else if(response.body() == null) {
            return null;
        } else if(!responseMsg.isHasAnno() && String.class.equals(type)) {
            return Util.toString(response.body().asReader());
        } else {
            InputStream inputStream = response.body().asInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String lines =reader.readLine();
                responseMsg.setReturnMsg(lines);
                ThreadLocalMessage.getInstance().setMessage(responseMsg);
                reader.close();
                Object obj;
                if (responseMsg.isHasAnno()){// 需要校验，则只返回responseVo
                    JSONObject jsonObject = JSON.parseObject(lines);
                    if (null == jsonObject.get("responseVo")){
                        obj = null;
                    } else if (String.class.equals(type)){
                        obj = jsonObject.get("responseVo").toString();
                    } else {
                        obj = JSON.parseObject(jsonObject.get("responseVo").toString(), type);
                    }
                }else {
                    obj = JSON.parseObject(lines, type);
                }
                return obj;
            } catch (Exception e){
                ThreadLocalMessage.getInstance().removeMessage();
                throw e;
            } finally {
                if (null != reader){
                    reader.close();
                }
            }
        }
    }

}
