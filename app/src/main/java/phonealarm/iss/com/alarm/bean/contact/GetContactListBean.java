package phonealarm.iss.com.alarm.bean.contact;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("information")
public class GetContactListBean {

	@XStreamAlias("result")
	private String result;

	@XStreamAlias("message")
	private String message;

	@XStreamAlias("contactslist")
	private ContactList contactslist;

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

	public ContactList getContactslist() {
		return contactslist;
	}

	public void setContactslist(ContactList contactslist) {
		this.contactslist = contactslist;
	}

}
