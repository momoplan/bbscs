package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.Permission;
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
public interface PermissionService {

  /**
   * 保存Permission对象
   * @param permission Permission
   * @return Permission
   * @throws BbscsException
   */
  public Permission savePermission(Permission permission) throws BbscsException;

  /**
   * 更新Permission对象
   * @param permission Permission
   * @return Permission
   * @throws BbscsException
   */
  public Permission updatePermission(Permission permission) throws BbscsException;

  /**
   * 根据ID取得Permission对象
   * @param id int
   * @return Permission
   */
  public Permission findPermissionByID(long id);

  /**
   * 取得所有Permission列表
   * @return List
   */
  public List findPermissionsAll();

  /**
   * 按照typeID取得Permission列表
   * @param typeID int
   * @return List
   */
  public List findPermissionsByTypeID(int typeID);

  /**
   * 根据指定多个ID取得Permission列表
   * @param ids List
   * @return List
   */
  public List findPermissionnIDs(List ids);

}
