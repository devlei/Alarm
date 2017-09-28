package phonealarm.iss.com.alarm.bean.interactquery;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaocuilong on 2017/9/27.
 */
@XStreamAlias("files")
public class InterQueryFile implements Serializable {

    @XStreamImplicit(itemFieldName = "fileurl")
    private List<InterQueryType> file;

    public List<InterQueryType> getFile() {
        return file;
    }

    public void setFile(List<InterQueryType> file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "InterQueryFile{" + "file=" + file + '}';
    }
}
