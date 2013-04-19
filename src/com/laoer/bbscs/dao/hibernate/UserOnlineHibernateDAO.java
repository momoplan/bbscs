package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.UserOnlineDAO;
import com.laoer.bbscs.bean.UserOnline;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.sql.SQLException;
import org.hibernate.Criteria;
//import com.laoer.bbscs.bean.Board;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

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
public class UserOnlineHibernateDAO
    extends HibernateDaoSupport implements UserOnlineDAO {

  private static final String LOAD_BY_USERID = "from UserOnline where userID = ?";

  private static final String LOAD_BY_USERNAME = "from UserOnline where userName = ?";

  private static final String LOADS_ALL_INTIME = "from UserOnline where onlineTime >= ?";

  private static final String LOADS_BY_BOARDID_INTIME =
      "from UserOnline where boardID = ? and onlineTime >= ?";

  private static final String LOADS_BY_GROUPID_INTIME =
      "from UserOnline where userGroupID = ? and onlineTime >= ?";

  private static final String REMOVE_ALL_OUTTIME = "delete from UserOnline where onlineTime < ?";

  private static final String GET_NUM_INTIME =
      "select count(*) from UserOnline where onlineTime >= ?";

  private static final String LOADS_IN_IDS =
      "from UserOnline where userID in (:ids) and onlineTime >= :atime";

  private static final String GET_NUM_IN_IDS =
      "select count(*) from UserOnline where userID in (:ids) and onlineTime >= :atime";

  public UserOnlineHibernateDAO() {
    super();
  }

  /**
   * 保存或更新UserOnline对象
   *
   * @param userOnline UserOnline
   * @return UserOnline
   * @todo Implement this com.laoer.bbscs.dao.UserOnlineDAO method
   */
  public UserOnline saveUserOnline(UserOnline userOnline) {
    this.getHibernateTemplate().saveOrUpdate(userOnline);
    return userOnline;
  }

  /**
   * 根据ID取得UserOnline对象
   *
   * @param id String
   * @return UserOnline
   * @todo Implement this com.laoer.bbscs.dao.UserOnlineDAO method
   */
  public UserOnline findUserOnlineByID(String id) {
    return (UserOnline)this.getHibernateTemplate().get(UserOnline.class, id);
  }

  /**
   * 根据UserID取得UserOnline对象
   *
   * @param userID String
   * @return UserOnline
   * @todo Implement this com.laoer.bbscs.dao.UserOnlineDAO method
   */
  public UserOnline findUserOnlineByUserID(String userID) {
    List l = this.getHibernateTemplate().find(LOAD_BY_USERID, userID);
    if (l == null || l.isEmpty()) {
      return null;
    }
    else {
      return (UserOnline) l.get(0);
    }
  }

  /**
   * 根据userName取得UserOnline对象
   *
   * @param userName String
   * @return UserOnline
   * @todo Implement this com.laoer.bbscs.dao.UserOnlineDAO method
   */
  public UserOnline findUserOnlineByUserName(String userName) {
    List l = this.getHibernateTemplate().find(LOAD_BY_USERNAME, userName);
    if (l == null || l.isEmpty()) {
      return null;
    }
    else {
      return (UserOnline) l.get(0);
    }
  }

  /**
   *
   * @param atime long
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.UserOnlineDAO method
   */
  public List findUserOnlinesAllInTime(long atime) {
    return this.getHibernateTemplate().find(LOADS_ALL_INTIME, new Long(atime));
  }

  /**
   *
   * @param boradID long
   * @param atime long
   * @return List
   */
  public List findUserOnlinesByBoardIDInTime(long boradID, long atime) {
    Object[] o = {new Long(boradID), new Long(atime)};
    return this.getHibernateTemplate().find(LOADS_BY_BOARDID_INTIME, o);
  }

  /**
   *
   * @param groupID int
   * @param atime long
   * @return List
   */
  public List findUserOnlineByGroupIDInTime(int groupID, long atime) {
    Object[] o = {new Long(groupID), new Long(atime)};
    return this.getHibernateTemplate().find(LOADS_BY_GROUPID_INTIME, o);
  }

  /**
   *
   * @param atime long
   * @return int
   */
  public long getUserOnlineNum(long atime) {
    List l = this.getHibernateTemplate().find(GET_NUM_INTIME,new Long(atime));
    if (l == null || l.isEmpty()) {
      return 0;
    }
    else {
      return ( (Long) l.get(0)).longValue();
    }
  }

  /**
   *
   * @param atime long
   * @param boradID long
   * @param hiddenUser int
   * @param groups List
   * @return List
   */
  public List findUserOnlines(final long atime, final long boardID, final int hiddenUser,
                              final List groups) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException {
        Criteria c = s.createCriteria(UserOnline.class);
        c.add(Restrictions.ge("onlineTime", new Long(atime)));
        if (boardID != 0) {
          c.add(Restrictions.eq("boardID", new Long(boardID)));
        }
        if (hiddenUser != -1) {
          c.add(Restrictions.eq("hiddenUser", new Integer(hiddenUser)));
        }
        if (groups != null && !groups.isEmpty()) {
          c.add(Restrictions.in("userGroupID", groups));
        }
        return c.list();
      }
    });

  }

  /**
   *
   * @param atime long
   * @param boradID long
   * @param hiddenUser int
   * @param groups List
   * @return int
   */
  public long getUserOnlineNum(final long atime, final long boardID, final int hiddenUser,
                              final List groups) {
    List l = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException {
        Criteria c = s.createCriteria(UserOnline.class);
        c.setProjection(Projections.count("id"));
        c.add(Restrictions.ge("onlineTime", new Long(atime)));
        if (boardID != 0) {
          c.add(Restrictions.eq("boardID", new Long(boardID)));
        }
        if (hiddenUser != -1) {
          c.add(Restrictions.eq("hiddenUser", new Integer(hiddenUser)));
        }
        if (groups != null && !groups.isEmpty()) {
          c.add(Restrictions.in("userGroupID", groups));
        }
        return c.list();
      }
    });
    if (l == null || l.isEmpty()) {
      return 0;
    }
    else {
      return ( (Integer) l.get(0)).longValue();
    }
  }

  /**
   *
   * @param atime long
   * @param ids List
   * @return int
   */
  public long getUserOnlineNumInIds(final long atime, final List ids) {
    List l = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(GET_NUM_IN_IDS);
        query.setParameterList("ids", ids);
        query.setLong("atime", atime);
        List list = query.list();
        return list;
      }
    });
    if (l == null || l.isEmpty()) {
      return 0;
    }
    else {
      return ( (Integer) l.get(0)).longValue();
    }
  }

  /**
   *
   * @param atime long
   * @param ids List
   * @return List
   */
  public List findUserOnlinesInIds(final long atime, final List ids) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(LOADS_IN_IDS);
        query.setParameterList("ids", ids);
        query.setLong("atime", atime);
        List list = query.list();
        return list;
      }
    });
  }

  /**
   *
   * @param atime long
   * @param ids List
   * @param boradID long
   * @param hiddenUser int
   * @param groups List
   * @return int
   */
  public long getUserOnlineNumInIds(final long atime, final List ids, final long boardID,
                                   final int hiddenUser, final List groups) {
    List l = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException {
        Criteria c = s.createCriteria(UserOnline.class);
        c.setProjection(Projections.count("id"));
        c.add(Restrictions.ge("onlineTime", new Long(atime)));
        c.add(Restrictions.in("userID", ids));
        if (boardID != 0) {
          c.add(Restrictions.eq("boardID", new Long(boardID)));
        }
        if (hiddenUser != -1) {
          c.add(Restrictions.eq("hiddenUser", new Integer(hiddenUser)));
        }
        if (groups != null && !groups.isEmpty()) {
          c.add(Restrictions.in("userGroupID", groups));
        }
        return c.list();
      }
    });
    if (l == null || l.isEmpty()) {
      return 0;
    }
    else {
      return ( (Integer) l.get(0)).longValue();
    }
  }

  /**
   *
   * @param atime long
   * @param ids List
   * @param boradID long
   * @param hiddenUser int
   * @param groups List
   * @return List
   */
  public List findUserOnlinesInIds(final long atime, final List ids, final long boardID,
                                   final int hiddenUser, final List groups) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException {
        Criteria c = s.createCriteria(UserOnline.class);
        c.add(Restrictions.ge("onlineTime", new Long(atime)));
        c.add(Restrictions.in("userID", ids));
        if (boardID != 0) {
          c.add(Restrictions.eq("boardID", new Long(boardID)));
        }
        if (hiddenUser != -1) {
          c.add(Restrictions.eq("hiddenUser", new Integer(hiddenUser)));
        }
        if (groups != null && !groups.isEmpty()) {
          c.add(Restrictions.in("userGroupID", groups));
        }
        return c.list();
      }
    });

  }

  /**
   * 删除UserOnline对象
   *
   * @param userOnline UserOnline
   * @todo Implement this com.laoer.bbscs.dao.UserOnlineDAO method
   */
  public void removeUserOnline(UserOnline userOnline) {
    this.getHibernateTemplate().delete(userOnline);
  }

  /**
   *
   * @param atime long
   * @todo Implement this com.laoer.bbscs.dao.UserOnlineDAO method
   */
  public void removeUserOnlineOutTime(final long atime) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_ALL_OUTTIME);
        query.setLong(0, atime);
        query.executeUpdate();
        return null;
      }
    });
  }
}
