package com.laoer.bbscs.web.interceptor;

import javax.servlet.http.*;
import javax.servlet.*;

import org.apache.struts2.ServletActionContext;

import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.servlet.UserCookie;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import org.springframework.web.context.*;
import org.springframework.web.context.support.*;
import org.apache.commons.logging.*;

public class UserCookieInterceptor extends AbstractInterceptor {

	private static final Log logger = LogFactory.getLog(UserCookieInterceptor.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 7415444281658181662L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();
		Object action = invocation.getAction();

		if (action instanceof UserCookieAware) {
			HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) ac.get(ServletActionContext.HTTP_RESPONSE);

			ServletContext servletContext = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
			WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			if (wc == null) {
				logger.error("ApplicationContext could not be found.");
			} else {
				SysConfig sysConfig = (SysConfig) wc.getBean("sysConfig");
				UserCookie userCookie = new UserCookie(request, response, sysConfig);
				//logger.debug("userCookie sid:" + userCookie.getSid());
				((UserCookieAware) action).setUserCookie(userCookie);
			}
		}
		return invocation.invoke();
	}

}
