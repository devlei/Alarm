package phonealarm.iss.com.alarm.bean.suspect;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 嫌疑犯实体类
 * 
 * @author Administrator
 * 
 */
@XStreamAlias("information")
public class SuspectBean {

	@XStreamAsAttribute
	private String code;

	@XStreamAlias("result")
	private String result;

	@XStreamAlias("message")
	private String message;

	@XStreamImplicit(itemFieldName = "suspectInfoList")
	private List<SuspectInfo> suspectInfoList = new ArrayList<SuspectInfo>();

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

	public List<SuspectInfo> getSuspectInfoList() {
		return suspectInfoList;
	}

	public void setSuspectInfoList(List<SuspectInfo> suspectInfoList) {
		this.suspectInfoList = suspectInfoList;
	}

	@Override
	public String toString() {
		return "SuspectBean [code=" + code + ", result=" + result
				+ ", message=" + message + ", suspectInfoList="
				+ suspectInfoList + "]";
	}

}
