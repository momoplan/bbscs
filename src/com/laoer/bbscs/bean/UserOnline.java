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
public class UserOnline {

  private String id;
  private String userID;
  private String userName;
  private String nickName;
  private long onlineTime;
  private String atPlace;
  private int userGroupID;
  private String validateCode;
  private long boardID;
  private int hiddenUser;

  public UserOnline() {
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

  public void setOnlineTime(long onlineTime) {
    this.onlineTime = onlineTime;
  }

  public void setAtPlace(String atPlace) {
    this.atPlace = atPlace;
  }

  public void setUserGroupID(int userGroupID) {
    this.userGroupID = userGroupID;
  }

  public void setValidateCode(String validateCode) {
    this.validateCode = validateCode;
  }

  public void setBoardID(long boardID) {
    this.boardID = boardID;
  }

  public void setHiddenUser(int hiddenUser) {
    this.hiddenUser = hiddenUser;
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

  public long getOnlineTime() {
    return onlineTime;
  }

  public String getAtPlace() {
    return atPlace;
  }

  public int getUserGroupID() {
    return userGroupID;
  }

  public String getValidateCode() {
    return validateCode;
  }

  public long getBoardID() {
    return boardID;
  }

  public int getHiddenUser() {
    return hiddenUser;
  }
}
