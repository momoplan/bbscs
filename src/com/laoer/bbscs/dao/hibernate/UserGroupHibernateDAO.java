package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.UserGroupDAO;
import com.laoer.bbscs.bean.UserGroup;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.sql.SQLException;

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
public class UserGroupHibernateDAO extends HibernateDaoSupport implements UserGroupDAO {

	private static final String LOADS_ALL = "from UserGroup order by id";

	private static final String LOADS_IN_IDS = "from UserGroup where id in (:ids)";

	public UserGroupHibernateDAO() {
		super();
	}

	/**
	 * 保存或更新UserGroup对象
	 *
	 * @param ug
	 *            UserGroup
	 * @return UserGroup
	 * @todo Implement this com.laoer.bbscs.dao.UserGroupDAO method
	 */
	public UserGroup saveUserGroup(UserGroup ug) {
		this.getHibernateTemplate().save(ug);
		return ug;
	}

	public UserGroup updateUserGroup(UserGroup ug) {
		this.getHibernateTemplate().update(ug);
		return ug;
	}

	/**
	 * 根据ID取得UserGroup对象
	 *
	 * @param id
	 *            int
	 * @return UserGroup
	 * @todo Implement this com.laoer.bbscs.dao.UserGroupDAO method
	 */
	public UserGroup findUserGroupByID(int id) {
		return (UserGroup) this.getHibernateTemplate().get(UserGroup.class, new Integer(id));
	}

	/**
	 * 取得所有UserGroup列表
	 *
	 * @return List
	 * @todo Implement this com.laoer.bbscs.dao.UserGroupDAO method
	 */
	public List findUserGroupsAll() {
		return this.getHibernateTemplate().find(LOADS_ALL);
	}

	/**
	 * 取得指定ID的UserGroup列表
	 *
	 * @param ids
	 *            List
	 * @return List
	 */
	public List findUserGroupInIDs(final List ids) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				Query query = s.createQuery(LOADS_IN_IDS);
				query.setParameterList("ids", ids);
				List list = query.list();
				return list;
			}
		});
	}

	/**
	 * 删除UserGroup对象
	 *
	 * @param ug
	 *            UserGroup
	 * @todo Implement this com.laoer.bbscs.dao.UserGroupDAO method
	 */
	public void removeUserGroup(UserGroup ug) {
		this.getHibernateTemplate().delete(ug);
	}
}
