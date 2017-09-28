package com.iss.phonealarm.bean.contact;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.UUID;

/**
 * 添加联系人
 *
 * @author Administrator
 */
@XStreamAlias("information")
public class AddContact implements Serializable {

    @XStreamAlias("contacts_id")
    private String contacts_id = UUID.randomUUID().toString();

    @XStreamAlias("contacts_name")
    private String contacts_name;

    @XStreamAlias("contacts_bind")
    private String contacts_bind;

    @XStreamAlias("contacts_address")
    private String contacts_address = "";

    @XStreamAlias("contacts_phone")
    private String contacts_phone;

    @XStreamAlias("contacts_type")
    private String contacts_type;

    @XStreamAlias("contacts_sex")
    private String contacts_sex;

    public String getContacts_type() {
        return contacts_type;
    }

    public void setContacts_type(String contacts_type) {
        this.contacts_type = contacts_type;
    }

    public String getContacts_sex() {
        return contacts_sex;
    }

    public void setContacts_sex(String contacts_sex) {
        this.contacts_sex = contacts_sex;
    }

    public String getContacts_id() {
        return contacts_id;
    }

    public void setContacts_id(String contacts_id) {
        this.contacts_id = contacts_id;
    }

    public String getContacts_name() {
        return contacts_name;
    }

    public void setContacts_name(String contacts_name) {
        this.contacts_name = contacts_name;
    }

    public String getContacts_bind() {
        return contacts_bind;
    }

    public void setContacts_bind(String contacts_bind) {
        this.contacts_bind = contacts_bind;
    }

    public String getContacts_address() {
        return contacts_address;
    }

    public void setContacts_address(String contacts_address) {
        this.contacts_address = contacts_address;
    }

    public String getContacts_phone() {
        return contacts_phone;
    }

    public void setContacts_phone(String contacts_phone) {
        this.contacts_phone = contacts_phone;
    }

    @Override
    public String toString() {
        return "AddContact{" +
                "contacts_id='" + contacts_id + '\'' +
                ", contacts_name='" + contacts_name + '\'' +
                ", contacts_bind='" + contacts_bind + '\'' +
                ", contacts_address='" + contacts_address + '\'' +
                ", contacts_phone='" + contacts_phone + '\'' +
                ", contacts_type='" + contacts_type + '\'' +
                ", contacts_sex='" + contacts_sex + '\'' +
                '}';
    }
}
