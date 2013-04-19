package com.laoer.bbscs.dao.hibernate;

import java.util.*;
import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.VoteUserDAO;
import com.laoer.bbscs.bean.VoteUser;
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
public class VoteUserHibernateDAO
    extends HibernateDaoSupport implements VoteUserDAO {

  private static final String LOAD_BY_VID_UID = "from VoteUser where voteID = ? and voteUserID = ?";

  private static final String REMOVE_OUT_TIME = "delete from VoteUser where voteTime < ?";

  public VoteUserHibernateDAO() {
    super();
  }

  /**
   *
   * @param voteUser VoteUser
   * @return VoteUser
   * @todo Implement this com.laoer.bbscs.dao.VoteUserDAO method
   */
  public VoteUser saveVoteUser(VoteUser voteUser) {
    this.getHibernateTemplate().saveOrUpdate(voteUser);
    return voteUser;
  }

  /**
   *
   * @param voteID String
   * @param userID String
   * @return VoteUser
   * @todo Implement this com.laoer.bbscs.dao.VoteUserDAO method
   */
  public VoteUser findVoteUserByVoteIDUserID(String voteID, String userID) {
    Object[] o = {voteID, userID};
    List l = this.getHibernateTemplate().find(LOAD_BY_VID_UID, o);
    if (l == null || l.isEmpty()) {
      return null;
    }
    else {
      return (VoteUser) l.get(0);
    }
  }

  /**
   *
   * @param atime long
   * @todo Implement this com.laoer.bbscs.dao.VoteUserDAO method
   */
  public void removeOutTime(final long atime) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_OUT_TIME);
        query.setLong(0, atime);
        query.executeUpdate();
        return null;
      }
    });
  }
}
