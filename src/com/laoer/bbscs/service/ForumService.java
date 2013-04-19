package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.Forum;
import com.laoer.bbscs.exception.BbscsException;
import java.util.List;
import com.laoer.bbscs.service.web.PageList;
import com.laoer.bbscs.service.web.Pages;
import com.laoer.bbscs.comm.OrderObj;
import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.fio.UploadFile;
import com.laoer.bbscs.bean.Vote;
import com.laoer.bbscs.bean.ForumBuy;

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
public interface ForumService {

	/**
	 * 保存或更新Forum对象
	 *
	 * @param forum
	 *            Forum
	 * @return Forum
	 * @throws BbscsException
	 */
	public Forum saveOrUpdateForum(Forum forum) throws BbscsException;

	/**
	 *
	 * @param forum
	 * @return
	 * @throws BbscsException
	 */
	public Forum saveForum(Forum forum) throws BbscsException;

	/**
	 *
	 * @param forum
	 * @return
	 * @throws BbscsException
	 */
	public Forum updateForum(Forum forum) throws BbscsException;

	/**
	 * 根据ID取得Forum对象
	 *
	 * @param id
	 *            String
	 * @return Forum
	 */
	public Forum findForumByID(String id);

	/**
	 * 根据ID,Bid取得Forum对象
	 *
	 * @param id
	 *            String
	 * @param bid
	 *            long
	 * @return Forum
	 */
	public Forum findForumByID(String id, long bid);

	/**
	 * 取得帖子数量
	 *
	 * @param bid
	 *            long
	 * @param isNew
	 *            int
	 * @param delSign
	 *            int
	 * @param auditing
	 *            int
	 * @return int
	 */
	public long getForumNum(long bid, int isNew, int delSign, int auditing, int auditingAttachFile);

	/**
	 * 取得正常帖子数量
	 *
	 * @param bid
	 *            long
	 * @return int
	 */
	public long getForumNum(long bid);

	/**
	 * 取得主帖数量
	 *
	 * @param bid
	 *            long
	 * @return int
	 */
	public long getForumMainNum(long bid);

	/**
	 * 取得已删除帖子数量
	 *
	 * @param bid
	 *            long
	 * @return int
	 */
	public long getForumDelNum(long bid);

	/**
	 * 取得未审核帖子数量
	 *
	 * @param bid
	 *            long
	 * @return int
	 */
	public long getForumAuditingNum(long bid);

	/**
	 * 取得同一主题帖子数量
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
	 * 取得一个主题的帖子数量
	 *
	 * @param bid
	 *            long
	 * @param mainID
	 *            String
	 * @return int
	 */
	public long getForumTopicNum(long bid, String mainID);

	/**
	 * 取得一个主题已删除帖子数量
	 *
	 * @param bid
	 *            long
	 * @param mainID
	 *            String
	 * @return int
	 */
	public long getForumTopicDelNum(long bid, String mainID);

	/**
	 * 取得一个主题未审核帖子数量
	 *
	 * @param bid
	 *            long
	 * @param mainID
	 *            String
	 * @return int
	 */
	public long getForumTopicAuditingNum(long bid, String mainID);

	/**
	 * 取得帖子列表
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
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForums(long bid, int isNew, int delSign, int auditing, int auditingAttachFile, OrderObj[] oo,
			Pages pages);

	/**
	 * 取得主帖分页列表(WWW方式，发帖时间排序)
	 *
	 * @param bid
	 *            long
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForumsMainWWW(long bid, Pages pages);

	/**
	 * 取得主帖分页列表(顶帖方式，回复时间排序)
	 *
	 * @param bid
	 *            long
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForumsMainLastRe(long bid, Pages pages);

	/**
	 * 取得正常帖子分页列表，不分主从
	 *
	 * @param bid
	 *            long
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForumsAll(long bid, Pages pages);

	/**
	 * 取得已删除帖子分页列表
	 *
	 * @param bid
	 *            long
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForumsDel(long bid, Pages pages);

	/**
	 * 取得未审核帖子分页列表
	 *
	 * @param bid
	 *            long
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForumsAuditing(long bid, Pages pages);

	/**
	 * 取得同一主题帖子分页列表
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
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForumsTopic(long bid, String mainID, int delSign, int auditing, OrderObj[] oo, Pages pages);

	/**
	 * 取得同一主题帖子分页列表
	 *
	 * @param bid
	 *            long
	 * @param mainID
	 *            String
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForumsTopic(long bid, String mainID, Pages pages);

	public List findForumsTopicAll(long bid, String mainID, int delSign, int auditing, OrderObj[] oo);

	/**
	 * 取得已删除同一主题帖子列表
	 *
	 * @param bid
	 *            long
	 * @param mainID
	 *            String
	 * @return List
	 */
	public List findForumsTopicDel(long bid, String mainID);

