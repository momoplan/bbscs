package com.laoer.bbscs.dao.hibernate;

import java.util.*;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.comm.BBSCSUtil;
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
public class SubscibesHibernateDAO
    extends HibernateDaoSupport implements SubscibeDAO {

  private int modNum = 10;

  public SubscibesHibernateDAO() {
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
    StringBuffer q = new StringBuffer();
    q.append("from Subscibe");
    q.append(BBSCSUtil.getTableID(bid, this.getModNum()));
    q.append(" where id = ? and userID = ?");
    Object[] o = {id, userID};
    List l = this.getHibernateTemplate().find(q.toString(), o);
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
    StringBuffer q = new StringBuffer();
    q.append("from Subscibe");
    q.append(BBSCSUtil.getTableID(bid, this.getModNum()));
    q.append(" where postID = ? and userID = ?");
    Object[] o = {postID, userID};
    List l = this.getHibernateTemplate().find(q.toString(), o);
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
    StringBuffer q = new StringBuffer();
    q.append("from Subscibe");
    q.append(BBSCSUtil.getTableID(bid, this.getModNum()));
    q.append(" where postID = ?");
    return this.getHibernateTemplate().find(q.toString(), postID);
  }

  /**
   *
   * @param userID String
   * @param bid long
   * @return int
   * @todo Implement this com.laoer.bbscs.dao.SubscibeDAO method
   */
  public long getSubscibeNumByUserID(String userID, long bid) {
    StringBuffer q = new StringBuffer();
    q.append("select count(*) from Subscibe");
    q.append(BBSCSUtil.getTableID(bid, this.getModNum()));
    q.append(" where userID = ? and boardID = ?");
    Object[] o = {userID, new Long(bid)};
    List l = this.getHibernateTemplate().find(q.toString(), o);
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
    final StringBuffer q = new StringBuffer();
    q.append("from Subscibe");
    q.append(BBSCSUtil.getTableID(bid, this.getModNum()));
    q.append(" where userID = ? and boardID = ? order by createTime desc");
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(q.toString());
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
    final StringBuffer q = new StringBuffer();
    q.append("delete from Subscibe");
    q.append(BBSCSUtil.getTableID(bid, this.getModNum()));
    q.append(" where id = ? and userID = ?");
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(q.toString());
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

  public int getModNum() {
    return modNum;
  }

  public void setModNum(int modNum) {
    this.modNum = modNum;
  }
}
