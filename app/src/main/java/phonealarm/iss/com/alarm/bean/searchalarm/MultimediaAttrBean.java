package phonealarm.iss.com.alarm.bean.searchalarm;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * 多媒体类
 * @author Administrator
 *
 */
@XStreamAlias("attachment")
@XStreamConverter(MultAttrConverter.class)
public class MultimediaAttrBean {

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

	@Override
	public String toString() {
		return "MultimediaAttrBean{" +
				"type='" + type + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}
