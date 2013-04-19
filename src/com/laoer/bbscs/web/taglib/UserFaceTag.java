package com.laoer.bbscs.web.taglib;

import javax.servlet.jsp.PageContext;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class UserFaceTag extends BbscsComponentTagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = -2113974447691755459L;

	public UserFaceTag() {
		// TODO 自动生成构造函数存根
	}

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public Component getBean(ValueStack stack, PageContext pageContext) {
		// TODO 自动生成方法存根
		return new UserFace(stack, pageContext);
	}

	protected void populateParams() {
		super.populateParams();

		UserFace tag = (UserFace) component;
		tag.setValue(value);
	}

}
