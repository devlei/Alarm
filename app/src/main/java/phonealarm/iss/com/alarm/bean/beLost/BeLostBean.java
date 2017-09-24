package phonealarm.iss.com.alarm.bean.beLost;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("information")
public class BeLostBean {

	@XStreamAsAttribute
	private String code;

	@XStreamAlias("result")
	private String result;

	@XStreamAlias("message")
	private String message;

	@XStreamImplicit(itemFieldName = "beLostInfoList")
	private List<BelostInfo> beLostInfoList = new ArrayList<BelostInfo>();

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

	public List<BelostInfo> getBeLostInfoList() {
		return beLostInfoList;
	}

	public void setBeLostInfoList(List<BelostInfo> beLostInfoList) {
		this.beLostInfoList = beLostInfoList;
	}

	@Override
	public String toString() {
		return "BeLostBean [code=" + code + ", result=" + result + ", message="
				+ message + ", beLostInfoList=" + beLostInfoList + "]";
	}

}
