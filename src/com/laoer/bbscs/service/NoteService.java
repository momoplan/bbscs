package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.Note;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.web.PageList;
import com.laoer.bbscs.service.web.Pages;
import java.util.List;

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
public interface NoteService {

  /**
   * 保存或更新Note对象
   * @param note Note
   * @return Note
   * @throws BbscsException
   */
  public Note saveNote(Note note) throws BbscsException;

  /**
   * 创建纸条
   * @param inbodNote Note
   * @param outboxNote Note
   * @return Note[]
   * @throws BbscsException
   */
  public Note[] createNote(Note inbodNote, Note outboxNote) throws BbscsException;

  /**
   * 根据ID和发送者ID取得Note对象
   * @param id String
   * @param fromID String
   * @return Note
   */
  public Note findNoteByIDFromID(String id, String fromID);

  /**
   * 根据ID和接收者ID取得Note对象
   * @param id String
   * @param toID String
   * @return Note
   */
  public Note findNoteByIDToID(String id, String toID);

  /**
   * 取得发件箱纸条数量
   * @param fromId String
   * @return int
   */
  public long getNoteAllNumOutBox(String fromID);

  /**
   * 取得发件箱Note分页列表
   * @param fromId String
   * @param pages Pages
   * @return PageList
   */
  public PageList findNotesOutBox(final String fromId, Pages pages);

  /**
   * 取得收件箱纸条数量
   * @param toID String
   * @return int
   */
  public long getNoteAllNumInBox(String toID);

  /**
   * 取得收件箱Note分页列表
   * @param toID String
   * @param pages Pages
   * @return PageList
   */
  public PageList findNotesInBox(final String toID, Pages pages);

  /**
   * 根据isNew值取得收件箱纸条数量
   * @param toID String
   * @param isNew int
   * @return int
   */
  public long getNoteNumInBoxByIsNew(String toID, int isNew);

  /**
   * 取得发件箱中指定ID的Note列表
   * @param fromId String
   * @param values List
   * @return List
   */
  public List findNotesInIDsOutBox(final String fromId, final List values);

  /**
   * 取得收件箱中指定ID的Note列表
   * @param fromId String
   * @param values List
   * @return List
   */
  public List findNotesInIDsInBox(final String toId, final List values);

  /**
   * 删除Note对象
   * @param note Note
   * @throws BbscsException
   */
  public void removeNote(Note note) throws BbscsException;

  /**
   * 删除发件箱的所有邮件
   * @param fromID String
   * @throws BbscsException
   */
  public void removeAllOutBox(String fromID) throws BbscsException;

  /**
   * 删除收件箱的所有邮件
   * @param toID String
   * @throws BbscsException
   */
  public void removeAllInBox(String toID) throws BbscsException;

  /**
   * 根据ID和发送者ID删除Note对象
   * @param id String
   * @param fromID String
   * @throws BbscsException
   */
  public void removeByIDFromID(String id, String fromID) throws BbscsException;

  /**
   * 根据ID和接收者ID删除Note对象
   * @param id String
   * @param toID String
   * @throws BbscsException
   */
  public void removeByIDToID(String id, String toID) throws BbscsException;

  /**
   * 删除指定ID的Note对象
   * @param values List
   * @param fromID String
   * @throws BbscsException
   */
  public void removeInIDsFromID(List values, String fromID) throws BbscsException;

  /**
   * 删除指定ID的Note对象
   * @param values List
   * @param toID String
   * @throws BbscsException
   */
  public void removeInIDsToID(List values, String toID) throws BbscsException;

}
