package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.Forum;
import java.util.*;
import com.laoer.bbscs.comm.OrderObj;

/**
 * <p>
 * Title: TianyiBBS
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
 * @author Laoer
 * @version 8.0
 */
public interface ForumDAO {

	/**
	 * 保存Forum对象
	 *
	 * @param forum
	 *            Forum
	 * @return Forum
	 */
	public Forum saveForum(Forum forum);

	/**
	 *
	 * @param forum
	 * @return
	 */
	public Forum saveOrUpdateForum(Forum forum);

	/**
	 *
	 * @param forum
	 * @return
	 */
	public Forum updateForum(Forum forum);

	/**
	 * 根据ID取得Forum对象
	 *
	 * @param id
	 *            String
	 * @return Forum
	 */
	public Forum findForumByID(String id);

	/**
	 * 根据ID，版区ID取得Forum对象
	 *
	 * @param id
	 *            String
	 * @param boardID
	 *            long
	 * @return Forum
	 */
	public Forum findForumByID(String id, long bid);

	/**
	 *
	 * @param bid
	 *            long
	 * @param isNew
	 *            int
	 * @param delSign
	 *            int
	 * @param auditing
	 *            int
	 * @param auditingAttachFile
	 *            int
	 * @return int
	 */
	public long getForumNum(long bid, int isNew, int delSign, int auditing, int auditingAttachFile);

	/**
	 *
	 * @param bid
	 *            long
	 * @param mainID
	 *            String
	 * @param delSign
	 *            int
	 * @param auditing
	 *            int
	 * @return int
	 */
	public long getForumTopicNum(long bid, String mainID, int delSign, int auditing);

	/**
	 *
	 * @param bid
	 *            long
	 * @param isNew
	 *            int
	 * @param delSign
	 *            int
	 * @param auditing
	 *            int
	 * @param oo
	 *            OrderObj[]
	 * @return List
	 */
	public List findForums(long bid, int isNew, int delSign, int auditing, OrderObj[] oo);

	/**
	 *
	 * @param bid
	 *            long
	 * @param isNew
	 *            int
	 * @param delSign
	 *            int
	 * @param auditing
	 *            int
	 * @param auditingAttachFile
	 *            int
	 * @param oo
	 *            OrderObj[]
	 * @param firstResult
	 *            int
	 * @param maxResults
	 *            int
	 * @return List
	 */
	public List findForums(long bid, int isNew, int delSign, int auditing, int auditingAttachFile, OrderObj[] oo,
			int firstResult, int maxResults);
	/**
	 *
	 * @param bid
	 *            long
	 * @param isNew
	 *            int
	 * @param delSign
	 *            int
	 * @param auditing
	 *            int
	 * @param auditingAttachFile
	 *            int
	 * @param haveAttachFile
	 *            int
	 * @param oo
	 *            OrderObj[]
	 * @param firstResult
	 *            int
	 * @param maxResults
	 *            int
	 * @return List
	 */
	public List findForumsByAttachFile(long bid, int isNew, int delSign, int auditing, int auditingAttachFile,int haveAttachFile, OrderObj[] oo,
			int firstResult, int maxResults);

	/**
	 *
	 * @param bid
	 *            long
	 * @param mainID
	 *            String
	 * @param delSign
	 *            int
	 * @param auditing
	 *            int
	 * @param auditingAttachFile
	 *            int
	 * @param oo
	 *            OrderObj[]
	 * @return List
	 */
	public List findForumsTopic(long bid, String mainID, int delSign, int auditing, int auditingAttachFile,
			OrderObj[] oo);

	/**
	 *
	 * @param bid
	 *            long
	 * @param mainID
	 *            String
	 * @param delSign
	 *            int
	 * @param auditing
	 *            int
	 * @param oo
	 *            OrderObj[]
	 * @param firstResult
	 *            int
	 * @param maxResults
	 *            int
	 * @return List
	 */
	public List findForumsTopic(long bid, String mainID, int delSign, int auditing, OrderObj[] oo, int firstResult,
			int maxResults);

	/**
	 *
	 * @param bid
	 *            long
	 * @param elite
	 *            long
	 * @param eliteId
	 *            long
	 * @return List
	 */
	public List findForumsElite(long bid, long elite, long eliteId);

	/**
	 *
	 * @param bid
	 *            long
	 * @param con
	 *            String
	 * @param text
	 *            String
	 * @param delSign
	 *            int
	 * @param auditing
	 *            int
	 * @return int
	 */
	public long getSearchNum(long bid, String con, String text, int delSign, int auditing);

	/**
	 *
	 * @param bid
	 *            long
	 * @param con
	 *            String
	 * @param text
	 *            String
	 * @param delSign
	 *            int
	 * @param auditing
	 *            int
	 * @param orderby
	 *            String
	 * @param ascOrDesc
	 *            int
	 * @param firstResult
	 *            int
	 * @param maxResults
	 *            int
	 * @return List
	 */
	public List getSearchList(long bid, String con, String text, int delSign, int auditing, String orderby,
			int ascOrDesc, int firstResult, int maxResults);

