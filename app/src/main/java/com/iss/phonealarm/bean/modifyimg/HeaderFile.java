package com.iss.phonealarm.bean.modifyimg;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.io.Serializable;


/**
 * Created by zhaocuilong on 2017/9/28.
 */
@XStreamAlias("picture")
@XStreamConverter(HeaderAttrConverter.class)
public class HeaderFile implements Serializable {

    private String type;

    private String filename;

    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HeaderFile{" +
                "type='" + type + '\'' +
                ", filename='" + filename + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
