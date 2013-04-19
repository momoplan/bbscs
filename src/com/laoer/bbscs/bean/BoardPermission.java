package com.laoer.bbscs.bean;

import java.util.*;

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
public class BoardPermission {

  private String id;
  private long boardID;
  private int groupID;
  private List permissions = new ArrayList();

  public BoardPermission() {
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setBoardID(long boardID) {
    this.boardID = boardID;
  }

  public void setGroupID(int groupID) {
    this.groupID = groupID;
  }

  public void setPermissions(List permissions) {
    this.permissions = permissions;
  }

  public String getId() {
    return id;
  }

  public long getBoardID() {
    return boardID;
  }

  public int getGroupID() {
    return groupID;
  }

  public List getPermissions() {
    return permissions;
  }
}
