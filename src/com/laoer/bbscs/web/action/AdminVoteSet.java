package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ui.RadioInt;

public class AdminVoteSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminVoteSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 2970462177338636329L;

	public AdminVoteSet() {
		this.setRadioYesNoListValues();
	}

	private SysConfig sysConfig;

	private int voteChange;

	private int voteItemLength;

	private int voteItemNum;

	private int voteUpdatePost;

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public int getVoteChange() {
		return voteChange;
	}

	public void setVoteChange(int voteChange) {
		this.voteChange = voteChange;
	}

	public int getVoteItemLength() {
		return voteItemLength;
	}

	public void setVoteItemLength(int voteItemLength) {
		this.voteItemLength = voteItemLength;
	}

	public int getVoteItemNum() {
		return voteItemNum;
	}

	public void setVoteItemNum(int voteItemNum) {
		this.voteItemNum = voteItemNum;
	}

	public int getVoteUpdatePost() {
		return voteUpdatePost;
	}

	public void setVoteUpdatePost(int voteUpdatePost) {
		this.voteUpdatePost = voteUpdatePost;
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
		this.setVoteChange(this.getSysConfig().getVoteChange());
		this.setVoteItemLength(this.getSysConfig().getVoteItemLength());
		this.setVoteItemNum(this.getSysConfig().getVoteItemNum());
		this.setVoteUpdatePost(this.getSysConfig().getVoteUpdatePost());
		return INPUT;
	}

	public String save() {
		this.getSysConfig().setVoteChange(this.getVoteChange());
		this.getSysConfig().setVoteItemLength(this.getVoteItemLength());
		this.getSysConfig().setVoteItemNum(this.getVoteItemNum());
		this.getSysConfig().setVoteUpdatePost(this.getVoteUpdatePost());
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
