package com.laoer.bbscs.web.servlet;

import javax.servlet.http.*;
import com.laoer.bbscs.service.config.*;
import com.laoer.bbscs.comm.*;
import com.laoer.bbscs.bean.*;
//import org.apache.commons.lang.math.NumberUtils;

import org.apache.commons.lang.*;
import org.apache.commons.logging.*;

//import java.net.*;

public class UserCookie {

	private static final Log logger = LogFactory.getLog(UserCookie.class);

	private static final String PASS_USERNAME_KEY = "PASS_USERNAME";

	private static final String PASS_USERNAME_DES_KEY = "PASS_USERNAME_DES";

	private static final String BBSCS_FORUMPERNUM_KEY = "FN";

	private static final String BBSCS_POSTPERNUM_KEY = "PN";

	private static final String BBSCS_TIMEZONE_KEY = "TZ";

	private static final String BBSCS_FORUMVIEWMODE_KEY = "VM";

	private static final String BBSCS_LASTSENDNOTETIME_KEY = "LN";

	private static final String BBSCS_LASTPOSTTIME_KEY = "LP";

	private static final String BBSCS_EDITTYPE = "ET";

	private static final String BBSCS_AUTHCODE = "AC";

	private static final String BBSCS_USERNAME = "U";

	private static final String BBSCS_PASSWD = "P";

	//private static final String BBSCS_SID = "sid";

	//private static final String BBSCS_SESSIONTIME_KEY = "ST";

	private HttpServletRequest request;

	private HttpServletResponse response;

	private SysConfig sysConfig;

	private DES des;

	private int postPerNum = 10;

	private int forumPerNum = 20;

	private int forumViewMode = 0;

	private String timeZone = "GMT+08:00";

	private String pusername = "";

	private String pusernamedes = "";

	private long lastSendNoteTime = 0;

	private long lastPostTime = 0;

	private int editType = 0;

	private String authCode = "";

	private String userName = "";

	private String passwd = "";

	//private String sid = "";

	//private long[] sessiontime = { 0, 0, 0 };

