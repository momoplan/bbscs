package com.laoer.bbscs.web.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class BoardMasterTag extends ComponentTagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 7707777925844089093L;

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		return new BoardMaster(arg0, arg1);
	}

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	protected void populateParams() {
		super.populateParams();

		BoardMaster tag = (BoardMaster) component;
		tag.setValue(value);
	}

}
