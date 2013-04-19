package com.laoer.bbscs.web.action;

import java.util.List;

import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.service.FriendService;
import com.laoer.bbscs.service.UserOnlineService;
import com.laoer.bbscs.service.config.SysConfig;

public class Online extends BaseMainAction {

	/**
	 *
	 */
	private static final long serialVersionUID = -711242309817427417L;

	private UserOnlineService userOnlineService;

	private SysConfig sysConfig;

	private FriendService friendService;

	public FriendService getFriendService() {
		return friendService;
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public UserOnlineService getUserOnlineService() {
		return userOnlineService;
	}

	public void setUserOnlineService(UserOnlineService userOnlineService) {
		this.userOnlineService = userOnlineService;
	}

	private List onlineList;

	public List getOnlineList() {
		return onlineList;
	}

	public void setOnlineList(List onlineList) {
		this.onlineList = onlineList;
	}

	public String user() {
		long atime = this.getTime();
		this.setOnlineList(this.getUserOnlineService().findUserOnlines(atime, 0, 0, Constant.NORMAL_USER_GROUPS));
		return SUCCESS;
	}

	public String friend() {
		long atime = this.getTime();
		this.setOnlineList(this.getUserOnlineService().findUserOnlinesInIds(atime,
				this.getFriendService().fileToFriendIDs(this.getUserSession().getId()), 0, 0,
				Constant.NORMAL_USER_GROUPS));
		return SUCCESS;
	}

	private long getTime() {
		return System.currentTimeMillis() - (this.getSysConfig().getUserOnlineTime() * 1000);
	}

}
