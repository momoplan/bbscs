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
public class Board implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6384778470266269515L;

	private Long id;

	private long parentID;

	private List parentIDs;

	private List childIDs;

	private String boardName;

	private String explains;

	private String bulletin;

	private String boardPic;

	private int useStat;

	private int orders;

	private int needPasswd;

	private String passwd;

	private int level;

	private int boardType;

	private int allowHTML;

	private int allowUBB;

	private int auditPost;

	private int auditAttach;

	private int addUserPostNum;

	private int isHidden;

	private int isAuth;

	private long mainPostNum;

	private long postNum;

	private Map boardMaster = new HashMap();

	private Set boardTag = new HashSet();

	public Board() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setParentID(long parentID) {
		this.parentID = parentID;
	}

	public void setParentIDs(List parentIDs) {
		this.parentIDs = parentIDs;
	}

	public void setChildIDs(List childIDs) {
		this.childIDs = childIDs;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public void setExplains(String explains) {
		this.explains = explains;
	}

	public void setBulletin(String bulletin) {
		this.bulletin = bulletin;
	}

	public void setBoardPic(String boardPic) {
		this.boardPic = boardPic;
	}

	public void setUseStat(int useStat) {
		this.useStat = useStat;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public void setNeedPasswd(int needPasswd) {
		this.needPasswd = needPasswd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}

	public void setAllowHTML(int allowHTML) {
		this.allowHTML = allowHTML;
	}

	public void setAllowUBB(int allowUBB) {
		this.allowUBB = allowUBB;
	}

	public void setAuditPost(int auditPost) {
		this.auditPost = auditPost;
	}

	public void setAuditAttach(int auditAttach) {
		this.auditAttach = auditAttach;
	}

	public void setAddUserPostNum(int addUserPostNum) {
		this.addUserPostNum = addUserPostNum;
	}

	public void setIsHidden(int isHidden) {
		this.isHidden = isHidden;
	}

	public void setIsAuth(int isAuth) {
		this.isAuth = isAuth;
	}

	public void setMainPostNum(long mainPostNum) {
		this.mainPostNum = mainPostNum;
	}

	public void setPostNum(long postNum) {
		this.postNum = postNum;
	}

	public void setBoardMaster(Map boardMaster) {
		this.boardMaster = boardMaster;
	}

	public void setBoardTag(Set boardTag) {
		this.boardTag = boardTag;
	}

	public Long getId() {
		return id;
	}

	public long getParentID() {
		return parentID;
	}

	public List getParentIDs() {
		return parentIDs;
	}

	public List getChildIDs() {
		return childIDs;
	}

	public String getBoardName() {
		return boardName;
	}

	public String getExplains() {
		return explains;
	}

	public String getBulletin() {
		return bulletin;
	}

	public String getBoardPic() {
		return boardPic;
	}

	public int getUseStat() {
		return useStat;
	}

	public int getOrders() {
		return orders;
	}

	public int getNeedPasswd() {
		return needPasswd;
	}

	public String getPasswd() {
		return passwd;
	}

	public int getLevel() {
		return level;
	}

	public int getBoardType() {
		return boardType;
	}

	public int getAllowHTML() {
		return allowHTML;
	}

	public int getAllowUBB() {
		return allowUBB;
	}

	public int getAuditPost() {
		return auditPost;
	}

	public int getAuditAttach() {
		return auditAttach;
	}

	public int getAddUserPostNum() {
		return addUserPostNum;
	}

	public int getIsHidden() {
		return isHidden;
	}

	public int getIsAuth() {
		return isAuth;
	}

	public long getMainPostNum() {
		return mainPostNum;
	}

	public long getPostNum() {
		return postNum;
	}

	public Map getBoardMaster() {
		return boardMaster;
	}

	public Set getBoardTag() {
		return boardTag;
	}

	public long getTopBid() {
		long topBid = this.getId().longValue();
		if (this.getParentIDs() != null && this.getParentIDs().size() > 0) {
			topBid = ((Long) this.getParentIDs().get(0)).longValue();
		}
		return topBid;
	}

	public BoardTag getBoardTagById(String tagid) {
		BoardTag bt = null;
		Iterator it = getBoardTag().iterator();
		while (it.hasNext()) {
			bt = (BoardTag) it.next();
			if (bt.getId().equals(tagid)) {
				return bt;
			}
		}
		return bt;
	}
}
