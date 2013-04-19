package com.laoer.bbscs.dao;

import java.util.*;
import com.laoer.bbscs.bean.Note;

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
public interface NoteDAO {

  /**
   * 保存或更新Note对象
   * @param note Note
   * @return Note
   */
  public Note saveNote(Note note);

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
   * @param firstResult int
   * @param maxResults int
   * @return List
   */
  public List findNotesOutBox(final String fromId, final int firstResult, final int maxResults);

  /**
   * 取得收件箱纸条数量
   * @param toID String
   * @return int
   */
  public long getNoteAllNumInBox(String toID);

  /**
   * 取得收件箱Note分页列表
   * @param toID String
   * @param firstResult int
   * @param maxResults int
   * @return List
   */
  public List findNotesInBox(final String toID, final int firstResult, final int maxResults);

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
   */
  public void removeNote(Note note);

  /**
   * 删除发件箱的所有邮件
   * @param fromID String
   */
  public void removeAllOutBox(String fromID);

  /**
   * 删除收件箱的所有邮件
   * @param toID String
   */
  public void removeAllInBox(String toID);

  /**
   * 根据ID和发送者ID删除Note对象
   * @param id String
   * @param fromID String
   */
  public void removeByIDFromID(String id, String fromID);

  /**
   * 根据ID和接收者ID删除Note对象
   * @param id String
   * @param toID String
   */
  public void removeByIDToID(String id, String toID);

  /**
   * 删除指定ID的Note对象
   * @param values List
   * @param fromID String
   */
  public void removeInIDsFromID(List values, String fromID);

  /**
   * 删除指定ID的Note对象
   * @param values List
   * @param toID String
   */
  public void removeInIDsToID(List values, String toID);

}
