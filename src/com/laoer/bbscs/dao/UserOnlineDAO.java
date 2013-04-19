package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.UserOnline;
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
public interface UserOnlineDAO {

  /**
   * 保存或更新UserOnline对象
   * @param userOnline UserOnline
   * @return UserOnline
   */
  public UserOnline saveUserOnline(UserOnline userOnline);

  /**
   * 根据ID取得UserOnline对象
   * @param id String
   * @return UserOnline
   */
  public UserOnline findUserOnlineByID(String id);

  /**
   * 根据UserID取得UserOnline对象
   * @param userID String
   * @return UserOnline
   */
  public UserOnline findUserOnlineByUserID(String userID);

  /**
   * 根据userName取得UserOnline对象
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
   * 删除UserOnline对象
   * @param userOnline UserOnline
   */
  public void removeUserOnline(UserOnline userOnline);

  /**
   *
   * @param atime long
   */
  public void removeUserOnlineOutTime(long atime);
}
