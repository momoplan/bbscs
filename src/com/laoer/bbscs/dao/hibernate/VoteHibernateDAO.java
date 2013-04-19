package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.VoteDAO;
import com.laoer.bbscs.bean.Vote;

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
public class VoteHibernateDAO
    extends HibernateDaoSupport implements VoteDAO {

  public VoteHibernateDAO() {
    super();
  }

  /**
   *
   * @param vote Vote
   * @return Vote
   * @todo Implement this com.laoer.bbscs.dao.VoteDAO method
   */
  public Vote saveVote(Vote vote) {
    this.getHibernateTemplate().saveOrUpdate(vote);
    return vote;
  }

  /**
   *
   * @param id String
   * @return Vote
   * @todo Implement this com.laoer.bbscs.dao.VoteDAO method
   */
  public Vote findVoteByID(String id) {
    return (Vote)this.getHibernateTemplate().get(Vote.class, id);
  }
}
