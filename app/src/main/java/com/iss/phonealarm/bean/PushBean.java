package com.iss.phonealarm.bean;

import java.io.Serializable;

/**
 * Created by zhaocuilong on 2017/10/31.
 */

public class PushBean implements Serializable {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
