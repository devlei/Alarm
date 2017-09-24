package phonealarm.iss.com.alarm.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 登录、注册、重置密码响应体
 * 
 * @author Administrator
 * 
 */
@XStreamAlias("information")
public class ResponseMessageBean {

	@XStreamAlias("result")
	private String result;

	@XStreamAlias("message")
	private String message;

	@XStreamAlias("username")
	private String username;
	
	@XStreamAlias("alarm_id")
	private String alarm_id;
	
	@XStreamAlias("contacts_id")
	private String contacts_id;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAlarm_id() {
		return alarm_id;
	}

	public void setAlarm_id(String alarm_id) {
		this.alarm_id = alarm_id;
	}

	public String getContacts_id() {
		return contacts_id;
	}

	public void setContacts_id(String contacts_id) {
		this.contacts_id = contacts_id;
	}
	
	

}
