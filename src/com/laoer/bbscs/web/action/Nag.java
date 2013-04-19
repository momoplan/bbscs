package com.laoer.bbscs.web.action;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.service.BoardSaveService;
import com.laoer.bbscs.service.BoardService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.interceptor.UserSessionAware;
import com.laoer.bbscs.web.servlet.UserCookie;
import com.laoer.bbscs.web.servlet.UserSession;
import java.util.*;

public class Nag extends BaseAction implements UserSessionAware {

	/**
	 *
	 */
	private static final long serialVersionUID = 732228269000076637L;

	UserCookie userCookie;

	UserSession userSession;

	private BoardService boardService;

	private BoardSaveService boardSaveService;

	private SysConfig sysConfig;

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

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

	public void setUserCookie(UserCookie userCookie) {
		this.userCookie = userCookie;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	private List boardList = new ArrayList();

	public List getBoardList() {
		return boardList;
	}

	public void setBoardList(List boardList) {
		this.boardList = boardList;
	}

	private Map<Long, List> boardMap = new HashMap<Long, List>();

	public Map<Long, List> getBoardMap() {
		return boardMap;
	}

	public void setBoardMap(Map<Long, List> boardMap) {
		this.boardMap = boardMap;
	}

	private List boardSaveList;

	public List getBoardSaveList() {
		return boardSaveList;
	}

	public void setBoardSaveList(List boardSaveList) {
		this.boardSaveList = boardSaveList;
	}

	private boolean urlRewrite = false;

	public boolean isUrlRewrite() {
		return urlRewrite;
	}

	public void setUrlRewrite(boolean urlRewrite) {
		this.urlRewrite = urlRewrite;
	}

	private boolean usePass = false;

	public boolean isUsePass() {
		return usePass;
	}

	public void setUsePass(boolean usePass) {
		this.usePass = usePass;
	}

	public String execute() {
		int isHidden = 0;
		if (userSession.isHaveSpecialPermission(Constant.SPERMISSION_CAN_SEE_HIDDEN_BOARD)) { // 如果用户有查看隐藏版区的权限
			isHidden = -1;
		}
		this.setUrlRewrite(Constant.USE_URL_REWRITE);
		this.boardList = this.getBoardService().findBoardsByParentID(0, 1, isHidden, Constant.FIND_BOARDS_BY_ORDER);
		// System.out.println(boardList);

		for (int i = 0; i < this.boardList.size(); i++) {
			Board b = (Board) this.boardList.get(i);
			List bclist = this.getBoardService().findBoardsByParentID(b.getId(), 1, isHidden,
					Constant.FIND_BOARDS_BY_ORDER);
			this.boardMap.put(b.getId(), bclist);
		}

		List bsaveids = this.getBoardSaveService().findBoardSaveBidsByUid(userSession.getId());
		this.boardSaveList = this.getBoardService().findBoardsInIDs(bsaveids);

		this.setUsePass(this.getSysConfig().isUsePass());

		return SUCCESS;
	}

}
