package com.laoer.bbscs.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.bean.BoardPermission;
import com.laoer.bbscs.bean.Permission;
import com.laoer.bbscs.bean.UserGroup;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;
import com.laoer.bbscs.web.ui.OptionsLong;

import java.util.*;

public class AdminBoardUg extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminBoardUg.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 3930671705589318104L;

	private UserGroupService userGroupService;

	private PermissionService permissionService;

	private BoardPermissionService boardPermissionService;

	private BoardService boardService;

	private AjaxMessagesJson ajaxMessagesJson;

	private long bid;

	private int gid;

	private List permissions = new ArrayList();

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public BoardPermissionService getBoardPermissionService() {
		return boardPermissionService;
	}

	public void setBoardPermissionService(BoardPermissionService boardPermissionService) {
		this.boardPermissionService = boardPermissionService;
	}

	public BoardService getBoardService() {
		return boardService;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public List getPermissions() {
		return permissions;
	}

	public void setPermissions(List permissions) {
		this.permissions = permissions;
	}

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public UserGroupService getUserGroupService() {
		return userGroupService;
	}

	public void setUserGroupService(UserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}

	private List ugList;

	public List getUgList() {
		return ugList;
	}

	public void setUgList(List ugList) {
		this.ugList = ugList;
	}

	private List<OptionsLong> permissionValues = new ArrayList<OptionsLong>();

	public List<OptionsLong> getPermissionValues() {
		return permissionValues;
	}

	public void setPermissionValues(List<OptionsLong> permissionValues) {
		this.permissionValues = permissionValues;
	}

	private Board board;

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	private UserGroup userGroup;

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	private void setPermissionValuesFromPlist(List plist) {
		for (int i = 0; i < plist.size(); i++) {
			Permission p = (Permission) plist.get(i);
			permissionValues.add(new OptionsLong(p.getId(), p.getPermissionName() + "(" + p.getId() + ")"));
		}
	}

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			logger.error(e);
			return ERROR;
		}
	}

	public String list() {
		this.setUgList(this.getUserGroupService().findUserGroupsAll());
		return "list";
	}

	@SuppressWarnings("unchecked")
	public String plist() {
		this.setAction("save");
		List plist = this.getPermissionService().findPermissionsByTypeID(2); // 取得版区普通权限
		List pslist = this.getPermissionService().findPermissionsByTypeID(3); // 取得版区特殊权限
		plist.addAll(pslist);
		this.setPermissionValuesFromPlist(plist);

		BoardPermission bp = this.getBoardPermissionService().findBoardPermissionByBidGid(this.getBid(), this.getGid());
		this.setPermissions(bp.getPermissions());

		board = this.getBoardService().getBoardByID(this.getBid());
		userGroup = this.getUserGroupService().findUserGroupByID(this.getGid());

		return "ugBoardPermissionList";
	}

	public String save() {
		BoardPermission bp = this.getBoardPermissionService().findBoardPermissionByBidGid(this.getBid(), this.getGid());
		bp.setPermissions(this.permissions);

		try {
			this.getBoardPermissionService().saveBoardPermission(bp);
			this.getAjaxMessagesJson().setMessage("0", this.getText("bbscs.dataupdate.succeed"));
		} catch (BbscsException e) {
			logger.error(e);
			this.getAjaxMessagesJson().setMessage("E_BP_SAVEFAILED", this.getText("error.dataupdate.failed"));
		}
		return RESULT_AJAXJSON;
	}

}
