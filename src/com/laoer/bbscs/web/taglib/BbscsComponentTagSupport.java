package com.laoer.bbscs.web.taglib;

import org.apache.struts2.views.jsp.StrutsBodyTagSupport;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.*;

import org.apache.struts2.components.Component;
import org.apache.struts2.dispatcher.Dispatcher;

import com.opensymphony.xwork2.inject.Container;
import com.opensymphony.xwork2.util.ValueStack;

public abstract class BbscsComponentTagSupport extends StrutsBodyTagSupport {

	protected Component component;

	public abstract Component getBean(ValueStack stack, PageContext pageContext);

	public int doEndTag() throws JspException {
		component.end(pageContext.getOut(), getBody());
		component = null;
		return EVAL_PAGE;
	}

	public int doStartTag() throws JspException {
		component = getBean(getStack(), pageContext);
		Container container = Dispatcher.getInstance().getContainer();
		container.inject(component);

		populateParams();
		boolean evalBody = component.start(pageContext.getOut());

		if (evalBody) {
			return component.usesBody() ? EVAL_BODY_BUFFERED : EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}

	protected void populateParams() {
		component.setId(id);
	}

	public Component getComponent() {
		return component;
	}
}
