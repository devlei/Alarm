package com.iss.phonealarm.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by zhaocuilong on 2017/10/14.
 */
@XStreamAlias("aboutInfo")
public class UpDate implements Serializable {

    @XStreamAlias("banbenhao")
    private String banbenhao;
    @XStreamAlias("downloadUrl")
    private String downloadUrl;

    public String getBanbenhao() {
        return banbenhao;
    }

    public void setBanbenhao(String banbenhao) {
        this.banbenhao = banbenhao;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Override
    public String toString() {
        return "UpDate{" +
                "banbenhao='" + banbenhao + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }
}
