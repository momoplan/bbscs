package com.laoer.bbscs.web.taglib;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.IteratorStatus;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.bean.Forum;
import com.laoer.bbscs.bean.ForumBuy;
import com.laoer.bbscs.bean.UserInfoSimple;
import com.laoer.bbscs.bean.Vote;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.comm.Util;
import com.laoer.bbscs.service.Cache;
import com.laoer.bbscs.service.ForumService;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.VoteItemService;
import com.laoer.bbscs.service.VoteService;
import com.laoer.bbscs.service.config.ForumConfig;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.servlet.UserCookie;
import com.laoer.bbscs.web.servlet.UserSession;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.TextUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ForumComponent extends Component {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(ForumComponent.class);

	private PageContext pageContext;

	private HttpServletRequest request;

	private HttpServletResponse response;

	private String forumValue = "";

	private String boardValue = "%{board}";

	private String itemClass = "font2";

	private String inPagesValue = "%{inpages}";

	private String currentPageValue = "%{page}";

	private String currentActionValue = "%{action}";

	private String tagIdValue = "%{tagId}";

	private String type = "";

	private String totalnumValue = "%{totalnum}";

	protected String indexValue = "%{rowstatus}";

	private WebApplicationContext wc = null;

	public ForumComponent(ValueStack stack, PageContext pageContext, HttpServletRequest request,
			HttpServletResponse response) {
		super(stack);
		this.pageContext = pageContext;
		this.request = request;
		this.response = response;
		this.wc = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
	}

	@SuppressWarnings("unchecked")
	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		if (forumValue == null) {
			forumValue = "top";
		} else if (altSyntax()) {
			if (forumValue.startsWith("%{") && forumValue.endsWith("}")) {
				forumValue = forumValue.substring(2, forumValue.length() - 1);
			}
		}
		if (boardValue == null) {
			boardValue = "top";
		} else if (altSyntax()) {
			if (boardValue.startsWith("%{") && boardValue.endsWith("}")) {
				boardValue = boardValue.substring(2, boardValue.length() - 1);
			}
		}
		if (inPagesValue == null) {
			inPagesValue = "top";
		} else if (altSyntax()) {
			if (inPagesValue.startsWith("%{") && inPagesValue.endsWith("}")) {
				inPagesValue = inPagesValue.substring(2, inPagesValue.length() - 1);
			}
		}
		if (currentPageValue == null) {
			currentPageValue = "top";
		} else if (altSyntax()) {
			if (currentPageValue.startsWith("%{") && currentPageValue.endsWith("}")) {
				currentPageValue = currentPageValue.substring(2, currentPageValue.length() - 1);
			}
		}
		if (currentActionValue == null) {
			currentActionValue = "top";
		} else if (altSyntax()) {
			if (currentActionValue.startsWith("%{") && currentActionValue.endsWith("}")) {
				currentActionValue = currentActionValue.substring(2, currentActionValue.length() - 1);
			}
		}
		if (tagIdValue == null) {
			tagIdValue = "top";
		} else if (altSyntax()) {
			if (tagIdValue.startsWith("%{") && tagIdValue.endsWith("}")) {
				tagIdValue = tagIdValue.substring(2, tagIdValue.length() - 1);
			}
		}
		if (totalnumValue == null) {
			totalnumValue = "top";
		} else if (altSyntax()) {
			if (totalnumValue.startsWith("%{") && totalnumValue.endsWith("}")) {
				totalnumValue = totalnumValue.substring(2, totalnumValue.length() - 1);
			}
		}
		if (indexValue == null) {
			indexValue = "top";
		} else if (altSyntax()) {
			if (indexValue.startsWith("%{") && indexValue.endsWith("}")) {
				indexValue = indexValue.substring(2, indexValue.length() - 1);
			}
		}

		// WebApplicationContext wc =
		// WebApplicationContextUtils.getWebApplicationContext(this.pageContext
		// .getServletContext());
		SysConfig sysConfig = (SysConfig) wc.getBean("sysConfig");
		ResourceBundleMessageSource messageSource = (ResourceBundleMessageSource) wc.getBean("messageSource");
		StringBuffer sb = new StringBuffer();

		Object forumObj = this.getStack().findValue(forumValue);
		if (forumObj == null) {
			return result;
		}
		Forum f = (Forum) forumObj;

		if (type.equalsIgnoreCase("floor")) {
			int index = 0;
			Object statusObj = this.getStack().findValue(indexValue);
			if (statusObj != null) {
				index = ((IteratorStatus) statusObj).getIndex();
			}
			int inpages = 1;
			Object inpagesObj = this.getStack().findValue(inPagesValue);
			if (inpagesObj != null) {
				inpages = ((Integer) inpagesObj).intValue();
			}
			UserCookie uc = new UserCookie(request, response, sysConfig);
			int perNum = this.getUserPostPerNum(uc.getPostPerNum(), sysConfig.getPostPerPage());
			int floor = (inpages - 1) * perNum + index;
			if (floor == 0) {
				sb.append(messageSource.getMessage("forum.floor0", null, request.getLocale()));
			} else {
				sb.append(messageSource.getMessage("forum.floor1", new String[] { String.valueOf(floor) }, request
						.getLocale()));
			}
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("face")) {
			if (f.getFace() == 0) {
				sb.append("<img src=\"");
				sb.append(sysConfig.getPostDefFaceImg());
				sb.append("\" align=\"absmiddle\"/>");
			} else {
				sb.append("<img src=\"images/");
				sb.append(f.getFace());
				sb.append(".gif\" align=\"absmiddle\"/>");
			}
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("title")) {
			Object boardObj = this.getStack().findValue(this.boardValue);
			int boardType = 3;
			Board board = (Board) boardObj;
			boardType = board.getBoardType();
			Object forumCurrentPageObj = this.getStack().findValue(this.currentPageValue);
			int fcpage = 1;
			if (forumCurrentPageObj != null) {
				fcpage = ((Integer) forumCurrentPageObj).intValue();
			}

			String fcaction = "index";
			Object fcactionObj = this.getStack().findValue(this.currentActionValue);
			if (fcactionObj != null) {
				fcaction = (String) fcactionObj;
			}

			String tagId = "0";
			Object tagIdObj = this.getStack().findValue(this.tagIdValue);
			if (tagIdObj != null) {
				tagId = (String) tagIdObj;
			}

			if (boardType == 2) {
				if (board.getId().longValue() != f.getBoardID()) {
					sb.append("[");
					sb.append(f.getBoardName());
					sb.append("] ");
				}
			}
			if (!f.getTagID().equals("0") && tagId.equals("0")) {
				sb.append("[");
				sb.append(f.getTagName());
				sb.append("] ");
			}
			if (f.getPostType() != 0) {
				if (f.getPostType() == 1) {
					sb.append(messageSource.getMessage("post.firstpus", null, this.request.getLocale()));
					sb.append(" ");
					// sb.append("[原创] ");
				}
				if (f.getPostType() == 2) {
					sb.append(messageSource.getMessage("post.fw", null, this.request.getLocale()));
					sb.append(" ");
					// sb.append("[转载] ");
				}
			}
			if (f.getIsVote() == 1) {
				// sb.append("[投票] ");
				sb.append(messageSource.getMessage("post.vote.title", null, this.request.getLocale()));
				sb.append(" ");
			}
			if (f.getTitleColor() != 0) {
				sb.append("<a href=\"");
				if (Constant.USE_URL_REWRITE) {
					sb.append("read-topic-" + f.getBoardID() + "-" + f.getMainID() + "-" + tagId + "-" + fcpage + "-"
							+ fcaction + "-1" + ".html");
				} else {
					sb.append(BBSCSUtil.getActionMappingURL("/read?action=topic&id=" + f.getMainID() + "&bid="
							+ f.getBoardID() + "&fcpage=" + fcpage + "&fcaction=" + fcaction + "&tagId=" + tagId,
							request));
				}
				sb.append("\">");
				sb.append("<font color=\"");
				sb.append(Constant.TITLECOLOR[f.getTitleColor()]);
				sb.append("\"><strong>");
				sb.append(TextUtils.htmlEncode(f.getTitle()));
				sb.append("</strong></font>");
				sb.append("</a>");
			} else {
				sb.append("<a href=\"");
				if (Constant.USE_URL_REWRITE) {
					sb.append("read-topic-" + f.getBoardID() + "-" + f.getMainID() + "-" + tagId + "-" + fcpage + "-"
							+ fcaction + "-1" + ".html");
				} else {
					sb.append(BBSCSUtil.getActionMappingURL("/read?action=topic&id=" + f.getMainID() + "&bid="
							+ f.getBoardID() + "&fcpage=" + fcpage + "&fcaction=" + fcaction + "&tagId=" + tagId,
							request));
				}

				sb.append("\">");
				sb.append(TextUtils.htmlEncode(f.getTitle()));
				sb.append("</a>");
			}
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("titlehistory")) {
			Object boardObj = this.getStack().findValue(this.boardValue);
			int boardType = 3;
			Board board = (Board) boardObj;
			boardType = board.getBoardType();
			Object forumCurrentPageObj = this.getStack().findValue(this.currentPageValue);
			int fcpage = 1;
			if (forumCurrentPageObj != null) {
				fcpage = ((Integer) forumCurrentPageObj).intValue();
			}

			String fcaction = "index";
			Object fcactionObj = this.getStack().findValue(this.currentActionValue);
			if (fcactionObj != null) {
				fcaction = (String) fcactionObj;
			}

			String tagId = "0";
			Object tagIdObj = this.getStack().findValue(this.tagIdValue);
			if (tagIdObj != null) {
				tagId = (String) tagIdObj;
			}

			if (boardType == 2) {
				if (board.getId().longValue() != f.getBoardID()) {
					sb.append("[");
					sb.append(f.getBoardName());
					sb.append("] ");
				}
			}
			if (!f.getTagID().equals("0") && tagId.equals("0")) {
				sb.append("[");
				sb.append(f.getTagName());
				sb.append("] ");
			}
			if (f.getPostType() != 0) {
				if (f.getPostType() == 1) {
					sb.append(messageSource.getMessage("post.firstpus", null, this.request.getLocale()));
					sb.append(" ");
					// sb.append("[原创] ");
				}
				if (f.getPostType() == 2) {
					sb.append(messageSource.getMessage("post.fw", null, this.request.getLocale()));
					sb.append(" ");
					// sb.append("[转载] ");
				}
			}
			if (f.getIsVote() == 1) {
				// sb.append("[投票] ");
				sb.append(messageSource.getMessage("post.vote.title", null, this.request.getLocale()));
				sb.append(" ");
			}
			if (f.getTitleColor() != 0) {
				sb.append("<a href=\"");
				if (Constant.USE_URL_REWRITE) {
					sb.append("read-history-" + f.getBoardID() + "-" + f.getMainID() + "-" + tagId + "-" + fcpage + "-"
							+ fcaction + "-1" + ".html");
				} else {
					sb.append(BBSCSUtil.getActionMappingURL("/read?action=history&id=" + f.getMainID() + "&bid="
							+ f.getBoardID() + "&fcpage=" + fcpage + "&fcaction=" + fcaction + "&tagId=" + tagId,
							request));
				}
				sb.append("\">");
				sb.append("<font color=\"");
				sb.append(Constant.TITLECOLOR[f.getTitleColor()]);
				sb.append("\"><strong>");
				sb.append(TextUtils.htmlEncode(f.getTitle()));
				sb.append("</strong></font>");
				sb.append("</a>");
			} else {
				sb.append("<a href=\"");
				if (Constant.USE_URL_REWRITE) {
					sb.append("read-history-" + f.getBoardID() + "-" + f.getMainID() + "-" + tagId + "-" + fcpage + "-"
							+ fcaction + "-1" + ".html");
				} else {
					sb.append(BBSCSUtil.getActionMappingURL("/read?action=history&id=" + f.getMainID() + "&bid="
							+ f.getBoardID() + "&fcpage=" + fcpage + "&fcaction=" + fcaction + "&tagId=" + tagId,
							request));
				}

				sb.append("\">");
				sb.append(TextUtils.htmlEncode(f.getTitle()));
				sb.append("</a>");
			}
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("titlemanage")) {
			Object boardObj = this.getStack().findValue(this.boardValue);
			int boardType = 3;
			Board board = (Board) boardObj;
			boardType = board.getBoardType();

			int fcpage = 1;
			String fcaction = "index";
			String tagId = "0";

			if (boardType == 2) {
				if (board.getId().longValue() != f.getBoardID()) {
					sb.append("[");
					sb.append(f.getBoardName());
					sb.append("] ");
				}
			}
			if (!f.getTagID().equals("0")) {
				sb.append("[");
				sb.append(f.getTagName());
				sb.append("] ");
			}

			if (f.getIsVote() == 1) {
				// sb.append("[投票] ");
				sb.append(messageSource.getMessage("post.vote.title", null, this.request.getLocale()));
				sb.append(" ");
			}

			if (f.getTitleColor() != 0) {
				sb.append("<a href=\"");
				if (Constant.USE_URL_REWRITE) {
					sb.append("read-topic-" + f.getBoardID() + "-" + f.getMainID() + "-" + tagId + "-" + fcpage + "-"
							+ fcaction + "-1" + ".html");
				} else {
					sb.append(BBSCSUtil.getActionMappingURL("/read?action=topic&id=" + f.getMainID() + "&bid="
							+ f.getBoardID() + "&fcpage=" + fcpage + "&fcaction=" + fcaction + "&tagId=" + tagId,
							request));
				}
				sb.append("\">");
				sb.append("<font color=\"");
				sb.append(Constant.TITLECOLOR[f.getTitleColor()]);
				sb.append("\"><strong>");
				sb.append(TextUtils.htmlEncode(f.getTitle()));
				sb.append("</strong></font>");
				sb.append("</a>");
			} else {
				sb.append("<a href=\"");
				if (Constant.USE_URL_REWRITE) {
					sb.append("read-topic-" + f.getBoardID() + "-" + f.getMainID() + "-" + tagId + "-" + fcpage + "-"
							+ fcaction + "-1" + ".html");
				} else {
					sb.append(BBSCSUtil.getActionMappingURL("/read?action=topic&id=" + f.getMainID() + "&bid="
							+ f.getBoardID() + "&fcpage=" + fcpage + "&fcaction=" + fcaction + "&tagId=" + tagId,
							request));
				}

				sb.append("\">");
				sb.append(TextUtils.htmlEncode(f.getTitle()));
				sb.append("</a>");
			}
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("titlewaste")) {
			Object boardObj = this.getStack().findValue(this.boardValue);
			int boardType = 3;
			Board board = (Board) boardObj;
			boardType = board.getBoardType();

			String tagId = "0";
			Object tagIdObj = this.getStack().findValue(this.tagIdValue);
			if (tagIdObj != null) {
				tagId = (String) tagIdObj;
			}

			if (boardType == 2) {
				if (board.getId().longValue() != f.getBoardID()) {
					sb.append("[");
					sb.append(f.getBoardName());
					sb.append("] ");
				}
			}
			if (!f.getTagID().equals("0") && tagId.equals("0")) {
				sb.append("[");
				sb.append(f.getTagName());
				sb.append("] ");
			}

			if (f.getIsVote() == 1) {
				// sb.append("[投票] ");
				sb.append(messageSource.getMessage("post.vote.title", null, this.request.getLocale()));
				sb.append(" ");
			}
			if (f.getTitleColor() != 0) {
				sb.append("<a href=\"");
				sb.append(BBSCSUtil.getActionMappingURL("/read?action=waste&mainid=" + f.getMainID() + "&bid="
						+ f.getBoardID(), request));
				sb.append("\">");
				sb.append("<font color=\"");
				sb.append(Constant.TITLECOLOR[f.getTitleColor()]);
				sb.append("\"><strong>");
				sb.append(TextUtils.htmlEncode(f.getTitle()));
				sb.append("</strong></font>");
				sb.append("</a>");
			} else {
				sb.append("<a href=\"");
				sb.append(BBSCSUtil.getActionMappingURL("/read?action=waste&mainid=" + f.getMainID() + "&bid="
						+ f.getBoardID(), request));
				sb.append("\">");
				sb.append(TextUtils.htmlEncode(f.getTitle()));
				sb.append("</a>");
			}
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("titleauditing") || type.equalsIgnoreCase("titleauditingattach")) {
			Object boardObj = this.getStack().findValue(this.boardValue);
			int boardType = 3;
			Board board = (Board) boardObj;
			boardType = board.getBoardType();

			String tagId = "0";
			Object tagIdObj = this.getStack().findValue(this.tagIdValue);
			if (tagIdObj != null) {
				tagId = (String) tagIdObj;
			}

			if (boardType == 2) {
				if (board.getId().longValue() != f.getBoardID()) {
					sb.append("[");
					sb.append(f.getBoardName());
					sb.append("] ");
				}
			}
			if (!f.getTagID().equals("0") && tagId.equals("0")) {
				sb.append("[");
				sb.append(f.getTagName());
				sb.append("] ");
			}

			if (f.getIsVote() == 1) {
				// sb.append("[投票] ");
				sb.append(messageSource.getMessage("post.vote.title", null, this.request.getLocale()));
				sb.append(" ");
			}

			if (f.getTitleColor() != 0) {
				sb.append("<a href=\"");
				if (type.equalsIgnoreCase("titleauditing")) {
					sb.append(BBSCSUtil.getActionMappingURL("/read?action=auditing&mainid=" + f.getMainID() + "&bid="
							+ f.getBoardID(), request));
				}
				if (type.equalsIgnoreCase("titleauditingattach")) {
					sb.append(BBSCSUtil.getActionMappingURL("/read?action=auditingAttach&mainid=" + f.getMainID()
							+ "&bid=" + f.getBoardID(), request));
				}
				sb.append("\">");
				sb.append("<font color=\"");
				sb.append(Constant.TITLECOLOR[f.getTitleColor()]);
				sb.append("\"><strong>");
				sb.append(TextUtils.htmlEncode(f.getTitle()));
				sb.append("</strong></font>");
				sb.append("</a>");
			} else {
				sb.append("<a href=\"");
				if (type.equalsIgnoreCase("titleauditing")) {
					sb.append(BBSCSUtil.getActionMappingURL("/read?action=auditing&mainid=" + f.getMainID() + "&bid="
							+ f.getBoardID(), request));
				}
				if (type.equalsIgnoreCase("titleauditingattach")) {
					sb.append(BBSCSUtil.getActionMappingURL("/read?action=auditingAttach&mainid=" + f.getMainID()
							+ "&bid=" + f.getBoardID(), request));
				}
				sb.append("\">");
				sb.append(TextUtils.htmlEncode(f.getTitle()));
				sb.append("</a>");
			}
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("titleitem")) {
			if (sysConfig.getUseLinkToPages() == 1) {
				UserCookie uc = new UserCookie(request, response, sysConfig);
				Object forumCurrentPageObj = this.getStack().findValue(this.currentPageValue);
				int fcpage = 1;
				if (forumCurrentPageObj != null) {
					fcpage = ((Integer) forumCurrentPageObj).intValue();
				}

				String fcaction = "index";
				Object fcactionObj = this.getStack().findValue(this.currentActionValue);
				if (fcactionObj != null) {
					fcaction = (String) fcactionObj;
				}

				String tagId = "0";
				Object tagIdObj = this.getStack().findValue(this.tagIdValue);
				if (tagIdObj != null) {
					tagId = (String) tagIdObj;
				}

				int totalNum = f.getReNum() + 1;
				int perNum = this.getUserPostPerNum(uc.getPostPerNum(), sysConfig.getPostPerPage());

				if (totalNum > perNum) {
					sb.append(" [<img src='images/multipage.gif' align='absmiddle'>");
					int totalPages = (int) Math.ceil((totalNum + perNum - 1) / perNum);
					String fileName = "";
					if (Constant.USE_URL_REWRITE) {
						fileName = "read-topic-" + f.getBoardID() + "-" + f.getId() + "-" + tagId + "-" + fcpage + "-"
								+ fcaction + "-{inpages}.html";
					} else {
						fileName = BBSCSUtil.getActionMappingURL("/read?action=topic&id=" + f.getId() + "&bid="
								+ f.getBoardID() + "&fcpage=" + fcpage + "&fcaction=" + fcaction + "&tagId=" + tagId
								+ "&inpages={inpages}", request);
					}
					for (int i = 1; i <= totalPages; i++) {
						if (i > sysConfig.getMaxMultiPage()) {
							sb.append(" ... <a href=\"");
							// sb.append(fileName);
							// sb.append(totalPages);
							sb.append(fileName.replaceAll("\\{inpages\\}", String.valueOf(totalPages)));
							sb.append("\">");
							sb.append(messageSource.getMessage("post.endpage", null, request.getLocale()));
							sb.append("</a>");
							break;
						}
						sb.append(" <a href=\"");
						// sb.append(fileName);
						// sb.append(i);
						sb.append(fileName.replaceAll("\\{inpages\\}", String.valueOf(i)));
						sb.append("\">");
						sb.append(i);
						sb.append("</a>");
					}
					sb.append("] ");
				}
			}

			sb.append("<a href=\"javascript:;\" onclick=\"viewSummary('");
			sb.append(f.getBoardID());
			sb.append("','");
			sb.append(f.getId());
			sb.append("');\">");
			sb.append("<img src=\"images/summary0.gif\" alt=\"");
			sb.append(messageSource.getMessage("post.summary", null, request.getLocale()));
			sb.append("\" border=\"0\" align=\"absmiddle\"/>");
			sb.append("</a>");

			sb.append("<span class=\"");
			sb.append(this.getItemClass());
			sb.append("\">");

			if (f.getHaveAttachFile() != 0 && f.getAttachFileName() != null && !f.getAttachFileName().isEmpty()) {
				sb.append(" [<img src=\"images/icons/");
				sb.append(BBSCSUtil
						.getFileTypeIcon(FilenameUtils.getExtension((String) (f.getAttachFileName().get(0)))));
				sb.append("\" align=\"absmiddle\"/> ");
				sb.append(messageSource.getMessage("post.attachfile", null, request.getLocale()));
				sb.append("]");
			}

			if (f.getIsTop() != 0) {
				sb.append(" ");
				sb.append(messageSource.getMessage("post.istop", null, request.getLocale()));
			}
			if (f.getIsLock() != 0) {
				sb.append(" ");
				sb.append(messageSource.getMessage("post.islock", null, request.getLocale()));
			}
			if (f.getElite() != 0) {
				sb.append(" ");
				sb.append(messageSource.getMessage("post.elite", null, request.getLocale()));
			}
			if (f.getCommend() != 0) {
				sb.append(" ");
				sb.append(messageSource.getMessage("post.commend", null, request.getLocale()));
			}
			sb.append("</span>");
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("titleitemhistory")) {
			if (sysConfig.getUseLinkToPages() == 1) {
				UserCookie uc = new UserCookie(request, response, sysConfig);
				Object forumCurrentPageObj = this.getStack().findValue(this.currentPageValue);
				int fcpage = 1;
				if (forumCurrentPageObj != null) {
					fcpage = ((Integer) forumCurrentPageObj).intValue();
				}

				String fcaction = "index";
				Object fcactionObj = this.getStack().findValue(this.currentActionValue);
				if (fcactionObj != null) {
					fcaction = (String) fcactionObj;
				}

				String tagId = "0";
				Object tagIdObj = this.getStack().findValue(this.tagIdValue);
				if (tagIdObj != null) {
					tagId = (String) tagIdObj;
				}

				int totalNum = f.getReNum() + 1;
				int perNum = this.getUserPostPerNum(uc.getPostPerNum(), sysConfig.getPostPerPage());

				if (totalNum > perNum) {
					sb.append(" [<img src='images/multipage.gif' align='absmiddle'>");
					int totalPages = (int) Math.ceil((totalNum + perNum - 1) / perNum);
					String fileName = "";
					if (Constant.USE_URL_REWRITE) {
						fileName = "read-history-" + f.getBoardID() + "-" + f.getId() + "-" + tagId + "-" + fcpage
								+ "-" + fcaction + "-{inpages}.html";
					} else {
						fileName = BBSCSUtil.getActionMappingURL("/read?action=history&id=" + f.getId() + "&bid="
								+ f.getBoardID() + "&fcpage=" + fcpage + "&fcaction=" + fcaction + "&tagId=" + tagId
								+ "&inpages={inpages}", request);
					}
					for (int i = 1; i <= totalPages; i++) {
						if (i > sysConfig.getMaxMultiPage()) {
							sb.append(" ... <a href=\"");
							// sb.append(fileName);
							// sb.append(totalPages);
							sb.append(fileName.replaceAll("\\{inpages\\}", String.valueOf(totalPages)));
							sb.append("\">");
							sb.append(messageSource.getMessage("post.endpage", null, request.getLocale()));
							sb.append("</a>");
							break;
						}
						sb.append(" <a href=\"");
						// sb.append(fileName);
						// sb.append(i);
						sb.append(fileName.replaceAll("\\{inpages\\}", String.valueOf(i)));
						sb.append("\">");
						sb.append(i);
						sb.append("</a>");
					}
					sb.append("] ");
				}
			}

			sb.append("<a href=\"javascript:;\" onclick=\"viewSummary('");
			sb.append(f.getBoardID());
			sb.append("','");
			sb.append(f.getId());
			sb.append("');\">");
			sb.append("<img src=\"images/summary0.gif\" alt=\"");
			sb.append(messageSource.getMessage("post.summary", null, request.getLocale()));
			sb.append("\" border=\"0\" align=\"absmiddle\"/>");
			sb.append("</a>");

			sb.append("<span class=\"");
			sb.append(this.getItemClass());
			sb.append("\">");

			if (f.getHaveAttachFile() != 0 && f.getAttachFileName() != null && !f.getAttachFileName().isEmpty()) {
				sb.append(" [<img src=\"images/icons/");
				sb.append(BBSCSUtil
						.getFileTypeIcon(FilenameUtils.getExtension((String) (f.getAttachFileName().get(0)))));
				sb.append("\" align=\"absmiddle\"/> ");
				sb.append(messageSource.getMessage("post.attachfile", null, request.getLocale()));
				sb.append("]");
			}

			if (f.getIsTop() != 0) {
				sb.append(" ");
				sb.append(messageSource.getMessage("post.istop", null, request.getLocale()));
			}
			if (f.getIsLock() != 0) {
				sb.append(" ");
				sb.append(messageSource.getMessage("post.islock", null, request.getLocale()));
			}
			if (f.getElite() != 0) {
				sb.append(" ");
				sb.append(messageSource.getMessage("post.elite", null, request.getLocale()));
			}
			if (f.getCommend() != 0) {
				sb.append(" ");
				sb.append(messageSource.getMessage("post.commend", null, request.getLocale()));
			}
			sb.append("</span>");
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("titleitemmanage")) {

			sb.append("<span class=\"");
			sb.append(this.getItemClass());
			sb.append("\">");

			if (f.getHaveAttachFile() != 0 && f.getAttachFileName() != null && !f.getAttachFileName().isEmpty()) {
				sb.append(" [<img src=\"images/icons/");
				sb.append(BBSCSUtil
						.getFileTypeIcon(FilenameUtils.getExtension((String) (f.getAttachFileName().get(0)))));
				sb.append("\" align=\"absmiddle\"/> ");
				sb.append(messageSource.getMessage("post.attachfile", null, request.getLocale()));
				sb.append("]");
			}

			if (f.getIsTop() != 0) {
				sb.append(" ");
				sb.append(messageSource.getMessage("post.istop", null, request.getLocale()));
			}
			if (f.getIsLock() != 0) {
				sb.append(" ");
				sb.append(messageSource.getMessage("post.islock", null, request.getLocale()));
			}
			if (f.getElite() != 0) {
				sb.append(" ");
				sb.append(messageSource.getMessage("post.elite", null, request.getLocale()));
			}
			if (f.getCommend() != 0) {
				sb.append(" ");
				sb.append(messageSource.getMessage("post.commend", null, request.getLocale()));
			}
			sb.append("</span>");
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("click")) {
			if (f.getClick() >= sysConfig.getForumHotViews()) {
				sb.append("<span class=\"");
				sb.append(this.getItemClass());
				sb.append("\">");
				sb.append(f.getClick());
				sb.append("</span>");
			} else {
				sb.append(f.getClick());
			}
			this.write(writer, sb.toString());
			return result;
		}
		if (type.equalsIgnoreCase("renum")) {
			sb.append("[");
			if (f.getReNum() > 0) {
				if (f.getReNum() >= sysConfig.getForumHotRes()) {
					sb.append("<span class=\"");
					sb.append(this.getItemClass());
					sb.append("\">");
					sb.append("+");
					sb.append(f.getReNum());
					sb.append("</span>");
				} else {
					sb.append("+");
					sb.append(f.getReNum());
				}
			} else {
				sb.append(f.getReNum());
			}
			sb.append("]");
			this.write(writer, sb.toString());
			return result;
		}
		if (type.equalsIgnoreCase("posttime")) {
			UserCookie uc = new UserCookie(request, response, sysConfig);
			if (sysConfig.getDateShowType() == 0) {
				sb.append(BBSCSUtil.formatDateTime(new Date(f.getPostTime()), sysConfig.getForumDateTimeFormat(), uc
						.getTimeZone()));
			} else {
				if (BBSCSUtil.isTodayTime(f.getPostTime(), uc.getTimeZone())) {
					sb.append(messageSource.getMessage("bbscs.today", null, request.getLocale()));
					sb.append(" ");
					sb.append(BBSCSUtil.formatDateTime(new Date(f.getPostTime()), sysConfig.getTimeFormat(), uc
							.getTimeZone()));
				} else if (BBSCSUtil.isYesterdayTime(f.getPostTime(), uc.getTimeZone())) {
					sb.append(messageSource.getMessage("bbscs.yesterday", null, request.getLocale()));
					sb.append(" ");
					sb.append(BBSCSUtil.formatDateTime(new Date(f.getPostTime()), sysConfig.getTimeFormat(), uc
							.getTimeZone()));
				} else {
					sb.append(BBSCSUtil.formatDateTime(new Date(f.getPostTime()), sysConfig.getForumDateTimeFormat(),
							uc.getTimeZone()));
				}
			}
			this.write(writer, sb.toString());
			return result;
		}
		if (type.equalsIgnoreCase("lasttime")) {
			UserCookie uc = new UserCookie(request, response, sysConfig);
			if (sysConfig.getDateShowType() == 0) {
				sb.append(BBSCSUtil.formatDateTime(new Date(f.getLastTime()), sysConfig.getForumDateTimeFormat(), uc
						.getTimeZone()));
			} else {
				if (BBSCSUtil.isTodayTime(f.getLastTime(), uc.getTimeZone())) {
					sb.append(messageSource.getMessage("bbscs.today", null, request.getLocale()));
					sb.append(" ");
					sb.append(BBSCSUtil.formatDateTime(new Date(f.getLastTime()), sysConfig.getTimeFormat(), uc
							.getTimeZone()));
				} else if (BBSCSUtil.isYesterdayTime(f.getLastTime(), uc.getTimeZone())) {
					sb.append(messageSource.getMessage("bbscs.yesterday", null, request.getLocale()));
					sb.append(" ");
					sb.append(BBSCSUtil.formatDateTime(new Date(f.getLastTime()), sysConfig.getTimeFormat(), uc
							.getTimeZone()));
				} else {
					sb.append(BBSCSUtil.formatDateTime(new Date(f.getLastTime()), sysConfig.getForumDateTimeFormat(),
							uc.getTimeZone()));
				}

			}
			this.write(writer, sb.toString());
			return result;
		}
		if (type.equalsIgnoreCase("deltime")) {
			UserCookie uc = new UserCookie(request, response, sysConfig);
			if (sysConfig.getDateShowType() == 0) {
				sb.append(BBSCSUtil.formatDateTime(new Date(f.getDelTime()), sysConfig.getForumDateTimeFormat(), uc
						.getTimeZone()));
			} else {
				if (BBSCSUtil.isTodayTime(f.getDelTime(), uc.getTimeZone())) {
					sb.append(messageSource.getMessage("bbscs.today", null, request.getLocale()));
					sb.append(" ");
					sb.append(BBSCSUtil.formatDateTime(new Date(f.getDelTime()), sysConfig.getTimeFormat(), uc
							.getTimeZone()));
				} else if (BBSCSUtil.isYesterdayTime(f.getDelTime(), uc.getTimeZone())) {
					sb.append(messageSource.getMessage("bbscs.yesterday", null, request.getLocale()));
					sb.append(" ");
					sb.append(BBSCSUtil.formatDateTime(new Date(f.getDelTime()), sysConfig.getTimeFormat(), uc
							.getTimeZone()));
				} else {
					sb.append(BBSCSUtil.formatDateTime(new Date(f.getDelTime()), sysConfig.getForumDateTimeFormat(), uc
							.getTimeZone()));
				}
			}
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("timeinpost")) {
			UserCookie uc = new UserCookie(request, response, sysConfig);
			sb.append(BBSCSUtil.formatDateTime(new Date(f.getPostTime()), sysConfig.getPostDateTimeFormat(), uc
					.getTimeZone()));

			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("detailsummary")) {
			Object boardObj = this.getStack().findValue(this.boardValue);
			Board board = (Board) boardObj;
			if (f.getIsVote() == 0) { // 不是投票帖
				if (f.getIsHidden() != 0) {
					sb.append(messageSource.getMessage("post.ishidden", null, request.getLocale()));

					// sb.append("这是一个隐藏帖！");
				} else {
					String detail = this.getForumDetail(f);
					if (f.getEditType() == 0) {
						detail = BBSCSUtil.getSpeShortString(detail, sysConfig.getPostViewLength(), "...");
						sb.append(BBSCSUtil.filterText(detail, (board.getAllowHTML() == 1), (board.getAllowUBB() == 1),
								true));
					} else {
						sb.append(BBSCSUtil.getSpeShortString(detail, sysConfig.getPostViewLength(), "..."));
					}
				}
			} else { // 投票帖
				sb.append(messageSource.getMessage("post.isvote", null, request.getLocale()));
			}
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("detail")) {
			Object usObj = this.getStack().findValue("userSession");
			UserSession us = (UserSession) usObj;
			Object boardObj = this.getStack().findValue(this.boardValue);
			Board board = (Board) boardObj;
			// String detail = f.getDetail();
			if (f.getIsVote() == 0) { // 不是投票帖
				if (f.getIsHidden() != 0) { // 是隐藏帖
					if (f.getUserID().equals(us.getId())
							|| us.isHaveBoardSpecialPermission(Constant.SPERMISSION_CAN_SEE_HIDDENDETAIL)) { // 发表人或有直接查看隐藏帖权限

						if (f.getIsHidden() == 1) {
							sb.append(messageSource.getMessage("post.hidden.needre0", null, request.getLocale()));
						}
						if (f.getIsHidden() == 2) {
							sb.append(messageSource.getMessage("post.hidden.buy", null, request.getLocale()));
						}
						if (f.getIsHidden() == 3) {
							sb.append(messageSource.getMessage("post.hidden.artnum", null, request.getLocale()));
						}

						sb.append("<br/>");

						sb.append(this.getForumDetail(f, board, sysConfig, request, us, messageSource));

					} else {
						if (f.getIsHidden() == 1) { // 回复帖
							ForumService forumService = (ForumService) wc.getBean("forumService");
							if (forumService.isReedUser(f.getBoardID(), f.getMainID(), us.getId())) {
								sb.append(messageSource.getMessage("post.hidden.needre0", null, request.getLocale()));
								sb.append("<br/>");

								sb.append(this.getForumDetail(f, board, sysConfig, request, us, messageSource));
							} else {
								sb.append(messageSource.getMessage("error.post.hidden.needre", null, request
										.getLocale()));
								if (f.getPreviewAttach() > 0) {
									sb.append("<br/>");
									sb.append(this.getForumPreviewDetail(f, board, sysConfig, request, us,
											messageSource));
								}
							}
						}
						if (f.getIsHidden() == 2) { // 金钱购买帖
							String tagId = "0";
							Object tagIdObj = this.getStack().findValue(this.tagIdValue);
							if (tagIdObj != null) {
								tagId = (String) tagIdObj;
							}

							ForumService forumService = (ForumService) wc.getBean("forumService");
							ForumBuy fb = forumService.findForumBuyByPostIdFromId(f.getId(), us.getId());
							if (fb != null) { // 购买过或有直接查看隐藏帖权限
								sb.append(messageSource.getMessage("post.hidden.buy", null, request.getLocale()));
								sb.append("<br/>");
								sb.append(this.getForumDetail(f, board, sysConfig, request, us, messageSource));
							} else {
								long bynum = forumService.getForumBuyNumByPostId(f.getId());
								String buyMsg = messageSource.getMessage("post.hidden.buy1", new String[] {
										String.valueOf(f.getIsHiddenValue()), String.valueOf(bynum) }, request
										.getLocale());

								Configuration tempConfiguration = (Configuration) wc.getBean("tempConfiguration");
								try {
									tempConfiguration.setDirectoryForTemplateLoading(new File(Constant.ROOTPATH
											+ Constant.FTL_PATH));
									tempConfiguration.setDefaultEncoding(Constant.CHARSET);
									tempConfiguration.setLocale(request.getLocale());
									tempConfiguration.setNumberFormat("0.##########");

									Map root = new HashMap();

									root.put("postid", f.getId());
									root.put("bid", Long.valueOf(f.getBoardID()));
									root.put("actionUrl", BBSCSUtil.getActionMappingURL("/post", request));
									root.put("buyMsg", buyMsg);
									root.put("tagId", tagId);
									root.put("iwantbuy", messageSource.getMessage("post.iwantbuy", null, request
											.getLocale()));

									// root.put("fdetail",
									// this.getForumDetail(f, board, sysConfig,
									// request, us));

									if (f.getPreviewAttach() > 0) {
										root.put("preview", this.getForumPreviewDetail(f, board, sysConfig, request,
												us, messageSource));
									} else {
										root.put("preview", "");
									}

									Template temp = tempConfiguration.getTemplate("forumBuy.ftl");
									// Writer writer = pageContext.getOut();
									StringWriter sw = new StringWriter();
									temp.process(root, sw);
									// writer.flush();

									this.write(writer, sw.toString());
									sw.flush();
								} catch (TemplateException ex) {
									logger.error(ex);
								} catch (IOException ex) {
									logger.error(ex);
								}
							}
						}
						if (f.getIsHidden() == 3) { // 资历帖
							UserService userService = (UserService) wc.getBean("userService");
							UserInfoSimple uis = userService.getUserInfoSimple(us.getId());
							if (uis.getArticleNum() >= f.getIsHiddenValue()) {
								sb.append(messageSource.getMessage("post.hidden.artnum", null, request.getLocale()));

								sb.append("<br/>");
								sb.append(this.getForumDetail(f, board, sysConfig, request, us, messageSource));
							} else {
								sb.append(messageSource.getMessage("error.post.hidden.artnum", new String[] { String
										.valueOf(f.getIsHiddenValue()) }, request.getLocale()));
							}
						}
					}
				} else { // 不是隐藏帖
					sb.append(this.getForumDetail(f, board, sysConfig, request, us, messageSource));
				}

				this.write(writer, sb.toString());
			} else { // 投票帖
				VoteService voteService = (VoteService) wc.getBean("voteService");
				// System.out.println(f.getVoteID());
				Vote vote = voteService.findVoteByID(f.getVoteID());
				// System.out.println(vote);
				if (vote != null) {
					// System.out.println("Vote exist..");
					VoteItemService voteItemService = (VoteItemService) wc.getBean("voteItemService");
					List voteItems = voteItemService.findVoteItemsByVoteID(vote.getId());
					Configuration tempConfiguration = (Configuration) wc.getBean("tempConfiguration");
					try {
						tempConfiguration
								.setDirectoryForTemplateLoading(new File(Constant.ROOTPATH + Constant.FTL_PATH));
						tempConfiguration.setDefaultEncoding(Constant.CHARSET);
						tempConfiguration.setLocale(request.getLocale());
						tempConfiguration.setNumberFormat("0.##########");

						Map root = new HashMap();

						root.put("postid", f.getId());
						root.put("bid", Long.valueOf(f.getBoardID()));
						root.put("vote", vote);
						root.put("vis", voteItems);
						root.put("fdetail", this.getForumDetail(f, board, sysConfig, request, us, messageSource));

						Template temp = tempConfiguration.getTemplate("vote.ftl");
						// Writer writer = pageContext.getOut();
						StringWriter sw = new StringWriter();
						temp.process(root, sw);
						// writer.flush();
						this.write(writer, sw.toString());
						sw.flush();
					} catch (TemplateException ex) {
						logger.error(ex);
					} catch (IOException ex) {
						logger.error(ex);
					}
				}
			}

			return result;
		}

		if (type.equalsIgnoreCase("attach")) {
			Object usObj = this.getStack().findValue("userSession");
			UserSession us = (UserSession) usObj;
			if (f.getHaveAttachFile() != 0 && f.getAttachFileName() != null && !f.getAttachFileName().isEmpty()) {
				if (f.getIsHidden() != 0) {
					if (f.getUserID().equals(us.getId())
							|| us.isHaveBoardSpecialPermission(Constant.SPERMISSION_CAN_SEE_HIDDENDETAIL)) { // 发表人或有直接查看隐藏帖权限

						if (f.getIsHidden() == 1) {
							sb.append(messageSource.getMessage("post.hidden.needre0", null, request.getLocale()));
						}
						if (f.getIsHidden() == 2) {
							sb.append(messageSource.getMessage("post.hidden.buy", null, request.getLocale()));
						}
						if (f.getIsHidden() == 3) {
							sb.append(messageSource.getMessage("post.hidden.artnum", null, request.getLocale()));
						}

						sb.append("<br/>");

						sb.append(this.getAttachFile(f, sysConfig, true, request, us, true, messageSource));

					} else {
						if (f.getIsHidden() == 1) { // 回复帖
							ForumService forumService = (ForumService) wc.getBean("forumService");
							if (forumService.isReedUser(f.getBoardID(), f.getMainID(), us.getId())) {
								sb.append(messageSource.getMessage("post.hidden.needre0", null, request.getLocale()));
								sb.append("<br/>");

								sb.append(this.getAttachFile(f, sysConfig, true, request, us, true, messageSource));
							} else {
								sb.append(messageSource.getMessage("error.post.hidden.needre", null, request
										.getLocale()));
								if (f.getPreviewAttach() > 0) {
									sb.append("<br/>");
									sb.append(this.getPreviewAttachFile(f, sysConfig, true, request, us, true,
											messageSource));
								}
							}
						}
						if (f.getIsHidden() == 2) { // 金钱购买帖
							ForumService forumService = (ForumService) wc.getBean("forumService");
							ForumBuy fb = forumService.findForumBuyByPostIdFromId(f.getId(), us.getId());
							if (fb != null) { // 购买过或有直接查看隐藏帖权限
								sb.append(messageSource.getMessage("post.hidden.buy", null, request.getLocale()));
								sb.append("<br/>");
								sb.append(this.getAttachFile(f, sysConfig, true, request, us, true, messageSource));
							} else {
								sb.append(messageSource.getMessage("post.hidden.buy2", null, request.getLocale()));
								if (f.getPreviewAttach() > 0) {
									sb.append("<br/>");
									sb.append(this.getPreviewAttachFile(f, sysConfig, true, request, us, true,
											messageSource));
								}
							}
						}
						if (f.getIsHidden() == 3) { // 资历帖
							UserService userService = (UserService) wc.getBean("userService");
							UserInfoSimple uis = userService.getUserInfoSimple(us.getId());
							if (uis.getArticleNum() >= f.getIsHiddenValue()) {
								sb.append(messageSource.getMessage("post.hidden.artnum", null, request.getLocale()));
								sb.append("<br/>");
								sb.append(this.getAttachFile(f, sysConfig, true, request, us, true, messageSource));
							} else {
								sb.append(messageSource.getMessage("error.post.hidden.artnum", new String[] { String
										.valueOf(f.getIsHiddenValue()) }, request.getLocale()));
							}
						}

					}
				} else {
					sb.append(this.getAttachFile(f, sysConfig, true, request, us, true, messageSource));
				}
			} else {
				sb.append(messageSource.getMessage("error.post.upfile.notexist1", null, request.getLocale()));
			}
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("sign")) {
			if (StringUtils.isNotBlank(f.getSign())) {
				sb.append("<div id=\"sign");
				sb.append(f.getId());
				sb.append("\" class=\"");
				sb.append(this.getItemClass());
				sb.append("\">");
				sb.append(BBSCSUtil.filterText(f.getSign(), sysConfig.isSignUseHtml(), sysConfig.isSignUseUBB(),
						sysConfig.isSignUseSmile()));
				sb.append("</div>");
				this.write(writer, sb.toString());
			}
			return result;
		}
		if (type.equalsIgnoreCase("amend")) {
			if (StringUtils.isNotBlank(f.getAmend())) {
				sb.append("<div id=\"amend");
				sb.append(f.getId());
				sb.append("\">");
				sb.append("<br/>------<br/><font color=\"#0099CC\">");
				sb.append(f.getAmend());
				sb.append("</font>");
				sb.append("</div>");
				this.write(writer, sb.toString());
			}
			return result;
		}
		if (type.equalsIgnoreCase("delmsg")) {
			if (f.getDelSign() == 1) {
				sb.append("<div id=\"delmsg");
				sb.append(f.getId());
				sb.append("\">");
				sb.append("<br/><br/><font color=\"#0099CC\">");
				sb.append(messageSource.getMessage("forum.delmsg", new String[] { f.getDelUserName(),
						Util.formatDateTime(new Date(f.getDelTime())), f.getDelIP() }, request.getLocale()));
				sb.append("</font>");
				sb.append("</div>");
				this.write(writer, sb.toString());

			}
			return result;
		}

		if (type.equalsIgnoreCase("re") || type.equalsIgnoreCase("requote")) {
			Object forumCurrentPageObj = this.getStack().findValue(this.currentPageValue);
			int fcpage = 1;
			if (forumCurrentPageObj != null) {
				fcpage = ((Integer) forumCurrentPageObj).intValue();
			}

			long topicTotal = 0;
			Object topicTotalObj = this.getStack().findValue(this.totalnumValue);
			if (topicTotalObj != null) {
				topicTotal = ((Long) topicTotalObj).longValue();
			}
			String tagId = "0";
			Object tagIdObj = this.getStack().findValue(this.tagIdValue);
			if (tagIdObj != null) {
				tagId = (String) tagIdObj;
			}

			StringBuffer linksb = new StringBuffer();
			linksb.append("/post?action=");
			linksb.append(type);
			linksb.append("&bid=");
			linksb.append(f.getBoardID());
			linksb.append("&parentID=");
			linksb.append(f.getId());
			linksb.append("&mainID=");
			linksb.append(f.getMainID());
			// linksb.append("&page=");
			linksb.append("&fcpage=");
			linksb.append(fcpage);
			linksb.append("&totalnum=");
			linksb.append(topicTotal);
			linksb.append("&tagId=");
			linksb.append(tagId);
			// linksb.append(f.getTagID());
			sb.append("<a href=\"");
			sb.append(BBSCSUtil.getActionMappingURL(linksb.toString(), request));
			sb.append("\">");
			if (type.equalsIgnoreCase("re")) {
				sb.append(messageSource.getMessage("bbscs.re", null, request.getLocale()));
			} else {
				sb.append(messageSource.getMessage("bbscs.requote", null, request.getLocale()));
			}
			sb.append("</a>");
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("sendnote")) {
			sb.append("<a href=\"javascript:;\" onclick=\"loadNoteSend('");
			sb.append(f.getId());
			sb.append("');\">");
			sb.append(messageSource.getMessage("pot.sendnote", null, request.getLocale()));
			sb.append("</a>");
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("upfilepage")) {
			sb.append("<a href=\"javascript:;\" onclick=\"showUpfilePage('");
			sb.append(f.getBoardID());
			sb.append("','");
			sb.append(f.getId());
			sb.append("');\">");
			sb.append(messageSource.getMessage("post.upfile", null, request.getLocale()));
			sb.append("</a>");
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("delattachpage")) {
			sb.append("<a href=\"javascript:;\" onclick=\"showDelAttachPage('");
			sb.append(f.getBoardID());
			sb.append("','");
			sb.append(f.getId());
			sb.append("');\">");
			sb.append(messageSource.getMessage("post.upfile.del", null, request.getLocale()));
			sb.append("</a>");
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("movepage")) {
			sb.append("<a href=\"javascript:;\" onclick=\"showMovePage('");
			sb.append(f.getBoardID());
			sb.append("','");
			sb.append(f.getId());
			sb.append("');\">");
			sb.append(messageSource.getMessage("post.move.showpage", null, request.getLocale()));
			sb.append("</a>");
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("upfileinpost")) {
			Object usObj = this.getStack().findValue("userSession");
			UserSession us = (UserSession) usObj;
			if (f.getHaveAttachFile() != 0 && f.getAttachFileName() != null && !f.getAttachFileName().isEmpty()) {
				sb.append(this.getAttachFile(f, sysConfig, true, request, us, false, messageSource));
			}
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("voteinpost")) {
			VoteService voteService = (VoteService) wc.getBean("voteService");
			// System.out.println(f.getVoteID());
			Vote vote = voteService.findVoteByID(f.getVoteID());
			// System.out.println(vote);
			if (vote != null) {
				// System.out.println("Vote exist..");
				VoteItemService voteItemService = (VoteItemService) wc.getBean("voteItemService");
				List voteItems = voteItemService.findVoteItemsByVoteID(vote.getId());
				Configuration tempConfiguration = (Configuration) wc.getBean("tempConfiguration");
				try {
					tempConfiguration.setDirectoryForTemplateLoading(new File(Constant.ROOTPATH + Constant.FTL_PATH));
					tempConfiguration.setDefaultEncoding(Constant.CHARSET);
					tempConfiguration.setLocale(request.getLocale());
					tempConfiguration.setNumberFormat("0.##########");

					Map root = new HashMap();

					root.put("postid", f.getId());
					root.put("bid", Long.valueOf(f.getBoardID()));
					root.put("vote", vote);
					root.put("vis", voteItems);

					Template temp = tempConfiguration.getTemplate("voteInPost.ftl");
					this.write(writer, sb.toString());

					temp.process(root, writer);
					// writer.flush();
				} catch (TemplateException ex) {
					logger.error(ex);
				} catch (IOException ex) {
					logger.error(ex);
				}
			}
			return result;
		}

		if (type.equalsIgnoreCase("edit")) {
			Object forumCurrentPageObj = this.getStack().findValue(this.currentPageValue);
			int fcpage = 1;
			if (forumCurrentPageObj != null) {
				fcpage = ((Integer) forumCurrentPageObj).intValue();
			}

			int inpages = 1;
			Object inpagesObj = this.getStack().findValue(inPagesValue);
			if (inpagesObj != null) {
				inpages = ((Integer) inpagesObj).intValue();
			}

			String tagId = "0";
			Object tagIdObj = this.getStack().findValue(this.tagIdValue);
			if (tagIdObj != null) {
				tagId = (String) tagIdObj;
			}

			StringBuffer linksb = new StringBuffer();
			if (f.getIsVote() == 0) {
				linksb.append("/post?action=edit&");
			} else {
				linksb.append("/votePost?action=edit&");
			}
			linksb.append("id=");
			linksb.append(f.getId());
			linksb.append("&bid=");
			linksb.append(f.getBoardID());
			// linksb.append("&page=");
			linksb.append("&fcpage=");
			linksb.append(fcpage);
			linksb.append("&inpages=");
			linksb.append(inpages);
			linksb.append("&mainID=");
			linksb.append(f.getMainID());
			linksb.append("&tagId=");
			linksb.append(tagId);
			// linksb.append(f.getTagID());
			sb.append("<a href=\"");
			sb.append(BBSCSUtil.getActionMappingURL(linksb.toString(), request));
			sb.append("\">");
			sb.append(messageSource.getMessage("bbscs.change", null, request.getLocale()));
			sb.append("</a>");
			this.write(writer, sb.toString());
			return result;
		}
		if (type.equalsIgnoreCase("del")) {
			Object forumCurrentPageObj = this.getStack().findValue(this.currentPageValue);
			int fcpage = 1;
			if (forumCurrentPageObj != null) {
				fcpage = ((Integer) forumCurrentPageObj).intValue();
			}
			sb.append("<a href=\"javascript:;\" onclick=\"delapost('");
			sb.append(f.getBoardID());
			sb.append("','");
			sb.append(f.getId());
			sb.append("','");
			sb.append(f.getIsNew());
			sb.append("','");
			sb.append(fcpage);
			sb.append("');\">");
			sb.append(messageSource.getMessage("bbscs.del", null, request.getLocale()));
			sb.append("</a>");
			this.write(writer, sb.toString());
			return result;
		}

		/*
		 * try { writer.write(sb.toString()); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

		return result;
	}

	private int getUserPostPerNum(int userNum, int sysNum) {
		if (userNum == 0) {
			return sysNum;
		} else {
			return userNum;
		}
	}

	private void write(Writer writer, String txt) {
		try {
			writer.write(txt);
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

	private StringBuffer getAttachFile(Forum f, SysConfig sysConfig, boolean indiv, HttpServletRequest request,
			UserSession us, boolean isOriginal, ResourceBundleMessageSource messageSource) {
		StringBuffer sb = new StringBuffer();
		if (!indiv) {
			sb.append("<div id=\"upfile");
			sb.append(f.getId());
			sb.append("\" class=\"font5\">");
		}
		sb.append("<BR />");
		if (f.getAuditingAttachFile() == 1
				&& !us.isHaveBoardSpecialPermission(Constant.SPERMISSION_CAN_SEE_NOT_AUDITING_ATTACH)) {
			sb.append(messageSource.getMessage("post.auditingattachfile.notpass", null, request.getLocale()));
		} else {
			if (f.getAuditingAttachFile() == 1
					&& us.isHaveBoardSpecialPermission(Constant.SPERMISSION_CAN_SEE_NOT_AUDITING_ATTACH)) {
				sb.append("<div>");
				sb.append(messageSource.getMessage("post.auditingattachfile.notpass1", null, request.getLocale()));
				sb.append("</div>");
			}
			if (!isOriginal) {
				sb.append("[<a href=\"");
				sb.append(BBSCSUtil.getActionMappingURL("/read?action=attach&id=" + f.getId() + "&bid="
						+ f.getBoardID(), request));
				sb.append("\" target=\"_blank\">");
				sb.append(messageSource.getMessage("post.attache.all", null, request.getLocale()));
				sb.append("</a>]<br/>");
			}
			for (int i = 0; i < f.getAttachFileName().size(); i++) {
				String fileName = (String) (f.getAttachFileName().get(i));
				sb.append("<img src=\"images/icons/");
				sb.append(BBSCSUtil.getFileTypeIcon(FilenameUtils.getExtension(fileName)));
				sb.append("\" align=\"absmiddle\"/> ");

				sb.append(messageSource.getMessage("post.attach", null, request.getLocale()));
				sb.append("[");
				sb.append(i + 1);
				sb.append("]");

				if (FilenameUtils.isExtension(fileName, sysConfig.getAttachImgTypes())) {
					sb.append("<div class=\"upfile1\">");
					sb.append("<a href=\"");
					sb.append(BBSCSUtil.getUpFileWebPath(f.getBoardID(), f.getPostTime()));
					sb.append(fileName);
					sb.append("\" target=\"_blank\">");
					sb.append("<img src=\"");
					sb.append(BBSCSUtil.getUpFileWebPath(f.getBoardID(), f.getPostTime()));
					sb.append(fileName);
					if (sysConfig.getReduceAttachImg() == 1 && !isOriginal) {
						sb.append(Constant.IMG_SMALL_FILEPREFIX);
					}
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
					sb.append(messageSource.getMessage("post.upfile.download", null, request.getLocale()));
					// sb.append(fileName);
					sb.append("</a>");
					sb.append("</div>");
				}
			}
		}
		if (!indiv) {
			sb.append("</div>");
		}
		return sb;
	}

	private StringBuffer getForumDetail(Forum f, Board board, SysConfig sysConfig, HttpServletRequest request,
			UserSession us, ResourceBundleMessageSource messageSource) {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotBlank(f.getQuoteText())) {
			sb.append("<blockquote class=\"quote1\"><strong>");
			sb.append(messageSource.getMessage("bbscs.quote", null, request.getLocale()));
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
			sb.append(this.getAttachFile(f, sysConfig, false, request, us, false, messageSource));
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
			Cache postCache = (Cache) wc.getBean("postCache");
			String detail = "";
			detail = (String) postCache.get(f.getId());
			if (detail == null) {
				ForumConfig forumConfig = (ForumConfig) wc.getBean("forumConfig");
				File postFile = new File(forumConfig.getForumPath(f.getBoardID(), f.getPostTime()) + f.getDetail());
				try {
					detail = FileUtils.readFileToString(postFile, Constant.CHARSET);
					if (StringUtils.isNotBlank(detail)) {
						postCache.add(f.getId(), detail);
					}
				} catch (IOException e) {
					detail = "";
				}
			}
			return detail;
		}
	}

	private StringBuffer getForumPreviewDetail(Forum f, Board board, SysConfig sysConfig, HttpServletRequest request,
			UserSession us, ResourceBundleMessageSource messageSource) {
		StringBuffer sb = new StringBuffer();
		/*
		 * if (StringUtils.isNotBlank(f.getQuoteText())) { sb.append("<blockquote
		 * class=\"quote1\"><strong>");
		 * sb.append(messageSource.getMessage("bbscs.quote", null,
		 * request.getLocale())); sb.append(":</strong><br />"); if
		 * (f.getQuoteEditType() == 0) {
		 * sb.append(BBSCSUtil.filterText(f.getQuoteText(),
		 * (board.getAllowHTML() == 1), (board.getAllowUBB() == 1), true)); }
		 * else { sb.append(BBSCSUtil.filterScript(f.getQuoteText())); }
		 * sb.append("</blockquote>"); }
		 */
		if (f.getHaveAttachFile() != 0 && f.getAttachFileName() != null && !f.getAttachFileName().isEmpty()) {
			sb.append(this.getPreviewAttachFile(f, sysConfig, false, request, us, false, messageSource));
		} else {
			sb.append("<div id=\"upfile");
			sb.append(f.getId());
			sb.append("\" class=\"font5\" style=\"display:none\"></div>");
		}

		/*
		 * if (f.getEditType() == 0) { sb.append(BBSCSUtil
		 * .filterText(f.getDetail(), (board.getAllowHTML() == 1),
		 * (board.getAllowUBB() == 1), true)); } else {
		 * sb.append(BBSCSUtil.filterScript(f.getDetail())); }
		 */
		return sb;
	}

	private StringBuffer getPreviewAttachFile(Forum f, SysConfig sysConfig, boolean indiv, HttpServletRequest request,
			UserSession us, boolean isOriginal, ResourceBundleMessageSource messageSource) {
		StringBuffer sb = new StringBuffer();
		if (!indiv) {
			sb.append("<div id=\"upfile");
			sb.append(f.getId());
			sb.append("\" class=\"font5\">");
		}
		sb.append("<BR />");
		if (f.getAuditingAttachFile() == 1
				&& !us.isHaveBoardSpecialPermission(Constant.SPERMISSION_CAN_SEE_NOT_AUDITING_ATTACH)) {
			sb.append(messageSource.getMessage("post.auditingattachfile.notpass", null, request.getLocale()));
		} else {
			if (f.getAuditingAttachFile() == 1
					&& us.isHaveBoardSpecialPermission(Constant.SPERMISSION_CAN_SEE_NOT_AUDITING_ATTACH)) {
				sb.append("<div>");
				sb.append(messageSource.getMessage("post.auditingattachfile.notpass1", null, request.getLocale()));
				sb.append("</div>");
			}

			// int anum = f.getAttachFileName().size();
			String fileName = (String) (f.getAttachFileName().get(0));
			sb.append("<div>");
			sb.append(messageSource.getMessage("post.hidden.preview", null, request.getLocale()));
			sb.append("</div>");

			if (FilenameUtils.isExtension(fileName, sysConfig.getAttachImgTypes())) {
				sb.append("<div class=\"upfile1\">");
				sb.append("<a href=\"");
				sb.append(BBSCSUtil.getUpFileWebPath(f.getBoardID(), f.getPostTime()));
				sb.append(fileName);
				sb.append("\" target=\"_blank\">");
				sb.append("<img src=\"");
				sb.append(BBSCSUtil.getUpFileWebPath(f.getBoardID(), f.getPostTime()));
				sb.append(fileName);
				if (sysConfig.getReduceAttachImg() == 1 && !isOriginal) {
					sb.append(Constant.IMG_SMALL_FILEPREFIX);
				}
				sb.append("\" border=\"0\" alt=\"\"/></a>");
				sb.append("</div>");
			}

		}
		if (!indiv) {
			sb.append("</div>");
		}
		return sb;
	}

	public String getBoardValue() {
		return boardValue;
	}

	public void setBoardValue(String boardValue) {
		this.boardValue = boardValue;
	}

	public String getCurrentActionValue() {
		return currentActionValue;
	}

	public void setCurrentActionValue(String currentActionValue) {
		this.currentActionValue = currentActionValue;
	}

	public String getCurrentPageValue() {
		return currentPageValue;
	}

	public void setCurrentPageValue(String currentPageValue) {
		this.currentPageValue = currentPageValue;
	}

	public String getForumValue() {
		return forumValue;
	}

	public void setForumValue(String forumValue) {
		this.forumValue = forumValue;
	}

	public String getInPagesValue() {
		return inPagesValue;
	}

	public void setInPagesValue(String inPagesValue) {
		this.inPagesValue = inPagesValue;
	}

	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}

	public PageContext getPageContext() {
		return pageContext;
	}

	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	public String getTagIdValue() {
		return tagIdValue;
	}

	public void setTagIdValue(String tagIdValue) {
		this.tagIdValue = tagIdValue;
	}

	public String getTotalnumValue() {
		return totalnumValue;
	}

	public void setTotalnumValue(String totalnumValue) {
		this.totalnumValue = totalnumValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIndexValue() {
		return indexValue;
	}

	public void setIndexValue(String indexValue) {
		this.indexValue = indexValue;
	}

}
