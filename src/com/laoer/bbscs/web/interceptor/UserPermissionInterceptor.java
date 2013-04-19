package com.laoer.bbscs.web.interceptor;

import java.util.*;

//import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
//import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.laoer.bbscs.bean.Permission;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
//import com.laoer.bbscs.service.Cache;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;
//import com.laoer.bbscs.web.servlet.UserCookie;
import com.laoer.bbscs.web.servlet.UserSession;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.views.util.*;

public class UserPermissionInterceptor extends AbstractInterceptor {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(UserPermissionInterceptor.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 1857640901215440713L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		ActionContext ac = invocation.getInvocationContext();

		// HttpServletRequest request = (HttpServletRequest)
		// ac.get(ServletActionContext.HTTP_REQUEST);
		// HttpServletResponse response = (HttpServletResponse)
		// ac.get(ServletActionContext.HTTP_RESPONSE);
		// logger.debug("request:"+request+" response:" + response);

		String actionName = "/" + ac.getName();

		String ajax = "html";
		String saction = "";

		// System.out.println((request instanceof MultiPartRequestWrapper));

		// System.out.println(ac.getValueStack().findString("action"));
		/*
		 * if (request instanceof MultiPartRequestWrapper) {
		 * MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper)
		 * request; String _ajax = multiWrapper.getParameter("ajax"); if (_ajax !=
		 * null) { ajax = _ajax; } String _saction =
		 * multiWrapper.getParameter("action"); if (_saction != null) { saction =
		 * _saction; } } else { Map map = ac.getParameters();
		 *
		 * String[] _ajax = (String[]) map.get("ajax"); if (_ajax != null) {
		 * ajax = _ajax[0]; }
		 *
		 * String[] _saction = (String[]) map.get("action"); if (_saction !=
		 * null) { saction = _saction[0]; } }
		 */

		Map map = ac.getParameters();

		/*
		 * System.out.println("map size"+map.size()); Set ms = map.entrySet();
		 * Iterator it = ms.iterator(); while (it.hasNext()) { Map.Entry e =
		 * (Map.Entry) it.next(); System.out.println(e.getKey());
		 * System.out.println(e.getValue()); }
		 */

		String[] _ajax = (String[]) map.get("ajax");
		if (_ajax != null) {
			ajax = _ajax[0];
		}

		String[] _saction = (String[]) map.get("action");
		if (_saction != null) {
			saction = _saction[0];
		}

		logger.debug("[actionName:" + actionName + ",saction:" + saction + ",ajax:" + ajax + "]");

		boolean havePermission = false;

		ServletContext servletContext = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		if (wc == null) {
			logger.error("ApplicationContext could not be found.");
			return "intercepthtml";
		} else {
			// UserCookie userCookie = new UserCookie(request, response,
			// sysConfig);
			// Cache userSessionCache = (Cache) wc.getBean("userSessionCache");

			// UserSession us = (UserSession)
			// userSessionCache.get(userCookie.getUserName());

			UserSession us = (UserSession) ac.getSession().get(Constant.USER_SESSION_KEY);

			Permission permission = (Permission) us.getUserPermission().get(actionName + "?action=*");
			if (permission != null) {
				havePermission = true;
			} else {
				permission = (Permission) us.getUserPermission().get(actionName + "?action=" + saction);
				if (permission != null) {
					havePermission = true;
				} else {
					havePermission = false;
				}
			}
			if (havePermission) {
				return invocation.invoke();
			} else {

				HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
				StringBuffer sb = new StringBuffer();
				sb.append(BBSCSUtil.getWebRealPath(request));
				sb.append(request.getContextPath());
				sb.append("/");
				sb.append(BBSCSUtil.getActionMappingURLWithoutPrefix(ac.getName()));
				UrlHelper.buildParametersString(map, sb, "&");

				String curl = sb.toString();

				SysConfig sysConfig = (SysConfig) wc.getBean("sysConfig");

				ResourceBundleMessageSource messageSource = (ResourceBundleMessageSource) wc.getBean("messageSource");

				if (ajax.equalsIgnoreCase("html")) {
					String errorMsg = messageSource.getMessage("error.noPermission", null, ac.getLocale());
					ac.getValueStack().set("interceptError", errorMsg);
					ac.getValueStack().set("tourl", curl);
					ac.getValueStack().set("useAuthCode", sysConfig.isUseAuthCode());
					if (sysConfig.getUsePass() == 0) {
						return "intercepthtml";
					} else {
						ac.getValueStack().set("actionUrl", sysConfig.getPassUrl());
						return "intercepthtmlpass";
					}
				} else if (ajax.equalsIgnoreCase("shtml")) {
					String errorMsg = messageSource.getMessage("error.noPermission.ajax", null, ac.getLocale());
					ac.getValueStack().set("interceptError", errorMsg);
					return "interceptshtml";
				} else {
					String errorMsg = messageSource.getMessage("error.noPermission.ajax", null, ac.getLocale());
					AjaxMessagesJson ajaxMessagesJson = (AjaxMessagesJson) wc.getBean("ajaxMessagesJson");
					ajaxMessagesJson.setMessage("E_NO_Permission", errorMsg);
					ac.getValueStack().set("ajaxMessagesJson", ajaxMessagesJson);
					return "ajaxjson";
				}
			}
		}
	}

}
