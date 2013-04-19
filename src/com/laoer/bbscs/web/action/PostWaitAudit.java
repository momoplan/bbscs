package com.laoer.bbscs.web.action;

public class PostWaitAudit extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = -7253071284640332430L;

	private long bid;

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public String execute() {
		return SUCCESS;
	}

}
