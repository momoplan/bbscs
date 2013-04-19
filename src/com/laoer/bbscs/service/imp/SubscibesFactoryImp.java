package com.laoer.bbscs.service.imp;

import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.comm.*;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

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
public class SubscibesFactoryImp
    implements SubscibeFactory {

  private static final Log logger = LogFactory.getLog(SubscibesFactoryImp.class);

  private int modNum;

  public SubscibesFactoryImp() {
  }

  /**
   *
   * @param bid long
   * @return Subscibe
   * @todo Implement this com.laoer.bbscs.service.SubscibeFactory method
   */
  public synchronized Subscibe getInstance(long bid) {
    try {
      return (Subscibe) Class.forName(BBSCSUtil.getClassName("Subscibe", bid, this.getModNum())).
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
