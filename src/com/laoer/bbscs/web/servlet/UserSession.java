package com.laoer.bbscs.web.servlet;

import java.io.Serializable;
import java.util.*;

import com.laoer.bbscs.bean.Permission;

public class UserSession implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4053934393043346809L;

	private String userName = "";

	private String id = "";

	private String nickName = "";

	private String email = "";

	private long lastActiveTime = 0;

	private Map userPermission = new HashMap();

	private Map boardPermission = new HashMap();

	private Map specialPermission = new HashMap();

	private Map boardSpecialPermission = new HashMap();

	private long bid = 0;

	private int groupID = 0;

	private long addedOnlineTime = 0;

	private long addedOnlineHour = 0;

	private String validateCode = "";

	private String[] signDetail = { "", "", "" };

	private String boardPass = "";

	private int initStatus = 0;

	public UserSession() {
	}

	public boolean isHaveSpecialPermission(long permissionID) {
		return this.specialPermission.containsKey(new Long(permissionID));
	}

	public boolean isHaveBoardSpecialPermission(long permissionID) {
		return this.boardSpecialPermission.containsKey(new Long(permissionID));
	}

	@SuppressWarnings("unchecked")
	public void setUserPermissionArray(Map[] permissionMap) {
		setSpecialPermission(permissionMap[1]);
		Set pset = permissionMap[0].entrySet();
		Iterator it = pset.iterator();
		while (it.hasNext()) {
			Map.Entry p = (Map.Entry) it.next();
			Permission permission = (Permission) p.getValue();
			String[] actions = permission.getAction().split(",");
			for (int i = 0; i < actions.length; i++) {
				String[] resources = ((String) p.getKey()).split(",");
				this.getUserPermission().put(resources[0] + "?action=" + actions[i], p.getValue());
			}
		}

	}

	@SuppressWarnings("unchecked")
	public void setBoardPermissionArray(Map[] permissionMap) {
		Set pset = permissionMap[0].entrySet();
		Iterator it = pset.iterator();
		while (it.hasNext()) {
			Map.Entry p = (Map.Entry) it.next();
			Permission permission = (Permission) p.getValue();
			String[] actions = permission.getAction().split(",");
			for (int i = 0; i < actions.length; i++) {
				String[] resources = ((String) p.getKey()).split(",");
				this.getBoardPermission().put(resources[0] + "?action=" + actions[i], p.getValue());
			}
		}
		Set gbspset = permissionMap[1].entrySet();
		Iterator gbspit = gbspset.iterator();
		while (gbspit.hasNext()) {
			Map.Entry p = (Map.Entry) gbspit.next();
			this.getBoardSpecialPermission().put(p.getKey(), p.getValue());
		}
		this.setInitStatus(1);
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setLastActiveTime(long lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}

	public void setUserPermission(Map userPermission) {
		this.userPermission = userPermission;
	}

	public void setBoardPermission(Map boardPermission) {

		this.boardPermission = boardPermission;
	}

	public void setSpecialPermission(Map specialPermission) {
		this.specialPermission = specialPermission;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public void setAddedOnlineTime(long addedOnlineTime) {
		this.addedOnlineTime = addedOnlineTime;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public void setAddedOnlineHour(long addedOnlineHour) {
		this.addedOnlineHour = addedOnlineHour;
	}

	public void setSignDetail(String[] signDetail) {
		this.signDetail = signDetail;
	}

	public void setBoardSpecialPermission(Map boardSpecialPermission) {
		this.boardSpecialPermission = boardSpecialPermission;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBoardPass(String boardPass) {
		this.boardPass = boardPass;
	}

	public String getUserName() {
		return userName;
	}

	public String getId() {
		return id;
	}

	public String getNickName() {
		return nickName;
	}

	public long getLastActiveTime() {
		return lastActiveTime;
	}

	public Map getUserPermission() {
		return userPermission;
	}

	public Map getBoardPermission() {

		return boardPermission;
	}

	public Map getSpecialPermission() {
		return specialPermission;
	}

	public long getBid() {
		return bid;
	}

	public int getGroupID() {
		return groupID;
	}

	public long getAddedOnlineTime() {
		return addedOnlineTime;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public long getAddedOnlineHour() {
		return addedOnlineHour;
	}

	public String[] getSignDetail() {
		return signDetail;
	}

	public Map getBoardSpecialPermission() {
		return boardSpecialPermission;
	}

	public String getEmail() {
		return email;
	}

	public String getBoardPass() {
		return boardPass;
	}

	public int getInitStatus() {
		return initStatus;
	}

	public void setInitStatus(int initStatus) {
		this.initStatus = initStatus;
	}

}
