package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.AgreeAgainstDAO;
import com.laoer.bbscs.bean.AgreeAgainst;

import java.util.*;
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
public class AgreeAgainstHibernateDAO
    extends HibernateDaoSupport implements AgreeAgainstDAO {

  private static final String LOAD_BY_UID_PID_BID =
      "from AgreeAgainst where postID = ? and userID = ? and boardID = ?";

  private static final String REMOVE_OUTTIEM = "delete from AgreeAgainst where createTime < ?";

  public AgreeAgainstHibernateDAO() {
    super();
  }

  /**
   *
   * @param agreeAgainst AgreeAgainst
   * @return AgreeAgainst
   * @todo Implement this com.laoer.bbscs.dao.AgreeAgainstDAO method
   */
  public AgreeAgainst saveAgreeAgainst(AgreeAgainst agreeAgainst) {
    this.getHibernateTemplate().saveOrUpdate(agreeAgainst);
    return agreeAgainst;
  }

  /**
   *
   * @param userID String
   * @param postID String
   * @param bid long
   * @return AgreeAgainst
   * @todo Implement this com.laoer.bbscs.dao.AgreeAgainstDAO method
   */
  public AgreeAgainst findAgreeAgainstByUidPidBid(String userID, String postID, long bid) {
    Object[] o = {postID, userID, new Long(bid)};
    List l = this.getHibernateTemplate().find(LOAD_BY_UID_PID_BID, o);
    if (l == null || l.isEmpty()) {
      return null;
    }
    else {
      return (AgreeAgainst) l.get(0);
    }
  }

  /**
   *
   * @param time long
   * @todo Implement this com.laoer.bbscs.dao.AgreeAgainstDAO method
   */
  public void removeOutTime(final long time) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_OUTTIEM);
        query.setLong(0, time);
        query.executeUpdate();
        return null;
      }
    });
  }
}
