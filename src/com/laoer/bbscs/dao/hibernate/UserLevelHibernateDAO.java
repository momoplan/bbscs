package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.UserLevelDAO;
import com.laoer.bbscs.bean.UserLevel;
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
public class UserLevelHibernateDAO
    extends HibernateDaoSupport implements UserLevelDAO {

  private static final String LOADS_BY_TYPE = "from UserLevel where levelType = ? order by levelValue";

  public UserLevelHibernateDAO() {
    super();
  }

  /**
   *
   * @param ul UserLevel
   * @return UserLevel
   * @todo Implement this com.laoer.bbscs.dao.UserLevelDAO method
   */
  public UserLevel saveUserLevel(UserLevel ul) {
    this.getHibernateTemplate().saveOrUpdate(ul);
    return ul;
  }

  /**
   *
   * @param id String
   * @return UserLevel
   * @todo Implement this com.laoer.bbscs.dao.UserLevelDAO method
   */
  public UserLevel findUserLevelById(String id) {
    return (UserLevel)this.getHibernateTemplate().get(UserLevel.class, id);
  }

  /**
   *
   * @param type int
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.UserLevelDAO method
   */
  public List findUserLevelsByType(int type) {
    return this.getHibernateTemplate().find(LOADS_BY_TYPE, new Integer(type));
  }

  public void removeUserLevel(UserLevel ul) {
    this.getHibernateTemplate().delete(ul);
  }
}
