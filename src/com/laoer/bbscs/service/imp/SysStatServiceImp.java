package com.laoer.bbscs.service.imp;

import java.util.*;
import java.io.*;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.service.config.UserConfig;
import com.laoer.bbscs.comm.*;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * <p>
 * Title: Tianyi BBS
 * </p>
 *
 * <p>
 * Description: BBSCS
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 *
 * <p>
 * Company: Laoer.com
 * </p>
 *
 * @author Gong Tianyi
 * @version 7.0
 */
public class SysStatServiceImp extends SysStatService {

	private static final Log logger = LogFactory.getLog(SysStatServiceImp.class);

	private UserConfig userConfig;

	public UserConfig getUserConfig() {
		return userConfig;
	}

	public void setUserConfig(UserConfig userConfig) {
		this.userConfig = userConfig;
	}

	public SysStatServiceImp() {
		// load();
	}

	public void load() {
		Properties prop = new Properties();
		File f = new File(this.getUserConfig().getSafePath() + "sysstat.properties");
		if (f.exists()) {
			try {
				FileInputStream fis = new FileInputStream(f);
				prop.load(fis);
				this.setOnlineNum(Long.parseLong(prop.getProperty("onlineNum", "0").trim()));
				this.setAppearTime(Long.parseLong(prop.getProperty("appearTime", "0").trim()));
				this.setAllUserNum(Long.parseLong(prop.getProperty("allUserNum", "0").trim()));
				this.setLastRegUser(prop.getProperty("lastRegUser", ""));
				this.setPostMainNum(Long.parseLong(prop.getProperty("postMainNum", "0").trim()));
				this.setPostNum(Long.parseLong(prop.getProperty("postNum", "0").trim()));
				this.setAppearTimeStr(Util.formatDateTime(new Date(this.getAppearTime())));
				fis.close();
			} catch (NumberFormatException ex) {
				logger.error(ex);
			} catch (FileNotFoundException ex) {
				logger.error(ex);
			} catch (IOException ex) {
				logger.error(ex);
			}
		} else {
			save();
		}
	}

	private void save() {
		String path = this.getUserConfig().getSafePath() + "sysstat.properties";
		Properties prop = new Properties();
		prop.setProperty("onlineNum", String.valueOf(this.getOnlineNum()));
		prop.setProperty("appearTime", String.valueOf(this.getAppearTime()));
		prop.setProperty("allUserNum", String.valueOf(this.getAllUserNum()));
		prop.setProperty("lastRegUser", this.getLastRegUser());
		prop.setProperty("postNum", String.valueOf(this.getPostNum()));
		prop.setProperty("postMainNum", String.valueOf(this.getPostMainNum()));
		try {
			FileOutputStream fos = new FileOutputStream(path);
			prop.store(fos, "sysstat.properties");
			fos.close();
		} catch (FileNotFoundException ex) {
			logger.error(ex);
		} catch (IOException ex) {
			logger.error(ex);
		}
	}

	/**
	 * saveAllUserNum
	 *
	 * @param allusernum
	 *            int
	 * @param lastreguser
	 *            String
	 * @todo Implement this com.laoer.bbscs.service.SysStatService method
	 */
	public void saveAllUserNum(long allusernum, String lastreguser) {
		this.load();
		this.setAllUserNum(allusernum);
		this.setLastRegUser(lastreguser);
		this.save();
	}

	/**
	 * saveOnline
	 *
	 * @param nowonlinenum
	 *            int
	 * @todo Implement this com.laoer.bbscs.service.SysStatService method
	 */
	public void saveOnline(long nowonlinenum) {
		this.load();
		if (nowonlinenum > this.getOnlineNum()) {
			long atime = System.currentTimeMillis();
			this.setOnlineNum(nowonlinenum);
			this.setAppearTime(atime);
			this.setAppearTimeStr(Util.formatDateTime(new Date(atime)));
			this.save();
		}
	}

	/**
	 * savePostNum
	 *
	 * @param main
	 *            int
	 * @param all
	 *            int
	 * @todo Implement this com.laoer.bbscs.service.SysStatService method
	 */
	public void savePostNum(long main, long all) {
		this.load();
		this.setPostMainNum(main);
		this.setPostNum(all);
		this.save();
	}
}