	/**
	 * 取得未审核同一主题帖子列表
	 *
	 * @param bid
	 *            long
	 * @param mainID
	 *            String
	 * @return List
	 */
	public List findForumsTopicAuditing(long bid, String mainID);

	/**
	 * 取得精华帖列表
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
	 * 取得搜索帖子数量
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
	 * 取得搜索结果分页列表
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
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList getSearchList(long bid, String con, String text, int delSign, int auditing, String orderby,
			int ascOrDesc, Pages pages);

	/**
	 * 取得搜索帖子数量
	 *
	 * @param bid
	 *            long
	 * @param con
	 *            String
	 * @param text
	 *            String
	 * @return int
	 */
	public long getSearchNum(long bid, String con, String text);

	/**
	 * 取得搜索结果分页列表
	 *
	 * @param bid
	 *            long
	 * @param con
	 *            String
	 * @param text
	 *            String
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList getSearchList(long bid, String con, String text, Pages pages);

	/**
	 * 取得自己的帖子数量
	 *
	 * @param userID
	 *            String
	 * @return int
	 */
	public long getForumOwnerNum(String userID);

	/**
	 * 取得自己的帖子分页列表
	 *
	 * @param userID
	 *            String
	 * @param isNew
	 *            int
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForumsOwner(String userID, int isNew, Pages pages);

	/**
	 * 删除Forum对象
	 *
	 * @param id
	 *            String
	 * @param bid
	 *            long
	 * @throws BbscsException
	 */
	public void removeForum(String id, long bid) throws BbscsException;

	/**
	 * 删除Forum对象
	 *
	 * @param id
	 *            String
	 * @throws BbscsException
	 */
	public void removeForum(String id) throws BbscsException;

	/**
	 * 删除Forum对象
	 *
	 * @param forum
	 *            Forum
	 * @throws BbscsException
	 */
	public void removeForum(Forum forum) throws BbscsException;

	/**
	 * 发帖
	 *
	 * @param forum
	 *            Forum
	 * @return Forum
	 * @throws BbscsException
	 */
	public Forum createForum(Forum forum) throws BbscsException;

	/**
	 * 发帖
	 *
	 * @param forum
	 *            Forum
	 * @param board
	 *            Board
	 * @param ui
	 *            UserInfo
	 * @param uploadFile
	 *            UploadFile
	 * @return Forum
	 * @throws BbscsException
	 */
	public Forum createForum(Forum forum, Board board, UserInfo ui, UploadFile uploadFile) throws BbscsException;

	/**
	 * 回帖
	 *
	 * @param forum
	 *            Forum
	 * @param mainForum
	 *            Forum
	 * @param board
	 *            Board
	 * @param ui
	 *            UserInfo
	 * @param uploadFile
	 *            UploadFile
	 * @param isQuote
	 *            boolean
	 * @return Forum
	 * @throws BbscsException
	 */
	public Forum createReForum(Forum forum, Forum mainForum, Board board, UserInfo ui, UploadFile uploadFile,
			boolean isQuote) throws BbscsException;

	/**
	 *
	 * @param forum
	 *            Forum
	 * @param uploadFile
	 *            UploadFile
	 * @return Forum
	 * @throws BbscsException
	 */
	public Forum createForumUpFile(Forum forum, UploadFile uploadFile) throws BbscsException;

	/**
	 * 帖子修改
	 *
	 * @param forum
	 *            Forum
	 * @return Forum
	 * @throws BbscsException
	 */
	public Forum editForum(Forum forum) throws BbscsException;

