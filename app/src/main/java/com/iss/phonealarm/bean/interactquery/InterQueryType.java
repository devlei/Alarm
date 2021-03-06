package com.iss.phonealarm.bean.interactquery;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.io.Serializable;

/**
 * Created by zhaocuilong on 2017/9/27.
 */
@XStreamAlias("fileurl")
@XStreamConverter(InterQueryAttrConverter.class)
public class InterQueryType implements Serializable {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "InterQueryType{" + "value='" + value + '\'' + '}';
    }
}
