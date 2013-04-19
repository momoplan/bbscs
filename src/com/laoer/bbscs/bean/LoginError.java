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
public class LoginError {

  private String id;
  private String userID;
  private int errorTimes;
  private long loginTime;

  public LoginError() {
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public void setErrorTimes(int errorTimes) {
    this.errorTimes = errorTimes;
  }

  public void setLoginTime(long loginTime) {
    this.loginTime = loginTime;
  }

  public String getId() {
    return id;
  }

  public String getUserID() {
    return userID;
  }

  public int getErrorTimes() {
    return errorTimes;
  }

  public long getLoginTime() {
    return loginTime;
  }
}
