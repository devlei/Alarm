package phonealarm.iss.com.alarm.bean.interact;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("fk_picture")
@XStreamConverter(InterAttrConverter.class)
public class InteractFile {

	private String type;

	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
