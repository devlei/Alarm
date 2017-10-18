package com.iss.phonealarm.bean.caseinfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author Administrator
 */
@XStreamAlias("casesInfo")
public class CaseInfo implements Serializable {

    @XStreamAlias("cases_id")
    private String cases_id;

    @XStreamAlias("cases_theme")
    private String cases_theme;

    @XStreamAlias("cases_infoType")
    private String cases_infoType;

    @XStreamAlias("cases_sendtime")
    private String cases_sendtime;//列表页时间

    @XStreamAlias("cases_content")
    private String cases_content;

    @XStreamAlias("pursuit_time")
    private String pursuit_time;

    @XStreamAlias("sendproxy")
    private String sendproxy;//来源发布机构

    @XStreamAlias("weburl")
    private String weburl = "";


    @XStreamImplicit(itemFieldName = "cases_files")
    private List<CaseFileInfo> cases_files = new ArrayList<CaseFileInfo>();

    public String getSendproxy() {
        return sendproxy;
    }

    public void setSendproxy(String sendproxy) {
        this.sendproxy = sendproxy;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public String getCases_id() {
        return cases_id;
    }

    public void setCases_id(String cases_id) {
        this.cases_id = cases_id;
    }

    public String getCases_theme() {
        return cases_theme;
    }

    public void setCases_theme(String cases_theme) {
        this.cases_theme = cases_theme;
    }

    public String getCases_infoType() {
        return cases_infoType;
    }

    public void setCases_infoType(String cases_infoType) {
        this.cases_infoType = cases_infoType;
    }

    public String getCases_sendtime() {
        return cases_sendtime;
    }

    public void setCases_sendtime(String cases_sendtime) {
        this.cases_sendtime = cases_sendtime;
    }

    public String getCases_content() {
        return cases_content;
    }

    public void setCases_content(String cases_content) {
        this.cases_content = cases_content;
    }

    public String getPursuit_time() {
        return pursuit_time;
    }

    public void setPursuit_time(String pursuit_time) {
        this.pursuit_time = pursuit_time;
    }

    public List<CaseFileInfo> getCases_files() {
        return cases_files;
    }

    public void setCases_files(List<CaseFileInfo> cases_files) {
        this.cases_files = cases_files;
    }

    @Override
    public String toString() {
        return "CaseInfo{" +
                "cases_id='" + cases_id + '\'' +
                ", cases_theme='" + cases_theme + '\'' +
                ", cases_infoType='" + cases_infoType + '\'' +
                ", cases_sendtime='" + cases_sendtime + '\'' +
                ", cases_content='" + cases_content + '\'' +
                ", pursuit_time='" + pursuit_time + '\'' +
                ", sendproxy='" + sendproxy + '\'' +
                ", weburl='" + weburl + '\'' +
                ", cases_files=" + cases_files +
                '}';
    }
}
