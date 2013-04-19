package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.LoginError;
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
public interface LoginErrorDAO {

  /**
   * 保存或更新LoginError对象
   * @param loginError LoginError
   * @return LoginError
   */
  public LoginError saveLoginError(LoginError loginError);

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
   */
  public void removeLoginErrorsOutTime(long atime);

  /**
   * 删除LoginError对象
   * @param loginError LoginError
   */
  public void removeLoginError(LoginError loginError);

}
