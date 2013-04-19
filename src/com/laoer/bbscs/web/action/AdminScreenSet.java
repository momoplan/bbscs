package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ui.RadioInt;

public class AdminScreenSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminScreenSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 4227694131692841594L;

	public AdminScreenSet() {
		this.setRadioYesNoListValues();
	}

	private SysConfig sysConfig;

	private String bestrowScreen;

	private String screenWord;

	private int useScreen;

	public String getBestrowScreen() {
		return bestrowScreen;
	}

	public void setBestrowScreen(String bestrowScreen) {
		this.bestrowScreen = bestrowScreen;
	}

	public String getScreenWord() {
		return screenWord;
	}

	public void setScreenWord(String screenWord) {
		this.screenWord = screenWord;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public int getUseScreen() {
		return useScreen;
	}

	public void setUseScreen(int useScreen) {
		this.useScreen = useScreen;
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
		this.setBestrowScreen(this.getSysConfig().getBestrowScreen());
		this.setScreenWord(this.getSysConfig().getScreenWord());
		this.setUseScreen(this.getSysConfig().getUseScreen());
		return INPUT;
	}

	public String save() {
		this.getSysConfig().setBestrowScreen(this.getBestrowScreen());
		this.getSysConfig().setScreenWord(this.getScreenWord());
		this.getSysConfig().setUseScreen(this.getUseScreen());
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
