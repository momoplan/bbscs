package com.laoer.bbscs.service.web;

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
 * @version 8.0
 */
public class PageList {

  private List objectList;
  private Pages pages;

  public PageList() {
  }

  public void setObjectList(List objectList) {
    this.objectList = objectList;
  }

  public void setPages(Pages pages) {
    this.pages = pages;
  }

  public List getObjectList() {
    return objectList;
  }

  public Pages getPages() {
    return pages;
  }
}
