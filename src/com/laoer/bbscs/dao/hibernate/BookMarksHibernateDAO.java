package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.BookMarkDAO;
import com.laoer.bbscs.bean.BookMark;
import java.util.List;
import com.laoer.bbscs.comm.BBSCSUtil;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.sql.SQLException;

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
public class BookMarksHibernateDAO
    extends HibernateDaoSupport implements BookMarkDAO {

  private int modNum;

  public BookMarksHibernateDAO() {
    super();
  }

  /**
   * 保存或更新BookMark对象
   *
   * @param bm BookMark
   * @return BookMark
   * @todo Implement this com.laoer.bbscs.dao.BookMarkDAO method
   */
  public BookMark saveBookMark(BookMark bm) {
    this.getHibernateTemplate().saveOrUpdate(bm);
    return bm;
  }

  /**
   * 根据ID和UserID取得BookMark对象
   *
   * @param id String
   * @param userID String
   * @return BookMark
   * @todo Implement this com.laoer.bbscs.dao.BookMarkDAO method
   */
  public BookMark findBookMarkByIDUserID(String id, String userID) {
    StringBuffer q = new StringBuffer();
    q.append("from BookMark");
    q.append(BBSCSUtil.getTableID(userID, this.getModNum()));
    q.append(" where id = ? and userID = ?");
    Object[] o = {id, userID};
    List l = this.getHibernateTemplate().find(q.toString(), o);
    if (l == null || l.isEmpty()) {
      return null;
    }
    else {
      return (BookMark) l.get(0);
    }
  }

  /**
   * 根据UserID取得BookMark数量
   *
   * @param userID String
   * @return int
   * @todo Implement this com.laoer.bbscs.dao.BookMarkDAO method
   */
  public long getBookMarkNumByUserID(String userID) {
    StringBuffer q = new StringBuffer();
    q.append("select count(*) from BookMark");
    q.append(BBSCSUtil.getTableID(userID, this.getModNum()));
    q.append(" where userID = ?");
    List l = this.getHibernateTemplate().find(q.toString(), userID);
    if (l == null || l.isEmpty()) {
      return 0;
    }
    else {
      return ( (Long) l.get(0)).longValue();
    }
  }

  /**
   * 根据UserID取得BookMark列表
   *
   * @param userID String
   * @param firstResult int
   * @param maxResults int
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.BookMarkDAO method
   */
  public List findBookMarks(final String userID, final int firstResult, final int maxResults) {
    final StringBuffer q = new StringBuffer();
    q.append("from BookMark");
    q.append(BBSCSUtil.getTableID(userID, this.getModNum()));
    q.append(" where userID = ? order by createTime desc");
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(q.toString());
        query.setString(0, userID);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);

        List list = query.list();
        return list;
      }
    });
  }

  /**
   * 根据UserID和isShare取得BookMark数量
   * @param userID String
   * @param isShare int
   * @return int
   * @todo Implement this com.laoer.bbscs.dao.BookMarkDAO method
   */
  public long getBookMarkNumByUserIDShare(String userID, int isShare) {
    StringBuffer q = new StringBuffer();
    q.append("select count(*) from BookMark");
    q.append(BBSCSUtil.getTableID(userID, this.getModNum()));
    q.append(" where userID = ? and isShare = ?");

    Object[] o = {userID, new Integer(isShare)};
    List l = this.getHibernateTemplate().find(q.toString(), o);
    if (l == null || l.isEmpty()) {
      return 0;
    }
    else {
      return ( (Long) l.get(0)).longValue();
    }
  }

  /**
   * 根据UserID和isShare取得BookMark列表
   * @param userID String
   * @param isShare int
   * @param firstResult int
   * @param maxResults int
   * @return List
   */
  public List findBookMarksByUserIDShare(final String userID, final int isShare,
                                         final int firstResult,
                                         final int maxResults) {
    final StringBuffer q = new StringBuffer();
    q.append("from BookMark");
    q.append(BBSCSUtil.getTableID(userID, this.getModNum()));
    q.append(" where userID = ? and isShare = ? order by createTime desc");

    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(q.toString());
        query.setString(0, userID);
        query.setInteger(1, isShare);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);

        List list = query.list();
        return list;
      }
    });
  }

  /**
   * 删除BookMark对象
   *
   * @param bm BookMark
   * @todo Implement this com.laoer.bbscs.dao.BookMarkDAO method
   */
  public void removeBookMark(BookMark bm) {
    this.getHibernateTemplate().delete(bm);
  }

  /**
   * 根据ID和UserID删除BookMark对象
   *
   * @param id String
   * @param userID String
   * @todo Implement this com.laoer.bbscs.dao.BookMarkDAO method
   */
  public void removeBookMarkByIDUserID(final String id, final String userID) {
    final StringBuffer q = new StringBuffer();
    q.append("delete from BookMark");
    q.append(BBSCSUtil.getTableID(userID, this.getModNum()));
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

  public int getModNum() {
    return modNum;
  }

  public void setModNum(int modNum) {
    this.modNum = modNum;
  }
}
