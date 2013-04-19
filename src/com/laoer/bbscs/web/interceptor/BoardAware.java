package com.laoer.bbscs.web.interceptor;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.web.servlet.UserCookie;
import com.laoer.bbscs.web.servlet.UserSession;

public interface BoardAware {

	//public void setBid(long bid);

	public void setBoard(Board board);

	public void setUserCookie(UserCookie userCookie);

	public void setUserSession(UserSession userSession);
}
