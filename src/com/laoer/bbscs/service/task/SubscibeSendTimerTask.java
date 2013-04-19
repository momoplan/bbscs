package com.laoer.bbscs.service.task;

import java.util.*;

import org.apache.commons.logging.*;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.comm.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.service.config.*;
import com.laoer.bbscs.service.mail.TemplateMail;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;

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
public class SubscibeSendTimerTask extends TimerTask {

	private static final Log logger = LogFactory.getLog(SubscibeSendTimerTask.class);

	private SubscibeService subscibeService;

	private SysQueue subscibeQueue;

	private NoteFactory noteFactory;

	private NoteService noteService;

	// private HtmlEmail htmlEmail;

	// private Configuration tempConfiguration;

	private SysConfig sysConfig;

	private BoardService boardService;

	private ResourceBundleMessageSource messageSource;

	private TemplateMail templateMail;

	private ForumService forumService;

	public SubscibeSendTimerTask() {

	}

	/**
	 * When an object implementing interface <code>Runnable</code> is used to
	 * create a thread, starting the thread causes the object's <code>run</code>
	 * method to be called in that separately executing thread.
	 *
	 * @todo Implement this java.lang.Runnable method
	 */
	public void run() {
		logger.info("Send Subscibe, subscibeQueue's Num:" + this.getSubscibeQueue().size());
		while (this.getSubscibeQueue().size() > 0) {
			Forum f = (Forum) this.getSubscibeQueue().get();
			if (f != null) {
				List sendlist = this.getSubscibeService().findSubscibesSend(f.getMainID(), f.getBoardID());
				Date sdate = new Date();
				for (int i = 0; i < sendlist.size(); i++) {
					Subscibe subs = (Subscibe) sendlist.get(i);
					if (!subs.getUserID().equals(f.getUserID())) {
						if (subs.getMsginform() == 1) {
							Note inboxNote = this.getNoteFactory().getInstance(subs.getUserID());
							inboxNote.setCreateTime(sdate);
							inboxNote.setFromID(f.getUserID());
							inboxNote.setFromNickName(f.getNickName());
							inboxNote.setFromUserName(f.getUserName());
							inboxNote.setIsNew(1);
							inboxNote.setIsRe(0);
							inboxNote.setNeedRe(0);
							String t = "<a href=\""
									+ BBSCSUtil.getActionMappingURLWithoutPrefix("read?action=topic&bid="
											+ f.getBoardID() + "&id=" + f.getMainID()) + "\">" + f.getTitle() + "</a>";
							inboxNote.setNoteContext(this.getMessageSource().getMessage("subs.content",
									new String[] { t }, BBSCSUtil.getLocale(subs.getUserLocale())));
							inboxNote.setNoteTitle(this.getMessageSource().getMessage("subs.title",
									new String[] { BBSCSUtil.getSpeShortString(f.getTitle(), 40, "...") },
									BBSCSUtil.getLocale(subs.getUserLocale())));
							inboxNote.setNoteType(1);
							inboxNote.setToID(subs.getUserID());
							inboxNote.setToNickName(subs.getNickName());
							inboxNote.setToUserName(subs.getUserName());
							inboxNote.setSysMsg(1);
							try {
								this.getNoteService().saveNote(inboxNote);
							} catch (BbscsException ex) {
								logger.error(ex);
							}
						}
						if (subs.getEmailinform() == 1) {
							if (StringUtils.isNotBlank(subs.getUserEmail()) && this.getSysConfig().getUseEmail() == 1) {
								Board board = this.getBoardService().getBoardByID(f.getBoardID());
								if (board != null) {
									try {

										String detail = this.getForumService().getForumDetail(f, false);
										if (f.getEditType() == 0) {
											detail = BBSCSUtil.filterText(detail, (board.getAllowHTML() == 1),
													(board.getAllowUBB() == 1), true);

										} //else {detail = f.getDetail();}

										String subject = f.getTitle();
										Map<String, String> root = new HashMap<String, String>();
										root.put("website", this.getSysConfig().getWebName());
										root.put("title", f.getTitle());
										root.put("detail", detail);
										root.put("url", "");
										this.getTemplateMail().sendMailFromTemplate(subs.getUserEmail(), subject,
												"mailSend.ftl", root, BBSCSUtil.getLocale(subs.getUserLocale()));

									} catch (Exception ex7) {
										ex7.printStackTrace();
										logger.error(ex7);
									}
								}
							}
						}
					}
				}
			}
		}
		logger.info("Send Subscibe End");

	}

	public SubscibeService getSubscibeService() {
		return subscibeService;
	}

	public SysQueue getSubscibeQueue() {
		return subscibeQueue;
	}

	public NoteFactory getNoteFactory() {
		return noteFactory;
	}

	public NoteService getNoteService() {
		return noteService;
	}

	// public HtmlEmail getHtmlEmail() {
	// return htmlEmail;
	// }

	// public Configuration getTempConfiguration() {
	// return tempConfiguration;
	// }

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public BoardService getBoardService() {
		return boardService;
	}

	public void setSubscibeService(SubscibeService subscibeService) {
		this.subscibeService = subscibeService;
	}

	public void setSubscibeQueue(SysQueue subscibeQueue) {
		this.subscibeQueue = subscibeQueue;
	}

	public void setNoteFactory(NoteFactory noteFactory) {
		this.noteFactory = noteFactory;
	}

	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}

	// public void setHtmlEmail(HtmlEmail htmlEmail) {
	// this.htmlEmail = htmlEmail;
	// }

	// public void setTempConfiguration(Configuration tempConfiguration) {
	// this.tempConfiguration = tempConfiguration;
	// }

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	public ResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(ResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public TemplateMail getTemplateMail() {
		return templateMail;
	}

	public void setTemplateMail(TemplateMail templateMail) {
		this.templateMail = templateMail;
	}

	public ForumService getForumService() {
		return forumService;
	}

	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}

}
