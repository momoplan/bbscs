package com.laoer.bbscs.web.action;

import java.util.List;

import com.laoer.bbscs.bean.Forum;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.ForumService;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;

public class DelAttach extends BaseBoardAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1029547168779428075L;

	private List<String> attchFileNames;

	private String id;

	public List<String> getAttchFileNames() {
		return attchFileNames;
	}

	public void setAttchFileNames(List<String> attchFileNames) {
		this.attchFileNames = attchFileNames;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private AjaxMessagesJson ajaxMessagesJson;

	private ForumService forumService;

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public ForumService getForumService() {
		return forumService;
	}

	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}

	public String index() {
		if (this.getAttchFileNames() == null || this.getAttchFileNames().isEmpty()) {
			this.getAjaxMessagesJson().setMessage("E_POST_DELATTACH_NO_CHOICE",
					this.getText("error.post.upfile.del.nochoice"));
			return RESULT_AJAXJSON;
		}

		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.getAjaxMessagesJson().setMessage("E_POST_NOT_EXIST", this.getText("error.post.getpost"));
			return RESULT_AJAXJSON;
		}

		try {
			this.getForumService().removeAttachFile(f, this.getAttchFileNames());
			this.getAjaxMessagesJson().setMessage("0", this.getText("post.upfile.delok"));
		} catch (BbscsException ex) {
			this.getAjaxMessagesJson()
					.setMessage("E_POST_DELATTACH_ERROR", this.getText("error.post.upfile.del.error"));
		}
		return RESULT_AJAXJSON;
	}

}
