package com.laoer.bbscs.service.imp;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.bean.Commend;
import com.laoer.bbscs.bean.Forum;
import com.laoer.bbscs.bean.ForumBuy;
import com.laoer.bbscs.bean.ForumHistory;
import com.laoer.bbscs.bean.Subscibe;
import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.bean.Vote;
import com.laoer.bbscs.bean.VoteItem;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.comm.OrderObj;
import com.laoer.bbscs.comm.SysQueue;
//import com.laoer.bbscs.comm.Util;
import com.laoer.bbscs.dao.BoardDAO;
import com.laoer.bbscs.dao.CommendDAO;
import com.laoer.bbscs.dao.ForumBuyDAO;
import com.laoer.bbscs.dao.ForumDAO;
import com.laoer.bbscs.dao.SubscibeDAO;
import com.laoer.bbscs.dao.UserInfoDAO;
import com.laoer.bbscs.dao.VoteDAO;
import com.laoer.bbscs.dao.VoteItemDAO;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.fio.CommendFileIO;
import com.laoer.bbscs.fio.ForumUploadFile;
import com.laoer.bbscs.fio.UploadFile;
import com.laoer.bbscs.fio.UserInfoFileIO;
import com.laoer.bbscs.service.Cache;
import com.laoer.bbscs.service.ForumService;
import com.laoer.bbscs.service.SubscibeFactory;
import com.laoer.bbscs.service.config.ForumConfig;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.service.web.PageList;
import com.laoer.bbscs.service.web.Pages;

