package com.common.codec;

import com.alibaba.fastjson.JSON;
import feign.RequestTemplate;
import feign.codec.Encoder;

import java.lang.reflect.Type;

/**
 * Created by sheying on 2018/06/07.
 * fastJson编码器
 */
public class FastJsonEncoder implements Encoder{

    public void encode(Object object, Type bodyType, RequestTemplate template) {
        template.body(JSON.toJSONString(object));
    }
}
