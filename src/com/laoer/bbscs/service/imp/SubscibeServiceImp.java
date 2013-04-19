package com.laoer.bbscs.service.imp;

import java.util.*;

import org.apache.commons.logging.*;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.service.web.*;

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
public class SubscibeServiceImp
    implements SubscibeService {

  private static final Log logger = LogFactory.getLog(SubscibeServiceImp.class);

  private SubscibeDAO subscibeDAO;

  public SubscibeServiceImp() {
  }

  /**
   *
   * @param subscibe Subscibe
   * @return Subscibe
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.SubscibeService method
   */
  public Subscibe saveSubscibe(Subscibe subscibe) throws BbscsException {
    try {
      return this.getSubscibeDAO().saveSubscibe(subscibe);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param id String
   * @param userID String
   * @param bid long
   * @return Subscibe
   * @todo Implement this com.laoer.bbscs.service.SubscibeService method
   */
  public Subscibe findSubscibeByID(String id, String userID, long bid) {
    return this.getSubscibeDAO().findSubscibeByID(id, userID, bid);
  }

  /**
   *
   * @param postID String
   * @param userID String
   * @param bid long
   * @return Subscibe
   * @todo Implement this com.laoer.bbscs.service.SubscibeService method
   */
  public Subscibe findSubscibeByPostID(String postID, String userID, long bid) {
    return this.getSubscibeDAO().findSubscibeByPostID(postID, userID, bid);
  }

  /**
   *
   * @param postID String
   * @param bid long
   * @return List
   * @todo Implement this com.laoer.bbscs.service.SubscibeService method
   */
  public List findSubscibesSend(String postID, long bid) {
    return this.getSubscibeDAO().findSubscibesSend(postID, bid);
  }

  /**
   *
   * @param userID String
   * @param bid long
   * @return int
   * @todo Implement this com.laoer.bbscs.service.SubscibeService method
   */
  public long getSubscibeNumByUserID(String userID, long bid) {
    return this.getSubscibeDAO().getSubscibeNumByUserID(userID, bid);
  }

  /**
   *
   * @param userID String
   * @param bid long
   * @param pages Pages
   * @return PageList
   * @todo Implement this com.laoer.bbscs.service.SubscibeService method
   */
  public PageList findSubscibesByUserID(String userID, long bid, Pages pages) {
    PageList pl = new PageList();
    if (pages.getTotalNum() == -1) {
      pages.setTotalNum(this.getSubscibeDAO().getSubscibeNumByUserID(userID, bid));
    }
    pages.executeCount();

    List l = this.getSubscibeDAO().findSubscibesByUserID(userID, bid, pages.getSpage(),
        pages.getPerPageNum());
    pl.setObjectList(l);
    pl.setPages(pages);
    return pl;

  }

  /**
   *
   * @param id String
   * @param userID String
   * @param bid long
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.SubscibeService method
   */
  public void removeSubscibe(String id, String userID, long bid) throws BbscsException {
    try {
      this.getSubscibeDAO().removeSubscibe(id, userID, bid);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param subscibe Subscibe
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.SubscibeService method
   */
  public void removeSubscibe(Subscibe subscibe) throws BbscsException {
    try {
      this.getSubscibeDAO().removeSubscibe(subscibe);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  public SubscibeDAO getSubscibeDAO() {
    return subscibeDAO;
  }

  public void setSubscibeDAO(SubscibeDAO subscibeDAO) {
    this.subscibeDAO = subscibeDAO;
  }
}
