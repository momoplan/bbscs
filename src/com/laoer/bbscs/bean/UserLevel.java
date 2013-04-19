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
public class UserLevel {

  private String levelName;
  private String id;
  private int levelValue;
  private int levelType;

  public UserLevel() {
  }

  public void setLevelName(String levelName) {
    this.levelName = levelName;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setLevelValue(int levelValue) {
    this.levelValue = levelValue;
  }

  public void setLevelType(int levelType) {
    this.levelType = levelType;
  }

  public String getLevelName() {
    return levelName;
  }

  public String getId() {
    return id;
  }

  public int getLevelValue() {
    return levelValue;
  }

  public int getLevelType() {
    return levelType;
  }
}
