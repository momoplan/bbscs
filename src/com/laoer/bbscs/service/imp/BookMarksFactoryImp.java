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
public class BookMarksFactoryImp
    implements BookMarkFactory {

  private static final Log logger = LogFactory.getLog(BookMarksFactoryImp.class);

  private int modNum;

  public BookMarksFactoryImp() {
  }

  /**
   * 取得BookMark实例
   *
   * @param userId String
   * @return BookMark
   * @todo Implement this com.laoer.bbscs.service.BookMarkFactory method
   */
  public synchronized BookMark getInstance(String userId) {
    try {
      return (BookMark) Class.forName(BBSCSUtil.getClassName("BookMark", userId, this.getModNum())).
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
