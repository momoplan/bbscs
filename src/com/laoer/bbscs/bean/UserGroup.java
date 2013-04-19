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
public class UserGroup implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6518820956240847106L;

	private Integer id;

	private String groupName;

	private String groupDesc;

	private int typeID;

	private Set roles = new HashSet();

	public UserGroup() {
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public Integer getId() {

		return id;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public int getTypeID() {
		return typeID;
	}

	public Set getRoles() {
		return roles;
	}
}
