package com.laoer.bbscs.comm;

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
public class OrderObj {

  private String orderBy = "";
  private int ascOrDesc = Constant.ORDER_DESC;

  public OrderObj() {
  }

  public OrderObj(String orderBy, int ascOrDesc) {
    this.orderBy = orderBy;
    this.ascOrDesc = ascOrDesc;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public void setAscOrDesc(int ascOrDesc) {
    this.ascOrDesc = ascOrDesc;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public int getAscOrDesc() {
    return ascOrDesc;
  }

}
