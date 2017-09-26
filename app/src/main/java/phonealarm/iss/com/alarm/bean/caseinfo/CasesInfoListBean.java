package phonealarm.iss.com.alarm.bean.caseinfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 要案返回接受参数实体类
 *
 * @author Administrator
 */
@XStreamAlias("information")
public class CasesInfoListBean implements Serializable {

    @XStreamAsAttribute
    private String code;

    @XStreamAlias("result")
    private String result;

    @XStreamAlias("message")
    private String message;

    @XStreamAlias("casesInfoList")
    private AllCaseInfo casesInfoList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public AllCaseInfo getCasesInfoList() {
        return casesInfoList;
    }

    public void setCasesInfoList(AllCaseInfo casesInfoList) {
        this.casesInfoList = casesInfoList;
    }

    @Override
    public String toString() {
        return "CasesInfoListBean{" +
                "code='" + code + '\'' +
                ", result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", casesInfoList=" + casesInfoList +
                '}';
    }
}
