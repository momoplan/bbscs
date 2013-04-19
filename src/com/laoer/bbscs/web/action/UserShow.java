package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;

import com.laoer.bbscs.bean.UserGroup;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.service.UserGroupService;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.web.PageList;
import com.laoer.bbscs.service.web.Pages;
import com.laoer.bbscs.web.interceptor.RequestBasePathAware;
import com.laoer.bbscs.web.ui.OptionsInt;

public class UserShow extends BaseMainAction implements RequestBasePathAware {

	/**
	 *
	 */
	private static final long serialVersionUID = -2614274147270394912L;

	private int groupID = 0;

	private String basePath;

	private List<OptionsInt> groupList = new ArrayList<OptionsInt>();

	private UserService userService;

	private UserGroupService userGroupService;

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public String getBasePath() {
		return basePath;
	}

	public List<OptionsInt> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<OptionsInt> groupList) {
		this.groupList = groupList;
	}

	public UserGroupService getUserGroupService() {
		return userGroupService;
	}

	public void setUserGroupService(UserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private PageList pageList;

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	public String index() {
		this.setGroupListInit();
		return SUCCESS;
	}

	public String list() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());
		pages.setPerPageNum(40);

		pages.setFileName(BBSCSUtil.getActionMappingURLWithoutPrefix("userShow?ajax=shtml&action=" + this.getAction()
				+ "&groupID=" + this.getGroupID()));
		this.setPageList(this.getUserService().findUserInfosByGroupID(this.getGroupID(), pages));
		return "userShowList";
	}

	private void setGroupListInit() {
		this.groupList.add(new OptionsInt(0, this.getText("bbscs.all")));
		List gs = this.getUserGroupService().findUserGroupsAll();
		for (int i = 0; i < gs.size(); i++) {
			UserGroup ug = (UserGroup) gs.get(i);
			this.groupList.add(new OptionsInt(ug.getId(), ug.getGroupName()));
		}
	}

}
