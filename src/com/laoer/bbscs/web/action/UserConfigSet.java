package com.laoer.bbscs.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.comm.SysOptionsValues;
import com.laoer.bbscs.exception.BbscsException;
//import com.laoer.bbscs.service.Cache;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;
import com.laoer.bbscs.web.ui.OptionsInt;
import com.laoer.bbscs.web.ui.OptionsString;
import com.laoer.bbscs.web.ui.RadioInt;

public class UserConfigSet extends BaseMainAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(UserConfigSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -1331626124326143455L;

	private SysConfig sysConfig;

	private UserService userService;

	// private Cache userSessionCache;

	private AjaxMessagesJson ajaxMessagesJson;

	private SysOptionsValues sysOptionsValues;

	private boolean acceptFriend;

	private int forumPerNum;

	private int forumViewMode;

	private boolean hiddenLogin;

	private int postPerNum;

	private boolean receiveNote;

	private String timeZone;

	private int editType;

	public boolean getAcceptFriend() {
		return acceptFriend;
	}

	public void setAcceptFriend(boolean acceptFriend) {
		this.acceptFriend = acceptFriend;
	}

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public int getEditType() {
		return editType;
	}

	public void setEditType(int editType) {
		this.editType = editType;
	}

	public int getForumPerNum() {
		return forumPerNum;
	}

	public void setForumPerNum(int forumPerNum) {
		this.forumPerNum = forumPerNum;
	}

	public int getForumViewMode() {
		return forumViewMode;
	}

	public void setForumViewMode(int forumViewMode) {
		this.forumViewMode = forumViewMode;
	}

	public boolean getHiddenLogin() {
		return hiddenLogin;
	}

	public void setHiddenLogin(boolean hiddenLogin) {
		this.hiddenLogin = hiddenLogin;
	}

	public int getPostPerNum() {
		return postPerNum;
	}

	public void setPostPerNum(int postPerNum) {
		this.postPerNum = postPerNum;
	}

	public boolean getReceiveNote() {
		return receiveNote;
	}

	public void setReceiveNote(boolean receiveNote) {
		this.receiveNote = receiveNote;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// public Cache getUserSessionCache() {
	// return userSessionCache;
	// }

	// public void setUserSessionCache(Cache userSessionCache) {
	// this.userSessionCache = userSessionCache;
	// }

	public SysOptionsValues getSysOptionsValues() {
		return sysOptionsValues;
	}

	public void setSysOptionsValues(SysOptionsValues sysOptionsValues) {
		this.sysOptionsValues = sysOptionsValues;
	}

	private List<OptionsInt> userForumNumPerPageValues;

	public List<OptionsInt> getUserForumNumPerPageValues() {
		return userForumNumPerPageValues;
	}

	public void setUserForumNumPerPageValues(List<OptionsInt> userForumNumPerPageValues) {
		this.userForumNumPerPageValues = userForumNumPerPageValues;
	}

	private void setUserForumNumPerPageValuesInit() {
		this.setUserForumNumPerPageValues(this.getSysOptionsValues().getUserForumNumPerPageValues(this.getLocale()));
	}

	private List<OptionsInt> userPostNumPerPageValues;

	public List<OptionsInt> getUserPostNumPerPageValues() {
		return userPostNumPerPageValues;
	}

	public void setUserPostNumPerPageValues(List<OptionsInt> userPostNumPerPageValues) {
		this.userPostNumPerPageValues = userPostNumPerPageValues;
	}

	private void setUserPostNumPerPageValuesInit() {
		this.setUserPostNumPerPageValues(this.getSysOptionsValues().getUserPostNumPerPageValues(this.getLocale(),
				this.getSysConfig().getUserPostPerPageNum()));
	}

	private List<OptionsString> userTimeZoneValues = Constant.USERTIMEZONE;

	public List<OptionsString> getUserTimeZoneValues() {
		return userTimeZoneValues;
	}

	public void setUserTimeZoneValues(List<OptionsString> userTimeZoneValues) {
		this.userTimeZoneValues = userTimeZoneValues;
	}

	private List<OptionsInt> forumViewModeValues;

	public List<OptionsInt> getForumViewModeValues() {
		return forumViewModeValues;
	}

	public void setForumViewModeValues(List<OptionsInt> forumViewModeValues) {
		this.forumViewModeValues = forumViewModeValues;
	}

	private void setForumViewModeValuesInit() {
		this.setForumViewModeValues(this.getSysOptionsValues().getForumViewModeValues(this.getLocale()));
	}

	List<RadioInt> radioEditInterfaceList = new ArrayList<RadioInt>();

	private void setRadioEditInterfaceValues() {
		radioEditInterfaceList.add(new RadioInt(-1, this.getText("bbscs.editInterface")));
		radioEditInterfaceList.add(new RadioInt(0, this.getText("bbscs.editInterface0")));
		radioEditInterfaceList.add(new RadioInt(1, this.getText("bbscs.editInterface1")));
		radioEditInterfaceList.add(new RadioInt(2, this.getText("bbscs.editInterface2")));
	}

	public List<RadioInt> getRadioEditInterfaceList() {
		return radioEditInterfaceList;
	}

	public void setRadioEditInterfaceList(List<RadioInt> radioEditInterfaceList) {
		this.radioEditInterfaceList = radioEditInterfaceList;
	}

	public String index() {
		this.setUserForumNumPerPageValuesInit();
		this.setUserPostNumPerPageValuesInit();
		this.setForumViewModeValuesInit();
		this.setRadioEditInterfaceValues();
		this.setAction("edit");
		UserInfo ui = this.getUserService().findUserInfoById(this.getUserSession().getId());
		if (ui != null) {
			this.setAcceptFriend(this.int2boolean(ui.getAcceptFriend()));
			this.setForumPerNum(ui.getForumPerNum());
			this.setForumViewMode(ui.getForumViewMode());
			this.setHiddenLogin(this.int2boolean(ui.getHiddenLogin()));
			this.setPostPerNum(ui.getPostPerNum());
			this.setReceiveNote(this.int2boolean(ui.getReceiveNote()));
			this.setTimeZone(ui.getTimeZone());
			this.setEditType(ui.getEditType());
		}
		return INPUT;
	}

	public String edit() {
		UserInfo ui = this.getUserService().findUserInfoById(this.getUserSession().getId());
		if (ui != null) {
			ui.setAcceptFriend(this.boolean2int(this.getAcceptFriend()));
			ui.setForumPerNum(this.getForumPerNum());
			ui.setForumViewMode(this.getForumViewMode());
			ui.setHiddenLogin(this.boolean2int(this.getHiddenLogin()));
			ui.setPostPerNum(this.getPostPerNum());
			ui.setReceiveNote(this.boolean2int(this.getReceiveNote()));
			ui.setTimeZone(this.getTimeZone());
			ui.setEditType(this.getEditType());

			try {
				ui = this.getUserService().saveUserInfo(ui);
				this.getUserCookie().addCookies(ui);
				this.getAjaxMessagesJson().setMessage("0", this.getText("userconfig.set.ok"));
			} catch (BbscsException ex) {
				logger.error(ex);
				this.getAjaxMessagesJson().setMessage("E_USERCONFIG_EDITFAILED",
						this.getText("error.userconfig.seterror"));
			}
			return RESULT_AJAXJSON;
		} else {
			this.getAjaxMessagesJson().setMessage("E_USER_NOEXIST", this.getText("error.user.noexist"));
			return RESULT_AJAXJSON;
		}
	}

}
