package com.common.codec;

import com.alibaba.fastjson.JSON;
import feign.RequestTemplate;
import feign.codec.Encoder;
import org.springframework.http.MediaType;

import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by sheying on 2018/06/07.
 * fastJson编码器
 */
public class FastJsonEncoder implements Encoder{
    private static final String HEADER_CONTENT_TYPE = "Content-Type";

    public void encode(Object object, Type bodyType, RequestTemplate template) {
        Map<String, Collection<String>> headers = template.headers();
        System.out.println(headers.isEmpty());
        Collection<String> contentTypes = (Collection)headers.get(HEADER_CONTENT_TYPE);

        // 没有设置Content-Type，默认application/json;charset=UTF-8
        if (null == contentTypes || contentTypes.isEmpty()){
            contentTypes = new ArrayList<String>();
            contentTypes.add(MediaType.APPLICATION_JSON_UTF8_VALUE);
            headers.put(HEADER_CONTENT_TYPE, contentTypes);
        }

        template.headers(headers);
        template.body(JSON.toJSONString(object).getBytes(), Charset.forName("UTF-8"));
    }

}
