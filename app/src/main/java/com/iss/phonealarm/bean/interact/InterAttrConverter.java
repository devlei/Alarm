package com.iss.phonealarm.bean.interact;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class InterAttrConverter implements Converter {

    @Override
    public boolean canConvert(Class type) {
        return type.equals(InteractFile.class);
    }


    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        InteractFile attr = (InteractFile) source;
        writer.addAttribute("type", attr.getType());
        writer.addAttribute("filename", attr.getType());
        writer.setValue(attr.getValue());
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader,
                            UnmarshallingContext context) {
        InteractFile a = new InteractFile();
        a.setType(reader.getAttribute("type"));
        a.setType(reader.getAttribute("filename"));
        a.setValue(reader.getValue());
        return a;
    }
}