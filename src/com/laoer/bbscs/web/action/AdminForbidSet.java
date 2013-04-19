package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ui.RadioInt;

public class AdminForbidSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminForbidSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 1908512888047169814L;

	public AdminForbidSet() {
		this.setRadioYesNoListValues();
	}

	private SysConfig sysConfig;

	private String forbidEmail;

	private String forbidIP;

	private int useForbid;

	public String getForbidEmail() {
		return forbidEmail;
	}

	public void setForbidEmail(String forbidEmail) {
		this.forbidEmail = forbidEmail;
	}

	public String getForbidIP() {
		return forbidIP;
	}

	public void setForbidIP(String forbidIP) {
		this.forbidIP = forbidIP;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public int getUseForbid() {
		return useForbid;
	}

	public void setUseForbid(int useForbid) {
		this.useForbid = useForbid;
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
		this.setForbidEmail(this.getSysConfig().getForbidEmail());
		this.setForbidIP(this.getSysConfig().getForbidIP());
		this.setUseForbid(this.getSysConfig().getUseForbid());
		return INPUT;
	}

	public String save() {
		this.getSysConfig().setForbidEmail(this.getForbidEmail());
		this.getSysConfig().setForbidIP(this.getForbidIP());
		this.getSysConfig().setUseForbid(this.getUseForbid());
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
