package com.laoer.bbscs.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.web.interceptor.UserSessionAware;
import com.laoer.bbscs.web.servlet.UserCookie;
import com.laoer.bbscs.web.servlet.UserSession;

public class BaseMainAction extends BaseAction implements UserSessionAware {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(BaseMainAction.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -8127629884045958732L;

	private UserCookie userCookie;

	private UserSession userSession;

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			logger.error(e);
			this.addActionError(this.getText("error.msg"));
			return ERROR;
		}
	}

	public UserCookie getUserCookie() {
		return userCookie;
	}

	public void setUserCookie(UserCookie userCookie) {
		this.userCookie = userCookie;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

}
