package com.iss.phonealarm.bean.interactquery;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * Created by zhaocuilong on 2017/9/27.
 */

public class InterQueryAttrConverter implements Converter {

    @Override
    public boolean canConvert(Class type) {
        return type.equals(InterQueryType.class);
    }


    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        InterQueryType attr = (InterQueryType) source;
        writer.setValue(attr.getValue());
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader,
                            UnmarshallingContext context) {
        InterQueryType a = new InterQueryType();
        a.setValue(reader.getValue());
        return a;
    }
}
