package com.laoer.bbscs.web.action;

import com.laoer.bbscs.bean.BoardSave;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.BoardSaveService;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;

public class BoardSaveAction extends BaseBoardAction {

	/**
	 *
	 */
	private static final long serialVersionUID = -5561105533819504716L;

	private AjaxMessagesJson ajaxMessagesJson;

	private BoardSaveService boardSaveService;

	private String id;

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public BoardSaveService getBoardSaveService() {
		return boardSaveService;
	}

	public void setBoardSaveService(BoardSaveService boardSaveService) {
		this.boardSaveService = boardSaveService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String save() {
		BoardSave bs = this.getBoardSaveService().findBoardSaveByUidBid(this.getUserSession().getId(), this.getBid());
		if (bs != null) {
			this.getAjaxMessagesJson().setMessage("E_BOARDSAVE_EXIST", this.getText("error.boardsave.exist"));
			return RESULT_AJAXJSON;
		}
		bs = new BoardSave();
		bs.setBoardID(this.getBid());
		bs.setUserID(this.getUserSession().getId());
		try {
			this.getBoardSaveService().saveBoardSave(bs);
			this.getAjaxMessagesJson().setMessage("0", this.getText("boardsave.add.ok"));
		} catch (BbscsException ex) {

			this.getAjaxMessagesJson().setMessage("E_BOARDSAVE_ADD_ERROR", this.getText("error.boarssave.add.error"));
		}
		return RESULT_AJAXJSON;
	}

}
