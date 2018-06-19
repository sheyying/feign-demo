package com.common.codec;

import com.alibaba.fastjson.JSON;
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
    public Object decode(Response response, Type type) throws IOException, FeignException {
        if(response.status() == 404) {
            return Util.emptyValueOf(type);
        } else if(response.body() == null) {
            return null;
        } else if(String.class.equals(type)) {
            return Util.toString(response.body().asReader());
        } else {
            InputStream inputStream = response.body().asInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String lines =reader.readLine();
                reader.close();
                Object obj = JSON.parseObject(lines, type);
                return obj;
            } catch (Exception e){
                throw e;
            } finally {
                if (null != reader){
                    reader.close();
                }
            }
        }
    }

}
