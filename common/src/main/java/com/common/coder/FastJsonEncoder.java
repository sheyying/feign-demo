package com.common.coder;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import java.lang.reflect.Type;

/**
 * Created by sheying on 2018/06/07.
 * fastJson编码器
 */
public class FastJsonEncoder implements Encoder{
    @Override
    public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {

    }
}
