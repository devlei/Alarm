package com.iss.phonealarm.bean.interact;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("fk_picture")
@XStreamConverter(InterAttrConverter.class)
public class InteractFile {

    private String type;

    private String value;

    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "InteractFile{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
