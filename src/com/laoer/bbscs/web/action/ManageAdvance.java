package com.laoer.bbscs.web.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laoer.bbscs.bean.BoardAuthUser;
import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.BoardAuthUserService;
import com.laoer.bbscs.service.BoardService;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;

public class ManageAdvance extends BaseBoardAction {

	/**
	 *
	 */
	private static final long serialVersionUID = -8359233095411406659L;

	private BoardAuthUserService boardAuthUserService;

	private UserService userService;

	private AjaxMessagesJson ajaxMessagesJson;

	private BoardService boardService;

	private String addAuthUserName;

	private String bulletin;

	private String delAuthUserID;

	private String delAuthUserName;

	private int forbidType;

	private String forbidUserName;

	private List authUsers;

	public String getAddAuthUserName() {
		return addAuthUserName;
	}

	public void setAddAuthUserName(String addAuthUserName) {
		this.addAuthUserName = addAuthUserName;
	}

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public BoardAuthUserService getBoardAuthUserService() {
		return boardAuthUserService;
	}

	public void setBoardAuthUserService(BoardAuthUserService boardAuthUserService) {
		this.boardAuthUserService = boardAuthUserService;
	}

	public String getBulletin() {
		return bulletin;
	}

	public void setBulletin(String bulletin) {
		this.bulletin = bulletin;
	}

	public String getDelAuthUserID() {
		return delAuthUserID;
	}

	public void setDelAuthUserID(String delAuthUserID) {
		this.delAuthUserID = delAuthUserID;
	}

	public String getDelAuthUserName() {
		return delAuthUserName;
	}

	public void setDelAuthUserName(String delAuthUserName) {
		this.delAuthUserName = delAuthUserName;
	}

	public int getForbidType() {
		return forbidType;
	}

	public void setForbidType(int forbidType) {
		this.forbidType = forbidType;
	}

	public String getForbidUserName() {
		return forbidUserName;
	}

