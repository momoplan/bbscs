/**
 *
 */
package com.laoer.bbscs.web.action;

import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.UserService;

/**
 * @author irtgong
 *
 */
public class RegValidate extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 7878136303906214290L;

	private String userName;

	private String validateCode;

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
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

}
