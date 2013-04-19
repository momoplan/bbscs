package com.laoer.bbscs.web.action;

import org.apache.log4j.Logger;

import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;

public class AdminUOTimeSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AdminUOTimeSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 2455503356256490437L;

	private SysConfig sysConfig;

	private int userOnlineTime;

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public int getUserOnlineTime() {
		return userOnlineTime;
	}

	public void setUserOnlineTime(int userOnlineTime) {
		this.userOnlineTime = userOnlineTime;
	}

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			logger.error(e);
			return INPUT;
		}
	}

	public String index() {
		this.setAction("save");
		this.setUserOnlineTime(this.getSysConfig().getUserOnlineTime());
		return INPUT;
	}

	public String save() {
		this.getSysConfig().setUserOnlineTime(this.getUserOnlineTime());
		try {
			this.getSysConfig().saveConfigs();
			this.addActionMessage(this.getText("bbscs.dataupdate.succeed"));
		} catch (SysConfigException e) {
			logger.error("save()", e);
			this.addActionError(this.getText("error.dataupdate.failed"));
		}
		return INPUT;
	}

}
