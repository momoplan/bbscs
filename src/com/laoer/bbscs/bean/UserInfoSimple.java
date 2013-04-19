package com.laoer.bbscs.bean;

/**
 * <p>Title: Tianyi BBS</p>
 *
 * <p>Description: BBSCS</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Laoer.com</p>
 *
 * @author Gong Tianyi
 * @version 7.0
 */
public class UserInfoSimple {

  private String id = "";
  private long regTime = System.currentTimeMillis();
  private long loginTime = System.currentTimeMillis();
  private int articleNum = 0;
  private int articleEliteNum = 0;
  private int userTitle = 0;
  private int lifeForce = 0;
  private int coin = 0;
  private int literary = 0;
  private int userKnow = 0;
  private int experience = 0;
  private int havePic = 0;
  private String picFileName = "-";
  private String userFrom = "-";
  private String userNo = "00000000";

  public String getUserNo() {
	return userNo;
}

public void setUserNo(String userNo) {
	this.userNo = userNo;
}

public UserInfoSimple() {
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setRegTime(long regTime) {
    this.regTime = regTime;
  }

  public void setLoginTime(long loginTime) {
    this.loginTime = loginTime;
  }

  public void setArticleNum(int articleNum) {
    this.articleNum = articleNum;
  }

  public void setArticleEliteNum(int articleEliteNum) {
    this.articleEliteNum = articleEliteNum;
  }

  public void setUserTitle(int userTitle) {
    this.userTitle = userTitle;
  }

  public void setLifeForce(int lifeForce) {
    this.lifeForce = lifeForce;
  }

  public void setCoin(int coin) {
    this.coin = coin;
  }

  public void setLiterary(int literary) {
    this.literary = literary;
  }

  public void setUserKnow(int userKnow) {
    this.userKnow = userKnow;
  }

  public void setExperience(int experience) {
    this.experience = experience;
  }

  public void setHavePic(int havePic) {
    this.havePic = havePic;
  }

  public void setPicFileName(String picFileName) {
    this.picFileName = picFileName;
  }

  public void setUserFrom(String userFrom) {
    this.userFrom = userFrom;
  }

  public String getId() {
    return id;
  }

  public long getRegTime() {
    return regTime;
  }

  public long getLoginTime() {
    return loginTime;
  }

  public int getArticleNum() {
    return articleNum;
  }

  public int getArticleEliteNum() {
    return articleEliteNum;
  }

  public int getUserTitle() {
    return userTitle;
  }

  public int getLifeForce() {
    return lifeForce;
  }

  public int getCoin() {
    return coin;
  }

  public int getLiterary() {
    return literary;
  }

  public int getUserKnow() {
    return userKnow;
  }

  public int getExperience() {
    return experience;
  }

  public int getHavePic() {
    return havePic;
  }

  public String getPicFileName() {
    return picFileName;
  }

  public String getUserFrom() {
    return userFrom;
  }

}
