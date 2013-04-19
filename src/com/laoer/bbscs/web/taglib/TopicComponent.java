package com.laoer.bbscs.web.taglib;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.components.Component;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.opensymphony.xwork2.util.ValueStack;

public class TopicComponent extends Component {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(TopicComponent.class);

	private PageContext pageContext;

	private HttpServletRequest request;

	//private HttpServletResponse response;

	protected String mainIdName = "%{id}";

	protected String forumCurrentPageName = "%{fcpage}";

	protected String inPagesName = "%{inpages}";

	protected String topicTotalNumName = "%{totalnum}";

	protected String type = "";

	protected String boardObjName = "%{board}";

	protected String forumCurrentActionName = "%{fcaction}";

	protected String tagIdProperty = "%{tagId}";

	public TopicComponent(ValueStack arg0, PageContext pageContext, HttpServletRequest request,
			HttpServletResponse response) {
		super(arg0);
		this.pageContext = pageContext;
		this.request = request;
		//this.response = response;
	}

	public boolean start(Writer writer) {
		boolean result = super.start(writer);

		if (mainIdName == null) {
			mainIdName = "top";
		} else if (altSyntax()) {
			if (mainIdName.startsWith("%{") && mainIdName.endsWith("}")) {
				mainIdName = mainIdName.substring(2, mainIdName.length() - 1);
			}
		}
		if (forumCurrentPageName == null) {
			forumCurrentPageName = "top";
		} else if (altSyntax()) {
			if (forumCurrentPageName.startsWith("%{") && forumCurrentPageName.endsWith("}")) {
				forumCurrentPageName = forumCurrentPageName.substring(2, forumCurrentPageName.length() - 1);
			}
		}
		if (inPagesName == null) {
			inPagesName = "top";
		} else if (altSyntax()) {
			if (inPagesName.startsWith("%{") && inPagesName.endsWith("}")) {
				inPagesName = inPagesName.substring(2, inPagesName.length() - 1);
			}
		}
		if (topicTotalNumName == null) {
			topicTotalNumName = "top";
		} else if (altSyntax()) {
			if (topicTotalNumName.startsWith("%{") && topicTotalNumName.endsWith("}")) {
				topicTotalNumName = topicTotalNumName.substring(2, topicTotalNumName.length() - 1);
			}
		}
		if (boardObjName == null) {
			boardObjName = "top";
		} else if (altSyntax()) {
			if (boardObjName.startsWith("%{") && boardObjName.endsWith("}")) {
				boardObjName = boardObjName.substring(2, boardObjName.length() - 1);
			}
		}

		//logger.debug("forumCurrentActionName:"+forumCurrentActionName);
		if (forumCurrentActionName == null) {
			forumCurrentActionName = "top";
		} else if (altSyntax()) {
			if (forumCurrentActionName.startsWith("%{") && forumCurrentActionName.endsWith("}")) {
				forumCurrentActionName = forumCurrentActionName.substring(2, forumCurrentActionName.length() - 1);
			}
		}

		//logger.debug("forumCurrentActionName:"+forumCurrentActionName);

		if (tagIdProperty == null) {
			tagIdProperty = "top";
		} else if (altSyntax()) {
			if (tagIdProperty.startsWith("%{") && tagIdProperty.endsWith("}")) {
				tagIdProperty = tagIdProperty.substring(2, tagIdProperty.length() - 1);
			}
		}

		Object boardObj = this.getStack().findValue(this.boardObjName);
		Board board = (Board) boardObj;

		String mainId = "";
		Object mainIdNameObj = this.getStack().findValue(this.mainIdName);
		if (mainIdNameObj != null) {
			mainId = (String) mainIdNameObj;
		}
		int fcpage = 1;
		Object fcpageObj = this.getStack().findValue(this.forumCurrentPageName);
		if (fcpageObj != null) {
			fcpage = ((Integer) fcpageObj).intValue();
		}

		String fcaction = "index";
		Object fcactionObj = this.getStack().findValue(this.forumCurrentActionName);
		if (fcactionObj != null) {
			fcaction = (String) fcactionObj;
		}

		String tagId = "0";
		Object tagIdObj = this.getStack().findValue(this.tagIdProperty);
		if (tagIdObj != null) {
			tagId = (String) tagIdObj;
		}

		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(this.pageContext
				.getServletContext());
		//SysConfig sysConfig = (SysConfig) wc.getBean("sysConfig");
		ResourceBundleMessageSource messageSource = (ResourceBundleMessageSource) wc.getBean("messageSource");

		StringBuffer sb = new StringBuffer();
		if (type.equalsIgnoreCase("returnforum")) {
			sb.append("<a href=\"");
			if (Constant.USE_URL_REWRITE) {
				sb.append("forum-" + fcaction + "-" + board.getId() + "-" + tagId + "-" + fcpage + "-0.html");
			} else {
				sb.append(BBSCSUtil.getActionMappingURL("/forum?action=" + fcaction + "&bid=" + board.getId()
						+ "&page=" + fcpage, request));
			}
			sb.append("\">");

			sb.append(messageSource.getMessage("bbscs.back", null, request.getLocale()));
			sb.append("</a>");
			this.write(writer, sb.toString());
			return result;
		}
		if (type.equalsIgnoreCase("subs")) {
			sb.append("<a href=\"javascript:;\" onclick=\"subsTopic('");
			sb.append(board.getId());
			sb.append("','");
			sb.append(mainId);
			sb.append("');\">");
			sb.append(messageSource.getMessage("post.subs.title", null, request.getLocale()));
			sb.append("</a>");
			this.write(writer, sb.toString());
			return result;
		}
		if (type.equalsIgnoreCase("mailsendtopic")) {
			sb.append("<a href=\"javascript:;\" onclick=\"mailSendTopicAll('");
			sb.append(board.getId());
			sb.append("','");
			sb.append(mainId);
			sb.append("');\">");
			sb.append(messageSource.getMessage("post.mailsendtopic.title", null, request.getLocale()));
			sb.append("</a>");
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("own") || type.equalsIgnoreCase("topic")) {
			sb.append("<a href=\"");
			sb.append(BBSCSUtil.getActionMappingURL("/read?action=" + type + "&bid=" + board.getId() + "&id=" + mainId
					+ "&fcpage=" + fcpage + "&fcaction=" + fcaction + "&tagId=" + tagId, request));
			sb.append("\">");
			if (type.equalsIgnoreCase("own")) {
				sb.append(messageSource.getMessage("post.read.own", null, request.getLocale()));
			}
			if (type.equalsIgnoreCase("topic")) {
				sb.append(messageSource.getMessage("post.read.all", null, request.getLocale()));
			}
			sb.append("</a>");
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("commend")) {
			File commendFile = new File(BBSCSUtil.getIncludePath() + "Commend_" + board.getTopBid() + ".html");
			String commendText = "";
			try {
				commendText = FileUtils.readFileToString(commendFile, Constant.CHARSET);
				if (commendText == null) {
					commendText = "";
				}
			} catch (IOException ex) {
				commendText = "";
			}
			sb.append(commendText);
			this.write(writer, sb.toString());
			return result;
		}

		if (type.equalsIgnoreCase("posturl")) {
			try {
				if (Constant.USE_URL_REWRITE) {
					sb.append(BBSCSUtil.absoluteActionURLHtml(request, "/main-read-" + board.getId() + "-" + mainId
							+ ".html"));
				} else {
					sb.append(BBSCSUtil.absoluteActionURL(request, "/main?action=read&bid=" + board.getId()
							+ "&postID=" + mainId));
				}
			} catch (MalformedURLException ex1) {
				sb.append("");
			}
			this.write(writer, sb.toString());
			return result;
		}

		return result;

	}

