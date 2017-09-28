package com.iss.phonealarm.bean.beLost;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("beLostInfo")
public class BelostInfo implements Serializable {

    @XStreamAlias("beLost_id")
    private String beLost_id;

    @XStreamAlias("beLost_name")
    private String beLost_name;

    @XStreamAlias("beLost_sex")
    private String beLost_sex;

    @XStreamAlias("beLost_age")
    private String beLost_age;

    @XStreamAlias("beLost_site")
    private String beLost_site;

    @XStreamAlias("beLost_date")
    private String beLost_date;

    @XStreamAlias("beLost_bithdate")
    private String beLost_bithdate;

    @XStreamAlias("beLost_weigth")
    private String beLost_weigth;
    @XStreamAlias("beLost_hairstyle")
    private String beLost_hairstyle;
    @XStreamAlias("beLost_information")
    private String beLost_information;

    @XStreamAlias("beLost_telename")
    private String beLost_telename;

    @XStreamAlias("beLost_telenum")
    private String beLost_telenum;

    @XStreamAlias("beLost_pUrl")
    private String beLost_pUrl;

    @XStreamAlias("pursuit_time")
    private String pursuit_time;

    public String getBeLost_id() {
        return beLost_id;
    }

    public void setBeLost_id(String beLost_id) {
        this.beLost_id = beLost_id;
    }

    public String getBeLost_name() {
        return beLost_name;
    }

    public void setBeLost_name(String beLost_name) {
        this.beLost_name = beLost_name;
    }

    public String getBeLost_sex() {
        return beLost_sex;
    }

    public void setBeLost_sex(String beLost_sex) {
        this.beLost_sex = beLost_sex;
    }

    public String getBeLost_age() {
        return beLost_age;
    }

    public void setBeLost_age(String beLost_age) {
        this.beLost_age = beLost_age;
    }

    public String getBeLost_site() {
        return beLost_site;
    }

    public void setBeLost_site(String beLost_site) {
        this.beLost_site = beLost_site;
    }

    public String getBeLost_date() {
        return beLost_date;
    }

    public void setBeLost_date(String beLost_date) {
        this.beLost_date = beLost_date;
    }

    public String getBeLost_bithdate() {
        return beLost_bithdate;
    }

    public void setBeLost_bithdate(String beLost_bithdate) {
        this.beLost_bithdate = beLost_bithdate;
    }

    public String getBeLost_weigth() {
        return beLost_weigth;
    }

    public void setBeLost_weigth(String beLost_weigth) {
        this.beLost_weigth = beLost_weigth;
    }

    public String getBeLost_hairstyle() {
        return beLost_hairstyle;
    }

    public void setBeLost_hairstyle(String beLost_hairstyle) {
        this.beLost_hairstyle = beLost_hairstyle;
    }

    public String getBeLost_information() {
        return beLost_information;
    }

    public void setBeLost_information(String beLost_information) {
        this.beLost_information = beLost_information;
    }

    public String getBeLost_telename() {
        return beLost_telename;
    }

    public void setBeLost_telename(String beLost_telename) {
        this.beLost_telename = beLost_telename;
    }

    public String getBeLost_telenum() {
        return beLost_telenum;
    }

    public void setBeLost_telenum(String beLost_telenum) {
        this.beLost_telenum = beLost_telenum;
    }

    public String getBeLost_pUrl() {
        return beLost_pUrl;
    }

    public void setBeLost_pUrl(String beLost_pUrl) {
        this.beLost_pUrl = beLost_pUrl;
    }

    public String getPursuit_time() {
        return pursuit_time;
    }

    public void setPursuit_time(String pursuit_time) {
        this.pursuit_time = pursuit_time;
    }

    @Override
    public String toString() {
        return "BelostInfo [beLost_id=" + beLost_id + ", beLost_name=" + beLost_name + ", beLost_sex=" + beLost_sex + ", beLost_age=" + beLost_age + ", beLost_site=" + beLost_site + ", beLost_date=" + beLost_date + ", beLost_bithdate=" + beLost_bithdate + ", beLost_weigth=" + beLost_weigth + ", beLost_hairstyle=" + beLost_hairstyle + ", beLost_information=" + beLost_information + ", beLost_telename=" + beLost_telename + ", beLost_telenum=" + beLost_telenum + ", beLost_pUrl=" + beLost_pUrl + ", pursuit_time=" + pursuit_time + "]";
    }

}
