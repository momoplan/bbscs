package com.laoer.bbscs.web.action;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.bean.Role;
import com.laoer.bbscs.bean.UserGroup;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.RoleService;
import com.laoer.bbscs.service.UserGroupService;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;
import com.laoer.bbscs.web.ui.OptionsInt;

import java.util.*;

public class AdminUgSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminUgSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 1034059577699701524L;

	private UserGroupService userGroupService;

	private RoleService roleService;

	private AjaxMessagesJson ajaxMessagesJson;

	private String groupDesc;

	private String groupName;

	private int id;

	private List<Integer> roleIDs = new ArrayList<Integer>();

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Integer> getRoleIDs() {
		return roleIDs;
	}

	public void setRoleIDs(List<Integer> roleIDs) {
		this.roleIDs = roleIDs;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public UserGroupService getUserGroupService() {
		return userGroupService;
	}

	public void setUserGroupService(UserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}

	private List ugList;

	public List getUgList() {
		return ugList;
	}

	public void setUgList(List ugList) {
		this.ugList = ugList;
	}

	private List<OptionsInt> roleValues = new ArrayList<OptionsInt>();

	public List<OptionsInt> getRoleValues() {
		return roleValues;
	}

	public void setRoleValues(List<OptionsInt> roleValues) {
		this.roleValues = roleValues;
	}

	private void setRoleValuesInit() {
		List roleList = this.getRoleService().findRolesAll();
		for (int i = 0; i < roleList.size(); i++) {
			Role role = (Role) roleList.get(i);
			roleValues.add(new OptionsInt(role.getId(), role.getRoleName()));
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

	public String index() {
		return "index";
	}

	public String list() {
		this.setUgList(this.getUserGroupService().findUserGroupsAll());
		return "list";
	}

	public String add() {
		this.setAction("addsave");
		this.setRoleValuesInit();
		return "ugSet";
	}

	@SuppressWarnings("unchecked")
	public String addsave() {
		if (StringUtils.isBlank(this.getGroupName())) {
			this.getAjaxMessagesJson().setMessage("E_NULL", this.getText("error.nullerror"));
			return RESULT_AJAXJSON;
		}
		//System.out.println(this.getRoleIDs());
		if (this.getRoleIDs().isEmpty()) {
			this.getRoleIDs().add(new Integer(0));
		}
		List roles = this.getRoleService().findRolesInIDs(this.getRoleIDs());
		//System.out.println(roles);
		UserGroup ug = new UserGroup();
		ug.setGroupDesc(this.getGroupDesc());
		ug.setGroupName(this.getGroupName());
		ug.setTypeID(1);//非系统用户组

		for (int i = 0; i < roles.size(); i++) {
			Role role = (Role) roles.get(i);
			ug.getRoles().add(role);
		}

		try {
			this.getUserGroupService().saveUserGroup(ug);
			this.getAjaxMessagesJson().setMessage("0", this.getText("admin.ug.add.ok"));
		} catch (BbscsException e) {
			logger.error(e);
			this.getAjaxMessagesJson().setMessage("E_UG_ADDFAILED", this.getText("error.admin.ug.add"));
		}
		return RESULT_AJAXJSON;
	}

	@SuppressWarnings("unchecked")
	public String edit() {
		this.setAction("editsave");
		UserGroup ug = this.userGroupService.findUserGroupByID(this.getId());
		this.setGroupDesc(ug.getGroupDesc());
		this.setGroupName(ug.getGroupName());
		Iterator it = ug.getRoles().iterator();
		while (it.hasNext()) {
			Role role = (Role) it.next();
			this.getRoleIDs().add(role.getId());
		}
		this.setRoleValuesInit();
		return "ugSet";
	}

	@SuppressWarnings("unchecked")
	public String editsave() {
		if (StringUtils.isBlank(this.getGroupName())) {
			this.getAjaxMessagesJson().setMessage("E_NULL", this.getText("error.nullerror"));
			return RESULT_AJAXJSON;
		}
		UserGroup ug = this.userGroupService.findUserGroupByID(this.getId());
		ug.setGroupDesc(this.getGroupDesc());
		ug.setGroupName(this.getGroupName());

		ug.getRoles().clear();
		if (this.getRoleIDs().isEmpty()) {
			this.getRoleIDs().add(new Integer(0));
		}
		List roles = this.getRoleService().findRolesInIDs(roleIDs);
		for (int i = 0; i < roles.size(); i++) {
			Role role = (Role) roles.get(i);
			ug.getRoles().add(role);
		}
		try {
			this.getUserGroupService().updateUserGroup(ug);// 更新UserGroup
			this.getAjaxMessagesJson().setMessage("0", this.getText("admin.ug.edit.ok"));
		} catch (BbscsException e) {
			logger.error(e);
			this.getAjaxMessagesJson().setMessage("E_UG_EDITFAILED", this.getText("error.admin.ug.edit"));
		}
		return RESULT_AJAXJSON;
	}

	public String del() {
		UserGroup ug = this.userGroupService.findUserGroupByID(this.getId());
		if (ug.getTypeID() == 0) {
			this.getAjaxMessagesJson().setMessage("E_UG_CANNOTDEL", this.getText("error.admin.ug.cannotdel"));
			return RESULT_AJAXJSON;
		}
		try {
			this.getUserGroupService().removeUserGroup(ug);
			this.getAjaxMessagesJson().setMessage("0", this.getText("admin.ug.del.ok"));
		} catch (BbscsException e) {
			this.getAjaxMessagesJson().setMessage("E_UG_DELFAILED", this.getText("error.admin.ug.del"));
		}
		return RESULT_AJAXJSON;
	}

}
