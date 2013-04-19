package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.EliteDAO;
import com.laoer.bbscs.bean.Elite;
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
public class EliteHibernateDAO
    extends HibernateDaoSupport implements EliteDAO {

  private static final String LOADS_BY_PID_BID = "from Elite where parentID = ? and boardID = ? order by orders";

  private static final String LOADS_IN_IDS = "from Elite where id in (:ids)";

  public EliteHibernateDAO() {
    super();
  }

  /**
   *
   * @param elite Elite
   * @return Elite
   * @todo Implement this com.laoer.bbscs.dao.EliteDAO method
   */
  public Elite saveElite(Elite elite) {
    this.getHibernateTemplate().saveOrUpdate(elite);
    return elite;
  }

  /**
   *
   * @param id long
   * @return Elite
   * @todo Implement this com.laoer.bbscs.dao.EliteDAO method
   */
  public Elite findEliteByID(long id) {
    return (Elite)this.getHibernateTemplate().get(Elite.class, new Long(id));
  }

  /**
   *
   * @param pid long
   * @param bid long
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.EliteDAO method
   */
  public List findElitesByPidBid(long pid, long bid) {
    Object[] o = {new Long(pid), new Long(bid)};
    return this.getHibernateTemplate().find(LOADS_BY_PID_BID, o);
  }

  /**
   *
   * @param elite Elite
   * @todo Implement this com.laoer.bbscs.dao.EliteDAO method
   */
  public void removeElite(Elite elite) {
    this.getHibernateTemplate().delete(elite);
  }

  /**
   *
   * @param ids List
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.EliteDAO method
   */
  public List findElitesInIds(final List ids) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(LOADS_IN_IDS);
        query.setParameterList("ids", ids);
        return query.list();
      }
    });
  }

}
