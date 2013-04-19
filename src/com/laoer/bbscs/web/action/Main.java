package com.laoer.bbscs.web.action;

import com.laoer.bbscs.comm.BBSCSUtil;
import org.apache.commons.lang.*;
import com.laoer.bbscs.comm.*;

public class Main extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 5103927236234600839L;

	private String inUrl;

	private long bid;

	private String postID;

	private String nagUrl;

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public String getInUrl() {
		return inUrl;
	}

	public void setInUrl(String inUrl) {
		this.inUrl = inUrl;
	}

	public String getPostID() {
		return postID;
	}

	public void setPostID(String postID) {
		this.postID = postID;
	}

	/**
	 * private String basePath;
	 *
	 * public void setBasePath(String basePath) { this.basePath = basePath; }
	 *
	 * public String getBasePath() { return basePath; }
	 */

	public String getNagUrl() {
		return nagUrl;
	}

	public void setNagUrl(String nagUrl) {
		this.nagUrl = nagUrl;
	}

	public String execute() {
		if (this.getAction().equalsIgnoreCase("read")) {
			if (this.bid == 0 && StringUtils.isBlank(this.postID)) {
				this.setInUrl(BBSCSUtil.getActionMappingURLWithoutPrefix("in"));
			} else {
				this.setInUrl(BBSCSUtil.getActionMappingURLWithoutPrefix("read?action=topic&bid=" + this.bid + "&id="
						+ this.postID));
			}
		} else if (this.getAction().equalsIgnoreCase("forum")) {
			if (this.bid == 0) {
				this.setInUrl(BBSCSUtil.getActionMappingURLWithoutPrefix("in"));
			} else {
				this.setInUrl(BBSCSUtil.getActionMappingURLWithoutPrefix("forum?action=index&bid=" + this.bid));
			}
		} else {
			if (StringUtils.isBlank(this.getInUrl())) {
				this.setInUrl(BBSCSUtil.getActionMappingURLWithoutPrefix("in"));
			}
		}
		if (Constant.USE_URL_REWRITE) {
			this.setNagUrl("nag.html");
		}
		else {
			this.setNagUrl(BBSCSUtil.getActionMappingURLWithoutPrefix("nag"));
		}
		return SUCCESS;
	}

}
