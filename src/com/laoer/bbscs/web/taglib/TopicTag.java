package com.laoer.bbscs.web.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class TopicTag extends ComponentTagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = -7859679466140438086L;

	protected String mainIdName = "%{id}";

	protected String forumCurrentPageName = "%{fcpage}";

	protected String inPagesName = "%{inpages}";

	protected String topicTotalNumName = "%{totalnum}";

	protected String type = "";

	protected String boardObjName = "%{board}";

	protected String forumCurrentActionName = "%{fcaction}";

	protected String tagIdProperty = "%{tagId}";

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		return new TopicComponent(arg0, pageContext, arg1, arg2);
	}

	protected void populateParams() {
		super.populateParams();

		TopicComponent tag = (TopicComponent)component;

		tag.setBoardObjName(boardObjName);
		tag.setForumCurrentActionName(forumCurrentActionName);
		tag.setForumCurrentPageName(forumCurrentPageName);
		tag.setInPagesName(inPagesName);
		tag.setMainIdName(mainIdName);
		tag.setTagIdProperty(tagIdProperty);
		tag.setTopicTotalNumName(topicTotalNumName);
		tag.setType(type);

	}

	public String getBoardObjName() {
		return boardObjName;
	}

	public void setBoardObjName(String boardObjName) {
		this.boardObjName = boardObjName;
	}

	public String getForumCurrentActionName() {
		return forumCurrentActionName;
	}

	public void setForumCurrentActionName(String forumCurrentActionName) {
		this.forumCurrentActionName = forumCurrentActionName;
	}

	public String getForumCurrentPageName() {
		return forumCurrentPageName;
	}

	public void setForumCurrentPageName(String forumCurrentPageName) {
		this.forumCurrentPageName = forumCurrentPageName;
	}

	public String getInPagesName() {
		return inPagesName;
	}

	public void setInPagesName(String inPagesName) {
		this.inPagesName = inPagesName;
	}

	public String getMainIdName() {
		return mainIdName;
	}

	public void setMainIdName(String mainIdName) {
		this.mainIdName = mainIdName;
	}

	public String getTagIdProperty() {
		return tagIdProperty;
	}

	public void setTagIdProperty(String tagIdProperty) {
		this.tagIdProperty = tagIdProperty;
	}

	public String getTopicTotalNumName() {
		return topicTotalNumName;
	}

	public void setTopicTotalNumName(String topicTotalNumName) {
		this.topicTotalNumName = topicTotalNumName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



}
