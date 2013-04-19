package com.laoer.bbscs.dao.hibernate;

import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.comm.Constant;
//import com.laoer.bbscs.comm.HQLCondition;
import com.laoer.bbscs.comm.OrderObj;
import com.laoer.bbscs.dao.ForumDAO;

public class ForumHibernateDAO extends HibernateDaoSupport implements ForumDAO {

	private String flag = "Main";

	private static String [] pic = {"jpg","jpeg","gif","png"};
	
	public ForumHibernateDAO(String flag) {
		super();
		this.flag = flag;
	}

	public Forum findForumByID(String id) {
		return (Forum) this.getHibernateTemplate().get(this.getForumClass(), id);
	}

	public Forum findForumByID(String id, long bid) {
		Object[] o = { id, new Long(bid) };
		String sql = "from " + getObjName() + " where id = ? and boardID = ?";
		List l = this.getHibernateTemplate().find(sql, o);
		if (l == null || l.isEmpty()) {
			return null;
		} else {
			return (Forum) l.get(0);
		}
	}

	public List findForums(final long bid, final int isNew, final int delSign, final int auditing, final OrderObj[] oo) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				if (bid != -1) {
					c.add(Restrictions.eq("boardID", new Long(bid)));
				}
				if (isNew != -1) {
					c.add(Restrictions.eq("isNew", new Integer(isNew)));
				}
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
				}
				if (oo != null && oo.length > 0) {
					for (int i = 0; i < oo.length; i++) {
						if (StringUtils.isNotBlank(oo[i].getOrderBy())) {
							if (oo[i].getAscOrDesc() == Constant.ORDER_ASC) {
								c.addOrder(Order.asc(oo[i].getOrderBy()));
							}
							if (oo[i].getAscOrDesc() == Constant.ORDER_DESC) {
								c.addOrder(Order.desc(oo[i].getOrderBy()));
							}
						}
					}
				}
				return c.list();
			}
		});
	}

	public List findForums(final long bid, final int isNew, final int delSign, final int auditing,
			final int auditingAttachFile, final OrderObj[] oo, final int firstResult, final int maxResults) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				if (bid != -1) {
					c.add(Restrictions.eq("boardID", new Long(bid)));
				}
				if (isNew != -1) {
					c.add(Restrictions.eq("isNew", new Integer(isNew)));
				}
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
				}
				if (auditingAttachFile != -1) {
					c.add(Restrictions.eq("auditingAttachFile", new Integer(auditingAttachFile)));
				}

				if (oo != null && oo.length > 0) {
					for (int i = 0; i < oo.length; i++) {
						if (StringUtils.isNotBlank(oo[i].getOrderBy())) {
							if (oo[i].getAscOrDesc() == Constant.ORDER_ASC) {
								c.addOrder(Order.asc(oo[i].getOrderBy()));
							}
							if (oo[i].getAscOrDesc() == Constant.ORDER_DESC) {
								c.addOrder(Order.desc(oo[i].getOrderBy()));
							}
						}
					}
				}

				c.setFirstResult(firstResult);
				c.setMaxResults(maxResults);
				return c.list();
			}
		});

	}
	
	public List findForumsByAttachFile(final long bid, final int isNew, final int delSign, final int auditing,
			final int auditingAttachFile,final int haveAttachFile, final OrderObj[] oo, final int firstResult, final int maxResults) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				if (bid != -1) {
					c.add(Restrictions.eq("boardID", new Long(bid)));
				}
				if (isNew != -1) {
					c.add(Restrictions.eq("isNew", new Integer(isNew)));
				}
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
				}
				if (auditingAttachFile != -1) {
					c.add(Restrictions.eq("auditingAttachFile", new Integer(auditingAttachFile)));
				}
				if (haveAttachFile != -1) {
					c.add(Restrictions.eq("haveAttachFile", new Integer(haveAttachFile)));
				}
				c.add(Restrictions.sqlRestriction("SUBSTR(AttachFileName FROM 53 FOR 4)IN('jpg','jpeg','gif','png')"));
				if (oo != null && oo.length > 0) {
					for (int i = 0; i < oo.length; i++) {
						if (StringUtils.isNotBlank(oo[i].getOrderBy())) {
							if (oo[i].getAscOrDesc() == Constant.ORDER_ASC) {
								c.addOrder(Order.asc(oo[i].getOrderBy()));
							}
							if (oo[i].getAscOrDesc() == Constant.ORDER_DESC) {
								c.addOrder(Order.desc(oo[i].getOrderBy()));
							}
						}
					}
				}

				c.setFirstResult(firstResult);
				c.setMaxResults(maxResults);
				return c.list();
			}
		});

	}
	

	public List findForums(final long bid, final String tagId, final int isNew, final int delSign, final int auditing,
			final OrderObj[] oo, final int firstResult, final int maxResults) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				if (bid != -1) {
					c.add(Restrictions.eq("boardID", new Long(bid)));
				}
				if (StringUtils.isNotBlank(tagId) && !tagId.equals("0")) {
					c.add(Restrictions.eq("tagID", tagId));
				}
				if (isNew != -1) {
					c.add(Restrictions.eq("isNew", new Integer(isNew)));
				}
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
				}
				if (oo != null && oo.length > 0) {
					for (int i = 0; i < oo.length; i++) {
						if (StringUtils.isNotBlank(oo[i].getOrderBy())) {
							if (oo[i].getAscOrDesc() == Constant.ORDER_ASC) {
								c.addOrder(Order.asc(oo[i].getOrderBy()));
							}
							if (oo[i].getAscOrDesc() == Constant.ORDER_DESC) {
								c.addOrder(Order.desc(oo[i].getOrderBy()));
							}
						}
					}
				}

				c.setFirstResult(firstResult);
				c.setMaxResults(maxResults);
				return c.list();
			}
		});

	}

	public List findForumsByIndexStatus(int indexStatus) {
		String sql = "from " + this.getObjName() + " where indexStatus = ?";
		return this.getHibernateTemplate().find(sql, new Integer(indexStatus));
	}

	public List findForumsCommend(final long commend, final int isNew, final int delSign, final int auditing,
			final String orderby, final int ascOrDesc, final int firstResult, final int maxResults) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				if (commend != -1) {
					c.add(Restrictions.eq("commend", new Long(commend)));
				}
				if (isNew != -1) {
					c.add(Restrictions.eq("isNew", new Integer(isNew)));
				}
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
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

	public List findForumsElite(long bid, long elite, long eliteId) {
		Object[] o = { new Long(bid), new Long(elite), new Long(eliteId), new Integer(0) };
		String sql = "from " + this.getObjName() + " where boardID = ? and elite = ? and eliteID = ? and delSign = ?";
		return this.getHibernateTemplate().find(sql, o);
	}

	public List findForumsHotTopic(final long bid, final int reNum, final int click, final int firstResult,
			final int maxResults) {
		final String sql = "from "
				+ this.getObjName()
				+ " where boardID = ? and isNew = ? and delSign = ? and auditing = ? and (reNum >= ? or click >= ?) order by postTime desc";
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				Query query = s.createQuery(sql);
				query.setLong(0, bid);
				query.setInteger(1, 1);
				query.setInteger(2, 0);
				query.setInteger(3, 0);
				query.setInteger(4, reNum);
				query.setInteger(5, click);
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);

				List list = query.list();
				return list;
			}
		});
	}

	public List findForumsInIds(final long bid, final List ids) {
		final String sql = "from " + this.getObjName() + " where id in (:ids) and boardID = :bid";
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				Query query = s.createQuery(sql);
				query.setParameterList("ids", ids);
				query.setLong("bid", bid);
				List list = query.list();
				return list;
			}
		});
	}

	public List findForumsInIds(final List ids) {
		final String sql = "from " + this.getObjName() + " where id in (:ids)";
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				Query query = s.createQuery(sql);
				query.setParameterList("ids", ids);
				List list = query.list();
				return list;
			}
		});
	}

	public List findForumsInMainIDByUserID(final long bid, final String mainID, final String userID, final int isnew,
			final int delSign, final int auditing, final String orderby, final int ascOrDesc, final int firstResult,
			final int maxResults) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(Forum.class);
				c.add(Restrictions.eq("mainID", mainID));
				c.add(Restrictions.eq("userID", userID));
				c.add(Restrictions.eq("boardID", new Long(bid)));
				if (isnew != -1) {
					c.add(Restrictions.eq("isNew", new Integer(isnew)));
				}
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
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

	public List findForumsOwner(final String userID, final int isNew, final int delSign, final int auditing,
			final String orderby, final int ascOrDesc, final int firstResult, final int maxResults) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				c.add(Restrictions.eq("userID", userID));
				if (isNew != -1) {
					c.add(Restrictions.eq("isNew", new Integer(isNew)));
				}
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
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

	public List findForumsRealDelAll(long bid, long atime) {
		Object[] o = { new Long(bid), new Long(atime) };
		String sql = "from " + this.getObjName() + " where boardID = ? and delSign = 1 and delTime < ?";
		return this.getHibernateTemplate().find(sql, o);
	}

	public List findForumsToHistory(long atime) {
		String sql = "from ForumMain where isNew = 1 and elite = 0 and lastTime <= ?";
		return this.getHibernateTemplate().find(sql, new Long(atime));
	}

	public List findForumsTopic(final long bid, final String mainID, final int delSign, final int auditing,
			final int auditingAttachFile, final OrderObj[] oo) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				c.add(Restrictions.eq("mainID", mainID));
				c.add(Restrictions.eq("boardID", new Long(bid)));
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
				}
				if (auditingAttachFile != -1) {
					c.add(Restrictions.eq("auditingAttachFile", new Integer(auditingAttachFile)));
				}

				if (oo != null && oo.length > 0) {
					for (int i = 0; i < oo.length; i++) {
						if (StringUtils.isNotBlank(oo[i].getOrderBy())) {
							if (oo[i].getAscOrDesc() == Constant.ORDER_ASC) {
								c.addOrder(Order.asc(oo[i].getOrderBy()));
							}
							if (oo[i].getAscOrDesc() == Constant.ORDER_DESC) {
								c.addOrder(Order.desc(oo[i].getOrderBy()));
							}
						}
					}
				}
				return c.list();
			}
		});
	}

	public List findForumsTopic(final long bid, final String mainID, final int delSign, final int auditing,
			final OrderObj[] oo, final int firstResult, final int maxResults) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				c.add(Restrictions.eq("mainID", mainID));
				c.add(Restrictions.eq("boardID", new Long(bid)));
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
				}
				if (oo != null && oo.length > 0) {
					for (int i = 0; i < oo.length; i++) {
						if (StringUtils.isNotBlank(oo[i].getOrderBy())) {
							if (oo[i].getAscOrDesc() == Constant.ORDER_ASC) {
								c.addOrder(Order.asc(oo[i].getOrderBy()));
							}
							if (oo[i].getAscOrDesc() == Constant.ORDER_DESC) {
								c.addOrder(Order.desc(oo[i].getOrderBy()));
							}
						}
					}
				}
				c.setFirstResult(firstResult);
				c.setMaxResults(maxResults);
				return c.list();
			}
		});
	}

	public long getForumNum(final long bid, final int isNew, final int delSign, final int auditing,
			final int auditingAttachFile) {
		List l = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				c.setProjection(Projections.count("id"));
				if (bid != -1) {
					c.add(Restrictions.eq("boardID", new Long(bid)));
				}
				if (isNew != -1) {
					c.add(Restrictions.eq("isNew", new Integer(isNew)));
				}
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
				}
				if (auditingAttachFile != -1) {
					c.add(Restrictions.eq("auditingAttachFile", new Integer(auditingAttachFile)));
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

	public long getForumNum(final long bid, final String tagId, final int isNew, final int delSign, final int auditing) {
		List l = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				c.setProjection(Projections.count("id"));
				if (bid != -1) {
					c.add(Restrictions.eq("boardID", new Long(bid)));
				}
				if (StringUtils.isNotBlank(tagId)) {
					c.add(Restrictions.eq("tagID", tagId));
				}
				if (isNew != -1) {
					c.add(Restrictions.eq("isNew", new Integer(isNew)));
				}
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
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

	public long getForumNumCommend(final long commend, final int isNew, final int delSign, final int auditing) {
		List l = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				c.setProjection(Projections.count("id"));

				if (commend != -1) {
					c.add(Restrictions.eq("commend", new Long(commend)));
				}
				if (isNew != -1) {
					c.add(Restrictions.eq("isNew", new Integer(isNew)));
				}
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
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

	public long getForumNumHotTopic(long bid, int reNum, int click) {
		Object[] o = { new Long(bid), new Integer(1), new Integer(0), new Integer(0), new Integer(reNum),
				new Integer(click) };
		String sql = "select count(id) from " + this.getObjName()
				+ " where boardID = ? and isNew = ? and delSign = ? and auditing = ? and (reNum >= ? or click >= ?)";
		List l = this.getHibernateTemplate().find(sql, o);
		if (l == null || l.isEmpty()) {
			return 0;
		} else {
			return ((Long) l.get(0)).longValue();
		}
	}

	public long getForumNumInMainIDByUserID(final long bid, final String mainID, final String userID, final int isnew,
			final int delSign, final int auditing) {
		List l = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				c.setProjection(Projections.count("id"));
				c.add(Restrictions.eq("boardID", new Long(bid)));

				c.add(Restrictions.eq("mainID", mainID));
				c.add(Restrictions.eq("userID", userID));
				if (isnew != -1) {
					c.add(Restrictions.eq("isNew", new Integer(isnew)));
				}
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
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

	public long getForumOwnerNum(final String userID, final int isNew, final int delSign, final int auditing) {
		List l = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				c.setProjection(Projections.count("id"));
				c.add(Restrictions.eq("userID", userID));
				if (isNew != -1) {
					c.add(Restrictions.eq("isNew", new Integer(isNew)));
				}
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
				}
				return c.list();
			}
		});
		if (l == null || l.isEmpty()) {
			return 0;
		} else {
			return ((Long) l.get(0)).longValue();
		}
	}

	public long getForumTopicNum(final long bid, final String mainID, final int delSign, final int auditing) {
		List l = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				c.setProjection(Projections.count("id"));
				c.add(Restrictions.eq("boardID", new Long(bid)));
				c.add(Restrictions.eq("mainID", mainID));
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
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

	public List getSearchList(final long bid, final String con, final String text, final int delSign,
			final int auditing, final String orderby, final int ascOrDesc, final int firstResult, final int maxResults) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				c.add(Restrictions.eq("boardID", new Long(bid)));
				c.add(Restrictions.like(con, "%" + text + "%"));
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
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

	public long getSearchNum(final long bid, final String con, final String text, final int delSign, final int auditing) {
		List l = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				c.setProjection(Projections.count("id"));
				c.add(Restrictions.eq("boardID", new Long(bid)));
				c.add(Restrictions.like(con, "%" + text + "%"));
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
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

	public void removeForum(final String id, final long bid) {
		final String sql = "delete from " + this.getObjName() + " where id = ? and boardID = ?";
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				Query query = s.createQuery(sql);
				query.setString(0, id);
				query.setLong(1, bid);
				query.executeUpdate();
				return null;
			}
		});
	}

	public void removeForum(final String id) {
		final String sql = "delete from " + this.getObjName() + " where id = ?";
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				Query query = s.createQuery(sql);
				query.setString(0, id);
				query.executeUpdate();
				return null;
			}
		});
	}

	public void removeForum(Forum forum) {
		this.getHibernateTemplate().delete(forum);
	}

	public Forum saveForum(Forum forum) {
		this.getHibernateTemplate().save(forum);
		return forum;
	}

	public Forum saveOrUpdateForum(Forum forum) {
		this.getHibernateTemplate().saveOrUpdate(forum);
		return forum;
	}

	public Forum updateForum(Forum forum) {
		this.getHibernateTemplate().update(forum);
		return forum;
	}

	public void updateForumsTag(final String oldTagId, final String newTagID, final String newTagName) {
		final String sql = "update " + this.getObjName() + " set tagID = ?, tagName = ? where tagID = ?";
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				Query query = s.createQuery(sql);
				query.setString(0, newTagID);
				query.setString(1, newTagName);
				query.setString(2, oldTagId);
				query.executeUpdate();
				return null;
			}
		});
	}

	public long getForumNumCommend(final long bid, final long commend, final int isNew, final int delSign,
			final int auditing) {
		List l = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				c.setProjection(Projections.count("id"));
				c.add(Restrictions.eq("boardID", new Long(bid)));
				if (commend != -1) {
					c.add(Restrictions.eq("commend", new Long(commend)));
				}
				if (isNew != -1) {
					c.add(Restrictions.eq("isNew", new Integer(isNew)));
				}
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
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

	public List findForumsCommend(final long bid, final long commend, final int isNew, final int delSign,
			final int auditing, final String orderby, final int ascOrDesc, final int firstResult, final int maxResults) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				c.add(Restrictions.eq("boardID", new Long(bid)));
				if (commend != -1) {
					c.add(Restrictions.eq("commend", new Long(commend)));
				}
				if (isNew != -1) {
					c.add(Restrictions.eq("isNew", new Integer(isNew)));
				}
				if (delSign != -1) {
					c.add(Restrictions.eq("delSign", new Integer(delSign)));
				}
				if (auditing != -1) {
					c.add(Restrictions.eq("auditing", new Integer(auditing)));
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

	public long getForumNumBeforeDate(long bid, long atime) {
		String sql = "select count(*) from " + this.getObjName() + " where boardID = ? and isNew = 1 and postTime < ?";
		Object[] o = { new Long(bid), new Long(atime) };
		List l = this.getHibernateTemplate().find(sql, o);
		if (l == null || l.isEmpty()) {
			return 0;
		} else {
			return ((Long) l.get(0)).longValue();
		}

	}

	public List findForumsBeforeDate(final long bid, final long atime, final int firstResult, final int maxResults) {
		final String sql = "from " + this.getObjName()
				+ " where boardID = ? and isNew = 1 and postTime < ? order by postTime desc";
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				Query query = s.createQuery(sql);
				query.setLong(0, bid);
				query.setLong(1, atime);
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);

				List list = query.list();
				return list;
			}
		});
	}

	private String getObjName() {
		return "Forum" + this.flag;
	}

	private Class getForumClass() {
		return Constant.FORUM_CLASS_MAP.get(this.flag);
		/*
		 * try { //return Class.forName("com.laoer.bbscs.bean." + getObjName()); }
		 * catch (ClassNotFoundException e) {
		 *
		 * e.printStackTrace(); return ForumMain.class; // return
		 * com.laoer.bbscs.bean.ForumMain.class; }
		 */
	}

	public List findAllForumsByClick(final int click, final OrderObj[] oo) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(getForumClass());
				if (click != -1) {
					c.add(Restrictions.ge("click", click));
				}
				if (oo != null && oo.length > 0) {
					for (int i = 0; i < oo.length; i++) {
						if (StringUtils.isNotBlank(oo[i].getOrderBy())) {
							if (oo[i].getAscOrDesc() == Constant.ORDER_ASC) {
								c.addOrder(Order.asc(oo[i].getOrderBy()));
							}
							if (oo[i].getAscOrDesc() == Constant.ORDER_DESC) {
								c.addOrder(Order.desc(oo[i].getOrderBy()));
							}
						}
					}
				}
				return c.list();
			}
		});
	}
}
