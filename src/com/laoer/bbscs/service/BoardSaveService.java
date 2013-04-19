package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.BoardSave;
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
public interface BoardSaveService {

  /**
   *
   * @param boardSave BoardSave
   * @return BoardSave
   * @throws BbscsException
   */
  public BoardSave saveBoardSave(BoardSave boardSave) throws BbscsException;

  /**
   *
   * @param id String
   * @return BoardSave
   */
  public BoardSave findBoardSaveById(String id);

  /**
   *
   * @param userId String
   * @param bid long
   * @return BoardSave
   */
  public BoardSave findBoardSaveByUidBid(String userId, long bid);

  /**
   *
   * @param userId String
   * @return List
   */
  public List findBoardSavesByUid(String userId);

  /**
   *
   * @param userId String
   * @return List
   */
  public List findBoardSaveBidsByUid(String userId);

  /**
   *
   * @param boardSave BoardSave
   * @throws BbscsException
   */
  public void removeBoardSave(BoardSave boardSave) throws BbscsException;

  /**
   *
   * @param userId String
   * @param bid long
   * @throws BbscsException
   */
  public void removeBoardSaveByUidBid(String userId, long bid) throws BbscsException;

  /**
   *
   * @param bid long
   * @throws BbscsException
   */
  public void removeBoardSaveByBid(long bid) throws BbscsException;

  /**
   *
   * @param userId String
   * @param ids List
   * @throws BbscsException
   */
  public void removeBoardSaveByBidsUid(String userId, List ids) throws BbscsException;
}
