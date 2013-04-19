/**
 *
 */
package com.laoer.bbscs.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.dao.UserInfoDAO;

import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import org.hibernate.criterion.Order;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Criteria;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.comm.UserInfoUti;
/*import com.meetup.memcached.MemCacheInvoke;
import com.ruyicai.util.ErrorCode;
import com.ruyicai.util.MessageUtil;
import com.ruyicai.util.ResourceBundleUtil;
import com.ruyicai.util.MD5.PaySign;*/

import org.hibernate.criterion.Projections;

/**
 *
 */
public class UserInfoHibernateDAO extends HibernateDaoSupport implements
		UserInfoDAO {

	private static final String LOAD_BY_USERNAME = "from UserInfo where userName = ?";

	private static final String LOAD_BY_ID = "from UserInfo where id = ?";

	private static final String LOAD_BY_EMAIL = "from UserInfo where email = ?";

	private static final String LOAD_ALL_COUNT = "select count(*) from UserInfo";
	
	private static final String LOAD_BY_USERNO = "FROM UserInfo where userNo = ?";
	
	private UserInfoUti userUtil = new UserInfoUti();

	//private static final String GET_NUM_BY_GROUPID = "select count(id) from UserInfo where groupID = ?";

	/*
	 * （非 Javadoc）
	 *
	 * @see com.laoer.bbscs.dao.UserInfoDAO#findUserInfoByEmail(java.lang.String)
	 */
	public UserInfo findUserInfoByEmail(String email) {
		List l = this.getHibernateTemplate().find(LOAD_BY_EMAIL, email);
		if (l == null || l.isEmpty()) {
			return null;
		} else {
			return (UserInfo) l.get(0);
		}
	}

	/*
	 * （非 Javadoc）
	 *
	 * @see com.laoer.bbscs.dao.UserInfoDAO#findUserInfoById(java.lang.String)
	 */
	public UserInfo findUserInfoById(String id) {
		List l = this.getHibernateTemplate().find(LOAD_BY_ID, id);
		if (l == null || l.isEmpty()) {
			return null;
		} else {
			return (UserInfo) l.get(0);
		}
	}

	/*
	 * （非 Javadoc）
	 *
	 * @see com.laoer.bbscs.dao.UserInfoDAO#findUserInfoByUserName(java.lang.String)
	 */
	//查询用户名是否存在
	public UserInfo findUserInfoByUserName(String userName) {
		List l = this.getHibernateTemplate().find(LOAD_BY_USERNAME, userName);
		if (l == null || l.isEmpty()) {
			return null;
		} else {
			return (UserInfo) l.get(0);
		}
		/*try {
			String url = Util.getResultMessage(Util.LINKURL + "/tuserinfoes?","json&find=ByUserName&userName="+userName+"&state=1","POST");
			JSONObject userinfo = JSONObject.fromObject(url).getJSONObject("value");
			if(userinfo != null && !userinfo.isNullObject()){
				 
				//return (UserInfo) JSONObject.toBean(userinfo);
				UserInfo userinfos = userUtil.rycUserTobbsUser(userinfo);
				return userinfos;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}*/
	}

	/*
	 * （非 Javadoc）
	 *
	 * @see com.laoer.bbscs.dao.UserInfoDAO#findUserInfoList(java.lang.String,
	 *      java.lang.String, int, int)
	 */
	public List findUserInfoList(final String orderby, final String ascordesc,
			final int firstResult, final int maxResults) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				String q = "from UserInfo order by " + orderby + " "
						+ ascordesc;
				Query query = s.createQuery(q);
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
				List list = query.list();
				return list;
			}
		});
	}

	/*
	 * （非 Javadoc）
	 *
	 * @see com.laoer.bbscs.dao.UserInfoDAO#findUserInfosByGroupID(int,
	 *      java.lang.String, int, int, int)
	 */
	public List findUserInfosByGroupID(final int groupID, final String orderby,
			final int ascOrDesc, final int firstResult, final int maxResults) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(UserInfo.class);
				if (groupID != 0) {
					c.add(Restrictions.eq("groupID", new Integer(groupID)));
				}

				if (StringUtils.isNotBlank(orderby)) {
					if (ascOrDesc == Constant.ORDER_ASC) {
						c.addOrder(Order.asc(orderby));
					}
					if (ascOrDesc == Constant.ORDER_DESC) {
						c.addOrder(Order.desc(orderby));
					}
				}
				c.setFirstResult(firstResult);
				c.setMaxResults(maxResults);
				return c.list();
			}
		});
	}

	/*
	 * （非 Javadoc）
	 *
	 * @see com.laoer.bbscs.dao.UserInfoDAO#getAllUserNum()
	 */
	public long getAllUserNum() {
		List l = this.getHibernateTemplate().find(LOAD_ALL_COUNT);
		if (l == null || l.isEmpty()) {
			return 0;
		} else {
			return ((Long) l.get(0)).longValue();
		}
	}

	/*
	 * （非 Javadoc）
	 *
	 * @see com.laoer.bbscs.dao.UserInfoDAO#getUserNumByGroupID(int)
	 */
	public long getUserNumByGroupID(final int groupID) {
		List l = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(UserInfo.class);
				c.setProjection(Projections.count("id"));
				if (groupID != 0) {
					c.add(Restrictions.eq("groupID", new Integer(groupID)));
				}
				return c.list();
			}
		});

		if (l == null || l.isEmpty()) {
			return 0;
		} else {
			return ((Integer) l.get(0)).longValue();
		}
	}

	/*
	 * （非 Javadoc）
	 *
	 * @see com.laoer.bbscs.dao.UserInfoDAO#saveUserInfo(com.laoer.bbscs.bean.UserInfo)
	 */
	public UserInfo saveUserInfo(UserInfo userInfo) {
		this.getHibernateTemplate().saveOrUpdate(userInfo);
		return userInfo;
		
	}

	/* (non-Javadoc)
	 * @see com.laoer.bbscs.dao.UserInfoDAO#findUserInfoByUserNo(java.lang.String)
	 */
	@Override
	public UserInfo findUserInfoByUserNo(String userno) {
		List l = this.getHibernateTemplate().find(LOAD_BY_USERNO, userno);
		if (l == null || l.isEmpty()) {
			return null;
		} else {
			return (UserInfo) l.get(0);
			
		}
	}

	@Override
	public List findUserInfo() {
		return this.getHibernateTemplate().find("from UserInfo");
	}

}
