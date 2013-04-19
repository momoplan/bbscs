package com.laoer.bbscs.bean;

import java.io.*;

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
public class UserDetail implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6131442610830018890L;

	/** identifier field */
	private String id;

	/** nullable persistent field */
	private String height;

	/** nullable persistent field */
	private String weight;

	/** nullable persistent field */
	private String interest;

	/** nullable persistent field */
	private String graduate;

	/** nullable persistent field */
	private String favourPeople;

	/** nullable persistent field */
	private String dreamJob;

	/** nullable persistent field */
	private String favourArt;

	/** nullable persistent field */
	private String favourMusic;

	/** nullable persistent field */
	private String favourPlace;

	/** nullable persistent field */
	private String favourMovie;

	/** nullable persistent field */
	private String favourChat;

	/** nullable persistent field */
	private String favourBook;

	/** nullable persistent field */
	private String dreamLover;

	/** nullable persistent field */
	private String favourTeam;

	/** nullable persistent field */
	private String homePage;

	/** nullable persistent field */
	private String oicqNo;

	/** nullable persistent field */
	private String icqNo;

	/** nullable persistent field */
	private int sex;

	/** nullable persistent field */
	private String brief;

	/** nullable persistent field */
	private String msn;

	/** nullable persistent field */
	private String yahoo;

	private UserInfo userInfo;

	public UserDetail() {

	}

	public String getBrief() {
		return brief;
	}

	public String getDreamJob() {
		return dreamJob;
	}

	public String getDreamLover() {
		return dreamLover;
	}

	public String getFavourArt() {
		return favourArt;
	}

	public String getFavourBook() {
		return favourBook;
	}

	public String getFavourChat() {
		return favourChat;
	}

	public String getFavourMovie() {
		return favourMovie;
	}

	public String getFavourMusic() {
		return favourMusic;
	}

	public String getFavourPeople() {
		return favourPeople;
	}

	public String getFavourPlace() {
		return favourPlace;
	}

	public String getFavourTeam() {
		return favourTeam;
	}

	public String getGraduate() {
		return graduate;
	}

	public String getHeight() {
		return height;
	}

	public String getHomePage() {
		return homePage;
	}

	public String getIcqNo() {
		return icqNo;
	}

	public String getId() {
		return id;
	}

	public String getInterest() {
		return interest;
	}

	public String getMsn() {
		return msn;
	}

	public String getOicqNo() {
		return oicqNo;
	}

	public int getSex() {
		return sex;
	}

	public String getWeight() {
		return weight;
	}

	public String getYahoo() {
		return yahoo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public void setDreamJob(String dreamJob) {
		this.dreamJob = dreamJob;
	}

	public void setDreamLover(String dreamLover) {
		this.dreamLover = dreamLover;
	}

	public void setFavourArt(String favourArt) {
		this.favourArt = favourArt;
	}

	public void setFavourBook(String favourBook) {
		this.favourBook = favourBook;
	}

	public void setFavourChat(String favourChat) {
		this.favourChat = favourChat;
	}

	public void setFavourMovie(String favourMovie) {
		this.favourMovie = favourMovie;
	}

	public void setFavourMusic(String favourMusic) {
		this.favourMusic = favourMusic;
	}

	public void setFavourPeople(String favourPeople) {
		this.favourPeople = favourPeople;
	}

	public void setFavourPlace(String favourPlace) {
		this.favourPlace = favourPlace;
	}

	public void setFavourTeam(String favourTeam) {
		this.favourTeam = favourTeam;
	}

	public void setGraduate(String graduate) {
		this.graduate = graduate;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public void setIcqNo(String icqNo) {
		this.icqNo = icqNo;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public void setOicqNo(String oicqNo) {
		this.oicqNo = oicqNo;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public void setYahoo(String yahoo) {
		this.yahoo = yahoo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
