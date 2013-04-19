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
public class UserTop implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3632244343719173333L;

	private String id;

	private String userID;

	private String userName;

	private String nickName;

	private int valueType;

	private int userValue;

	private int valueInc;

	public UserTop() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setValueType(int valueType) {
		this.valueType = valueType;
	}

	public void setUserValue(int userValue) {
		this.userValue = userValue;
	}

	public void setValueInc(int valueInc) {
		this.valueInc = valueInc;
	}

	public String getId() {
		return id;
	}

	public String getUserID() {
		return userID;
	}

	public String getUserName() {
		return userName;
	}

	public String getNickName() {
		return nickName;
	}

	public int getValueType() {
		return valueType;
	}

	public int getUserValue() {
		return userValue;
	}

	public int getValueInc() {
		return valueInc;
	}
}
