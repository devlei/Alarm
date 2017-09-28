package phonealarm.iss.com.alarm.bean.modifyimg;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("information")
public class ModifyHeaderImage implements Serializable {

    @XStreamAlias("userid")
    private String userid;

    @XStreamAlias("picture")
    private HeaderFile headerFile;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public HeaderFile getHeaderFile() {
        return headerFile;
    }

    public void setHeaderFile(HeaderFile headerFile) {
        this.headerFile = headerFile;
    }

    @Override
    public String toString() {
        return "ModifyHeaderImage{" +
                "userid='" + userid + '\'' +
                ", headerFile=" + headerFile +
                '}';
    }
}
 