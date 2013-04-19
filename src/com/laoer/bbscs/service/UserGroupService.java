package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.UserGroup;
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
public interface UserGroupService {

  /**
   * 保存UserGroup对象
   * @param ug UserGroup
   * @return UserGroup
   * @throws BbscsException
   */
  public UserGroup saveUserGroup(UserGroup ug) throws BbscsException;

  /**
   * 更新UserGroup对象
   * @param ug UserGroup
   * @return UserGroup
   * @throws BbscsException
   */
  public UserGroup updateUserGroup(UserGroup ug) throws BbscsException;

  /**
   * 根据ID取得UserGroup对象
   * @param id int
   * @return UserGroup
   */
  public UserGroup findUserGroupByID(int id);

  /**
   * 取得所有UserGroup列表
   * @return List
   */
  public List findUserGroupsAll();

  /**
   * 取得指定ID的UserGroup列表
   * @param ids List
   * @return List
   */
  public List findUserGroupInIDs(List ids);

  /**
   * 删除UserGroup对象
   * @param ug UserGroup
   * @throws BbscsException
   */
  public void removeUserGroup(UserGroup ug) throws BbscsException;

}
