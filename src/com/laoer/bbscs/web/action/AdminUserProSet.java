package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ui.RadioInt;

public class AdminUserProSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AdminUserProSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -2267428982022441728L;

	public AdminUserProSet() {
		this.setRadioYesNoListValues();
	}

	private SysConfig sysConfig;

	private String canNotUseNickName;

	private int signMaxLen;

	private int signUseHtml;

	private int signUseSmile;

	private int signUseUBB;

	private int useSign;

	public String getCanNotUseNickName() {
		return canNotUseNickName;
	}

	public void setCanNotUseNickName(String canNotUseNickName) {
		this.canNotUseNickName = canNotUseNickName;
	}

	public int getSignMaxLen() {
		return signMaxLen;
	}

	public void setSignMaxLen(int signMaxLen) {
		this.signMaxLen = signMaxLen;
	}

	public int getSignUseHtml() {
		return signUseHtml;
	}

	public void setSignUseHtml(int signUseHtml) {
		this.signUseHtml = signUseHtml;
	}

	public int getSignUseSmile() {
		return signUseSmile;
	}

	public void setSignUseSmile(int signUseSmile) {
		this.signUseSmile = signUseSmile;
	}

	public int getSignUseUBB() {
		return signUseUBB;
	}

	public void setSignUseUBB(int signUseUBB) {
		this.signUseUBB = signUseUBB;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public int getUseSign() {
		return useSign;
	}

	public void setUseSign(int useSign) {
		this.useSign = useSign;
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
		this.setCanNotUseNickName(this.getSysConfig().getCanNotUseNickName());
		this.setSignMaxLen(this.getSysConfig().getSignMaxLen());
		this.setSignUseHtml(this.getSysConfig().getSignUseHtml());
		this.setSignUseSmile(this.getSysConfig().getSignUseSmile());
		this.setSignUseUBB(this.getSysConfig().getSignUseUBB());
		this.setUseSign(this.getSysConfig().getUseSign());
		return INPUT;
	}

	public String save() {
		this.getSysConfig().setCanNotUseNickName(this.getCanNotUseNickName());
		this.getSysConfig().setSignMaxLen(this.getSignMaxLen());
		this.getSysConfig().setSignUseHtml(this.getSignUseHtml());
		this.getSysConfig().setSignUseSmile(this.getSignUseSmile());
		this.getSysConfig().setSignUseUBB(this.getSignUseUBB());
		this.getSysConfig().setUseSign(this.getUseSign());
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
