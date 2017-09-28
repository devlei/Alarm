package com.iss.phonealarm.bean.beLost;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaocuilong on 2017/9/26.
 */

@XStreamAlias("beLostInfoList")
public class AllBelost {

    @XStreamImplicit(itemFieldName = "beLostInfo")
    private List<BelostInfo> beLostInfoList = new ArrayList<BelostInfo>();

    public List<BelostInfo> getBeLostInfoList() {
        return beLostInfoList;
    }

    public void setBeLostInfoList(List<BelostInfo> beLostInfoList) {
        this.beLostInfoList = beLostInfoList;
    }

    @Override
    public String toString() {
        return "AllBelost{" +
                "beLostInfoList=" + beLostInfoList +
                '}';
    }
}
