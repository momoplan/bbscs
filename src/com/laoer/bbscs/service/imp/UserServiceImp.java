package com.laoer.bbscs.service.imp;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.service.web.PageList;
import com.laoer.bbscs.service.web.Pages;
import com.laoer.bbscs.web.servlet.UserSession;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.comm.*;
import com.laoer.bbscs.fio.*;
import org.apache.commons.logging.*;

public class UserServiceImp implements UserService {

	private static final Log logger = LogFactory.getLog(UserServiceImp.class);

	private UserInfoDAO userInfoDAO;

	private UserInfoFileIO userInfoFileIO;

	private UserGroupDAO userGroupDAO;

	private PermissionDAO permissionDAO;

	private Cache userPermissionCache;

	private UserLevelService userLevelService;

	public PermissionDAO getPermissionDAO() {
		return permissionDAO;
	}

	public void setPermissionDAO(PermissionDAO permissionDAO) {
		this.permissionDAO = permissionDAO;
	}

	public UserGroupDAO getUserGroupDAO() {
		return userGroupDAO;
	}

	public void setUserGroupDAO(UserGroupDAO userGroupDAO) {
		this.userGroupDAO = userGroupDAO;
	}

	public Cache getUserPermissionCache() {
		return userPermissionCache;
	}

	public void setUserPermissionCache(Cache userPermissionCache) {
		this.userPermissionCache = userPermissionCache;
	}

	public UserInfoDAO getUserInfoDAO() {
		return userInfoDAO;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

	public UserInfoFileIO getUserInfoFileIO() {
		return userInfoFileIO;
	}

	public void setUserInfoFileIO(UserInfoFileIO userInfoFileIO) {
		this.userInfoFileIO = userInfoFileIO;
	}

	public UserLevelService getUserLevelService() {
		return userLevelService;
	}

	public void setUserLevelService(UserLevelService userLevelService) {
		this.userLevelService = userLevelService;
	}

	public void createTopFile() throws BbscsException {
		// TODO 自动生成方法存根

	}

	public void createUserFacePic(UserInfo ui, String distFileName, InputStream stream) throws BbscsException {
		if (ui.getHavePic() == 1) {
			this.getUserInfoFileIO().delUserPicFile(ui);
		}
		try {
			this.getUserInfoFileIO().saveUserUpFile(ui, distFileName, stream);
			ui.setHavePic(1);
			ui.setPicFileName(distFileName);
			ui = this.getUserInfoDAO().saveUserInfo(ui);
			this.getUserInfoFileIO().writeUserFile(ui);
		} catch (Exception e) {
			logger.error(e);
			throw new BbscsException(e);
		}
	}

	public UserInfo findUserInfoByEmail(String email) {
		return this.getUserInfoDAO().findUserInfoByEmail(email);
	}

	public UserInfo findUserInfoById(String id) {
		return this.getUserInfoDAO().findUserInfoById(id);
	}

	public UserInfo findUserInfoByUserName(String userName) {
		return this.getUserInfoDAO().findUserInfoByUserName(userName);
	}

	public PageList findUserInfoList(String orderby, String ascordesc, Pages pages) {
		PageList pl = new PageList();

		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(this.getAllUserNum());
		}
		pages.executeCount();

		List l = this.getUserInfoDAO().findUserInfoList(orderby, ascordesc, pages.getSpage(), pages.getPerPageNum());

		pl.setObjectList(l);
		pl.setPages(pages);
		return pl;
	}

	public PageList findUserInfosByGroupID(int groupID, String orderby, int ascOrDesc, Pages pages) {
		PageList pl = new PageList();

		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(this.getUserInfoDAO().getUserNumByGroupID(groupID));
		}
		pages.executeCount();

		List l = this.getUserInfoDAO().findUserInfosByGroupID(groupID, orderby, ascOrDesc, pages.getSpage(),
				pages.getPerPageNum());

