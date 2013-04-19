package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.UserTopDAO;
import com.laoer.bbscs.bean.UserTop;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.sql.SQLException;

/**
 * <p>Title: TianyiBBS</p>
 *
 * <p>Description: BBSCS</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Laoer.com</p>
 *
 * @author Laoer
 * @version 7.0
 */
public class UserTopHibernateDAO
    extends HibernateDaoSupport implements UserTopDAO {

  private static final String LOADS_BY_TYPE = "from UserTop where valueType = ? order by valueInc desc";

  private static final String REMOVE_BY_TYPE = "delete from UserTop where valueType = ?";

  public UserTopHibernateDAO() {
    super();
  }

  /**
   *
   * @param ut UserTop
   * @return UserTop
   * @todo Implement this com.laoer.bbscs.dao.UserTopDAO method
   */
  public UserTop saveUserTop(UserTop ut) {
    this.getHibernateTemplate().saveOrUpdate(ut);
    return ut;
  }

  /**
   *
   * @param type int
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.UserTopDAO method
   */
  public List findUserTopsByType(int type) {
    return this.getHibernateTemplate().find(LOADS_BY_TYPE, new Integer(type));
  }

  /**
   *
   * @param type int
   * @param firstResult int
   * @param maxResults int
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.UserTopDAO method
   */
  public List findUserTopsByType(final int type, final int firstResult, final int maxResults) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(LOADS_BY_TYPE);
        query.setInteger(0, type);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);

        List list = query.list();
        return list;
      }
    });

  }

  /**
   *
   * @param type int
   * @todo Implement this com.laoer.bbscs.dao.UserTopDAO method
   */
  public void removeUserTopByType(final int type) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_BY_TYPE);
        query.setInteger(0, type);
        query.executeUpdate();
        return null;
      }
    });
  }
}
