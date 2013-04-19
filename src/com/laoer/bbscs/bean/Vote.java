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
public class Vote implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1741600212281533826L;

	private String id;

	private String title;

	private int isM;

	private long deadLine;

	public Vote() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIsM(int isM) {
		this.isM = isM;
	}

	public void setDeadLine(long deadLine) {
		this.deadLine = deadLine;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getIsM() {
		return isM;
	}

	public long getDeadLine() {
		return deadLine;
	}
}
