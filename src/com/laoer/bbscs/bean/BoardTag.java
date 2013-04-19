package com.laoer.bbscs.bean;

import java.io.*;
import org.apache.commons.lang.builder.*;

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
public class BoardTag implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3047066772996705989L;

	private String id;

	private long boardID;

	private String tagName;

	private int orders;

	private Board board;

	public BoardTag() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setBoardID(long boardID) {
		this.boardID = boardID;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public String getId() {
		return id;
	}

	public long getBoardID() {
		return boardID;
	}

	public String getTagName() {
		return tagName;
	}

	public int getOrders() {
		return orders;
	}

	public Board getBoard() {
		return board;
	}

	public boolean equals(Object obj) {
		if (obj instanceof BoardTag == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		BoardTag rhs = (BoardTag) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(id, rhs.getId()).isEquals();
	}

}
