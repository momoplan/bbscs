package com.laoer.bbscs.service.imp;

import java.util.*;

import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

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
public class UserOnlineServiceImp
    implements UserOnlineService {

  private static final Log logger = LogFactory.getLog(UserOnlineServiceImp.class);

  private UserOnlineDAO userOnlineDAO;

  public UserOnlineServiceImp() {
  }

  /**
   *
   * @param userOnline UserOnline
   * @return UserOnline
   * @throws BbscsException
   */
  public UserOnline saveUserOnline(UserOnline userOnline) throws BbscsException {
    try {
      return this.getUserOnlineDAO().saveUserOnline(userOnline);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param userOnline UserOnline
   * @return UserOnline
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.UserOnlineService method
   */
  public UserOnline createUserOnline(UserOnline userOnline) throws BbscsException {
    try {
      UserOnline uo = this.getUserOnlineDAO().findUserOnlineByUserID(userOnline.getUserID());
      if (uo == null) {
        return this.getUserOnlineDAO().saveUserOnline(userOnline);
      }
      else {
        uo.setAtPlace(userOnline.getAtPlace());
        uo.setBoardID(userOnline.getBoardID());
        uo.setNickName(userOnline.getNickName());
        uo.setOnlineTime(userOnline.getOnlineTime());
        uo.setUserGroupID(userOnline.getUserGroupID());
        uo.setValidateCode(userOnline.getValidateCode());
        uo.setHiddenUser(userOnline.getHiddenUser());
        return this.getUserOnlineDAO().saveUserOnline(uo);
      }
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param id String
   * @return UserOnline
   * @todo Implement this com.laoer.bbscs.service.UserOnlineService method
   */
  public UserOnline findUserOnlineByID(String id) {
    return this.getUserOnlineDAO().findUserOnlineByID(id);
  }

  /**
   *
   * @param userID String
   * @return UserOnline
   * @todo Implement this com.laoer.bbscs.service.UserOnlineService method
   */
  public UserOnline findUserOnlineByUserID(String userID) {
    return this.getUserOnlineDAO().findUserOnlineByUserID(userID);
  }

  /**
   *
   * @param userName String
   * @return UserOnline
   * @todo Implement this com.laoer.bbscs.service.UserOnlineService method
   */
  public UserOnline findUserOnlineByUserName(String userName) {
    return this.getUserOnlineDAO().findUserOnlineByUserName(userName);
  }

  /**
   *
   * @param atime long
   * @return List
   * @todo Implement this com.laoer.bbscs.service.UserOnlineService method
   */
  public List findUserOnlinesAllInTime(long atime) {
    return this.getUserOnlineDAO().findUserOnlinesAllInTime(atime);
  }

  /**
   *
   * @param boradID long
   * @param atime long
   * @return List
   * @todo Implement this com.laoer.bbscs.service.UserOnlineService method
   */
  public List findUserOnlinesByBoardIDInTime(long boradID, long atime) {
    return this.getUserOnlineDAO().findUserOnlinesByBoardIDInTime(boradID, atime);
  }

  /**
   *
   * @param groupID int
   * @param atime long
   * @return List
   * @todo Implement this com.laoer.bbscs.service.UserOnlineService method
   */
  public List findUserOnlineByGroupIDInTime(int groupID, long atime) {
    return this.getUserOnlineDAO().findUserOnlineByGroupIDInTime(groupID, atime);
  }

  /**
   *
   * @param atime long
   * @return int
   */
  public long getUserOnlineNum(long atime) {
    return this.getUserOnlineDAO().getUserOnlineNum(atime);
  }

  /**
   *
   * @param atime long
   * @param boradID long
   * @param hiddenUser int
   * @param groups List
   * @return List
   */
  public List findUserOnlines(long atime, long boradID, int hiddenUser, List groups) {
    return this.getUserOnlineDAO().findUserOnlines(atime, boradID, hiddenUser, groups);
  }

  /**
   *
   * @param atime long
   * @param boradID long
   * @param hiddenUser int
   * @param groups List
   * @return int
   */
  public long getUserOnlineNum(long atime, long boradID, int hiddenUser, List groups) {
    return this.getUserOnlineDAO().getUserOnlineNum(atime, boradID, hiddenUser, groups);
  }

  /**
   *
   * @param atime long
   * @param ids List
   * @return int
   */
  public long getUserOnlineNumInIds(long atime, List ids) {
    return this.getUserOnlineDAO().getUserOnlineNumInIds(atime, ids);
  }

  /**
   *
   * @param atime long
   * @param ids List
   * @return List
   */
  public List findUserOnlinesInIds(long atime, List ids) {
    return this.getUserOnlineDAO().findUserOnlinesInIds(atime, ids);
  }

  /**
   *
   * @param atime long
   * @param ids List
   * @param boradID long
   * @param hiddenUser int
   * @param groups List
   * @return int
   */
  public long getUserOnlineNumInIds(long atime, List ids, long boradID, int hiddenUser, List groups) {
    return this.getUserOnlineDAO().getUserOnlineNumInIds(atime, ids, boradID, hiddenUser, groups);
  }

  /**
   *
   * @param atime long
   * @param ids List
   * @param boradID long
   * @param hiddenUser int
   * @param groups List
   * @return List
   */
  public List findUserOnlinesInIds(long atime, List ids, long boradID, int hiddenUser, List groups) {
    return this.getUserOnlineDAO().findUserOnlinesInIds(atime, ids, boradID, hiddenUser, groups);
  }

  /**
   *
   * @param userOnline UserOnline
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.UserOnlineService method
   */
  public void removeUserOnline(UserOnline userOnline) throws BbscsException {
    try {
      this.getUserOnlineDAO().removeUserOnline(userOnline);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param atime long
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.UserOnlineService method
   */
  public void removeUserOnlineOutTime(long atime) throws BbscsException {
    try {
      this.getUserOnlineDAO().removeUserOnlineOutTime(atime);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  public UserOnlineDAO getUserOnlineDAO() {
    return userOnlineDAO;
  }

  public void setUserOnlineDAO(UserOnlineDAO userOnlineDAO) {
    this.userOnlineDAO = userOnlineDAO;
  }
}
