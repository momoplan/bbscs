package com.laoer.bbscs.web.taglib;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.components.Component;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.service.config.SysConfig;
import com.opensymphony.xwork2.util.ValueStack;

public class WebInfo extends Component {

	private PageContext pageContext;

	private String type;

	public WebInfo(ValueStack stack, PageContext pageContext) {
		super(stack);
		this.pageContext = pageContext;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(this.pageContext
				.getServletContext());
		SysConfig sysConfig = (SysConfig) wc.getBean("sysConfig");
		StringBuffer sb = new StringBuffer();
		if (this.getType().equalsIgnoreCase("forumname")) {
			sb.append(sysConfig.getForumName());
		}

		if (this.getType().equalsIgnoreCase("poweredby")) {
			sb.append(" - ");
			sb.append("bbs.ruyicai.com");
		}

		if (this.getType().equalsIgnoreCase("meta")) {
			sb.append("<meta name=\"keywords\" content=\"");
			sb.append(sysConfig.getMetaKeywords());
			sb.append("\"/>\n");
			sb.append("<meta name=\"description\" content=\"");
			sb.append(sysConfig.getMetaDescription());
			sb.append("\"/>");
		}
		if (this.getType().equalsIgnoreCase("pagefoot")) {
			Locale locale = this.pageContext.getRequest().getLocale();
			ResourceBundleMessageSource messageSource = (ResourceBundleMessageSource) wc.getBean("messageSource");
			if (StringUtils.isNotBlank(sysConfig.getWebName())) {
				if (StringUtils.isNotBlank(sysConfig.getWebUrl())) {
					sb.append("<a href=\"");
					sb.append(sysConfig.getWebUrl());
					sb.append("\" target=\"_blank\">");
					sb.append(sysConfig.getWebName());
					sb.append("</a>");
				} else {
					sb.append(sysConfig.getWebName());
				}
			}
			if (StringUtils.isNotBlank(sysConfig.getForumName())) {
				sb.append(" | ");
				if (StringUtils.isNotBlank(sysConfig.getForumUrl())) {
					sb.append("<a href=\"");
					sb.append(sysConfig.getForumUrl());
					sb.append("\" target=\"_blank\">");
					sb.append(sysConfig.getForumName());
					sb.append("</a>");
				} else {
					sb.append(sysConfig.getForumName());
				}
			}
			if (StringUtils.isNotBlank(sysConfig.getWebmasterEmail())) {
				sb.append(" | ");
				sb.append("<a href=\"mailto:");
				sb.append(sysConfig.getWebmasterEmail());
				sb.append("\">");
				sb.append(messageSource.getMessage("bbscs.contactus", null, locale));
				sb.append("</a>");
			}
			if (StringUtils.isNotBlank(sysConfig.getPrivacyUrl())) {
				sb.append(" | ");
				sb.append("<a href=\"");
				sb.append(sysConfig.getPrivacyUrl());
				sb.append("\" target=\"_blank\">");
				sb.append(messageSource.getMessage("bbscs.privacy", null, locale));
				sb.append("</a>");
			}
			if (StringUtils.isNotBlank(sysConfig.getCopyRightMsg())) {
				sb.append("<BR/>");
				sb.append(sysConfig.getCopyRightMsg());
			}
			sb.append("<BR/>");
			sb.append("<strong><font face=\"Tahoma\" size=\"1\" color=\"#A0A0A4\">");
			sb.append("<a href=\"http://bbs.ruyicai.com\" target='_blank'>bbs.ruyicai.com</a>");
			sb.append(" V");
			sb.append(Constant.VSERION);
			sb.append(" &copy; 2007</font></strong>");
		}

		try {
			writer.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

}
