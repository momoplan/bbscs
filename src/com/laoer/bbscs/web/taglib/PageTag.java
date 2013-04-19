package com.laoer.bbscs.web.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class PageTag extends ComponentTagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = -5371048231966321759L;

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		// TODO 自动生成方法存根
		return new Page(arg0);
	}

	protected String styleClass = "";

	protected String argPage = "page";

	protected String argTotal = "total";

	protected int pageSep = 10;

	protected String javaScript = "";

	private String value;

	public String getArgPage() {
		return argPage;
	}

	public void setArgPage(String argPage) {
		this.argPage = argPage;
	}

	public String getArgTotal() {
		return argTotal;
	}

	public void setArgTotal(String argTotal) {
		this.argTotal = argTotal;
	}

	public String getJavaScript() {
		return javaScript;
	}

	public void setJavaScript(String javaScript) {
		this.javaScript = javaScript;
	}

	public int getPageSep() {
		return pageSep;
	}

	public void setPageSep(int pageSep) {
		this.pageSep = pageSep;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	protected void populateParams() {
		super.populateParams();

		Page tag = (Page) component;
		tag.setArgPage(argPage);
		tag.setArgTotal(argTotal);
		tag.setJavaScript(javaScript);
		tag.setPageSep(pageSep);
		tag.setStyleClass(styleClass);
		tag.setValue(value);
	}

}
