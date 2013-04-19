package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.web.*;
import com.laoer.bbscs.web.servlet.UserSession;

import java.util.*;
import java.io.*;
import com.laoer.bbscs.bean.UserInfoSimple;

/**
 * <p>Title: 天乙社区</p>
 *
 * <p>Description: BBSCS</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Laoer.com</p>
 *
 * @author Gong Tianyi
 * @version 8.0
 */
public interface UserService {

  /**
   *保存或更新UserInfo对象
   * @param userInfo UserInfo
   * @return UserInfo
   * @throws BbscsException
   */
  public UserInfo saveUserInfo(UserInfo userInfo) throws BbscsException;

  /**
   * 根据主键查找UserInfo对象
   * @param id String
   * @return UserInfo
   */
  public UserInfo findUserInfoById(String id);
  
  /**
   * 根据userNo查找UserInfo对象
   * @param userno String
   * @return UserInfo
   */
  public UserInfo findUserInfoByUserNo(String userno);

  /**
   * 根据用户名查找UserInfo对象
   * @param userName String
   * @return UserInfo
   */
  public UserInfo findUserInfoByUserName(String userName);

  /**
   * 根据Email查找UserInfo对象
   * @param email String
   * @return UserInfo
   */
  public UserInfo findUserInfoByEmail(String email);

  /**
   * 取得所有用户数量
   * @return int
   */
  public long getAllUserNum();

  /**
   * 取得用户分页列表
   * @param orderby String
   * @param ascordesc String
   * @param pages Pages
   * @return PageList
   */
  public PageList findUserInfoList(String orderby, String ascordesc, Pages pages);

  /**
   * 取得用户权限
   * @param userInfo UserInfo
   * @return List
   */
  public Map[] getUserPermission(UserInfo userInfo);

  /**
   * 根据组ID取得权限
   * @param groupID int
   * @return Map[]
   */
  public Map[] getUserPermission(int groupID);

  /**
   * 用户登录过程
   * @param userInfo UserInfo
   * @return UserInfo
   * @throws BbscsException
   */
  public UserInfo saveAtLogin(UserInfo userInfo) throws BbscsException;

  /**
   * 写用户文件
   * @param userInfo UserInfo
   */
  public void writeUserFile(UserInfo userInfo);

  /**
   * 从用户文件取得UserInfoSimple对象
   * @param userID String
   * @return UserInfoSimple
   */
  public UserInfoSimple getUserInfoSimple(String userID);

  /**
   * 保存用户上传头像
   * @param userID String
   * @param stream InputStream
   * @throws BbscsException
   */
  public void createUserFacePic(UserInfo ui, String distFileName, InputStream stream) throws
      BbscsException;

  /**
   * 删除用户照片
   * @param ui UserInfo
   * @throws BbscsException
   */
  public void removeUserFacePic(UserInfo ui) throws BbscsException;

  /**
   * 取得用户头衔
   * @param ui UserInfo
   * @return Object[]
   */
  public int getUserTitleValue(UserInfo ui);

  /**
   * 取得用户头衔
   * @param uis UserInfoSimple
   * @return int
   */
  public int getUserTitleValue(UserInfoSimple uis);

  /**
   *
   * @param ui UserInfo
   * @return String
   */
  public String getUserTitle(UserInfo ui);

  /**
   *
   * @param uis UserInfoSimple
   * @return String
   */
  public String getUserTitle(UserInfoSimple uis);

  /**
   *
   * @throws BbscsException
   */
  public void createTopFile() throws BbscsException;

  /**
   *
   * @param groupID int
   * @return int
   */
  public long getUserNumByGroupID(int groupID);

  /**
   *
   * @param groupID int
   * @param orderby String
   * @param ascOrDesc int
   * @param pages Pages
   * @return PageList
   */
  public PageList findUserInfosByGroupID(int groupID, String orderby, int ascOrDesc, Pages pages);

  /**
   *
   * @param groupID int
   * @param pages Pages
   * @return PageList
   */
  public PageList findUserInfosByGroupID(int groupID, Pages pages);

  public UserSession getUserSession(String userName);

  public UserSession getUserSession(UserInfo ui);
  
  public List getUserinfo();
  
}
