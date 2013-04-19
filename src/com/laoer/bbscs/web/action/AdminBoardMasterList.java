package com.laoer.bbscs.web.action;

import java.util.Collection;
import java.util.Map;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.service.BoardService;

public class AdminBoardMasterList extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = -9059634265072240594L;

	private BoardService boardService;

	private long bid;

	public BoardService getBoardService() {
		return boardService;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	private Collection bmlist;

	public Collection getBmlist() {
		return bmlist;
	}

	public void setBmlist(Collection bmlist) {
		this.bmlist = bmlist;
	}

	public String execute() {
		Board b = this.getBoardService().getBoardByID(this.getBid());
		Map bms = b.getBoardMaster();
		this.setBmlist(bms.values());
		return SUCCESS;
	}

}
