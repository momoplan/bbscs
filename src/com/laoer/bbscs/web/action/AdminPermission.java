package com.laoer.bbscs.web.action;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.bean.Permission;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.PermissionService;
import com.laoer.bbscs.web.ui.OptionsInt;

public class AdminPermission extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminPermission.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 6947847123773828160L;

	public AdminPermission() {
		// TODO 自动生成构造函数存根
	}

	private PermissionService permissionService;

	private String actionName;

	private String permissionName;

	private String resource;

	private long id;

	private int typeID;

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	private List permissionList;

	public List getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List permissionList) {
		this.permissionList = permissionList;
	}

	private List<OptionsInt> permissionTypeValues = new ArrayList<OptionsInt>();

	public List<OptionsInt> getPermissionTypeValues() {
		return permissionTypeValues;
	}

	public void setPermissionTypeValues(List<OptionsInt> permissionTypeValues) {
		this.permissionTypeValues = permissionTypeValues;
	}

	private void setPermissionTypeValues() {
		for (int i = 0; i < 4; i++) {
			permissionTypeValues.add(new OptionsInt(i, this.getText("admin.permission.typeid" + i)));
		}
	}

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			logger.error(e);
			return ERROR;
		}
	}

	public String list() {
		this.setPermissionList(this.getPermissionService().findPermissionsAll());
		return "list";
	}

	public String add() {
		this.setAction("addsave");
		this.setPermissionTypeValues();
		return INPUT;
	}

	public String addsave() {
		Permission permission = new Permission();
		permission.setAction(this.getActionName());
		permission.setId(new Long(this.getId()));
		permission.setPermissionName(this.getPermissionName());
		permission.setResource(this.getResource());
		permission.setTypeID(this.getTypeID());

		try {
			this.getPermissionService().savePermission(permission);
			return SUCCESS;
		} catch (BbscsException e) {
			logger.error(e);
			this.setPermissionTypeValues();
			this.addActionError(this.getText("error.admin.permission.add"));
			return INPUT;
		}
	}

	public String edit() {
		this.setAction("editsave");
		this.setPermissionTypeValues();
		Permission permission = this.getPermissionService().findPermissionByID(this.getId());
		this.setActionName(permission.getAction());
		this.setPermissionName(permission.getPermissionName());
		this.setResource(permission.getResource());
		this.setTypeID(permission.getTypeID());
		return INPUT;
	}

	public String editsave() {
		Permission permission = this.getPermissionService().findPermissionByID(this.getId());
		permission.setAction(this.getActionName());
		permission.setPermissionName(this.getPermissionName());
		permission.setResource(this.getResource());
		permission.setTypeID(this.getTypeID());

		try {
			this.getPermissionService().updatePermission(permission);
			return SUCCESS;

		} catch (BbscsException e) {
			logger.error(e);
			this.setPermissionTypeValues();
			this.addActionError(this.getText("error.admin.permission.edit"));
			return INPUT;
		}
	}

}
