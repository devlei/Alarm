package phonealarm.iss.com.alarm.bean.infocollection;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by zhaocuilong on 2017/9/26.
 */
@XStreamAlias("RZR")
public class LiablePerson implements Serializable {

    @XStreamAlias("PNAME")
    private String PNAME;

    @XStreamAlias("PIDCARD")
    private String PIDCARD;

    @XStreamAlias("PTELE")
    private String PTELE = "";

    public String getPNAME() {
        return PNAME;
    }

    public void setPNAME(String PNAME) {
        this.PNAME = PNAME;
    }

    public String getPIDCARD() {
        return PIDCARD;
    }

    public void setPIDCARD(String PIDCARD) {
        this.PIDCARD = PIDCARD;
    }

    public String getPTELE() {
        return PTELE;
    }

    public void setPTELE(String PTELE) {
        this.PTELE = PTELE;
    }

    @Override
    public String toString() {
        return "LiablePerson{" +
                "PNAME='" + PNAME + '\'' +
                ", PIDCARD='" + PIDCARD + '\'' +
                ", PTELE='" + PTELE + '\'' +
                '}';
    }
}
