package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ui.RadioInt;

public class AdminAttachSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminAttachSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 4305885052122888497L;

	public AdminAttachSet() {
		this.setRadioYesNoListValues();
	}

	private SysConfig sysConfig;

	private String attachFileType;

	private int attachImgRow;

	private String attachImgType;

	private int attachmentNum;

	private int canDelAttachmentClosedPost;

	private int canDelAttachmentOverTime;

	private int reduceAttachImg;

	private int reduceAttachImgSize;

	private int viewAttachImg;

	private int attachFileSize;

	public int getAttachFileSize() {
		return attachFileSize;
	}

	public void setAttachFileSize(int attachFileSize) {
		this.attachFileSize = attachFileSize;
	}

	public String getAttachFileType() {
		return attachFileType;
	}

	public void setAttachFileType(String attachFileType) {
		this.attachFileType = attachFileType;
	}

	public int getAttachImgRow() {
		return attachImgRow;
	}

	public void setAttachImgRow(int attachImgRow) {
		this.attachImgRow = attachImgRow;
	}

	public String getAttachImgType() {
		return attachImgType;
	}

	public void setAttachImgType(String attachImgType) {
		this.attachImgType = attachImgType;
	}

	public int getAttachmentNum() {
		return attachmentNum;
	}

	public void setAttachmentNum(int attachmentNum) {
		this.attachmentNum = attachmentNum;
	}

	public int getCanDelAttachmentClosedPost() {
		return canDelAttachmentClosedPost;
	}

	public void setCanDelAttachmentClosedPost(int canDelAttachmentClosedPost) {
		this.canDelAttachmentClosedPost = canDelAttachmentClosedPost;
	}

	public int getCanDelAttachmentOverTime() {
		return canDelAttachmentOverTime;
	}

	public void setCanDelAttachmentOverTime(int canDelAttachmentOverTime) {
		this.canDelAttachmentOverTime = canDelAttachmentOverTime;
	}

	public int getReduceAttachImg() {
		return reduceAttachImg;
	}

	public void setReduceAttachImg(int reduceAttachImg) {
		this.reduceAttachImg = reduceAttachImg;
	}

	public int getReduceAttachImgSize() {
		return reduceAttachImgSize;
	}

	public void setReduceAttachImgSize(int reduceAttachImgSize) {
		this.reduceAttachImgSize = reduceAttachImgSize;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public int getViewAttachImg() {
		return viewAttachImg;
	}

	public void setViewAttachImg(int viewAttachImg) {
		this.viewAttachImg = viewAttachImg;
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
		this.setAttachFileType(this.getSysConfig().getAttachFileType());
		this.setAttachImgRow(this.getSysConfig().getAttachImgRow());
		this.setAttachImgType(this.getSysConfig().getAttachImgType());
		this.setAttachmentNum(this.getSysConfig().getAttachmentNum());
		this.setCanDelAttachmentClosedPost(this.getSysConfig().getCanDelAttachmentClosedPost());
		this.setCanDelAttachmentOverTime(this.getSysConfig().getCanDelAttachmentOverTime());
		this.setReduceAttachImg(this.getSysConfig().getReduceAttachImg());
		this.setReduceAttachImgSize(this.getSysConfig().getReduceAttachImgSize());
		this.setViewAttachImg(this.getSysConfig().getViewAttachImg());
		this.setAttachFileSize(this.getSysConfig().getAttachFileSize());
		return INPUT;
	}

	public String save() {
		this.getSysConfig().setAttachFileType(this.getAttachFileType());
		this.getSysConfig().setAttachImgRow(this.getAttachImgRow());
		this.getSysConfig().setAttachImgType(this.getAttachImgType());
		this.getSysConfig().setAttachmentNum(this.getAttachmentNum());
		this.getSysConfig().setCanDelAttachmentClosedPost(this.getCanDelAttachmentClosedPost());
		this.getSysConfig().setCanDelAttachmentOverTime(this.getCanDelAttachmentOverTime());
		this.getSysConfig().setReduceAttachImg(this.getReduceAttachImg());
		this.getSysConfig().setReduceAttachImgSize(this.getReduceAttachImgSize());
		this.getSysConfig().setViewAttachImg(this.getViewAttachImg());
		this.getSysConfig().setAttachFileSize(this.getAttachFileSize());
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
