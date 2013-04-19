package com.laoer.bbscs.service.imp;

import java.util.*;

import org.apache.commons.logging.*;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;

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
public class BoardAuthUserServiceImp
    implements BoardAuthUserService {

  private static final Log logger = LogFactory.getLog(BoardAuthUserServiceImp.class);

  private BoardAuthUserDAO boardAuthUserDAO;

  public BoardAuthUserServiceImp() {
  }

  /**
   *
   * @param boardAuthUser BoardAuthUser
   * @return BoardAuthUser
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.BoardAuthUserService method
   */
  public BoardAuthUser saveBoardAuthUser(BoardAuthUser boardAuthUser) throws BbscsException {
    try {
      return this.getBoardAuthUserDAO().saveBoardAuthUser(boardAuthUser);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param id String
   * @return BoardAuthUser
   * @todo Implement this com.laoer.bbscs.service.BoardAuthUserService method
   */
  public BoardAuthUser findBoardAuthUserById(String id) {
    return this.getBoardAuthUserDAO().findBoardAuthUserById(id);
  }

  /**
   *
   * @param bid long
   * @param uid String
   * @return BoardAuthUser
   * @todo Implement this com.laoer.bbscs.service.BoardAuthUserService method
   */
  public BoardAuthUser findBoardAuthUserByBidUid(long bid, String uid) {
    return this.getBoardAuthUserDAO().findBoardAuthUserByBidUid(bid, uid);
  }

  /**
   *
   * @param bid long
   * @param userName String
   * @return BoardAuthUser
   * @todo Implement this com.laoer.bbscs.service.BoardAuthUserService method
   */
  public BoardAuthUser findBoardAuthUserByBidUserName(long bid, String userName) {
    return this.getBoardAuthUserDAO().findBoardAuthUserByBidUserName(bid, userName);
  }

  /**
   *
   * @param bid long
   * @return List
   * @todo Implement this com.laoer.bbscs.service.BoardAuthUserService method
   */
  public List findBoardAuthUsersByBid(long bid) {
    return this.getBoardAuthUserDAO().findBoardAuthUsersByBid(bid);
  }

  /**
   *
   * @param boardAuthUser BoardAuthUser
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.BoardAuthUserService method
   */
  public void removeBoardAuthUser(BoardAuthUser boardAuthUser) throws BbscsException {
    try {
      this.getBoardAuthUserDAO().removeBoardAuthUser(boardAuthUser);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param bid long
   * @param uid String
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.BoardAuthUserService method
   */
  public void removeBoardAuthUserByBidUid(long bid, String uid) throws BbscsException {
    try {
      this.getBoardAuthUserDAO().removeBoardAuthUserByBidUid(bid, uid);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param bid long
   * @param userName String
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.BoardAuthUserService method
   */
  public void removeBoardAuthUserByBidUserName(long bid, String userName) throws BbscsException {
    try {
      this.getBoardAuthUserDAO().removeBoardAuthUserByBidUserName(bid, userName);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  public BoardAuthUserDAO getBoardAuthUserDAO() {
    return boardAuthUserDAO;
  }

  public void setBoardAuthUserDAO(BoardAuthUserDAO boardAuthUserDAO) {
    this.boardAuthUserDAO = boardAuthUserDAO;
  }
}
