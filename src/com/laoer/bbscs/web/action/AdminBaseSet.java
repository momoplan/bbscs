package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ui.RadioInt;

public class AdminBaseSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminBaseSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -8153415548838977604L;

	public AdminBaseSet() {
		this.setRadioYesNoListValues();
	}

	private int canSeePageNum;

	private String logoutUrl;

	private String metaDescription;

	private String metaKeywords;

	private int useSafeLogin;

	private int useAuthCode;

	private SysConfig sysConfig;

	public int getCanSeePageNum() {
		return canSeePageNum;
	}

	public void setCanSeePageNum(int canSeePageNum) {
		this.canSeePageNum = canSeePageNum;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public int getUseSafeLogin() {
		return useSafeLogin;
	}

	public void setUseSafeLogin(int useSafeLogin) {
		this.useSafeLogin = useSafeLogin;
	}

	List<RadioInt> radioYesNoList = new ArrayList<RadioInt>();

	private void setRadioYesNoListValues() {
		if (logger.isDebugEnabled()) {
			logger.debug("setRadioYesNoListValues() - start"); //$NON-NLS-1$
		}

		radioYesNoList.add(new RadioInt(0, this.getText("bbscs.no")));
		radioYesNoList.add(new RadioInt(1, this.getText("bbscs.yes")));

		if (logger.isDebugEnabled()) {
			logger.debug("setRadioYesNoListValues() - end"); //$NON-NLS-1$
		}
	}

	public List<RadioInt> getRadioYesNoList() {
		return radioYesNoList;
	}

	public void setRadioYesNoList(List<RadioInt> radioYesNoList) {
		this.radioYesNoList = radioYesNoList;
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
		this.setCanSeePageNum(this.getSysConfig().getCanSeePageNum());
		this.setLogoutUrl(this.getSysConfig().getLogoutUrl());
		this.setMetaDescription(this.getSysConfig().getMetaDescription());
		this.setMetaKeywords(this.getSysConfig().getMetaKeywords());
		this.setUseSafeLogin(this.getSysConfig().getUseSafeLogin());
		this.setUseAuthCode(this.getSysConfig().getUseAuthCode());
		return INPUT;
	}

	public String save() {
		this.getSysConfig().setCanSeePageNum(this.getCanSeePageNum());
		this.getSysConfig().setMetaDescription(this.getMetaDescription());
		this.getSysConfig().setMetaKeywords(this.getMetaKeywords());
		this.getSysConfig().setLogoutUrl(this.getLogoutUrl());
		this.getSysConfig().setUseSafeLogin(this.getUseSafeLogin());
		this.getSysConfig().setUseAuthCode(this.getUseAuthCode());
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

	public int getUseAuthCode() {
		return useAuthCode;
	}

	public void setUseAuthCode(int useAuthCode) {
		this.useAuthCode = useAuthCode;
	}

}
