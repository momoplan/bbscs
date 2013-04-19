package com.laoer.bbscs.web.taglib;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.components.Component;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.opensymphony.xwork2.util.ValueStack;

public class PostComponent extends Component {

	private PageContext pageContext;

	private HttpServletRequest request;

	//private HttpServletResponse response;

	protected String type = "";

	protected String boardValue = "%{board}";

	protected String tagIdValue = "%{tagId}";

	public PostComponent(ValueStack arg0, PageContext pageContext, HttpServletRequest request,
			HttpServletResponse response) {
		super(arg0);
		this.request = request;
		//this.response = response;
		this.pageContext = pageContext;
	}

	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		if (boardValue == null) {
			boardValue = "top";
		} else if (altSyntax()) {
			if (boardValue.startsWith("%{") && boardValue.endsWith("}")) {
				boardValue = boardValue.substring(2, boardValue.length() - 1);
			}
		}
		if (tagIdValue == null) {
			tagIdValue = "top";
		} else if (altSyntax()) {
			if (tagIdValue.startsWith("%{") && tagIdValue.endsWith("}")) {
				tagIdValue = tagIdValue.substring(2, tagIdValue.length() - 1);
			}
		}
		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(this.pageContext
				.getServletContext());
		ResourceBundleMessageSource messageSource = (ResourceBundleMessageSource) wc.getBean("messageSource");
		StringBuffer sb = new StringBuffer();
		if (type.equalsIgnoreCase("postat") || type.equalsIgnoreCase("voteat")) {
			Object boardObj = this.getStack().findValue(this.boardValue);
			if (boardObj != null) {
				String tagId = "0";
				Board board = (Board) boardObj;
				Object tagIdObj = this.getStack().findValue(this.tagIdValue);
				if (tagIdObj != null) {
					tagId = (String) tagIdObj;
				}
				sb.append("<a href=\"");
				if (Constant.USE_URL_REWRITE) {
					sb.append("forum-index-");
					sb.append(board.getId().longValue());
					sb.append(".html");
				} else {
					sb.append(BBSCSUtil.getActionMappingURL("/forum?action=index&bid=" + board.getId().longValue(),
							request));
				}
				sb.append("\">");
				sb.append(board.getBoardName());
				sb.append("</a>");
				if (!tagId.equals("0")) {
					sb.append(" - ");
					sb.append("<a href=\"");
					if (Constant.USE_URL_REWRITE) {
						sb.append("forum-index-");
						sb.append(board.getId().longValue());
						sb.append("-");
						sb.append(tagId);
						sb.append("-1-0.html");
					} else {
						sb.append(BBSCSUtil.getActionMappingURL("/forum?action=index&bid=" + board.getId().longValue()
								+ "tagId=" + tagId, request));
					}
					sb.append("\">");
					sb.append(board.getBoardTagById(tagId).getTagName());
					sb.append("</a>");
				}
				if (type.equalsIgnoreCase("postat")) {
					this.write(writer, messageSource.getMessage("post.youatboards", new String[] { sb.toString() },
							request.getLocale()));
				}
				if (type.equalsIgnoreCase("voteat")) {
					this.write(writer, messageSource.getMessage("post.youatboardsvote", new String[] { sb.toString() },
							request.getLocale()));
				}
				return result;
			}
		}

		return result;
	}

	private void write(Writer writer, String txt) {
		try {
			writer.write(txt);
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

	public String getBoardValue() {
		return boardValue;
	}

	public void setBoardValue(String boardValue) {
		this.boardValue = boardValue;
	}

	public String getTagIdValue() {
		return tagIdValue;
	}

	public void setTagIdValue(String tagIdValue) {
		this.tagIdValue = tagIdValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
