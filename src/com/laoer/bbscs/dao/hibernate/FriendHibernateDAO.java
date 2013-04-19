package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.FriendDAO;
import com.laoer.bbscs.bean.Friend;
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
public class FriendHibernateDAO extends HibernateDaoSupport implements FriendDAO {

	private static final String LOAD_BY_ID = "from Friend where id = ? and userID = ?";

	private static final String LOAD_BY_NAME = "from Friend where userID = ? and friendName = ?";

	private static final String GET_FRIEND_NUM = "select count(*) from Friend where userID = ? and isBlack = ?";

	private static final String GET_FRIEND_NUM_ALL = "select count(*) from Friend where userID = ?";

	private static final String LOADS_OWN_FRIEND = "from Friend where userID = ? and isBlack = ?";

	private static final String LOADS_OWN_FRIEND_ID = "select id from Friend where userID = ? and isBlack = ?";

	private static final String REMOVE_FRIEND = "delete from Friend where id = ? and userID = ?";

	public FriendHibernateDAO() {
	}

	/**
	 * 保存或更新Friend对象
	 *
	 * @param f
	 *            Friend
	 * @return Friend
	 * @todo Implement this com.laoer.bbscs.dao.FriendDAO method
	 */
	public Friend saveFriend(Friend f) {
		this.getHibernateTemplate().saveOrUpdate(f);
		return f;
	}

	/**
	 * 根据ID取得Friend对象
	 *
	 * @param id
	 *            String
	 * @param ownId
	 *            String
	 * @return Friend
	 */
	public Friend findFriendByID(String id, String ownId) {
		Object[] o = { id, ownId };
		List l = this.getHibernateTemplate().find(LOAD_BY_ID, o);
		if (l == null || l.isEmpty()) {
			return null;
		} else {
			return (Friend) l.get(0);
		}
	}

	/**
	 * 根据朋友名、自身ID取得Friend对象
	 *
	 * @param fname
	 *            String
	 * @param ownId
	 *            String
	 * @return Friend
	 * @todo Implement this com.laoer.bbscs.dao.FriendDAO method
	 */
	public Friend findFriendByName(String fname, String ownId) {
		Object[] o = { ownId, fname };
		List l = this.getHibernateTemplate().find(LOAD_BY_NAME, o);
		if (l == null || l.isEmpty()) {
			return null;
		} else {
			return (Friend) l.get(0);
		}
	}

	/**
	 * 取得朋友数量
	 *
	 * @param ownId
	 *            String
	 * @param isBlack
	 *            int
	 * @return int
	 * @todo Implement this com.laoer.bbscs.dao.FriendDAO method
	 */
	public long getFriendNum(String ownId, int isBlack) {
		if (isBlack != -1) {
			Object[] o = { ownId, new Integer(isBlack) };
			List l = this.getHibernateTemplate().find(GET_FRIEND_NUM, o);
			if (l == null || l.isEmpty()) {
				return 0;
			} else {
				return ((Long) l.get(0)).longValue();
			}
		} else {
			Object[] o = { ownId };
			List l = this.getHibernateTemplate().find(GET_FRIEND_NUM_ALL, o);
			if (l == null || l.isEmpty()) {
				return 0;
			} else {
				return ((Long) l.get(0)).longValue();
			}
		}
	}

	/**
	 * 取得朋友列表
	 *
	 * @param ownId
	 *            String
	 * @param isBlack
	 *            int
	 * @return List
	 * @todo Implement this com.laoer.bbscs.dao.FriendDAO method
	 */
	public List findFriends(String ownId, int isBlack) {
		Object[] o = { ownId, new Integer(isBlack) };
		return this.getHibernateTemplate().find(LOADS_OWN_FRIEND, o);
	}

	/**
	 * 删除Friend对象
	 *
	 * @param f
	 *            Friend
	 * @todo Implement this com.laoer.bbscs.dao.FriendDAO method
	 */
	public void removeFriend(Friend f) {
		this.getHibernateTemplate().delete(f);
	}

	/**
	 * 删除Friend对象
	 *
	 * @param id
	 *            String
	 * @param ownId
	 *            String
	 * @todo Implement this com.laoer.bbscs.dao.FriendDAO method
	 */
	public void removeFriend(final String id, final String ownId) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				Query query = s.createQuery(REMOVE_FRIEND);
				query.setString(0, id);
				query.setString(1, ownId);
				query.executeUpdate();
				return null;
			}
		});
	}

	public List findFriendIds(String ownId, int isBlack) {
		Object[] o = { ownId, new Integer(isBlack) };
		return this.getHibernateTemplate().find(LOADS_OWN_FRIEND_ID, o);
	}
}
