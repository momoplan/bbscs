package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.UserLevel;
import com.laoer.bbscs.exception.BbscsException;
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
public interface UserLevelService {

  /**
   *
   * @param ul UserLevel
   * @return UserLevel
   * @throws BbscsException
   */
  public UserLevel saveUserLevel(UserLevel ul) throws BbscsException;

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
   * @param type int
   * @param value int
   * @return UserLevel
   */
  public UserLevel getUserLevelByUserValue(int type, int value);

  /**
   *
   * @param ul UserLevel
   * @throws BbscsException
   */
  public void removeUserLevel(UserLevel ul) throws BbscsException;

}
