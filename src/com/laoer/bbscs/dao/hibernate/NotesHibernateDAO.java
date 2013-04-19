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
public class NotesHibernateDAO
    extends HibernateDaoSupport implements NoteDAO {

  private int modNum;

  public NotesHibernateDAO() {
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
    StringBuffer q = new StringBuffer();
    q.append("from Note");
    q.append(BBSCSUtil.getTableID(fromID, this.getModNum()));
    q.append(" where id = ? and fromID = ?");
    Object[] o = {id, fromID};
    List l = this.getHibernateTemplate().find(q.toString(), o);
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
    StringBuffer q = new StringBuffer();
    q.append("from Note");
    q.append(BBSCSUtil.getTableID(toID, this.getModNum()));
    q.append(" where id = ? and toID = ?");
    Object[] o = {id, toID};
    List l = this.getHibernateTemplate().find(q.toString(), o);
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
    StringBuffer q = new StringBuffer();
    q.append("select count(*) from Note");
    q.append(BBSCSUtil.getTableID(fromID, this.getModNum()));
    q.append(" where fromID = ? and noteType = ?");
    Object[] o = {fromID, new Integer(0)};
    List l = this.getHibernateTemplate().find(q.toString(), o);
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
    final StringBuffer q = new StringBuffer();
    q.append("from Note");
    q.append(BBSCSUtil.getTableID(fromId, this.getModNum()));
    q.append(" where fromID = ? and noteType = ? order by createTime desc");
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(q.toString());
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
    StringBuffer q = new StringBuffer();
    q.append("select count(*) from Note");
    q.append(BBSCSUtil.getTableID(toID, this.getModNum()));
    q.append(" where toID = ? and noteType = ?");
    Object[] o = {toID, new Integer(1)};
    List l = this.getHibernateTemplate().find(q.toString(), o);
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
    final StringBuffer q = new StringBuffer();
    q.append("from Note");
    q.append(BBSCSUtil.getTableID(toID, this.getModNum()));
    q.append(" where toID = ? and noteType = ? order by createTime desc");
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(q.toString());
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
    StringBuffer q = new StringBuffer();
    q.append("select count(*) from Note");
    q.append(BBSCSUtil.getTableID(toID, this.getModNum()));
    q.append(" where toID = ? and noteType = ? and isNew = ?");
    Object[] o = {toID, new Integer(1), new Integer(isNew)};
    List l = this.getHibernateTemplate().find(q.toString(), o);
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
    final StringBuffer q = new StringBuffer();
    q.append("from Note");
    q.append(BBSCSUtil.getTableID(fromId, this.getModNum()));
    q.append(" where id in (:ids) and fromID = :fromId");
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(q.toString());
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
   * @param toId String
   * @param values List
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public List findNotesInIDsInBox(final String toId, final List values) {
    final StringBuffer q = new StringBuffer();
    q.append("from Note");
    q.append(BBSCSUtil.getTableID(toId, this.getModNum()));
    q.append(" where id in (:ids) and toID = :toId");
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(q.toString());
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
    final StringBuffer q = new StringBuffer();
    q.append("delete from Note");
    q.append(BBSCSUtil.getTableID(fromID, this.getModNum()));
    q.append(" where fromID = ? and noteType = ?");
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(q.toString());
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
    final StringBuffer q = new StringBuffer();
    q.append("delete from Note");
    q.append(BBSCSUtil.getTableID(toID, this.getModNum()));
    q.append(" where toID = ? and noteType = ?");
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(q.toString());
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
    final StringBuffer q = new StringBuffer();
    q.append("delete from Note");
    q.append(BBSCSUtil.getTableID(fromID, this.getModNum()));
    q.append(" where id = ? and fromID = ?");
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(q.toString());
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
    final StringBuffer q = new StringBuffer();
    q.append("delete from Note");
    q.append(BBSCSUtil.getTableID(toID, this.getModNum()));
    q.append(" where id = ? and toID = ?");
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(q.toString());
        query.setString(0, id);
        query.setString(1, toID);
        query.executeUpdate();
        return null;
      }
    });
  }

  /**
   * 删除指定ID的Note对象
   *
   * @param values List
   * @param fromID String
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public void removeInIDsFromID(final List values, final String fromID) {
    final StringBuffer q = new StringBuffer();
    q.append("delete from Note");
    q.append(BBSCSUtil.getTableID(fromID, this.getModNum()));
    q.append(" where id in (:ids) and fromID = :fromId");
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(q.toString());
        query.setParameterList("ids", values);
        query.setString("fromId", fromID);
        query.executeUpdate();
        return null;
      }
    });
  }

  /**
   * 删除指定ID的Note对象
   *
   * @param values List
   * @param toID String
   * @todo Implement this com.laoer.bbscs.dao.NoteDAO method
   */
  public void removeInIDsToID(final List values, final String toID) {
    final StringBuffer q = new StringBuffer();
    q.append("delete from Note");
    q.append(BBSCSUtil.getTableID(toID, this.getModNum()));
    q.append(" where id in (:ids) and toID = :toId");
    getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(q.toString());
        query.setParameterList("ids", values);
        query.setString("toId", toID);
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
