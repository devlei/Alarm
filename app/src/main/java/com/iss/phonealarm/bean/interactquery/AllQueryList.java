package com.iss.phonealarm.bean.interactquery;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by zhaocuilong on 2017/9/27.
 */
@XStreamAlias("jmhdInfoList")
public class AllQueryList {

    @XStreamImplicit(itemFieldName = "jmhdInfo")
    private List<InterQueryInfo> jmhdInfo;

    public List<InterQueryInfo> getJmhdInfo() {
        return jmhdInfo;
    }

    public void setJmhdInfo(List<InterQueryInfo> jmhdInfo) {
        this.jmhdInfo = jmhdInfo;
    }

    @Override
    public String toString() {
        return "AllQueryList{" +
                "jmhdInfo=" + jmhdInfo +
                '}';
    }

}
