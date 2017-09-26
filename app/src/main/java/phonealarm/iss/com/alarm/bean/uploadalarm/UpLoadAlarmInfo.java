package phonealarm.iss.com.alarm.bean.uploadalarm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import phonealarm.iss.com.alarm.bean.searchalarm.AlarmInfoBean;

@XStreamAlias("information")
public class UpLoadAlarmInfo implements Serializable {

    @XStreamAlias("alarm_id")
    private String alarm_id;

    @XStreamAlias("alarm_phone")
    private String alarm_phone;

    @XStreamAlias("alarm_addres")
    private String alarm_addres;

    @XStreamAlias("alarm_content")
    private String alarm_content;

    @XStreamAlias("alarm_longtitude")
    private String alarm_longtitude;

    @XStreamAlias("alarm_latitude")
    private String alarm_latitude;

    @XStreamAlias("alarm_type")
    private String alarm_type;

    @XStreamAlias("filelist")
    private UploadFileList filelist;

    public UploadFileList getFilelist() {
        return filelist;
    }

    public void setFilelist(UploadFileList filelist) {
        this.filelist = filelist;
    }

    public String getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(String alarm_id) {
        this.alarm_id = alarm_id;
    }

    public String getAlarm_phone() {
        return alarm_phone;
    }

    public void setAlarm_phone(String alarm_phone) {
        this.alarm_phone = alarm_phone;
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

    public String getAlarm_type() {
        return alarm_type;
    }

    public void setAlarm_type(String alarm_type) {
        this.alarm_type = alarm_type;
    }

}
