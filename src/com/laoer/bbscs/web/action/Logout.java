package com.laoer.bbscs.web.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.*;
import org.apache.struts2.interceptor.SessionAware;

import com.laoer.bbscs.bean.UserOnline;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.comm.Util;
import com.laoer.bbscs.exception.BbscsException;
//import com.laoer.bbscs.service.Cache;
import com.laoer.bbscs.service.UserOnlineService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.interceptor.RequestBasePathAware;
import com.laoer.bbscs.web.interceptor.UserSessionAware;
import com.laoer.bbscs.web.servlet.UserCookie;
import com.laoer.bbscs.web.servlet.UserSession;

public class Logout extends BaseAction implements UserSessionAware, RequestBasePathAware, SessionAware {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(Logout.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 3296047153044103588L;

	private UserCookie userCookie;

	private UserSession userSession;

	private String basePath;

	public void setUserCookie(UserCookie userCookie) {
		this.userCookie = userCookie;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	private SysConfig sysConfig;

	private UserOnlineService userOnlineService;

	// private Cache userSessionCache;

	private String logoutUrl;

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public UserOnlineService getUserOnlineService() {
		return userOnlineService;
	}

	public void setUserOnlineService(UserOnlineService userOnlineService) {
		this.userOnlineService = userOnlineService;
	}

	/*
	 * public Cache getUserSessionCache() { return userSessionCache; }
	 *
	 * public void setUserSessionCache(Cache userSessionCache) {
	 * this.userSessionCache = userSessionCache; }
	 */

	private Map session;

	public void setSession(Map session) {
		this.session = session;
	}

	public Map getSession() {
		return session;
	}

	public String execute() {
		UserOnline uo = this.getUserOnlineService().findUserOnlineByUserID(userSession.getId());
		if (uo != null) {
			try {
				this.getUserOnlineService().removeUserOnline(uo);
			} catch (BbscsException e) {
				logger.error(e);
			}
		}
		// this.getUserSessionCache().remove(userCookie.getUserName());
		this.getSession().remove(Constant.USER_SESSION_KEY);
		userCookie.removeAllCookies();
		String url = request.getParameter("url");
		logger.debug("得到退出请求的地址url="+url);
		//得到用户
		Util.logoutUser(request);
		request.getSession().invalidate();

		if (StringUtils.isNotBlank(this.getAction()) && this.getAction().equalsIgnoreCase("pass")) {
			userCookie.removePassCookies();
		}

		if (StringUtils.isBlank(this.getSysConfig().getLogoutUrl())
				|| this.getSysConfig().getLogoutUrl().startsWith("/")) {
			//String url = this.getSysConfig().getLogoutUrl().substring(1, this.getSysConfig().getLogoutUrl().length());
			//url = BBSCSUtil.getActionMappingURLWithoutPrefix(url);
			url = "http://users.ruyicai.com/login.jsp?reqUrl=http://bbs.ruyicai.com/login.bbscs?action=login";

			this.setLogoutUrl(url);
		} else {
			this.setLogoutUrl(this.getSysConfig().getLogoutUrl());
		}
		return SUCCESS;
	}
	

}
