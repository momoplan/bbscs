package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.Subscibe;
import com.laoer.bbscs.exception.BbscsException;
import java.util.List;
import com.laoer.bbscs.service.web.PageList;
import com.laoer.bbscs.service.web.Pages;

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
public interface SubscibeService {

  /**
   *
   * @param subscibe Subscibe
   * @return Subscibe
   * @throws BbscsException
   */
  public Subscibe saveSubscibe(Subscibe subscibe) throws BbscsException;

  /**
   *
   * @param id String
   * @param userID String
   * @param bid long
   * @return Subscibe
   */
  public Subscibe findSubscibeByID(String id, String userID, long bid);

  /**
   *
   * @param postID String
   * @param userID String
   * @param bid long
   * @return Subscibe
   */
  public Subscibe findSubscibeByPostID(String postID, String userID, long bid);

  /**
   *
   * @param postID String
   * @param bid long
   * @return List
   */
  public List findSubscibesSend(String postID, long bid);

  /**
   *
   * @param userID String
   * @param bid long
   * @return int
   */
  public long getSubscibeNumByUserID(String userID, long bid);

  /**
   *
   * @param userID String
   * @param bid long
   * @param pages Pages
   * @return PageList
   */
  public PageList findSubscibesByUserID(String userID, long bid, Pages pages);

  /**
   *
   * @param id String
   * @param userID String
   * @param bid long
   * @throws BbscsException
   */
  public void removeSubscibe(String id, String userID, long bid) throws BbscsException;

  /**
   *
   * @param subscibe Subscibe
   * @throws BbscsException
   */
  public void removeSubscibe(Subscibe subscibe) throws BbscsException;

}
