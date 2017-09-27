package phonealarm.iss.com.alarm.bean.searchalarm;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaocuilong on 2017/9/27.
 */
@XStreamAlias("attach_list")
public class AlarmFilesList {

    @XStreamImplicit(itemFieldName = "attachment")
    private List<MultimediaAttrBean> attach_list;

    public List<MultimediaAttrBean> getAttach_list() {
        return attach_list;
    }

    public void setAttach_list(List<MultimediaAttrBean> attach_list) {
        this.attach_list = attach_list;
    }

    @Override
    public String toString() {
        return "AlarmFilesList{" +
                "attach_list=" + attach_list +
                '}';
    }
}
