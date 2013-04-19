package com.laoer.bbscs.dao.hibernate;

import java.util.*;
import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.BoardAuthUserDAO;
import com.laoer.bbscs.bean.BoardAuthUser;
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
public class BoardAuthUserHibernateDAO
    extends HibernateDaoSupport implements BoardAuthUserDAO {

  private static final String LOAD_BY_BID_UID = "from BoardAuthUser where boardID = ? and userID = ?";

  private static final String LOAD_BY_BID_USERNAME = "from BoardAuthUser where boardID = ? and userName = ?";

  private static final String LOADS_BY_BID = "from BoardAuthUser where boardID = ? order by createTime asc";

  private static final String REMOVE_BY_BID_UID =
      "delete from BoardAuthUser where boardID = ? and userID = ?";

  private static final String REMOVE_BY_BID_USERNAME =
      "delete from BoardAuthUser where boardID = ? and userName = ?";

  public BoardAuthUserHibernateDAO() {
    super();
  }

  /**
   *
   * @param boardAuthUser BoardAuthUser
   * @return BoardAuthUser
   * @todo Implement this com.laoer.bbscs.dao.BoardAuthUserDAO method
   */
  public BoardAuthUser saveBoardAuthUser(BoardAuthUser boardAuthUser) {
    this.getHibernateTemplate().saveOrUpdate(boardAuthUser);
    return boardAuthUser;
  }

  /**
   *
   * @param id String
   * @return BoardAuthUser
   * @todo Implement this com.laoer.bbscs.dao.BoardAuthUserDAO method
   */
  public BoardAuthUser findBoardAuthUserById(String id) {
    return (BoardAuthUser)this.getHibernateTemplate().get(BoardAuthUser.class, id);
  }

  /**
   *
   * @param bid long
   * @param uid String
   * @return BoardAuthUser
   * @todo Implement this com.laoer.bbscs.dao.BoardAuthUserDAO method
   */
  public BoardAuthUser findBoardAuthUserByBidUid(long bid, String uid) {
    Object[] o = {new Long(bid), uid};
    List l = this.getHibernateTemplate().find(LOAD_BY_BID_UID, o);
    if (l == null || l.isEmpty()) {
      return null;
    }
    else {
      return (BoardAuthUser) l.get(0);
    }
  }

  /**
   *
   * @param bid long
   * @param userName String
   * @return BoardAuthUser
   * @todo Implement this com.laoer.bbscs.dao.BoardAuthUserDAO method
   */
  public BoardAuthUser findBoardAuthUserByBidUserName(long bid, String userName) {
    Object[] o = {new Long(bid), userName};
    List l = this.getHibernateTemplate().find(LOAD_BY_BID_USERNAME, o);
    if (l == null || l.isEmpty()) {
      return null;
    }
    else {
      return (BoardAuthUser) l.get(0);
    }
  }

  public List findBoardAuthUsersByBid(long bid) {
    return this.getHibernateTemplate().find(LOADS_BY_BID, new Long(bid));
  }

  /**
   *
   * @param boardAuthUser BoardAuthUser
   * @todo Implement this com.laoer.bbscs.dao.BoardAuthUserDAO method
   */
  public void removeBoardAuthUser(BoardAuthUser boardAuthUser) {
    this.getHibernateTemplate().delete(boardAuthUser);
  }

  /**
   *
   * @param bid long
   * @param uid String
   * @todo Implement this com.laoer.bbscs.dao.BoardAuthUserDAO method
   */
  public void removeBoardAuthUserByBidUid(final long bid, final String uid) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_BY_BID_UID);
        query.setLong(1, bid);
        query.setString(0, uid);
        query.executeUpdate();
        return null;
      }
    });
  }

  /**
   *
   * @param bid long
   * @param userName String
   * @todo Implement this com.laoer.bbscs.dao.BoardAuthUserDAO method
   */
  public void removeBoardAuthUserByBidUserName(final long bid, final String userName) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_BY_BID_USERNAME);
        query.setLong(1, bid);
        query.setString(0, userName);
        query.executeUpdate();
        return null;
      }
    });
  }
}
