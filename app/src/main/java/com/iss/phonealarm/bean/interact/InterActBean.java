package com.iss.phonealarm.bean.interact;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 警民交互参数接收实体类
 *
 * @author Administrator
 */
@XStreamAlias("information")
public class InterActBean {

    @XStreamAlias("fk_nickname")
    private String fk_nickname;

    @XStreamAlias("fk_telenum")
    private String fk_telenum;

    @XStreamAlias("fk_appuser")
    private String fk_appuser;

    @XStreamAlias("fk_content")
    private String fk_content;

    @XStreamAlias("fk_date")
    private String fk_date;

    @XStreamAlias("filelist")
    private AllInterAct filelist;

    public String getFk_appuser() {
        return fk_appuser;
    }

    public void setFk_appuser(String fk_appuser) {
        this.fk_appuser = fk_appuser;
    }

    public String getFk_nickname() {
        return fk_nickname;
    }

    public void setFk_nickname(String fk_nickname) {
        this.fk_nickname = fk_nickname;
    }

    public String getFk_telenum() {
        return fk_telenum;
    }

    public void setFk_telenum(String fk_telenum) {
        this.fk_telenum = fk_telenum;
    }

    public String getFk_content() {
        return fk_content;
    }

    public void setFk_content(String fk_content) {
        this.fk_content = fk_content;
    }

    public String getFk_date() {
        return fk_date;
    }

    public void setFk_date(String fk_date) {
        this.fk_date = fk_date;
    }

    public AllInterAct getFilelist() {
        return filelist;
    }

    public void setFilelist(AllInterAct filelist) {
        this.filelist = filelist;
    }

    @Override
    public String toString() {
        return "InterActBean{" +
                "fk_nickname='" + fk_nickname + '\'' +
                ", fk_telenum='" + fk_telenum + '\'' +
                ", fk_appuser='" + fk_appuser + '\'' +
                ", fk_content='" + fk_content + '\'' +
                ", fk_date='" + fk_date + '\'' +
                ", filelist=" + filelist +
                '}';
    }
}
