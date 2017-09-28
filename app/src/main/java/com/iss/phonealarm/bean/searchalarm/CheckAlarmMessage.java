package com.iss.phonealarm.bean.searchalarm;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/*
 * 查询报警记录
 */
@XStreamAlias("information")
public class CheckAlarmMessage implements Serializable {

    @XStreamAlias("result")
    private int result;
    @XStreamAlias("message")
    private String message;
    @XStreamAlias("alarmlist")
    private AlarmInfoBeanList alarmlist;

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

    public AlarmInfoBeanList getAlarmlist() {
        return alarmlist;
    }

    public void setAlarmlist(AlarmInfoBeanList alarmlist) {
        this.alarmlist = alarmlist;
    }

    @Override
    public String toString() {
        return "CheckAlarmMessage{" + "result='" + result + '\'' + ", message='" + message + '\'' + ", alarmlist=" +
                alarmlist + '}';
    }
}
