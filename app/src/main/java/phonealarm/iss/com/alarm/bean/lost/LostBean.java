package phonealarm.iss.com.alarm.bean.lost;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 失物招领Bean
 * @author Administrator
 * 
 */
@XStreamAlias("information")
public class LostBean {

	@XStreamAlias("result")
	private String result;

	@XStreamAlias("message")
	private String message;

	@XStreamImplicit(itemFieldName = "lostInfoList")
	private List<LostInfo> lostInfoList = new ArrayList<LostInfo>();

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

	public List<LostInfo> getLostInfoList() {
		return lostInfoList;
	}

	public void setLostInfoList(List<LostInfo> lostInfoList) {
		this.lostInfoList = lostInfoList;
	}

	@Override
	public String toString() {
		return "LostBean [result=" + result + ", message=" + message
				+ ", lostInfoList=" + lostInfoList + "]";
	}

}