	/**
	 *
	 * @param bid
	 *            long
	 * @param commen
	 *            int
	 * @return int
	 */
	public long getForumNumCommend(long bid, long commen);

	/**
	 *
	 * @param commend
	 *            long
	 * @return int
	 */
	public long getForumNumCommend(long commend);


	/**
	 *
	 * @param bid
	 *            long
	 * @param commen
	 *            int
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForumsCommend(long bid, long commen, Pages pages);

	/**
	 *
	 * @param commend
	 *            long
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForumsCommend(long commend, Pages pages);

	/**
	 *
	 * @param bid
	 *            long
	 * @param mainID
	 *            String
	 * @param userID
	 *            String
	 * @return boolean
	 */
	public boolean isReedUser(long bid, String mainID, String userID);

	/**
	 * 删除一个帖子
	 *
	 * @param forum
	 *            Forum
	 * @param board
	 *            Board
	 * @param ui
	 *            UserInfo
	 * @throws BbscsException
	 */
	public void delaPost(Forum forum, Board board, UserInfo ui) throws BbscsException;

	/**
	 *
	 * @param forum
	 *            Forum
	 * @param ui
	 *            UserInfo
	 * @throws BbscsException
	 */
	public void savePostElite(Forum forum, UserInfo ui) throws BbscsException;

	public void saveForums(List forums) throws BbscsException;

	public void saveForumsEliteDel(List forums) throws BbscsException;

	/**
	 *
	 * @param forum
	 *            Forum
	 * @param board
	 *            Board
	 * @param vote
	 *            Vote
	 * @param ui
	 *            UserInfo
	 * @return Forum
	 * @throws BbscsException
	 */
	public Forum createVoteForum(Forum forum, Board board, Vote vote, UserInfo ui,String voteItem) throws BbscsException;

	/**
	 * 搂主帖子数
	 *
	 * @param bid
	 *            long
	 * @param userID
	 *            String
	 * @param mainID
	 *            String
	 * @return int
	 */
	public long getForumOwnerNum(long bid, String userID, String mainID);

	/**
	 * 只看楼主
	 *
	 * @param bid
	 *            long
	 * @param userID
	 *            String
	 * @param mainID
	 *            String
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForumsOwner(long bid, String userID, String mainID, Pages pages);

	/**
	 * 删除附件
	 *
	 * @param forum
	 *            Forum
	 * @param fileNames
	 *            String[]
	 * @throws BbscsException
	 */
	public void removeAttachFile(Forum forum, List fileNames) throws BbscsException;

	/**
	 * 删除某个帖子所有附件
	 *
	 * @param forum
	 *            Forum
	 * @throws BbscsException
	 */
	public void removeAllAttachFile(Forum forum) throws BbscsException;

	/**
	 * 删除未通过审核的帖子附件
	 *
	 * @param ids
	 *            List
	 * @throws BbscsException
	 */
	public void delForumsNotAuditingAttachFile(List ids) throws BbscsException;

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
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForumsHotTopic(long bid, int reNum, int click, Pages pages);

	/**
	 *
	 * @param atime
	 *            long
	 * @throws BbscsException
	 */
	public void removeToHistory(long atime) throws BbscsException;

	/**
	 *
	 * @param forums
	 *            List
	 * @param board
	 *            Board
	 * @throws BbscsException
	 */
	public void delPosts(List forums, Board board) throws BbscsException;

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
	 * @param forum
	 *            Forum
	 * @throws BbscsException
	 */
	public void delPostReal(Forum forum) throws BbscsException;

	/**
	 *
	 * @param ids
	 *            List
	 * @throws BbscsException
	 */
	public void delPostsReal(List ids) throws BbscsException;

	/**
	 * s
	 *
	 * @param bid
	 *            long
	 * @throws BbscsException
	 */
	public void delWastePost(long bid) throws BbscsException;

	/**
	 *
	 * @param bid
	 *            long
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForumsAllManage(long bid, Pages pages);


	/**
	 *
	 * @param ids
	 *            List
	 * @param board
	 *            Board
	 * @throws BbscsException
	 */
	public void saveForumsResume(List ids, Board board) throws BbscsException;

