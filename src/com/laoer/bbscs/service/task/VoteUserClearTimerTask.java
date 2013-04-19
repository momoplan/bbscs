package com.laoer.bbscs.service.task;

import java.util.*;

import org.apache.commons.logging.*;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.exception.*;

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
public class VoteUserClearTimerTask
    extends TimerTask {

  private static final Log logger = LogFactory.getLog(VoteUserClearTimerTask.class);

  private AgreeAgainstService agreeAgainstService;

  private VoteUserService voteUserService;

  public VoteUserClearTimerTask() {
  }

  /**
   * When an object implementing interface <code>Runnable</code> is used to create a thread, starting the
   * thread causes the object's <code>run</code> method to be called in that separately executing thread.
   *
   * @todo Implement this java.lang.Runnable method
   */
  public void run() {
    long atime = System.currentTimeMillis() - (24 * 3600000);
    try {
      logger.info("Vote user clear...");
      this.getAgreeAgainstService().removeOutTime(atime);
      this.getVoteUserService().removeOutTime(atime);
      logger.info("Vote user clear end");
    }
    catch (BbscsException ex) {
      logger.error(ex);
    }
  }

  public AgreeAgainstService getAgreeAgainstService() {
    return agreeAgainstService;
  }

  public VoteUserService getVoteUserService() {
    return voteUserService;
  }

  public void setAgreeAgainstService(AgreeAgainstService agreeAgainstService) {
    this.agreeAgainstService = agreeAgainstService;
  }

  public void setVoteUserService(VoteUserService voteUserService) {
    this.voteUserService = voteUserService;
  }
}
