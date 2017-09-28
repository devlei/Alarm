package com.iss.phonealarm.bean.caseinfo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("cases_file")
@XStreamConverter(CaseFileAttrConverte.class)
public class CaseFileInfo {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
