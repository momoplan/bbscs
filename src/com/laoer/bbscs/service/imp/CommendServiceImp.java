package com.laoer.bbscs.service.imp;

import java.io.*;
import java.util.*;

import org.apache.commons.logging.*;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.fio.*;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.service.web.*;
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
public class CommendServiceImp implements CommendService {

	private static final Log logger = LogFactory.getLog(CommendServiceImp.class);

	private CommendDAO commendDAO;

	private ForumDAO forumDAO;

	private CommendFileIO commendFileIO;

	private Cache sysListObjCache;

	public CommendServiceImp() {
	}

	/**
	 *
	 * @param commend
	 *            Commend
	 * @return Commend
	 * @throws BbscsException
	 * @todo Implement this com.laoer.bbscs.service.CommendService method
	 */
	public Commend saveCommend(Commend commend) throws BbscsException {
		try {
			return this.getCommendDAO().saveCommend(commend);
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	/**
	 *
	 * @param id
	 *            String
	 * @return Commend
	 * @todo Implement this com.laoer.bbscs.service.CommendService method
	 */
	public Commend findCommendByID(String id) {
		return this.getCommendDAO().findCommendByID(id);
	}

	/**
	 *
	 * @param postID
	 *            String
	 * @return Commend
	 * @todo Implement this com.laoer.bbscs.service.CommendService method
	 */
	public Commend findCommendByPostID(String postID) {
		return this.getCommendDAO().findCommendByPostID(postID);
	}

	/**
	 *
	 * @param commendBoardID
	 *            long
	 * @return int
	 * @todo Implement this com.laoer.bbscs.service.CommendService method
	 */
	public int getCommendNumByCommendBoardID(long commendBoardID) {
		return this.getCommendDAO().getCommendNumByCommendBoardID(commendBoardID);
	}

	/**
	 *
	 * @param commendBoardID
	 *            long
	 * @param pages
	 *            Pages
	 * @return PageList
	 * @todo Implement this com.laoer.bbscs.service.CommendService method
	 */
	public PageList findCommendsByCommendBoardID(long commendBoardID, Pages pages) {
		PageList pl = new PageList();
		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(this.getCommendDAO().getCommendNumByCommendBoardID(commendBoardID));
		}
		pages.executeCount();
		List l = this.getCommendDAO().findCommendsByCommendBoardID(commendBoardID, pages.getSpage(),
				pages.getPerPageNum());
		pl.setObjectList(l);
		pl.setPages(pages);
		return pl;
	}

	/**
	 *
	 * @param commendTop
	 *            int
	 * @return int
	 * @todo Implement this com.laoer.bbscs.service.CommendService method
	 */
	public int getCommendNumByCommendTop(int commendTop) {
		return this.getCommendDAO().getCommendNumByCommendTop(commendTop);
	}

	/**
	 *
	 * @param commendTop
	 *            int
	 * @param pages
	 *            Pages
	 * @return PageList
	 * @todo Implement this com.laoer.bbscs.service.CommendService method
	 */
	public PageList findCommendsByCommendTop(int commendTop, Pages pages) {
		PageList pl = new PageList();
		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(this.getCommendDAO().getCommendNumByCommendTop(commendTop));
		}
		pages.executeCount();
		List l = this.getCommendDAO().findCommendsByCommendTop(commendTop, pages.getSpage(), pages.getPerPageNum());
		pl.setObjectList(l);
		pl.setPages(pages);
		return pl;
	}

	/**
	 *
	 * @param commend
	 *            Commend
	 * @throws BbscsException
	 * @todo Implement this com.laoer.bbscs.service.CommendService method
	 */
	public void removeCommend(Commend commend) throws BbscsException {
		try {
			this.getCommendDAO().removeCommend(commend);
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	/**
	 *
	 * @param postID
	 *            String
	 * @throws BbscsException
	 * @todo Implement this com.laoer.bbscs.service.CommendService method
	 */
	public void removeCommend(String postID) throws BbscsException {
		try {
			this.getCommendDAO().removeCommend(postID);
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public void removeCommend(long commendBoardID, List ids) throws BbscsException {
		List l = this.getCommendDAO().findCommendsInIds(ids);
		try {
			for (int i = 0; i < l.size(); i++) {
				Commend c = (Commend) l.get(i);
				Forum f = this.getForumDAO().findForumByID(c.getPostID(), c.getBoardID());
				f.setCommend(0);
				this.getForumDAO().saveOrUpdateForum(f);
				this.getCommendDAO().removeCommend(c);
			}
			List commendList = this.getCommendDAO().findCommendsByCommendBoardID(commendBoardID, 0, 10);
			this.getCommendFileIO().saveCommendInReadPageFile(commendBoardID, commendList);
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public void createCommendTopFile(int num) throws BbscsException {
		List l = this.getCommendDAO().findCommendsByCommendTop(0, 0, num);
		try {
			this.getCommendFileIO().saveCommendInReadPageFile(0, l);
		} catch (IOException ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public List findCommendsByCommendTopCache(int commendTop, int num) {
		List l = (List) this.getSysListObjCache().get(Constant.COMMEND_CACHE_NAME);
		if (l == null) {
			l = this.getCommendDAO().findCommendsByCommendTop(commendTop, 0, num);
			this.getSysListObjCache().add(Constant.COMMEND_CACHE_NAME, l);
		}
		return l;
	}

	public CommendDAO getCommendDAO() {
		return commendDAO;
	}

	public ForumDAO getForumDAO() {
		return forumDAO;
	}

	public CommendFileIO getCommendFileIO() {
		return commendFileIO;
	}

	public void setCommendDAO(CommendDAO commendDAO) {
		this.commendDAO = commendDAO;
	}

	public void setForumDAO(ForumDAO forumDAO) {
		this.forumDAO = forumDAO;
	}

	public void setCommendFileIO(CommendFileIO commendFileIO) {
		this.commendFileIO = commendFileIO;
	}

	public Cache getSysListObjCache() {
		return sysListObjCache;
	}

	public void setSysListObjCache(Cache sysListObjCache) {
		this.sysListObjCache = sysListObjCache;
	}

}
