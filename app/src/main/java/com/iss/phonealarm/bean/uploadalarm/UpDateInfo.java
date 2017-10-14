package com.iss.phonealarm.bean.uploadalarm;

import com.iss.phonealarm.bean.UpDate;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by zhaocuilong on 2017/10/14.
 */
@XStreamAlias("information")
public class UpDateInfo implements Serializable {

    @XStreamAlias("result")
    private String result;
    @XStreamAlias("message")
    private String message;
    @XStreamAlias("aboutInfo")
    private UpDate aboutInfo;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UpDate getAboutInfo() {
        return aboutInfo;
    }

    public void setAboutInfo(UpDate aboutInfo) {
        this.aboutInfo = aboutInfo;
    }

    @Override
    public String toString() {
        return "UpDateInfo{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", aboutInfo=" + aboutInfo +
                '}';
    }
}
