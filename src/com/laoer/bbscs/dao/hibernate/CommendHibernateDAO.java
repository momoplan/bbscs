package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.CommendDAO;
import com.laoer.bbscs.bean.Commend;
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
public class CommendHibernateDAO
    extends HibernateDaoSupport implements CommendDAO {

  private static final String LOAD_BY_POSTID = "from Commend where postID = ?";

  private static final String GET_NUM_BY_COMMENDBOARDID =
      "select count(id) from Commend where commendBoardID = ?";

  private static final String LOADS_BY_COMMENDBOARDID =
      "from Commend where commendBoardID = ? order by createTime desc";

  private static final String GET_NUM_BY_COMMENDTOP =
      "select count(id) from Commend where commendTop = ?";

  private static final String LOADS_BY_COMMENDTOP =
      "from Commend where commendTop = ? order by createTime desc";

  private static final String GET_NUM_BY_BOARDCATEGORY =
      "select count(id) from Commend where boardCategory = ?";

  private static final String LOADS_BY_BOARDCATEGORY =
      "from Commend where boardCategory = ? order by createTime desc";

  private static final String GET_NUM_BY_TOPCATEGORY =
      "select count(id) from Commend where topCategory = ?";

  private static final String LOADS_BY_TOPCATEGORY =
      "from Commend where topCategory = ? order by createTime desc";

  private static final String REMOVE_BY_POSTID = "delete from Commend where postID = ?";

  private static final String LOADS_IN_IDS = "from Commend where id in (:ids)";

  public CommendHibernateDAO() {
    super();
  }

  /**
   *
   * @param commend Commend
   * @return Commend
   * @todo Implement this com.laoer.bbscs.dao.CommendDAO method
   */
  public Commend saveCommend(Commend commend) {
    this.getHibernateTemplate().saveOrUpdate(commend);
    return commend;
  }

  /**
   *
   * @param id String
   * @return Commend
   * @todo Implement this com.laoer.bbscs.dao.CommendDAO method
   */
  public Commend findCommendByID(String id) {
    return (Commend)this.getHibernateTemplate().get(Commend.class, id);
  }

  /**
   *
   * @param postID String
   * @return Commend
   * @todo Implement this com.laoer.bbscs.dao.CommendDAO method
   */
  public Commend findCommendByPostID(String postID) {
    List l = this.getHibernateTemplate().find(LOAD_BY_POSTID, postID);
    if (l == null || l.isEmpty()) {
      return null;
    }
    else {
      return (Commend) l.get(0);
    }
  }

  /**
   *
   * @param commendBoardID long
   * @return int
   * @todo Implement this com.laoer.bbscs.dao.CommendDAO method
   */
  public int getCommendNumByCommendBoardID(long commendBoardID) {
    List l = this.getHibernateTemplate().find(GET_NUM_BY_COMMENDBOARDID, new Long(commendBoardID));
    if (l == null || l.isEmpty()) {
      return 0;
    }
    else {
      return ( (Integer) l.get(0)).intValue();
    }
  }

  /**
   *
   * @param commendBoardID long
   * @param firstResult int
   * @param maxResults int
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.CommendDAO method
   */
  public List findCommendsByCommendBoardID(final long commendBoardID, final int firstResult,
                                           final int maxResults) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(LOADS_BY_COMMENDBOARDID);
        query.setLong(0, commendBoardID);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);

        List list = query.list();
        return list;
      }
    });
  }

  /**
   *
   * @param commendTop int
   * @return int
   * @todo Implement this com.laoer.bbscs.dao.CommendDAO method
   */
  public int getCommendNumByCommendTop(int commendTop) {
    List l = this.getHibernateTemplate().find(GET_NUM_BY_COMMENDTOP, new Long(commendTop));
    if (l == null || l.isEmpty()) {
      return 0;
    }
    else {
      return ( (Integer) l.get(0)).intValue();
    }
  }

  /**
   *
   * @param commendTop int
   * @param firstResult int
   * @param maxResults int
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.CommendDAO method
   */
  public List findCommendsByCommendTop(final int commendTop, final int firstResult, final int maxResults) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(LOADS_BY_COMMENDTOP);
        query.setInteger(0, commendTop);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);

        List list = query.list();
        return list;
      }
    });
  }

  /**
   *
   * @param boardCategory String
   * @return int
   * @todo Implement this com.laoer.bbscs.dao.CommendDAO method
   */
  public int getCommendNumByBoardCategory(String boardCategory) {
    List l = this.getHibernateTemplate().find(GET_NUM_BY_BOARDCATEGORY, boardCategory);
    if (l == null || l.isEmpty()) {
      return 0;
    }
    else {
      return ( (Integer) l.get(0)).intValue();
    }
  }

  /**
   *
   * @param boardCategory String
   * @param firstResult int
   * @param maxResults int
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.CommendDAO method
   */
  public List findCommendsByBoardCategory(final String boardCategory, final int firstResult,
                                          final int maxResults) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(LOADS_BY_BOARDCATEGORY);
        query.setString(0, boardCategory);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);

        List list = query.list();
        return list;
      }
    });
  }

  /**
   *
   * @param topCategory String
   * @return int
   * @todo Implement this com.laoer.bbscs.dao.CommendDAO method
   */
  public int getCommendNumByTopCategory(String topCategory) {
    List l = this.getHibernateTemplate().find(GET_NUM_BY_TOPCATEGORY, topCategory);
    if (l == null || l.isEmpty()) {
      return 0;
    }
    else {
      return ( (Integer) l.get(0)).intValue();
    }

  }

  /**
   *
   * @param topCategory String
   * @param firstResult int
   * @param maxResults int
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.CommendDAO method
   */
  public List findCommendsByTopCategory(final String topCategory, final int firstResult, final int maxResults) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(LOADS_BY_TOPCATEGORY);
        query.setString(0, topCategory);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);

        List list = query.list();
        return list;
      }
    });
  }

  public List findCommendsInIds(final List ids) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(LOADS_IN_IDS);
        query.setParameterList("ids", ids);
        List list = query.list();
        return list;
      }
    });
  }

  /**
   *
   * @param commend Commend
   */
  public void removeCommend(Commend commend) {
    this.getHibernateTemplate().delete(commend);
  }

  public void removeCommend(final String postID) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_BY_POSTID);
        query.setString(0, postID);
        query.executeUpdate();
        return null;
      }
    });
  }
}
