package com.laoer.bbscs.web.action;

import org.apache.commons.lang.StringUtils;

import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.web.interceptor.RequestBasePathAware;

public class BoardPasswd extends BaseMainAction implements RequestBasePathAware {

	/**
	 *
	 */
	private static final long serialVersionUID = 3731883365585632134L;

	private long bid = 0;

	private String passwd;

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	private String forwardUrl;

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	private String basePath;

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBasePath() {
		return basePath;
	}

	public String pass() {
		if (StringUtils.isBlank(this.getPasswd())) {
			this.addActionError(this.getText("error.nullerror"));
			return "boardPasswd";
		}
		this.getUserSession().setBoardPass(this.getBid() + ":" + this.getPasswd());
		this.setForwardUrl(this.getBasePath()
				+ BBSCSUtil.getActionMappingURLWithoutPrefix("forum?action=index&bid=" + this.getBid()));
		return SUCCESS;
	}

}
