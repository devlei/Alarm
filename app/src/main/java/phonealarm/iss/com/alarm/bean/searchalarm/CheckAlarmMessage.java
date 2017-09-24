package phonealarm.iss.com.alarm.bean.searchalarm;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/*
 * 查询报警记录
 */
@XStreamAlias("information")
public class CheckAlarmMessage {

	@XStreamAlias("result")
	private String result;
	@XStreamAlias("message")
	private String message;
	@XStreamAlias("alarmlist")
	private AlarmInfoBeanList alarmlist;

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

	public AlarmInfoBeanList getAlarmlist() {
		return alarmlist;
	}

	public void setAlarmlist(AlarmInfoBeanList alarmlist) {
		this.alarmlist = alarmlist;
	}

}
