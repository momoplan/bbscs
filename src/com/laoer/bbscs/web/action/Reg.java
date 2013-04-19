package com.laoer.bbscs.web.action;

import com.laoer.bbscs.service.*;
import com.laoer.bbscs.service.config.*;
import com.laoer.bbscs.comm.*;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.web.interceptor.*;
import com.laoer.bbscs.web.servlet.UserCookie;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.service.mail.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.*;
import org.apache.struts2.interceptor.SessionAware;

public class Reg extends BaseAction implements RemoteAddrAware, UserCookieAware, SessionAware  {

	/**
	 *
	 */
	private static final long serialVersionUID = -8383329678871879794L;

	private Map session;

    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }

	// private String action;

	private String answer;

	private String email;

	private String nickName;

	private String passwd;

	private String question;

	private String rePasswd;

	private String userName;

	private String validateCode;

	private String authCode;

	private boolean useAuthCode = true;

	private UserService userService;

	private SysConfig sysConfig;

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

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public TemplateMail getTemplateMail() {
		return templateMail;
	}

	public void setTemplateMail(TemplateMail templateMail) {
		this.templateMail = templateMail;
	}

	/*
	 * public String getAction() { return action; }
	 *
	 * public void setAction(String action) { this.action = action; }
	 */

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getRePasswd() {
		return rePasswd;
	}

	public void setRePasswd(String rePasswd) {
		this.rePasswd = rePasswd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public boolean isUseAuthCode() {
		return useAuthCode;
	}

	public void setUseAuthCode(boolean useAuthCode) {
		this.useAuthCode = useAuthCode;
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

	public SysStatService getSysStatService() {
		return sysStatService;
	}

	public void setSysStatService(SysStatService sysStatService) {
		this.sysStatService = sysStatService;
	}

	private UserCookie userCookie;

	public UserCookie getUserCookie() {
		return userCookie;
	}

	public void setUserCookie(UserCookie userCookie) {
		this.userCookie = userCookie;
	}

	public String input() throws Exception {
		if (this.getSysConfig().getUsePass() == 1) {
			return "passreg";
		}

		if (this.getSysConfig().getOpenUserReg() == 0) { // 关闭注册
			addActionError(this.getText("error.reg.notallowreg"));
			return ERROR;
		}
		this.setUseAuthCode(this.getSysConfig().isUseRegAuthCode());
		this.setAction("add");
		return INPUT;
	}

	public String add() {
		//是否是管理员身份
		if (this.getSysConfig().getUsePass() == 1) {
			return "passreg";
		}
		this.setUseAuthCode(this.getSysConfig().isUseRegAuthCode());
		/*if (this.getSysConfig().getOpenUserReg() == 0) { // 关闭注册
			addActionError(this.getText("error.reg.notallowreg"));
			return ERROR;
		}
		if (this.getSysConfig().isCanNotRegUserName(this.getUserName())) { // 不能注册的用户名
			addFieldError("userName", this.getText("error.reg.badusername", new String[] { this.getUserName() }));
		}
		if (this.getSysConfig().getUseForbid() == 1) {
			if (this.getSysConfig().isForbidIP(this.getUserRemoteAddr())) {
				this.addFieldError("userName", this.getText("error.reg.ipforbid", new String[] { this
						.getUserRemoteAddr() }));
			}
			if (this.getSysConfig().isForbidEmail(this.getEmail())) {
				this.addFieldError("email", this.getText("error.reg.emailforbid", new String[] { this.getEmail() }));
			}
		}
		if (this.getSysConfig().isUseRegAuthCode()) {
			String cauthCode = this.getSessionAuthCode();
			if (StringUtils.isBlank(cauthCode) || !cauthCode.equals(this.getAuthCode())) {
				this.addFieldError("authCode", this.getText("error.reg.authcode.same"));
			}
		}*/

		if (this.hasFieldErrors()) {
			return INPUT;
		}

		UserInfo ui = this.getUserService().findUserInfoByUserName(this.getUserName());

		if (ui != null) {
			this.addFieldError("userName", this.getText("error.reg.name1"));
			return INPUT;
		}

		/*ui = this.getUserService().findUserInfoByEmail(this.getEmail());
		if (ui != null) {
			this.addFieldError("email", this.getText("error.reg.emailerror"));
			return INPUT;
		}*/

		ui = new UserInfo();

		ui.setAcceptFriend(1);
		ui.setAnswer(this.getAnswer());
		ui.setArticleEliteNum(0);
		ui.setArticleNum(0);
		ui.setBirthDay(1);
		ui.setBirthMonth(1);
		ui.setBirthYear(1980);
		ui.setEmail(this.getEmail());
		ui.setExperience(0);
		ui.setForumPerNum(0);
		ui.setForumViewMode(0);
		ui.setHavePic(0);
		ui.setLastLoginIP("0.0.0.0");
		ui.setLastLoginTime(new Date());
		ui.setLifeForce(0);
		ui.setLiterary(0);
		ui.setLoginIP("0.0.0.0");
		ui.setLoginTime(new Date());
		ui.setLoginTimes(0);
		ui.setNickName(this.getSysConfig().bestrowScreenNickName(this.getNickName().equals("null")?this.getUserName():this.getNickName()==null?this.getUserName():this.getNickName())); // 屏蔽敏感字
		ui.setPasswd(this.getPasswd());
		ui.setPicFileName("");
		ui.setPostPerNum(0);
		ui.setQuestion(this.getQuestion());
		ui.setReceiveNote(1);
		ui.setRegTime(new Date());
		ui.setRePasswd(Util.hash(this.getPasswd()));
		ui.setSignDetail0(this.getText("bbscs.userdefaultsign"));
		ui.setSignDetail1(this.getText("bbscs.userdefaultsign"));
		ui.setSignDetail2(this.getText("bbscs.userdefaultsign"));
		ui.setSignName0("A");
		ui.setSignName1("B");
		ui.setSignName2("C");
		ui.setStayTime(0);
		ui.setTimeZone("GMT+08:00");
		ui.setUserFrom(this.getIpSeeker().getCountry(this.getUserRemoteAddr()));
		ui.setUserKnow(0);
		ui.setUserName(this.getUserName());
		ui.setUserTitle(0);
		if (this.getSysConfig().isCheckRegUser() || this.getSysConfig().isCheckRegUserEmail()) {
			ui.setValidated(0);
			ui.setGroupID(Constant.USER_GROUP_UNVUSER);
		} else {
			ui.setValidated(1);
			ui.setGroupID(Constant.USER_GROUP_REGUSER);
		}
		ui.setEditType(-1);
		ui.setUserLocale(this.getLocale().toString());
		ui.setValidateCode(RandomStringUtils.randomAlphanumeric(10));
		ui.setCoin(100);

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

		ui.setUserDetail(ud);
		ud.setUserInfo(ui);

		try {
			ui = this.getUserService().saveUserInfo(ui);
			
			String showName = this.getNickName();
			String re = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";
			Pattern pattern = Pattern.compile(re);
			Matcher matcher = pattern.matcher(showName);
			Matcher matcher2 = pattern.matcher(this.getUserName());
			if (matcher.matches()&&showName.length()>7) {
				showName = showName.substring(0, 3) + "****" + showName.substring(7);
			}else if(matcher2.matches()&&(showName==null||showName.equals("null")||showName.length()==0)&&this.getUserName().length()>7){
				showName = this.getUserName().substring(0, 3) + "****" + this.getUserName().substring(7);
			}
			
			this.getSysStatService().saveAllUserNum(this.getUserService().getAllUserNum(), showName);
			if (this.getSysConfig().isCheckRegUserEmail()) {
				String subject = this.getText("reg.validate.email.title", new String[] { this.getSysConfig()
						.getForumName() });
				Map<String, String> root = new HashMap<String, String>();
				root.put("website", this.getSysConfig().getForumName());
				root.put("forumurl", this.getSysConfig().getForumUrl());
				root.put("userName", ui.getUserName());
				root.put("validateCode", ui.getValidateCode());
				this.getTemplateMail().sendMailFromTemplate(ui.getEmail(), subject, "regValidate.ftl", root,
						this.getLocale());
			}
			return SUCCESS;
		} catch (BbscsException e) {
			this.addActionError(this.getText("error.reg.createrror"));
			return ERROR;
		}
	}

	public String validateuser() {
		UserInfo ui = this.getUserService().findUserInfoByUserName(this.getUserName());
		if (ui == null) {
			this.addActionError(this.getText("error.user.notexist"));
			return ERROR;
		}
		if (!ui.getValidateCode().equals(this.getValidateCode())) {
			this.addActionError(this.getText("error.reg.validatecode"));
			return ERROR;
		}
		ui.setGroupID(Constant.USER_GROUP_REGUSER);
		ui.setValidated(1);
		try {
			this.getUserService().saveUserInfo(ui);
		} catch (BbscsException e) {
			this.addActionError(this.getText("error.reg.validateerror"));
			return ERROR;
		}
		return SUCCESS;
	}

	private String getSessionAuthCode() {
        return (String)this.getSession().get("authCode");
    }
}
