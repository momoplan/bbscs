package com.laoer.bbscs.web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import org.springframework.web.context.*;
import org.springframework.web.context.support.*;
import com.laoer.bbscs.service.ForumService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.service.BoardService;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndContent;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.laoer.bbscs.bean.Forum;
import com.sun.syndication.feed.synd.SyndEntry;
import java.net.MalformedURLException;
import com.sun.syndication.feed.synd.SyndContentImpl;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.jdom.output.Format;
import com.sun.syndication.io.SyndFeedOutput;
import org.jdom.Document;
import org.jdom.output.XMLOutputter;
import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.service.web.Pages;
import com.laoer.bbscs.service.web.PageList;

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

public class Rss extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = -2551980824463396664L;

	private static final Log logger = LogFactory.getLog(Rss.class);

	private static final String CONTENT_TYPE = "text/xml; charset=UTF-8";

	// Initialize global variables
	public void init() throws ServletException {
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long bid;
		try {
			bid = Long.parseLong(request.getParameter("bid"));
		} catch (NumberFormatException e) {
			bid = 0L;
		}
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}

		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();

		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());

		ForumService forumService = (ForumService) wc.getBean("forumService");
		SysConfig sysConfig = (SysConfig) wc.getBean("sysConfig");
		BoardService boardService = (BoardService) wc.getBean("boardService");

		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0");
		String postLink = "";

		if (bid == 0) {
			feed.setTitle(sysConfig.getForumName());
			feed.setLink(sysConfig.getForumUrl());
			feed.setDescription(sysConfig.getWebName() + " - " + sysConfig.getForumName());

			List<SyndEntry> entries = new ArrayList<SyndEntry>();
			SyndEntry entry;
			SyndContent description;

			// List newfs = forumService.findForumsAllNew(50);
			List newfs = forumService.findForumsAllNewCache(50);
			for (int i = 0; i < newfs.size(); i++) {
				Forum f = (Forum) newfs.get(i);
				try {
					postLink = BBSCSUtil.absoluteActionURL(request,
							"/main.bbscs?action=read&bid=" + f.getBoardID() + "&postID=" + f.getMainID()).toString();
				} catch (MalformedURLException ex) {
					postLink = "";
				}

				entry = new SyndEntryImpl();
				entry.setTitle(f.getTitle());
				entry.setLink(postLink);
				entry.setPublishedDate(new Date(f.getPostTime()));

				description = new SyndContentImpl();
				if (f.getEditType() == 0) {
					description.setType("text/plain");
				} else {
					description.setType("text/html");
				}
				description.setValue(BBSCSUtil.getSpeShortString(forumService.getForumDetail(f, false), 400, ""));
				entry.setDescription(description);
				entries.add(entry);

			}

			feed.setEntries(entries);
			try {
				SyndFeedOutput output = new SyndFeedOutput();
				// String feedStr = output.outputString(feed);
				Document messagesDocument = output.outputJDom(feed);
				Format format = Format.getPrettyFormat();
				format.setOmitDeclaration(true);
				XMLOutputter xmlo = new XMLOutputter(format);
				xmlo.output(messagesDocument, out);
			} catch (Exception ex) {
				logger.error(ex);
			}
		} else {
			Board board = boardService.getBoardByID(bid);
			if (board != null) {
				if (board.getBoardType() == 2 || board.getBoardType() == 3) {
					String forumLink = "";
					try {
						forumLink = BBSCSUtil.absoluteActionURL(request, "/forum.bbscs?action=index&bid=" + bid)
								.toString();
					} catch (MalformedURLException ex2) {
						forumLink = "";
					}
					feed.setTitle(sysConfig.getForumName() + " - " + board.getBoardName());
					feed.setLink(forumLink);
					feed.setDescription(sysConfig.getWebName() + " - " + sysConfig.getForumName() + " - "
							+ board.getBoardName());

					List<SyndEntry> entries = new ArrayList<SyndEntry>();
					SyndEntry entry;
					SyndContent description;

					Pages pages = new Pages();
					pages.setPage(1);
					pages.setPerPageNum(40);
					pages.setFileName("");

					PageList pl = forumService.findForumsMainWWW(bid, pages);
					List flist = pl.getObjectList();

					for (int i = 0; i < flist.size(); i++) {
						Forum f = (Forum) flist.get(i);
						try {
							postLink = BBSCSUtil.absoluteActionURL(request,
									"/main.bbscs?action=read&bid=" + f.getBoardID() + "&postID=" + f.getMainID())
									.toString();
						} catch (MalformedURLException ex) {
							postLink = "";
						}

						entry = new SyndEntryImpl();
						entry.setTitle(f.getTitle());
						entry.setLink(postLink);
						entry.setPublishedDate(new Date(f.getPostTime()));

						description = new SyndContentImpl();
						if (f.getEditType() == 0) {
							description.setType("text/plain");
						} else {
							description.setType("text/html");
						}
						description.setValue(BBSCSUtil
								.getSpeShortString(forumService.getForumDetail(f, false), 400, ""));
						entry.setDescription(description);
						entries.add(entry);
					}

					feed.setEntries(entries);
					try {
						SyndFeedOutput output = new SyndFeedOutput();
						// String feedStr = output.outputString(feed);
						Document messagesDocument = output.outputJDom(feed);
						Format format = Format.getPrettyFormat();
						format.setOmitDeclaration(true);
						XMLOutputter xmlo = new XMLOutputter(format);
						xmlo.output(messagesDocument, out);
					} catch (Exception ex) {
						logger.error(ex);
					}
				}
			}
		}

		out.close();
		return;
	}

	// Clean up resources
	public void destroy() {
	}
}
