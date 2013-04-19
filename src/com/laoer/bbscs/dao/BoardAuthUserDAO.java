package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.BoardAuthUser;
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
public interface BoardAuthUserDAO {

  /**
   *
   * @param boardAuthUser BoardAuthUser
   * @return BoardAuthUser
   */
  public BoardAuthUser saveBoardAuthUser(BoardAuthUser boardAuthUser);

  /**
   *
   * @param id String
   * @return BoardAuthUser
   */
  public BoardAuthUser findBoardAuthUserById(String id);

  /**
   *
   * @param bid long
   * @param uid String
   * @return BoardAuthUser
   */
  public BoardAuthUser findBoardAuthUserByBidUid(long bid, String uid);

  /**
   *
   * @param bid long
   * @param userName String
   * @return BoardAuthUser
   */
  public BoardAuthUser findBoardAuthUserByBidUserName(long bid, String userName);

  /**
   *
   * @param bid long
   * @return List
   */
  public List findBoardAuthUsersByBid(long bid);

  /**
   *
   * @param boardAuthUser BoardAuthUser
   */
  public void removeBoardAuthUser(BoardAuthUser boardAuthUser);

  /**
   *
   * @param bid long
   * @param uid String
   */
  public void removeBoardAuthUserByBidUid(long bid, String uid);

  /**
   *
   * @param bid long
   * @param userName String
   */
  public void removeBoardAuthUserByBidUserName(long bid, String userName);
}
