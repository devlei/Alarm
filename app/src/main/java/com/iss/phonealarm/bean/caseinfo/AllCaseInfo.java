package com.iss.phonealarm.bean.caseinfo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaocuilong on 2017/9/26.
 */
@XStreamAlias("casesInfoList")
public class AllCaseInfo {

    @XStreamImplicit(itemFieldName = "casesInfo")
    private List<CaseInfo> casesInfoList = new ArrayList<CaseInfo>();

    public List<CaseInfo> getCasesInfoList() {
        return casesInfoList;
    }

    public void setCasesInfoList(List<CaseInfo> casesInfoList) {
        this.casesInfoList = casesInfoList;
    }

    @Override
    public String toString() {
        return "AllCaseInfo{" +
                "casesInfoList=" + casesInfoList +
                '}';
    }
}
