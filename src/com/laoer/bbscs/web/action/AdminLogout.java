package com.laoer.bbscs.web.action;

import java.util.Map;

import com.laoer.bbscs.comm.Constant;
//import com.laoer.bbscs.service.Cache;
import com.laoer.bbscs.web.interceptor.UserCookieAware;
import com.laoer.bbscs.web.servlet.UserCookie;
//import org.apache.commons.lang.*;
import org.apache.struts2.interceptor.SessionAware;

public class AdminLogout extends BaseAction implements UserCookieAware,SessionAware {

	/**
	 *
	 */
	private static final long serialVersionUID = -9037859867589584103L;

	private UserCookie userCookie;

	//private Cache userSessionCache;

	public void setUserCookie(UserCookie userCookie) {
		this.userCookie = userCookie;
	}

	public UserCookie getUserCookie() {
		return userCookie;
	}

	/*
	public Cache getUserSessionCache() {
		return userSessionCache;
	}

	public void setUserSessionCache(Cache userSessionCache) {
		this.userSessionCache = userSessionCache;
	}
	*/

	private Map session;

	public void setSession(Map session) {
		this.session = session;
	}

	public Map getSession() {
		return session;
	}

	public String execute() {
		//if (StringUtils.isNotBlank(this.getUserCookie().getUserName())) {
		//	this.getUserSessionCache().remove(this.getUserCookie().getUserName());
		//}

		this.getSession().remove(Constant.USER_SESSION_KEY);
		this.getUserCookie().removeAllCookies();
		return SUCCESS;
	}

}
