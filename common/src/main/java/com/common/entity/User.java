package com.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by sheying on 2018/06/05.
 */
@Data
public class User implements Serializable{

    private static final long serialVersionUID = 889695893318376669L;

    private long id;

    private String name;

    private Integer age;

    private String sex;

    private Map<String, Object> headerInfo;
}
