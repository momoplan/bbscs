package com.laoer.bbscs.bean;

import java.io.*;

/**
 * <p>
 * Title: Tianyi BBS
 * </p>
 *
 * <p>
 * Description: BBSCS
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 *
 * <p>
 * Company: Laoer.com
 * </p>
 *
 * @author Gong Tianyi
 * @version 7.0
 */
public class Friend implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5382754578376902099L;

	private String id;

	private String userID;

	private String userName;

	private String friendID;

	private String friendName;

	private String friendComment;

	private int isBlack;

	public Friend() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setFriendID(String friendID) {
		this.friendID = friendID;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public void setFriendComment(String friendComment) {
		this.friendComment = friendComment;
	}

	public void setIsBlack(int isBlack) {
		this.isBlack = isBlack;
	}

	public String getId() {
		return id;
	}

	public String getUserID() {
		return userID;
	}

	public String getUserName() {
		return userName;
	}

	public String getFriendID() {
		return friendID;
	}

	public String getFriendName() {
		return friendName;
	}

	public String getFriendComment() {
		return friendComment;
	}

	public int getIsBlack() {
		return isBlack;
	}
}
