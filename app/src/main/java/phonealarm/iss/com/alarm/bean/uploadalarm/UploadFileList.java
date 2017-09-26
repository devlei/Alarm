package phonealarm.iss.com.alarm.bean.uploadalarm;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaocuilong on 2017/9/26.
 */
@XStreamAlias("filelist")
public class UploadFileList {

    @XStreamImplicit(itemFieldName = "file")
    private List<UpLoadFileBean> file;

    public List<UpLoadFileBean> getFile() {
        return file;
    }

    public void setFile(List<UpLoadFileBean> file) {
        this.file = file;
    }
}
