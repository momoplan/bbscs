package com.laoer.bbscs.web.action;

import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ajax.*;
import com.laoer.bbscs.comm.*;
import com.laoer.bbscs.bean.*;
import org.apache.commons.lang.*;

public class CheckUserName extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = -6320290736252590313L;

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private AjaxMessagesJson ajaxMessagesJson;

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	private UserService userService;

	private SysConfig sysConfig;

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

	public String execute() {
		if (StringUtils.isBlank(this.getUserName())) {
			this.getAjaxMessagesJson().setMessage("E_USERNAME_001", "请填写用户名！");
		}
		else if (!Util.validateUserName(this.getUserName())) {
			this.getAjaxMessagesJson().setMessage("E_USERNAME_002", "用户名只能由英文、数字和下划线组成！");
		}
		else if (this.getSysConfig().isCanNotRegUserName(this.getUserName())) {
			this.getAjaxMessagesJson().setMessage("E_USERNAME_004", "该用户不能注册！");
		}
		else {
			UserInfo userInfo = this.getUserService().findUserInfoByUserName(this.getUserName());
			if (userInfo != null) {
				this.getAjaxMessagesJson().setMessage("E_USERNAME_003", "用户名已存在，请选择其他用户名！");
			}
			else {
				this.getAjaxMessagesJson().setMessage("0", "该用户名可以注册！");
			}
		}

		// System.out.println(this.getAjaxMessagesJson().getJsonString());
		return RESULT_AJAXJSON;
	}

}
