package com.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by sheying on 2018/06/15.
 */
@Data
public class House<T> implements Serializable {

    private static final long serialVersionUID = 889695893318376669L;

    private T something;

    private String address;

}
