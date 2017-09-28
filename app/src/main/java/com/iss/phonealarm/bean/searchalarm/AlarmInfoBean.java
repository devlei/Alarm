package com.iss.phonealarm.bean.searchalarm;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Administrator
 */
@XStreamAlias("alarminfo")
public class AlarmInfoBean implements Serializable {

    @XStreamAlias("alarm_id")
    private String alarm_id;

    @XStreamAlias("alarm_phone")
    private String alarm_phone;

    @XStreamAlias("alarm_addres")
    private String alarm_addres;

    @XStreamAlias("alarm_content")
    private String alarm_content;

    @XStreamAlias("rptalarm_time")
    private String rptalarm_time;

    @XStreamAlias("alarm_longtitude")
    private String alarm_longtitude;

    @XStreamAlias("alarm_latitude")
    private String alarm_latitude;

    @XStreamAlias("alarm_status")
    private String alarm_status;

    @XStreamAlias("alarm_type")
    private String alarm_type;

    @XStreamAlias("attach_list")
    private AlarmFilesList alarmFilesList;

    public AlarmFilesList getAlarmFilesList() {
        return alarmFilesList;
    }

    public void setAlarmFilesList(AlarmFilesList alarmFilesList) {
        this.alarmFilesList = alarmFilesList;
    }

    public String getAlarm_phone() {
        return alarm_phone;
    }

    public void setAlarm_phone(String alarm_phone) {
        this.alarm_phone = alarm_phone;
    }

    public String getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(String alarm_id) {
        this.alarm_id = alarm_id;
    }

    public String getAlarm_addres() {
        return alarm_addres;
    }

    public void setAlarm_addres(String alarm_addres) {
        this.alarm_addres = alarm_addres;
    }

    public String getAlarm_content() {
        return alarm_content;
    }

    public void setAlarm_content(String alarm_content) {
        this.alarm_content = alarm_content;
    }

    public String getRptalarm_time() {
        return rptalarm_time;
    }

    public void setRptalarm_time(String rptalarm_time) {
        this.rptalarm_time = rptalarm_time;
    }

    public String getAlarm_longtitude() {
        return alarm_longtitude;
    }

    public void setAlarm_longtitude(String alarm_longtitude) {
        this.alarm_longtitude = alarm_longtitude;
    }

    public String getAlarm_latitude() {
        return alarm_latitude;
    }

    public void setAlarm_latitude(String alarm_latitude) {
        this.alarm_latitude = alarm_latitude;
    }

    public String getAlarm_status() {
        return alarm_status;
    }

    public void setAlarm_status(String alarm_status) {
        this.alarm_status = alarm_status;
    }

    public String getAlarm_type() {
        return alarm_type;
    }

    public void setAlarm_type(String alarm_type) {
        this.alarm_type = alarm_type;
    }

    @Override
    public String toString() {
        return "AlarmInfoBean{" +
                "alarm_id='" + alarm_id + '\'' +
                ", alarm_phone='" + alarm_phone + '\'' +
                ", alarm_addres='" + alarm_addres + '\'' +
                ", alarm_content='" + alarm_content + '\'' +
                ", rptalarm_time='" + rptalarm_time + '\'' +
                ", alarm_longtitude='" + alarm_longtitude + '\'' +
                ", alarm_latitude='" + alarm_latitude + '\'' +
                ", alarm_status='" + alarm_status + '\'' +
                ", alarm_type='" + alarm_type + '\'' +
                ", alarmFilesList=" + alarmFilesList +
                '}';
    }
}
