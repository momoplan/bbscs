package com.laoer.bbscs.service.scheduling;

import org.apache.log4j.Logger;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.ForumArchivesService;

public class ForumArchivesJob extends QuartzJobBean {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ForumArchivesJob.class);

	private ForumArchivesService forumArchivesService;

	public ForumArchivesService getForumArchivesService() {
		return forumArchivesService;
	}

	public void setForumArchivesService(ForumArchivesService forumArchivesService) {
		this.forumArchivesService = forumArchivesService;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		try {
			logger.info("###存档###");
			this.getForumArchivesService().createForumArchives();
			logger.info("###存档结束###");
		} catch (BbscsException e) {
			logger.error(e);
		}

	}

}
