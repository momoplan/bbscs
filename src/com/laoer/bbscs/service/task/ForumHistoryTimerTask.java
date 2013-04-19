package com.laoer.bbscs.service.task;

import java.util.*;

import com.laoer.bbscs.service.*;
import com.laoer.bbscs.service.config.SysConfig;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import com.laoer.bbscs.exception.*;

/**
 * <p>
 * Title: TianyiBBS
 * </p>
 *
 * <p>
 * Description: BBSCS
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 *
 * <p>
 * Company: Laoer.com
 * </p>
 *
 * @author Laoer
 * @version 7.0
 */
public class ForumHistoryTimerTask extends TimerTask {

	private static final Log logger = LogFactory.getLog(ForumHistoryTimerTask.class);

	private ForumService forumService;

	private SysConfig sysConfig;

	public ForumHistoryTimerTask() {
	}

	/**
	 * When an object implementing interface <code>Runnable</code> is used to
	 * create a thread, starting the thread causes the object's <code>run</code>
	 * method to be called in that separately executing thread.
	 *
	 * @todo Implement this java.lang.Runnable method
	 */
	public void run() {
		// long atime = System.currentTimeMillis() - 7776000000l; // 90天时间
		long htime = (long) (this.getSysConfig().getPostToHistoryDay()) * 24l * 3600000l;
		long atime = System.currentTimeMillis() - htime;
		try {
			logger.info("ForumHistory Move Start...");
			logger.info("htime:" + htime + " atime:" + atime);
			this.getForumService().removeToHistory(atime);
			logger.info("ForumHistory Move End");
		} catch (BbscsException ex) {
			logger.error(ex);
		}
	}

	public ForumService getForumService() {
		return forumService;
	}

	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

}