public class ForumServiceImp implements ForumService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ForumServiceImp.class);

	private ForumDAO forumDAO;

	private UserInfoDAO userInfoDAO;

	private ForumUploadFile forumUploadFile;

	private Cache sysListObjCache;

	private SysConfig sysConfig;

	private UserInfoFileIO userInfoFileIO;

	private SubscibeFactory subscibeFactory;

	private SubscibeDAO subscibeDAO;

	private BoardDAO boardDAO;

	private SysQueue subscibeQueue;

	private ForumBuyDAO forumBuyDAO;

	private VoteDAO voteDAO;

	private VoteItemDAO voteItemDAO;

	private CommendDAO commendDAO;

	private CommendFileIO commendFileIO;

	private ForumDAO forumHistoryDAO;

	private ForumConfig forumConfig;

	private Cache postCache;

	public ForumDAO getForumDAO() {
		return forumDAO;
	}

	public void setForumDAO(ForumDAO forumDAO) {
		this.forumDAO = forumDAO;
	}

	public UserInfoDAO getUserInfoDAO() {
		return userInfoDAO;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

	public ForumUploadFile getForumUploadFile() {
		return forumUploadFile;
	}

	public void setForumUploadFile(ForumUploadFile forumUploadFile) {
		this.forumUploadFile = forumUploadFile;
	}

	public Cache getSysListObjCache() {
		return sysListObjCache;
	}

	public void setSysListObjCache(Cache sysListObjCache) {
		this.sysListObjCache = sysListObjCache;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public SubscibeDAO getSubscibeDAO() {
		return subscibeDAO;
	}

	public void setSubscibeDAO(SubscibeDAO subscibeDAO) {
		this.subscibeDAO = subscibeDAO;
	}

	public SubscibeFactory getSubscibeFactory() {
		return subscibeFactory;
	}

	public void setSubscibeFactory(SubscibeFactory subscibeFactory) {
		this.subscibeFactory = subscibeFactory;
	}

	public UserInfoFileIO getUserInfoFileIO() {
		return userInfoFileIO;
	}

	public void setUserInfoFileIO(UserInfoFileIO userInfoFileIO) {
		this.userInfoFileIO = userInfoFileIO;
	}

	public BoardDAO getBoardDAO() {
		return boardDAO;
	}

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	public SysQueue getSubscibeQueue() {
		return subscibeQueue;
	}

	public void setSubscibeQueue(SysQueue subscibeQueue) {
		this.subscibeQueue = subscibeQueue;
	}

	public ForumBuyDAO getForumBuyDAO() {
		return forumBuyDAO;
	}

	public void setForumBuyDAO(ForumBuyDAO forumBuyDAO) {
		this.forumBuyDAO = forumBuyDAO;
	}

	public VoteDAO getVoteDAO() {
		return voteDAO;
	}

	public void setVoteDAO(VoteDAO voteDAO) {
		this.voteDAO = voteDAO;
	}

	public VoteItemDAO getVoteItemDAO() {
		return voteItemDAO;
	}

	public void setVoteItemDAO(VoteItemDAO voteItemDAO) {
		this.voteItemDAO = voteItemDAO;
	}

	public CommendDAO getCommendDAO() {
		return commendDAO;
	}

	public void setCommendDAO(CommendDAO commendDAO) {
		this.commendDAO = commendDAO;
	}

	public CommendFileIO getCommendFileIO() {
		return commendFileIO;
	}

	public void setCommendFileIO(CommendFileIO commendFileIO) {
		this.commendFileIO = commendFileIO;
	}

	public ForumDAO getForumHistoryDAO() {
		return forumHistoryDAO;
	}

	public void setForumHistoryDAO(ForumDAO forumHistoryDAO) {
		this.forumHistoryDAO = forumHistoryDAO;
	}

	public ForumConfig getForumConfig() {
		return forumConfig;
	}

	public void setForumConfig(ForumConfig forumConfig) {
		this.forumConfig = forumConfig;
	}

	public Cache getPostCache() {
		return postCache;
	}

	public void setPostCache(Cache postCache) {
		this.postCache = postCache;
	}

	public void createCommendPage(long commend) throws BbscsException {
		// TODO 自动生成方法存根

	}

	public Forum createForum(Forum forum) throws BbscsException {
		Board board = this.getBoardDAO().getBoardByID(forum.getBoardID());
		UserInfo ui = this.getUserInfoDAO().findUserInfoById(forum.getUserID());
		return createForum(forum, board, ui, null);
	}

	@SuppressWarnings("unchecked")
	public Forum createForum(Forum forum, Board board, UserInfo ui, UploadFile uploadFile) throws BbscsException {
		try {
			if (Constant.POST_STORAGE_MODE == 0) {
				forum = this.getForumDAO().saveOrUpdateForum(forum);
			} else {
				String detail = forum.getDetail();
				forum = this.getForumDAO().saveOrUpdateForum(forum);

				String postFileName = "P_" + forum.getBoardID() + "_" + forum.getId() + ".txt";
				File postFile = new File(this.getForumConfig().getForumPath(forum.getBoardID(), forum.getPostTime())
						+ postFileName);
				FileUtils.writeStringToFile(postFile, detail, Constant.CHARSET);
				forum.setDetail(postFileName);
			}

			if (uploadFile != null) {
				String fileName = "File_" + forum.getId() + "_" + System.currentTimeMillis() + "."
						+ FilenameUtils.getExtension(uploadFile.getFileName());
				String toFilePath = BBSCSUtil.getUpFilePath(forum.getBoardID(), forum.getPostTime());
				this.getForumUploadFile().saveUploadFile(toFilePath + fileName, uploadFile, this.getSysConfig());
				forum.setHaveAttachFile(1);
				forum.getAttachFileName().add(fileName);
				if (board.getAuditAttach() == 1) {
					forum.setAuditingAttachFile(1);
				}
			}
			forum.setMainID(forum.getId());
			forum = this.getForumDAO().saveOrUpdateForum(forum);
			//if (board.getAuditPost() == 0 && board.getAddUserPostNum() == 1) { // 不需要审核,并且版区为增加用户发帖数量
			if (forum.getAuditing() == 0 && board.getAddUserPostNum() == 1) { // 不需要审核,并且版区为增加用户发帖数量
				ui.setArticleNum(ui.getArticleNum() + 1);
				ui.setExperience(ui.getExperience() + 2); // 发帖增加经验值2点。add at
				// 2007.2.23
				ui = this.getUserInfoDAO().saveUserInfo(ui);
				this.getUserInfoFileIO().writeUserFile(ui);
			}
			if (forum.getEmailInform() != 0 || forum.getMsgInform() != 0) {
				if (this.getSubscibeDAO().findSubscibeByPostID(forum.getId(), ui.getId(), forum.getBoardID()) == null) {
					Subscibe subs = this.getSubscibeFactory().getInstance(forum.getBoardID());
					subs.setBoardID(forum.getBoardID());
					subs.setCreateTime(new Date());
					subs.setEmailinform(forum.getEmailInform());
					subs.setMsginform(forum.getMsgInform());
					subs.setNickName(ui.getNickName());
					subs.setPostID(forum.getId());
					subs.setPostTitle(forum.getTitle());
					subs.setUserEmail(ui.getEmail());
					subs.setUserID(ui.getId());
					subs.setUserName(ui.getUserName());
					subs.setUserLocale(ui.getUserLocale());
					this.getSubscibeDAO().saveSubscibe(subs);
				}
			}

			this.getSysListObjCache().remove(Constant.FORUM_NEW_CACHE_NAME);
			return forum;
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	@SuppressWarnings("unchecked")
	public Forum createForumUpFile(Forum forum, UploadFile uploadFile) throws BbscsException {
		try {
			if (uploadFile != null) {
				String fileName = "File_" + forum.getId() + "_" + System.currentTimeMillis() + "."
						+ FilenameUtils.getExtension(uploadFile.getFileName());
				String toFilePath = BBSCSUtil.getUpFilePath(forum.getBoardID(), forum.getPostTime());
				this.getForumUploadFile().saveUploadFile(toFilePath + fileName, uploadFile, this.getSysConfig());
				List attachFiles = new ArrayList();
				attachFiles.addAll(forum.getAttachFileName());
				attachFiles.add(fileName);
				forum.setAttachFileName(attachFiles);
				if (forum.getHaveAttachFile() == 0) {
					forum.setHaveAttachFile(1);
				}

				forum = this.getForumDAO().saveOrUpdateForum(forum);
			}
			return forum;
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	@SuppressWarnings("unchecked")
	public Forum createReForum(Forum forum, Forum mainForum, Board board, UserInfo ui, UploadFile uploadFile,
			boolean isQuote) throws BbscsException {
		try {
			String detail = forum.getDetail();
			//if (board.getAuditPost() == 0) { // 不需要审核
			if (forum.getAuditing() == 0) { // 不需要审核
				mainForum.setLastPostNickName(forum.getNickName());
				mainForum.setLastPostTitle(forum.getTitle());
				mainForum.setLastPostUserName(forum.getUserName());
				mainForum.setLastTime(forum.getPostTime());
				mainForum.setReNum(mainForum.getReNum() + 1);
				mainForum.setIsAnonymousReply(forum.getIsAnonymousReply());
				if (forum.getParentID().equals(forum.getMainID())) { // 回复的是主帖
					if (mainForum.getCanNotRe() == 0) {
						mainForum.setCanNotRe(1);
					}
					if (isQuote) {
						forum.setQuoteEditType(mainForum.getEditType());
						if (this.getSysConfig().getQuoteMaxSize() > 0) {
							forum.setQuoteText(BBSCSUtil.getSpeShortString(this.getForumDetail(mainForum, false), this
									.getSysConfig().getQuoteMaxSize(), "..."));
						} else {
							forum.setQuoteText(this.getForumDetail(mainForum, false));
						}
					}
				}
			} else {
				//mainForum.setLastPostTitle(forum.getTitle()); change at 2007.9.7
				//mainForum.setLastTime(forum.getPostTime()); change at 2007.9.7
				if (forum.getParentID().equals(forum.getMainID())) { // 回复的是主帖
					if (isQuote) {
						forum.setQuoteEditType(mainForum.getEditType());
						if (this.getSysConfig().getQuoteMaxSize() > 0) {
							forum.setQuoteText(BBSCSUtil.getSpeShortString(this.getForumDetail(mainForum, false), this
									.getSysConfig().getQuoteMaxSize(), "..."));
						} else {
							forum.setQuoteText(this.getForumDetail(mainForum, false));
						}
					}
				}
			}
			mainForum = this.getForumDAO().saveOrUpdateForum(mainForum);
			if (mainForum.getReNum() == this.getSysConfig().getForumHotRes()) { // 回复次数达到热贴标准,增加发帖人人缘系数
				UserInfo mui = this.getUserInfoDAO().findUserInfoById(mainForum.getUserID());
				if (mui != null) {
					mui.setUserKnow(mui.getUserKnow() + 1);
					this.getUserInfoDAO().saveUserInfo(mui);
					this.getUserInfoFileIO().writeUserFile(mui);
				}
			}
			Forum reForum = mainForum;
			if (!forum.getParentID().equals(forum.getMainID())) { // 回复的不是主帖
				reForum = this.getForumDAO().findForumByID(forum.getParentID(), forum.getBoardID());
				if (reForum != null) {
					if (reForum.getCanNotRe() == 0) {
						reForum.setCanNotRe(1);
						reForum = this.getForumDAO().saveOrUpdateForum(reForum);
					}
					if (isQuote) {
						forum.setQuoteEditType(reForum.getEditType());
						String reDetail = this.getForumDetail(reForum, false);
						if (this.getSysConfig().getQuoteMaxSize() > 0) {
							forum.setQuoteText(BBSCSUtil.getSpeShortString(reDetail, this.getSysConfig()
									.getQuoteMaxSize(), "..."));
						} else {
							forum.setQuoteText(reDetail);
						}
					}
				}
			}

			// 处理文章标题
			if (forum.getTitle().equalsIgnoreCase(Constant.RE)) {
				if (reForum != null) {
					if (reForum.getTitle().startsWith(Constant.RE)) {
						forum.setTitle(reForum.getTitle());
					} else {
						if (BBSCSUtil.getSysCharsetStrLength(Constant.RE + reForum.getTitle()) > 90) {
							forum.setTitle(reForum.getTitle());
						} else {
							forum.setTitle(Constant.RE + reForum.getTitle());
						}
					}
				}
			}

			forum = this.getForumDAO().saveOrUpdateForum(forum);
			if (Constant.POST_STORAGE_MODE == 0) {
				if (uploadFile != null) {
					String fileName = "File_" + forum.getId() + "_" + System.currentTimeMillis() + "."
							+ FilenameUtils.getExtension(uploadFile.getFileName());
					String toFilePath = BBSCSUtil.getUpFilePath(forum.getBoardID(), forum.getPostTime());
					this.getForumUploadFile().saveUploadFile(toFilePath + fileName, uploadFile, this.getSysConfig());
					forum.setHaveAttachFile(1);
					forum.getAttachFileName().add(fileName);
					forum = this.getForumDAO().saveOrUpdateForum(forum);
				}
			} else {
				if (uploadFile != null) {
					String fileName = "File_" + forum.getId() + "_" + System.currentTimeMillis() + "."
							+ FilenameUtils.getExtension(uploadFile.getFileName());
					String toFilePath = BBSCSUtil.getUpFilePath(forum.getBoardID(), forum.getPostTime());
					this.getForumUploadFile().saveUploadFile(toFilePath + fileName, uploadFile, this.getSysConfig());
					forum.setHaveAttachFile(1);
					forum.getAttachFileName().add(fileName);

				}

				String postFileName = "P_" + forum.getBoardID() + "_" + forum.getId() + ".txt";
				File postFile = new File(this.getForumConfig().getForumPath(forum.getBoardID(), forum.getPostTime())
						+ postFileName);
				FileUtils.writeStringToFile(postFile, detail, Constant.CHARSET);
				forum.setDetail(postFileName);

				forum = this.getForumDAO().saveOrUpdateForum(forum);
			}

			//if (board.getAuditPost() == 0 && board.getAddUserPostNum() == 1) { // 不需要审核,并且版区为增加用户发帖数量
			if (forum.getAuditing() == 0 && board.getAddUserPostNum() == 1) { // 不需要审核,并且版区为增加用户发帖数量
				ui.setArticleNum(ui.getArticleNum() + 1);
				ui.setExperience(ui.getExperience() + 1); // 回帖增加经验值1点。add at
				// 2007.2.23
				ui = this.getUserInfoDAO().saveUserInfo(ui);
				this.getUserInfoFileIO().writeUserFile(ui);
			}

			if (forum.getEmailInform() != 0 || forum.getMsgInform() != 0) {
				Subscibe subs = this.getSubscibeFactory().getInstance(forum.getBoardID());
				subs.setBoardID(forum.getBoardID());
				subs.setCreateTime(new Date());
				subs.setEmailinform(forum.getEmailInform());
				subs.setMsginform(forum.getMsgInform());
				subs.setNickName(ui.getNickName());
				subs.setPostID(forum.getId());
				subs.setPostTitle(forum.getTitle());
				subs.setUserEmail(ui.getEmail());
				subs.setUserID(ui.getId());
				subs.setUserName(ui.getUserName());
				subs.setUserLocale(ui.getUserLocale());
				this.getSubscibeDAO().saveSubscibe(subs);
			}
			this.getSubscibeQueue().add(forum);

			return forum;
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public Forum createVoteForum(Forum forum, Board board, Vote vote, UserInfo ui, String voteItem)
			throws BbscsException {
		try {
			// String detail = forum.getDetail();
			// forum.setDetail("");
			String[] details = voteItem.split("\n");

			vote = this.getVoteDAO().saveVote(vote);
			for (int i = 0; i < details.length; i++) {
				VoteItem vi = new VoteItem();
				vi.setItem(details[i]);
				vi.setItemValue(0);
				vi.setVoteID(vote.getId());
				this.getVoteItemDAO().saveVoteItem(vi);
			}
			forum.setVoteID(vote.getId());
			forum = this.getForumDAO().saveOrUpdateForum(forum);

			forum.setMainID(forum.getId());
			forum = this.getForumDAO().saveOrUpdateForum(forum);
			if (board.getAuditPost() == 0 && board.getAddUserPostNum() == 1) { // 增加用户发帖数量
				ui.setArticleNum(ui.getArticleNum() + 1);
				ui = this.getUserInfoDAO().saveUserInfo(ui);
				this.getUserInfoFileIO().writeUserFile(ui);
			}
			if (forum.getEmailInform() != 0 || forum.getMsgInform() != 0) {
				if (this.getSubscibeDAO().findSubscibeByPostID(forum.getId(), ui.getId(), forum.getBoardID()) == null) {
					Subscibe subs = this.getSubscibeFactory().getInstance(forum.getBoardID());
					subs.setBoardID(forum.getBoardID());
					subs.setCreateTime(new Date());
					subs.setEmailinform(forum.getEmailInform());
					subs.setMsginform(forum.getMsgInform());
					subs.setNickName(ui.getNickName());
					subs.setPostID(forum.getId());
					subs.setPostTitle(forum.getTitle());
					subs.setUserEmail(ui.getEmail());
					subs.setUserID(ui.getId());
					subs.setUserName(ui.getUserName());
					this.getSubscibeDAO().saveSubscibe(subs);
				}
			}
			this.getSysListObjCache().remove(Constant.FORUM_NEW_CACHE_NAME);
			return forum;
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public void delForumsNotAuditingAttachFile(List ids) throws BbscsException {
		List forums = this.getForumDAO().findForumsInIds(ids);
		for (int i = 0; i < forums.size(); i++) {
			Forum forum = (Forum) forums.get(i);
			if (forum.getHaveAttachFile() == 1) {
				List fl = forum.getAttachFileName();
				String filePath = BBSCSUtil.getUpFilePath(forum.getBoardID(), forum.getPostTime());
				try {
					for (int j = 0; j < fl.size(); j++) {
						File attachFile = new File(filePath + (String) fl.get(j));
						if (attachFile.exists()) {
							FileUtils.forceDelete(attachFile);
						}
						File attachFileSmall = new File(filePath + (String) fl.get(j) + Constant.IMG_SMALL_FILEPREFIX);
						if (attachFileSmall.exists()) {
							FileUtils.forceDelete(attachFileSmall);
						}
					}
					fl.clear();
					forum.setAttachFileName(fl);
					forum.setAuditingAttachFile(0);
					forum.setHaveAttachFile(0);
					this.getForumDAO().saveOrUpdateForum(forum);
				} catch (Exception ex) {
					logger.error(ex);
					throw new BbscsException(ex);
				}
			}
		}
	}

	public void delPostReal(Forum forum) throws BbscsException {
		try {
			/*
			 * if (forum.getHaveAttachFile() == 1 && forum.getAttachFileName() !=
			 * null && forum.getAttachFileName().size() > 0) { List fl =
			 * forum.getAttachFileName(); String filePath =
			 * BBSCSUtil.getUpFilePath(forum.getBoardID(), forum.getPostTime());
			 * for (int i = 0; i < fl.size(); i++) { File attachFile = new
			 * File(filePath + (String) fl.get(i)); if (attachFile.exists()) {
			 * FileUtils.forceDelete(attachFile); } File attachFileSmall = new
			 * File(filePath + (String) fl.get(i) +
			 * Constant.IMG_SMALL_FILEPREFIX); if (attachFileSmall.exists()) {
			 * FileUtils.forceDelete(attachFileSmall); } } }
			 */
			if (Constant.POST_STORAGE_MODE == 1) {
				this.getForumUploadFile().delDetailFile(forum);
			}
			this.getForumUploadFile().delUploadFile(forum);
			this.getForumDAO().removeForum(forum);
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public void delPosts(List forums, Board board) throws BbscsException {
		for (int i = 0; i < forums.size(); i++) {
			try {
				Forum forum = (Forum) forums.get(i);
				if (forum.getAuditing() == 0) { // 如果已近是通过审核的帖子
					forum = this.getForumDAO().saveOrUpdateForum(forum); // 保存标注删除，包括删除人、时间等信息
					if (forum.getIsNew() != 1) { // 如果不是主帖
						Forum mainForum = this.getForumDAO().findForumByID(forum.getMainID(), forum.getBoardID()); // 取主帖
						if (mainForum != null) {
							mainForum.setReNum(mainForum.getReNum() - 1); // 减少主帖回复数
							if (mainForum.getLastTime() == forum.getPostTime()) { // 如果删除的是最后一个回复帖，要修正主帖表中最后回复人和时间
								OrderObj[] oo = { new OrderObj("postTime", Constant.ORDER_DESC) };
								List l = this.getForumDAO().findForumsTopic(mainForum.getBoardID(), mainForum.getId(),
										0, 0, oo, 0, 1);
								if (l != null && !l.isEmpty()) {
									Forum lastF = (Forum) l.get(0);
									mainForum.setLastPostNickName(lastF.getNickName());
									mainForum.setLastPostTitle(lastF.getTitle());
									mainForum.setLastPostUserName(lastF.getUserName());
									mainForum.setLastTime(lastF.getPostTime());
								}
							}
							this.getForumDAO().saveOrUpdateForum(mainForum); // 保存主帖
						}
					}
					UserInfo ui = this.getUserInfoDAO().findUserInfoById(forum.getUserID());
					if (board.getAddUserPostNum() == 1 && ui != null) { // 版区为增加用户发帖数量
						if (ui.getArticleNum() > 0) {
							ui.setArticleNum(ui.getArticleNum() - 1); // 减少用户发帖数
							ui = this.getUserInfoDAO().saveUserInfo(ui);
							this.getUserInfoFileIO().writeUserFile(ui);
						}

						if (forum.getIsNew() == 1) {
							ui.setExperience(ui.getExperience() - 2); // 主帖，扣除发帖人2点经验值
						} else {
							ui.setExperience(ui.getExperience() - 1); // 回帖，扣除发帖人1点经验值
						}
					}
					if (forum.getElite() != 0 || forum.getCommend() != 0) { // 如果是精华或推荐
						if (forum.getElite() != 0) {
							if (ui.getArticleEliteNum() >= 1) {
								ui.setArticleEliteNum(ui.getArticleEliteNum() - 1);
							} else {
								ui.setArticleEliteNum(0);
							}
							ui.setLiterary(ui.getLiterary() - 3);
						}
						if (forum.getCommend() != 0) {
							ui.setLiterary(ui.getLiterary() - 1);
							this.getCommendDAO().removeCommend(forum.getId());
						}

						ui = this.getUserInfoDAO().saveUserInfo(ui);
						this.getUserInfoFileIO().writeUserFile(ui);
					}

					if (forum.getIsNew() == 1) { // 如果是主帖，尝试从新帖Cache中清除
						this.getSysListObjCache().remove(Constant.FORUM_NEW_CACHE_NAME);
					}
				} else { // 如果是尚未审核
					forum = this.getForumDAO().saveOrUpdateForum(forum); // 保存标注删除，包括删除人、时间等信息
				}
			} catch (Exception ex) {
				logger.error(ex);
				throw new BbscsException(ex);
			}
		}
	}

	public void delPostsReal(List ids) throws BbscsException {
		List forums = this.getForumDAO().findForumsInIds(ids);
		for (int i = 0; i < forums.size(); i++) {
			Forum forum = (Forum) forums.get(i);
			if ((System.currentTimeMillis() - forum.getDelTime()) > 7 * 24 * 3600 * 1000) {
				try {
					/*
					 * if (forum.getHaveAttachFile() == 1 &&
					 * forum.getAttachFileName() != null &&
					 * forum.getAttachFileName().size() > 0) { List fl =
					 * forum.getAttachFileName(); String filePath =
					 * BBSCSUtil.getUpFilePath(forum.getBoardID(),
					 * forum.getPostTime()); for (int j = 0; j < fl.size(); j++) {
					 * File attachFile = new File(filePath + (String)
					 * fl.get(j)); if (attachFile.exists()) {
					 * FileUtils.forceDelete(attachFile); } File attachFileSmall =
					 * new File(filePath + (String) fl.get(j) +
					 * Constant.IMG_SMALL_FILEPREFIX); if
					 * (attachFileSmall.exists()) {
					 * FileUtils.forceDelete(attachFileSmall); } } }
					 */
					if (Constant.POST_STORAGE_MODE == 1) {
						this.getForumUploadFile().delDetailFile(forum);
					}
					this.getForumUploadFile().delUploadFile(forum);
					this.getForumDAO().removeForum(forum);
				} catch (Exception ex) {
					logger.error(ex);
					throw new BbscsException(ex);
				}
			}
		}
	}

	public void delWastePost(long bid) throws BbscsException {
		long atime = System.currentTimeMillis() - 7 * 24 * 3600 * 1000;
		List forums = this.getForumDAO().findForumsRealDelAll(bid, atime);
		for (int i = 0; i < forums.size(); i++) {
			Forum forum = (Forum) forums.get(i);
			try {
				/*
				 * if (forum.getHaveAttachFile() == 1 &&
				 * forum.getAttachFileName() != null &&
				 * forum.getAttachFileName().size() > 0) { List fl =
				 * forum.getAttachFileName(); String filePath =
				 * BBSCSUtil.getUpFilePath(forum.getBoardID(),
				 * forum.getPostTime()); for (int j = 0; j < fl.size(); j++) {
				 * File attachFile = new File(filePath + (String) fl.get(j)); if
				 * (attachFile.exists()) { FileUtils.forceDelete(attachFile); }
				 * File attachFileSmall = new File(filePath + (String) fl.get(j) +
				 * Constant.IMG_SMALL_FILEPREFIX); if (attachFileSmall.exists()) {
				 * FileUtils.forceDelete(attachFileSmall); } } }
				 */
				this.getForumUploadFile().delUploadFile(forum);
				this.getForumDAO().removeForum(forum);
			} catch (Exception ex) {
				logger.error(ex);
				throw new BbscsException(ex);
			}
		}
	}

	public void delaPost(Forum forum, Board board, UserInfo ui) throws BbscsException {
		try {
			forum = this.getForumDAO().saveOrUpdateForum(forum);
			if (forum.getIsNew() != 1) {
				Forum mainForum = this.getForumDAO().findForumByID(forum.getMainID(), forum.getBoardID());
				if (mainForum != null) {
					mainForum.setReNum(mainForum.getReNum() - 1);
					if (mainForum.getLastTime() == forum.getPostTime()) {
						OrderObj[] oo = { new OrderObj("postTime", Constant.ORDER_DESC) };
						List l = this.getForumDAO().findForumsTopic(mainForum.getBoardID(), mainForum.getId(), 0, 0,
								oo, 0, 1);

						if (l != null && !l.isEmpty()) {
							Forum lastF = (Forum) l.get(0);
							mainForum.setLastPostNickName(lastF.getNickName());
							mainForum.setLastPostTitle(lastF.getTitle());
							mainForum.setLastPostUserName(lastF.getUserName());
							mainForum.setLastTime(lastF.getPostTime());
						}
					}
					this.getForumDAO().saveOrUpdateForum(mainForum);
				}
			}
			if (board.getAddUserPostNum() == 1 && ui != null) { // 版区为增加用户发帖数量
				if (ui.getArticleNum() > 0) {
					ui.setArticleNum(ui.getArticleNum() - 1);
					ui = this.getUserInfoDAO().saveUserInfo(ui);
					this.getUserInfoFileIO().writeUserFile(ui);
				}

				if (forum.getIsNew() == 1) {
					ui.setExperience(ui.getExperience() - 2); // 主帖，扣除发帖人2点经验值
				} else {
					ui.setExperience(ui.getExperience() - 1); // 回帖，扣除发帖人1点经验值
				}
			}
			if (forum.getElite() != 0 || forum.getCommend() != 0) {
				if (forum.getElite() != 0) {
					if (ui.getArticleEliteNum() >= 1) {
						ui.setArticleEliteNum(ui.getArticleEliteNum() - 1);
					} else {
						ui.setArticleEliteNum(0);
					}
					ui.setLiterary(ui.getLiterary() - 3);
				}
				if (forum.getCommend() != 0) {
					ui.setLiterary(ui.getLiterary() - 1);
					this.getCommendDAO().removeCommend(forum.getId());
				}

				ui = this.getUserInfoDAO().saveUserInfo(ui);
				this.getUserInfoFileIO().writeUserFile(ui);
			}
			if (forum.getIsNew() == 1) {
				this.getSysListObjCache().remove(Constant.FORUM_NEW_CACHE_NAME);
			}
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}

	}

	public Forum editForum(Forum forum) throws BbscsException {

		return null;
	}

	public ForumBuy findForumBuyByPostIdFromId(String postId, String fromId) {
		return this.getForumBuyDAO().findForumBuyByPostIdFromId(postId, fromId);
	}

	public Forum findForumByID(String id) {
		return this.getForumDAO().findForumByID(id);
	}

	public Forum findForumByID(String id, long bid) {
		return this.getForumDAO().findForumByID(id, bid);
	}

	public List findForums(long bid, int isNew, int delSign, int auditing, OrderObj[] oo) {
		return this.getForumDAO().findForums(bid, isNew, delSign, auditing, oo);
	}

	public PageList findForums(long bid, int isNew, int delSign, int auditing, int auditingAttachFile, OrderObj[] oo,
			Pages pages) {
		PageList pl = new PageList();
		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(this.getForumDAO().getForumNum(bid, isNew, delSign, auditing, auditingAttachFile));
		}
		pages.executeCount();

		List l = this.getForumDAO().findForums(bid, isNew, delSign, auditing, auditingAttachFile, oo, pages.getSpage(),
				pages.getPerPageNum());
		pl.setObjectList(l);
		pl.setPages(pages);
		return pl;
	}

	public PageList findForumsAll(long bid, Pages pages) {
		OrderObj oo0 = new OrderObj("isTop", Constant.ORDER_DESC);
		OrderObj oo1 = new OrderObj("lastTime", Constant.ORDER_DESC);
		OrderObj[] oo = { oo0, oo1 };
		return this.findForums(bid, -1, 0, 0, -1, oo, pages);
	}

	public PageList findForumsAll(long bid, String tagID, Pages pages) {
		OrderObj oo0 = new OrderObj("isTop", Constant.ORDER_DESC);
		OrderObj oo1 = new OrderObj("lastTime", Constant.ORDER_DESC);
		OrderObj[] oo = { oo0, oo1 };
		PageList pl = new PageList();
		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(this.getForumDAO().getForumNum(bid, tagID, -1, 0, 0));
		}

		pages.executeCount();
		List l = this.getForumDAO().findForums(bid, tagID, -1, 0, 0, oo, pages.getSpage(), pages.getPerPageNum());
		pl.setObjectList(l);
		pl.setPages(pages);
		return pl;
	}

	public PageList findForumsAllManage(long bid, Pages pages) {
		OrderObj oo0 = new OrderObj("postTime", Constant.ORDER_DESC);
		OrderObj[] oo = { oo0 };
		return this.findForums(bid, -1, 0, 0, -1, oo, pages);
	}

	public List findForumsAllNew(int num) {
		OrderObj[] oo = { new OrderObj("postTime", Constant.ORDER_DESC) };
		return this.getForumDAO().findForums(-1, 1, 0, 0, -1, oo, 0, num);
	}

	public List findForumsAllNewCache(int num) {
		List l = (List) this.getSysListObjCache().get(Constant.FORUM_NEW_CACHE_NAME);
		if (l == null) {
			OrderObj[] oo = { new OrderObj("postTime", Constant.ORDER_DESC) };
			l = this.getForumDAO().findForums(-1, 1, 0, 0, -1, oo, 0, num);
			this.getSysListObjCache().add(Constant.FORUM_NEW_CACHE_NAME, l);
		}
		return l;
	}
	
	public List findForumsAllNewAttachFile(int num) {
		OrderObj[] oo = { new OrderObj("postTime", Constant.ORDER_DESC) };
		return this.getForumDAO().findForumsByAttachFile(-1, 1, 0, 0, -1, 1, oo, 0, num);
	}
	
	public List findForumsAllNewLastPostTitle(int num) {
		OrderObj[] oo = { new OrderObj("lastTime", Constant.ORDER_DESC) };
		return this.getForumDAO().findForums(-1, 1, 0, 0, -1, oo, 0, num);
	}
	
	public List findForumsAllNewClickForums(int num) {
		OrderObj[] oo = { new OrderObj("postTime", Constant.ORDER_DESC) };
		return this.getForumDAO().findAllForumsByClick(num,oo);
	}

	public PageList findForumsAuditing(long bid, Pages pages) {
		OrderObj[] oo = { new OrderObj("postTime", Constant.ORDER_DESC) };
		return this.findForums(bid, -1, 0, 1, -1, oo, pages);
	}

	public PageList findForumsAuditingAttachFile(long bid, Pages pages) {
		OrderObj[] oo = { new OrderObj("postTime", Constant.ORDER_DESC) };
		return this.findForums(bid, -1, 0, -1, 1, oo, pages);
	}

	public List findForumsByIndexStatus(int indexStatus) {
		return this.getForumDAO().findForumsByIndexStatus(indexStatus);
	}

	public PageList findForumsCommend(long bid, long commen, Pages pages) {
		PageList pl = new PageList();
		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(this.getForumNumCommend(bid, commen));
		}
		pages.executeCount();

		List l = this.getForumDAO().findForumsCommend(bid, commen, -1, 0, 0, "postTime", Constant.ORDER_DESC,
				pages.getSpage(), pages.getPerPageNum());
		pl.setObjectList(l);
		pl.setPages(pages);
		return pl;
	}

	public PageList findForumsCommend(long commend, Pages pages) {
		PageList pl = new PageList();
		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(this.getForumNumCommend(commend));
		}
		pages.executeCount();

		List l = this.getForumDAO().findForumsCommend(commend, -1, 0, 0, "postTime", Constant.ORDER_DESC,
				pages.getSpage(), pages.getPerPageNum());
		pl.setObjectList(l);
		pl.setPages(pages);
		return pl;
	}

	public PageList findForumsDel(long bid, Pages pages) {
		OrderObj[] oo = { new OrderObj("delTime", Constant.ORDER_DESC) };
		return this.findForums(bid, -1, 1, -1, -1, oo, pages);
	}

	public List findForumsElite(long bid, long elite, long eliteId) {
		return this.getForumDAO().findForumsElite(bid, elite, eliteId);
	}

	public PageList findForumsHotTopic(long bid, int reNum, int click, Pages pages) {
		PageList pl = new PageList();
		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(this.getForumNumHotTopic(bid, reNum, click));
		}
		pages.executeCount();

		List l = this.getForumDAO().findForumsHotTopic(bid, reNum, click, pages.getSpage(), pages.getPerPageNum());
		pl.setObjectList(l);
		pl.setPages(pages);
		return pl;
	}

	public List findForumsInIds(long bid, List ids) {
		return this.getForumDAO().findForumsInIds(bid, ids);
	}

	public List findForumsInIds(List ids) {
		return this.getForumDAO().findForumsInIds(ids);
	}

	public PageList findForumsMainLastRe(long bid, Pages pages) {
		OrderObj oo0 = new OrderObj("isTop", Constant.ORDER_DESC);
		OrderObj oo1 = new OrderObj("lastTime", Constant.ORDER_DESC);
		OrderObj[] oo = { oo0, oo1 };
		return this.findForums(bid, 1, 0, 0, -1, oo, pages);
	}

	public PageList findForumsMainLastRe(long bid, String tagID, Pages pages) {
		OrderObj oo0 = new OrderObj("isTop", Constant.ORDER_DESC);
		OrderObj oo1 = new OrderObj("lastTime", Constant.ORDER_DESC);
		OrderObj[] oo = { oo0, oo1 };
		PageList pl = new PageList();
		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(this.getForumDAO().getForumNum(bid, tagID, 1, 0, 0));
		}

		pages.executeCount();
		List l = this.getForumDAO().findForums(bid, tagID, 1, 0, 0, oo, pages.getSpage(), pages.getPerPageNum());
		pl.setObjectList(l);
		pl.setPages(pages);
		return pl;
	}

	public PageList findForumsMainWWW(long bid, Pages pages) {
		OrderObj oo0 = new OrderObj("isTop", Constant.ORDER_DESC);
		OrderObj oo1 = new OrderObj("postTime", Constant.ORDER_DESC);
		OrderObj[] oo = { oo0, oo1 };
		return this.findForums(bid, 1, 0, 0, -1, oo, pages);
	}

	public PageList findForumsMainWWW(long bid, String tagID, Pages pages) {
		OrderObj oo0 = new OrderObj("isTop", Constant.ORDER_DESC);
		OrderObj oo1 = new OrderObj("postTime", Constant.ORDER_DESC);
		OrderObj[] oo = { oo0, oo1 };
		PageList pl = new PageList();
		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(this.getForumDAO().getForumNum(bid, tagID, 1, 0, 0));
		}

		pages.executeCount();
		List l = this.getForumDAO().findForums(bid, tagID, 1, 0, 0, oo, pages.getSpage(), pages.getPerPageNum());
		pl.setObjectList(l);
		pl.setPages(pages);
		return pl;
	}

	public PageList findForumsOwner(String userID, int isNew, Pages pages) {
		PageList pl = new PageList();
		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(this.getForumOwnerNum(userID));
		}
		pages.executeCount();

		List l = this.getForumDAO().findForumsOwner(userID, isNew, 0, 0, "postTime", Constant.ORDER_DESC,
				pages.getSpage(), pages.getPerPageNum());
		pl.setObjectList(l);
		pl.setPages(pages);
		return pl;
	}

	public PageList findForumsOwner(long bid, String userID, String mainID, Pages pages) {
		PageList pl = new PageList();
		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(this.getForumOwnerNum(bid, userID, mainID));
		}
		// System.out.println(pages.getTotalNum());
		pages.executeCount();

		// System.out.println(pages.getTotalNum());

		List l = this.getForumDAO().findForumsInMainIDByUserID(bid, mainID, userID, -1, 0, 0, "postTime",
				Constant.ORDER_ASC, pages.getSpage(), pages.getPerPageNum());
		pl.setObjectList(l);
		pl.setPages(pages);
		return pl;
	}

	public PageList findForumsTopic(long bid, String mainID, int delSign, int auditing, OrderObj[] oo, Pages pages) {
		PageList pl = new PageList();
		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(this.getForumDAO().getForumTopicNum(bid, mainID, delSign, auditing));
		}
		pages.executeCount();

		List l = this.getForumDAO().findForumsTopic(bid, mainID, delSign, auditing, oo, pages.getSpage(),
				pages.getPerPageNum());
		pl.setObjectList(l);
		pl.setPages(pages);
		return pl;
	}

	public PageList findForumsTopic(long bid, String mainID, Pages pages) {
		OrderObj[] oo = { new OrderObj("postTime", Constant.ORDER_ASC) };
		return this.findForumsTopic(bid, mainID, 0, 0, oo, pages);
	}

	public List findForumsTopicAll(long bid, String mainID, int delSign, int auditing, OrderObj[] oo) {
		return this.getForumDAO().findForumsTopic(bid, mainID, delSign, auditing, -1, oo);
	}

	public List findForumsTopicAuditing(long bid, String mainID) {
		OrderObj[] oo = { new OrderObj("postTime", Constant.ORDER_ASC) };
		return this.getForumDAO().findForumsTopic(bid, mainID, 0, 1, -1, oo);
	}

	public List findForumsTopicAuditingAttachFile(long bid, String mainID) {
		OrderObj[] oo = { new OrderObj("postTime", Constant.ORDER_ASC) };
		return this.getForumDAO().findForumsTopic(bid, mainID, 0, -1, 1, oo);
	}

	public List findForumsTopicDel(long bid, String mainID) {
		OrderObj[] oo = { new OrderObj("postTime", Constant.ORDER_ASC) };
		return this.getForumDAO().findForumsTopic(bid, mainID, 1, -1, -1, oo);
	}

	public long getForumAuditingNum(long bid) {
		return this.getForumDAO().getForumNum(bid, -1, 0, 1, -1);
	}

	public long getForumBuyNumByPostId(String postId) {
		return this.getForumBuyDAO().getForumBuyNumByPostId(postId);
	}

	public long getForumDelNum(long bid) {
		return this.getForumDAO().getForumNum(bid, -1, 1, -1, -1);
	}

	public long getForumMainNum(long bid) {
		return this.getForumDAO().getForumNum(bid, 1, 0, 0, -1);
	}

	public long getForumNum(long bid, int isNew, int delSign, int auditing, int auditingAttachFile) {
		return this.getForumDAO().getForumNum(bid, isNew, delSign, auditing, auditingAttachFile);
	}

	public long getForumNum(long bid) {
		return this.getForumDAO().getForumNum(bid, -1, 0, 0, -1);
	}

	public long getForumNumCommend(long bid, long commen) {
		return this.getForumDAO().getForumNumCommend(bid, commen, -1, 0, 0);
	}

	public long getForumNumCommend(long commend) {
		return this.getForumDAO().getForumNumCommend(commend, -1, 0, 0);
	}

	public long getForumNumHotTopic(long bid, int reNum, int click) {
		return this.getForumDAO().getForumNumHotTopic(bid, reNum, click);
	}

	public long getForumOwnerNum(String userID) {
		return this.getForumDAO().getForumOwnerNum(userID, -1, 0, 0);
	}

	public long getForumOwnerNum(long bid, String userID, String mainID) {
		return this.getForumDAO().getForumNumInMainIDByUserID(bid, mainID, userID, -1, 0, 0);
	}

	public long getForumTopicAuditingNum(long bid, String mainID) {
		return this.getForumDAO().getForumTopicNum(bid, mainID, 0, 1);
	}

	public long getForumTopicDelNum(long bid, String mainID) {
		return this.getForumDAO().getForumTopicNum(bid, mainID, 1, -1);
	}

	public long getForumTopicNum(long bid, String mainID, int delSign, int auditing) {
		return this.getForumDAO().getForumTopicNum(bid, mainID, delSign, auditing);
	}

	public long getForumTopicNum(long bid, String mainID) {
		return this.getForumDAO().getForumTopicNum(bid, mainID, 0, 0);
	}

	public long getForumsAuditingAttachFileNum(long bid) {
		return this.getForumDAO().getForumNum(bid, -1, 0, -1, 1);
	}

	public PageList getSearchList(long bid, String con, String text, int delSign, int auditing, String orderby,
			int ascOrDesc, Pages pages) {
		PageList pl = new PageList();
		if (pages.getTotalNum() == -1) {
			pages.setTotalNum(this.getForumDAO().getSearchNum(bid, con, text, delSign, auditing));
		}
		pages.executeCount();

		List l = this.getForumDAO().getSearchList(bid, con, text, delSign, auditing, orderby, ascOrDesc,
				pages.getSpage(), pages.getPerPageNum());
		pl.setObjectList(l);
		pl.setPages(pages);
		return pl;
	}

	public PageList getSearchList(long bid, String con, String text, Pages pages) {
		return this.getSearchList(bid, con, text, 0, 0, "postTime", Constant.ORDER_DESC, pages);
	}

	public long getSearchNum(long bid, String con, String text, int delSign, int auditing) {
		return this.getForumDAO().getSearchNum(bid, con, text, delSign, auditing);
	}

	public long getSearchNum(long bid, String con, String text) {
		return this.getForumDAO().getSearchNum(bid, con, text, 0, 0);
	}

	public boolean isReedUser(long bid, String mainID, String userID) {
		long num = this.getForumDAO().getForumNumInMainIDByUserID(bid, mainID, userID, 0, 0, 0);
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void removeAllAttachFile(Forum forum) throws BbscsException {
		if (forum.getHaveAttachFile() == 1) {
			List fl = forum.getAttachFileName();
			// String filePath = BBSCSUtil.getUpFilePath(forum.getBoardID(),
			// forum.getPostTime());
			try {
				/*
				 * for (int i = 0; i < fl.size(); i++) { File attachFile = new
				 * File(filePath + (String) fl.get(i)); if (attachFile.exists()) {
				 * FileUtils.forceDelete(attachFile); } File attachFileSmall =
				 * new File(filePath + (String) fl.get(i) +
				 * Constant.IMG_SMALL_FILEPREFIX); if (attachFileSmall.exists()) {
				 * FileUtils.forceDelete(attachFileSmall); } }
				 */
				this.getForumUploadFile().delUploadFile(forum);
				fl.clear();
				forum.setAttachFileName(fl);
				forum.setAuditingAttachFile(0);
				forum.setHaveAttachFile(0);
				this.getForumDAO().saveOrUpdateForum(forum);
			} catch (Exception ex) {
				logger.error(ex);
				throw new BbscsException(ex);
			}
		}
	}

	public void removeAttachFile(Forum forum, List fileNames) throws BbscsException {
		// List fl = forum.getAttachFileName();
		// String filePath = BBSCSUtil.getUpFilePath(forum.getBoardID(),
		// forum.getPostTime());
		try {
			/*
			 * for (int i = 0; i < fileNames.size(); i++) { String fn = (String)
			 * fileNames.get(i); File attachFile = new File(filePath + fn); if
			 * (attachFile.exists()) { FileUtils.forceDelete(attachFile); } File
			 * attachFileSmall = new File(filePath + fn +
			 * Constant.IMG_SMALL_FILEPREFIX); if (attachFileSmall.exists()) {
			 * FileUtils.forceDelete(attachFileSmall); } fl.remove(fn); }
			 */
			List fl = this.getForumUploadFile().delUploadFile(forum, fileNames);
			forum.setAttachFileName(fl);
			this.getForumDAO().saveOrUpdateForum(forum);
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public void removeForum(String id, long bid) throws BbscsException {
		try {
			this.getForumDAO().removeForum(id, bid);
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public void removeForum(String id) throws BbscsException {
		try {
			this.getForumDAO().removeForum(id);
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}

	}

	public void removeForum(Forum forum) throws BbscsException {
		try {
			this.getForumDAO().removeForum(forum);
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public void removeToHistory(long atime) throws BbscsException {
		// String month = Util.formatDate(new Date(atime), "yyyy-MM");
		// if (!BBSCSUtil.ArchivesMonthInFile(month)) {
		// BBSCSUtil.writeMonthToFile(month);
		// }
		List ml = this.getForumDAO().findForumsToHistory(atime);
		for (int i = 0; i < ml.size(); i++) {
			Forum f = (Forum) ml.get(i);
			List fl = this.getForumDAO().findForumsTopic(f.getBoardID(), f.getId(), 0, 0, -1, null);
			for (int j = 0; j < fl.size(); j++) {
				Forum mf = (Forum) fl.get(j);
				Forum fh = new ForumHistory();
				try {
					BeanUtils.copyProperties(fh, mf);
					this.getForumHistoryDAO().saveForum(fh);
					this.getForumDAO().removeForum(mf);
				} catch (Exception ex) {
					logger.error(ex);
					throw new BbscsException(ex);
				}
			}
		}
	}

	public Forum saveEditForum(Forum forum) throws BbscsException {
		this.getSysListObjCache().remove(Constant.FORUM_NEW_CACHE_NAME);
		try {
			if (Constant.POST_STORAGE_MODE == 0) {
				return this.getForumDAO().saveOrUpdateForum(forum);
			} else {
				String detail = forum.getDetail();
				String postFileName = "P_" + forum.getBoardID() + "_" + forum.getId() + ".txt";
				File postFile = new File(this.getForumConfig().getForumPath(forum.getBoardID(), forum.getPostTime())
						+ postFileName);
				FileUtils.writeStringToFile(postFile, detail, Constant.CHARSET);
				forum.setDetail(postFileName);
				forum = this.getForumDAO().saveOrUpdateForum(forum);
				this.getPostCache().remove(forum.getId());
				return forum;
			}
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public Forum saveForum(Forum forum) throws BbscsException {
		try {
			return this.getForumDAO().saveForum(forum);
		} catch (Exception e) {
			logger.error(e);
			throw new BbscsException(e);
		}
	}

	public void saveForumBuy(long bid, String postId, Forum f, UserInfo buyFromUi) throws BbscsException {
		ForumBuy forumBuy = new ForumBuy();
		forumBuy.setPostID(postId);
		forumBuy.setBuyFromID(buyFromUi.getId());
		forumBuy.setBuyFromName(buyFromUi.getUserName());
		forumBuy.setBuyPrice(f.getIsHiddenValue());
		forumBuy.setBuyTime(System.currentTimeMillis());
		forumBuy.setBuyToID(f.getUserID());
		forumBuy.setBuyToName(f.getUserName());

		int income = (int) (f.getIsHiddenValue() * this.getSysConfig().getPostPriceTax());
		forumBuy.setBuyToUserIncome(income);
		buyFromUi.setCoin(buyFromUi.getCoin() - f.getIsHiddenValue());
		UserInfo ui = this.getUserInfoDAO().findUserInfoById(f.getUserID());
		ui.setCoin(ui.getCoin() + income);

		try {
			this.getForumBuyDAO().saveForumBuy(forumBuy);
			ui = this.getUserInfoDAO().saveUserInfo(ui);
			buyFromUi = this.getUserInfoDAO().saveUserInfo(buyFromUi);
			this.getUserInfoFileIO().writeUserFile(ui);
			this.getUserInfoFileIO().writeUserFile(buyFromUi);
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public void saveForumChangeUser(Forum forum, int[] titleType, int[] values) throws BbscsException {
		try {
			forum = this.getForumDAO().saveOrUpdateForum(forum);
			if (titleType != null && values != null && titleType.length == values.length) {
				UserInfo ui = this.getUserInfoDAO().findUserInfoById(forum.getUserID());
				if (ui != null) {
					for (int i = 0; i < titleType.length; i++) {
						switch (titleType[i]) {
						case 0:
							ui.setExperience(ui.getExperience() + values[i]);
						case 1:
							ui.setLiterary(ui.getLiterary() + values[i]);
						case 2:
							ui.setUserKnow(ui.getUserKnow() + values[i]);
						case 3:
							ui.setCoin(ui.getCoin() + values[i]);
						default:
							ui.setExperience(ui.getExperience() + values[i]);
						}
					}
					ui = this.getUserInfoDAO().saveUserInfo(ui);
					this.getUserInfoFileIO().writeUserFile(ui);
				}
			}
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public void saveForumChangeUser(Forum forum, int titleType, int values) throws BbscsException {
		try {
			forum = this.getForumDAO().saveOrUpdateForum(forum);
			UserInfo ui = this.getUserInfoDAO().findUserInfoById(forum.getUserID());
			if (ui != null) {
				switch (titleType) {
				case 0:
					ui.setExperience(ui.getExperience() + values);
				case 1:
					ui.setLiterary(ui.getLiterary() + values);
				case 2:
					ui.setUserKnow(ui.getUserKnow() + values);
				case 3:
					ui.setCoin(ui.getCoin() + values);
				default:
					ui.setExperience(ui.getExperience() + values);
				}
				ui = this.getUserInfoDAO().saveUserInfo(ui);
				this.getUserInfoFileIO().writeUserFile(ui);
			}
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public void saveForumCommend(int createOrDel, Board board, Forum f) throws BbscsException {
		long topbid = board.getTopBid();
		if (createOrDel == 0) {
			if (f.getCommend() == 0) {
				try {
					f.setCommend(topbid);
					f = this.getForumDAO().saveOrUpdateForum(f);
					UserInfo ui = this.getUserInfoDAO().findUserInfoById(f.getUserID());
					ui.setLiterary(ui.getLiterary() + 1);
					ui = this.getUserInfoDAO().saveUserInfo(ui);
					this.getUserInfoFileIO().writeUserFile(ui);
					Commend commend = this.getCommendDAO().findCommendByPostID(f.getId());
					if (commend == null) {
						commend = new Commend();
						commend.setBoardCategory("");
						commend.setBoardID(board.getId().longValue());
						commend.setBoardName(board.getBoardName());
						commend.setCommendBoardID(topbid);
						commend.setCommendTop(0);
						commend.setCreateTime(System.currentTimeMillis());
						commend.setPostID(f.getId());
						commend.setPostMainID(f.getMainID());
						commend.setTitle(f.getTitle());
						commend.setTopCategory("");
						commend.setUserID(f.getUserID());
						commend.setUserName(f.getUserName());
						this.getCommendDAO().saveCommend(commend);
					}
					List commendList = this.getCommendDAO().findCommendsByCommendBoardID(topbid, 0, 10);
					this.getCommendFileIO().saveCommendInReadPageFile(topbid, commendList);
				} catch (Exception ex1) {
					logger.error(ex1);
					throw new BbscsException(ex1);
				}
			}
		} else {
			if (f.getCommend() != 0) {
				try {
					f.setCommend(0);
					f = this.getForumDAO().saveOrUpdateForum(f);
					UserInfo ui = this.getUserInfoDAO().findUserInfoById(f.getUserID());
					ui.setLiterary(ui.getLiterary() - 1);
					ui = this.getUserInfoDAO().saveUserInfo(ui);
					this.getUserInfoFileIO().writeUserFile(ui);
					this.getCommendDAO().removeCommend(f.getId());
					List commendList = this.getCommendDAO().findCommendsByCommendBoardID(topbid, 0, 10);
					this.getCommendFileIO().saveCommendInReadPageFile(topbid, commendList);
				} catch (Exception ex2) {
					logger.error(ex2);
					throw new BbscsException(ex2);
				}
			}
		}
		// -------------
		this.getSysListObjCache().remove(Constant.COMMEND_CACHE_NAME);
		// -------------
	}

	public void saveForums(List forums) throws BbscsException {
		for (int i = 0; i < forums.size(); i++) {
			Forum forum = (Forum) forums.get(i);
			try {
				this.getForumDAO().saveOrUpdateForum(forum);
			} catch (Exception ex) {
				logger.error(ex);
				throw new BbscsException(ex);
			}
		}
	}

	public void saveForumsAuditing(List ids, Board board) throws BbscsException {
		List forums = this.getForumDAO().findForumsInIds(ids);
		for (int i = 0; i < forums.size(); i++) {
			Forum forum = (Forum) forums.get(i);
			forum.setAuditing(0);
			if (forum.getIndexStatus() == Constant.INDEX_STATUS_AUDIT) {
				forum.setIndexStatus(Constant.INDEX_STATUS_NO_INDEX);
			}

			try {
				forum = this.getForumDAO().saveOrUpdateForum(forum);
				if (!forum.getId().equals(forum.getMainID())) { // 不是主帖
					Forum mainForum = this.getForumDAO().findForumByID(forum.getMainID(), forum.getBoardID()); // 取得主帖
					if (mainForum != null) {
						mainForum.setReNum(mainForum.getReNum() + 1); // 增加主帖回复数
						mainForum.setLastPostNickName(forum.getNickName());
						mainForum.setLastPostUserName(forum.getUserName());
						mainForum.setLastPostTitle(forum.getTitle());
						mainForum.setLastTime(forum.getPostTime());

						if (forum.getParentID().equals(forum.getMainID())) { // 回复的是主帖
							if (mainForum.getCanNotRe() == 0) {
								mainForum.setCanNotRe(1); // 置主帖为不可自己删除和修改
							}
						}

						this.getForumDAO().saveOrUpdateForum(mainForum);
					}
					Forum pforum = this.getForumDAO().findForumByID(forum.getParentID(), forum.getBoardID());
					if (pforum != null) {
						if (pforum.getCanNotRe() == 0) {
							pforum.setCanNotRe(1); // 置父帖帖为不可自己删除和修改
						}
						this.getForumDAO().saveOrUpdateForum(pforum);
					}
				}
				if (board.getAddUserPostNum() == 1) {
					UserInfo ui = this.getUserInfoDAO().findUserInfoById(forum.getUserID());
					if (ui != null) {
						ui.setArticleNum(ui.getArticleNum() + 1);
						if (forum.getIsNew() == 1) {
							ui.setExperience(ui.getExperience() + 2); // 发帖增加经验值2点。add
							// at
							// 2007.2.23
						} else {
							ui.setExperience(ui.getExperience() + 1); // 回帖增加经验值1点。add
							// at
							// 2007.2.23
						}
						ui = this.getUserInfoDAO().saveUserInfo(ui);
						this.getUserInfoFileIO().writeUserFile(ui);
					}
				}
			} catch (Exception ex) {
				logger.error(ex);
				throw new BbscsException(ex);
			}
		}
		this.getSysListObjCache().remove(Constant.FORUM_NEW_CACHE_NAME);
	}

	public void saveForumsAuditingAttachFile(List ids) throws BbscsException {
		List forums = this.getForumDAO().findForumsInIds(ids);
		for (int i = 0; i < forums.size(); i++) {
			Forum forum = (Forum) forums.get(i);
			forum.setAuditingAttachFile(0);
			try {
				this.getForumDAO().saveOrUpdateForum(forum);
			} catch (Exception ex) {
				logger.error(ex);
				throw new BbscsException(ex);
			}
		}
	}

	public void saveForumsEliteDel(List forums) throws BbscsException {
		for (int i = 0; i < forums.size(); i++) {
			Forum forum = (Forum) forums.get(i);
			try {
				forum = this.getForumDAO().saveOrUpdateForum(forum);
				UserInfo ui = this.getUserInfoDAO().findUserInfoById(forum.getUserID());
				if (ui.getArticleEliteNum() >= 1) {
					ui.setArticleEliteNum(ui.getArticleEliteNum() - 1);
				} else {
					ui.setArticleEliteNum(0);
				}
				ui.setLiterary(ui.getLiterary() - 5);
				ui = this.getUserInfoDAO().saveUserInfo(ui);
				this.getUserInfoFileIO().writeUserFile(ui);
			} catch (Exception ex) {
				logger.error(ex);
				throw new BbscsException(ex);
			}
		}
	}

	public void saveForumsResume(List ids, Board board) throws BbscsException {
		List forums = this.getForumDAO().findForumsInIds(ids);
		for (int i = 0; i < forums.size(); i++) {
			Forum forum = (Forum) forums.get(i);
			if (forum.getAuditing() == 0) { // 已通过审核的帖子恢复
				forum.setDelSign(0);
				if (forum.getIndexStatus() == Constant.INDEX_STATUS_NO_INDEX_TO_DEL) {
					forum.setIndexStatus(Constant.INDEX_STATUS_NO_INDEX);
				} else if (forum.getIndexStatus() == Constant.INDEX_STATUS_DELED) {
					forum.setIndexStatus(Constant.INDEX_STATUS_NO_INDEX);
				}
				try {
					this.getForumDAO().saveOrUpdateForum(forum);
					if (!forum.getId().equals(forum.getMainID())) {
						Forum mainForum = this.getForumDAO().findForumByID(forum.getMainID(), forum.getBoardID());
						if (mainForum != null) {
							mainForum.setReNum(mainForum.getReNum() + 1);
							this.getForumDAO().saveOrUpdateForum(mainForum);
						}
					}
					if (board.getAddUserPostNum() == 1) {
						UserInfo ui = this.getUserInfoDAO().findUserInfoById(forum.getUserID());
						if (ui != null) {
							ui.setArticleNum(ui.getArticleNum() + 1);

							if (forum.getIsNew() == 1) {
								ui.setExperience(ui.getExperience() + 2); // 主帖增加2点经验值
							} else {
								ui.setExperience(ui.getExperience() + 1); // 回帖增加1点经验值
							}

							ui = this.getUserInfoDAO().saveUserInfo(ui);
							this.getUserInfoFileIO().writeUserFile(ui);
						}
					}
				} catch (Exception ex) {
					logger.error(ex);
					throw new BbscsException(ex);
				}
			} else { // 未通过审核的被删除的帖子恢复
				forum.setDelSign(0);
				try {
					this.getForumDAO().saveOrUpdateForum(forum);
				} catch (Exception ex1) {
					logger.error(ex1);
					throw new BbscsException(ex1);
				}
			}
		}
	}

	public void saveMoveForum(Forum forum, Board toboard) throws BbscsException {

	}

	public void saveMoveForum(long frombid, String mainid, Board toboard) throws BbscsException {
		List fl = this.getForumDAO().findForumsTopic(frombid, mainid, 0, 0, -1, null);
		try {
			for (int i = 0; i < fl.size(); i++) {
				Forum forum = (Forum) fl.get(i);
				if (forum.getHaveAttachFile() != 0 && forum.getAttachFileName() != null
						&& forum.getAttachFileName().size() > 0) {
					this.getForumUploadFile().moveUploadFile(forum, toboard.getId().longValue());
				}
				String detail = this.getForumDetail(forum, true);
				this.getForumUploadFile().delDetailFile(forum);
				forum.setBoardID(toboard.getId().longValue());
				forum.setBoardName(toboard.getBoardName());
				forum.setTagID("0");
				forum.setTagName("");
				String postFileName = "P_" + forum.getBoardID() + "_" + forum.getId() + ".txt";
				File postFile = new File(this.getForumConfig().getForumPath(forum.getBoardID(), forum.getPostTime())
						+ postFileName);
				FileUtils.writeStringToFile(postFile, detail, Constant.CHARSET);
				forum.setDetail(postFileName);
				forum = this.getForumDAO().saveOrUpdateForum(forum);
				this.getPostCache().remove(forum.getId());
			}
			this.clearForumCache(mainid);
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public Forum saveOrUpdateForum(Forum forum) throws BbscsException {
		try {
			return this.getForumDAO().saveOrUpdateForum(forum);
		} catch (Exception e) {
			logger.error(e);
			throw new BbscsException(e);
		}
	}

	public void savePostElite(Forum forum, UserInfo ui) throws BbscsException {
		try {
			forum = this.getForumDAO().saveOrUpdateForum(forum);
			ui.setArticleEliteNum(ui.getArticleEliteNum() + 1);
			ui.setLiterary(ui.getLiterary() + 3);
			ui = this.getUserInfoDAO().saveUserInfo(ui);
			this.getUserInfoFileIO().writeUserFile(ui);
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public Forum updateForum(Forum forum) throws BbscsException {
		try {
			return this.getForumDAO().updateForum(forum);
		} catch (Exception e) {
			logger.error(e);
			throw new BbscsException(e);
		}
	}

	public String getForumDetail(Forum forum, boolean forcefromfile) {
		if (Constant.POST_STORAGE_MODE == 0 || forum.getIsVote() == 1) {
			return forum.getDetail();
		} else {
			if (forcefromfile) {
				return this.getForumDetailFromFile(forum);
			} else {
				String detail = (String) this.getPostCache().get(forum.getId());
				if (detail == null) {
					detail = this.getForumDetailFromFile(forum);
					if (StringUtils.isNotBlank(detail)) {
						postCache.add(forum.getId(), detail);
					}
				}
				return detail;
			}
		}
	}

	private String getForumDetailFromFile(Forum forum) {
		File postFile = new File(forumConfig.getForumPath(forum.getBoardID(), forum.getPostTime()) + forum.getDetail());
		try {
			return FileUtils.readFileToString(postFile, Constant.CHARSET);
		} catch (IOException e) {
			logger.error(e);
			return "";
		}
	}

	private void clearForumCache(String postid) {
		List l = (List) this.getSysListObjCache().get(Constant.FORUM_NEW_CACHE_NAME);
		if (l != null && !l.isEmpty()) {
			for (int i = 0; i < l.size(); i++) {
				Forum f = (Forum) l.get(i);
				if (f.getId().equals(postid)) {
					this.getSysListObjCache().remove(Constant.FORUM_NEW_CACHE_NAME);
				}
			}
		}
	}

}
