package com.laoer.bbscs.web.action;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.comm.Util;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.config.SysConfig;

public class Cpasswd extends BaseMainAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(Cpasswd.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -8482940032371202588L;

	private String newpasswd1;

	private String newpasswd2;

	private String oldpasswd;

	private UserService userService;

	private SysConfig sysConfig;

	public String getNewpasswd1() {
		return newpasswd1;
	}

	public void setNewpasswd1(String newpasswd1) {
		this.newpasswd1 = newpasswd1;
	}

	public String getNewpasswd2() {
		return newpasswd2;
	}

	public void setNewpasswd2(String newpasswd2) {
		this.newpasswd2 = newpasswd2;
	}

	public String getOldpasswd() {
		return oldpasswd;
	}

	public void setOldpasswd(String oldpasswd) {
		this.oldpasswd = oldpasswd;
	}

	public String index() {
		if (this.getSysConfig().getUsePass() == 1) {
			return "passcpasswd";
		}

		this.setAction("change");
		return INPUT;
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

	public String change() {
		if (StringUtils.isBlank(this.oldpasswd) || StringUtils.isBlank(this.newpasswd1)
				|| StringUtils.isBlank(this.newpasswd2)) {
			this.addActionError(this.getText("error.nullerror"));
			return INPUT;
		}
		if (this.newpasswd1.length() < 3 || this.newpasswd1.length() > 20) {
			this.addActionError(this.getText("error.reg.passwd.toolong"));
			return INPUT;
		}
		if (!this.newpasswd1.equals(this.newpasswd2)) {
			this.addActionError(this.getText("error.reg.passwd.notsame"));
			return INPUT;
		}
		UserInfo ui = this.getUserService().findUserInfoById(this.getUserSession().getId());
		if (ui != null) {
			String op = Util.hash(this.getOldpasswd());
			if (!op.equals(ui.getRePasswd())) {
				this.addActionError(this.getText("error.changepasswdold"));
				return INPUT;
			}
			ui.setPasswd(this.getNewpasswd1());
			ui.setRePasswd(Util.hash(this.getNewpasswd1()));
			try {
				this.getUserService().saveUserInfo(ui);
				this.addActionMessage(this.getText("succeed.changepasswd"));
			} catch (BbscsException ex) {
				logger.error(ex);
				this.addActionError(this.getText("error.passwdchange"));
			}
		} else {
			this.addActionError(this.getText("error.passwdchange"));
		}
		return INPUT;
	}

}
