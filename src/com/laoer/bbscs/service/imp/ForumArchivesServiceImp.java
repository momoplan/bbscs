/**
 *
 */
package com.laoer.bbscs.service.imp;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.bean.Forum;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.comm.OrderObj;
import com.laoer.bbscs.comm.Util;
import com.laoer.bbscs.dao.ForumArchivesDAO;
import com.laoer.bbscs.dao.ForumDAO;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.BoardService;

import com.laoer.bbscs.service.ForumArchivesService;
import com.laoer.bbscs.service.config.ForumConfig;
import com.laoer.bbscs.service.config.SysConfig;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

/**
 * @author GongTianyi
 *
 */
public class ForumArchivesServiceImp implements ForumArchivesService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ForumArchivesServiceImp.class);

	private ForumDAO forumHistoryDAO;

	private ForumArchivesDAO forumArchivesDAO;

	private BoardService boardService;

	private Configuration tempConfiguration;

	private ForumConfig forumConfig;

	private SysConfig sysConfig;

	public ForumArchivesDAO getForumArchivesDAO() {
		return forumArchivesDAO;
	}

	public void setForumArchivesDAO(ForumArchivesDAO forumArchivesDAO) {
		this.forumArchivesDAO = forumArchivesDAO;
	}

	public ForumDAO getForumHistoryDAO() {
		return forumHistoryDAO;
	}

	public void setForumHistoryDAO(ForumDAO forumHistoryDAO) {
		this.forumHistoryDAO = forumHistoryDAO;
	}

	public BoardService getBoardService() {
		return boardService;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	public Configuration getTempConfiguration() {
		return tempConfiguration;
	}

	public void setTempConfiguration(Configuration tempConfiguration) {
		this.tempConfiguration = tempConfiguration;
	}

	public ForumConfig getForumConfig() {
		return forumConfig;
	}

	public void setForumConfig(ForumConfig forumConfig) {
		this.forumConfig = forumConfig;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	/*
	 * （非 Javadoc）
	 *
	 * @see com.laoer.bbscs.service.ForumArchivesService#createForumArchives()
	 */
	public void createForumArchives() throws BbscsException {
		for (int y = 12; y >= 3; y--) {
			Calendar cld = Calendar.getInstance();
			cld.set(Calendar.HOUR_OF_DAY, 0);
			cld.set(Calendar.MINUTE, 0);
			cld.set(Calendar.SECOND, 0);
			cld.set(Calendar.MILLISECOND, 0);
			cld.add(Calendar.MONTH, -y);
			logger.info("计算时间点:" + Util.formatDateTime(cld.getTime()));
			long atime = cld.getTimeInMillis();
			cld.add(Calendar.MONTH, -1);
			String month = Util.formatDate(cld.getTime(), "yyyy-MM");
			logger.info("存档月份:" + month);

			List blist = this.getBoardService().findBoardsByParentID(0, 1, -1, Constant.FIND_BOARDS_BY_ORDER);
			for (int i = 0; i < blist.size(); i++) {
				Board b = (Board) blist.get(i);
				if (b.getBoardType() == 3) {
					this.createForumArchivesFile(b, atime, month);
				}
				List bclist = this.getBoardService().findBoardsByParentID(b.getId(), 1, -1,
						Constant.FIND_BOARDS_BY_ORDER);
				for (int j = 0; j < bclist.size(); j++) {
					Board bc = (Board) bclist.get(j);
					if (bc.getBoardType() == 3) {
						this.createForumArchivesFile(bc, atime, month);
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void createForumArchivesFile(Board b, long atime, String month) throws BbscsException {
		if (!BBSCSUtil.ArchivesMonthInFile(b.getId(), month)) {
			try {
				this.getTempConfiguration().setDirectoryForTemplateLoading(
						new File(Constant.ROOTPATH + Constant.FTL_PATH));
				this.getTempConfiguration().setDefaultEncoding(Constant.CHARSET);
				this.getTempConfiguration().setNumberFormat("0.##########");
				Locale locale = new Locale("zh", "CN");
				this.getTempConfiguration().setLocale(locale);
				Template temp = this.getTempConfiguration().getTemplate("archivesPostMain.ftl");

				long total = this.getForumHistoryDAO().getForumNumBeforeDate(b.getId(), atime);
				if (total > 0) {
					BBSCSUtil.writeMonthToFile(b.getId(), month);
				}

				int allPage = (int) Math.ceil((total + 40 - 1) / 40);

				logger.info(b.getBoardName() + " total:" + total + " allPage:" + allPage);

				for (int i = 1; i <= allPage; i++) {
					int spage = (i - 1) * 40;
					List l = this.getForumHistoryDAO().findForumsBeforeDate(b.getId(), atime, spage, 40);
					List mainlist = new ArrayList();
					for (int j = 0; j < l.size(); j++) {
						Forum f = (Forum) l.get(j);
						Map pmap = new HashMap();
						pmap.put("id", f.getMainID());
						pmap.put("title", f.getTitle());
						pmap.put("renum", f.getReNum());
						pmap.put("username", f.getUserName());
						pmap.put("postTime", Util.formatDate6(new Date(f.getPostTime())));
						pmap.put("postdir", Util.formatDate4(new Date(f.getPostTime())) + "/" + (f.getPostTime() % 100)
								+ "/");
						mainlist.add(pmap);

						List fl = this.getForumHistoryDAO().findForumsTopic(b.getId(), f.getMainID(), 0, 0, -1,
								new OrderObj[] { new OrderObj("postTime", Constant.ORDER_ASC) });
						List topiclist = new ArrayList();
						for (int x = 0; x < fl.size(); x++) {
							Forum tf = (Forum) fl.get(x);
							Map tmap = new HashMap();
							tmap.put("id", tf.getMainID());
							tmap.put("title", tf.getTitle());
							tmap.put("username", tf.getUserName());
							tmap.put("posttime", Util.formatDateTime(new Date(tf.getPostTime())));
							tmap.put("content", this.getForumDetail(tf, b).toString());
							topiclist.add(tmap);
							if (x != 0) {
								this.forumArchivesDAO.saveForumArchives(b.getId(), tf);
								this.getForumHistoryDAO().removeForum(tf);
							}
						}

						Template temptopic = this.getTempConfiguration().getTemplate("archivesPostTopic.ftl");

						OutputStream out = new FileOutputStream(BBSCSUtil.getArchivesPostTopicPath(b.getId(), month, f
								.getPostTime())
								+ f.getMainID() + ".html");
						Writer writer = new OutputStreamWriter(out, Constant.CHARSET);
						Map root = new HashMap();
						root.put("topoctitle", f.getTitle());
						root.put("topiclist", topiclist);

						temptopic.process(root, writer);
						writer.flush();

						this.forumArchivesDAO.saveForumArchives(b.getId(), f);
						this.getForumHistoryDAO().removeForum(f);
					}
					StringBuffer sb = new StringBuffer();
					for (int x = 1; x <= allPage; x++) {
						sb.append("[<a href=\"");
						sb.append(x);
						sb.append(".html\">");
						if (x == i) {
							sb.append("<strong>");
							sb.append(x);
							sb.append("</strong>");
						} else {
							sb.append(x);
						}
						sb.append("</a>] ");
					}

					OutputStream out = new FileOutputStream(BBSCSUtil.getArchivesPostMainListPath(b.getId(), month) + i
							+ ".html");
					Writer writer = new OutputStreamWriter(out, Constant.CHARSET);
					Map root = new HashMap();
					root.put("boardName", b.getBoardName());
					root.put("month", month);
					root.put("mainlist", mainlist);
					root.put("pagebreak", sb.toString());

					temp.process(root, writer);
					writer.flush();
				}

			} catch (Exception e) {
				logger.error(e);
				throw new BbscsException(e);
			}
		}
	}

	private StringBuffer getAttachFile(Forum f) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id=\"upfile");
		sb.append(f.getId());
		sb.append("\" class=\"font5\">");
		for (int i = 0; i < f.getAttachFileName().size(); i++) {
			String fileName = (String) (f.getAttachFileName().get(i));
			sb.append("<img src=\"../../../../../images/icons/");
			sb.append(BBSCSUtil.getFileTypeIcon(FilenameUtils.getExtension(fileName)));
			sb.append("\" align=\"absmiddle\"/> ");

			sb.append("附件");
			sb.append("[");
			sb.append(i + 1);
			sb.append("]");

			if (FilenameUtils.isExtension(fileName, this.getSysConfig().getAttachImgTypes())) {
				sb.append("<div class=\"upfile1\">");
				sb.append("<a href=\"../../../../../");
				sb.append(BBSCSUtil.getUpFileWebPath(f.getBoardID(), f.getPostTime()));
				sb.append(fileName);
				sb.append("\" target=\"_blank\">");
				sb.append("<img src=\"../../../../../");
				sb.append(BBSCSUtil.getUpFileWebPath(f.getBoardID(), f.getPostTime()));
				sb.append(fileName);
				// if (sysConfig.getReduceAttachImg() == 1) {
				// sb.append(Constant.IMG_SMALL_FILEPREFIX);
				// }
				sb.append("\" border=\"0\" alt=\"\"/></a>");
				sb.append("</div>");
			} else if (BBSCSUtil.isFlashFile(fileName)) {
				sb.append("<div class=\"upfile1\">");
				sb
						.append("<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0\">");
				sb.append("<param name=\"movie\" value=\"");
				sb.append(BBSCSUtil.getUpFileWebPath(f.getBoardID(), f.getPostTime()));
				sb.append(fileName);
				sb.append("\">");
				sb.append("<param name=\"quality\" value=\"high\">");
				sb.append("<embed src=\"");
				sb.append(BBSCSUtil.getUpFileWebPath(f.getBoardID(), f.getPostTime()));
				sb.append(fileName);
				sb
						.append("\" quality=\"high\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" type=\"application/x-shockwave-flash\"></embed></object>");
				sb.append("</div>");
			} else {
				sb.append("<div class=\"upfile1\">");
				sb.append("<a href=\"");
				sb.append(BBSCSUtil.getUpFileWebPath(f.getBoardID(), f.getPostTime()));
				sb.append(fileName);
				sb.append("\" target=\"_blank\">");
				sb.append("附件下载");
				// sb.append(fileName);
				sb.append("</a>");
				sb.append("</div>");
			}
		}
		sb.append("</div>");
		return sb;
	}

	private StringBuffer getForumDetail(Forum f, Board board) {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotBlank(f.getQuoteText())) {
			sb.append("<blockquote class=\"quote1\"><strong>");
			sb.append("引用");
			sb.append(":</strong><br />");
			if (f.getQuoteEditType() == 0) {
				sb.append(BBSCSUtil.filterText(f.getQuoteText(), (board.getAllowHTML() == 1),
						(board.getAllowUBB() == 1), true));
			} else {
				sb.append(BBSCSUtil.filterScript(f.getQuoteText()));
			}
			sb.append("</blockquote>");
		}
		if (f.getHaveAttachFile() != 0 && f.getAttachFileName() != null && !f.getAttachFileName().isEmpty()) {
			sb.append(this.getAttachFile(f));
		} else {
			sb.append("<div id=\"upfile");
			sb.append(f.getId());
			sb.append("\" class=\"font5\" style=\"display:none\"></div>");
		}

		if (f.getIsVote() == 0) {
			String detail = this.getForumDetail(f);
			if (f.getEditType() == 0) {
				sb.append(BBSCSUtil.filterText(detail, (board.getAllowHTML() == 1), (board.getAllowUBB() == 1), true));
			} else {
				sb.append(BBSCSUtil.filterScript(detail));
			}
		} else {
			if (f.getEditType() == 0) {
				sb.append(BBSCSUtil.filterText(f.getDetail(), (board.getAllowHTML() == 1), (board.getAllowUBB() == 1),
						true));
			} else {
				sb.append(BBSCSUtil.filterScript(f.getDetail()));
			}
		}
		return sb;
	}

	private String getForumDetail(Forum f) {
		if (Constant.POST_STORAGE_MODE == 0) {
			return f.getDetail();
		} else {
			String detail = "";
			File postFile = new File(this.getForumConfig().getForumPath(f.getBoardID(), f.getPostTime())
					+ f.getDetail());
			try {
				detail = FileUtils.readFileToString(postFile, Constant.CHARSET);

			} catch (IOException e) {
				detail = "";
			}
			return detail;
		}
	}

}
