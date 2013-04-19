package com.laoer.bbscs.service;

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
public interface NoteFactory {

  /**
   *
   * @param userId String
   * @return Note
   */
  public Note getInstance(String userId);

}
