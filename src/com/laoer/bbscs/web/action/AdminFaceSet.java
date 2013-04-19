package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ui.RadioInt;

public class AdminFaceSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminFaceSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 2760798663880180516L;

	public AdminFaceSet() {
		this.setRadioYesNoListValues();
	}

	private SysConfig sysConfig;

	private int faceHigh;

	private int faceSize;

	private int faceWidth;

	private int useFace;

	public int getFaceHigh() {
		return faceHigh;
	}

	public void setFaceHigh(int faceHigh) {
		this.faceHigh = faceHigh;
	}

	public int getFaceSize() {
		return faceSize;
	}

	public void setFaceSize(int faceSize) {
		this.faceSize = faceSize;
	}

	public int getFaceWidth() {
		return faceWidth;
	}

	public void setFaceWidth(int faceWidth) {
		this.faceWidth = faceWidth;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public int getUseFace() {
		return useFace;
	}

	public void setUseFace(int useFace) {
		this.useFace = useFace;
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
		this.setFaceHigh(this.getSysConfig().getFaceHigh());
		this.setFaceSize(this.getSysConfig().getFaceSize());
		this.setFaceWidth(this.getSysConfig().getFaceWidth());
		this.setUseFace(this.getSysConfig().getUseFace());
		return INPUT;
	}

	public String save() {
		this.getSysConfig().setFaceHigh(this.getFaceHigh());
		this.getSysConfig().setFaceSize(this.getFaceSize());
		this.getSysConfig().setFaceWidth(this.getFaceWidth());
		this.getSysConfig().setUseFace(this.getUseFace());
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
