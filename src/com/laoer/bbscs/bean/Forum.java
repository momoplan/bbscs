package com.laoer.bbscs.bean;

import java.io.*;
import java.util.*;

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
public class Forum implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 659397874752488233L;

	private String id;

	private String parentID;

	private String mainID;

	private long boardID;

	private String boardName;

	private int reNum;

	private int face;

	private String userID;

	private String userName;

	private String nickName;

	private String title;

	private String detail;

	private String sign;

	private int artSize;

	private int click;

	private long postTime;

	private long lastTime;

	private String ipAddress;

	private int isNew;

	private long elite;

	private long eliteID;

	private int agree;

	private int beAgainst;

	private int canNotDel;

	private int delSign;

	private String delUserID;

	private String delUserName;

	private long delTime;

	private String delIP;

	private String amend;

	private String doEliteName;

	private long doEliteTime;

	private int haveAttachFile;

	private List attachFileName = new ArrayList();

	private String lastPostUserName;

	private String lastPostTitle;

	private String lastPostNickName;

	private long isTop;

	private int isLock;

	private int auditing;

	private int auditingAttachFile;

	private int isVote;

	private int isHidden;

	private int editType;

	private String quoteText;

	private int postType;

	private int titleColor;

	private int canNotRe;

	private long commend;

	private int isHiddenValue;

	private int userBlog;

	private int indexStatus;

	private int quoteEditType;

	private int emailInform;

	private int msgInform;

	private String voteID;

	private String tagID;

	private String tagName;

	private int isGuest;

	private int previewAttach;
	
	private int isAnonymousReply;

	public Forum() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public void setMainID(String mainID) {
		this.mainID = mainID;
	}

	public void setBoardID(long boardID) {
		this.boardID = boardID;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public void setReNum(int reNum) {
		this.reNum = reNum;
	}

	public void setFace(int face) {
		this.face = face;
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

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public void setArtSize(int artSize) {
		this.artSize = artSize;
	}

	public void setClick(int click) {
		this.click = click;
	}

	public void setPostTime(long postTime) {
		this.postTime = postTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public void setElite(long elite) {
		this.elite = elite;
	}

	public void setEliteID(long eliteID) {
		this.eliteID = eliteID;
	}

	public void setAgree(int agree) {
		this.agree = agree;
	}

	public void setBeAgainst(int beAgainst) {
		this.beAgainst = beAgainst;
	}

	public void setCanNotDel(int canNotDel) {

		this.canNotDel = canNotDel;
	}

	public void setDelSign(int delSign) {
		this.delSign = delSign;
	}

	public void setDelUserID(String delUserID) {
		this.delUserID = delUserID;
	}

	public void setDelUserName(String delUserName) {
		this.delUserName = delUserName;
	}

	public void setDelTime(long delTime) {
		this.delTime = delTime;
	}

	public void setDelIP(String delIP) {
		this.delIP = delIP;
	}

	public void setAmend(String amend) {
		this.amend = amend;
	}

	public void setDoEliteName(String doEliteName) {
		this.doEliteName = doEliteName;
	}

	public void setDoEliteTime(long doEliteTime) {
		this.doEliteTime = doEliteTime;
	}

	public void setHaveAttachFile(int haveAttachFile) {
		this.haveAttachFile = haveAttachFile;
	}

	public void setAttachFileName(List attachFileName) {
		this.attachFileName = attachFileName;
	}

	public void setLastPostUserName(String lastPostUserName) {
		this.lastPostUserName = lastPostUserName;
	}

	public void setLastPostTitle(String lastPostTitle) {
		this.lastPostTitle = lastPostTitle;
	}

	public void setLastPostNickName(String lastPostNickName) {
		this.lastPostNickName = lastPostNickName;
	}

	public void setIsTop(long isTop) {
		this.isTop = isTop;
	}

	public void setIsLock(int isLock) {
		this.isLock = isLock;
	}

	public void setAuditing(int auditing) {
		this.auditing = auditing;
	}

	public void setAuditingAttachFile(int auditingAttachFile) {
		this.auditingAttachFile = auditingAttachFile;
	}

	public void setIsVote(int isVote) {
		this.isVote = isVote;
	}

	public void setIsHidden(int isHidden) {
		this.isHidden = isHidden;
	}

	public void setEditType(int editType) {
		this.editType = editType;
	}

	public void setQuoteText(String quoteText) {
		this.quoteText = quoteText;
	}

	public void setPostType(int postType) {
		this.postType = postType;
	}

	public void setTitleColor(int titleColor) {
		this.titleColor = titleColor;
	}

	public void setCanNotRe(int canNotRe) {
		this.canNotRe = canNotRe;
	}

	public void setCommend(long commend) {
		this.commend = commend;
	}

	public void setIsHiddenValue(int isHiddenValue) {
		this.isHiddenValue = isHiddenValue;
	}

	public void setUserBlog(int userBlog) {
		this.userBlog = userBlog;
	}

	public void setIndexStatus(int indexStatus) {
		this.indexStatus = indexStatus;
	}

	public void setQuoteEditType(int quoteEditType) {
		this.quoteEditType = quoteEditType;
	}

	public void setEmailInform(int emailInform) {
		this.emailInform = emailInform;
	}

	public void setMsgInform(int msgInform) {
		this.msgInform = msgInform;
	}

	public void setVoteID(String voteID) {
		this.voteID = voteID;
	}

	public void setTagID(String tagID) {
		this.tagID = tagID;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public void setIsGuest(int isGuest) {
		this.isGuest = isGuest;
	}

	public void setPreviewAttach(int previewAttach) {
		this.previewAttach = previewAttach;
	}

	public String getId() {
		return id;
	}

	public String getParentID() {
		return parentID;
	}

	public String getMainID() {
		return mainID;
	}

	public long getBoardID() {
		return boardID;
	}

	public String getBoardName() {
		return boardName;
	}

	public int getReNum() {
		return reNum;
	}

	public int getFace() {
		return face;
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

	public String getTitle() {
		return title;
	}

	public String getDetail() {
		return detail;
	}

	public String getSign() {
		return sign;
	}

	public int getArtSize() {
		return artSize;
	}

	public int getClick() {
		return click;
	}

	public long getPostTime() {
		return postTime;
	}

	public long getLastTime() {
		return lastTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public int getIsNew() {
		return isNew;
	}

	public long getElite() {
		return elite;
	}

	public long getEliteID() {
		return eliteID;
	}

	public int getAgree() {
		return agree;
	}

	public int getBeAgainst() {
		return beAgainst;
	}

	public int getCanNotDel() {

		return canNotDel;
	}

	public int getDelSign() {
		return delSign;
	}

	public String getDelUserID() {
		return delUserID;
	}

	public String getDelUserName() {
		return delUserName;
	}

	public long getDelTime() {
		return delTime;
	}

	public String getDelIP() {
		return delIP;
	}

	public String getAmend() {
		return amend;
	}

	public String getDoEliteName() {
		return doEliteName;
	}

	public long getDoEliteTime() {
		return doEliteTime;
	}

	public int getHaveAttachFile() {
		return haveAttachFile;
	}

	public List getAttachFileName() {
		return attachFileName;
	}

	public String getLastPostUserName() {
		return lastPostUserName;
	}

	public String getLastPostTitle() {
		return lastPostTitle;
	}

	public String getLastPostNickName() {
		return lastPostNickName;
	}

	public long getIsTop() {
		return isTop;
	}

	public int getIsLock() {
		return isLock;
	}

	public int getAuditing() {
		return auditing;
	}

	public int getAuditingAttachFile() {
		return auditingAttachFile;
	}

	public int getIsVote() {
		return isVote;
	}

	public int getIsHidden() {
		return isHidden;
	}

	public int getEditType() {
		return editType;
	}

	public String getQuoteText() {
		return quoteText;
	}

	public int getPostType() {
		return postType;
	}

	public int getTitleColor() {
		return titleColor;
	}

	public int getCanNotRe() {
		return canNotRe;
	}

	public long getCommend() {
		return commend;
	}

	public int getIsHiddenValue() {
		return isHiddenValue;
	}

	public int getUserBlog() {
		return userBlog;
	}

	public int getIndexStatus() {
		return indexStatus;
	}

	public int getQuoteEditType() {
		return quoteEditType;
	}

	public int getEmailInform() {
		return emailInform;
	}

	public int getMsgInform() {
		return msgInform;
	}

	public String getVoteID() {
		return voteID;
	}

	public String getTagID() {
		return tagID;
	}

	public String getTagName() {
		return tagName;
	}

	public int getIsGuest() {
		return isGuest;
	}

	public int getPreviewAttach() {
		return previewAttach;
	}

	public int getIsAnonymousReply() {
		return isAnonymousReply;
	}

	public void setIsAnonymousReply(int isAnonymousReply) {
		this.isAnonymousReply = isAnonymousReply;
	}
	
	
}
