package com.laoer.bbscs.service.config;

import java.io.File;

public class UserConfig {

	private String safePath;

	public UserConfig() {
	}

	public String getUserFilePath(String userID) {
		StringBuffer sb = new StringBuffer();
		int num = Math.abs(userID.hashCode());
		sb.append(this.getSafePath());
		if (!this.getSafePath().endsWith("/")) {
			sb.append("/");
		}
		sb.append("user/");
		sb.append(num % 100);
		sb.append("/");
		sb.append(userID);
		sb.append("/");
		File ft = new File(sb.toString());
		if (!ft.exists()) {
			ft.mkdirs();
		}
		return sb.toString();
	}

	public String getIndexPath() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getSafePath());
		sb.append("index/");
		return sb.toString();
	}

	public File getIndexFilePath() {
		File indexFilePath = new File(this.getIndexPath());
		if (!indexFilePath.exists()) {
			indexFilePath.mkdirs();
		}
		return indexFilePath;
	}

	public boolean indexExist() {
		File file = new File(this.getIndexPath() + "segments");
		return file.exists();
	}

	public String getSafePath() {
		return safePath;
	}

	public void setSafePath(String safePath) {
		this.safePath = safePath;
	}
}
