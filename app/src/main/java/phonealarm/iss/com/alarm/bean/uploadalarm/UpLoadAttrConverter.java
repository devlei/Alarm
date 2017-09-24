package phonealarm.iss.com.alarm.bean.uploadalarm;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class UpLoadAttrConverter implements Converter {

	@Override
	public boolean canConvert(Class type) {
		return type.equals(UpLoadFileBean.class);// ת������
	}

	/**
	 * ��java����תΪxmlʱʹ��
	 */
	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		UpLoadFileBean attr = (UpLoadFileBean) source;
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
		UpLoadFileBean a = new UpLoadFileBean();// �ڽ���attributeԪ��ʱ���ȴ���һ��CarAttr����
		a.setType(reader.getAttribute("type"));// ��attributeԪ�ص�name��������ΪCarAttr�����name����ֵ
		a.setValue(reader.getValue());// ��attributeԪ�ص�txtֵ����ΪCarAttr�����valueֵ
		return a;
	}
}