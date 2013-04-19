package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.BoardDAO;
import com.laoer.bbscs.bean.Board;
import java.util.*;

import com.laoer.bbscs.comm.Constant;

import org.hibernate.Criteria;
//import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

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
public class BoardHibernateDAO extends HibernateDaoSupport implements BoardDAO {

	// private static final String LOADS_BY_PARENTID = "from Board where
	// parentID = ?";

	private static final String LOADS_BY_PARENTID_BY_ORDER = "from Board where parentID = ? order by orders";

	private static final String LOADS_ALL = "from Board";

	public static final String[] FIND_BOARDS = new String[3];

	public static final String LOAD_NEXT_ORDER = "select max(orders) from Board where parentID = ?";

	private static final String LOAD_IDS_IN_USE = "select id from Board where parentID = ? and useStat = 1";

	public BoardHibernateDAO() {
		super();
	}

	/**
	 * 保存或更新Board对象
	 *
	 * @param board
	 *            Board
	 * @return Board
	 * @todo Implement this com.laoer.bbscs.dao.BoardDAO method
	 */
	public Board saveBoard(Board board) {
		this.getHibernateTemplate().saveOrUpdate(board);
		return board;
	}

	/**
	 * 根据ID取得Board对象
	 *
	 * @param id
	 *            long
	 * @return Board
	 * @todo Implement this com.laoer.bbscs.dao.BoardDAO method
	 */
	public Board getBoardByID(long id) {
		return (Board) this.getHibernateTemplate().get(Board.class, new Long(id));
	}

	/**
	 * 根据parentID取得Board对象列表
	 *
	 * @param pid
	 *            long
	 * @return List
	 * @todo Implement this com.laoer.bbscs.dao.BoardDAO method
	 */
	public List findBoardsByParentID(long pid) {
		return this.getHibernateTemplate().find(LOADS_BY_PARENTID_BY_ORDER, new Long(pid));
	}

	/**
	 * 根据parentID取得Board对象列表
	 *
	 * @param pid
	 *            long
	 * @param useStat
	 *            int
	 * @param hidden
	 *            int
	 * @param orderType
	 *            int
	 * @return List
	 * @todo Implement this com.laoer.bbscs.dao.BoardDAO method
	 */
	public List findBoardsByParentID(final long pid, final int useStat, final int hidden, final int orderType) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(Board.class);
				c.add(Restrictions.eq("parentID", new Long(pid)));
				if (useStat != -1) {
					c.add(Restrictions.eq("useStat", new Integer(useStat)));
				}
				if (hidden != -1) {
					c.add(Restrictions.eq("isHidden", new Integer(hidden)));
				}
				if (orderType != -1) {
					if (orderType == Constant.FIND_BOARDS_BY_ORDER) {
						c.addOrder(Order.asc("orders"));
					}
					if (orderType == Constant.FIND_BOARDS_BY_MAINPOSTNUM) {
						c.addOrder(Order.desc("mainPostNum"));
					}
					if (orderType == Constant.FIND_BOARDS_BY_POSTNUM) {
						c.addOrder(Order.desc("postNum"));
					}
				}
				//c.setFetchMode("boardMaster", FetchMode.JOIN);

