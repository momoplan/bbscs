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
public class ForumBuy {

  private String id;
  private String postID;
  private String buyFromID;
  private String buyFromName;
  private String buyToID;
  private String buyToName;
  private int buyPrice;
  private int buyToUserIncome;
  private long buyTime;

  public ForumBuy() {
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setPostID(String postID) {
    this.postID = postID;
  }

  public void setBuyFromID(String buyFromID) {
    this.buyFromID = buyFromID;
  }

  public void setBuyFromName(String buyFromName) {
    this.buyFromName = buyFromName;
  }

  public void setBuyToID(String buyToID) {
    this.buyToID = buyToID;
  }

  public void setBuyToName(String buyToName) {
    this.buyToName = buyToName;
  }

  public void setBuyPrice(int buyPrice) {
    this.buyPrice = buyPrice;
  }

  public void setBuyToUserIncome(int buyToUserIncome) {
    this.buyToUserIncome = buyToUserIncome;
  }

  public void setBuyTime(long buyTime) {
    this.buyTime = buyTime;
  }

  public String getId() {
    return id;
  }

  public String getPostID() {
    return postID;
  }

  public String getBuyFromID() {
    return buyFromID;
  }

  public String getBuyFromName() {
    return buyFromName;
  }

  public String getBuyToID() {
    return buyToID;
  }

  public String getBuyToName() {
    return buyToName;
  }

  public int getBuyPrice() {
    return buyPrice;
  }

  public int getBuyToUserIncome() {
    return buyToUserIncome;
  }

  public long getBuyTime() {
    return buyTime;
  }
}
