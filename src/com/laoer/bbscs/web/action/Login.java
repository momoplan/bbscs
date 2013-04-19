package com.laoer.bbscs.web.action;

import com.laoer.bbscs.service.*;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.service.mail.TemplateMail;
import com.laoer.bbscs.comm.*;
import com.laoer.bbscs.web.interceptor.*;
import com.laoer.bbscs.web.servlet.UserCookie;
import com.laoer.bbscs.web.servlet.UserSession;
import com.laoer.bbscs.web.ui.RadioInt;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.exception.*;

import net.sf.json.JSONObject;

import org.apache.commons.logging.*;
import org.apache.commons.lang.*;
import org.apache.struts2.interceptor.SessionAware;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Login extends BaseAction implements RequestBasePathAware, RemoteAddrAware, UserCookieAware, SessionAware {

	private static final Log logger = LogFactory.getLog(Login.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 9023754752433923497L;

	public Login() {
		this.setRadioYesNoListValues();
		this.setCookieTimeListValues();
	}

	private SysConfig sysConfig;

	private UserService userService;

	private LoginErrorService loginErrorService;

	private UserOnlineService userOnlineService;
	

	// private Cache userSessionCache;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	private String actionUrl;

	private String tourl;
	
	/*private String password;

	private String userName;*/

	private int hiddenLogin;

	private String authCode;

	private boolean urlRewrite = false;

	private boolean useAuthCode = true;

	private int cookieTime = -1;
	
	private IPSeeker ipSeeker;
	
	private String userRemoteAddr = "";
	
	private TemplateMail templateMail;

	private SysStatService sysStatService;
	
	
	public IPSeeker getIpSeeker() {
		return ipSeeker;
	}

	public void setIpSeeker(IPSeeker ipSeeker) {
		this.ipSeeker = ipSeeker;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getTourl() {
		return tourl;
	}

	public void setTourl(String tourl) {
		this.tourl = tourl;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public int getHiddenLogin() {
		return hiddenLogin;
	}

	public void setHiddenLogin(int hiddenLogin) {
		this.hiddenLogin = hiddenLogin;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.userRemoteAddr = remoteAddr;
	}
	
	public String getUserRemoteAddr() {
		return userRemoteAddr;
	}

	public void setUserRemoteAddr(String userRemoteAddr) {
		this.userRemoteAddr = userRemoteAddr;
	}
	/*public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}*/

	public SysStatService getSysStatService() {
		return sysStatService;
	}

	public void setSysStatService(SysStatService sysStatService) {
		this.sysStatService = sysStatService;
	}
	
	public TemplateMail getTemplateMail() {
		return templateMail;
	}

	public void setTemplateMail(TemplateMail templateMail) {
		this.templateMail = templateMail;
	}

	private UserCookie userCookie;

	
	public void setUserCookie(UserCookie userCookie) {
		this.userCookie = userCookie;
	}

	public UserCookie getUserCookie() {
		return userCookie;
	}

	private String basePath;

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBasePath() {
		return basePath;
	}
	
	List<RadioInt> radioYesNoList = new ArrayList<RadioInt>();

	private void setRadioYesNoListValues() {
		radioYesNoList.add(new RadioInt(0, this.getText("bbscs.no")));
		radioYesNoList.add(new RadioInt(1, this.getText("bbscs.yes")));
	}

	public List<RadioInt> getRadioYesNoList() {
		return radioYesNoList;
	}

	public void setRadioYesNoList(List<RadioInt> radioYesNoList) {
		this.radioYesNoList = radioYesNoList;
	}

	List<RadioInt> cookieTimeList = new ArrayList<RadioInt>();

	private void setCookieTimeListValues() {
		cookieTimeList.add(new RadioInt(365 * 24 * 3600, this.getText("login.cookietime0")));
		cookieTimeList.add(new RadioInt(30 * 24 * 3600, this.getText("login.cookietime1")));
		cookieTimeList.add(new RadioInt(24 * 3600, this.getText("login.cookietime2")));
		cookieTimeList.add(new RadioInt(-1, this.getText("login.cookietime3")));
	}

	public List<RadioInt> getCookieTimeList() {
		return cookieTimeList;
	}

	public void setCookieTimeList(List<RadioInt> cookieTimeList) {
		this.cookieTimeList = cookieTimeList;
	}

	public boolean isUrlRewrite() {
		return urlRewrite;
	}

	public void setUrlRewrite(boolean urlRewrite) {
		this.urlRewrite = urlRewrite;
	}

	private Map session;

	public void setSession(Map session) {
		this.session = session;
	}

	public Map getSession() {
		return session;
	}

	private void setUserAuthCodeValue() {
		this.setUseAuthCode(this.getSysConfig().isUseAuthCode());
	}

	public String execute() {
		this.setUrlRewrite(Constant.USE_URL_REWRITE);
		this.setUserAuthCodeValue();
		// setRadioYesNoListValues();
		if (this.getAction().equalsIgnoreCase("index")) {
			this.setAction("login");
			this.setHiddenLogin(0);

			// this.setTourl("main");
			if (Constant.USE_URL_REWRITE) {
				// tourl = BBSCSUtil.absoluteActionURLHtml(request,
				// "/main.html").toString();
				tourl = this.getBasePath() + "main.html";
			} else {
				// tourl = BBSCSUtil.absoluteActionURL(request,
				// "/main").toString();
				tourl = this.getBasePath() + BBSCSUtil.getActionMappingURLWithoutPrefix("main");
			}

			return this.input();
		}
		if (this.getAction().equalsIgnoreCase("admin")) {
			this.setAction("login");
			this.setHiddenLogin(0);
			// tourl = BBSCSUtil.absoluteActionURL(request,
			// "/adminMain").toString();
			tourl = this.getBasePath() + BBSCSUtil.getActionMappingURLWithoutPrefix("adminMain");

			return this.input();
		}
		if (this.getAction().equalsIgnoreCase("relogin")) {
			this.setAction("login");
			this.setHiddenLogin(0);

			if (Constant.USE_URL_REWRITE) {
				tourl = this.getBasePath() + "main.html";
			} else {
				tourl = this.getBasePath() + BBSCSUtil.getActionMappingURLWithoutPrefix("main");
			}
			this.addActionError(this.getText("error.login.re"));
			return this.input();
		}

		if (this.getAction().equalsIgnoreCase("login")) {
			try {
				return this.login();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (this.getAction().equalsIgnoreCase("check")) {
			return this.check();
		}

		return this.input();
	}

	public String index() {
		this.setAction("login");
		this.setHiddenLogin(0);
		if (Constant.USE_URL_REWRITE) {
			tourl = this.getBasePath() + "main.html";
		} else {
			tourl = this.getBasePath() + BBSCSUtil.getActionMappingURLWithoutPrefix("main");
		}

		return this.input();
	}

	public String check() {
		if (StringUtils.isNotBlank(this.getUserCookie().getUserName())
				&& StringUtils.isNotBlank(this.getUserCookie().getPasswd())) {
			return this.cookieLogin();
		} else {
			return this.index();
		}
	}

	public String input() {
		if (this.getSysConfig().getUsePass() == 0) {
			return INPUT;
		} else {
			this.setActionUrl(this.getSysConfig().getPassUrl());
			return "loginPass";
		}
	}
	
	@SuppressWarnings("unchecked")
	public String login() throws IOException {
		
		JSONObject returnObj = Util.getUserNo(request);
		/*String username = request.getParameter("userName");
		String pass = request.getParameter("password");
		this.setPassword((String) request.getAttribute("pass"));
		this.setUserName((String) request.getAttribute("userName"));*/
		logger.info("获取userno得到的返回数据是："+returnObj);
		String userno = "";
		if("0".equals(returnObj.getString("errorCode"))){
			userno = returnObj.getString("value");
		}
		
		//根据userno查bbs库里是否有用户，无用户增加用户信息；有用户略过增加操作(对于如意彩用户如果是第一次登录bbs，要在bbs库里插入此用户信息)
		UserInfo ui = this.getUserService().findUserInfoByUserNo(userno);
		
		if(ui==null || "".equals(ui)){
			JSONObject objUser = Util.getUserInfo(request).getJSONObject("value");
			
			UserInfo newUser = this.getUserService().findUserInfoByUserName(objUser.getString("userName"));

			if (newUser != null) {
				this.addActionError(this.getText("error.reg.name1"));
				return ERROR;
			}

			if(objUser.getString("email").length()<1||!objUser.getString("email").equals("null")){
				newUser = this.getUserService().findUserInfoByEmail(objUser.getString("email"));
				if (newUser != null) {
					this.addActionError(this.getText("error.reg.emailerror"));
					return ERROR;
				}
			}
			
			newUser = new UserInfo();
			
			newUser.setUserNo(objUser.getString("userno"));
			newUser.setAcceptFriend(1);
			newUser.setAnswer("");
			newUser.setArticleEliteNum(0);
			newUser.setArticleNum(0);
			newUser.setBirthDay(1);
			newUser.setBirthMonth(1);
			newUser.setBirthYear(1980);
			newUser.setEmail(objUser.getString("email"));
			newUser.setExperience(0);
			newUser.setForumPerNum(0);
			newUser.setForumViewMode(0);
			newUser.setHavePic(0);
			newUser.setLastLoginIP("0.0.0.0");
			newUser.setLastLoginTime(new Date());
			newUser.setLifeForce(0);
			newUser.setLiterary(0);
			newUser.setLoginIP("0.0.0.0");
			newUser.setLoginTime(new Date());
			newUser.setLoginTimes(0);
			newUser.setNickName(objUser.getString("nickname").equals("null")?objUser.getString("userName"):objUser.getString("nickname")==null?objUser.getString("userName"):objUser.getString("nickname")); 
			newUser.setPasswd(objUser.getString("password"));
			newUser.setPicFileName("");
			newUser.setPostPerNum(0);
			newUser.setQuestion("");
			newUser.setReceiveNote(1);
			newUser.setRegTime(new Date());
			newUser.setRePasswd(objUser.getString("password"));
			newUser.setSignDetail0(this.getText("bbscs.userdefaultsign"));
			newUser.setSignDetail1(this.getText("bbscs.userdefaultsign"));
			newUser.setSignDetail2(this.getText("bbscs.userdefaultsign"));
			newUser.setSignName0("A");
			newUser.setSignName1("B");
			newUser.setSignName2("C");
			newUser.setStayTime(0);
			newUser.setTimeZone("GMT+08:00");
			newUser.setUserFrom(this.getIpSeeker().getCountry(this.getUserRemoteAddr()));
			newUser.setUserKnow(0);
			newUser.setUserName(objUser.getString("userName"));
			newUser.setUserTitle(0);
			if (this.getSysConfig().isCheckRegUser() || this.getSysConfig().isCheckRegUserEmail()) {
				newUser.setValidated(0);
				newUser.setGroupID(Constant.USER_GROUP_UNVUSER);
			} else {
				newUser.setValidated(1);
				newUser.setGroupID(Constant.USER_GROUP_REGUSER);
			}
			newUser.setEditType(-1);
			newUser.setUserLocale(this.getLocale().toString());
			newUser.setValidateCode(RandomStringUtils.randomAlphanumeric(10));
			newUser.setCoin(100);

			UserDetail ud = new UserDetail();
			ud.setBrief("");
			ud.setDreamJob("");
			ud.setDreamLover("");
			ud.setFavourArt("");
			ud.setFavourBook("");
			ud.setFavourChat("");
			ud.setFavourMovie("");
			ud.setFavourMusic("");
			ud.setFavourPeople("");
			ud.setFavourTeam("");
			ud.setGraduate("");
			ud.setHeight("");
			ud.setHomePage("");
			ud.setIcqNo("");
			ud.setInterest("");
			ud.setMsn("");
			ud.setOicqNo("");
			ud.setSex((short) 0);
			ud.setWeight("");
			ud.setYahoo("");

			newUser.setUserDetail(ud);
			ud.setUserInfo(newUser);

			try {
				newUser = this.getUserService().saveUserInfo(newUser);
				
				String showName = objUser.getString("nickname");
				String re = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";
				Pattern pattern = Pattern.compile(re);
				Matcher matcher = pattern.matcher(showName);
				Matcher matcher2 = pattern.matcher(objUser.getString("userName"));
				if (matcher.matches()&&showName.length()>7) {
					showName = showName.substring(0, 3) + "****" + showName.substring(7);
				}else if(matcher2.matches()&&(showName==null||showName.equals("null")||showName.length()==0)&&objUser.getString("userName").length()>7){
					showName = objUser.getString("userName").substring(0, 3) + "****" + objUser.getString("userName").substring(7);
				}
				
				this.getSysStatService().saveAllUserNum(this.getUserService().getAllUserNum(), showName);
				if (this.getSysConfig().isCheckRegUserEmail()) {
					String subject = this.getText("reg.validate.email.title", new String[] { this.getSysConfig()
							.getForumName() });
					Map<String, String> root = new HashMap<String, String>();
					root.put("website", this.getSysConfig().getForumName());
					root.put("forumurl", this.getSysConfig().getForumUrl());
					root.put("userName", objUser.getString("userName"));
					root.put("validateCode", ui.getValidateCode());
					this.getTemplateMail().sendMailFromTemplate(objUser.getString("email"), subject, "regValidate.ftl", root,
							this.getLocale());
				}
			} catch (BbscsException e) {
				this.addActionError(this.getText("error.reg.createrror"));
				return ERROR;
			}
			ui = this.getUserService().findUserInfoByUserNo(userno);
		}
		
		/*//对于如意彩用户如果是第一次登录bbs，要在bbs库里插入此用户信息
		if (this.getSysConfig().isUseSafeLogin()) {
			if (this.getLoginErrorService().isCanNotLogin(ui.getId())) {
				this.addActionError(this.getText("error.login.times"));
				return INPUT;
			}
		}

		if (!Util.hash(pass).equals(ui.getRePasswd())) { // 密码是否错误
			if (this.getSysConfig().isUseSafeLogin()) {
				try {
					this.getLoginErrorService().createLoginError(ui.getId());
				} catch (BbscsException ex1) {
					logger.error(ex1);
				}
			}
			this.addActionError(this.getText("error.login.passwd"));
			return INPUT;
		}

		if (this.getSysConfig().isUseAuthCode()) {//验证码是否错误
			//String cauthCode = this.getUserCookie().getAuthCode();
			String cauthCode = this.getSessionAuthCode();

			if (StringUtils.isBlank(cauthCode) || !cauthCode.equals(this.getAuthCode())) {
				this.addActionError(this.getText("error.login.authcode"));
				return INPUT;
			}
		}*/

		ui.setLastLoginIP(ui.getLoginIP());
		ui.setLastLoginTime(ui.getLoginTime());

		ui.setLoginIP(this.getUserRemoteAddr());
		ui.setLoginTime(new Date());
		ui.setUserLocale(this.getLocale().toString());

		long nowTime = System.currentTimeMillis();
		UserOnline uo = new UserOnline();
		uo.setAtPlace("");
		uo.setBoardID(0);
		uo.setNickName(ui.getNickName().equals("null")?ui.getUserName():ui.getNickName()==null?ui.getUserName():ui.getNickName());
		uo.setOnlineTime(nowTime);
		uo.setUserGroupID(ui.getGroupID());
		uo.setUserID(ui.getId());
		uo.setUserName(ui.getUserName());
		uo.setValidateCode(ui.getId() + "_" + nowTime);
		if (this.getHiddenLogin() == 1 || ui.getHiddenLogin() == 1) { // 用户隐身登录
			uo.setHiddenUser(1);
		}

		try {
			ui = this.getUserService().saveAtLogin(ui); // 用户登录处理
			if("".equals(ui)){
				this.addActionError(this.getText(request.getAttribute("error").toString()));
				return ERROR;
			}
			uo = this.getUserOnlineService().createUserOnline(uo); // 加入在线用户表
			tourl = this.getBasePath() + "main.bbscs";
		} catch (Exception ex) {
			logger.error(ex);
			this.addActionError(this.getText("sorry,there is error!"));
			return ERROR;
		}

		UserSession us = userService.getUserSession(ui);
		us.setValidateCode(uo.getValidateCode());
		// this.getUserSessionCache().add(ui.getUserName(), us);
		this.getSession().put(Constant.USER_SESSION_KEY, us);

		this.getUserCookie().removeAuthCode();
		this.getUserCookie().addCookies(ui);
		// this.getUserCookie().addValidateCode(uo.getValidateCode());
		if (this.getCookieTime() != -1) {
			this.getUserCookie().addC("U", ui.getUserName(), this.getCookieTime());
			this.getUserCookie().addDES("P", Util.hash(ui.getPasswd()), this.getCookieTime());
		}
		
		//当有参数为 reqUrl时  登录后将跳转到 reqUrl
		String referer = null;
		try{
		referer = request.getParameter("reqUrl");
		String serverName = request.getServerName();
		if(referer!=null && referer.indexOf(serverName) > -1){
			response.sendRedirect(referer);
			return null ;
		}
		}catch (Exception e) {
			logger.info("response.sendRedirect:" + referer);
		}
		return SUCCESS;
	}
	
	
	@SuppressWarnings("unchecked")
	public String cookieLogin() {
		UserInfo ui = this.getUserService().findUserInfoByUserName(this.getUserCookie().getUserName());
		if (ui == null) {
			this.addActionError(this.getText("error.user.notexist"));
			return INPUT;
		}

		/*if (!this.getUserCookie().getPasswd().equals(ui.getRePasswd())) { // 密码错误
			if (this.getSysConfig().isUseSafeLogin()) {
				try {
					this.getLoginErrorService().createLoginError(ui.getId());
				} catch (BbscsException ex1) {
					logger.error(ex1);
				}
			}
			this.addActionError(this.getText("error.login.passwd"));
			return INPUT;
		}*/

		ui.setLastLoginIP(ui.getLoginIP());
		ui.setLastLoginTime(ui.getLoginTime());

		ui.setLoginIP(this.getUserRemoteAddr());
		ui.setLoginTime(new Date());
		ui.setUserLocale(this.getLocale().toString());

		long nowTime = System.currentTimeMillis();
		UserOnline uo = new UserOnline();
		uo.setAtPlace("");
		uo.setBoardID(0);
		uo.setNickName(ui.getNickName().equals("null")?ui.getUserName():ui.getNickName()==null?ui.getUserName():ui.getNickName());
		uo.setOnlineTime(nowTime);
		uo.setUserGroupID(ui.getGroupID());
		uo.setUserID(ui.getId());
		uo.setUserName(ui.getUserName());
		uo.setValidateCode(ui.getId() + "_" + nowTime);
		if (this.getHiddenLogin() == 1 || ui.getHiddenLogin() == 1) { // 用户隐身登录
			uo.setHiddenUser(1);
		}

		try {
			ui = this.getUserService().saveAtLogin(ui); // 用户登录处理
			if(!"success".equals(ui)){
				
				this.addActionError(this.getText(request.getAttribute("error").toString()));
				return INPUT;
				
			}
			uo = this.getUserOnlineService().createUserOnline(uo); // 加入在线用户表
		} catch (BbscsException ex) {
			logger.error(ex);
			return INPUT;
		}

		UserSession us = userService.getUserSession(ui);
		us.setValidateCode(uo.getValidateCode());
		this.getSession().put(Constant.USER_SESSION_KEY, us);

		this.getUserCookie().removeAuthCode();
		this.getUserCookie().addCookies(ui);

		if (Constant.USE_URL_REWRITE) {
			tourl = this.getBasePath() + "main.html";
		} else {
			tourl = this.getBasePath() + BBSCSUtil.getActionMappingURLWithoutPrefix("main");
		}

		return SUCCESS;
	}

	public LoginErrorService getLoginErrorService() {
		return loginErrorService;
	}

	public void setLoginErrorService(LoginErrorService loginErrorService) {
		this.loginErrorService = loginErrorService;
	}

	public UserOnlineService getUserOnlineService() {
		return userOnlineService;
	}

	public void setUserOnlineService(UserOnlineService userOnlineService) {
		this.userOnlineService = userOnlineService;
	}

	public boolean isUseAuthCode() {
		return useAuthCode;
	}

	public void setUseAuthCode(boolean useAuthCode) {
		this.useAuthCode = useAuthCode;
	}

	public int getCookieTime() {
		return cookieTime;
	}

	public void setCookieTime(int cookieTime) {
		this.cookieTime = cookieTime;
	}

	/*
	 * public Cache getUserSessionCache() { return userSessionCache; }
	 *
	 * public void setUserSessionCache(Cache userSessionCache) {
	 * this.userSessionCache = userSessionCache; }
	 */

	private String getSessionAuthCode() {
        return (String)this.getSession().get("authCode");
    }

}