	/**
	 *
	 * @param userID
	 *            String
	 * @param isNew
	 *            int
	 * @param delSign
	 *            int
	 * @param auditing
	 *            int
	 * @return int
	 */
	public long getForumOwnerNum(String userID, int isNew, int delSign, int auditing);

	/**
	 *
	 * @param userID
	 *            String
	 * @param isNew
	 *            int
	 * @param delSign
	 *            int
	 * @param auditing
	 *            int
	 * @param firstResult
	 *            int
	 * @param maxResults
	 *            int
	 * @return List
	 */
	public List findForumsOwner(String userID, int isNew, int delSign, int auditing, final String orderby,
			final int ascOrDesc, int firstResult, int maxResults);

	/**
	 *
	 * @param id
	 *            String
	 * @param bid
	 *            long
	 */
	public void removeForum(String id, long bid);

	/**
	 *
	 * @param id
	 *            String
	 */
	public void removeForum(String id);

	/**
	 *
	 * @param forum
	 *            Forum
	 */
	public void removeForum(Forum forum);

	/**
	 *
	 * @param commend
	 *            long
	 * @return int
	 */
	public long getForumNumCommend(long commend, int isNew, int delSign, int auditing);

	/**
	 *
	 * @param commend
	 *            long
	 * @param orderby
	 *            String
	 * @param ascOrDesc
	 *            int
	 * @param firstResult
	 *            int
	 * @param maxResults
	 *            int
	 * @return List
	 */
	public List findForumsCommend(long commend, int isNew, int delSign, int auditing, String orderby, int ascOrDesc,
			int firstResult, int maxResults);

	public long getForumNumCommend(long bid, long commend, int isNew, int delSign, int auditing);

	public List findForumsCommend(long bid, long commend, int isNew, int delSign, int auditing, String orderby,
			int ascOrDesc, int firstResult, int maxResults);

	/**
	 *
	 * @param bid
	 *            long
	 * @param reNum
	 *            int
	 * @param click
	 *            int
	 * @return int
	 */
	public long getForumNumHotTopic(long bid, int reNum, int click);

	/**
	 *
	 * @param bid
	 *            long
	 * @param reNum
	 *            int
	 * @param click
	 *            int
	 * @param firstResult
	 *            int
	 * @param maxResults
	 *            int
	 * @return List
	 */
	public List findForumsHotTopic(long bid, int reNum, int click, int firstResult, int maxResults);

	/**
	 *
	 * @param atime
	 *            long
	 * @return List
	 */
	public List findForumsToHistory(long atime);

	/**
	 *
	 * @param bid
	 *            long
	 * @param mainID
	 *            String
	 * @param userID
	 *            String
	 * @param isnew
	 *            int
	 * @param delSign
	 *            int
	 * @param auditing
	 *            int
	 * @return int
	 */
	public long getForumNumInMainIDByUserID(long bid, String mainID, String userID, int isnew, int delSign, int auditing);

	/**
	 *
	 * @param bid
	 *            long
	 * @param mainID
	 *            String
	 * @param userID
	 *            String
	 * @param isnew
	 *            int
	 * @param delSign
	 *            int
	 * @param auditing
	 *            int
	 * @param orderby
	 *            String
	 * @param ascOrDesc
	 *            int
	 * @param firstResult
	 *            int
	 * @param maxResults
	 *            int
	 * @return List
	 */
	public List findForumsInMainIDByUserID(long bid, String mainID, String userID, int isnew, int delSign,
			int auditing, String orderby, int ascOrDesc, int firstResult, int maxResults);

	/**
	 *
	 * @param bid
	 *            long
	 * @param ids
	 *            List
	 * @return List
	 */
	public List findForumsInIds(long bid, List ids);

	/**
	 *
	 * @param ids
	 *            List
	 * @return List
	 */
	public List findForumsInIds(List ids);

	/**
	 *
	 * @param bid
	 *            long
	 * @param atime
	 *            long
	 * @return List
	 */
	public List findForumsRealDelAll(long bid, long atime);

	/**
	 *
	 * @param indexStatus
	 *            int
	 * @return List
	 */
	public List findForumsByIndexStatus(int indexStatus);

	/**
	 *
	 * @param bid
	 *            long
	 * @param tagId
	 *            String
	 * @param isNew
	 *            int
	 * @param delSign
	 *            int
	 * @param auditing
	 *            int
	 * @return int
	 */
	public long getForumNum(long bid, String tagId, int isNew, int delSign, int auditing);

	/**
	 *
	 * @param bid
	 *            long
	 * @param tagId
	 *            String
	 * @param isNew
	 *            int
	 * @param delSign
	 *            int
	 * @param auditing
	 *            int
	 * @param oo
	 *            OrderObj[]
	 * @param firstResult
	 *            int
	 * @param maxResults
	 *            int
	 * @return List
	 */
	public List findForums(long bid, String tagId, int isNew, int delSign, int auditing, OrderObj[] oo,
			int firstResult, int maxResults);

	public void updateForumsTag(String oldTagId, String newTagID, String newTagName);

	public long getForumNumBeforeDate(long bid, long atime);

	public List findForumsBeforeDate(long bid, long atime, int firstResult, int maxResults);

	public List findAllForumsByClick(final int click, final OrderObj[] oo);
}
