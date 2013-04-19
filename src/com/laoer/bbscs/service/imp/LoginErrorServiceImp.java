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
public class LoginErrorServiceImp
    implements LoginErrorService {

  private static final Log logger = LogFactory.getLog(LoginErrorServiceImp.class);

  private LoginErrorDAO loginErrorDAO;

  public LoginErrorServiceImp() {
  }

  /**
   * 保存或更新LoginError对象
   *
   * @param loginError LoginError
   * @return LoginError
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.LoginErrorService method
   */
  public LoginError saveLoginError(LoginError loginError) throws BbscsException {
    try {
      return this.getLoginErrorDAO().saveLoginError(loginError);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 根据ID取得LoginError对象
   *
   * @param id String
   * @return LoginError
   * @todo Implement this com.laoer.bbscs.service.LoginErrorService method
   */
  public LoginError findLoginErrorByID(String id) {
    return this.getLoginErrorDAO().findLoginErrorByID(id);
  }

  /**
   * 根据UserID取得LoginError对象
   *
   * @param userID String
   * @return LoginError
   * @todo Implement this com.laoer.bbscs.service.LoginErrorService method
   */
  public LoginError findLoginErrorByUserID(String userID) {
    return this.getLoginErrorDAO().findLoginErrorByUserID(userID);
  }

  /**
   * 取得超过指定时间的LoginError列表
   *
   * @param atime long
   * @return List
   * @todo Implement this com.laoer.bbscs.service.LoginErrorService method
   */
  public List findLoginErrorsOutTime(long atime) {
    return this.getLoginErrorDAO().findLoginErrorsOutTime(atime);
  }

  /**
   * 删除超过指定时间的LoginError对象
   *
   * @param atime long
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.LoginErrorService method
   */
  public void removeLoginErrorsOutTime(long atime) throws BbscsException {
    try {
      this.getLoginErrorDAO().removeLoginErrorsOutTime(atime);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param loginError LoginError
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.LoginErrorService method
   */
  public void removeLoginError(LoginError loginError) throws BbscsException {
    try {
      this.getLoginErrorDAO().removeLoginError(loginError);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 创建或取得一个已有的LoginError对象
   *
   * @param userID String
   * @return LoginError
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.LoginErrorService method
   */
  public LoginError createLoginError(String userID) throws BbscsException {
    LoginError loginError = this.getLoginErrorDAO().findLoginErrorByUserID(userID);
    if (loginError == null) {
      loginError = new LoginError();
      loginError.setErrorTimes(1);
      loginError.setLoginTime(System.currentTimeMillis());
      loginError.setUserID(userID);
    }
    else {
      loginError.setErrorTimes(loginError.getErrorTimes() + 1);
      loginError.setLoginTime(System.currentTimeMillis());
    }
    try {
      return this.getLoginErrorDAO().saveLoginError(loginError);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 15分钟之内登录错误次数超过5次，不能登录
   *
   * @param userID String
   * @return boolean
   * @todo Implement this com.laoer.bbscs.service.LoginErrorService method
   */
  public boolean isCanNotLogin(String userID) {
    LoginError loginError = this.getLoginErrorDAO().findLoginErrorByUserID(userID);
    if (loginError != null) {
      if ( (System.currentTimeMillis() - loginError.getLoginTime()) <= 15 * 60000) {
        if (loginError.getErrorTimes() >= 5) {
          return true;
        }
      }
    }
    return false;
  }

  public LoginErrorDAO getLoginErrorDAO() {
    return loginErrorDAO;
  }

  public void setLoginErrorDAO(LoginErrorDAO loginErrorDAO) {
    this.loginErrorDAO = loginErrorDAO;
  }
}
