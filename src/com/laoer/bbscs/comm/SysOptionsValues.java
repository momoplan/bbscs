package com.laoer.bbscs.comm;

import org.springframework.context.support.ResourceBundleMessageSource;

import com.laoer.bbscs.web.ui.OptionsInt;

import java.util.*;
import org.apache.commons.lang.math.NumberUtils;

public class SysOptionsValues {

	private ResourceBundleMessageSource messageSource;

	public ResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(ResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public List<OptionsInt> getUserTitleValues(Locale locale) {
		List<OptionsInt> l = new ArrayList<OptionsInt>();
		l.add(new OptionsInt(0, this.getMessageSource().getMessage("bbscs.usertitle0", null, locale)));
		l.add(new OptionsInt(1, this.getMessageSource().getMessage("bbscs.usertitle1", null, locale)));
		l.add(new OptionsInt(2, this.getMessageSource().getMessage("bbscs.usertitle2", null, locale)));
		l.add(new OptionsInt(3, this.getMessageSource().getMessage("bbscs.usertitle3", null, locale)));
		return l;
	}

	public List<OptionsInt> getUserForumNumPerPageValues(Locale locale) {
		List<OptionsInt> l = new ArrayList<OptionsInt>();
		l.add(new OptionsInt(0, this.getMessageSource().getMessage("bbscs.usesystem", null, locale)));
		l.add(new OptionsInt(20, "20"));
		l.add(new OptionsInt(30, "30"));
		l.add(new OptionsInt(40, "40"));
		return l;
	}

	public List<OptionsInt> getUserPostNumPerPageValues(Locale locale, String[] ppns) {
		List<OptionsInt> l = new ArrayList<OptionsInt>();
		l.add(new OptionsInt(0, this.getMessageSource().getMessage("bbscs.usesystem", null, locale)));
		for (int i = 0; i < ppns.length; i++) {
			l.add(new OptionsInt(NumberUtils.toInt(ppns[i], 10), ppns[i]));
		}
		return l;
	}

	public List<OptionsInt> getForumViewModeValues(Locale locale) {
		List<OptionsInt> l = new ArrayList<OptionsInt>();
		l.add(new OptionsInt(0, this.getMessageSource().getMessage("bbscs.viewmode0", null, locale)));
		l.add(new OptionsInt(1, this.getMessageSource().getMessage("bbscs.viewmode1", null, locale)));
		l.add(new OptionsInt(2, this.getMessageSource().getMessage("bbscs.viewmode2", null, locale)));
		return l;
	}

	public String getTitleColorValues(int haveTitleColorp, Locale locale) {
		StringBuffer sb = new StringBuffer();
		sb.append("<option value=\"0\">");
		sb.append(this.getMessageSource().getMessage("bbscs.default", null, locale));
		sb.append("</option>");
		if (haveTitleColorp == 1) {
			for (int i = 1; i < Constant.TITLECOLOR.length; i++) {
				sb.append("<option value=\"");
				sb.append(i);
				sb.append("\" class=\"titleColor");
				sb.append(i);
				sb.append("\">");
				sb.append(Constant.TITLECOLOR[i]);
				sb.append("</option>");
			}
		}
		return sb.toString();
	}

	public List<OptionsInt> getPostPriceValues(String[] prices) {
		List<OptionsInt> l = new ArrayList<OptionsInt>();
		for (int i = 0; i < prices.length; i++) {
			l.add(new OptionsInt(Integer.parseInt(prices[i]), prices[i]));
		}
		return l;
	}

}
