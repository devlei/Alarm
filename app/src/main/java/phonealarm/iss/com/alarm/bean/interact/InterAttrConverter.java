package phonealarm.iss.com.alarm.bean.interact;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class InterAttrConverter implements Converter {

	@Override
	public boolean canConvert(Class type) {
		return type.equals(InteractFile.class);// ת������
	}

	/**
	 * ��java����תΪxmlʱʹ��
	 */
	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		InteractFile attr = (InteractFile) source;
		// writer.startNode("attribute");
		writer.addAttribute("type", attr.getType());
		writer.setValue(attr.getValue());
		// writer.endNode();
	}

	/**
	 * ��xmlתΪjava����ʹ��
	 */
	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		InteractFile a = new InteractFile();// �ڽ���attributeԪ��ʱ���ȴ���һ��CarAttr����
		a.setType(reader.getAttribute("type"));// ��attributeԪ�ص�name��������ΪCarAttr�����name����ֵ
		a.setValue(reader.getValue());// ��attributeԪ�ص�txtֵ����ΪCarAttr�����valueֵ
		return a;
	}
}