package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ui.RadioInt;

public class AdminPostViewSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AdminPostViewSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 3816561682391540762L;

	public AdminPostViewSet() {
		this.setRadioYesNoListValues();
	}

	private SysConfig sysConfig;

	private String postDefFaceImg;

	private int postPerPage;

	private int postShowUserImg;

	private int postVoteRec;

	private String userPerPage;

	public String getPostDefFaceImg() {
		return postDefFaceImg;
	}

	public void setPostDefFaceImg(String postDefFaceImg) {
		this.postDefFaceImg = postDefFaceImg;
	}

	public int getPostPerPage() {
		return postPerPage;
	}

	public void setPostPerPage(int postPerPage) {
		this.postPerPage = postPerPage;
	}

	public int getPostShowUserImg() {
		return postShowUserImg;
	}

	public void setPostShowUserImg(int postShowUserImg) {
		this.postShowUserImg = postShowUserImg;
	}

	public int getPostVoteRec() {
		return postVoteRec;
	}

	public void setPostVoteRec(int postVoteRec) {
		this.postVoteRec = postVoteRec;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public String getUserPerPage() {
		return userPerPage;
	}

	public void setUserPerPage(String userPerPage) {
		this.userPerPage = userPerPage;
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
		this.setPostDefFaceImg(this.getSysConfig().getPostDefFaceImg());
		this.setPostPerPage(this.getSysConfig().getPostPerPage());
		this.setPostShowUserImg(this.getSysConfig().getPostShowUserImg());
		this.setPostVoteRec(this.getSysConfig().getPostVoteRec());
		this.setUserPerPage(this.getSysConfig().getUserPerPage());
		return INPUT;
	}

	public String save() {
		this.getSysConfig().setPostDefFaceImg(this.getPostDefFaceImg());
		this.getSysConfig().setPostPerPage(this.getPostPerPage());
		this.getSysConfig().setPostShowUserImg(this.getPostShowUserImg());
		this.getSysConfig().setPostVoteRec(this.getPostVoteRec());
		this.getSysConfig().setUserPerPage(this.getUserPerPage());
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
