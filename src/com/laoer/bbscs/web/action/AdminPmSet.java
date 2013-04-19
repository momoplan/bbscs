package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ui.RadioInt;

public class AdminPmSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AdminPmSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 4485709441059485426L;

	public AdminPmSet() {
		this.setRadioYesNoListValues();
	}

	private SysConfig sysConfig;

	private int checkNewPm;

	private int pmAllowFace;

	private int pmAllowHTML;

	private int pmAllowSmilies;

	private int pmAllowUBB;

	private int pmFloodTime;

	private int pmMaxLength;

	private int pmPerPage;

	private int usePm;

	public int getCheckNewPm() {
		return checkNewPm;
	}

	public void setCheckNewPm(int checkNewPm) {
		this.checkNewPm = checkNewPm;
	}

	public int getPmAllowFace() {
		return pmAllowFace;
	}

	public void setPmAllowFace(int pmAllowFace) {
		this.pmAllowFace = pmAllowFace;
	}

	public int getPmAllowHTML() {
		return pmAllowHTML;
	}

	public void setPmAllowHTML(int pmAllowHTML) {
		this.pmAllowHTML = pmAllowHTML;
	}

	public int getPmAllowSmilies() {
		return pmAllowSmilies;
	}

	public void setPmAllowSmilies(int pmAllowSmilies) {
		this.pmAllowSmilies = pmAllowSmilies;
	}

	public int getPmAllowUBB() {
		return pmAllowUBB;
	}

	public void setPmAllowUBB(int pmAllowUBB) {
		this.pmAllowUBB = pmAllowUBB;
	}

	public int getPmFloodTime() {
		return pmFloodTime;
	}

	public void setPmFloodTime(int pmFloodTime) {
		this.pmFloodTime = pmFloodTime;
	}

	public int getPmMaxLength() {
		return pmMaxLength;
	}

	public void setPmMaxLength(int pmMaxLength) {
		this.pmMaxLength = pmMaxLength;
	}

	public int getPmPerPage() {
		return pmPerPage;
	}

	public void setPmPerPage(int pmPerPage) {
		this.pmPerPage = pmPerPage;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public int getUsePm() {
		return usePm;
	}

	public void setUsePm(int usePm) {
		this.usePm = usePm;
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
		this.setCheckNewPm(this.getSysConfig().getCheckNewPm());
		this.setPmAllowFace(this.getSysConfig().getPmAllowFace());
		this.setPmAllowHTML(this.getSysConfig().getPmAllowHTML());
		this.setPmAllowSmilies(this.getSysConfig().getPmAllowSmilies());
		this.setPmAllowUBB(this.getSysConfig().getPmAllowUBB());
		this.setPmFloodTime(this.getSysConfig().getPmFloodTime());
		this.setPmMaxLength(this.getSysConfig().getPmMaxLength());
		this.setPmPerPage(this.getSysConfig().getPmPerPage());
		this.setUsePm(this.getSysConfig().getUsePm());
		return INPUT;
	}

	public String save() {
		this.getSysConfig().setCheckNewPm(this.getCheckNewPm());
		this.getSysConfig().setPmAllowFace(this.getPmAllowFace());
		this.getSysConfig().setPmAllowHTML(this.getPmAllowHTML());
		this.getSysConfig().setPmAllowSmilies(this.getPmAllowSmilies());
		this.getSysConfig().setPmAllowUBB(this.getPmAllowUBB());
		this.getSysConfig().setPmFloodTime(this.getPmFloodTime());
		this.getSysConfig().setPmMaxLength(this.getPmMaxLength());
		this.getSysConfig().setPmPerPage(this.getPmPerPage());
		this.getSysConfig().setUsePm(this.getUsePm());
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
