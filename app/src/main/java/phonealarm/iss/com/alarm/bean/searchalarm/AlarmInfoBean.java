package phonealarm.iss.com.alarm.bean.searchalarm;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@XStreamAlias("alarminfo")
public class AlarmInfoBean implements Serializable {

    @XStreamAlias("alarm_id")
    private String alarm_id;

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

    @XStreamImplicit(itemFieldName = "attach_list")
    private List<MultimediaAttrBean> attach_list = new ArrayList<MultimediaAttrBean>();

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

    public List<MultimediaAttrBean> getAttach_list() {
        return attach_list;
    }

    public void setAttach_list(List<MultimediaAttrBean> attach_list) {
        this.attach_list = attach_list;
    }


}