		pl.setObjectList(l);
		pl.setPages(pages);
		return pl;
	}

	public PageList findUserInfosByGroupID(int groupID, Pages pages) {
		PageList pl = new PageList();

		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(this.getUserInfoDAO().getUserNumByGroupID(groupID));
		}
		pages.executeCount();

		List l = this.getUserInfoDAO().findUserInfosByGroupID(groupID, "regTime", Constant.ORDER_DESC,
				pages.getSpage(), pages.getPerPageNum());

		pl.setObjectList(l);
		pl.setPages(pages);
		return pl;
	}

	public long getAllUserNum() {
		return this.getUserInfoDAO().getAllUserNum();
	}

	public UserInfoSimple getUserInfoSimple(String userID) {
		return this.getUserInfoFileIO().getUserInfoSimple(userID);
	}

	public long getUserNumByGroupID(int groupID) {
		return this.getUserInfoDAO().getUserNumByGroupID(groupID);
	}

	public Map[] getUserPermission(UserInfo userInfo) {
		return this.getUserPermission(userInfo.getGroupID());
	}

	public Map[] getUserPermission(int groupID) {
		if (Constant.USE_PERMISSION_CACHE) {
			Map[] userPermission = (Map[]) this.getUserPermissionCache().get("U_" + String.valueOf(groupID));
			if (userPermission == null) {
				userPermission = this.getPermissionMaps(groupID);
				this.getUserPermissionCache().add("U_" + String.valueOf(groupID), userPermission);
			}
			return userPermission;
		} else {
			return this.getPermissionMaps(groupID);
		}
	}

	@SuppressWarnings("unchecked")
	private Map[] getPermissionMaps(int groupID) {
		Map[] userPermission = { new HashMap(), new HashMap() };
		UserGroup ug = this.getUserGroupDAO().findUserGroupByID(groupID); // 取得用户属组
		if (ug != null) {
			Iterator it = ug.getRoles().iterator(); // 取得角色列表
			while (it.hasNext()) {
				Role role = (Role) it.next();
				List permissions = role.getPermissions(); // 取得角色的权限ID列表
				if (permissions != null && !permissions.isEmpty()) {
					List permissionList = this.getPermissionDAO().findPermissionnIDs(permissions); // 取得权限列表
					for (int i = 0; i < permissionList.size(); i++) {
						Permission permission = (Permission) permissionList.get(i);
						if (permission.getTypeID() == 0) {
							userPermission[0].put(permission.getResource() + "," + permission.getAction(), permission);
						} else {
							userPermission[1].put(permission.getId(), permission);
						}
					}
				}
			}
		}
		return userPermission;
	}

	public String getUserTitle(UserInfo ui) {
		int type = 0;
		List ull = null;
		if (ui == null) {
			ull = this.getUserLevelService().findUserLevelsByType(type);
			return this.getUserLevelByUserValue(ull, 0).getLevelName();
		} else {
			type = ui.getUserTitle();
			ull = this.getUserLevelService().findUserLevelsByType(type);
			if (ull != null) {
				return this.getUserLevelByUserValue(ull, this.getUserTitleValue(ui)).getLevelName();
			} else {
				return "-";
			}
		}
	}

	public String getUserTitle(UserInfoSimple uis) {
		int type = 0;
		List ull = null;
		if (uis == null) {
			ull = this.getUserLevelService().findUserLevelsByType(type);
			return this.getUserLevelByUserValue(ull, 0).getLevelName();
		} else {
			type = uis.getUserTitle();
			ull = this.getUserLevelService().findUserLevelsByType(type);
			if (ull != null) {
				return this.getUserLevelByUserValue(ull, this.getUserTitleValue(uis)).getLevelName();
			} else {
				return "-";
			}
		}
	}

	private UserLevel getUserLevelByUserValue(List l, int value) {
		for (int i = 0; i < l.size(); i++) {
			UserLevel ul = (UserLevel) l.get(i);
			if (value < ul.getLevelValue()) {
				return ul;
			}
		}
		UserLevel ul = new UserLevel();
		ul.setLevelName("-");
		ul.setLevelValue(0);
		return ul;
	}

	public int getUserTitleValue(UserInfo ui) {
		switch (ui.getUserTitle()) {
		case 0:
			return ui.getExperience();
		case 1:
			return ui.getLiterary();
		case 2:
			return ui.getUserKnow();
		case 3:
			return ui.getCoin();
		default:
			return ui.getExperience();
		}
	}

	public int getUserTitleValue(UserInfoSimple uis) {
		switch (uis.getUserTitle()) {
		case 0:
			return uis.getExperience();
		case 1:
			return uis.getLiterary();
		case 2:
			return uis.getUserKnow();
		case 3:
			return uis.getCoin();
		default:
			return uis.getExperience();
		}
	}

	public void removeUserFacePic(UserInfo ui) throws BbscsException {
		if (ui.getHavePic() == 1) {
			this.getUserInfoFileIO().delUserPicFile(ui);
		}
		try {
			ui.setHavePic(0);
			ui.setPicFileName("");
			ui = this.getUserInfoDAO().saveUserInfo(ui);
			this.getUserInfoFileIO().writeUserFile(ui);
		} catch (Exception e) {
			logger.error(e);
			throw new BbscsException(e);
		}
	}
	
	public UserInfo saveAtLogin(UserInfo userInfo) throws BbscsException {
		try {
			if ((System.currentTimeMillis() - userInfo.getLastLoginTime().getTime()) > 30 * 60000) {
				userInfo.setLoginTimes(userInfo.getLoginTimes() + 1);
				userInfo.setExperience(userInfo.getExperience() + 1);
			}
			userInfo = this.getUserInfoDAO().saveUserInfo(userInfo);
			this.getUserInfoFileIO().writeUserFile(userInfo);
			return userInfo;
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public UserInfo saveUserInfo(UserInfo userInfo) throws BbscsException {
		try {
			userInfo = this.getUserInfoDAO().saveUserInfo(userInfo);
			this.getUserInfoFileIO().writeUserFile(userInfo);
			return userInfo;
		} catch (Exception e) {
			logger.error(e);
			throw new BbscsException(e);
		}
	}

	public void writeUserFile(UserInfo userInfo) {
		try {
			this.getUserInfoFileIO().writeUserFile(userInfo);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public UserSession getUserSession(String userName) {
		if (!userName.startsWith(Constant.GUEST_USERNAME)) {
			UserInfo ui = this.getUserInfoDAO().findUserInfoByUserName(userName);
			String nickname = ui.getNickName();
			String re = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";
			Pattern pattern = Pattern.compile(re);
			Matcher matcher = pattern.matcher(nickname);
			Matcher matcher2 = pattern.matcher(ui.getUserName());
			if (matcher.matches()&&nickname.length()>7) {
				String new_nickName = nickname.substring(0, 3) + "****" + nickname.substring(7);
				ui.setNickName(new_nickName);
			}else if(matcher2.matches()&&(nickname==null||nickname.equals("null")||nickname.length()==0)&&ui.getUserName().length()>7){
				String new_nickName = ui.getUserName().substring(0, 3) + "****" + ui.getUserName().substring(7);
				ui.setNickName(new_nickName);
			}else{
				ui.setNickName(nickname);
			}
			if (ui != null) {
				return this.getUserSession(ui);
			}
		}
		long now = System.currentTimeMillis();
		UserSession us = new UserSession();

		us.setGroupID(Constant.USER_GROUP_GUEST);

		us.setId(Constant.GUEST_USERID + now);
		us.setNickName("Guest");
		us.setUserName(Constant.GUEST_USERNAME + now);
		Map[] permissionMap = this.getUserPermission(Constant.USER_GROUP_GUEST);
		us.setUserPermissionArray(permissionMap);

		return us;
	}

	public UserSession getUserSession(UserInfo ui) {
		UserSession us = new UserSession();
		us.setEmail(ui.getEmail());
		us.setGroupID(ui.getGroupID());
		us.setId(ui.getId());
		
		String nickname = ui.getNickName();
		String re = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";
		Pattern pattern = Pattern.compile(re);
		Matcher matcher = pattern.matcher(nickname);
		Matcher matcher2 = pattern.matcher(ui.getUserName());
		if (matcher.matches()&&nickname.length()>7) {
			String new_nickName = nickname.substring(0, 3) + "****" + nickname.substring(7);
			us.setNickName(new_nickName);
		}else if(matcher2.matches()&&(nickname==null||nickname.equals("null")||nickname.length()==0)&&ui.getUserName().length()>7){
			String new_nickName = ui.getUserName().substring(0, 3) + "****" + ui.getUserName().substring(7);
			us.setNickName(new_nickName);
		}else{
			us.setNickName(nickname);
		}
		
		
		String[] signDetail = new String[3];
		signDetail[0] = ui.getSignDetail0() == null ? "" : ui.getSignDetail0();
		signDetail[1] = ui.getSignDetail1() == null ? "" : ui.getSignDetail1();
		signDetail[2] = ui.getSignDetail2() == null ? "" : ui.getSignDetail2();
		us.setSignDetail(signDetail);
		us.setUserName(ui.getUserName());
		us.setLastActiveTime(System.currentTimeMillis());

		Map[] permissionMap = this.getUserPermission(ui);

		us.setUserPermissionArray(permissionMap);

		return us;
	}

	public UserInfo findUserInfoByUserNo(String userno) {
		return this.getUserInfoDAO().findUserInfoByUserNo(userno);
	}
	
	public List getUserinfo(){
		return this.getUserInfoDAO().findUserInfo();
	}

}
