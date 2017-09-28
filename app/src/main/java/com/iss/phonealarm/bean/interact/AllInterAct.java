package com.iss.phonealarm.bean.interact;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by zhaocuilong on 2017/9/27.
 */
@XStreamAlias("filelist")
public class AllInterAct {
    @XStreamImplicit(itemFieldName = "fk_picture")
    private List<InteractFile> filelist;

    public List<InteractFile> getFilelist() {
        return filelist;
    }

    public void setFilelist(List<InteractFile> filelist) {
        this.filelist = filelist;
    }

    @Override
    public String toString() {
        return "AllInterAct{" +
                "filelist=" + filelist +
                '}';
    }
}
