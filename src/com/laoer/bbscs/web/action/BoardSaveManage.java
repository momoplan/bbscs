package com.laoer.bbscs.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

import com.laoer.bbscs.bean.BoardSave;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.BoardSaveService;
import com.laoer.bbscs.service.BoardService;

public class BoardSaveManage extends BaseMainAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(BoardSaveManage.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -4865517042206383827L;

	private BoardSaveService boardSaveService;

	private BoardService boardService;

	private List boardList;

	private List<Long> ids;

	public BoardSaveService getBoardSaveService() {
		return boardSaveService;
	}

	public void setBoardSaveService(BoardSaveService boardSaveService) {
		this.boardSaveService = boardSaveService;
	}

	public BoardService getBoardService() {
		return boardService;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	public List getBoardList() {
		return boardList;
	}

	public void setBoardList(List boardList) {
		this.boardList = boardList;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String index() {
		this.setAction("dels");
		List bslist = this.getBoardSaveService().findBoardSavesByUid(this.getUserSession().getId());
		this.setBoardList(this.getBoardService().findBoardsInIDs(this.getBoardIds(bslist)));
		return SUCCESS;
	}

	public String dels() {
		if (this.getIds() == null || this.getIds().isEmpty()) {
			this.addActionError(this.getText("error.parametererror"));
			return ERROR;
		}
		try {
			this.getBoardSaveService().removeBoardSaveByBidsUid(this.getUserSession().getId(), this.getIds());
			return "list";
		} catch (BbscsException e) {
			logger.error(e);
			this.addActionError(this.getText("error.boardsave.del"));
			return ERROR;
		}

	}

	private List getBoardIds(List bslist) {
		List<Long> blist = new ArrayList<Long>();
		for (int i = 0; i < bslist.size(); i++) {
			BoardSave bs = (BoardSave) bslist.get(i);
			blist.add(new Long(bs.getBoardID()));
		}
		return blist;
	}
}
