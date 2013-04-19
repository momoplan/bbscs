package com.laoer.bbscs.service.imp;

import org.apache.commons.logging.*;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.comm.*;
import com.laoer.bbscs.service.*;

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
public class NotesFactoryImp
    implements NoteFactory {

  private static final Log logger = LogFactory.getLog(NotesFactoryImp.class);

  private int modNum;

  public NotesFactoryImp() {
  }

  public synchronized Note getInstance(String userId) {
    try {
      return (Note) Class.forName(BBSCSUtil.getClassName("Note", userId, this.getModNum())).
          newInstance();
    }
    catch (ClassNotFoundException ex) {
      logger.error(ex);
      return null;
    }
    catch (IllegalAccessException ex) {
      logger.error(ex);
      return null;
    }
    catch (InstantiationException ex) {
      logger.error(ex);
      return null;
    }
  }

  public int getModNum() {
    return modNum;
  }

  public void setModNum(int modNum) {
    this.modNum = modNum;
  }
}
