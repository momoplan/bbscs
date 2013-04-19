package com.laoer.bbscs.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.bean.Permission;
import com.laoer.bbscs.bean.Role;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.PermissionService;
import com.laoer.bbscs.service.RoleService;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;
import com.laoer.bbscs.web.ui.OptionsLong;

import java.util.*;
import org.apache.commons.lang.*;

public class AdminRole extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminRole.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -4385682639442172413L;

	private RoleService roleService;

	private PermissionService permissionService;

	private AjaxMessagesJson ajaxMessagesJson;

	private List<Long> permissions = new ArrayList<Long>();;

	private String roleName;

	private int id;

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Long> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Long> permissions) {
		this.permissions = permissions;
	}

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	private List roleList;

	public List getRoleList() {
		return roleList;
	}

	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

	private List<OptionsLong> permissionValues = new ArrayList<OptionsLong>();

	public List getPermissionValues() {
		return permissionValues;
	}

	public void setPermissionValues(List<OptionsLong> permissionValues) {
		this.permissionValues = permissionValues;
	}

	@SuppressWarnings("unchecked")
	private void setPermissionValuesInit() {
		List permissionList = this.getPermissionService().findPermissionsByTypeID(0);
		permissionList.addAll(this.getPermissionService().findPermissionsByTypeID(1));

		for (int i = 0; i < permissionList.size(); i++) {
			Permission p = (Permission) permissionList.get(i);
			permissionValues.add(new OptionsLong(p.getId(), p.getPermissionName() + "(" + p.getId() + ")"));
		}

		// this.setPermissionValues(permissionList);
	}

	@SuppressWarnings("unchecked")
	private void setBoardPermissionValuesInit() {
		List permissionList = this.getPermissionService().findPermissionsByTypeID(2);
		permissionList.addAll(this.getPermissionService().findPermissionsByTypeID(3));
		for (int i = 0; i < permissionList.size(); i++) {
			Permission p = (Permission) permissionList.get(i);
			permissionValues.add(new OptionsLong(p.getId(), p.getPermissionName() + "(" + p.getId() + ")"));
		}
		// this.setPermissionValues(permissionList);
	}

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			logger.error(e);
			return ERROR;
		}
	}

	public String index() {
		return "index";
	}

	public String list() {
		this.setRoleList(this.getRoleService().findRolesAll());
		return "list";
	}

	public String add() {
		this.setAction("addsave");
		this.setPermissionValuesInit();
		return "roleSet";
	}

	public String addsave() {
		if (StringUtils.isBlank(this.getRoleName())) {
			this.getAjaxMessagesJson().setMessage("E_NULL", this.getText("error.nullerror"));
			return RESULT_AJAXJSON;
		}
		Role role = new Role();
		role.setRoleName(this.getRoleName());
		role.setTypeID(Constant.ROLE_TYPE_USERADD);
		// System.out.println(this.getPermissions());

		role.setPermissions(this.getPermissions());
		try {
			role = this.getRoleService().saveRole(role);
			this.getAjaxMessagesJson().setMessage("0", this.getText("admin.role.add.ok"));
		} catch (BbscsException e) {
			logger.error(e);
			this.getAjaxMessagesJson().setMessage("E_ROLE_ADDFAILED", this.getText("error.admin.role.add"));
		}
		return RESULT_AJAXJSON;
	}

	@SuppressWarnings("unchecked")
	public String edit() {
		this.setAction("editsave");
		Role role = this.getRoleService().findRoleByID(this.getId());
		this.setRoleName(role.getRoleName());
		this.setPermissions(role.getPermissions());
		if (role.getTypeID() == Constant.ROLE_TYPE_BOARD) {
			this.setBoardPermissionValuesInit();
		} else {
			this.setPermissionValuesInit();
		}
		return "roleSet";
	}

	public String editsave() {
		if (StringUtils.isBlank(this.getRoleName())) {
			this.getAjaxMessagesJson().setMessage("E_NULL", this.getText("error.nullerror"));
			return RESULT_AJAXJSON;
		}
		Role role = this.getRoleService().findRoleByID(this.getId());
		role.setRoleName(this.getRoleName());
		role.setPermissions(this.getPermissions());

		try {
			this.getRoleService().saveRole(role);
			this.getAjaxMessagesJson().setMessage("0", this.getText("admin.role.edit.ok"));
		} catch (BbscsException e) {
			logger.error(e);
			this.getAjaxMessagesJson().setMessage("E_ROLE_EDITFAILED", this.getText("error.admin.role.edit"));
		}
		return RESULT_AJAXJSON;
	}

	public String del() {
		Role role = this.getRoleService().findRoleByID(this.getId());
		if (role.getTypeID() <= 1) {
			this.getAjaxMessagesJson().setMessage("E_ROLE_CANNOTDEL", this.getText("error.admin.role.cannotdel"));
			return RESULT_AJAXJSON;
		} else {
			try {
				this.getRoleService().removeRole(role);
				this.getAjaxMessagesJson().setMessage("0", this.getText("admin.role.del.ok"));
			} catch (BbscsException ex1) {
				this.getAjaxMessagesJson().setMessage("E_ROLE_DELFAILED", this.getText("error.admin.role.del"));
			}
		}
		return RESULT_AJAXJSON;
	}

}
