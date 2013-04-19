package com.laoer.bbscs.dao;

import java.util.*;
import com.laoer.bbscs.bean.Permission;

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
public interface PermissionDAO {

  /**
   * 保存Permission对象
   * @param permission Permission
   * @return Permission
   */
  public Permission savePermission(Permission permission);

  /**
   * 更新Permission对象
   * @param permission Permission
   * @return Permission
   */
  public Permission updatePermission(Permission permission);

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
