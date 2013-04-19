package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.BoardSave;

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
public interface BoardSaveDAO {

  /**
   *
   * @param boardSave BoardSave
   * @return BoardSave
   */
  public BoardSave saveBoardSave(BoardSave boardSave);

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
   */
  public void removeBoardSave(BoardSave boardSave);

  /**
   *
   * @param userId String
   * @param bid long
   */
  public void removeBoardSaveByUidBid(String userId, long bid);

  /**
   *
   * @param bid long
   */
  public void removeBoardSaveByBid(long bid);

  /**
   *
   * @param userId String
   * @param ids List
   */
  public void removeBoardSaveByBidsUid(String userId, List ids);

}
