package com.laoer.bbscs.bean;

import java.io.*;
import java.util.*;

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
public class BookMark implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2907756203476233319L;

	private String id;

	private String userID;

	private String bookMarkName;

	private String url;

	private String alt;

	private int isShare;

	private Date createTime;

	public BookMark() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setBookMarkName(String bookMarkName) {
		this.bookMarkName = bookMarkName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public void setIsShare(int isShare) {
		this.isShare = isShare;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public String getUserID() {
		return userID;
	}

	public String getBookMarkName() {
		return bookMarkName;
	}

	public String getUrl() {
		return url;
	}

	public String getAlt() {
		return alt;
	}

	public int getIsShare() {
		return isShare;
	}

	public Date getCreateTime() {
		return createTime;
	}
}
