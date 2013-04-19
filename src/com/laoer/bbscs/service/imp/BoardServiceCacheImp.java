package com.laoer.bbscs.service.imp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.BoardService;
import com.laoer.bbscs.service.Cache;
import com.laoer.bbscs.service.SysStatService;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.comm.*;

public class BoardServiceCacheImp implements BoardService {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(BoardServiceCacheImp.class);

	private BoardDAO boardDAO;

	private Cache sysListObjCache;

	private Cache boardCache;

	private UserGroupDAO userGroupDAO;

	private BoardPermissionDAO boardPermissionDAO;

	private PermissionDAO permissionDAO;

	private Cache userPermissionCache;

	private RoleDAO roleDAO;

	private ForumDAO forumDAO;

	private ForumDAO forumHistoryDAO;

	private SysStatService sysStatService;

	public BoardDAO getBoardDAO() {
		return boardDAO;
	}

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	public Cache getBoardCache() {
		return boardCache;
	}

	public void setBoardCache(Cache boardCache) {
		this.boardCache = boardCache;
	}

	public Cache getSysListObjCache() {
		return sysListObjCache;
	}

	public void setSysListObjCache(Cache sysListObjCache) {
		this.sysListObjCache = sysListObjCache;
	}

	public UserGroupDAO getUserGroupDAO() {
		return userGroupDAO;
	}

	public void setUserGroupDAO(UserGroupDAO userGroupDAO) {
		this.userGroupDAO = userGroupDAO;
	}

	public BoardPermissionDAO getBoardPermissionDAO() {
		return boardPermissionDAO;
	}

	public void setBoardPermissionDAO(BoardPermissionDAO boardPermissionDAO) {
		this.boardPermissionDAO = boardPermissionDAO;
	}

	public PermissionDAO getPermissionDAO() {
		return permissionDAO;
	}

	public void setPermissionDAO(PermissionDAO permissionDAO) {
		this.permissionDAO = permissionDAO;
	}

	public Cache getUserPermissionCache() {
		return userPermissionCache;
	}

