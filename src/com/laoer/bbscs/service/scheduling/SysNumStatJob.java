package com.laoer.bbscs.service.scheduling;

import org.apache.commons.logging.*;
import org.quartz.*;
import org.springframework.scheduling.quartz.*;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.comm.*;
import java.util.*;
import com.laoer.bbscs.bean.SysNumStat;
import com.laoer.bbscs.exception.*;

/**
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 *
 * @version 7.0
 */
public class SysNumStatJob extends QuartzJobBean {

	private static final Log logger = LogFactory.getLog(SysNumStatJob.class);

	private SysNumStatService sysNumStatService;

	private UserService userService;

	private ForumService forumService;

	private ForumService forumHistoryService;

	public SysNumStatJob() {
	}

	/**
	 * executeInternal
	 *
	 * @param jobExecutionContext
	 *            JobExecutionContext
	 * @throws JobExecutionException
	 * @todo Implement this org.springframework.scheduling.quartz.QuartzJobBean
	 *       method
	 */
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		java.util.Calendar cld = java.util.Calendar.getInstance();
		cld.setTime(new Date());
		cld.add(java.util.Calendar.DATE, -1);
		logger.info(Util.formatDate(cld.getTime()) + " 统计数据");

		SysNumStat snsnow = new SysNumStat();
		snsnow.setCreateTime(System.currentTimeMillis());
		long usernumall = this.getUserService().getAllUserNum();
		logger.info("社区人数:" + usernumall);
		long postNum = this.getForumService().getForumNum(-1) + this.getForumHistoryService().getForumNum(-1);
		long postMainNum = this.getForumService().getForumMainNum(-1)
				+ this.getForumHistoryService().getForumMainNum(-1);
		logger.info("主题数:" + postMainNum);
		logger.info("帖子总数:" + postNum);
		snsnow.setNum0(usernumall);
		snsnow.setNum1(postMainNum);
		snsnow.setNum2(postNum);
		snsnow.setNum3(0);
		snsnow.setNum4(0);
		snsnow.setNum5(0);

		cld.add(java.util.Calendar.DATE, -1);
		logger.info("查找 " + Util.formatDate(cld.getTime()) + " 数据");
		SysNumStat sns = this.getSysNumStatService().findSysNumStatByRecDate(Util.formatDate4(cld.getTime()));

		if (sns == null) {
			snsnow.setNumInc0(0);
			snsnow.setNumInc1(0);
			snsnow.setNumInc2(0);
			snsnow.setNumInc3(0);
			snsnow.setNumInc4(0);
			snsnow.setNumInc5(0);
		} else {
			snsnow.setNumInc0(usernumall - sns.getNum0());
			snsnow.setNumInc1(postMainNum - sns.getNum1());
			snsnow.setNumInc2(postNum - sns.getNum2());
			snsnow.setNumInc3(0);
			snsnow.setNumInc4(0);
			snsnow.setNumInc5(0);
		}

		cld.add(java.util.Calendar.DATE, 1);
		logger.info("记录 " + Util.formatDate(cld.getTime()) + " 数据");
		try {
			this.getSysNumStatService().saveSysNumStat(Util.formatDate4(cld.getTime()), snsnow);
		} catch (BbscsException ex) {
			logger.error(ex);
		}
	}

	public ForumService getForumHistoryService() {
		return forumHistoryService;
	}

	public ForumService getForumService() {
		return forumService;
	}

	public SysNumStatService getSysNumStatService() {
		return sysNumStatService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setForumHistoryService(ForumService forumHistoryService) {
		this.forumHistoryService = forumHistoryService;
	}

	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}

	public void setSysNumStatService(SysNumStatService sysNumStatService) {
		this.sysNumStatService = sysNumStatService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
