package phonealarm.iss.com.alarm.bean.contact;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("contactslist")
public class ContactList {

    @XStreamImplicit(itemFieldName = "contacts")
    private List<GetContact> contacts = new ArrayList<GetContact>();

    public List<GetContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<GetContact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "ContactList{" +
                "contacts=" + contacts +
                '}';
    }
}