	/**
	 *
	 * @param ids
	 *            List
	 * @param board
	 *            Board
	 * @throws BbscsException
	 */
	public void saveForumsAuditing(List ids, Board board) throws BbscsException;

	/**
	 *
	 * @param ids
	 *            List
	 * @throws BbscsException
	 */
	public void saveForumsAuditingAttachFile(List ids) throws BbscsException;

	/**
	 *
	 * @param bid
	 *            long
	 * @return int
	 */
	public long getForumsAuditingAttachFileNum(long bid);

	/**
	 *
	 * @param bid
	 *            long
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForumsAuditingAttachFile(long bid, Pages pages);

	/**
	 *
	 * @param bid
	 *            long
	 * @param mainID
	 *            String
	 * @return List
	 */
	public List findForumsTopicAuditingAttachFile(long bid, String mainID);

	/**
	 *
	 * @param forum
	 *            Forum
	 * @param titleType
	 *            int[]
	 * @param values
	 *            int[]
	 * @throws BbscsException
	 */
	public void saveForumChangeUser(Forum forum, int[] titleType, int[] values) throws BbscsException;

	/**
	 *
	 * @param forum
	 *            Forum
	 * @param titleType
	 *            int
	 * @param values
	 *            int
	 * @throws BbscsException
	 */
	public void saveForumChangeUser(Forum forum, int titleType, int values) throws BbscsException;

	/**
	 *
	 * @param commend
	 *            long
	 * @throws BbscsException
	 */
	public void createCommendPage(long commend) throws BbscsException;

	/**
	 *
	 * @param createOrDel
	 *            int
	 * @param forum
	 *            Forum
	 * @throws BbscsException
	 */
	public void saveForumCommend(int createOrDel, Board board, Forum forum) throws BbscsException;

	/**
	 *
	 * @param indexStatus
	 *            int
	 * @return List
	 */
	public List findForumsByIndexStatus(int indexStatus);

	/**
	 *
	 * @param num
	 *            int
	 * @return List
	 */
	public List findForumsAllNew(int num);

	/**
	 *
	 * @param num
	 *            int
	 * @return List
	 */
	public List findForumsAllNewCache(int num);
	
	/**
	 *
	 * @param num
	 *            int
	 * @return List
	 */
	public List findForumsAllNewAttachFile(int num);
	/**
	 *
	 * @param num
	 *            int
	 * @return List
	 */
	public List findForumsAllNewLastPostTitle(int num);

	/**
	 *
	 * @param bid
	 *            long
	 * @param tagID
	 *            String
	 * @return PageList
	 */
	public PageList findForumsAll(long bid, String tagID, Pages pages);

	/**
	 *
	 * @param bid
	 *            long
	 * @param tagID
	 *            String
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForumsMainWWW(long bid, String tagID, Pages pages);

	/**
	 *
	 * @param bid
	 *            long
	 * @param tagID
	 *            String
	 * @param pages
	 *            Pages
	 * @return PageList
	 */
	public PageList findForumsMainLastRe(long bid, String tagID, Pages pages);

	/**
	 *
	 * @param bid
	 *            long
	 * @param postId
	 *            String
	 * @param f
	 *            Forum
	 * @param buyFromUi
	 *            UserInfo
	 * @throws BbscsException
	 */
	public void saveForumBuy(long bid, String postId, Forum f, UserInfo buyFromUi) throws BbscsException;

	/**
	 *
	 * @param postId
	 *            String
	 * @return int
	 */
	public long getForumBuyNumByPostId(String postId);

	/**
	 *
	 * @param postId
	 *            String
	 * @param fromId
	 *            String
	 * @return ForumBuy
	 */
	public ForumBuy findForumBuyByPostIdFromId(String postId, String fromId);

	public Forum saveEditForum(Forum forum) throws BbscsException;

	public void saveMoveForum(Forum forum, Board toboard) throws BbscsException;

	public void saveMoveForum(long frombid, String mainid, Board toboard) throws BbscsException;

	public String getForumDetail(Forum forum, boolean forcefromfile);
	
	public List findForumsAllNewClickForums(int num);
}
