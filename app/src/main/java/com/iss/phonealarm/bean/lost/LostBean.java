package com.iss.phonealarm.bean.lost;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 失物招领Bean
 *
 * @author Administrator
 */
@XStreamAlias("information")
public class LostBean {

    @XStreamAlias("result")
    private int result;

    @XStreamAlias("message")
    private String message;

    @XStreamAlias("lostInfoList")
    private AllLost allLost;

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

    public AllLost getAllLost() {
        return allLost;
    }

    public void setAllLost(AllLost allLost) {
        this.allLost = allLost;
    }

    @Override
    public String toString() {
        return "LostBean{" + "result='" + result + '\'' + ", message='" + message + '\'' + ", allLost=" + allLost + '}';
    }
}
