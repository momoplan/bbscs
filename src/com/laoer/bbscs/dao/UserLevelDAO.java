package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.UserLevel;
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
public interface UserLevelDAO {

  /**
   *
   * @param ul UserLevel
   * @return UserLevel
   */
  public UserLevel saveUserLevel(UserLevel ul);

  /**
   *
   * @param id String
   * @return UserLevel
   */
  public UserLevel findUserLevelById(String id);

  /**
   *
   * @param type int
   * @return List
   */
  public List findUserLevelsByType(int type);

  /**
   *
   * @param ul UserLevel
   */
  public void removeUserLevel(UserLevel ul);
}
