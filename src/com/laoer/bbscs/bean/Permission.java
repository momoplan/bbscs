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
public class Permission implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3434947498241175450L;

	private Long id;

	private String permissionName;

	private String resource;

	private String action;

	private int typeID;

	public Permission() {
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Long getId() {

		return id;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public String getResource() {
		return resource;
	}

	public String getAction() {
		return action;
	}

	public int getTypeID() {
		return typeID;
	}
}
