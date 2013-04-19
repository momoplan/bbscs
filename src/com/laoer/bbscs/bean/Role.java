package com.laoer.bbscs.bean;

import java.util.*;
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
public class Role implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7369484258914632198L;

	private Integer id;

	private String roleName;

	private int typeID;

	private List permissions = new ArrayList();

	public Role() {
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public Integer getId() {

		return id;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public void setPermissions(List permissions) {
		this.permissions = permissions;
	}

	public String getRoleName() {
		return roleName;
	}

	public int getTypeID() {
		return typeID;
	}

	public List getPermissions() {
		return permissions;
	}
}
