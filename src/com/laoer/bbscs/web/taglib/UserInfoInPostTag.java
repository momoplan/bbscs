package com.laoer.bbscs.web.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class UserInfoInPostTag extends ComponentTagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = -8692511273381073766L;

	protected String idValue = "";

	protected String styleClass = "pic1";

	protected String facePicName = "images/defaultFace.jpg";

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		// TODO 自动生成方法存根
		return new UserInfoInPost(arg0, pageContext, arg1, arg2);
	}

	protected void populateParams() {
		super.populateParams();

		UserInfoInPost tag = (UserInfoInPost)component;
		tag.setFacePicName(facePicName);
		tag.setIdValue(idValue);
		tag.setStyleClass(styleClass);
	}

	public String getFacePicName() {
		return facePicName;
	}

	public void setFacePicName(String facePicName) {
		this.facePicName = facePicName;
	}

	public String getIdValue() {
		return idValue;
	}

	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

}
