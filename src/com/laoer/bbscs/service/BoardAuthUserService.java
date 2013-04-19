package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.BoardAuthUser;
import com.laoer.bbscs.exception.BbscsException;
import java.util.List;

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
public interface BoardAuthUserService {

  /**
   *
   * @param boardAuthUser BoardAuthUser
   * @return BoardAuthUser
   * @throws BbscsException
   */
  public BoardAuthUser saveBoardAuthUser(BoardAuthUser boardAuthUser) throws BbscsException;

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
   * @throws BbscsException
   */
  public void removeBoardAuthUser(BoardAuthUser boardAuthUser) throws BbscsException;

  /**
   *
   * @param bid long
   * @param uid String
   * @throws BbscsException
   */
  public void removeBoardAuthUserByBidUid(long bid, String uid) throws BbscsException;

  /**
   *
   * @param bid long
   * @param userName String
   * @throws BbscsException
   */
  public void removeBoardAuthUserByBidUserName(long bid, String userName) throws BbscsException;

}
