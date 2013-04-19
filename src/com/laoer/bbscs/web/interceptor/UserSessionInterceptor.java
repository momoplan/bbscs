package com.laoer.bbscs.web.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.servlet.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.commons.logging.*;
//import com.laoer.bbscs.service.*;

public class UserSessionInterceptor extends AbstractInterceptor {

	private static final Log logger = LogFactory.getLog(UserSessionInterceptor.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 783164607193227719L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();
		Object action = invocation.getAction();

		if (action instanceof UserSessionAware) {
			HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) ac.get(ServletActionContext.HTTP_RESPONSE);

			ServletContext servletContext = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
			WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(servletContext);

			if (wc == null) {
				logger.error("ApplicationContext could not be found.");
			} else {
				SysConfig sysConfig = (SysConfig) wc.getBean("sysConfig");
				UserCookie userCookie = new UserCookie(request, response, sysConfig);
				((UserSessionAware) action).setUserCookie(userCookie);
				// Cache userSessionCache =
				// (Cache)wc.getBean("userSessionCache");
				// UserSession us =
				// (UserSession)userSessionCache.get(userCookie.getUserName());

				UserSession us = (UserSession) ac.getSession().get(Constant.USER_SESSION_KEY);
				/*
				if (us == null) {
					UserService userService = (UserService) wc.getBean("userService");
					us = userService.getUserSession(userCookie.getUserName());
					userSessionCache.add(userCookie.getUserName(), us);
				}*/
				((UserSessionAware) action).setUserSession(us);
			}
		}

		return invocation.invoke();
	}

}
