package phonealarm.iss.com.alarm.bean.interactquery;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by zhaocuilong on 2017/9/27.
 */
@XStreamAlias("information")
public class InterQueryBean implements Serializable {

    @XStreamAlias("result")
    private String result;

    @XStreamAlias("message")
    private String message;

    @XStreamAlias("jmhdInfoList")
    private AllQueryList jmhdInfoList;


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

    public AllQueryList getJmhdInfoList() {
        return jmhdInfoList;
    }

    public void setJmhdInfoList(AllQueryList jmhdInfoList) {
        this.jmhdInfoList = jmhdInfoList;
    }

    @Override
    public String toString() {
        return "InterQueryBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", jmhdInfoList=" + jmhdInfoList +
                '}';
    }
}
