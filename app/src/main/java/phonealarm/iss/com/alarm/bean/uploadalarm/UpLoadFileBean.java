package phonealarm.iss.com.alarm.bean.uploadalarm;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("file")
@XStreamConverter(UpLoadAttrConverter.class)
public class UpLoadFileBean {

    private String type = "";

    private String value = "";

    private String filename = "";

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

}
