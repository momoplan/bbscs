package com.laoer.bbscs.web.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class InTag extends ComponentTagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 5884162006577708884L;

	protected String type = "";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		return new InComponent(arg0);
	}

	protected void populateParams() {
		super.populateParams();

		InComponent tag = (InComponent)component;

		tag.setType(type);
	}

}
