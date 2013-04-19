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
public class Note implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3477954144482528377L;

	private String id;

	private String fromID;

	private String fromUserName;

	private String fromNickName;

	private String toID;

	private String toUserName;

	private String toNickName;

	private int noteType;

	private String noteContext;

	private Date createTime;

	private int isNew;

	private int needRe;

	private String noteTitle;

	private int isRe;

	private int sysMsg;

	public Note() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFromID(String fromID) {
		this.fromID = fromID;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public void setFromNickName(String fromNickName) {
		this.fromNickName = fromNickName;
	}

	public void setToID(String toID) {
		this.toID = toID;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public void setToNickName(String toNickName) {
		this.toNickName = toNickName;
	}

	public void setNoteType(int noteType) {
		this.noteType = noteType;
	}

	public void setNoteContext(String noteContext) {
		this.noteContext = noteContext;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public void setNeedRe(int needRe) {

		this.needRe = needRe;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public void setIsRe(int isRe) {
		this.isRe = isRe;
	}

	public void setSysMsg(int sysMsg) {
		this.sysMsg = sysMsg;
	}

	public String getId() {
		return id;
	}

	public String getFromID() {
		return fromID;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public String getFromNickName() {
		return fromNickName;
	}

	public String getToID() {
		return toID;
	}

	public String getToUserName() {
		return toUserName;
	}

	public String getToNickName() {
		return toNickName;
	}

	public int getNoteType() {
		return noteType;
	}

	public String getNoteContext() {
		return noteContext;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public int getIsNew() {
		return isNew;
	}

	public int getNeedRe() {

		return needRe;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public int getIsRe() {
		return isRe;
	}

	public int getSysMsg() {
		return sysMsg;
	}
}
