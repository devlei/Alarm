package phonealarm.iss.com.alarm.bean.lost;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("lostInfo")
public class LostInfo {

	@XStreamAlias("lost_id")
	private String lost_id;

	@XStreamAlias("lost_name")
	private String lost_name;

	@XStreamAlias("lost_site")
	private String lost_site;

	@XStreamAlias("lost_date")
	private String lost_date;

	@XStreamAlias("lost_info")
	private String lost_info;

	@XStreamAlias("lost_unit")
	private String lost_unit;

	@XStreamAlias("pursuit_time")
	private String pursuit_time;

	public String getLost_id() {
		return lost_id;
	}

	public void setLost_id(String lost_id) {
		this.lost_id = lost_id;
	}

	public String getLost_name() {
		return lost_name;
	}

	public void setLost_name(String lost_name) {
		this.lost_name = lost_name;
	}

	public String getLost_site() {
		return lost_site;
	}

	public void setLost_site(String lost_site) {
		this.lost_site = lost_site;
	}

	public String getLost_date() {
		return lost_date;
	}

	public void setLost_date(String lost_date) {
		this.lost_date = lost_date;
	}

	public String getLost_info() {
		return lost_info;
	}

	public void setLost_info(String lost_info) {
		this.lost_info = lost_info;
	}

	public String getLost_unit() {
		return lost_unit;
	}

	public void setLost_unit(String lost_unit) {
		this.lost_unit = lost_unit;
	}

	public String getPursuit_time() {
		return pursuit_time;
	}

	public void setPursuit_time(String pursuit_time) {
		this.pursuit_time = pursuit_time;
	}

	@Override
	public String toString() {
		return "LostInfo [lost_id=" + lost_id + ", lost_name=" + lost_name
				+ ", lost_site=" + lost_site + ", lost_date=" + lost_date
				+ ", lost_info=" + lost_info + ", lost_unit=" + lost_unit
				+ ", pursuit_time=" + pursuit_time + "]";
	}

}
