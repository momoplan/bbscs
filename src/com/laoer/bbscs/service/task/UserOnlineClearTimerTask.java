package com.laoer.bbscs.service.task;

import java.util.*;

import com.laoer.bbscs.service.*;
import com.laoer.bbscs.exception.*;
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
public class UserOnlineClearTimerTask
    extends TimerTask {

  private static final Log logger = LogFactory.getLog(UserOnlineClearTimerTask.class);

  private UserOnlineService userOnlineService;

  public UserOnlineClearTimerTask() {
  }

  /**
   * When an object implementing interface <code>Runnable</code> is used to create a thread, starting the
   * thread causes the object's <code>run</code> method to be called in that separately executing thread.
   *
   * @todo Implement this java.lang.Runnable method
   */
  public void run() {
    long atime = System.currentTimeMillis() - 3600000;
    try {
      logger.info("UserOnline Clear...");
      this.getUserOnlineService().removeUserOnlineOutTime(atime);
      logger.info("UserOnline Clear End");
    }
    catch (BbscsException ex) {
      logger.error(ex);
    }
  }

  public UserOnlineService getUserOnlineService() {
    return userOnlineService;
  }

  public void setUserOnlineService(UserOnlineService userOnlineService) {
    this.userOnlineService = userOnlineService;
  }
}
