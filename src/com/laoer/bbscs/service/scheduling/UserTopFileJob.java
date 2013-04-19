package com.laoer.bbscs.service.scheduling;

import org.apache.commons.logging.*;
import org.quartz.*;
import org.springframework.scheduling.quartz.*;
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
public class UserTopFileJob
    extends QuartzJobBean {

  private static final Log logger = LogFactory.getLog(UserTopFileJob.class);

  private UserTopService userTopService;

  public UserTopFileJob() {
  }

  /**
   * executeInternal
   *
   * @param jobExecutionContext JobExecutionContext
   * @throws JobExecutionException
   * @todo Implement this org.springframework.scheduling.quartz.QuartzJobBean method
   */
  protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    logger.info("Create User Top File Start");
    try {
      this.getUserTopService().createUserTopFile();
    }
    catch (BbscsException ex) {
      logger.error(ex);
    }
    logger.info("Create User Top File End");
  }

  public UserTopService getUserTopService() {
    return userTopService;
  }

  public void setUserTopService(UserTopService userTopService) {
    this.userTopService = userTopService;
  }
}
