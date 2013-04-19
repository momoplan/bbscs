package com.laoer.bbscs.bean;

/**
 * <p>Title: TianyiBBS</p>
 *
 * <p>Description: BBSCS</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Laoer.com</p>
 *
 * @author Laoer
 * @version 7.0
 */
public class BoardAuthUser {

  private String id;
  private long boardID;
  private String userID;
  private String userName;
  private long createTime;

  public BoardAuthUser() {
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setBoardID(long boardID) {
    this.boardID = boardID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setCreateTime(long createTime) {
    this.createTime = createTime;
  }

  public String getId() {
    return id;
  }

  public long getBoardID() {
    return boardID;
  }

  public String getUserID() {
    return userID;
  }

  public String getUserName() {
    return userName;
  }

  public long getCreateTime() {
    return createTime;
  }
}
