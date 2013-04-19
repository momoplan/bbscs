package com.laoer.bbscs.bean;

import java.io.*;
import java.util.*;

/**
 * <p>
 * Title: TianyiBBS
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
 * @author Laoer
 * @version 7.0
 */
public class Subscibe implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5997983221018361450L;

	private String id;

	private String userID;

	private String userName;

	private String nickName;

	private String postID;

	private String postTitle;

	private long boardID;

	private int emailinform;

	private int msginform;

	private String userEmail;

	private Date createTime;

	private String userLocale;

	public Subscibe() {
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

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setPostID(String postID) {
		this.postID = postID;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public void setBoardID(long boardID) {
		this.boardID = boardID;
	}

	public void setEmailinform(int emailinform) {
		this.emailinform = emailinform;
	}

	public void setMsginform(int msginform) {
		this.msginform = msginform;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUserLocale(String userLocale) {
		this.userLocale = userLocale;
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

	public String getNickName() {
		return nickName;
	}

	public String getPostID() {
		return postID;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public long getBoardID() {
		return boardID;
	}

	public int getEmailinform() {
		return emailinform;
	}

	public int getMsginform() {
		return msginform;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getUserLocale() {
		return userLocale;
	}
}
