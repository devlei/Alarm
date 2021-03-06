package com.iss.phonealarm.bean.searchalarm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 报警信息数据接收实体
 *
 * @author Administrator
 */
@XStreamAlias("alarmlist")
public class AlarmInfoBeanList implements Serializable {

    @XStreamImplicit(itemFieldName = "alarminfo")
    private List<AlarmInfoBean> alarminfo = new ArrayList<AlarmInfoBean>();

    public List<AlarmInfoBean> getAlarminfo() {
        return alarminfo;
    }

    public void setAlarminfo(List<AlarmInfoBean> alarminfo) {
        this.alarminfo = alarminfo;
    }

    @Override
    public String toString() {
        return "AlarmInfoBeanList{" +
                "alarminfo=" + alarminfo +
                '}';
    }
}
