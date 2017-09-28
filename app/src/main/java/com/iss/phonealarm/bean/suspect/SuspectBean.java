package com.iss.phonealarm.bean.suspect;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 嫌疑犯实体类
 *
 * @author Administrator
 */
@XStreamAlias("information")
public class SuspectBean {

    @XStreamAsAttribute
    private String code;

    @XStreamAlias("result")
    private int result;

    @XStreamAlias("message")
    private String message;

    @XStreamAlias("suspectInfoList")
    private AllSuspectInfo suspectInfoList;

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

    public AllSuspectInfo getSuspectInfoList() {
        return suspectInfoList;
    }

    public void setSuspectInfoList(AllSuspectInfo suspectInfoList) {
        this.suspectInfoList = suspectInfoList;
    }

    @Override
    public String toString() {
        return "SuspectBean{" + "code='" + code + '\'' + ", result='" + result + '\'' + ", message='" + message +
                '\'' + ", suspectInfoList=" + suspectInfoList + '}';
    }
}
