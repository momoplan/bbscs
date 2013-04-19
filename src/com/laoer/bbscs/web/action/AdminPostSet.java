package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ui.RadioInt;
import com.laoer.bbscs.comm.*;
import com.laoer.bbscs.exception.SysConfigException;

public class AdminPostSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AdminPostSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 5073877925682818354L;

	public AdminPostSet() {
		this.setRadioEditInterfaceValues();
		this.setRadioLogIPValues();
		this.setRadioYesNoListValues();
	}

	private SysConfig sysConfig;

	private int editPostLimit;

	private int editPostTitleLimit;

	private int logIP;

	private int postCheckTime;

	private int postMaxSize;

	private int postMinSize;

	private int quoteMaxSize;

	private int editInterface;

	private int usePostPeriodOfTime;

	private int postPeriodOfTimeDay;

	private int postPeriodOfTimeStart;

	private int postPeriodOfTimeEnd;

	private int postToHistoryDay;

	public int getEditInterface() {
		return editInterface;
	}

	public void setEditInterface(int editInterface) {
		this.editInterface = editInterface;
	}

	public int getEditPostLimit() {
		return editPostLimit;
	}

	public void setEditPostLimit(int editPostLimit) {
		this.editPostLimit = editPostLimit;
	}

	public int getEditPostTitleLimit() {
		return editPostTitleLimit;
	}

	public void setEditPostTitleLimit(int editPostTitleLimit) {
		this.editPostTitleLimit = editPostTitleLimit;
	}

	public int getLogIP() {
		return logIP;
	}

	public void setLogIP(int logIP) {
		this.logIP = logIP;
	}

	public int getPostCheckTime() {
		return postCheckTime;
	}

	public void setPostCheckTime(int postCheckTime) {
		this.postCheckTime = postCheckTime;
	}

	public int getPostMaxSize() {
		return postMaxSize;
	}

	public void setPostMaxSize(int postMaxSize) {
		this.postMaxSize = postMaxSize;
	}

	public int getPostMinSize() {
		return postMinSize;
	}

	public void setPostMinSize(int postMinSize) {
		this.postMinSize = postMinSize;
	}

	public int getPostPeriodOfTimeDay() {
		return postPeriodOfTimeDay;
	}

	public void setPostPeriodOfTimeDay(int postPeriodOfTimeDay) {
		this.postPeriodOfTimeDay = postPeriodOfTimeDay;
	}

	public int getPostPeriodOfTimeEnd() {
		return postPeriodOfTimeEnd;
	}

	public void setPostPeriodOfTimeEnd(int postPeriodOfTimeEnd) {
		this.postPeriodOfTimeEnd = postPeriodOfTimeEnd;
	}

	public int getPostPeriodOfTimeStart() {
		return postPeriodOfTimeStart;
	}

	public void setPostPeriodOfTimeStart(int postPeriodOfTimeStart) {
		this.postPeriodOfTimeStart = postPeriodOfTimeStart;
	}

	public int getQuoteMaxSize() {
		return quoteMaxSize;
	}

	public void setQuoteMaxSize(int quoteMaxSize) {
		this.quoteMaxSize = quoteMaxSize;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public int getUsePostPeriodOfTime() {
		return usePostPeriodOfTime;
	}

	public void setUsePostPeriodOfTime(int usePostPeriodOfTime) {
		this.usePostPeriodOfTime = usePostPeriodOfTime;
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

	List<RadioInt> radioEditInterfaceList = new ArrayList<RadioInt>();

	private void setRadioEditInterfaceValues() {
		radioEditInterfaceList.add(new RadioInt(0, this.getText("bbscs.editInterface0")));
		radioEditInterfaceList.add(new RadioInt(1, this.getText("bbscs.editInterface1")));
		radioEditInterfaceList.add(new RadioInt(2, this.getText("bbscs.editInterface2")));
	}

	public List<RadioInt> getRadioEditInterfaceList() {
		return radioEditInterfaceList;
	}

	public void setRadioEditInterfaceList(List<RadioInt> radioEditInterfaceList) {
		this.radioEditInterfaceList = radioEditInterfaceList;
	}

	List<RadioInt> radioLogIPList = new ArrayList<RadioInt>();

	private void setRadioLogIPValues() {
		radioLogIPList.add(new RadioInt(0, this.getText("bbscs.logIP0")));
		radioLogIPList.add(new RadioInt(1, this.getText("bbscs.logIP1")));
	}

	public List<RadioInt> getRadioLogIPList() {
		return radioLogIPList;
	}

	public void setRadioLogIPList(List<RadioInt> radioLogIPList) {
		this.radioLogIPList = radioLogIPList;
	}

	private List hourValues = Constant.HOURS;

	public List getHourValues() {
		return hourValues;
	}

	public void setHourValues(List hourValues) {
		this.hourValues = hourValues;
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
		this.setEditInterface(this.getSysConfig().getEditInterface());
		this.setEditPostLimit(this.getSysConfig().getEditPostLimit());
		this.setEditPostTitleLimit(this.getSysConfig().getEditPostTitleLimit());
		this.setLogIP(this.getSysConfig().getLogIP());
		this.setPostCheckTime(this.getSysConfig().getPostCheckTime());
		this.setPostMaxSize(this.getSysConfig().getPostMaxSize());
		this.setPostMinSize(this.getSysConfig().getPostMinSize());
		this.setQuoteMaxSize(this.getSysConfig().getQuoteMaxSize());
		this.setUsePostPeriodOfTime(this.getSysConfig().getUsePostPeriodOfTime());
		this.setPostPeriodOfTimeDay(this.getSysConfig().getPostPeriodOfTimeDay());
		this.setPostPeriodOfTimeStart(this.getSysConfig().getPostPeriodOfTimeStart());
		this.setPostPeriodOfTimeEnd(this.getSysConfig().getPostPeriodOfTimeEnd());
		this.setPostToHistoryDay(this.getSysConfig().getPostToHistoryDay());
		return INPUT;
	}

	public String save() {
		this.getSysConfig().setEditInterface(this.getEditInterface());
		this.getSysConfig().setEditPostLimit(this.getEditPostLimit());
		this.getSysConfig().setEditPostTitleLimit(this.getEditPostTitleLimit());
		this.getSysConfig().setLogIP(this.getLogIP());
		this.getSysConfig().setPostCheckTime(this.getPostCheckTime());
		this.getSysConfig().setPostMaxSize(this.getPostMaxSize());
		this.getSysConfig().setPostMinSize(this.getPostMinSize());
		this.getSysConfig().setQuoteMaxSize(this.getQuoteMaxSize());
		this.getSysConfig().setUsePostPeriodOfTime(this.getUsePostPeriodOfTime());
		this.getSysConfig().setPostPeriodOfTimeDay(this.getPostPeriodOfTimeDay());
		this.getSysConfig().setPostPeriodOfTimeStart(this.getPostPeriodOfTimeStart());
		this.getSysConfig().setPostPeriodOfTimeEnd(this.getPostPeriodOfTimeEnd());
		this.getSysConfig().setPostToHistoryDay(this.getPostToHistoryDay());
		try {
			this.getSysConfig().saveConfigs();
			this.addActionMessage(this.getText("bbscs.dataupdate.succeed"));
		} catch (SysConfigException e) {
			logger.error("save()", e);
			this.addActionError(this.getText("error.dataupdate.failed"));
		}
		return INPUT;
	}

	public int getPostToHistoryDay() {
		return postToHistoryDay;
	}

	public void setPostToHistoryDay(int postToHistoryDay) {
		this.postToHistoryDay = postToHistoryDay;
	}

}
