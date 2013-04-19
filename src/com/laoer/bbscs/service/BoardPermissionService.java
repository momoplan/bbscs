package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.BoardPermission;
import com.laoer.bbscs.exception.BbscsException;
import java.util.List;

/**
 * <p>Title: Tianyi BBS</p>
 *
 * <p>Description: BBSCS</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Laoer.com</p>
 *
 * @author Gong Tianyi
 * @version 7.0
 */
public interface BoardPermissionService {

  /**
   * 保存或更新BoardPermission对象
   * @param bp BoardPermission
   * @return BoardPermission
   * @throws BbscsException
   */
  public BoardPermission saveBoardPermission(BoardPermission bp) throws BbscsException;

  /**
   *
   * @param bp BoardPermission
   * @return BoardPermission
   * @throws BbscsException
   */
  public BoardPermission updateBoardPermission(BoardPermission bp) throws BbscsException;

  /**
   * 根据ID取得BoardPermission对象
   * @param id String
   * @return BoardPermission
   */
  public BoardPermission findBoardPermissionByID(String id);

  /**
   * 根据BoardID和GrouopID取得BoardPermission对象
   * @param bid long
   * @param gid int
   * @return BoardPermission
   */
  public BoardPermission findBoardPermissionByBidGid(long bid, int gid);

  /**
   * 根据BoardID取得BoardPermission列表
   * @param bid long
   * @return List
   */
  public List findBoardPermissionsByBid(long bid);

  /**
   * 根据GroupID取得BoardPermission对象
   * @param gid int
   * @return List
   */
  public List findBoardPermissionsByGid(int gid);

  /**
   * 根据BoardID删除BoardPermission对象
   * @param bid long
   * @throws BbscsException
   */
  public void removeBoardPermissionsByBid(long bid) throws BbscsException;

  /**
   * 根据GroupID删除BoardPermission对象
   * @param gid int
   * @throws BbscsException
   */
  public void removeBoardPermissionsByGid(int gid) throws BbscsException;

}
