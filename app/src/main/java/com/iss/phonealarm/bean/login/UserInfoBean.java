package com.iss.phonealarm.bean.login;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("information")
public class UserInfoBean implements Serializable {

    @XStreamAlias("userid")
    private String userid;

    @XStreamAlias("password")
    private String password;

    @XStreamAlias("username")
    private String username = "";//昵称

    @XStreamAlias("telephone")
    private String telephone;

    @XStreamAlias("starttime")
    private String starttime;

    @XStreamAlias("startadress")
    private String startadress = "暂未获取";

    @XStreamAlias("endtime")
    private String endtime;

    @XStreamAlias("endadress")
    private String endadress = "暂未获取";

    @XStreamAlias("endAddress")
    private String endAddress = "暂未获取";

    @XStreamAlias("imei")
    private String imei;

    @XStreamAlias("device_type")
    private String device_type;

    @XStreamAlias("clientid")
    private String clientid;

    @XStreamAlias("current_userid")
    private String current_userid;

    @XStreamAlias("new_userid")
    private String new_userid;

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public String getCurrent_userid() {
        return current_userid;
    }

    public void setCurrent_userid(String current_userid) {
        this.current_userid = current_userid;
    }

    public String getNew_userid() {
        return new_userid;
    }

    public void setNew_userid(String new_userid) {
        this.new_userid = new_userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getStartadress() {
        return startadress;
    }

    public void setStartadress(String startadress) {
        this.startadress = startadress;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getEndadress() {
        return endadress;
    }

    public void setEndadress(String endadress) {
        this.endadress = endadress;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }


    @Override
    public String toString() {
        return "UserInfoBean{" +
                "userid='" + userid + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", telephone='" + telephone + '\'' +
                ", starttime='" + starttime + '\'' +
                ", startadress='" + startadress + '\'' +
                ", endtime='" + endtime + '\'' +
                ", endadress='" + endadress + '\'' +
                ", imei='" + imei + '\'' +
                ", device_type='" + device_type + '\'' +
                ", clientid='" + clientid + '\'' +
                ", current_userid='" + current_userid + '\'' +
                ", new_userid='" + new_userid + '\'' +
                '}';
    }
}
