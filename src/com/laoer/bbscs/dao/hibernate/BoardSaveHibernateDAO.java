package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.BoardSaveDAO;
import com.laoer.bbscs.bean.BoardSave;
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
public class BoardSaveHibernateDAO
    extends HibernateDaoSupport implements BoardSaveDAO {

  private static final String LOAD_BY_UID_BID = "from BoardSave where userID = ? and boardID = ?";

  private static final String LOADS_BY_USERID = "from BoardSave where userID = ?";

  private static final String LOADS_BOARDID_BY_USERID = "select boardID from BoardSave where userID = ?";

  private static final String REMOVE_BY_UID_BID = "delete from BoardSave where userID = ? and boardID = ?";

  private static final String REMOVE_BY_BID = "delete from BoardSave where boardID = ?";

  private static final String REMOVE_IN_IDS_BY_UID =
      "delete from BoardSave where userID = :userID and boardID in (:ids)";

  public BoardSaveHibernateDAO() {
    super();
  }

  /**
   *
   * @param boardSave BoardSave
   * @return BoardSave
   * @todo Implement this com.laoer.bbscs.dao.BoardSaveDAO method
   */
  public BoardSave saveBoardSave(BoardSave boardSave) {
    this.getHibernateTemplate().saveOrUpdate(boardSave);
    return boardSave;
  }

  /**
   *
   * @param id String
   * @return BoardSave
   * @todo Implement this com.laoer.bbscs.dao.BoardSaveDAO method
   */
  public BoardSave findBoardSaveById(String id) {
    return (BoardSave)this.getHibernateTemplate().get(BoardSave.class, id);
  }

  /**
   *
   * @param userId String
   * @param bid long
   * @return BoardSave
   * @todo Implement this com.laoer.bbscs.dao.BoardSaveDAO method
   */
  public BoardSave findBoardSaveByUidBid(String userId, long bid) {
    Object[] o = {userId, new Long(bid)};
    List l = this.getHibernateTemplate().find(LOAD_BY_UID_BID, o);
    if (l == null || l.isEmpty()) {
      return null;
    }
    else {
      return (BoardSave) l.get(0);
    }
  }

  /**
   *
   * @param userId String
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.BoardSaveDAO method
   */
  public List findBoardSavesByUid(String userId) {
    return this.getHibernateTemplate().find(LOADS_BY_USERID, userId);
  }

  /**
   *
   * @param userId String
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.BoardSaveDAO method
   */
  public List findBoardSaveBidsByUid(String userId) {
    return this.getHibernateTemplate().find(LOADS_BOARDID_BY_USERID, userId);
  }

  /**
   *
   * @param boardSave BoardSave
   * @todo Implement this com.laoer.bbscs.dao.BoardSaveDAO method
   */
  public void removeBoardSave(BoardSave boardSave) {
    this.getHibernateTemplate().delete(boardSave);
  }

  /**
   *
   * @param userId String
   * @param bid long
   * @todo Implement this com.laoer.bbscs.dao.BoardSaveDAO method
   */
  public void removeBoardSaveByUidBid(final String userId, final long bid) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_BY_UID_BID);
        query.setString(0, userId);
        query.setLong(1, bid);
        query.executeUpdate();
        return null;
      }
    });
  }

  /**
   *
   * @param bid long
   * @todo Implement this com.laoer.bbscs.dao.BoardSaveDAO method
   */
  public void removeBoardSaveByBid(final long bid) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_BY_BID);
        query.setLong(0, bid);
        query.executeUpdate();
        return null;
      }
    });
  }

  public void removeBoardSaveByBidsUid(final String userId, final List ids) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_IN_IDS_BY_UID);
        query.setString("userID", userId);
        query.setParameterList("ids", ids);
        query.executeUpdate();
        return null;
      }
    });
  }
}
