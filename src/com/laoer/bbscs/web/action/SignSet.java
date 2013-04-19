package com.laoer.bbscs.web.action;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.exception.BbscsException;
//import com.laoer.bbscs.service.Cache;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;

public class SignSet extends BaseMainAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(SignSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -1104215147915978844L;

	private SysConfig sysConfig;

	private UserService userService;

	//private Cache userSessionCache;

	private AjaxMessagesJson ajaxMessagesJson;

	private String signDetail;

	private int signID;

	private String userSign0;

	private String userSign1;

	private String userSign2;

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
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

	public String getSignDetail() {
		return signDetail;
	}

	public void setSignDetail(String signDetail) {
		this.signDetail = signDetail;
	}

	public int getSignID() {
		return signID;
	}

	public void setSignID(int signID) {
		this.signID = signID;
	}

	public String getUserSign0() {
		return userSign0;
	}

	public void setUserSign0(String userSign0) {
		this.userSign0 = userSign0;
	}

	public String getUserSign1() {
		return userSign1;
	}

	public void setUserSign1(String userSign1) {
		this.userSign1 = userSign1;
	}

	public String getUserSign2() {
		return userSign2;
	}

	public void setUserSign2(String userSign2) {
		this.userSign2 = userSign2;
	}

	public String index() {
		String[] userSign = new String[3];
		userSign[0] = this.getUserSession().getSignDetail()[0];
		userSign[1] = this.getUserSession().getSignDetail()[1];
		userSign[2] = this.getUserSession().getSignDetail()[2];
		userSign = BBSCSUtil.filterUserSign(userSign, this.getSysConfig().isSignUseHtml(), this.getSysConfig()
				.isSignUseUBB(), this.getSysConfig().isSignUseSmile());
		this.setUserSign0(userSign[0]);
		this.setUserSign1(userSign[1]);
		this.setUserSign2(userSign[2]);
		return SUCCESS;
	}

	public String edit() {
		this.setAction("editdo");
		this.setSignDetail(this.getUserSession().getSignDetail()[this.getSignID()]);
		return INPUT;
	}

	public String editdo() {
		if (BBSCSUtil.getSysCharsetStrLength(this.getSignDetail()) > this.getSysConfig().getSignMaxLen()) { // 签名超过指定长度
			this.getAjaxMessagesJson().setMessage(
					"E_USER_SIGN_TOOLONG",
					this.getText("error.sign.toolong", new String[] { String.valueOf(this.getSysConfig()
							.getSignMaxLen()) }));
			return RESULT_AJAXJSON;
		}
		UserInfo ui = this.getUserService().findUserInfoById(this.getUserSession().getId());
		if (ui != null) {
			String signDetail = "";
			if (StringUtils.isBlank(this.getSignDetail())) { // 签名为空，设为默认签名
				signDetail = this.getText("bbscs.userdefaultsign");
				switch (this.getSignID()) {
				case 0:
					ui.setSignDetail0(signDetail);
					break;
				case 1:
					ui.setSignDetail1(signDetail);
					break;
				case 2:
					ui.setSignDetail2(signDetail);
					break;
				}
			} else {
				signDetail = this.getSysConfig().bestrowScreen(this.getSignDetail()); // 过滤敏感词

				switch (this.getSignID()) {
				case 0:
					ui.setSignDetail0(signDetail);
					break;
				case 1:
					ui.setSignDetail1(signDetail);
					break;
				case 2:
					ui.setSignDetail2(signDetail);
					break;
				}
			}
			try {
				ui = this.getUserService().saveUserInfo(ui);
				this.getUserSession().getSignDetail()[this.getSignID()] = signDetail;
				//this.getUserSessionCache().remove(this.getUserCookie().getUserName());
				signDetail = BBSCSUtil.filterText(signDetail, this.getSysConfig().isSignUseHtml(), this.getSysConfig()
						.isSignUseUBB(), this.getSysConfig().isSignUseSmile());
				this.getAjaxMessagesJson().setMessage("0", this.getText("sign.edit.ok"), signDetail);
			} catch (BbscsException ex) {
				logger.error(ex);
				this.getAjaxMessagesJson().setMessage("E_USER_SIGN_ERROR", this.getText("error.sign.edit"));
			}
		}
		return RESULT_AJAXJSON;
	}

}
