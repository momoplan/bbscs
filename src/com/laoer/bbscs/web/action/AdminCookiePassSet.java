package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.comm.DES;
import com.laoer.bbscs.comm.Util;
import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;
import com.laoer.bbscs.web.ui.RadioInt;

public class AdminCookiePassSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminCookiePassSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 4571922370487031108L;

	public AdminCookiePassSet() {
		this.setRadioYesNoListValues();
	}

	private SysConfig sysConfig;

	private String cookieDomain;

	private String cookieKey;

	private String cookiePath;

	private String passChangeUrl;

	private String passRegUrl;

	private String passUrl;

	private int usePass;

	public String getCookieDomain() {
		return cookieDomain;
	}

	public void setCookieDomain(String cookieDomain) {
		this.cookieDomain = cookieDomain;
	}

	public String getCookieKey() {
		return cookieKey;
	}

	public void setCookieKey(String cookieKey) {
		this.cookieKey = cookieKey;
	}

	public String getCookiePath() {
		return cookiePath;
	}

	public void setCookiePath(String cookiePath) {
		this.cookiePath = cookiePath;
	}

	public String getPassChangeUrl() {
		return passChangeUrl;
	}

	public void setPassChangeUrl(String passChangeUrl) {
		this.passChangeUrl = passChangeUrl;
	}

	public String getPassRegUrl() {
		return passRegUrl;
	}

	public void setPassRegUrl(String passRegUrl) {
		this.passRegUrl = passRegUrl;
	}

	public String getPassUrl() {
		return passUrl;
	}

	public void setPassUrl(String passUrl) {
		this.passUrl = passUrl;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public int getUsePass() {
		return usePass;
	}

	public void setUsePass(int usePass) {
		this.usePass = usePass;
	}

	List<RadioInt> radioYesNoList = new ArrayList<RadioInt>();

	private void setRadioYesNoListValues() {
		if (logger.isDebugEnabled()) {
			logger.debug("setRadioYesNoListValues() - start"); //$NON-NLS-1$
		}

		radioYesNoList.add(new RadioInt(0, this.getText("bbscs.no")));
		radioYesNoList.add(new RadioInt(1, this.getText("bbscs.yes")));

		if (logger.isDebugEnabled()) {
			logger.debug("setRadioYesNoListValues() - end"); //$NON-NLS-1$
		}
	}

	public List<RadioInt> getRadioYesNoList() {
		return radioYesNoList;
	}

	public void setRadioYesNoList(List<RadioInt> radioYesNoList) {
		this.radioYesNoList = radioYesNoList;
	}

	private AjaxMessagesJson ajaxMessagesJson;

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
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
		this.setCookieDomain(this.getSysConfig().getCookieDomain());
		this.setCookieKey(this.getSysConfig().getCookieKey());
		this.setCookiePath(this.getSysConfig().getCookiePath());
		this.setPassChangeUrl(this.getSysConfig().getPassChangeUrl());
		this.setPassRegUrl(this.getSysConfig().getPassRegUrl());
		this.setPassUrl(this.getSysConfig().getPassUrl());
		this.setUsePass(this.getSysConfig().getUsePass());
		return INPUT;
	}

	public String save() {
		this.getSysConfig().setCookieDomain(this.getCookieDomain());
		this.getSysConfig().setCookieKey(this.getCookieKey());
		this.getSysConfig().setCookiePath(this.getCookiePath());
		this.getSysConfig().setPassChangeUrl(this.getPassChangeUrl());
		this.getSysConfig().setPassRegUrl(this.getPassRegUrl());
		this.getSysConfig().setPassUrl(this.getPassUrl());
		this.getSysConfig().setUsePass(this.getUsePass());

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

	public String key() {
		byte[] key; //密钥文件(byte)
		try {
			DES des = new DES(DES._DESede); // 声明DES
			key = des.getKey(); //获取随机生成的密钥
			String keystr = Util.base64Encode(key);
			this.getSysConfig().setCookieKey(keystr);
			this.getSysConfig().saveConfigs();
			this.getAjaxMessagesJson().setMessage("0", keystr);
		} catch (Exception e) {
			logger.error(e);
			this.getAjaxMessagesJson().setMessage("E_GETKEY_001", this.getText("error.admincookie.getkey"));
		}
		return RESULT_AJAXJSON;
	}

}
