package com.laoer.bbscs.dao.hibernate;

import com.laoer.bbscs.dao.ForumBuyDAO;
import com.laoer.bbscs.bean.ForumBuy;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.util.*;

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
public class ForumBuyHibernateDAO
    extends HibernateDaoSupport implements ForumBuyDAO {

  private static final String LOAD_BY_POSTID_FROMID = "from ForumBuy where postID = ? and buyFromID = ?";

  private static final String GET_NUM_BY_POSTID = "select count(id) from ForumBuy where postID = ?";

  public ForumBuyHibernateDAO() {
    super();
  }

  /**
   *
   * @param forumBuy ForumBuy
   * @return ForumBuy
   * @todo Implement this com.laoer.bbscs.dao.ForumBuyDAO method
   */
  public ForumBuy saveForumBuy(ForumBuy forumBuy) {
    this.getHibernateTemplate().saveOrUpdate(forumBuy);
    return forumBuy;
  }

  /**
   *
   * @param id String
   * @return ForumBuy
   * @todo Implement this com.laoer.bbscs.dao.ForumBuyDAO method
   */
  public ForumBuy findForumBuyId(String id) {
    return (ForumBuy)this.getHibernateTemplate().get(ForumBuy.class, id);
  }

  /**
   *
   * @param postId String
   * @param fromId String
   * @return ForumBuy
   * @todo Implement this com.laoer.bbscs.dao.ForumBuyDAO method
   */
  public ForumBuy findForumBuyByPostIdFromId(String postId, String fromId) {
    Object[] o = {postId, fromId};
    List l = this.getHibernateTemplate().find(LOAD_BY_POSTID_FROMID, o);
    if (l == null || l.isEmpty()) {
      return null;
    }
    else {
      return (ForumBuy) l.get(0);
    }
  }

  /**
   *
   * @param postId String
   * @return int
   * @todo Implement this com.laoer.bbscs.dao.ForumBuyDAO method
   */
  public long getForumBuyNumByPostId(String postId) {
    List l = this.getHibernateTemplate().find(GET_NUM_BY_POSTID, postId);
    if (l == null || l.isEmpty()) {
      return 0;
    }
    else {
      return ( (Long) l.get(0)).longValue();
    }
  }
}
