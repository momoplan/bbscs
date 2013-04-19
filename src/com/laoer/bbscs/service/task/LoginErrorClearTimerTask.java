package com.laoer.bbscs.service.task;

import java.util.*;

import com.laoer.bbscs.service.*;
import com.laoer.bbscs.exception.*;
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
public class LoginErrorClearTimerTask
    extends TimerTask {

  private static final Log logger = LogFactory.getLog(LoginErrorClearTimerTask.class);

  private LoginErrorService loginErrorService;

  public LoginErrorClearTimerTask() {
  }

  /**
   * When an object implementing interface <code>Runnable</code> is used to create a thread,
   * starting the thread causes the object's <code>run</code> method to be called in that
   * separately executing thread.
   *
   * @todo Implement this java.lang.Runnable method
   */
  public void run() {
    long atime = System.currentTimeMillis() - (15 * 6000);
    try {
      logger.info("Start LoginErrorClearTimerTask...");
      this.getLoginErrorService().removeLoginErrorsOutTime(atime);
      logger.info("End LoginErrorClearTimerTask");
    }
    catch (BbscsException ex) {
      logger.error(ex);
    }
  }

  public LoginErrorService getLoginErrorService() {
    return loginErrorService;
  }

  public void setLoginErrorService(LoginErrorService loginErrorService) {
    this.loginErrorService = loginErrorService;
  }
}