	public UserCookie(HttpServletRequest request, HttpServletResponse response, SysConfig sysConfig) {
		this.request = request;
		this.response = response;
		this.sysConfig = sysConfig;
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
					if (this.sysConfig.isUsePass()) {
						if (sCookie.getName().equals(PASS_USERNAME_KEY)) {
							this.pusername = sCookie.getValue();
							// System.out.println("pass username:" + username);
						}
						if (sCookie.getName().equals(PASS_USERNAME_DES_KEY)) {
							if (StringUtils.isNotBlank(sCookie.getValue())) {
								buf = Util.base64decodebyte(java.net.URLDecoder.decode(sCookie.getValue(), "UTF-8") );
								byte[] dec = des.decode(buf, Util.base64decodebyte(this.sysConfig.getCookieKey()));
								this.pusernamedes = new String(dec);
								// System.out.println("pass usernamedes:" +
								// usernamedes);
							}
						}
					}
					if (sCookie.getName().equals(BBSCS_FORUMPERNUM_KEY)) {
						this.forumPerNum = Integer.parseInt(sCookie.getValue());
						// System.out.println("this.formPerNum
						// "+this.formPerNum);
					}
					if (sCookie.getName().equals(BBSCS_POSTPERNUM_KEY)) {
						this.postPerNum = Integer.parseInt(sCookie.getValue());
					}
					if (sCookie.getName().equals(BBSCS_TIMEZONE_KEY)) {
						this.timeZone = Util.base64decode(sCookie.getValue());
					}
					if (sCookie.getName().equals(BBSCS_FORUMVIEWMODE_KEY)) {
						this.forumViewMode = Integer.parseInt(sCookie.getValue());

					}
					if (sCookie.getName().equals(BBSCS_EDITTYPE)) {
						this.editType = Integer.parseInt(sCookie.getValue());
					}

					if (sCookie.getName().equals(BBSCS_LASTSENDNOTETIME_KEY)) {
						if (StringUtils.isNotBlank(sCookie.getValue())) {
							buf = Util.base64decodebyte(java.net.URLDecoder.decode(sCookie.getValue(),"UTF-8"));
							byte[] dec = des.decode(buf, Util.base64decodebyte(this.sysConfig.getCookieKey()));
							this.lastSendNoteTime = Long.parseLong(new String(dec));
						}
					}
					if (sCookie.getName().equals(BBSCS_LASTPOSTTIME_KEY)) {
						if (StringUtils.isNotBlank(sCookie.getValue())) {
							//buf = Util.base64decodebyte(sCookie.getValue());
							buf = Util.base64decodebyte(java.net.URLDecoder.decode(sCookie.getValue(),"UTF-8"));
							byte[] dec = des.decode(buf, Util.base64decodebyte(this.sysConfig.getCookieKey()));
							this.lastPostTime = Long.parseLong(new String(dec));
						}
					}
					if (sCookie.getName().equals(BBSCS_AUTHCODE)) {
						if (StringUtils.isNotBlank(sCookie.getValue())) {
							//buf = Util.base64decodebyte(sCookie.getValue());
							buf = Util.base64decodebyte(java.net.URLDecoder.decode(sCookie.getValue(),"UTF-8"));
							byte[] dec = des.decode(buf, Util.base64decodebyte(this.sysConfig.getCookieKey()));
							this.authCode = new String(dec);
						}
					}
					if (sCookie.getName().equals(BBSCS_USERNAME)) {
						if (StringUtils.isNotBlank(sCookie.getValue())) {
							this.userName = sCookie.getValue();
						}
					}
					if (sCookie.getName().equals(BBSCS_PASSWD)) {
						if (StringUtils.isNotBlank(sCookie.getValue())) {
							//buf = Util.base64decodebyte(sCookie.getValue());
							buf = Util.base64decodebyte(java.net.URLDecoder.decode(sCookie.getValue(),"UTF-8"));
							byte[] dec = des.decode(buf, Util.base64decodebyte(this.sysConfig.getCookieKey()));
							this.passwd = new String(dec);
						}
					}

					/*
					if (sCookie.getName().equals(BBSCS_SID)) {
						if (StringUtils.isNotBlank(sCookie.getValue())) {
							this.sid = sCookie.getValue();
						}
					}


					if (sCookie.getName().equals(BBSCS_SESSIONTIME_KEY)) {
						if (StringUtils.isNotBlank(sCookie.getValue())) {
							buf = Util.base64decodebyte(sCookie.getValue());
							byte[] dec = des.decode(buf, Util.base64decodebyte(this.sysConfig.getCookieKey()));
							String latmp = new String(dec);
							String[] cks = latmp.split(",");
							if (cks != null && cks.length == sessiontime.length) {
								for (int j = 0; j < cks.length; j++) {
									sessiontime[j] = NumberUtils.toLong(cks[j], 0);
								}
							}
						}
					}*/

				}
			}
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	public void addC(String name, String value) {
		Cookie cookies = new Cookie(name, value);
		cookies.setPath(this.sysConfig.getCookiePath());
		cookies.setMaxAge(-1);
		// cookies.setMaxAge(30 * 60);
		if (StringUtils.isNotBlank(this.sysConfig.getCookieDomain())) {
			cookies.setDomain(this.sysConfig.getCookieDomain());
		}
		this.response.addCookie(cookies);
	}

	public void addDES(String name, String value) {
		try {
			// DES des = new DES(DES._DESede);
			des.setKey(Util.base64decodebyte(this.sysConfig.getCookieKey()));
			byte[] enc = des.encode(value.getBytes());
			value = java.net.URLEncoder.encode(Util.base64Encode(enc), "UTF-8") ;
			Cookie cookies = new Cookie(name, value);
			cookies.setPath(this.sysConfig.getCookiePath());
			// cookies.setMaxAge(30 * 60);
			cookies.setMaxAge(-1);
			if (StringUtils.isNotBlank(this.sysConfig.getCookieDomain())) {
				cookies.setDomain(this.sysConfig.getCookieDomain());
			}
			this.response.addCookie(cookies);
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.error("addDES(String name, String value)" + ex);
		}
	}

	public void addC(String name, String value, int maxage) {
		Cookie cookies = new Cookie(name, value);
		cookies.setPath(this.sysConfig.getCookiePath());
		cookies.setMaxAge(maxage);
		// cookies.setMaxAge(30 * 60);
		if (StringUtils.isNotBlank(this.sysConfig.getCookieDomain())) {
			cookies.setDomain(this.sysConfig.getCookieDomain());
		}
		this.response.addCookie(cookies);
	}

	public void addDES(String name, String value, int maxage) {
		try {
			// DES des = new DES(DES._DESede);
			des.setKey(Util.base64decodebyte(this.sysConfig.getCookieKey()));
			byte[] enc = des.encode(value.getBytes());
			//value = Util.base64Encode(enc);
			value = java.net.URLEncoder.encode(Util.base64Encode(enc), "UTF-8") ;
			Cookie cookies = new Cookie(name, value);
			cookies.setPath(this.sysConfig.getCookiePath());
			// cookies.setMaxAge(30 * 60);
			cookies.setMaxAge(maxage);
			if (StringUtils.isNotBlank(this.sysConfig.getCookieDomain())) {
				cookies.setDomain(this.sysConfig.getCookieDomain());
			}
			this.response.addCookie(cookies);
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.error("addDES(String name, String value)" + ex);
		}
	}

	public void addCookies(UserInfo ui) {
		this.forumPerNum = ui.getForumPerNum();
		addC(BBSCS_FORUMPERNUM_KEY, String.valueOf(ui.getForumPerNum()), -1);

		this.postPerNum = ui.getPostPerNum();
		addC(BBSCS_POSTPERNUM_KEY, String.valueOf(ui.getPostPerNum()), -1);

		this.timeZone = ui.getTimeZone();
		addC(BBSCS_TIMEZONE_KEY, Util.base64Encode(ui.getTimeZone()), -1);

		this.forumViewMode = ui.getForumViewMode();
		addC(BBSCS_FORUMVIEWMODE_KEY, String.valueOf(ui.getForumViewMode()), -1);

		this.editType = ui.getEditType();
		addC(BBSCS_EDITTYPE, String.valueOf(ui.getEditType()), -1);

		// addLastActiveTime();
	}

	public void addGuestCookies() {
		this.forumPerNum = 20;
		addC(BBSCS_FORUMPERNUM_KEY, String.valueOf(this.forumPerNum), -1);
		this.postPerNum = 10;
		addC(BBSCS_POSTPERNUM_KEY, String.valueOf(this.postPerNum), -1);
		this.timeZone = "GMT+08:00";
		addC(BBSCS_TIMEZONE_KEY, Util.base64Encode(this.timeZone), -1);
		this.forumViewMode = 0;
		addC(BBSCS_FORUMVIEWMODE_KEY, String.valueOf(this.forumViewMode), -1);
		this.editType = -1;
		addC(BBSCS_EDITTYPE, String.valueOf(this.editType), -1);

		// addLastActiveTime();
	}

	public void addLastNoteSendTime() {
		this.lastSendNoteTime = System.currentTimeMillis();
		addDES(BBSCS_LASTSENDNOTETIME_KEY, String.valueOf(this.lastSendNoteTime), -1);
	}

	public void addLastPostTime() {
		this.lastPostTime = System.currentTimeMillis();
		addDES(BBSCS_LASTPOSTTIME_KEY, String.valueOf(this.lastPostTime), -1);
	}

	/*
	 * public void addLastActiveTime() { this.lastActiveTime =
	 * System.currentTimeMillis(); addDES(BBSCS_LASTACTIVETIM_KEY,
	 * String.valueOf(this.lastActiveTime), -1); }
	 */

	public void addForumViewMode(int vm) {
		this.forumViewMode = vm;
		addC(BBSCS_FORUMVIEWMODE_KEY, String.valueOf(vm), -1);
	}

	public void addAuthCode(String authCode) {
		this.addDES(BBSCS_AUTHCODE, authCode, -1);
	}

	public void removeAuthCode() {
		this.addC(BBSCS_AUTHCODE, "", 0);
	}

	public void removeAllCookies() {
		addC(BBSCS_FORUMPERNUM_KEY, "", 0);
		addC(BBSCS_POSTPERNUM_KEY, "", 0);
		addC(BBSCS_TIMEZONE_KEY, "", 0);
		// addC(BBSCS_FORUMVIEWMODE_KEY, "", 0);
		addC(BBSCS_LASTSENDNOTETIME_KEY, "", 0);
		addC(BBSCS_LASTPOSTTIME_KEY, "", 0);
		addC(BBSCS_FORUMVIEWMODE_KEY, "", 0);
		addC(BBSCS_EDITTYPE, "-1", 0);
		addC(BBSCS_AUTHCODE, "", 0);
		addC(BBSCS_USERNAME, "", 0);
		addC(BBSCS_PASSWD, "", 0);
	}

	public void removePassCookies() {
		addC(PASS_USERNAME_KEY, "", 0);
		addC(PASS_USERNAME_DES_KEY, "", 0);
	}

	public boolean isLoginPass() {
		if (StringUtils.isNotBlank(this.pusername) && StringUtils.isNotBlank(this.pusernamedes)
				&& this.pusername.equals(this.pusernamedes)) {
			return true;
		}
		return false;
	}

	public boolean isSaveLoginCookie() {
		if (StringUtils.isNotBlank(this.userName) && StringUtils.isNotBlank(this.passwd)) {
			return true;
		} else {
			return false;
		}
	}

	public int getPostPerNum() {
		return postPerNum;
	}

	public int getForumPerNum() {

		return forumPerNum;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public int getForumViewMode() {
		return forumViewMode;
	}

	public String getPusername() {
		return pusername;
	}

	public String getPusernamedes() {
		return pusernamedes;
	}

	public long getLastPostTime() {
		return lastPostTime;
	}

	public long getLastSendNoteTime() {
		return lastSendNoteTime;
	}

	public int getEditType() {
		return editType;
	}

	public void setPostPerNum(int postPerNum) {
		this.postPerNum = postPerNum;
	}

	public void setForumPerNum(int forumPerNum) {

		this.forumPerNum = forumPerNum;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public void setForumViewMode(int forumViewMode) {
		this.forumViewMode = forumViewMode;
	}

	public void setPusername(String pusername) {
		this.pusername = pusername;
	}

	public void setPusernamedes(String pusernamedes) {
		this.pusernamedes = pusernamedes;
	}

	public void setLastPostTime(long lastPostTime) {
		this.lastPostTime = lastPostTime;
	}

	public void setLastSendNoteTime(long lastSendNoteTime) {
		this.lastSendNoteTime = lastSendNoteTime;
	}

	public void setEditType(int editType) {
		this.editType = editType;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
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
	}*/

}
