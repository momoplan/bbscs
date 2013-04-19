package com.laoer.bbscs.bean;

import java.io.Serializable;

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
public class Config implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1653627634458864160L;

	private String id;

	private String confContext;

	public Config() {
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getId() {

		return id;
	}

	public void setConfContext(String confContext) {
		this.confContext = confContext;
	}

	public String getConfContext() {
		return confContext;
	}
}
