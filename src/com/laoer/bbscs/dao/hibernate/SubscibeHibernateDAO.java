package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.SubscibeDAO;
import com.laoer.bbscs.bean.Subscibe;
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
public class SubscibeHibernateDAO
    extends HibernateDaoSupport implements SubscibeDAO {

  private static final String LOAD_BY_ID = "from Subscibe where id = ? and userID = ?";

  private static final String LOAD_BY_POSTID = "from Subscibe where postID = ? and userID = ?";

  private static final String LOADS_SEND = "from Subscibe where postID = ?";

  private static final String GET_NUM_BY_USER =
      "select count(*) from Subscibe where userID = ? and boardID = ?";

  private static final String LOADS_BY_USER = "from Subscibe where userID = ? and boardID = ? order by createTime desc";

  private static final String REMOVE_BY_ID = "delete from Subscibe where id = ? and userID = ?";

  public SubscibeHibernateDAO() {
  }

  /**
   * 保存或更新Subscibe对象
   *
   * @param subscibe Subscibe
   * @return Subscibe
   * @todo Implement this com.laoer.bbscs.dao.SubscibeDAO method
   */
  public Subscibe saveSubscibe(Subscibe subscibe) {
    this.getHibernateTemplate().saveOrUpdate(subscibe);
    return subscibe;
  }

  /**
   *
   * @param id String
   * @param userID String
   * @param bid long
   * @return Subscibe
   * @todo Implement this com.laoer.bbscs.dao.SubscibeDAO method
   */
  public Subscibe findSubscibeByID(String id, String userID, long bid) {
    Object[] o = {id, userID};
    List l = this.getHibernateTemplate().find(LOAD_BY_ID, o);
    if (l == null || l.isEmpty()) {
      return null;
    }
    else {
      return (Subscibe) l.get(0);
    }
  }

  /**
   *
   * @param postID String
   * @param userID String
   * @param bid long
   * @return Subscibe
   * @todo Implement this com.laoer.bbscs.dao.SubscibeDAO method
   */
  public Subscibe findSubscibeByPostID(String postID, String userID, long bid) {
    Object[] o = {postID, userID};
    List l = this.getHibernateTemplate().find(LOAD_BY_POSTID, o);
    if (l == null || l.isEmpty()) {
      return null;
    }
    else {
      return (Subscibe) l.get(0);
    }
  }

  /**
   *
   * @param postID String
   * @param bid long
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.SubscibeDAO method
   */
  public List findSubscibesSend(String postID, long bid) {
    return this.getHibernateTemplate().find(LOADS_SEND, postID);
  }

  /**
   *
   * @param userID String
   * @param bid long
   * @return int
   * @todo Implement this com.laoer.bbscs.dao.SubscibeDAO method
   */
  public long getSubscibeNumByUserID(String userID, long bid) {
    Object[] o = {userID, new Long(bid)};
    List l = this.getHibernateTemplate().find(GET_NUM_BY_USER, o);
    if (l == null || l.isEmpty()) {
      return 0;
    }
    else {
      return ( (Long) l.get(0)).longValue();
    }
  }

  /**
   *
   * @param userID String
   * @param bid long
   * @param firstResult int
   * @param maxResults int
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.SubscibeDAO method
   */
  public List findSubscibesByUserID(final String userID, final long bid, final int firstResult,
                                    final int maxResults) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(LOADS_BY_USER);
        query.setString(0, userID);
        query.setLong(1, bid);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);

        List list = query.list();
        return list;
      }
    });

  }

  /**
   *
   * @param id String
   * @param userID String
   * @param bid long
   * @todo Implement this com.laoer.bbscs.dao.SubscibeDAO method
   */
  public void removeSubscibe(final String id, final String userID, final long bid) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_BY_ID);
        query.setString(0, id);
        query.setString(1, userID);
        query.executeUpdate();
        return null;
      }
    });
  }

  /**
   *
   * @param subscibe Subscibe
   * @todo Implement this com.laoer.bbscs.dao.SubscibeDAO method
   */
  public void removeSubscibe(Subscibe subscibe) {
    this.getHibernateTemplate().delete(subscibe);
  }
}
