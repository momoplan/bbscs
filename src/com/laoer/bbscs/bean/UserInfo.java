package com.laoer.bbscs.bean;

import java.io.*;
import java.util.*;

/**
 * <p>
 * Title: 天乙社区
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
 * @version 8.0
 */
public class UserInfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3061361040619333288L;

	private String id;

	private String userName;

	private String nickName;

	private String passwd;

	private String rePasswd;

	private String email;

	private String question;

	private String answer;

	private Date regTime;

	private Date loginTime;

	private String loginIP;

	private int loginTimes;

	private Date lastLoginTime;

	private String lastLoginIP;

	private int articleNum;

	private long stayTime;

	private int articleEliteNum;

	private int lifeForce;

	private int userTitle;

	private int literary;

	private int experience;

	private int userKnow;

	private String signName0;

	private String signDetail0;

	private String signName1;

	private String signDetail1;

	private String signName2;

	private String signDetail2;

	private int havePic;

	private String picFileName;

	private int forumPerNum;

	private int postPerNum;

	private String userFrom;

	private String timeZone;

	private int birthYear;

	private int birthMonth;

	private int birthDay;

	private int receiveNote;

	private int acceptFriend;

	private int forumViewMode;

	private UserDetail userDetail;

	private int validated;

	private int groupID;

	private int coin;

	private int hiddenLogin;

	private int editType;

	private String userLocale;

	private String validateCode;
	
	private String userNo;	

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public UserInfo() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setRePasswd(String rePasswd) {
		this.rePasswd = rePasswd;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}

	public void setStayTime(long stayTime) {
		this.stayTime = stayTime;
	}

	public void setArticleEliteNum(int articleEliteNum) {
		this.articleEliteNum = articleEliteNum;
	}

	public void setLifeForce(int lifeForce) {
		this.lifeForce = lifeForce;
	}

	public void setUserTitle(int userTitle) {
		this.userTitle = userTitle;
	}

	public void setLiterary(int literary) {
		this.literary = literary;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public void setUserKnow(int userKnow) {
		this.userKnow = userKnow;
	}

	public void setSignName0(String signName0) {

		this.signName0 = signName0;
	}

	public void setSignDetail0(String signDetail0) {

		this.signDetail0 = signDetail0;
	}

	public void setSignName1(String signName1) {

		this.signName1 = signName1;
	}

	public void setSignDetail1(String signDetail1) {

		this.signDetail1 = signDetail1;
	}

	public void setSignName2(String signName2) {

		this.signName2 = signName2;
	}

	public void setSignDetail2(String signDetail2) {

		this.signDetail2 = signDetail2;
	}

	public void setHavePic(int havePic) {
		this.havePic = havePic;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public void setForumPerNum(int forumPerNum) {
		this.forumPerNum = forumPerNum;
	}

	public void setPostPerNum(int postPerNum) {
		this.postPerNum = postPerNum;
	}

	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}

	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

	public void setReceiveNote(int receiveNote) {
		this.receiveNote = receiveNote;
	}

	public void setAcceptFriend(int acceptFriend) {
		this.acceptFriend = acceptFriend;
	}

	public void setForumViewMode(int forumViewMode) {
		this.forumViewMode = forumViewMode;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public void setValidated(int validated) {
		this.validated = validated;
	}

	public void setGroupID(int groupID) {

		this.groupID = groupID;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public void setHiddenLogin(int hiddenLogin) {
		this.hiddenLogin = hiddenLogin;
	}

	public void setEditType(int editType) {
		this.editType = editType;
	}

	public void setUserLocale(String userLocale) {
		this.userLocale = userLocale;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getNickName() {
		return nickName;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getRePasswd() {
		return rePasswd;
	}

	public String getEmail() {
		return email;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public Date getRegTime() {
		return regTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public int getLoginTimes() {
		return loginTimes;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public int getArticleNum() {
		return articleNum;
	}

	public long getStayTime() {
		return stayTime;
	}

	public int getArticleEliteNum() {
		return articleEliteNum;
	}

	public int getLifeForce() {
		return lifeForce;
	}

	public int getUserTitle() {
		return userTitle;
	}

	public int getLiterary() {
		return literary;
	}

	public int getExperience() {
		return experience;
	}

	public int getUserKnow() {
		return userKnow;
	}

	public String getSignName0() {

		return signName0;
	}

	public String getSignDetail0() {

		return signDetail0;
	}

	public String getSignName1() {

		return signName1;
	}

	public String getSignDetail1() {

		return signDetail1;
	}

	public String getSignName2() {

		return signName2;
	}

	public String getSignDetail2() {

		return signDetail2;
	}

	public int getHavePic() {
		return havePic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public int getForumPerNum() {
		return forumPerNum;
	}

	public int getPostPerNum() {
		return postPerNum;
	}

	public String getUserFrom() {
		return userFrom;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public int getBirthDay() {
		return birthDay;
	}

	public int getReceiveNote() {
		return receiveNote;
	}

	public int getAcceptFriend() {
		return acceptFriend;
	}

	public int getForumViewMode() {
		return forumViewMode;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public int getValidated() {
		return validated;
	}

	public int getGroupID() {

		return groupID;
	}

	public int getCoin() {
		return coin;
	}

	public int getHiddenLogin() {
		return hiddenLogin;
	}

	public int getEditType() {
		return editType;
	}

	public String getUserLocale() {
		return userLocale;
	}

	public String getValidateCode() {
		return validateCode;
	}
}
