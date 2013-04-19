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
public class BoardSave implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5390014211916049604L;

	private String id;

	private String userID;

	private long boardID;

	public BoardSave() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setBoardID(long boardID) {
		this.boardID = boardID;
	}

	public String getId() {
		return id;
	}

	public String getUserID() {
		return userID;
	}

	public long getBoardID() {
		return boardID;
	}
}
