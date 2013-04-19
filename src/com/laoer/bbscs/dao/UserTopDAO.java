package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.UserTop;
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
public interface UserTopDAO {

  /**
   *
   * @param ut UserTop
   * @return UserTop
   */
  public UserTop saveUserTop(UserTop ut);

  /**
   *
   * @param type int
   * @return List
   */
  public List findUserTopsByType(int type);

  /**
   *
   * @param type int
   * @param firstResult int
   * @param maxResults int
   * @return List
   */
  public List findUserTopsByType(int type, int firstResult, int maxResults);

  /**
   *
   * @param type int
   */
  public void removeUserTopByType(int type);

}
