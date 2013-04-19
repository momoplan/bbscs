package com.laoer.bbscs.web.servlet;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laoer.bbscs.comm.DES;
import com.laoer.bbscs.comm.Util;

public class UserSidCookie {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(UserSidCookie.class);

	private static final String BBSCS_SID = "sid";

	private static final String BBSCS_SESSIONTIME_KEY = "ST";

	private HttpServletRequest request;

	private HttpServletResponse response;

	private DES des;

	private String cookieKey = "";

	private String cookieDomain = "";

	private String cookiePath = "/";

	private String sid = "";

	private long[] sessiontime = { 0, 0, 0 };

	public UserSidCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey,
			String cookieDomain, String cookiePath) {
		this.request = request;
		this.response = response;
		this.cookieKey = cookieKey;
		this.cookieDomain = cookieDomain;
		this.cookiePath = cookiePath;
		try {
			des = new DES(DES._DESede);
		} catch (Exception ex) {
			logger.error(ex);
		}
		getCookies();
	}

	private void getCookies() {
		Cookie cookies[] = request.getCookies();
		Cookie sCookie = null;
		byte[] buf;
		try {
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					sCookie = cookies[i];

					if (sCookie.getName().equals(BBSCS_SID)) {
						if (StringUtils.isNotBlank(sCookie.getValue())) {
							this.sid = sCookie.getValue();
						}
					}

					if (sCookie.getName().equals(BBSCS_SESSIONTIME_KEY)) {
						if (StringUtils.isNotBlank(sCookie.getValue())) {
							buf = Util.base64decodebyte(sCookie.getValue());
							byte[] dec = des.decode(buf, Util.base64decodebyte(this.cookieKey));
							String latmp = new String(dec);
							String[] cks = latmp.split(",");
							if (cks != null && cks.length == sessiontime.length) {
								for (int j = 0; j < cks.length; j++) {
									sessiontime[j] = NumberUtils.toLong(cks[j], 0);
								}
							}
						}
					}

				}
			}
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	public void addC(String name, String value) {
		Cookie cookies = new Cookie(name, value);
		cookies.setPath(this.cookiePath);
		cookies.setMaxAge(-1);
		// cookies.setMaxAge(30 * 60);
		if (StringUtils.isNotBlank(this.cookieDomain)) {
			cookies.setDomain(this.cookieDomain);
		}
		this.response.addCookie(cookies);
	}

	public void addDES(String name, String value) {
		try {
			// DES des = new DES(DES._DESede);
			des.setKey(Util.base64decodebyte(this.cookieKey));
			byte[] enc = des.encode(value.getBytes());
			value = Util.base64Encode(enc);
			Cookie cookies = new Cookie(name, value);
			cookies.setPath(this.cookiePath);
			// cookies.setMaxAge(30 * 60);
			cookies.setMaxAge(-1);
			if (StringUtils.isNotBlank(this.cookieDomain)) {
				cookies.setDomain(this.cookieDomain);
			}
			this.response.addCookie(cookies);
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.error("addDES(String name, String value)" + ex);
		}
	}

	public void addC(String name, String value, int maxage) {
		Cookie cookies = new Cookie(name, value);
		cookies.setPath(this.cookiePath);
		cookies.setMaxAge(maxage);
		// cookies.setMaxAge(30 * 60);
		if (StringUtils.isNotBlank(this.cookieDomain)) {
			cookies.setDomain(this.cookieDomain);
		}
		this.response.addCookie(cookies);
	}

	public void addDES(String name, String value, int maxage) {
		try {
			// DES des = new DES(DES._DESede);
			des.setKey(Util.base64decodebyte(this.cookieKey));
			byte[] enc = des.encode(value.getBytes());
			value = Util.base64Encode(enc);
			Cookie cookies = new Cookie(name, value);
			cookies.setPath(this.cookiePath);
			// cookies.setMaxAge(30 * 60);
			cookies.setMaxAge(maxage);
			if (StringUtils.isNotBlank(this.cookieDomain)) {
				cookies.setDomain(this.cookieDomain);
			}
			this.response.addCookie(cookies);
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.error("addDES(String name, String value)" + ex);
		}
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void addSid(String value) {
		this.addC(BBSCS_SID, value);
	}

	public void addCookiesArray(int maxage) {
		StringBuffer sb = new StringBuffer();
		int len = this.sessiontime.length;
		for (int i = 0; i < len; i++) {
			sb.append(this.sessiontime[i]);
			if (i != (len - 1)) {
				sb.append(",");
			}
		}
		this.addDES(BBSCS_SESSIONTIME_KEY, sb.toString(), maxage);
	}

	public long getLastActiveTime() {
		return this.sessiontime[0];
	}

	public void setLastActiveTime(long time) {
		this.sessiontime[0] = time;
	}

	public long getAddedOnlineTime() {
		return this.sessiontime[1];
	}

	public void setAddedOnlineTime(long time) {
		this.sessiontime[1] = time;
	}

	public long getAddedOnlineHour() {
		return this.sessiontime[2];
	}

	public void setAddedOnlineHour(long time) {
		this.sessiontime[2] = time;
	}

	public void addLastActiveTime() {
		this.setLastActiveTime(System.currentTimeMillis());
		this.addCookiesArray(-1);
	}

	public void addAddedOnlineTime(long time) {
		this.setAddedOnlineTime(time);
		this.addCookiesArray(-1);
	}

	public void addAddedOnlineHour(long time) {
		this.setAddedOnlineHour(time);
		this.addCookiesArray(-1);
	}

}