	public void setUserPermissionCache(Cache userPermissionCache) {
		this.userPermissionCache = userPermissionCache;
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public ForumDAO getForumDAO() {
		return forumDAO;
	}

	public void setForumDAO(ForumDAO forumDAO) {
		this.forumDAO = forumDAO;
	}

	public ForumDAO getForumHistoryDAO() {
		return forumHistoryDAO;
	}

	public void setForumHistoryDAO(ForumDAO forumHistoryDAO) {
		this.forumHistoryDAO = forumHistoryDAO;
	}

	public SysStatService getSysStatService() {
		return sysStatService;
	}

	public void setSysStatService(SysStatService sysStatService) {
		this.sysStatService = sysStatService;
	}

	@SuppressWarnings("unchecked")
	public Board createBoard(Board board) throws BbscsException {
		try {
			Board pboard = this.getBoardDAO().getBoardByID(board.getParentID()); // 取得父级版区
			if (pboard != null) { // 父级版区存在
				List pboards = new ArrayList();
				pboards.addAll(pboard.getParentIDs());
				pboards.add(pboard.getId());
				board.setParentIDs(pboards); // 设置父级版区列表字段
				board.setLevel(pboard.getLevel() + 1); // 设置级别
			}
			board = this.getBoardDAO().saveBoard(board);

			if (pboard != null) {
				List pcboards = this.getBoardDAO().findBoardsByParentID(board.getParentID(), 1, -1,
						Constant.FIND_BOARDS_BY_ORDER); // 取得父级半区的所有子版区列表

				List cids = this.getBoardIDs(pcboards);
				pboard.setChildIDs(cids); // 设置父级版区的所有子版区列表字段
				this.getBoardDAO().saveBoard(pboard);
				this.getBoardCache().remove(pboard.getId());
			}

			this.clearBoradListSysListCache(board.getParentID());

			// 为版区增加用户组版区权限
			List gl = this.getUserGroupDAO().findUserGroupsAll(); // 取得用户组列表
			BoardPermission bp;

			for (int i = 0; i < gl.size(); i++) {
				UserGroup ug = (UserGroup) gl.get(i);
				bp = new BoardPermission();
				bp.setBoardID(board.getId().longValue());
				bp.setGroupID(ug.getId().intValue());

				switch (ug.getId().intValue()) {
				case 1:
					bp.setPermissions(Constant.BOARD_PERMISSION_GROUP_LIST_1);
					break;
				case 2:
					bp.setPermissions(Constant.BOARD_PERMISSION_GROUP_LIST_2);
					break;
				case 3:
					bp.setPermissions(Constant.BOARD_PERMISSION_GROUP_LIST_3);
					break;
				case 4:
					bp.setPermissions(Constant.BOARD_PERMISSION_GROUP_LIST_4);
					break;
				case 5:
					bp.setPermissions(Constant.BOARD_PERMISSION_GROUP_LIST_5);
					break;
				case 6:
					bp.setPermissions(Constant.BOARD_PERMISSION_GROUP_LIST_6);
					break;
				default:
					bp.setPermissions(Constant.BOARD_PERMISSION_GROUP_LIST_1);
				}
				this.getBoardPermissionDAO().saveBoardPermission(bp);
			}

			return board;

		} catch (Exception e) {
			logger.error(e);
			throw new BbscsException(e);
		}

	}

	/*
	 * public List findBoardIdsByParentID(long pid, int useStat, int hidden, int
	 * orderType) { return null; }
	 */

	@SuppressWarnings("unchecked")
	public List findBoardsAllTree(long pid, List topList, int useStat, int hidden, int orderType) {
		List l = this.getBoardDAO().findBoardsByParentID(pid, useStat, hidden, orderType);

		for (int i = 0; i < l.size(); i++) {
			Board b = (Board) l.get(i);
			topList.add(b);
			this.findBoardsAllTree(b.getId().longValue(), topList, useStat, hidden, orderType);
		}
		return topList;
	}

	public List findBoardsByParentID(long pid, int useStat, int hidden, int orderType) {
		List l = (List) this.getSysListObjCache().get(
				"[B][" + pid + "][" + useStat + "][" + hidden + "][" + orderType + "]");
		if (l == null) {
			// l = this.getBoardDAO().findBoardsByParentID(pid, useStat, hidden,
			// orderType);
			l = this.getBoardDAO().findBoardIdsByParentID(pid, useStat, hidden, orderType);
			this.getSysListObjCache().add("[B][" + pid + "][" + useStat + "][" + hidden + "][" + orderType + "]", l);
		}
		List<Board> bl = new ArrayList<Board>();
		if (l != null && !l.isEmpty()) {
			for (int i = 0; i < l.size(); i++) {
				// Board b = this.getBoardByID(((Long)l.get(i)).longValue());
				Board b = this.getBoardByID((Long) l.get(i));
				if (b != null) {
					bl.add(b);
				}
			}
		}

		return bl;
	}

	public List findBoardsInIDs(List ids) {
		List<Board> l = new ArrayList<Board>();
		if (ids != null && !ids.isEmpty()) {
			for (int i = 0; i < ids.size(); i++) {
				// Board b = this.getBoardByID(((Long) ids.get(i)).longValue());
				Board b = this.getBoardByID((Long) ids.get(i));
				if (b != null) {
					l.add(b);
				}
			}
		}
		return l;
	}

	public Board getBoardByID(long id) {
		Board board = (Board) this.getBoardCache().get(new Long(id));
		if (board == null) {
			board = this.getBoardDAO().getBoardByID(id);
			if (board != null) {
				// board.getBoardMaster().size();
				this.getBoardCache().add(board.getId(), board);
			}
		}
		return board;
	}

	public Map[] getBoardMasterPermission(int roleID) {
		if (Constant.USE_PERMISSION_CACHE) {
			Map[] mapPermission = (Map[]) this.getUserPermissionCache().get("R_" + String.valueOf(roleID));
			if (mapPermission == null) {
				mapPermission = this.getPermissionMaps(roleID);
				this.getUserPermissionCache().add("R_" + String.valueOf(roleID), mapPermission);
			}
			return mapPermission;
		} else {
			return this.getPermissionMaps(roleID);
		}
	}

	public Map[] getBoardPermission(long bid, int groupID) {
		if (Constant.USE_PERMISSION_CACHE) {
			Map[] mapPermission = (Map[]) this.getUserPermissionCache().get(
					"BG_" + String.valueOf(bid) + "_" + String.valueOf(groupID));
			if (mapPermission == null) {
				mapPermission = this.getPermissionMaps(bid, groupID);
				this.getUserPermissionCache().add("BG_" + String.valueOf(bid) + "_" + String.valueOf(groupID),
						mapPermission);
			}
			return mapPermission;
		} else {
			return this.getPermissionMaps(bid, groupID);
		}
	}

	@SuppressWarnings("unchecked")
	private Map[] getPermissionMaps(long bid, int groupID) {
		Map[] boardPermission = { new HashMap(), new HashMap() };
		BoardPermission bp = this.getBoardPermissionDAO().findBoardPermissionByBidGid(bid, groupID);
		List permissions = bp.getPermissions(); // 取得权限ID列表
		if (permissions != null && !permissions.isEmpty()) {
			List permissionList = this.getPermissionDAO().findPermissionnIDs(permissions); // 取得权限列表
			for (int i = 0; i < permissionList.size(); i++) {
				Permission permission = (Permission) permissionList.get(i);
				if (permission.getTypeID() == 2) {
					boardPermission[0].put(permission.getResource() + "," + permission.getAction(), permission);
				}
				if (permission.getTypeID() == 3) {
					boardPermission[1].put(permission.getId(), permission);
				}
			}
		}
		return boardPermission;
	}

	@SuppressWarnings("unchecked")
	private Map[] getPermissionMaps(int roleID) {
		Map[] mapPermission = { new HashMap(), new HashMap() };

		Role role = this.getRoleDAO().findRoleByID(roleID);
		List permissions = role.getPermissions(); // 取得角色的权限ID列表
		if (permissions != null && !permissions.isEmpty()) {
			List permissionList = this.getPermissionDAO().findPermissionnIDs(permissions); // 取得权限列表
			for (int i = 0; i < permissionList.size(); i++) {
				Permission permission = (Permission) permissionList.get(i);
				if (permission.getTypeID() == 2) {
					mapPermission[0].put(permission.getResource() + "," + permission.getAction(), permission);
				}
				if (permission.getTypeID() == 3) {
					mapPermission[1].put(permission.getId(), permission);
				}
			}
		}
		return mapPermission;
	}

	public int getNextOrder(long pid) {
		return this.getBoardDAO().getNextOrder(pid);
	}

	public int getPostSumNum(int mainorall, int useStat, int hidden) {
		return this.getBoardDAO().getPostSumNum(mainorall, useStat, hidden);
	}

	public boolean isBoardMaster(Board board, String userName) {
		if (board.getBoardMaster().get(userName) == null) {
			return false;
		} else {
			return true;
		}
	}

	public void removeBoard(Board board) throws BbscsException {
		try {
			Long lbid = board.getId();
			long pbid = board.getParentID();
			Board pboard = this.getBoardDAO().getBoardByID(board.getParentID()); // 取得父版区

			this.getBoardDAO().removeBoard(board); // 删除版区
			this.getBoardPermissionDAO().removeBoardPermissionsByBid(board.getId().longValue());

			if (pboard != null) { // 父版区存在,对ChildIDs字段做矫正
				List pcboards = this.getBoardDAO().findBoardsByParentID(pboard.getId().longValue(), 1, 0,
						Constant.FIND_BOARDS_BY_ORDER);
				List cids = this.getBoardIDs(pcboards);
				pboard.setChildIDs(cids);
				this.getBoardDAO().saveBoard(pboard);
			}

			this.getBoardCache().remove(lbid);
			this.clearBoradListSysListCache(pbid);
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public void removeBoardTag(Board board, String tagID) throws BbscsException {
		BoardTag bt = null;
		Iterator it = board.getBoardTag().iterator();
		while (it.hasNext()) {
			bt = (BoardTag) it.next();
			if (bt.getId().equals(tagID)) {
				board.getBoardTag().remove(bt);
				break;
			}
		}
		try {
			board = this.getBoardDAO().saveBoard(board);
			this.getForumDAO().updateForumsTag(tagID, "0", "");
			this.getForumHistoryDAO().updateForumsTag(tagID, "0", "");
			this.getBoardCache().remove(board.getId());
		} catch (Exception e) {
			logger.error(e);
			throw new BbscsException(e);
		}

	}

	public Board saveBoard(Board board) throws BbscsException {
		try {
			board = this.getBoardDAO().saveBoard(board);
			this.getBoardCache().remove(board.getId()); // 从Board Cache中清除
			return board;
		} catch (Exception e) {
			logger.error(e);
			throw new BbscsException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Board updateBoard(Board board, long oldParentID) throws BbscsException {
		try {
			Board pboard = this.getBoardDAO().getBoardByID(board.getParentID());

			if (pboard != null) {
				List pboards = new ArrayList();
				pboards.addAll(pboard.getParentIDs());
				pboards.add(pboard.getId());
				board.setParentIDs(pboards);
				board.setLevel(pboard.getLevel() + 1);
			} else {
				board.setParentIDs(new ArrayList());
				board.setLevel(0);
			}
			board = this.getBoardDAO().saveBoard(board);

			if (pboard != null) {
				List pcboards = this.getBoardDAO().findBoardsByParentID(board.getParentID(), 1, -1,
						Constant.FIND_BOARDS_BY_ORDER);
				List cids = this.getBoardIDs(pcboards);
				pboard.setChildIDs(cids);
				this.getBoardDAO().saveBoard(pboard);
				this.getBoardCache().remove(pboard.getId());
			}
			this.clearBoradListSysListCache(board.getParentID());

			if (oldParentID != -1) { // 父级版区改变。修正父级版区数据
				Board pboardOld = this.getBoardDAO().getBoardByID(oldParentID);
				if (pboardOld != null) {
					List pcboards = this.getBoardDAO().findBoardsByParentID(pboardOld.getId().longValue(), 1, -1,
							Constant.FIND_BOARDS_BY_ORDER);
					List cids = this.getBoardIDs(pcboards);
					pboardOld.setChildIDs(cids);
					this.getBoardDAO().saveBoard(pboardOld);
					this.getBoardCache().remove(pboardOld.getId());
					this.clearBoradListSysListCache(oldParentID);
				}
			}
			this.getBoardCache().remove(board.getId()); // 从Cache中清除

			return board;
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public List getBoardIDs(List boards) {
		List<Long> l = new ArrayList<Long>();
		for (int i = 0; i < boards.size(); i++) {
			Board b = (Board) boards.get(i);
			l.add(b.getId());
		}
		return l;
	}

	public void saveBoardsPostNumCount() throws BbscsException {
		long totalNum = 0;
		long totalMainNum = 0;
		List bl = findBoardsByParentID(0, 1, -1, Constant.FIND_BOARDS_BY_ORDER);
		for (int i = 0; i < bl.size(); i++) {
			Board b = (Board) bl.get(i);
			if (b.getBoardType() == 3) {
				b.setMainPostNum(this.getForumDAO().getForumNum(b.getId(), 1, 0, 0, -1)
						+ this.getForumHistoryDAO().getForumNum(b.getId(), 1, 0, 0, -1));
				b.setPostNum(this.getForumDAO().getForumNum(b.getId(), -1, 0, 0, -1)
						+ this.getForumHistoryDAO().getForumNum(b.getId(), -1, 0, 0, -1));
				try {
					b = this.getBoardDAO().saveBoard(b);
					totalNum = totalNum + b.getPostNum();
					totalMainNum = totalMainNum + b.getMainPostNum();
					// if (Constant.USE_CLUSTER) {
					this.getBoardCache().remove(b.getId()); // 从Cache中清除
					// } else {
					// this.getBoardCache().add(b.getId(), b);
					// }
				} catch (Exception ex1) {
					logger.error(ex1);
					throw new BbscsException(ex1);
				}
			}
			List bl2 = findBoardsByParentID(b.getId(), 1, -1, Constant.FIND_BOARDS_BY_ORDER);
			if (!bl2.isEmpty()) {
				for (int j = 0; j < bl2.size(); j++) {
					Board b2 = (Board) bl2.get(j);
					if (b2.getBoardType() == 3) {
						b2.setMainPostNum(this.getForumDAO().getForumNum(b2.getId(), 1, 0, 0, -1)
								+ this.getForumHistoryDAO().getForumNum(b2.getId(), 1, 0, 0, -1));
						b2.setPostNum(this.getForumDAO().getForumNum(b2.getId(), -1, 0, 0, -1)
								+ this.getForumHistoryDAO().getForumNum(b2.getId(), -1, 0, 0, -1));
						try {
							b2 = this.getBoardDAO().saveBoard(b2);
							totalNum = totalNum + b2.getPostNum();
							totalMainNum = totalMainNum + b2.getMainPostNum();
							// if (Constant.USE_CLUSTER) {
							this.getBoardCache().remove(b2.getId()); // 从Cache中清除
							// } else {
							// this.getBoardCache().add(b2.getId(), b2);
							// }
						} catch (Exception ex1) {
							logger.error(ex1);
							throw new BbscsException(ex1);
						}
					}
				}
			}
		}
		logger.info("postMainNum:" + totalMainNum + " postNum:" + totalNum);
		this.getSysStatService().savePostNum(totalMainNum, totalNum);
	}

	private void clearBoradListSysListCache(long pid) {
		String[] useStats = { "-1", "0", "1" };
		String[] hiddens = { "-1", "0", "1" };
		String[] orderTypes = { "0", "1", "2" };
		for (int i = 0; i < useStats.length; i++) {
			for (int j = 0; j < hiddens.length; j++) {
				for (int x = 0; x < orderTypes.length; x++) {
					this.getSysListObjCache().remove(
							"[B][" + pid + "][" + useStats[i] + "][" + hiddens[j] + "][" + orderTypes[x] + "]");
				}
			}
		}
	}

}
