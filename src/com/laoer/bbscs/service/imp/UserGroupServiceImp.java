package com.laoer.bbscs.service.imp;

import java.util.*;

import org.apache.commons.logging.*;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.comm.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;

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
public class UserGroupServiceImp
    implements UserGroupService {

  private static final Log logger = LogFactory.getLog(UserGroupServiceImp.class);

  private UserGroupDAO userGroupDAO;

  private BoardDAO boardDAO;

  private BoardPermissionDAO boardPermissionDAO;

  private Cache userPermissionCache;

  public UserGroupServiceImp() {
  }

  /**
   * 保存或更新UserGroup对象
   *
   * @param ug UserGroup
   * @return UserGroup
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.UserGroupService method
   */
  public UserGroup saveUserGroup(UserGroup ug) throws BbscsException {
    try {
      ug = this.getUserGroupDAO().saveUserGroup(ug);
      List bplist = this.getBoardPermissionDAO().findBoardPermissionsByGid(ug.getId().intValue()); //取得版区权限列表
      if (bplist.size() == 0) { //没有版区权限列表,说明是新的用户组,增加版区权限记录
        List blist = this.getBoardDAO().findBoardsAll();
        Board b;
        for (int i = 0; i < blist.size(); i++) {
          b = (Board) blist.get(i);
          BoardPermission bp = new BoardPermission();
          bp.setBoardID(b.getId().longValue());
          bp.setGroupID(ug.getId().intValue());
          bp.setPermissions(Constant.BOARD_PERMISSION_GROUP_LIST_1);
          this.getBoardPermissionDAO().saveBoardPermission(bp);
        }
      }
      this.clearPermissionCache();
      return ug;
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  public UserGroup updateUserGroup(UserGroup ug) throws BbscsException {
    try {
      ug = this.getUserGroupDAO().updateUserGroup(ug);
      this.clearPermissionCache();
      return ug;
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 根据ID取得UserGroup对象
   *
   * @param id int
   * @return UserGroup
   * @todo Implement this com.laoer.bbscs.service.UserGroupService method
   */
  public UserGroup findUserGroupByID(int id) {
    return this.getUserGroupDAO().findUserGroupByID(id);
  }

  /**
   * 取得所有UserGroup列表
   *
   * @return List
   * @todo Implement this com.laoer.bbscs.service.UserGroupService method
   */
  public List findUserGroupsAll() {
    return this.getUserGroupDAO().findUserGroupsAll();
  }

  /**
   *
   * @param ids List
   * @return List
   */
  public List findUserGroupInIDs(List ids) {
    return this.getUserGroupDAO().findUserGroupInIDs(ids);
  }

  /**
   * 删除UserGroup对象
   *
   * @param ug UserGroup
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.UserGroupService method
   */
  public void removeUserGroup(UserGroup ug) throws BbscsException {
    try {
      this.getUserGroupDAO().removeUserGroup(ug);
      this.getBoardPermissionDAO().removeBoardPermissionsByGid(ug.getId().intValue());
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


  public UserGroupDAO getUserGroupDAO() {
    return userGroupDAO;
  }

  public BoardDAO getBoardDAO() {
    return boardDAO;
  }

  public BoardPermissionDAO getBoardPermissionDAO() {
    return boardPermissionDAO;
  }

  public Cache getUserPermissionCache() {
    return userPermissionCache;
  }

  public void setUserGroupDAO(UserGroupDAO userGroupDAO) {
    this.userGroupDAO = userGroupDAO;
  }

  public void setBoardDAO(BoardDAO boardDAO) {
    this.boardDAO = boardDAO;
  }

  public void setBoardPermissionDAO(BoardPermissionDAO boardPermissionDAO) {
    this.boardPermissionDAO = boardPermissionDAO;
  }

  public void setUserPermissionCache(Cache userPermissionCache) {
    this.userPermissionCache = userPermissionCache;
  }
}
