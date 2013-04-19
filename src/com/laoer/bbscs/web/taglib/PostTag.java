package com.laoer.bbscs.web.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class PostTag extends ComponentTagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 5331680101626446658L;

	protected String type = "";

	protected String boardValue = "%{board}";

	protected String tagIdValue = "%{tagId}";

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		// TODO 自动生成方法存根
		return new PostComponent(arg0, pageContext, arg1, arg2);
	}

	protected void populateParams() {
		super.populateParams();

		PostComponent tag = (PostComponent)component;

		tag.setBoardValue(boardValue);
		tag.setTagIdValue(tagIdValue);
		tag.setType(type);
	}

	public String getBoardValue() {
		return boardValue;
	}

	public void setBoardValue(String boardValue) {
		this.boardValue = boardValue;
	}

	public String getTagIdValue() {
		return tagIdValue;
	}

	public void setTagIdValue(String tagIdValue) {
		this.tagIdValue = tagIdValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



}
