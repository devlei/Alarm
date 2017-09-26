package phonealarm.iss.com.alarm.bean.searchalarm;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class MultAttrConverter implements Converter {

    @Override
    public boolean canConvert(Class type) {
        return type.equals(MultimediaAttrBean.class);
    }


    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        MultimediaAttrBean attr = (MultimediaAttrBean) source;
        writer.addAttribute("type", attr.getType());
        writer.setValue(attr.getValue());
    }


    @Override
    public Object unmarshal(HierarchicalStreamReader reader,
                            UnmarshallingContext context) {
        //TODO 未解析出来
        MultimediaAttrBean a = new MultimediaAttrBean();
        a.setType(reader.getAttribute("type"));
        a.setValue(reader.getValue());
        return a;
    }
}