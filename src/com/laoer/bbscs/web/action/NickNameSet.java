package com.laoer.bbscs.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.commons.lang.StringUtils;

import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.exception.BbscsException;
//import com.laoer.bbscs.service.Cache;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;

public class NickNameSet extends BaseMainAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(NickNameSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 6377849115824005548L;

	private SysConfig sysConfig;

	private UserService userService;

	//private Cache userSessionCache;

	private AjaxMessagesJson ajaxMessagesJson;

	private String nickName;

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	/*
	public Cache getUserSessionCache() {
		return userSessionCache;
	}

	public void setUserSessionCache(Cache userSessionCache) {
		this.userSessionCache = userSessionCache;
	}*/

	public String index() {
		this.setAction("edit");
		this.setNickName(this.getUserSession().getNickName());
		return INPUT;
	}

	public String edit() {
		String nickName = StringUtils.trimToEmpty(this.getNickName());
		if (StringUtils.isBlank(nickName)) {
			this.getAjaxMessagesJson().setMessage("E_USER_NICKNAME_NULL", this.getText("error.nickname.null"));
			return RESULT_AJAXJSON;
		}

		UserInfo ui = this.getUserService().findUserInfoById(this.getUserSession().getId());
		if (ui != null) {
			ui.setNickName(nickName);
			try {
				this.getUserService().saveUserInfo(ui);
				this.getUserSession().setNickName(nickName);
				//this.getUserSessionCache().remove(this.getUserCookie().getUserName());
				this.getAjaxMessagesJson().setMessage("0", this.getText("nickname.set.ok"), nickName);
			} catch (BbscsException ex) {
				logger.error(ex);
				this.getAjaxMessagesJson().setMessage("E_USER_NICKNAME_SETFAILED",
						this.getText("error.nickname.seterror"));
			}
		}
		return RESULT_AJAXJSON;
	}

}
