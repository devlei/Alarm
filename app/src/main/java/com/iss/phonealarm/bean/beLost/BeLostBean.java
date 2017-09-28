package com.iss.phonealarm.bean.beLost;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("information")
public class BeLostBean {

    @XStreamAsAttribute
    private String code;

    @XStreamAlias("result")
    private int result;

    @XStreamAlias("message")
    private String message;

    @XStreamAlias("beLostInfoList")
    private AllBelost allBelost;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AllBelost getAllBelost() {
        return allBelost;
    }

    public void setAllBelost(AllBelost allBelost) {
        this.allBelost = allBelost;
    }

    @Override
    public String toString() {
        return "BeLostBean{" + "code='" + code + '\'' + ", result='" + result + '\'' + ", message='" + message + '\''
                + ", allBelost=" + allBelost + '}';
    }
}
