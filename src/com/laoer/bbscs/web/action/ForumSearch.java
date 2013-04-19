package com.laoer.bbscs.web.action;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;

import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.service.ForumService;
import com.laoer.bbscs.service.web.PageList;
import com.laoer.bbscs.service.web.Pages;
import com.laoer.bbscs.web.interceptor.RequestBasePathAware;

public class ForumSearch extends BaseBoardAction implements RequestBasePathAware {

	/**
	 *
	 */
	private static final long serialVersionUID = -903283960055452384L;

	private String basePath;

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBasePath() {
		return basePath;
	}

	private ForumService forumService;

	public ForumService getForumService() {
		return forumService;
	}

	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}

	private String con = "title";

	private String text;

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private PageList pageList;

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	public String index() {
		if (StringUtils.isBlank(this.getText())) {
			this.addActionError(this.getText("error.nullerror"));
			return ERROR;
		}
		Pages pages = new Pages();
		pages.setPage(this.getPage());
		pages.setPerPageNum(40);
		String text = "";
		try {
			text = java.net.URLEncoder.encode(this.getText(), Constant.CHARSET);
		} catch (UnsupportedEncodingException ex) {
			text = "";
		}
		pages.setFileName(BBSCSUtil.getActionMappingURLWithoutPrefix("/forumSearch?bid=" + this.getBid() + "&con="
				+ this.getCon() + "&text=" + text));
		if (this.getTotal() > 0) {
			pages.setTotalNum(this.getTotal());
		}
		this.setPageList(this.getForumService().getSearchList(this.getBid(), this.getCon(), this.getText(), pages));
		return SUCCESS;
	}

}
