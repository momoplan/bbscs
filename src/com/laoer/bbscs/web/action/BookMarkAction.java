package com.laoer.bbscs.web.action;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.bean.BookMark;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.BookMarkFactory;
import com.laoer.bbscs.service.BookMarkService;
import com.laoer.bbscs.service.web.PageList;
import com.laoer.bbscs.service.web.Pages;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;
import com.laoer.bbscs.web.interceptor.RequestBasePathAware;

public class BookMarkAction extends BaseMainAction implements RequestBasePathAware {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(BookMarkAction.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -8585741486890858750L;

	private String basePath;

	private PageList pageList;

	private String alt;

	private String bookMarkName;

	private String id;

	private int isShare;

	private String url;

	private BookMarkService bookMarkService;

	private BookMarkFactory bookMarkFactory;

	private AjaxMessagesJson ajaxMessagesJson;

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getBookMarkName() {
		return bookMarkName;
	}

	public void setBookMarkName(String bookMarkName) {
		this.bookMarkName = bookMarkName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIsShare() {
		return isShare;
	}

	public void setIsShare(int isShare) {
		this.isShare = isShare;
	}

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public BookMarkFactory getBookMarkFactory() {
		return bookMarkFactory;
	}

	public void setBookMarkFactory(BookMarkFactory bookMarkFactory) {
		this.bookMarkFactory = bookMarkFactory;
	}

	public BookMarkService getBookMarkService() {
		return bookMarkService;
	}

	public void setBookMarkService(BookMarkService bookMarkService) {
		this.bookMarkService = bookMarkService;
	}

	public String index() {
		return SUCCESS;
	}

	public String list() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());
		pages.setPerPageNum(10);
		pages.setFileName(this.getBasePath()
				+ BBSCSUtil.getActionMappingURLWithoutPrefix("bookMark?action=" + this.getAction() + "&ajax=shtml"));
		this.setPageList(this.getBookMarkService().findBookMarks(this.getUserSession().getId(), pages));
		return "list";
	}

	public String add() {
		this.setAction("addsave");
		this.setIsShare(1);
		return INPUT;
	}

	public String addsave() {
		if (StringUtils.isBlank(this.getBookMarkName())) {
			this.getAjaxMessagesJson().setMessage("E_BOOKMARK_NAME_NULL", this.getText("error.bookmark.name.null"));
			return RESULT_AJAXJSON;
		}
		BookMark bm = this.getBookMarkFactory().getInstance(this.getUserSession().getId());
		bm.setAlt(StringUtils.trimToEmpty(this.getAlt()));
		bm.setBookMarkName(StringUtils.trimToEmpty(this.getBookMarkName()));
		bm.setCreateTime(new Date());
		bm.setIsShare(this.getIsShare());
		bm.setUrl(StringUtils.trimToEmpty(this.getUrl()));
		bm.setUserID(this.getUserSession().getId());

		try {
			bm = this.getBookMarkService().saveBookMark(bm);
			this.getAjaxMessagesJson().setMessage("0", this.getText("bookmark.add.ok"));
		} catch (BbscsException ex) {
			logger.error(ex);
			this.getAjaxMessagesJson().setMessage("E_NOTE_ADDFAILED", this.getText("error.bookmark.add.error"));
		}
		return RESULT_AJAXJSON;
	}

	public String edit() {
		this.setAction("editdo");
		BookMark bm = this.getBookMarkService().findBookMarkByIDUserID(this.getId(), this.getUserSession().getId());
		if (bm == null) {
			this.addActionError(this.getText("error.bookmark.notexist"));
			return RESULT_HTMLERROR;
		}
		this.setAlt(bm.getAlt());
		this.setBookMarkName(bm.getBookMarkName());
		this.setId(bm.getId());
		this.setIsShare(bm.getIsShare());
		this.setUrl(bm.getUrl());
		return INPUT;
	}

	public String editdo() {
		BookMark bm = this.getBookMarkService().findBookMarkByIDUserID(this.getId(), this.getUserSession().getId());
		if (bm == null) {
			this.getAjaxMessagesJson().setMessage("E_BOOKMARK_NOTEXIST", this.getText("error.bookmark.notexist"));
			return RESULT_AJAXJSON;
		}
		bm.setAlt(StringUtils.trimToEmpty(this.getAlt()));
		bm.setBookMarkName(StringUtils.trimToEmpty(this.getBookMarkName()));
		bm.setIsShare(this.getIsShare());
		bm.setUrl(StringUtils.trimToEmpty(this.getUrl()));

		try {
			bm = this.getBookMarkService().saveBookMark(bm);
			this.getAjaxMessagesJson().setMessage("0", this.getText("bookmark.edit.ok"));
		} catch (BbscsException ex1) {
			logger.error(ex1);
			this.getAjaxMessagesJson().setMessage("E_BOOKMARK_EDITFAILED", this.getText("error.bookmark.edit.error"));

		}
		return RESULT_AJAXJSON;
	}

	public String share() {
		BookMark bm = this.getBookMarkService().findBookMarkByIDUserID(this.getId(), this.getUserSession().getId());
		if (bm == null) {
			this.getAjaxMessagesJson().setMessage("E_BOOKMARK_NOTEXIST", this.getText("error.bookmark.notexist"));
			return RESULT_AJAXJSON;
		}
		bm.setIsShare(this.getIsShare());
		try {
			bm = this.getBookMarkService().saveBookMark(bm);
			this.getAjaxMessagesJson().setMessage("0", this.getText("bookmark.edit.ok"));
		} catch (BbscsException ex1) {
			logger.error(ex1);
			this.getAjaxMessagesJson().setMessage("E_BOOKMARK_EDITFAILED", this.getText("error.bookmark.edit.error"));
		}

		return RESULT_AJAXJSON;
	}

	public String del() {
		try {
			this.getBookMarkService().removeBookMarkByIDUserID(this.getId(), this.getUserSession().getId());
			this.getAjaxMessagesJson().setMessage("0", this.getText("bookmark.del.ok"));
		} catch (BbscsException ex2) {
			logger.error(ex2);
			this.getAjaxMessagesJson().setMessage("E_BOOKMARK_DELFAILED", this.getText("error.bookmark.del.error"));
		}
		return RESULT_AJAXJSON;
	}

}
