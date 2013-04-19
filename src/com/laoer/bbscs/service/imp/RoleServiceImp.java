package com.laoer.bbscs.service.imp;

import java.util.*;

import org.apache.commons.logging.*;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.comm.Constant;

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
public class RoleServiceImp implements RoleService {

	private static final Log logger = LogFactory.getLog(RoleServiceImp.class);

	private RoleDAO roleDAO;

	private Cache userPermissionCache;

	public RoleServiceImp() {
	}

	/**
	 * 保存或更新Role对象
	 *
	 * @param role
	 *            Role
	 * @return Role
	 * @throws BbscsException
	 * @todo Implement this com.laoer.bbscs.service.RoleService method
	 */
	public Role saveRole(Role role) throws BbscsException {
		try {
			role = this.getRoleDAO().saveRole(role);
			this.clearPermissionCache();
			return role;
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	/**
	 * 根据ID取得Role
	 *
	 * @param id
	 *            int
	 * @return Role
	 * @todo Implement this com.laoer.bbscs.service.RoleService method
	 */
	public Role findRoleByID(int id) {
		return this.getRoleDAO().findRoleByID(id);
	}

	/**
	 * 取得所有Role列表
	 *
	 * @return List
	 * @todo Implement this com.laoer.bbscs.service.RoleService method
	 */
	public List findRolesAll() {
		return this.getRoleDAO().findRolesAll();
	}

	/**
	 * 根据tpyeID取得Role列表
	 *
	 * @param typeID
	 *            int
	 * @return List
	 * @todo Implement this com.laoer.bbscs.service.RoleService method
	 */
	public List findRolesByTypeID(int typeID) {
		return this.getRoleDAO().findRolesByTypeID(typeID);
	}

	/**
	 * 根据指定ID取得Role对象列表
	 *
	 * @param ids
	 *            List
	 * @return List
	 */
	public List findRolesInIDs(List ids) {
		return this.getRoleDAO().findRolesInIDs(ids);
	}

	/**
	 * 删除Role对象
	 *
	 * @param role
	 *            Role
	 * @throws BbscsException
	 * @todo Implement this com.laoer.bbscs.service.RoleService method
	 */
	public void removeRole(Role role) throws BbscsException {
		try {
			this.getRoleDAO().removeRole(role);
			this.clearPermissionCache();
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	private void clearPermissionCache() {
		if (Constant.USE_PERMISSION_CACHE) {
			this.getUserPermissionCache().removeAll();
		}
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public Cache getUserPermissionCache() {
		return userPermissionCache;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void setUserPermissionCache(Cache userPermissionCache) {
		this.userPermissionCache = userPermissionCache;
	}
}
