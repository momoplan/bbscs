package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.BoardPermissionDAO;
import com.laoer.bbscs.bean.BoardPermission;
import java.util.List;
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
public class BoardPermissionHibernateDAO
    extends HibernateDaoSupport implements BoardPermissionDAO {

  private static final String LOAD_BY_BID_GID =
      "from BoardPermission where boardID = ? and groupID = ?";

  private static final String LOADS_BY_BID = "from BoardPermission where boardID = ?";

  private static final String LOADS_BY_GID = "from BoardPermission where groupID = ?";

  private static final String REMOVE_BY_BID = "delete from BoardPermission where boardID = ?";

  private static final String REMOVE_BY_GID = "delete from BoardPermission where groupID = ?";

  public BoardPermissionHibernateDAO() {
    super();
  }

  /**
   * 保存或更新BoardPermission对象
   *
   * @param bp BoardPermission
   * @return BoardPermission
   * @todo Implement this com.laoer.bbscs.dao.BoardPermissionDAO method
   */
  public BoardPermission saveBoardPermission(BoardPermission bp) {
    this.getHibernateTemplate().saveOrUpdate(bp);
    return bp;
  }

  public BoardPermission updateBoardPermission(BoardPermission bp) {
    //System.out.println("update bp");
    this.getHibernateTemplate().update(bp);
    return bp;
  }

  /**
   * 根据ID取得BoardPermission对象
   *
   * @param id String
   * @return BoardPermission
   * @todo Implement this com.laoer.bbscs.dao.BoardPermissionDAO method
   */
  public BoardPermission findBoardPermissionByID(String id) {
    return (BoardPermission)this.getHibernateTemplate().get(BoardPermission.class, id);
  }

  /**
   * 根据BoardID和GrouopID取得BoardPermission对象
   *
   * @param bid long
   * @param gid int
   * @return BoardPermission
   * @todo Implement this com.laoer.bbscs.dao.BoardPermissionDAO method
   */
  public BoardPermission findBoardPermissionByBidGid(long bid, int gid) {
    Object[] o = {new Long(bid), new Integer(gid)};
    List l = this.getHibernateTemplate().find(LOAD_BY_BID_GID, o);
    if (l == null || l.isEmpty()) {
      return null;
    }
    else {
      return (BoardPermission) l.get(0);
    }
  }

  /**
   * 根据BoardID取得BoardPermission列表
   *
   * @param bid long
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.BoardPermissionDAO method
   */
  public List findBoardPermissionsByBid(long bid) {
    return this.getHibernateTemplate().find(LOADS_BY_BID, new Long(bid));
  }

  /**
   * 根据GroupID取得BoardPermission对象
   *
   * @param gid int
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.BoardPermissionDAO method
   */
  public List findBoardPermissionsByGid(int gid) {
    return this.getHibernateTemplate().find(LOADS_BY_GID, new Integer(gid));
  }

  /**
   * 根据BoardID删除BoardPermission对象
   *
   * @param bid long
   * @todo Implement this com.laoer.bbscs.dao.BoardPermissionDAO method
   */
  public void removeBoardPermissionsByBid(final long bid) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_BY_BID);
        query.setLong(0, bid);
        query.executeUpdate();
        return null;
      }
    });
  }

  /**
   * 根据GroupID删除BoardPermission对象
   *
   * @param gid int
   * @todo Implement this com.laoer.bbscs.dao.BoardPermissionDAO method
   */
  public void removeBoardPermissionsByGid(final int gid) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_BY_GID);
        query.setLong(0, gid);
        query.executeUpdate();
        return null;
      }
    });
  }
}
