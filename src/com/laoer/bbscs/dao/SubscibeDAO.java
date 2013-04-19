package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.Subscibe;
import java.util.*;

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
public interface SubscibeDAO {

  /**
   * 保存或更新Subscibe对象
   * @param subscibe Subscibe
   * @return Subscibe
   */
  public Subscibe saveSubscibe(Subscibe subscibe);

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
   * @param firstResult int
   * @param maxResults int
   * @return List
   */
  public List findSubscibesByUserID(String userID, long bid, final int firstResult,
                                    final int maxResults);

  /**
   *
   * @param id String
   * @param userID String
   * @param bid long
   */
  public void removeSubscibe(String id, String userID, long bid);

  /**
   *
   * @param subscibe Subscibe
   */
  public void removeSubscibe(Subscibe subscibe);

}
