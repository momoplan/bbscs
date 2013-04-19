package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.VoteItemDAO;
import com.laoer.bbscs.bean.VoteItem;
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
public class VoteItemHibernateDAO
    extends HibernateDaoSupport implements VoteItemDAO {

  private static final String LOADS_BY_VOTEID = "from VoteItem where voteID = ?";

  private static final String LOADS_IN_IDS = "from VoteItem where id in (:ids)";

  public VoteItemHibernateDAO() {
    super();
  }

  /**
   *
   * @param voteItem VoteItem
   * @return VoteItem
   * @todo Implement this com.laoer.bbscs.dao.VoteItemDAO method
   */
  public VoteItem saveVoteItem(VoteItem voteItem) {
    this.getHibernateTemplate().saveOrUpdate(voteItem);
    return voteItem;
  }

  /**
   *
   * @param id String
   * @return VoteItem
   * @todo Implement this com.laoer.bbscs.dao.VoteItemDAO method
   */
  public VoteItem findVoteItemByID(String id) {
    return (VoteItem)this.getHibernateTemplate().get(VoteItem.class, id);
  }

  /**
   *
   * @param voteID String
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.VoteItemDAO method
   */
  public List findVoteItemsByVoteID(String voteID) {
    return this.getHibernateTemplate().find(LOADS_BY_VOTEID, voteID);
  }

  public List findVoteItemsInIds(final List ids) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(LOADS_IN_IDS);
        query.setParameterList("ids", ids);
        return query.list();
      }
    });
  }
}