				return c.list();
			}
		});
	}

	public List findBoardIdsByParentID(final long pid, final int useStat, final int hidden, final int orderType) {
		StringBuffer sb = new StringBuffer();
		sb.append("select id from Board where parentID = ?");
		if (useStat != -1) {
			sb.append(" and useStat = ");
			sb.append(useStat);
		}
		if (hidden != -1) {
			sb.append(" and isHidden = ");
			sb.append(hidden);
		}
		if (orderType != -1) {
			sb.append(" order by ");
			if (orderType == Constant.FIND_BOARDS_BY_ORDER) {
				sb.append(" orders asc");
			}
			if (orderType == Constant.FIND_BOARDS_BY_MAINPOSTNUM) {
				sb.append(" mainPostNum desc");
			}
			if (orderType == Constant.FIND_BOARDS_BY_POSTNUM) {
				sb.append(" postNum desc");
			}
		}

		return this.getHibernateTemplate().find(sb.toString(), new Long(pid));
		/*
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(Board.class);
				c.createCriteria("id");
				c.add(Restrictions.eq("parentID", new Long(pid)));
				if (useStat != -1) {
					c.add(Restrictions.eq("useStat", new Integer(useStat)));
				}
				if (hidden != -1) {
					c.add(Restrictions.eq("isHidden", new Integer(hidden)));
				}
				if (orderType != -1) {
					if (orderType == Constant.FIND_BOARDS_BY_ORDER) {
						c.addOrder(Order.asc("orders"));
					}
					if (orderType == Constant.FIND_BOARDS_BY_MAINPOSTNUM) {
						c.addOrder(Order.desc("mainPostNum"));
					}
					if (orderType == Constant.FIND_BOARDS_BY_POSTNUM) {
						c.addOrder(Order.desc("postNum"));
					}
				}

				return c.list();
			}
		});*/
	}

	/**
	 *
	 * @return List
	 */
	public List findBoardsAll() {
		return this.getHibernateTemplate().find(LOADS_ALL);
	}

	/**
	 * 根据parentID预取得Board序列
	 *
	 * @param pid
	 *            long
	 * @return int
	 * @todo Implement this com.laoer.bbscs.dao.BoardDAO method
	 */
	public int getNextOrder(long pid) {
		List l = getHibernateTemplate().find(LOAD_NEXT_ORDER, new Long(pid));
		if (l != null && !l.isEmpty()) {
			if (l.get(0) == null) {
				return 5;
			} else {
				return ((Integer) l.get(0)).intValue() + 5;
			}
		} else {
			return 5;
		}
	}

	/**
	 * 取得帖子总数（主帖或全部）
	 *
	 * @param mainorall
	 *            int
	 * @param useStat
	 *            int
	 * @param hidden
	 *            int
	 * @return int
	 * @todo Implement this com.laoer.bbscs.dao.BoardDAO method
	 */
	public int getPostSumNum(final int mainorall, final int useStat, final int hidden) {
		int sum = 0;

		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(Board.class);
				if (mainorall == 0) {
					c.setProjection(Projections.projectionList().add(Projections.sum("mainPostNum")));
				}
				if (mainorall == 1) {
					c.setProjection(Projections.projectionList().add(Projections.sum("postNum")));
				}
				if (useStat != -1) {
					c.add(Restrictions.eq("useStat", new Integer(useStat)));
				}
				if (hidden != -1) {
					c.add(Restrictions.eq("isHidden", new Integer(hidden)));
				}
				return c.list();
			}
		});
		if (!list.isEmpty()) {
			Object obj = (Object) list.get(0);
			if (obj != null) {
				sum = ((Integer) obj).intValue();
			}
		}
		return sum;
	}

	/**
	 * 删除Board对象
	 *
	 * @param board
	 *            Board
	 * @todo Implement this com.laoer.bbscs.dao.BoardDAO method
	 */
	public void removeBoard(Board board) {
		this.getHibernateTemplate().delete(board);
	}

	public List findBoardsInIDs(final List ids, final int useStat, final int hidden) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(Board.class);
				if (ids == null) {
					List idss = new ArrayList();
					idss.add(new Long(0));
					c.add(Restrictions.in("id", idss));
				} else if (ids.isEmpty()) {
					ids.add(new Long(0));
					c.add(Restrictions.in("id", ids));
				} else {
					c.add(Restrictions.in("id", ids));
				}
				if (useStat != -1) {
					c.add(Restrictions.eq("useStat", new Integer(useStat)));
				}
				if (hidden != -1) {
					c.add(Restrictions.eq("isHidden", new Integer(hidden)));
				}
				c.addOrder(Order.asc("orders"));
				//c.setFetchMode("boardMaster", FetchMode.JOIN);
				return c.list();
			}
		});
	}

	public List findBoardsNeedCount(final int useStat, final int hidden) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(Board.class);

				c.add(Restrictions.or(Restrictions.eq("boardType", new Integer(3)), Restrictions.eq("boardType",
						new Integer(4))));

				if (useStat != -1) {
					c.add(Restrictions.eq("useStat", new Integer(useStat)));
				}
				if (hidden != -1) {
					c.add(Restrictions.eq("isHidden", new Integer(hidden)));
				}
				c.addOrder(Order.desc("mainPostNum"));
				c.addOrder(Order.desc("postNum"));
				return c.list();
			}
		});
	}

	public List findBoardsByParentID(final long pid, final int useStat, final int hidden) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException {
				Criteria c = s.createCriteria(Board.class);
				c.add(Restrictions.eq("parentID", new Long(pid)));
				if (useStat != -1) {
					c.add(Restrictions.eq("useStat", new Integer(useStat)));
				}
				if (hidden != -1) {
					c.add(Restrictions.eq("isHidden", new Integer(hidden)));
				}
				//c.setFetchMode("boardMaster", FetchMode.JOIN);
				return c.list();
			}
		});
	}

	public List findBoardsIdByParentIDInUse(long pid) {
		return this.getHibernateTemplate().find(LOAD_IDS_IN_USE, new Long(pid));
	}
}