	private void write(Writer writer, String txt) {
		try {
			writer.write(txt);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public String getBoardObjName() {
		return boardObjName;
	}

	public void setBoardObjName(String boardObjName) {
		this.boardObjName = boardObjName;
	}

	public String getForumCurrentActionName() {
		return forumCurrentActionName;
	}

	public void setForumCurrentActionName(String forumCurrentActionName) {
		this.forumCurrentActionName = forumCurrentActionName;
	}

	public String getForumCurrentPageName() {
		return forumCurrentPageName;
	}

	public void setForumCurrentPageName(String forumCurrentPageName) {
		this.forumCurrentPageName = forumCurrentPageName;
	}

	public String getInPagesName() {
		return inPagesName;
	}

	public void setInPagesName(String inPagesName) {
		this.inPagesName = inPagesName;
	}

	public String getMainIdName() {
		return mainIdName;
	}

	public void setMainIdName(String mainIdName) {
		this.mainIdName = mainIdName;
	}

	public String getTagIdProperty() {
		return tagIdProperty;
	}

	public void setTagIdProperty(String tagIdProperty) {
		this.tagIdProperty = tagIdProperty;
	}

	public String getTopicTotalNumName() {
		return topicTotalNumName;
	}

	public void setTopicTotalNumName(String topicTotalNumName) {
		this.topicTotalNumName = topicTotalNumName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
