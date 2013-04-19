package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.UserOnline;
import java.util.List;
import com.laoer.bbscs.exception.BbscsException;

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
public interface UserOnlineService {

  /**
   *
   * @param userOnline UserOnline
   * @return UserOnline
   * @throws BbscsException
   */
  public UserOnline saveUserOnline(UserOnline userOnline) throws BbscsException;

  /**
   *
   * @param userOnline UserOnline
   * @return UserOnline
   * @throws BbscsException
   */
  public UserOnline createUserOnline(UserOnline userOnline) throws BbscsException;

  /**
   *
   * @param id String
   * @return UserOnline
   */
  public UserOnline findUserOnlineByID(String id);

  /**
   *
   * @param userID String
   * @return UserOnline
   */
  public UserOnline findUserOnlineByUserID(String userID);

  /**
   *
   * @param userName String
   * @return UserOnline
   */
  public UserOnline findUserOnlineByUserName(String userName);

  /**
   *
   * @param atime long
   * @return List
   */
  public List findUserOnlinesAllInTime(long atime);

  /**
   *
   * @param boradID long
   * @param atime long
   * @return List
   */
  public List findUserOnlinesByBoardIDInTime(long boradID, long atime);

  /**
   *
   * @param groupID int
   * @param atime long
   * @return List
   */
  public List findUserOnlineByGroupIDInTime(int groupID, long atime);

  /**
   *
   * @param atime long
   * @return int
   */
  public long getUserOnlineNum(long atime);

  /**
   *
   * @param atime long
   * @param boradID long
   * @param hiddenUser int
   * @param groups List
   * @return List
   */
  public List findUserOnlines(long atime, long boradID, int hiddenUser, List groups);

  /**
   *
   * @param atime long
   * @param boradID long
   * @param hiddenUser int
   * @param groups List
   * @return int
   */
  public long getUserOnlineNum(long atime, long boradID, int hiddenUser, List groups);

  /**
   *
   * @param atime long
   * @param ids List
   * @return int
   */
  public long getUserOnlineNumInIds(long atime, List ids);

  /**
   *
   * @param atime long
   * @param ids List
   * @return List
   */
  public List findUserOnlinesInIds(long atime, List ids);

  /**
   *
   * @param atime long
   * @param ids List
   * @param boradID long
   * @param hiddenUser int
   * @param groups List
   * @return int
   */
  public long getUserOnlineNumInIds(long atime, List ids, long boradID, int hiddenUser, List groups);

  /**
   *
   * @param atime long
   * @param ids List
   * @param boradID long
   * @param hiddenUser int
   * @param groups List
   * @return List
   */
  public List findUserOnlinesInIds(long atime, List ids, long boradID, int hiddenUser, List groups);

  /**
   *
   * @param userOnline UserOnline
   * @throws BbscsException
   */
  public void removeUserOnline(UserOnline userOnline) throws BbscsException;

  /**
   *
   * @param atime long
   * @throws BbscsException
   */
  public void removeUserOnlineOutTime(long atime) throws BbscsException;

}
