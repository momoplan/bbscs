package com.laoer.bbscs.web.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class ForumTag extends ComponentTagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 7811516724988107590L;

	private String forumValue = "";

	private String boardValue = "%{board}";

	private String itemClass = "font2";

	private String inPagesValue = "%{inpages}";

	private String currentPageValue = "%{page}";

	private String currentActionValue = "%{action}";

	private String tagIdValue = "%{tagId}";

	private String type = "";

	private String totalnumValue = "%{totalnum}";

	protected String indexValue = "%{rowstatus}";

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		return new ForumComponent(arg0, pageContext, arg1, arg2);
	}
	protected void populateParams() {
		super.populateParams();

		ForumComponent tag = (ForumComponent)component;
		tag.setBoardValue(boardValue);
		tag.setCurrentActionValue(currentActionValue);
		tag.setCurrentPageValue(currentPageValue);
		tag.setForumValue(forumValue);
		tag.setInPagesValue(inPagesValue);
		tag.setItemClass(itemClass);
		tag.setTagIdValue(tagIdValue);
		tag.setTotalnumValue(totalnumValue);
		tag.setType(type);
		tag.setIndexValue(indexValue);
	}


	public String getBoardValue() {
		return boardValue;
	}

	public void setBoardValue(String boardValue) {
		this.boardValue = boardValue;
	}

	public String getCurrentActionValue() {
		return currentActionValue;
	}

	public void setCurrentActionValue(String currentActionValue) {
		this.currentActionValue = currentActionValue;
	}

	public String getCurrentPageValue() {
		return currentPageValue;
	}

	public void setCurrentPageValue(String currentPageValue) {
		this.currentPageValue = currentPageValue;
	}

	public String getForumValue() {
		return forumValue;
	}

	public void setForumValue(String forumValue) {
		this.forumValue = forumValue;
	}

	public String getInPagesValue() {
		return inPagesValue;
	}

	public void setInPagesValue(String inPagesValue) {
		this.inPagesValue = inPagesValue;
	}

	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}

	public String getTagIdValue() {
		return tagIdValue;
	}

	public void setTagIdValue(String tagIdValue) {
		this.tagIdValue = tagIdValue;
	}

	public String getTotalnumValue() {
		return totalnumValue;
	}

	public void setTotalnumValue(String totalnumValue) {
		this.totalnumValue = totalnumValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



}
