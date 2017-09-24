package phonealarm.iss.com.alarm.bean.carinfo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 所有车辆实体参数接收实体类
 * @author Administrator
 */
@XStreamAlias("information")
public class InformationBean {

	@XStreamAsAttribute
	private String code;
	
	@XStreamAlias("result")
	private int result;
	
	@XStreamAlias("message")
	private String message;
	
	@XStreamAlias("carInfoList")
	private CarInfoList carInfoList;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CarInfoList getCarInfoList() {
		return carInfoList;
	}

	public void setCarInfoList(CarInfoList carInfoList) {
		this.carInfoList = carInfoList;
	}

	@Override
	public String toString() {
		return "Information [code=" + code + ", result=" + result
				+ ", message=" + message + ", carInfoList=" + carInfoList + "]";
	}
	
	
	

}
