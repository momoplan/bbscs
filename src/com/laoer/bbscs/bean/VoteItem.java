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
public class VoteItem implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7646785021711765140L;

	private String id;

	private String voteID;

	private String item;

	private int itemValue;

	public VoteItem() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setVoteID(String voteID) {
		this.voteID = voteID;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public void setItemValue(int itemValue) {
		this.itemValue = itemValue;
	}

	public String getId() {
		return id;
	}

	public String getVoteID() {
		return voteID;
	}

	public String getItem() {
		return item;
	}

	public int getItemValue() {
		return itemValue;
	}
}
