package com.laoer.bbscs.web.interceptor;

import com.laoer.bbscs.web.servlet.UserCookie;
import com.laoer.bbscs.web.servlet.UserSession;

public interface UserSessionAware {

	public void setUserCookie(UserCookie userCookie);

	public void setUserSession(UserSession userSession);

}
