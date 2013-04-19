package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.Role;
import java.util.*;

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
public interface RoleDAO {

  /**
   * 保存或更新Role对象
   * @param role Role
   * @return Role
   */
  public Role saveRole(Role role);

  /**
   * 根据ID取得Role
   * @param id int
   * @return Role
   */
  public Role findRoleByID(int id);

  /**
   * 取得所有Role列表
   * @return List
   */
  public List findRolesAll();

  /**
   * 根据tpyeID取得Role列表
   * @param typeID int
   * @return List
   */
  public List findRolesByTypeID(int typeID);

  /**
   * 根据指定ID取得Role对象列表
   * @param ids List
   * @return List
   */
  public List findRolesInIDs(List ids);

  /**
   * 删除Role对象
   * @param role Role
   */
  public void removeRole(Role role);

}
