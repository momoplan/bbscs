package com.laoer.bbscs.service.imp;

import java.util.*;

import org.apache.commons.logging.*;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.comm.Constant;

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
public class BoardPermissionServiceImp
    implements BoardPermissionService {

  private static final Log logger = LogFactory.getLog(BoardPermissionServiceImp.class);

  private BoardPermissionDAO boardPermissionDAO;

  private Cache userPermissionCache;

  public BoardPermissionServiceImp() {
  }

  /**
   * 保存或更新BoardPermission对象
   *
   * @param bp BoardPermission
   * @return BoardPermission
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.BoardPermissionService method
   */
  public BoardPermission saveBoardPermission(BoardPermission bp) throws BbscsException {
    try {
      bp = this.getBoardPermissionDAO().saveBoardPermission(bp);
      this.clearPermissionCache();
      return bp;
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  public BoardPermission updateBoardPermission(BoardPermission bp) throws BbscsException {
    try {
      bp = this.getBoardPermissionDAO().updateBoardPermission(bp);
      this.clearPermissionCache();
      return bp;
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 根据ID取得BoardPermission对象
   *
   * @param id String
   * @return BoardPermission
   * @todo Implement this com.laoer.bbscs.service.BoardPermissionService method
   */
  public BoardPermission findBoardPermissionByID(String id) {
    return this.getBoardPermissionDAO().findBoardPermissionByID(id);
  }

  /**
   * 根据BoardID和GrouopID取得BoardPermission对象
   *
   * @param bid long
   * @param gid int
   * @return BoardPermission
   * @todo Implement this com.laoer.bbscs.service.BoardPermissionService method
   */
  public BoardPermission findBoardPermissionByBidGid(long bid, int gid) {
    return this.getBoardPermissionDAO().findBoardPermissionByBidGid(bid, gid);
  }

  /**
   * 根据BoardID取得BoardPermission列表
   *
   * @param bid long
   * @return List
   * @todo Implement this com.laoer.bbscs.service.BoardPermissionService method
   */
  public List findBoardPermissionsByBid(long bid) {
    return this.getBoardPermissionDAO().findBoardPermissionsByBid(bid);
  }

  /**
   * 根据GroupID取得BoardPermission对象
   *
   * @param gid int
   * @return List
   * @todo Implement this com.laoer.bbscs.service.BoardPermissionService method
   */
  public List findBoardPermissionsByGid(int gid) {
    return this.getBoardPermissionDAO().findBoardPermissionsByGid(gid);
  }

  /**
   * 根据BoardID删除BoardPermission对象
   *
   * @param bid long
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.BoardPermissionService method
   */
  public void removeBoardPermissionsByBid(long bid) throws BbscsException {
    try {
      this.getBoardPermissionDAO().removeBoardPermissionsByBid(bid);
      this.clearPermissionCache();
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 根据GroupID删除BoardPermission对象
   *
   * @param gid int
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.BoardPermissionService method
   */
  public void removeBoardPermissionsByGid(int gid) throws BbscsException {
    try {
      this.getBoardPermissionDAO().removeBoardPermissionsByGid(gid);
      this.clearPermissionCache();
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  private void clearPermissionCache() {
    if (Constant.USE_PERMISSION_CACHE) {
      this.getUserPermissionCache().removeAll();
    }
  }

  public BoardPermissionDAO getBoardPermissionDAO() {
    return boardPermissionDAO;
  }

  public Cache getUserPermissionCache() {
    return userPermissionCache;
  }

  public void setBoardPermissionDAO(BoardPermissionDAO boardPermissionDAO) {
    this.boardPermissionDAO = boardPermissionDAO;
  }

  public void setUserPermissionCache(Cache userPermissionCache) {
    this.userPermissionCache = userPermissionCache;
  }
}
