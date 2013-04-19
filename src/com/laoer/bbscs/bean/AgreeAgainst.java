package com.laoer.bbscs.bean;

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
public class AgreeAgainst {

	private String id;

	private String userID;

	private String postID;

	private long boardID;

	private int voteType;

	private long createTime;

	public AgreeAgainst() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setPostID(String postID) {
		this.postID = postID;
	}

	public void setBoardID(long boardID) {
		this.boardID = boardID;
	}

	public void setVoteType(int voteType) {
		this.voteType = voteType;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public String getUserID() {
		return userID;
	}

	public String getPostID() {
		return postID;
	}

	public long getBoardID() {
		return boardID;
	}

	public int getVoteType() {
		return voteType;
	}

	public long getCreateTime() {
		return createTime;
	}
}
