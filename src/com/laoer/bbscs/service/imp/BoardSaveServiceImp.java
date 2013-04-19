package com.laoer.bbscs.service.imp;

import java.util.*;

import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

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
public class BoardSaveServiceImp
    implements BoardSaveService {

  private static final Log logger = LogFactory.getLog(BoardSaveServiceImp.class);

  private BoardSaveDAO boardSaveDAO;

  public BoardSaveServiceImp() {
  }

  /**
   *
   * @param boardSave BoardSave
   * @return BoardSave
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.BoardSaveService method
   */
  public BoardSave saveBoardSave(BoardSave boardSave) throws BbscsException {
    try {
      return this.getBoardSaveDAO().saveBoardSave(boardSave);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param id String
   * @return BoardSave
   * @todo Implement this com.laoer.bbscs.service.BoardSaveService method
   */
  public BoardSave findBoardSaveById(String id) {
    return this.getBoardSaveDAO().findBoardSaveById(id);
  }

  /**
   *
   * @param userId String
   * @param bid long
   * @return BoardSave
   * @todo Implement this com.laoer.bbscs.service.BoardSaveService method
   */
  public BoardSave findBoardSaveByUidBid(String userId, long bid) {
    return this.getBoardSaveDAO().findBoardSaveByUidBid(userId, bid);
  }

  /**
   *
   * @param userId String
   * @return List
   * @todo Implement this com.laoer.bbscs.service.BoardSaveService method
   */
  public List findBoardSavesByUid(String userId) {
    return this.getBoardSaveDAO().findBoardSavesByUid(userId);
  }

  /**
   *
   * @param userId String
   * @return List
   * @todo Implement this com.laoer.bbscs.service.BoardSaveService method
   */
  public List findBoardSaveBidsByUid(String userId) {
    return this.getBoardSaveDAO().findBoardSaveBidsByUid(userId);
  }

  /**
   *
   * @param boardSave BoardSave
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.BoardSaveService method
   */
  public void removeBoardSave(BoardSave boardSave) throws BbscsException {
    try {
      this.getBoardSaveDAO().removeBoardSave(boardSave);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param userId String
   * @param bid long
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.BoardSaveService method
   */
  public void removeBoardSaveByUidBid(String userId, long bid) throws BbscsException {
    try {
      this.getBoardSaveDAO().removeBoardSaveByUidBid(userId, bid);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param bid long
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.BoardSaveService method
   */
  public void removeBoardSaveByBid(long bid) throws BbscsException {
    try {
      this.getBoardSaveDAO().removeBoardSaveByBid(bid);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  public void removeBoardSaveByBidsUid(String userId, List ids) throws BbscsException {
    try {
      this.getBoardSaveDAO().removeBoardSaveByBidsUid(userId, ids);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  public BoardSaveDAO getBoardSaveDAO() {
    return boardSaveDAO;
  }

  public void setBoardSaveDAO(BoardSaveDAO boardSaveDAO) {
    this.boardSaveDAO = boardSaveDAO;
  }
}
