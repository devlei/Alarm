package com.iss.phonealarm.bean.alarmjudge;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * 出警评价
 *
 * @author Administrator
 */
@XStreamAlias("information")
public class AlarmJudgeBean implements Serializable {

    @XStreamAlias("alarm_id")
    private String alarm_id;

    @XStreamAlias("assess_content")
    private String assess_content;

    @XStreamAlias("assess_level")
    private String assess_level;

    @XStreamAlias("phone")
    private String phone;

    public String getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(String alarm_id) {
        this.alarm_id = alarm_id;
    }

    public String getAssess_content() {
        return assess_content;
    }

    public void setAssess_content(String assess_content) {
        this.assess_content = assess_content;
    }

    public String getAssess_level() {
        return assess_level;
    }

    public void setAssess_level(String assess_level) {
        this.assess_level = assess_level;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "AlarmJudgeBean{" +
                "alarm_id='" + alarm_id + '\'' +
                ", assess_content='" + assess_content + '\'' +
                ", assess_level='" + assess_level + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
