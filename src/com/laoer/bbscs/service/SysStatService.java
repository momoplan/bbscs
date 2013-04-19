package com.laoer.bbscs.service;

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
public abstract class SysStatService {

  private long onlineNum = 0;
  private long appearTime = 0;
  private String appearTimeStr = "";
  private long allUserNum = 0;
  private String lastRegUser = "";
  private long postMainNum = 0;
  private long postNum = 0;

  public SysStatService() {
  }

  public void setOnlineNum(long onlineNum) {

    this.onlineNum = onlineNum;
  }

  public void setAppearTime(long appearTime) {

    this.appearTime = appearTime;
  }

  public void setAppearTimeStr(String appearTimeStr) {
    this.appearTimeStr = appearTimeStr;
  }

  public void setAllUserNum(long allUserNum) {
    this.allUserNum = allUserNum;
  }

  public void setLastRegUser(String lastRegUser) {
    this.lastRegUser = lastRegUser;
  }

  public void setPostMainNum(long postMainNum) {
    this.postMainNum = postMainNum;
  }

  public void setPostNum(long postNum) {
    this.postNum = postNum;
  }

  public long getOnlineNum() {

    return onlineNum;
  }

  public long getAppearTime() {

    return appearTime;
  }

  public String getAppearTimeStr() {
    return appearTimeStr;
  }

  public long getAllUserNum() {
    return allUserNum;
  }

  public String getLastRegUser() {
    return lastRegUser;
  }

  public long getPostMainNum() {
    return postMainNum;
  }

  public long getPostNum() {
    return postNum;
  }

  public abstract void load();

  public abstract void saveOnline(long nowonlinenum);

  public abstract void saveAllUserNum(long allusernum, String lastreguser);

  public abstract void savePostNum(long main, long all);

}
