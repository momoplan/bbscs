package com.laoer.bbscs.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.*;

import java.util.*;
import com.laoer.bbscs.comm.*;
import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ui.*;

public class AdminDateTimeFormatSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminDateTimeFormatSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -3017534659449736260L;

	public AdminDateTimeFormatSet() {
		this.setDateShowTypeValues();
	}

	private SysConfig sysConfig;

	private String birthDateTimeFormat;

	private int dateShowType;

	private int defaultTimeZone;

	private String forumDateTimeFormat;

	private String otherDateTimeFormat;

	private String postDateTimeFormat;

	private String regDateTimeFormat;

	private String timeFormat;

	private List timeZoneValues = Constant.TIMEZONE;

	private List<OptionsInt> dateShowTypeValues = new ArrayList<OptionsInt>();

	public String getBirthDateTimeFormat() {
		return birthDateTimeFormat;
	}

	public void setBirthDateTimeFormat(String birthDateTimeFormat) {
		this.birthDateTimeFormat = birthDateTimeFormat;
	}

	public int getDateShowType() {
		return dateShowType;
	}

	public void setDateShowType(int dateShowType) {
		this.dateShowType = dateShowType;
	}

	public int getDefaultTimeZone() {
		return defaultTimeZone;
	}

	public void setDefaultTimeZone(int defaultTimeZone) {
		this.defaultTimeZone = defaultTimeZone;
	}

	public String getForumDateTimeFormat() {
		return forumDateTimeFormat;
	}

	public void setForumDateTimeFormat(String forumDateTimeFormat) {
		this.forumDateTimeFormat = forumDateTimeFormat;
	}

	public String getOtherDateTimeFormat() {
		return otherDateTimeFormat;
	}

	public void setOtherDateTimeFormat(String otherDateTimeFormat) {
		this.otherDateTimeFormat = otherDateTimeFormat;
	}

	public String getPostDateTimeFormat() {
		return postDateTimeFormat;
	}

	public void setPostDateTimeFormat(String postDateTimeFormat) {
		this.postDateTimeFormat = postDateTimeFormat;
	}

	public String getRegDateTimeFormat() {
		return regDateTimeFormat;
	}

	public void setRegDateTimeFormat(String regDateTimeFormat) {
		this.regDateTimeFormat = regDateTimeFormat;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public List getDateShowTypeValues() {
		return dateShowTypeValues;
	}

	public void setDateShowTypeValues(List<OptionsInt> dateShowTypeValues) {
		this.dateShowTypeValues = dateShowTypeValues;
	}

	public List getTimeZoneValues() {
		return timeZoneValues;
	}

	public void setTimeZoneValues(List timeZoneValues) {
		this.timeZoneValues = timeZoneValues;
	}

	private void setDateShowTypeValues() {
		this.dateShowTypeValues.add(new OptionsInt(0, this.getText("admin.DateShowType0")));
		this.dateShowTypeValues.add(new OptionsInt(1, this.getText("admin.DateShowType1")));
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
		this.setBirthDateTimeFormat(this.getSysConfig().getBirthDateTimeFormat());
		this.setDateShowType(this.getSysConfig().getDateShowType());
		this.setDefaultTimeZone(this.getSysConfig().getDefaultTimeZone());
		this.setForumDateTimeFormat(this.getSysConfig().getForumDateTimeFormat());
		this.setOtherDateTimeFormat(this.getSysConfig().getOtherDateTimeFormat());
		this.setPostDateTimeFormat(this.getSysConfig().getPostDateTimeFormat());
		this.setRegDateTimeFormat(this.getSysConfig().getRegDateTimeFormat());
		this.setTimeFormat(this.getSysConfig().getTimeFormat());
		return INPUT;
	}

	public String save() {
		if (StringUtils.isBlank(this.getBirthDateTimeFormat()) || StringUtils.isBlank(this.getForumDateTimeFormat())
				|| StringUtils.isBlank(this.getOtherDateTimeFormat())
				|| StringUtils.isBlank(this.getPostDateTimeFormat())
				|| StringUtils.isBlank(this.getRegDateTimeFormat()) || StringUtils.isBlank(this.getTimeFormat())) {
			this.addActionError(this.getText("error.nullerror"));
			return INPUT;
		}
		this.getSysConfig().setBirthDateTimeFormat(
				StringUtils.defaultIfEmpty(this.getBirthDateTimeFormat(), "yyyy-MM-dd"));
		this.getSysConfig().setDateShowType(this.getDateShowType());
		// System.out.println(adminDateTimeFormatSetForm.getForumDateTimeFormat());
		this.getSysConfig().setDefaultTimeZone(this.getDefaultTimeZone());
		this.getSysConfig().setForumDateTimeFormat(
				StringUtils.defaultIfEmpty(this.getForumDateTimeFormat(), "MM-dd HH:mm"));
		this.getSysConfig().setOtherDateTimeFormat(
				StringUtils.defaultIfEmpty(this.getOtherDateTimeFormat(), "yyyy-MM-dd HH:mm:ss"));
		this.getSysConfig().setPostDateTimeFormat(
				StringUtils.defaultIfEmpty(this.getPostDateTimeFormat(), "yyyy-MM-dd HH:mm:ss"));
		this.getSysConfig().setRegDateTimeFormat(StringUtils.defaultIfEmpty(this.getRegDateTimeFormat(), "yyyy-MM-dd"));
		this.getSysConfig().setTimeFormat(StringUtils.defaultIfEmpty(this.getTimeFormat(), ""));

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

}
