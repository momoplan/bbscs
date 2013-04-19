package com.laoer.bbscs.web.action;

import org.apache.log4j.Logger;

import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;

public class AdminPostHiddenSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AdminPostHiddenSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 1467613649947392893L;

	private SysConfig sysConfig;

	private boolean postHiddenTypeArtNum;

	private boolean postHiddenTypeMoney;

	private boolean postHiddenTypeRe;

	private String postPriceList;

	private float postPriceTax;

	public boolean getPostHiddenTypeArtNum() {
		return postHiddenTypeArtNum;
	}

	public void setPostHiddenTypeArtNum(boolean postHiddenTypeArtNum) {
		this.postHiddenTypeArtNum = postHiddenTypeArtNum;
	}

	public boolean getPostHiddenTypeMoney() {
		return postHiddenTypeMoney;
	}

	public void setPostHiddenTypeMoney(boolean postHiddenTypeMoney) {
		this.postHiddenTypeMoney = postHiddenTypeMoney;
	}

	public boolean getPostHiddenTypeRe() {
		return postHiddenTypeRe;
	}

	public void setPostHiddenTypeRe(boolean postHiddenTypeRe) {
		this.postHiddenTypeRe = postHiddenTypeRe;
	}

	public String getPostPriceList() {
		return postPriceList;
	}

	public void setPostPriceList(String postPriceList) {
		this.postPriceList = postPriceList;
	}

	public float getPostPriceTax() {
		return postPriceTax;
	}

	public void setPostPriceTax(float postPriceTax) {
		this.postPriceTax = postPriceTax;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
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
		this.setPostHiddenTypeArtNum(int2boolean(this.getSysConfig().getPostHiddenTypeArtNum()));
		this.setPostHiddenTypeMoney(int2boolean(this.getSysConfig().getPostHiddenTypeMoney()));
		this.setPostHiddenTypeRe(int2boolean(this.getSysConfig().getPostHiddenTypeRe()));
		this.setPostPriceList(this.getSysConfig().getPostPriceList());
		this.setPostPriceTax(this.getSysConfig().getPostPriceTax());
		return INPUT;
	}

	public String save() {
		this.getSysConfig().setPostHiddenTypeArtNum(boolean2int(this.getPostHiddenTypeArtNum()));
		this.getSysConfig().setPostHiddenTypeMoney(boolean2int(this.getPostHiddenTypeMoney()));
		this.getSysConfig().setPostHiddenTypeRe(boolean2int(this.getPostHiddenTypeRe()));
		this.getSysConfig().setPostPriceList(this.getPostPriceList());
		this.getSysConfig().setPostPriceTax(this.getPostPriceTax());
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
