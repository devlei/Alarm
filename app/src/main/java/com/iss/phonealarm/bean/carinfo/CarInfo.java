package com.iss.phonealarm.bean.carinfo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("carInfo")
public class CarInfo implements Serializable {
    @XStreamAlias("car_id")
    private String car_id;

    @XStreamAlias("car_num")
    private String car_num;//车拍号

    @XStreamAlias("car_type")
    private String car_type;

    @XStreamAlias("car_brand")
    private String car_brand;

    @XStreamAlias("car_color")
    private String car_color;

    @XStreamAlias("car_info")
    private String car_info;

    @XStreamAlias("car_purl")
    private String car_purl;

    @XStreamAlias("car_date")
    private String car_date;//列表显示时间

    @XStreamAlias("pursuit_time")
    private String pursuit_time;//追踪时间

    @XStreamAlias("car_adress")
    private String car_adress;

    public String getCar_adress() {
        return car_adress;
    }

    public void setCar_adress(String car_adress) {
        this.car_adress = car_adress;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getCar_brand() {
        return car_brand;
    }

    public void setCar_brand(String car_brand) {
        this.car_brand = car_brand;
    }

    public String getCar_color() {
        return car_color;
    }

    public void setCar_color(String car_color) {
        this.car_color = car_color;
    }

    public String getCar_info() {
        return car_info;
    }

    public void setCar_info(String car_info) {
        this.car_info = car_info;
    }

    public String getCar_purl() {
        return car_purl;
    }

    public void setCar_purl(String car_purl) {
        this.car_purl = car_purl;
    }

    public String getCar_date() {
        return car_date;
    }

    public void setCar_date(String car_date) {
        this.car_date = car_date;
    }

    public String getPursuit_time() {
        return pursuit_time;
    }

    public void setPursuit_time(String pursuit_time) {
        this.pursuit_time = pursuit_time;
    }

    @Override
    public String toString() {
        return "CarInfo [car_id=" + car_id + ", car_num=" + car_num + ", car_type=" + car_type + ", car_brand=" +
                car_brand + ", car_color=" + car_color + ", car_info=" + car_info + ", car_purl=" + car_purl + ", " +
                "car_date=" + car_date + ", pursuit_time=" + pursuit_time + "]";
    }


}
