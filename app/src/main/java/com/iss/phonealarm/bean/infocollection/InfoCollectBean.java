package com.iss.phonealarm.bean.infocollection;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by zhaocuilong on 2017/9/26.
 */
@XStreamAlias("information")
public class InfoCollectBean implements Serializable {

    @XStreamAlias("SHANGBAOID")
    private String SHANGBAOID;

    @XStreamAlias("HOUSEADRESS")
    private String HOUSEADRESS;

    @XStreamAlias("FUZENAME")
    private String FUZENAME;

    @XStreamAlias("FUZECARD")
    private String FUZERENCARD;

    @XStreamAlias("FUZETELE")
    private String FUZETELE = "";

    @XStreamAlias("FUZEHOUSE")
    private String FUZERENHOUSE = "";

    @XStreamAlias("STARTTIME")
    private String STARTTIME;

    @XStreamAlias("ENDTIME")
    private String ENDTIME;

    @XStreamAlias("HOTEL_ADRESS")
    private String HOTEL_ADRESS;

    @XStreamAlias("HOTEL_NAME")
    private String HOTEL_NAME;

    @XStreamAlias("HOME_NAME")
    private String HOME_NAME;

    @XStreamAlias("HOTEL_TELE")
    private String HOTEL_TELE = "";

    @XStreamAlias("RZR_LIST")
    private LiablePersonList liablePersonList;

    public String getSHANGBAOID() {
        return SHANGBAOID;
    }

    public void setSHANGBAOID(String SHANGBAOID) {
        this.SHANGBAOID = SHANGBAOID;
    }

    public String getHOUSEADRESS() {
        return HOUSEADRESS;
    }

    public void setHOUSEADRESS(String HOUSEADRESS) {
        this.HOUSEADRESS = HOUSEADRESS;
    }

    public String getFUZENAME() {
        return FUZENAME;
    }

    public void setFUZENAME(String FUZENAME) {
        this.FUZENAME = FUZENAME;
    }

    public String getFUZERENCARD() {
        return FUZERENCARD;
    }

    public void setFUZERENCARD(String FUZERENCARD) {
        this.FUZERENCARD = FUZERENCARD;
    }

    public String getFUZETELE() {
        return FUZETELE;
    }

    public void setFUZETELE(String FUZETELE) {
        this.FUZETELE = FUZETELE;
    }

    public String getFUZERENHOUSE() {
        return FUZERENHOUSE;
    }

    public void setFUZERENHOUSE(String FUZERENHOUSE) {
        this.FUZERENHOUSE = FUZERENHOUSE;
    }

    public String getSTARTTIME() {
        return STARTTIME;
    }

    public void setSTARTTIME(String STARTTIME) {
        this.STARTTIME = STARTTIME;
    }

    public String getENDTIME() {
        return ENDTIME;
    }

    public void setENDTIME(String ENDTIME) {
        this.ENDTIME = ENDTIME;
    }

    public LiablePersonList getLiablePersonList() {
        return liablePersonList;
    }

    public void setLiablePersonList(LiablePersonList liablePersonList) {
        this.liablePersonList = liablePersonList;
    }

    public String getHOTEL_ADRESS() {
        return HOTEL_ADRESS;
    }

    public void setHOTEL_ADRESS(String HOTEL_ADRESS) {
        this.HOTEL_ADRESS = HOTEL_ADRESS;
    }

    public String getHOTEL_NAME() {
        return HOTEL_NAME;
    }

    public void setHOTEL_NAME(String HOTEL_NAME) {
        this.HOTEL_NAME = HOTEL_NAME;
    }

    public String getHOME_NAME() {
        return HOME_NAME;
    }

    public void setHOME_NAME(String HOME_NAME) {
        this.HOME_NAME = HOME_NAME;
    }

    public String getHOTEL_TELE() {
        return HOTEL_TELE;
    }

    public void setHOTEL_TELE(String HOTEL_TELE) {
        this.HOTEL_TELE = HOTEL_TELE;
    }

    @Override
    public String toString() {
        return "InfoCollectBean{" +
                "SHANGBAOID='" + SHANGBAOID + '\'' +
                ", HOUSEADRESS='" + HOUSEADRESS + '\'' +
                ", FUZENAME='" + FUZENAME + '\'' +
                ", FUZERENCARD='" + FUZERENCARD + '\'' +
                ", FUZETELE='" + FUZETELE + '\'' +
                ", FUZERENHOUSE='" + FUZERENHOUSE + '\'' +
                ", STARTTIME='" + STARTTIME + '\'' +
                ", ENDTIME='" + ENDTIME + '\'' +
                ", HOTEL_ADRESS='" + HOTEL_ADRESS + '\'' +
                ", HOTEL_NAME='" + HOTEL_NAME + '\'' +
                ", HOME_NAME='" + HOME_NAME + '\'' +
                ", HOTEL_TELE='" + HOTEL_TELE + '\'' +
                ", liablePersonList=" + liablePersonList +
                '}';
    }
}
