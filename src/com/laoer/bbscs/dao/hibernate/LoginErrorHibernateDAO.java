package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.LoginErrorDAO;
import com.laoer.bbscs.bean.LoginError;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.sql.SQLException;

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
public class LoginErrorHibernateDAO
    extends HibernateDaoSupport implements LoginErrorDAO {

  private static final String LOAD_BY_USERID = "from LoginError where userID = ?";

  private static final String LOADS_OUT_TIME = "from LoginError where loginTime < ?";

  private static final String REMOVE_OUT_TIME = "delete from LoginError where loginTime < ?";

  public LoginErrorHibernateDAO() {
    super();
  }

  /**
   * 保存或更新LoginError对象
   *
   * @param loginError LoginError
   * @return LoginError
   * @todo Implement this com.laoer.bbscs.dao.LoginErrorDAO method
   */
  public LoginError saveLoginError(LoginError loginError) {
    this.getHibernateTemplate().saveOrUpdate(loginError);
    return loginError;
  }

  /**
   * 根据ID取得LoginError对象
   *
   * @param id String
   * @return LoginError
   * @todo Implement this com.laoer.bbscs.dao.LoginErrorDAO method
   */
  public LoginError findLoginErrorByID(String id) {
    return (LoginError)this.getHibernateTemplate().get(LoginError.class, id);
  }

  /**
   * 根据UserID取得LoginError对象
   *
   * @param userID String
   * @return LoginError
   * @todo Implement this com.laoer.bbscs.dao.LoginErrorDAO method
   */
  public LoginError findLoginErrorByUserID(String userID) {
    List l = this.getHibernateTemplate().find(LOAD_BY_USERID, userID);
    if (l == null || l.isEmpty()) {
      return null;
    }
    else {
      return (LoginError) l.get(0);
    }
  }

  /**
   * 取得超过指定时间的LoginError列表
   *
   * @param atime long
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.LoginErrorDAO method
   */
  public List findLoginErrorsOutTime(long atime) {
    return this.getHibernateTemplate().find(LOADS_OUT_TIME, new Long(atime));
  }

  /**
   * 删除超过指定时间的LoginError对象
   *
   * @param atime long
   * @todo Implement this com.laoer.bbscs.dao.LoginErrorDAO method
   */
  public void removeLoginErrorsOutTime(final long atime) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_OUT_TIME);
        query.setLong(0, atime);
        query.executeUpdate();
        return null;
      }
    });
  }

  /**
   * 删除LoginError对象
   *
   * @param loginError LoginError
   * @todo Implement this com.laoer.bbscs.dao.LoginErrorDAO method
   */
  public void removeLoginError(LoginError loginError) {
    this.getHibernateTemplate().delete(loginError);
  }
}
