package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.NoteDAO;
import com.laoer.bbscs.bean.Note;
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
public class NoteHibernateDAO
    extends HibernateDaoSupport implements NoteDAO {

  private static final String LOAD_BY_ID_FROMID = "from Note where id = ? and fromID = ?";

  private static final String LOAD_BY_ID_TOID = "from Note where id = ? and toID = ?";

  private static final String GET_ALL_NUM_OUTBOX =
      "select count(*) from Note where fromID = ? and noteType = ?";

  private static final String LOADS_OUTBOX =
      "from Note where fromID = ? and noteType = ? order by createTime desc";

  private static final String GET_ALL_NUM_INBOX =
      "select count(*) from Note where toID = ? and noteType = ?";

  private static final String LOADS_INBOX =
      "from Note where toID = ? and noteType = ? order by createTime desc";

  private static final String GET_NUM_INBOX_BY_ISNEW =
      "select count(*) from Note where toID = ? and noteType = ? and isNew = ?";

  private static final String LODAS_INIDS_OUTBOX =
      "from Note where id in (:ids) and fromID = :fromId";

  private static final String LODAS_INIDS_INBOX = "from Note where id in (:ids) and toID = :toId";

  private static final String REMOVE_ALL_OUTBOX =
      "delete from Note where fromID = ? and noteType = ?";

  private static final String REMOVE_ALL_INBOX = "delete from Note where toID = ? and noteType = ?";

  private static final String REMOVE_BY_ID_FROMID = "delete from Note where id = ? and fromID = ?";

  private static final String REMOVE_BY_ID_TOID = "delete from Note where id = ? and toID = ?";

  private static final String REMOVE_INIDS_FROMID =
      "delete from Note where id in (:ids) and fromID = :fromId";

  private static final String REMOVE_INIDS_TOID =
      "delete from Note where id in (:ids) and toID = :toId";

  public NoteHibernateDAO() {
    super();
  }

  /**
   * 保存或更新Note对象
   *
   * @param note Note
   * @return Note
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public Note saveNote(Note note) {
    this.getHibernateTemplate().saveOrUpdate(note);
    return note;
  }

  /**
   * 根据ID和发送者ID取得Note对象
   *
   * @param id String
   * @param fromID String
   * @return Note
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public Note findNoteByIDFromID(String id, String fromID) {
    Object[] o = {id, fromID};
    List l = this.getHibernateTemplate().find(LOAD_BY_ID_FROMID, o);
    if (l == null || l.isEmpty()) {
      return null;
    }
    else {
      return (Note) l.get(0);
    }
  }

  /**
   * 根据ID和接收者ID取得Note对象
   *
   * @param id String
   * @param toID String
   * @return Note
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public Note findNoteByIDToID(String id, String toID) {
    Object[] o = {id, toID};
    List l = this.getHibernateTemplate().find(LOAD_BY_ID_TOID, o);
    if (l == null || l.isEmpty()) {
      return null;
    }
    else {
      return (Note) l.get(0);
    }
  }

  /**
   * 取得发件箱纸条数量
   *
   * @param fromID String
   * @return int
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public long getNoteAllNumOutBox(String fromID) {
    Object[] o = {fromID, new Integer(0)};
    List l = this.getHibernateTemplate().find(GET_ALL_NUM_OUTBOX, o);
    if (l == null || l.isEmpty()) {
      return 0;
    }
    else {
      return ( (Long) l.get(0)).longValue();
    }
  }

  /**
   * 取得发件箱Note分页列表
   *
   * @param fromId String
   * @param firstResult int
   * @param maxResults int
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public List findNotesOutBox(final String fromId, final int firstResult, final int maxResults) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(LOADS_OUTBOX);
        query.setString(0, fromId);
        query.setInteger(1, 0);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);

        List list = query.list();
        return list;
      }
    });
  }

  /**
   * 取得收件箱纸条数量
   *
   * @param toID String
   * @return int
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public long getNoteAllNumInBox(String toID) {
    Object[] o = {toID, new Integer(1)};
    List l = this.getHibernateTemplate().find(GET_ALL_NUM_INBOX, o);
    if (l == null || l.isEmpty()) {
      return 0;
    }
    else {
      return ( (Long) l.get(0)).longValue();
    }
  }

  /**
   * 取得收件箱Note分页列表
   *
   * @param toID String
   * @param firstResult int
   * @param maxResults int
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public List findNotesInBox(final String toID, final int firstResult, final int maxResults) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(LOADS_INBOX);
        query.setString(0, toID);
        query.setInteger(1, 1);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);

        List list = query.list();
        return list;
      }
    });
  }

  /**
   * 根据isNew值取得收件箱纸条数量
   * @param toID String
   * @param isNew int
   * @return int
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public long getNoteNumInBoxByIsNew(String toID, int isNew) {
    Object[] o = {toID, new Integer(1), new Integer(isNew)};
    List l = this.getHibernateTemplate().find(GET_NUM_INBOX_BY_ISNEW, o);
    if (l == null || l.isEmpty()) {
      return 0;
    }
    else {
      return ( (Long) l.get(0)).longValue();
    }
  }

  /**
   * 取得发件箱中指定ID的Note列表
   *
   * @param fromId String
   * @param values List
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public List findNotesInIDsOutBox(final String fromId, final List values) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(LODAS_INIDS_OUTBOX);
        query.setParameterList("ids", values);
        query.setString("fromId", fromId);
        List list = query.list();
        return list;
      }
    });
  }

  /**
   * 取得收件箱中指定ID的Note列表
   *
   * @param fromId String
   * @param values List
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public List findNotesInIDsInBox(final String toId, final List values) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(LODAS_INIDS_INBOX);
        query.setParameterList("ids", values);
        query.setString("toId", toId);
        List list = query.list();
        return list;
      }
    });
  }

  /**
   * 删除Note对象
   *
   * @param note Note
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public void removeNote(Note note) {
    this.getHibernateTemplate().delete(note);
  }

  /**
   * 删除发件箱的所有邮件
   *
   * @param fromID String
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public void removeAllOutBox(final String fromID) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_ALL_OUTBOX);
        query.setString(0, fromID);
        query.setInteger(1, 0);
        query.executeUpdate();
        return null;
      }
    });
  }

  /**
   * 删除收件箱的所有邮件
   *
   * @param toID String
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public void removeAllInBox(final String toID) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_ALL_INBOX);
        query.setString(0, toID);
        query.setInteger(1, 1);
        query.executeUpdate();
        return null;
      }
    });
  }

  /**
   * 根据ID和发送者ID删除Note对象
   *
   * @param id String
   * @param fromID String
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public void removeByIDFromID(final String id, final String fromID) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_BY_ID_FROMID);
        query.setString(0, id);
        query.setString(1, fromID);
        query.executeUpdate();
        return null;
      }
    });
  }

  /**
   * 根据ID和接收者ID删除Note对象
   *
   * @param id String
   * @param toID String
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public void removeByIDToID(final String id, final String toID) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_BY_ID_TOID);
        query.setString(0, id);
        query.setString(1, toID);
        query.executeUpdate();
        return null;
      }
    });
  }

  /**
   * 删除指定ID的Note对象
   * @param values List
   * @param fromID String
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public void removeInIDsFromID(final List values, final String fromID) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_INIDS_FROMID);
        query.setParameterList("ids", values);
        query.setString("fromId", fromID);
        query.executeUpdate();
        return null;
      }
    });
  }

  /**
   * 删除指定ID的Note对象
   * @param values List
   * @param toID String
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public void removeInIDsToID(final List values, final String toID) {
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(REMOVE_INIDS_TOID);
        query.setParameterList("ids", values);
        query.setString("toId", toID);
        query.executeUpdate();
        return null;
      }
    });
  }
}
