package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.LoginError;
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
public interface LoginErrorService {

  /**
   * 保存或更新LoginError对象
   * @param loginError LoginError
   * @return LoginError
   * @throws BbscsException
   */
  public LoginError saveLoginError(LoginError loginError) throws BbscsException;

  /**
   * 根据ID取得LoginError对象
   * @param id String
   * @return LoginError
   */
  public LoginError findLoginErrorByID(String id);

  /**
   * 根据UserID取得LoginError对象
   * @param userID String
   * @return LoginError
   */
  public LoginError findLoginErrorByUserID(String userID);

  /**
   * 取得超过指定时间的LoginError列表
   * @param atime long
   * @return List
   */
  public List findLoginErrorsOutTime(long atime);

  /**
   * 删除超过指定时间的LoginError对象
   * @param atime long
   * @throws BbscsException
   */
  public void removeLoginErrorsOutTime(long atime) throws BbscsException;

  /**
   *
   * @param loginError LoginError
   * @throws BbscsException
   */
  public void removeLoginError(LoginError loginError) throws BbscsException;

  /**
   * 创建或取得一个已有的LoginError对象
   * @param userID String
   * @return LoginError
   * @throws BbscsException
   */
  public LoginError createLoginError(String userID) throws BbscsException;

  /**
   * 15分钟之内登录错误次数超过5次，不能登录
   * @param userID String
   * @return boolean
   */
  public boolean isCanNotLogin(String userID);

}
