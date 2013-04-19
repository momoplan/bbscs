package com.laoer.bbscs.web.action;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import java.util.*;

import com.laoer.bbscs.service.BoardService;
import com.laoer.bbscs.comm.*;

public class AdminBoardList extends BaseAction {
	/**
	 * Logger for this class
	 */
	// private static final Log logger =
	// LogFactory.getLog(AdminBoardList.class);
	/**
	 *
	 */
	private static final long serialVersionUID = 9211063583782380744L;

	private BoardService boardService;

	private List boardTreeList;

	public List getBoardTreeList() {
		return boardTreeList;
	}

	public void setBoardTreeList(List boardTreeList) {
		this.boardTreeList = boardTreeList;
	}

	public BoardService getBoardService() {
		return boardService;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	public String execute() {
		//List l = this.getBoardService().findBoardsByParentID(0, -1, -1, Constant.FIND_BOARDS_BY_ORDER);
		//System.out.println(l);
		this.setBoardTreeList(this.getBoardService().findBoardsAllTree(0, new ArrayList(), -1, -1,
				Constant.FIND_BOARDS_BY_ORDER));
		return "boardList";
	}

}
