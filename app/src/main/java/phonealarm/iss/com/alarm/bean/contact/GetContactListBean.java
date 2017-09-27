package phonealarm.iss.com.alarm.bean.contact;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("information")
public class GetContactListBean {

    @XStreamAlias("result")
    private int result;

    @XStreamAlias("message")
    private String message;

    @XStreamAlias("contactslist")
    private ContactList contactslist;

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

    public ContactList getContactslist() {
        return contactslist;
    }

    public void setContactslist(ContactList contactslist) {
        this.contactslist = contactslist;
    }

    @Override
    public String toString() {
        return "GetContactListBean{" + "result='" + result + '\'' + ", message='" + message + '\'' + ", " +
                "contactslist=" + contactslist + '}';
    }
}
