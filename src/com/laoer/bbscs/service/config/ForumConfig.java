package com.laoer.bbscs.service.config;

import java.io.File;
import java.util.Date;

import com.laoer.bbscs.comm.*;

public class ForumConfig {

	private String safePath;

	public String getSafePath() {
		return safePath;
	}

	public void setSafePath(String safePath) {
		this.safePath = safePath;
	}

	public String getForumPath(long bid, long adate) {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getSafePath());
		if (!this.getSafePath().endsWith("/")) {
			sb.append("/");
		}
		sb.append("post/");
		sb.append(bid % 20);
		sb.append("/");
		sb.append(bid);
		sb.append("/");
		sb.append(Util.formatDate4(new Date(adate)));
		sb.append("/");
		sb.append(adate % 100);
		sb.append("/");
		File ft = new File(sb.toString());
		if (!ft.exists()) {
			ft.mkdirs();
		}
		return sb.toString();
	}

	public String getForumPathOld(long bid, long adate) {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getSafePath());
		if (!this.getSafePath().endsWith("/")) {
			sb.append("/");
		}
		sb.append("post/");
		sb.append(bid % 20);
		sb.append("/");
		sb.append(bid);
		sb.append("/");
		sb.append(Util.formatDate4(new Date(adate)));
		sb.append("/");
		File ft = new File(sb.toString());
		if (!ft.exists()) {
			ft.mkdirs();
		}
		return sb.toString();
	}
}
