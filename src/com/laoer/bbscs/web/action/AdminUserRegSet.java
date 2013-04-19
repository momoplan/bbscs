package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ui.RadioInt;

public class AdminUserRegSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AdminUserRegSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 8069894184264886564L;

	public AdminUserRegSet() {
		this.setRadioYesNoListValues();
	}

	private SysConfig sysConfig;

	private String canNotRegUserName;

	private int checkRegUser;

	private int checkRegUserEmail;

	private int openUserReg;

	private int useRegAuthCode;

	public String getCanNotRegUserName() {
		return canNotRegUserName;
	}

	public void setCanNotRegUserName(String canNotRegUserName) {
		this.canNotRegUserName = canNotRegUserName;
	}

	public int getCheckRegUser() {
		return checkRegUser;
	}

	public void setCheckRegUser(int checkRegUser) {
		this.checkRegUser = checkRegUser;
	}

	public int getCheckRegUserEmail() {
		return checkRegUserEmail;
	}

	public void setCheckRegUserEmail(int checkRegUserEmail) {
		this.checkRegUserEmail = checkRegUserEmail;
	}

	public int getOpenUserReg() {
		return openUserReg;
	}

	public void setOpenUserReg(int openUserReg) {
		this.openUserReg = openUserReg;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	List<RadioInt> radioYesNoList = new ArrayList<RadioInt>();

	private void setRadioYesNoListValues() {
		radioYesNoList.add(new RadioInt(0, this.getText("bbscs.no")));
		radioYesNoList.add(new RadioInt(1, this.getText("bbscs.yes")));
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
		this.setCanNotRegUserName(this.getSysConfig().getCanNotRegUserName());
		this.setCheckRegUser(this.getSysConfig().getCheckRegUser());
		this.setCheckRegUserEmail(this.getSysConfig().getCheckRegUserEmail());
		this.setOpenUserReg(this.getSysConfig().getOpenUserReg());
		this.setUseRegAuthCode(this.getSysConfig().getUseRegAuthCode());
		return INPUT;
	}

	public String save() {
		this.getSysConfig().setCanNotRegUserName(this.getCanNotRegUserName());
		this.getSysConfig().setCheckRegUser(this.getCheckRegUser());
		this.getSysConfig().setCheckRegUserEmail(this.getCheckRegUserEmail());
		this.getSysConfig().setOpenUserReg(this.getOpenUserReg());
		this.getSysConfig().setUseRegAuthCode(this.getUseRegAuthCode());
		try {
			this.getSysConfig().saveConfigs();
			this.addActionMessage(this.getText("bbscs.dataupdate.succeed"));
		} catch (SysConfigException e) {
			logger.error("save()", e); //$NON-NLS-1$
			this.addActionError(this.getText("error.dataupdate.failed"));
		}
		return INPUT;
	}

	public int getUseRegAuthCode() {
		return useRegAuthCode;
	}

	public void setUseRegAuthCode(int useRegAuthCode) {
		this.useRegAuthCode = useRegAuthCode;
	}

}
