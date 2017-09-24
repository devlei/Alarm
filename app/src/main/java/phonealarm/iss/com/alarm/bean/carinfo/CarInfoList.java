package phonealarm.iss.com.alarm.bean.carinfo;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("carInfoList")
public class CarInfoList {

	@XStreamImplicit(itemFieldName = "carInfo")
	private List<CarInfo> carInfoList = new ArrayList<CarInfo>();

	public List<CarInfo> getCarInfoList() {
		return carInfoList;
	}

	public void setCarInfoList(List<CarInfo> carInfoList) {
		this.carInfoList = carInfoList;
	}

	@Override
	public String toString() {
		return "CarInfoList [carInfoList=" + carInfoList + "]";
	}

	
}
