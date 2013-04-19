package com.laoer.bbscs.web.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SidFilter extends HttpServlet implements Filter {

	/**
	 *
	 */
	private static final long serialVersionUID = -4180822387987073861L;

	private FilterConfig filterConfig;

	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) srequest;
		HttpServletResponse response = (HttpServletResponse) sresponse;

		UserSidCookie usc = new UserSidCookie(request, response, "GRY7JWH+QEkOcPJJ2SpngKJM8hqYZEwQ", "", "/");
		String sid = usc.getSid();
		if (sid == null || sid.length() == 0) {
			sid = java.util.UUID.randomUUID().toString();
			usc.addSid(sid);
		}

		long nowTime = System.currentTimeMillis();
		if (usc.getLastActiveTime() != 0) {
			long addedTime = nowTime - usc.getLastActiveTime();
			usc.setAddedOnlineHour(usc.getAddedOnlineTime() + addedTime);
			usc.setAddedOnlineTime(usc.getAddedOnlineHour() + addedTime);
			usc.setLastActiveTime(nowTime);
			usc.addCookiesArray(-1);
		}
		else {
			usc.addLastActiveTime();
		}

		/*
		Cookie cookies[] = request.getCookies();
		Cookie sCookie = null;
		boolean ishavesid = false;
		String sid = "";
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				sCookie = cookies[i];
				if (sCookie.getName().equals("sid")) {
					ishavesid = true;
					sid = sCookie.getValue();
				}
			}
		}

		if (!ishavesid) {
			sid = java.util.UUID.randomUUID().toString();
			Cookie mycookies = new Cookie("sid", sid);
			mycookies.setMaxAge(-1);
			response.addCookie(mycookies);
		}*/

		// System.out.println("request.getSession():"+request.getSession());

		//chain.doFilter(new HttpServletRequestWrapper(request, sid), sresponse);
		chain.doFilter(srequest, sresponse);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
