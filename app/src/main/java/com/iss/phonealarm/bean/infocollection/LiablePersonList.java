package com.iss.phonealarm.bean.infocollection;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaocuilong on 2017/9/26.
 */
@XStreamAlias("RZR_LIST")
public class LiablePersonList implements Serializable {

    @XStreamImplicit(itemFieldName = "RZR")
    private List<LiablePerson> rzeList;

    public List<LiablePerson> getRzeList() {
        return rzeList;
    }

    public void setRzeList(List<LiablePerson> rzeList) {
        this.rzeList = rzeList;
    }

    @Override
    public String toString() {
        return "LiablePersonList{" +
                "rzeList=" + rzeList +
                '}';
    }
}
