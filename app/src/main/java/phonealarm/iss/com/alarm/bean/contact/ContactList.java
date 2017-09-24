package phonealarm.iss.com.alarm.bean.contact;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("contactslist")
public class ContactList {
	
	@XStreamImplicit(itemFieldName = "contacts")
	private List<AddContact> contacts = new ArrayList<AddContact>();

	public List<AddContact> getContacts() {
		return contacts;
	}

	public void setContacts(List<AddContact> contacts) {
		this.contacts = contacts;
	}
	
	

}
