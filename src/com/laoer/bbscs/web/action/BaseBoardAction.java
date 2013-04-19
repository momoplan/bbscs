package com.laoer.bbscs.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.web.interceptor.BoardAware;
import com.laoer.bbscs.web.servlet.UserCookie;
import com.laoer.bbscs.web.servlet.UserSession;

public class BaseBoardAction extends BaseAction implements BoardAware {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(BaseBoardAction.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 319945419761351044L;

	private Board board;

	private UserCookie userCookie;

	private UserSession userSession;

	private long bid = 0;

	private String tagId = "0";

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setUserCookie(UserCookie userCookie) {
		this.userCookie = userCookie;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public Board getBoard() {
		return board;
	}

	public UserCookie getUserCookie() {
		return userCookie;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			logger.error(e);
			return ERROR;
		}
	}

}
