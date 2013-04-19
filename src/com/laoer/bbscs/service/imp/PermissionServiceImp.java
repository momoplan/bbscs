package com.laoer.bbscs.service.imp;

import java.util.*;

import org.apache.commons.logging.*;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.comm.*;

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
public class PermissionServiceImp
    implements PermissionService {

  private static final Log logger = LogFactory.getLog(PermissionServiceImp.class);

  private PermissionDAO permissionDAO;

  private Cache userPermissionCache;

  public PermissionServiceImp() {
  }

  /**
   * 保存Permission对象
   *
   * @param permission Permission
   * @return Permission
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.PermissionService method
   */
  public Permission savePermission(Permission permission) throws BbscsException {
    try {
      permission = this.getPermissionDAO().savePermission(permission);
      this.clearPermissionCache();
      return permission;
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 更新Permission对象
   * @param permission Permission
   * @return Permission
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.PermissionService method
   */
  public Permission updatePermission(Permission permission) throws BbscsException {
    try {
      permission = this.getPermissionDAO().updatePermission(permission);
      this.clearPermissionCache();
      return permission;
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param id int
   * @return Permission
   * @todo Implement this com.laoer.bbscs.service.PermissionService method
   */
  public Permission findPermissionByID(long id) {
    return this.getPermissionDAO().findPermissionByID(id);
  }

  /**
   * 取得所有Permission列表
   *
   * @return List
   * @todo Implement this com.laoer.bbscs.service.PermissionService method
   */
  public List findPermissionsAll() {
    return this.getPermissionDAO().findPermissionsAll();
  }

  /**
   * 按照typeID取得Permission列表
   *
   * @param typeID int
   * @return List
   * @todo Implement this com.laoer.bbscs.service.PermissionService method
   */
  public List findPermissionsByTypeID(int typeID) {
    return this.getPermissionDAO().findPermissionsByTypeID(typeID);
  }

  /**
   *
   * @param ids List
   * @return List
   */
  public List findPermissionnIDs(List ids) {
    return this.getPermissionDAO().findPermissionnIDs(ids);
  }

  private void clearPermissionCache() {
    if (Constant.USE_PERMISSION_CACHE) {
      this.getUserPermissionCache().removeAll();
    }
  }

  public PermissionDAO getPermissionDAO() {
    return permissionDAO;
  }

  public Cache getUserPermissionCache() {
    return userPermissionCache;
  }

  public void setPermissionDAO(PermissionDAO permissionDAO) {
    this.permissionDAO = permissionDAO;
  }

  public void setUserPermissionCache(Cache userPermissionCache) {
    this.userPermissionCache = userPermissionCache;
  }
}