	public void setForbidUserName(String forbidUserName) {
		this.forbidUserName = forbidUserName;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List getAuthUsers() {
		return authUsers;
	}

	public void setAuthUsers(List authUsers) {
		this.authUsers = authUsers;
	}

	public BoardService getBoardService() {
		return boardService;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	public String index() {
		if (this.getBoard().getIsAuth() == 1) {
			this.setAuthUsers(this.getBoardAuthUserService().findBoardAuthUsersByBid(this.getBid()));
		}
		return SUCCESS;
	}

	public String forbiduser() {
		if (StringUtils.isBlank(this.getForbidUserName())) {
			this.getAjaxMessagesJson().setMessage("E_NULL", this.getText("error.nullerror"));
			return RESULT_AJAXJSON;
		}
		UserInfo ui = this.getUserService().findUserInfoByUserName(this.getForbidUserName());
		if (ui == null) {
			this.getAjaxMessagesJson().setMessage("E_USER_NOEXIST", this.getText("error.user.noexist"));
			return RESULT_AJAXJSON;
		}
		if (this.getForbidType() == 0) {
			if (ui.getGroupID() == Constant.USER_GROUP_ADMIN || ui.getGroupID() == Constant.USER_GROUP_SUPERBM
					|| this.getBoardService().isBoardMaster(this.getBoard(), this.getForbidUserName())) {
				this.getAjaxMessagesJson().setMessage("E_MANAGEADV_CANNOTFORBID",
						this.getText("error.manageadv.cannotforbid"));
				return RESULT_AJAXJSON;
			}
			if (ui.getGroupID() == Constant.USER_GROUP_FORBID) {
				this.getAjaxMessagesJson().setMessage("E_MANAGEADV_FORBID_EXIST",
						this.getText("error.manageadv.forbidexist"));
				return RESULT_AJAXJSON;
			}
			ui.setGroupID(Constant.USER_GROUP_FORBID);
			try {
				this.getUserService().saveUserInfo(ui);
				this.getAjaxMessagesJson().setMessage("0",
						this.getText("manageadv.forbid.addok", new String[] { this.getForbidUserName() }));
			} catch (BbscsException ex) {
				this.getAjaxMessagesJson().setMessage("E_MANAGEADV_FORBID_ADD_ERROR",
						this.getText("error.manageadv.adderror"));
			}
			return RESULT_AJAXJSON;
		}
		if (this.getForbidType() == 1) {
			if (ui.getGroupID() != Constant.USER_GROUP_FORBID) {
				this.getAjaxMessagesJson().setMessage("E_MANAGEADV_NOT_FORBID",
						this.getText("error.manageadv.cannotforbid"));
				return RESULT_AJAXJSON;
			}
			ui.setGroupID(Constant.USER_GROUP_REGUSER);
			try {
				this.getUserService().saveUserInfo(ui);
				this.getAjaxMessagesJson().setMessage("0",
						this.getText("manageadv.forbid.outok", new String[] { this.getForbidUserName() }));

			} catch (BbscsException ex1) {
				this.getAjaxMessagesJson().setMessage("E_MANAGEADV_FORBID_OUT_ERROR",
						this.getText("error.manageadv.adderror"));
			}
			return RESULT_AJAXJSON;
		}
		return RESULT_AJAXJSON;
	}

	public String bulletin() {
		String bulletin = StringUtils.trimToEmpty(this.getBulletin());
		this.getBoard().setBulletin(bulletin);
		try {
			this.getBoardService().saveBoard(this.getBoard());
			this.getAjaxMessagesJson().setMessage("0", this.getText("manageadv.bulletin.updateok"));

		} catch (BbscsException ex2) {
			this.getAjaxMessagesJson().setMessage("E_MANAGEADV_FORBID_OUT_ERROR",
					this.getText("error.manageadv.bulletin.updateerror"));
		}
		return RESULT_AJAXJSON;
	}

	public String addauth() {
		if (StringUtils.isBlank(this.getAddAuthUserName())) {
			this.getAjaxMessagesJson().setMessage("E_NULL", this.getText("error.nullerror"));
			return RESULT_AJAXJSON;
		}
		BoardAuthUser bau = this.getBoardAuthUserService().findBoardAuthUserByBidUserName(this.getBid(),
				this.getAddAuthUserName());
		if (bau != null) {
			this.getAjaxMessagesJson().setMessage("E_MANAGEADV_AUTHUSER_EXIST",
					this.getText("error.manageadv.authuserexist"));
			return RESULT_AJAXJSON;
		}
		UserInfo ui = this.getUserService().findUserInfoByUserName(this.getAddAuthUserName());
		if (ui == null) {
			this.getAjaxMessagesJson().setMessage("E_USER_NOEXIST", this.getText("error.user.noexist"));
			return RESULT_AJAXJSON;
		}
		bau = new BoardAuthUser();
		bau.setBoardID(this.getBid());
		bau.setCreateTime(System.currentTimeMillis());
		bau.setUserID(ui.getId());
		bau.setUserName(ui.getUserName());
		try {
			this.getBoardAuthUserService().saveBoardAuthUser(bau);
			this.getAjaxMessagesJson().setMessage("0", this.getText("manageadv.authuser.add.ok"));
		} catch (BbscsException ex3) {
			this.getAjaxMessagesJson().setMessage("E_MANAGEADV_AUTHUSER_ADD_ERROR",
					this.getText("error.manageadv.authuser.add.error"));
		}
		return RESULT_AJAXJSON;
	}

	public String delauth() {
		if (StringUtils.isBlank(this.getDelAuthUserName())) {
			this.getAjaxMessagesJson().setMessage("E_NULL", this.getText("error.nullerror"));
			return RESULT_AJAXJSON;
		}
		BoardAuthUser bau = this.getBoardAuthUserService().findBoardAuthUserByBidUserName(this.getBid(),
				this.getDelAuthUserName());
		if (bau != null) {
			try {
				this.getBoardAuthUserService().removeBoardAuthUser(bau);
				this.getAjaxMessagesJson().setMessage("0", this.getText("manageadv.authuser.del.ok"));
			} catch (BbscsException ex4) {
				this.getAjaxMessagesJson().setMessage("E_MANAGEADV_AUTHUSER_DEL_ERROR",
						this.getText("error.manageadv.authuser.del.error"));
			}
			return RESULT_AJAXJSON;
		} else {
			this.getAjaxMessagesJson().setMessage("E_MANAGEADV_AUTHUSER_DEL_ERROR",
					this.getText("error.manageadv.authuser.del.error"));
			return RESULT_AJAXJSON;
		}
	}

}
