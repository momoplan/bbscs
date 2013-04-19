package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.Commend;
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
public interface CommendDAO {

  /**
   *
   * @param commend Commend
   * @return Commend
   */
  public Commend saveCommend(Commend commend);

  /**
   *
   * @param id String
   * @return Commend
   */
  public Commend findCommendByID(String id);

  /**
   *
   * @param postID String
   * @return Commend
   */
  public Commend findCommendByPostID(String postID);

  /**
   *
   * @param commendBoardID long
   * @return int
   */
  public int getCommendNumByCommendBoardID(long commendBoardID);

  /**
   *
   * @param commendBoardID long
   * @param firstResult int
   * @param maxResults int
   * @return List
   */
  public List findCommendsByCommendBoardID(long commendBoardID, final int firstResult, final int maxResults);

  /**
   *
   * @param commendTop int
   * @return int
   */
  public int getCommendNumByCommendTop(int commendTop);

  /**
   *
   * @param commendTop int
   * @param firstResult int
   * @param maxResults int
   * @return List
   */
  public List findCommendsByCommendTop(int commendTop, final int firstResult, final int maxResults);

  /**
   *
   * @param boardCategory String
   * @return int
   */
  public int getCommendNumByBoardCategory(String boardCategory);

  /**
   *
   * @param boardCategory String
   * @param firstResult int
   * @param maxResults int
   * @return List
   */
  public List findCommendsByBoardCategory(String boardCategory, final int firstResult, final int maxResults);

  /**
   *
   * @param topCategory String
   * @return int
   */
  public int getCommendNumByTopCategory(String topCategory);

  /**
   *
   * @param topCategory String
   * @param firstResult int
   * @param maxResults int
   * @return List
   */
  public List findCommendsByTopCategory(String topCategory, final int firstResult, final int maxResults);

  /**
   *
   * @param ids List
   * @return List
   */
  public List findCommendsInIds(List ids);

  /**
   *
   * @param commend Commend
   */
  public void removeCommend(Commend commend);

  /**
   *
   * @param postID String
   */
  public void removeCommend(String postID);

}
