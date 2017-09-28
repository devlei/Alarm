package com.iss.phonealarm.bean.lost;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaocuilong on 2017/9/26.
 */
@XStreamAlias("lostInfoList")
public class AllLost {

    @XStreamImplicit(itemFieldName = "lostInfo")
    private List<LostInfo> lostInfoList = new ArrayList<LostInfo>();

    public List<LostInfo> getLostInfoList() {
        return lostInfoList;
    }

    public void setLostInfoList(List<LostInfo> lostInfoList) {
        this.lostInfoList = lostInfoList;
    }

    @Override
    public String toString() {
        return "AllLost{" +
                "lostInfoList=" + lostInfoList +
                '}';
    }
}
