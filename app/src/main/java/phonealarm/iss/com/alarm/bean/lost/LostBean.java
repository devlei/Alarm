package phonealarm.iss.com.alarm.bean.lost;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 失物招领Bean
 *
 * @author Administrator
 */
@XStreamAlias("information")
public class LostBean {

    @XStreamAlias("result")
    private String result;

    @XStreamAlias("message")
    private String message;

    @XStreamAlias("lostInfoList")
    private AllLost allLost;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AllLost getAllLost() {
        return allLost;
    }

    public void setAllLost(AllLost allLost) {
        this.allLost = allLost;
    }

    @Override
    public String toString() {
        return "LostBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", allLost=" + allLost +
                '}';
    }
}
