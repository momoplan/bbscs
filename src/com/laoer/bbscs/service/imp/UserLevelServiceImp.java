package com.laoer.bbscs.service.imp;

import java.util.*;

import org.apache.commons.logging.*;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;

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
public class UserLevelServiceImp implements UserLevelService {

	private static final Log logger = LogFactory.getLog(UserLevelServiceImp.class);

	private UserLevelDAO userLevelDAO;

	private Cache sysListObjCache;

	public UserLevelServiceImp() {
	}

	/**
	 *
	 * @param ul
	 *            UserLevel
	 * @return UserLevel
	 * @throws BbscsException
	 * @todo Implement this com.laoer.bbscs.service.UserLevelService method
	 */
	public UserLevel saveUserLevel(UserLevel ul) throws BbscsException {
		try {
			ul = this.getUserLevelDAO().saveUserLevel(ul);
			this.getSysListObjCache().remove("UserLevel-" + ul.getLevelType());
			return ul;
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	/**
	 *
	 * @param id
	 *            String
	 * @return UserLevel
	 * @todo Implement this com.laoer.bbscs.service.UserLevelService method
	 */
	public UserLevel findUserLevelById(String id) {
		return this.getUserLevelDAO().findUserLevelById(id);
	}

	/**
	 *
	 * @param type
	 *            int
	 * @return List
	 * @todo Implement this com.laoer.bbscs.service.UserLevelService method
	 */
	public List findUserLevelsByType(int type) {
		List l = (List) this.getSysListObjCache().get("UserLevel-" + type);
		if (l == null) {
			l = this.getUserLevelDAO().findUserLevelsByType(type);
			this.getSysListObjCache().add("UserLevel-" + type, l);
		}
		return l;
	}

	/**
	 *
	 * @param type
	 *            int
	 * @param value
	 *            int
	 * @return UserLevel
	 * @todo Implement this com.laoer.bbscs.service.UserLevelService method
	 */
	public UserLevel getUserLevelByUserValue(int type, int value) {
		List l = this.getUserLevelDAO().findUserLevelsByType(type);
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

	public void removeUserLevel(UserLevel ul) throws BbscsException {
		try {
			this.getSysListObjCache().remove("UserLevel-" + ul.getLevelType());
			this.getUserLevelDAO().removeUserLevel(ul);
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public UserLevelDAO getUserLevelDAO() {
		return userLevelDAO;
	}

	public void setUserLevelDAO(UserLevelDAO userLevelDAO) {
		this.userLevelDAO = userLevelDAO;
	}

	public Cache getSysListObjCache() {
		return sysListObjCache;
	}

	public void setSysListObjCache(Cache sysListObjCache) {
		this.sysListObjCache = sysListObjCache;
	}

}
