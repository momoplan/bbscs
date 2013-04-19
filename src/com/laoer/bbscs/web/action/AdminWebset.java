package com.laoer.bbscs.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;

public class AdminWebset extends BaseAction {

	private static final Log logger = LogFactory.getLog(AdminWebset.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 199327690843139411L;

	private SysConfig sysConfig;

	private String copyRightMsg;

	private String forumName;

	private String forumUrl;

	private String privacyUrl;

	private String webName;

	private String webUrl;

	private String webmasterEmail;

	public String getCopyRightMsg() {
		return copyRightMsg;
	}

	public void setCopyRightMsg(String copyRightMsg) {
		this.copyRightMsg = copyRightMsg;
	}

	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public String getForumUrl() {
		return forumUrl;
	}

	public void setForumUrl(String forumUrl) {
		this.forumUrl = forumUrl;
	}

	public String getPrivacyUrl() {
		return privacyUrl;
	}

	public void setPrivacyUrl(String privacyUrl) {
		this.privacyUrl = privacyUrl;
	}

	public String getWebmasterEmail() {
		return webmasterEmail;
	}

	public void setWebmasterEmail(String webmasterEmail) {
		this.webmasterEmail = webmasterEmail;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public String execute() {
		/*
		if (this.getAction().equalsIgnoreCase("index")) {
			return this.index();
		}
		if (this.getAction().equalsIgnoreCase("save")) {
			return this.save();
		}
		return INPUT;
		*/
		try {
			return this.executeMethod(this.getAction().toLowerCase());
		} catch (Exception e) {
			logger.error(e);
			return INPUT;
		}
	}

	public String index() {
		this.setAction("save");
		this.setCopyRightMsg(this.getSysConfig().getCopyRightMsg());
		this.setForumName(this.getSysConfig().getForumName());
		this.setForumUrl(this.getSysConfig().getForumUrl());
		this.setPrivacyUrl(this.getSysConfig().getPrivacyUrl());
		this.setWebmasterEmail(this.getSysConfig().getWebmasterEmail());
		this.setWebName(this.getSysConfig().getWebName());
		this.setWebUrl(this.getSysConfig().getWebUrl());
		return INPUT;
	}

	public String save() {
		this.setAction("save");
		this.getSysConfig().setCopyRightMsg(this.getCopyRightMsg());
		this.getSysConfig().setForumName(this.getForumName());
		this.getSysConfig().setForumUrl(this.getForumUrl());
		this.getSysConfig().setPrivacyUrl(this.getPrivacyUrl());
		this.getSysConfig().setWebmasterEmail(this.getWebmasterEmail());
		this.getSysConfig().setWebName(this.getWebName());
		this.getSysConfig().setWebUrl(this.getWebUrl());
		try {
			this.getSysConfig().saveConfigs();
			this.addActionMessage(this.getText("bbscs.dataupdate.succeed"));

			return INPUT;
		} catch (SysConfigException e) {
			logger.error("save()", e); //$NON-NLS-1$
			this.addActionError(this.getText("error.dataupdate.failed"));

			return INPUT;
		}
	}

}
