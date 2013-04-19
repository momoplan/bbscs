package com.laoer.bbscs.service.imp;

import com.laoer.bbscs.service.NoteFactory;
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
public class NoteFactoryImp
    implements NoteFactory {

  public NoteFactoryImp() {
  }

  public synchronized Note getInstance(String userId) {
    return new Note();
  }

}
