package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ui.RadioInt;

public class AdminForumViewSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AdminForumViewSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 7545396011899969337L;

	public AdminForumViewSet() {
		this.setRadioYesNoListValues();
	}

	private SysConfig sysConfig;

	private int forumHotRes;

	private int forumHotViews;

	private int forumPrePage;

	private int maxMultiPage;

	private int postViewLength;

	private int useLinkToPages;

	public int getForumHotRes() {
		return forumHotRes;
	}

	public void setForumHotRes(int forumHotRes) {
		this.forumHotRes = forumHotRes;
	}

	public int getForumHotViews() {
		return forumHotViews;
	}

	public void setForumHotViews(int forumHotViews) {
		this.forumHotViews = forumHotViews;
	}

	public int getForumPrePage() {
		return forumPrePage;
	}

	public void setForumPrePage(int forumPrePage) {
		this.forumPrePage = forumPrePage;
	}

	public int getMaxMultiPage() {
		return maxMultiPage;
	}

	public void setMaxMultiPage(int maxMultiPage) {
		this.maxMultiPage = maxMultiPage;
	}

	public int getPostViewLength() {
		return postViewLength;
	}

	public void setPostViewLength(int postViewLength) {
		this.postViewLength = postViewLength;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public int getUseLinkToPages() {
		return useLinkToPages;
	}

	public void setUseLinkToPages(int useLinkToPages) {
		this.useLinkToPages = useLinkToPages;
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
		this.setForumHotRes(this.getSysConfig().getForumHotRes());
		this.setForumHotViews(this.getSysConfig().getForumHotViews());
		this.setForumPrePage(this.getSysConfig().getForumPrePage());
		this.setMaxMultiPage(this.getSysConfig().getMaxMultiPage());
		this.setPostViewLength(this.getSysConfig().getPostViewLength());
		this.setUseLinkToPages(this.getSysConfig().getUseLinkToPages());
		return INPUT;
	}

	public String save() {
		this.getSysConfig().setForumHotRes(this.getForumHotRes());
		this.getSysConfig().setForumHotViews(this.getForumHotViews());
		this.getSysConfig().setForumPrePage(this.getForumPrePage());
		this.getSysConfig().setMaxMultiPage(this.getMaxMultiPage());
		this.getSysConfig().setPostViewLength(this.getPostViewLength());
		this.getSysConfig().setUseLinkToPages(this.getUseLinkToPages());
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
