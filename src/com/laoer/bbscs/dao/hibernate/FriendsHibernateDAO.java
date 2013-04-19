package com.laoer.bbscs.dao.hibernate;

import java.util.*;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.comm.BBSCSUtil;
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
public class FriendsHibernateDAO extends HibernateDaoSupport implements FriendDAO {

	private int modNum;

	public FriendsHibernateDAO() {
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
	 * @todo Implement this com.laoer.bbscs.dao.FriendDAO method
	 */
	public Friend findFriendByID(String id, String ownId) {
		StringBuffer sb = new StringBuffer();
		sb.append("from Friend");
		sb.append(BBSCSUtil.getTableID(ownId, this.getModNum()));
		sb.append(" where id = ? and userID = ?");
		Object[] o = { id, ownId };
		List l = this.getHibernateTemplate().find(sb.toString(), o);
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
		StringBuffer sb = new StringBuffer();
		sb.append("from Friend");
		sb.append(BBSCSUtil.getTableID(ownId, this.getModNum()));
		sb.append(" where userID = ? and friendName = ?");
		Object[] o = { ownId, fname };
		List l = this.getHibernateTemplate().find(sb.toString(), o);
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
		Object[] o;
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from Friend");
		sb.append(BBSCSUtil.getTableID(ownId, this.getModNum()));
		if (isBlack != -1) {
			sb.append(" where userID = ? and isBlack = ?");
			o = new Object[] { ownId, new Integer(isBlack) };
		} else {
			sb.append(" where userID = ?");
			o = new Object[] { ownId };
		}
		// Object[] o = { ownId, new Integer(isBlack) };
		List l = this.getHibernateTemplate().find(sb.toString(), o);
		if (l == null || l.isEmpty()) {
			return 0;
		} else {
			return ((Long) l.get(0)).longValue();
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
		StringBuffer sb = new StringBuffer();
		sb.append("from Friend");
		sb.append(BBSCSUtil.getTableID(ownId, this.getModNum()));
		sb.append(" where userID = ? and isBlack = ?");
		Object[] o = { ownId, new Integer(isBlack) };
		return this.getHibernateTemplate().find(sb.toString(), o);
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
		final StringBuffer sb = new StringBuffer();
		sb.append("delete from Friend");
		sb.append(BBSCSUtil.getTableID(ownId, this.getModNum()));
		sb.append(" where id = ? and userID = ?");
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				Query query = s.createQuery(sb.toString());
				query.setString(0, id);
				query.setString(1, ownId);
				query.executeUpdate();
				return null;
			}
		});
	}

	public List findFriendIds(String ownId, int isBlack) {
		StringBuffer sb = new StringBuffer();
		sb.append("select id from Friend");
		sb.append(BBSCSUtil.getTableID(ownId, this.getModNum()));
		sb.append(" where userID = ? and isBlack = ?");
		Object[] o = { ownId, new Integer(isBlack) };
		return this.getHibernateTemplate().find(sb.toString(), o);
	}

	public void setModNum(int modNum) {
		this.modNum = modNum;
	}

	public int getModNum() {
		return modNum;
	}
}
