package com.laoer.bbscs.web.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class PrefixLineTag extends ComponentTagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 3310356267203700674L;

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		return new PrefixLine(arg0);
	}

	private String showText;

	private String levelValue;

	public void setLevelValue(String levelValue) {
		this.levelValue = levelValue;
	}

	public void setShowText(String showText) {
		this.showText = showText;
	}

	protected void populateParams() {
		super.populateParams();

		PrefixLine tag = (PrefixLine) component;
		tag.setLevelValue(levelValue);
		tag.setShowText(showText);
	}

}
