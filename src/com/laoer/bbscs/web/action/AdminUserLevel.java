package com.laoer.bbscs.web.action;

import org.apache.log4j.Logger;
import com.laoer.bbscs.service.*;
import java.util.*;
import org.apache.commons.lang.*;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.exception.BbscsException;

public class AdminUserLevel extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AdminUserLevel.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -6354084988940577017L;

	private UserLevelService userLevelService;

	private List userLevelList;

	private String id;

	private String levelName;

	private int levelValue;

	private int levelType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getLevelType() {
		return levelType;
	}

	public void setLevelType(int levelType) {
		this.levelType = levelType;
	}

	public int getLevelValue() {
		return levelValue;
	}

	public void setLevelValue(int levelValue) {
		this.levelValue = levelValue;
	}

	public List getUserLevelList() {
		return userLevelList;
	}

	public void setUserLevelList(List userLevelList) {
		this.userLevelList = userLevelList;
	}

	public UserLevelService getUserLevelService() {
		return userLevelService;
	}

	public void setUserLevelService(UserLevelService userLevelService) {
		this.userLevelService = userLevelService;
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
		this.setUserLevelList(this.getUserLevelService().findUserLevelsByType(this.getLevelType()));
		return "userlevel";
	}

	public String add() {
		this.setAction("addnew");
		return INPUT;
	}

	public String addnew() {
		if (StringUtils.isBlank(this.getLevelName())) {
			this.addActionMessage(this.getText("error.nullerror"));
			return INPUT;
		}
		UserLevel ul = new UserLevel();
		ul.setLevelName(this.getLevelName());
		ul.setLevelType(this.getLevelType());
		ul.setLevelValue(this.getLevelValue());

		try {
			ul = this.getUserLevelService().saveUserLevel(ul);
		} catch (BbscsException e) {
			logger.error(e);
			this.addActionError(this.getText("error.admin.ul.add"));
			return INPUT;
		}

		return SUCCESS;
	}

	public String edit() {
		UserLevel ul = this.getUserLevelService().findUserLevelById(this.getId());
		if (ul == null) {
			this.addActionError(this.getText("error.admin.ul.get"));
			return ERROR;
		}
		this.setAction("editdo");
		this.setLevelName(ul.getLevelName());
		this.setLevelType(ul.getLevelType());
		this.setLevelValue(ul.getLevelValue());
		return INPUT;
	}

	public String editdo() {
		UserLevel ul = this.getUserLevelService().findUserLevelById(this.getId());
		if (ul == null) {
			this.addActionError(this.getText("error.admin.ul.get"));
			return ERROR;
		}
		ul.setLevelName(this.getLevelName());
		ul.setLevelValue(this.getLevelValue());

		try {
			ul = this.getUserLevelService().saveUserLevel(ul);
		} catch (BbscsException e) {
			logger.error(e);
			this.addActionError(this.getText("error.admin.ul.add"));
			return INPUT;
		}

		return SUCCESS;
	}

	public String del() {
		UserLevel ul = this.getUserLevelService().findUserLevelById(this.getId());
		if (ul == null) {
			this.addActionError(this.getText("error.admin.ul.get"));
			return ERROR;
		}
		try {
			this.getUserLevelService().removeUserLevel(ul);
		} catch (BbscsException e) {
			logger.error(e);
			this.addActionError(this.getText("error.admin.ul.add"));
			return ERROR;
		}
		return SUCCESS;
	}

}
