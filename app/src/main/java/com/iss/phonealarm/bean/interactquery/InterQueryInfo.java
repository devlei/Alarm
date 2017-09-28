package com.iss.phonealarm.bean.interactquery;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by zhaocuilong on 2017/9/27.
 */
@XStreamAlias("jmhdInfo")
public class InterQueryInfo implements Serializable {

    @XStreamAlias("jmhd_id")
    private String jmhd_id;

    @XStreamAlias("jmhd_nickname")
    private String jmhd_nickname;

    @XStreamAlias("jmhd_telenum")
    private String jmhd_telenum;

    @XStreamAlias("fk_content")
    private String fk_content;

    @XStreamAlias("reply_content")
    private String reply_content;

    @XStreamAlias("fk_date")
    private String fk_date;

    @XStreamAlias("reply_date")
    private String reply_date;

    @XStreamAlias("files")
    private InterQueryFile files;

    public String getJmhd_id() {
        return jmhd_id;
    }

    public void setJmhd_id(String jmhd_id) {
        this.jmhd_id = jmhd_id;
    }

    public String getJmhd_nickname() {
        return jmhd_nickname;
    }

    public void setJmhd_nickname(String jmhd_nickname) {
        this.jmhd_nickname = jmhd_nickname;
    }

    public String getJmhd_telenum() {
        return jmhd_telenum;
    }

    public void setJmhd_telenum(String jmhd_telenum) {
        this.jmhd_telenum = jmhd_telenum;
    }

    public String getFk_content() {
        return fk_content;
    }

    public void setFk_content(String fk_content) {
        this.fk_content = fk_content;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public String getFk_date() {
        return fk_date;
    }

    public void setFk_date(String fk_date) {
        this.fk_date = fk_date;
    }

    public String getReply_date() {
        return reply_date;
    }

    public void setReply_date(String reply_date) {
        this.reply_date = reply_date;
    }

    public InterQueryFile getFiles() {
        return files;
    }

    public void setFiles(InterQueryFile files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "InterQueryInfo{" + "jmhd_id='" + jmhd_id + '\'' + ", jmhd_nickname='" + jmhd_nickname + '\'' + ", " +
                "jmhd_telenum='" + jmhd_telenum + '\'' + ", fk_content='" + fk_content + '\'' + ", reply_content='" +
                reply_content + '\'' + ", fk_date='" + fk_date + '\'' + ", reply_date='" + reply_date + '\'' + ", " +
                "files=" + files + '}';
    }
}
