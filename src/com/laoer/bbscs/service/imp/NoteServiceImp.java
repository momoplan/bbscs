package com.laoer.bbscs.service.imp;

import java.util.*;

import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.service.web.*;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

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
public class NoteServiceImp
    implements NoteService {

  private static final Log logger = LogFactory.getLog(NoteServiceImp.class);

  private NoteDAO noteDAO;

  public NoteServiceImp() {
  }

  /**
   * 保存或更新Note对象
   *
   * @param note Note
   * @return Note
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public Note saveNote(Note note) throws BbscsException {
    try {
      return this.getNoteDAO().saveNote(note);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 创建纸条
   *
   * @param inbodNote Note
   * @param outboxNote Note
   * @return Note[]
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public Note[] createNote(Note inbodNote, Note outboxNote) throws BbscsException {
    try {
      inbodNote = this.getNoteDAO().saveNote(inbodNote);
      outboxNote = this.getNoteDAO().saveNote(outboxNote);
      Note[] note = {inbodNote, outboxNote};
      return note;
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 根据ID和发送者ID取得Note对象
   *
   * @param id String
   * @param fromID String
   * @return Note
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public Note findNoteByIDFromID(String id, String fromID) {
    return this.getNoteDAO().findNoteByIDFromID(id, fromID);
  }

  /**
   * 根据ID和接收者ID取得Note对象
   *
   * @param id String
   * @param toID String
   * @return Note
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public Note findNoteByIDToID(String id, String toID) {
    return this.getNoteDAO().findNoteByIDToID(id, toID);
  }

  /**
   * 取得发件箱纸条数量
   *
   * @param fromID String
   * @return int
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public long getNoteAllNumOutBox(String fromID) {
    return this.getNoteDAO().getNoteAllNumOutBox(fromID);
  }

  /**
   * 取得发件箱Note分页列表
   *
   * @param fromId String
   * @param pages Pages
   * @return PageList
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public PageList findNotesOutBox(String fromId, Pages pages) {
    PageList pl = new PageList();
    if (pages.getTotalNum() == -1) {
      pages.setTotalNum(this.getNoteDAO().getNoteAllNumOutBox(fromId));
    }
    pages.executeCount();
    List l = this.getNoteDAO().findNotesOutBox(fromId, pages.getSpage(), pages.getPerPageNum());
    pl.setObjectList(l);
    pl.setPages(pages);
    return pl;
  }

  /**
   * 取得收件箱纸条数量
   *
   * @param toID String
   * @return int
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public long getNoteAllNumInBox(String toID) {
    return this.getNoteDAO().getNoteAllNumInBox(toID);
  }

  /**
   * 取得收件箱Note分页列表
   *
   * @param toID String
   * @param pages Pages
   * @return PageList
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public PageList findNotesInBox(String toID, Pages pages) {
    PageList pl = new PageList();
    if (pages.getTotalNum() == -1) {
      pages.setTotalNum(this.getNoteDAO().getNoteAllNumInBox(toID));
    }
    pages.executeCount();
    List l = this.getNoteDAO().findNotesInBox(toID, pages.getSpage(), pages.getPerPageNum());
    pl.setObjectList(l);
    pl.setPages(pages);
    return pl;
  }

  /**
   * 根据isNew值取得收件箱纸条数量
   * @param toID String
   * @param isNew int
   * @return int
   */
  public long getNoteNumInBoxByIsNew(String toID, int isNew) {
    return this.getNoteDAO().getNoteNumInBoxByIsNew(toID, isNew);
  }

  /**
   * 取得发件箱中指定ID的Note列表
   *
   * @param fromId String
   * @param values List
   * @return List
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public List findNotesInIDsOutBox(String fromId, List values) {
    return this.getNoteDAO().findNotesInIDsOutBox(fromId, values);
  }

  /**
   * 取得收件箱中指定ID的Note列表
   *
   * @param toId String
   * @param values List
   * @return List
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public List findNotesInIDsInBox(String toId, List values) {
    return this.getNoteDAO().findNotesInIDsInBox(toId, values);
  }

  /**
   * 删除Note对象
   *
   * @param note Note
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public void removeNote(Note note) throws BbscsException {
    try {
      this.getNoteDAO().removeNote(note);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 删除发件箱的所有邮件
   *
   * @param fromID String
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public void removeAllOutBox(String fromID) throws BbscsException {
    try {
      this.getNoteDAO().removeAllOutBox(fromID);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 删除收件箱的所有邮件
   *
   * @param toID String
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public void removeAllInBox(String toID) throws BbscsException {
    try {
      this.getNoteDAO().removeAllInBox(toID);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 根据ID和发送者ID删除Note对象
   *
   * @param id String
   * @param fromID String
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public void removeByIDFromID(String id, String fromID) throws BbscsException {
    try {
      this.getNoteDAO().removeByIDFromID(id, fromID);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 根据ID和接收者ID删除Note对象
   *
   * @param id String
   * @param toID String
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public void removeByIDToID(String id, String toID) throws BbscsException {
    try {
      this.getNoteDAO().removeByIDToID(id, toID);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 删除指定ID的Note对象
   *
   * @param values List
   * @param fromID String
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public void removeInIDsFromID(List values, String fromID) throws BbscsException {
    try {
      this.getNoteDAO().removeInIDsFromID(values, fromID);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 删除指定ID的Note对象
   *
   * @param values List
   * @param toID String
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.NoteService method
   */
  public void removeInIDsToID(List values, String toID) throws BbscsException {
    try {
      this.getNoteDAO().removeInIDsToID(values, toID);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  public NoteDAO getNoteDAO() {
    return noteDAO;
  }

  public void setNoteDAO(NoteDAO noteDAO) {
    this.noteDAO = noteDAO;
  }
}
