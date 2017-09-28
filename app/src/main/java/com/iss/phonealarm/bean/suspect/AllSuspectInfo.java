package com.iss.phonealarm.bean.suspect;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaocuilong on 2017/9/26.
 */
@XStreamAlias("suspectInfoList")
public class AllSuspectInfo {

    @XStreamImplicit(itemFieldName = "suspectInfo")
    private List<SuspectInfo> suspectInfoList = new ArrayList<SuspectInfo>();

    public List<SuspectInfo> getSuspectInfoList() {
        return suspectInfoList;
    }

    public void setSuspectInfoList(List<SuspectInfo> suspectInfoList) {
        this.suspectInfoList = suspectInfoList;
    }

    @Override
    public String toString() {
        return "AllSuspectInfo{" +
                "suspectInfoList=" + suspectInfoList +
                '}';
    }
}
