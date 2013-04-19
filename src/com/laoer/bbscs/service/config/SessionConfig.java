package com.laoer.bbscs.service.config;

import java.io.File;

import com.laoer.bbscs.comm.*;

public class SessionConfig {

	private String safePath;

	public String getSafePath() {
		return safePath;
	}

	public void setSafePath(String safePath) {
		this.safePath = safePath;
	}

	public String getSessionPath(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getSafePath());
		if (!this.getSafePath().endsWith("/")) {
			sb.append("/");
		}
		sb.append("session/");
		sb.append(BBSCSUtil.getStringHashCode(id) % 100);
		sb.append("/");
		String tmp = "";
		if (id.length() > 5) {
			tmp = id.substring(0, 5);
		} else {
			tmp = id;
		}
		sb.append(BBSCSUtil.getStringHashCode(tmp) % 100);
		sb.append("/");
		sb.append(id);
		sb.append("/");
		File ft = new File(sb.toString());
		if (!ft.exists()) {
			ft.mkdirs();
		}
		return sb.toString();
	}

}
