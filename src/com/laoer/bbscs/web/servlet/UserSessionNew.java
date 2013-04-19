package com.laoer.bbscs.web.servlet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.laoer.bbscs.bean.Permission;

public class UserSessionNew implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4956348335892181005L;

	private String userName = "";

	private String id = "";

	private String nickName = "";

	private String email = "";

	private int groupID = 0;

	private String[] signDetail = { "", "", "" };

	private Map userPermission = new HashMap();

	private Map boardPermission = new HashMap();

	private Map specialPermission = new HashMap();

	private Map boardSpecialPermission = new HashMap();

	private int initStatus = 0;

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

	public Map getBoardPermission() {
		return boardPermission;
	}

	public void setBoardPermission(Map boardPermission) {
		this.boardPermission = boardPermission;
	}

	public Map getBoardSpecialPermission() {
		return boardSpecialPermission;
	}

	public void setBoardSpecialPermission(Map boardSpecialPermission) {
		this.boardSpecialPermission = boardSpecialPermission;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String[] getSignDetail() {
		return signDetail;
	}

	public void setSignDetail(String[] signDetail) {
		this.signDetail = signDetail;
	}

	public Map getSpecialPermission() {
		return specialPermission;
	}

	public void setSpecialPermission(Map specialPermission) {
		this.specialPermission = specialPermission;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(Map userPermission) {
		this.userPermission = userPermission;
	}

	public int getInitStatus() {
		return initStatus;
	}

	public void setInitStatus(int initStatus) {
		this.initStatus = initStatus;
	}

}
