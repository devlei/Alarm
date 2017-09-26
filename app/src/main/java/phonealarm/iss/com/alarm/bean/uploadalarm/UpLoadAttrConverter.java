package phonealarm.iss.com.alarm.bean.uploadalarm;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class UpLoadAttrConverter implements Converter {

    @Override
    public boolean canConvert(Class type) {
        return type.equals(UpLoadFileBean.class);
    }

    /**
     * 转换累
     */
    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        UpLoadFileBean attr = (UpLoadFileBean) source;
        if (null != attr) {
            writer.addAttribute("type", attr.getType());
            writer.addAttribute("filename", attr.getFilename());
            writer.setValue(attr.getValue());
        }
    }
    @Override
    public Object unmarshal(HierarchicalStreamReader reader,
                            UnmarshallingContext context) {
        UpLoadFileBean a = new UpLoadFileBean();
        a.setType(reader.getAttribute("type"));
        a.setType(reader.getAttribute("filename"));
        a.setValue(reader.getValue());
        return a;
    }
}