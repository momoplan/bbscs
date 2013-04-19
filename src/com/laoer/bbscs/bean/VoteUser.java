package com.laoer.bbscs.bean;

import java.io.Serializable;

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
public class VoteUser implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6546440582438669075L;

	private String id;

	private String voteID;

	private String voteUserID;

	private long voteTime;

	public VoteUser() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setVoteID(String voteID) {
		this.voteID = voteID;
	}

	public void setVoteUserID(String voteUserID) {
		this.voteUserID = voteUserID;
	}

	public void setVoteTime(long voteTime) {
		this.voteTime = voteTime;
	}

	public String getId() {
		return id;
	}

	public String getVoteID() {
		return voteID;
	}

	public String getVoteUserID() {
		return voteUserID;
	}

	public long getVoteTime() {
		return voteTime;
	}
}
