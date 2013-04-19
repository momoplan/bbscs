package com.laoer.bbscs.web.action;

import com.laoer.bbscs.bean.Subscibe;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.SubscibeService;
import com.laoer.bbscs.service.web.PageList;
import com.laoer.bbscs.service.web.Pages;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;
import com.laoer.bbscs.web.interceptor.RequestBasePathAware;

public class Subs extends BaseBoardAction implements RequestBasePathAware {

	/**
	 *
	 */
	private static final long serialVersionUID = 6423283584915270149L;

	private String basePath;

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBasePath() {
		return basePath;
	}

	private int cpage;

	private String id;

	private String postID;

	private SubscibeService subscibeService;

	private AjaxMessagesJson ajaxMessagesJson;

	public int getCpage() {
		return cpage;
	}

	public void setCpage(int cpage) {
		this.cpage = cpage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPostID() {
		return postID;
	}

	public void setPostID(String postID) {
		this.postID = postID;
	}

	private PageList pageList;

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public SubscibeService getSubscibeService() {
		return subscibeService;
	}

	public void setSubscibeService(SubscibeService subscibeService) {
		this.subscibeService = subscibeService;
	}

	public String index() {
		return "subsmy";
	}

	public String list() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());
		pages.setPerPageNum(20);
		pages.setFileName(this.getBasePath()
				+ BBSCSUtil.getActionMappingURLWithoutPrefix("/subs?ajax=shtml&action=" + this.getAction() + "&bid="
						+ this.getBid()));
		this.setPageList(this.getSubscibeService().findSubscibesByUserID(this.getUserSession().getId(), this.getBid(),
				pages));
		return "list";
	}

	public String delemail() {
		return this.dela("delemail");
	}

	public String delmsg() {
		return this.dela("delmsg");
	}

	private String dela(String deltype) {
		Subscibe subs = this.getSubscibeService().findSubscibeByID(this.getId(), this.getUserSession().getId(),
				this.getBid());
		if (subs != null) {
			if (deltype.equalsIgnoreCase("delemail")) {
				subs.setEmailinform(0);
			}
			if (deltype.equalsIgnoreCase("delmsg")) {
				subs.setMsginform(0);
			}
			try {
				subs = this.getSubscibeService().saveSubscibe(subs);
				if (subs.getEmailinform() == 0 && subs.getMsginform() == 0) {
					this.getSubscibeService().removeSubscibe(subs);

					this.getAjaxMessagesJson().setMessage("1", this.getText("subs.cancle.ok"));
				} else {

					this.getAjaxMessagesJson().setMessage("0", this.getText("subs.cancle.ok"));
				}
			} catch (BbscsException ex) {
				this.getAjaxMessagesJson().setMessage("E_SUBS_CANCLE_ERROR", this.getText("error.subs.cancle.error"));
			}
		} else {
			this.getAjaxMessagesJson().setMessage("E_SUBS_CANCLE_ERROR", this.getText("error.subs.cancle.error"));
		}

		return RESULT_AJAXJSON;
	}

	public String del() {
		try {
			this.getSubscibeService().removeSubscibe(this.getId(), this.getUserSession().getId(), this.getBid());
			this.getAjaxMessagesJson().setMessage("0", this.getText("subs.cancle.ok"));
		} catch (BbscsException ex1) {
			this.getAjaxMessagesJson().setMessage("E_SUBS_CANCLE_ERROR", this.getText("error.subs.cancle.error"));
		}
		return RESULT_AJAXJSON;
	}

}
