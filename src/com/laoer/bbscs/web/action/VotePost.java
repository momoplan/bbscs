package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laoer.bbscs.bean.BoardTag;
import com.laoer.bbscs.bean.Forum;
import com.laoer.bbscs.bean.ForumMain;
import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.bean.Vote;
import com.laoer.bbscs.bean.VoteItem;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.comm.Util;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.BoardService;
import com.laoer.bbscs.service.ForumService;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.VoteItemService;
import com.laoer.bbscs.service.VoteService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.interceptor.RemoteAddrAware;
import com.laoer.bbscs.web.interceptor.RequestBasePathAware;
import com.laoer.bbscs.web.ui.OptionsInt;
import com.laoer.bbscs.web.ui.OptionsString;

public class VotePost extends BaseBoardAction implements RequestBasePathAware, RemoteAddrAware {

	/**
	 *
	 */
	private static final long serialVersionUID = 6841154224463080848L;

	private String basePath;

	private String remoteAddr;

	private int deadLineDay;

	private int deadLineMon;

	private String deadLineYear;

	private String detail;

	private String voteItem;

	private int isM;

	private String title;

	private String id;

	private List<OptionsString> tagValues = new ArrayList<OptionsString>();

	private ForumService forumService;

	private BoardService boardService;

	private SysConfig sysConfig;

	private UserService userService;

	private VoteService voteService;

	private VoteItemService voteItemService;

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getBasePath() {
		return basePath;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public int getDeadLineDay() {
		return deadLineDay;
	}

	public void setDeadLineDay(int deadLineDay) {
		this.deadLineDay = deadLineDay;
	}

	public int getDeadLineMon() {
		return deadLineMon;
	}

	public void setDeadLineMon(int deadLineMon) {
		this.deadLineMon = deadLineMon;
	}

	public String getDeadLineYear() {
		return deadLineYear;
	}

	public void setDeadLineYear(String deadLineYear) {
		this.deadLineYear = deadLineYear;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIsM() {
		return isM;
	}

	public void setIsM(int isM) {
		this.isM = isM;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVoteItem() {
		return voteItem;
	}

	public void setVoteItem(String voteItem) {
		this.voteItem = voteItem;
	}

	private List<OptionsString> yearValues = Constant.NYEAR;

	public List<OptionsString> getYearValues() {
		return yearValues;
	}

	public void setYearValues(List<OptionsString> yearValues) {
		this.yearValues = yearValues;
	}

	private List<OptionsInt> monthValues = Constant.MONTH;

	public List<OptionsInt> getMonthValues() {
		return monthValues;
	}

	public void setMonthValues(List<OptionsInt> monthValues) {
		this.monthValues = monthValues;
	}

	private List<OptionsInt> dayValues = Constant.DAY;

	public List<OptionsInt> getDayValues() {
		return dayValues;
	}

	public void setDayValues(List<OptionsInt> dayValues) {
		this.dayValues = dayValues;
	}

	public List<OptionsString> getTagValues() {
		return tagValues;
	}

	public void setTagValues(List<OptionsString> tagValues) {
		this.tagValues = tagValues;
	}

	public BoardService getBoardService() {
		return boardService;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	public ForumService getForumService() {
		return forumService;
	}

	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public VoteItemService getVoteItemService() {
		return voteItemService;
	}

	public void setVoteItemService(VoteItemService voteItemService) {
		this.voteItemService = voteItemService;
	}

	public VoteService getVoteService() {
		return voteService;
	}

	public void setVoteService(VoteService voteService) {
		this.voteService = voteService;
	}

	private String forwardUrl;

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public String add() {
		this.setAction("addsave");
		this.setDeadLineYear(String.valueOf(Util.getYear()));
		this.setDeadLineMon(Util.getMonth());
		this.setTagsAttribute();
		return INPUT;
	}

	public String addsave() {
		String stitle = StringUtils.trimToEmpty(this.getTitle());
		String sdetail = StringUtils.trimToEmpty(this.getDetail());
		String svoteItem = StringUtils.trimToEmpty(this.getVoteItem());
		if (StringUtils.isBlank(stitle) || BBSCSUtil.getSysCharsetStrLength(stitle) > 90) {
			this.addActionError(this.getText("error.post.titletoolong"));
			this.setTagsAttribute();
			return INPUT;
		}
		stitle = this.getSysConfig().bestrowScreen(stitle);
		if (StringUtils.isBlank(this.getVoteItem())) {
			this.setTagsAttribute();
			this.addActionError(this.getText("error.post.nodetail"));
			return INPUT;
		}
		sdetail = this.getSysConfig().bestrowScreen(sdetail);
		svoteItem = this.getSysConfig().bestrowScreen(voteItem);
		String[] details = svoteItem.split("\n");
		if (this.getSysConfig().getVoteItemNum() > 0) {
			if (details.length > this.getSysConfig().getVoteItemNum()) {
				this.addActionError(this.getText("error.vote.itemnum", new String[] { String.valueOf(this
						.getSysConfig().getVoteItemNum()) }));
				this.setTagsAttribute();
				return INPUT;
			}
		}
		int voteItemLength = this.getSysConfig().getVoteItemLength();
		if (voteItemLength > 255) {
			voteItemLength = 255;
		}
		for (int i = 0; i < details.length; i++) {
			if (details[i].length() == 0 || BBSCSUtil.getSysCharsetStrLength(details[i]) > voteItemLength) {
				this.addActionError(this.getText("error.vote.itemlength",
						new String[] { String.valueOf(voteItemLength) }));

				this.setTagsAttribute();
				return INPUT;
			}
		}

		if (Util.Date2Long(Integer.parseInt(this.getDeadLineYear()), this.getDeadLineMon(), this.getDeadLineDay()) <= Util
				.getLongTime()) {
			this.addActionError(this.getText("error.vote.deadline"));
			this.setTagsAttribute();
			return INPUT;
		}

		long nowtime = System.currentTimeMillis();
		Forum f = new ForumMain();
		f.setAgree(0);
		f.setAmend("");
		f.setArtSize(0);
		f.setAttachFileName(new ArrayList());
		f.setAuditing(this.getBoard().getAuditPost());
		f.setAuditingAttachFile(this.getBoard().getAuditAttach());
		f.setBeAgainst(0);
		f.setBoardID(this.getBid());
		f.setBoardName(this.getBoard().getBoardName());
		f.setCanNotDel(0);
		f.setCanNotRe(0);
		f.setClick(0);
		f.setCommend(0);
		f.setDelIP("");
		f.setDelSign(0);
		f.setDelTime(0);
		f.setDelUserID("");
		f.setDelUserName("");
		f.setDetail(sdetail);
		f.setDoEliteName("");
		f.setDoEliteTime(0);
		f.setEditType(0);
		f.setElite(0);
		f.setEliteID(0);
		f.setFace(99);
		f.setHaveAttachFile(0);
		f.setIpAddress(this.getRemoteAddr());
		f.setIsHidden(0);
		f.setIsHiddenValue(0);
		f.setIsLock(0);
		f.setIsNew(1);
		f.setIsTop(0);
		f.setIsVote(1);
		f.setLastPostNickName("---");
		f.setLastPostTitle("");
		f.setLastPostUserName("---");
		f.setLastTime(nowtime);
		f.setMainID("");
		f.setNickName(this.getUserSession().getNickName());
		f.setParentID("");
		f.setPostTime(nowtime);
		f.setPostType(0);
		f.setQuoteText("");
		f.setReNum(0);
		f.setSign("");
		f.setTitle(stitle);
		f.setTitleColor(0);
		f.setUserID(this.getUserSession().getId());
		f.setUserName(this.getUserSession().getUserName());
		f.setEmailInform(0);
		f.setMsgInform(0);

		f.setTagID(this.getTagId());
		if (this.getTagId().equals("0")) {
			f.setTagName("");
		} else {
			f.setTagName(this.getBoard().getBoardTagById(this.getTagId()).getTagName());
		}

		Vote vote = new Vote();
		vote.setDeadLine(Util.Date2Long(Integer.parseInt(this.getDeadLineYear()), this.getDeadLineMon(), this
				.getDeadLineDay()));
		vote.setIsM(this.getIsM());
		vote.setTitle(this.getTitle());

		UserInfo ui = this.getUserService().findUserInfoById(this.getUserSession().getId());
		if (ui == null) {
			this.addActionError(this.getText("error.post.guest"));
			this.setTagsAttribute();
			return INPUT;
		}

		try {
			this.getForumService().createVoteForum(f, this.getBoard(), vote, ui, svoteItem);
			this.getUserCookie().addLastPostTime();
			if (this.getBoard().getAuditPost() == 0) {
				if (Constant.USE_URL_REWRITE) {
					this.setForwardUrl(this.getBasePath() + "/forum-index-" + this.getBid() + "-" + this.getTagId()
							+ "-1-0.html");
				} else {
					this.setForwardUrl(this.getBasePath()
							+ BBSCSUtil.getActionMappingURLWithoutPrefix("forum?action=index&bid=" + this.getBid()
									+ "&tagId=" + this.getTagId()));
				}
			} else {
				this.setForwardUrl(this.getBasePath()
						+ BBSCSUtil.getActionMappingURLWithoutPrefix("postWaitAudit?bid=" + this.getBid()));
			}
			return SUCCESS;

		} catch (BbscsException ex) {
			this.addActionError(this.getText("error.vote.addvote"));
			this.setTagsAttribute();
			return INPUT;
		}
	}

	public String edit() {
		if (this.getSysConfig().getVoteChange() == 0) {
			this.addActionError(this.getText("error.post.notchange"));
			return ERROR;
		}

		this.setAction("editdo");
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.addActionError(this.getText("error.post.getpost"));
			return ERROR;
		}
		if (!(f.getUserID().equals(this.getUserSession().getId()) || this.getUserSession()
				.isHaveBoardSpecialPermission(Constant.SPERMISSION_CAN_EDITPOST))) {
			this.addActionError(this.getText("error.noPermission"));
			return ERROR;
		}
		Vote vote = this.getVoteService().findVoteByID(f.getVoteID());
		this.setIsM(vote.getIsM());
		this.setTitle(vote.getTitle());
		this.setDeadLineDay(Util.getDay(vote.getDeadLine()));
		this.setDeadLineMon(Util.getMonth(vote.getDeadLine()));
		this.setDeadLineYear(String.valueOf(Util.getYear(vote.getDeadLine())));

		List l = this.getVoteItemService().findVoteItemsByVoteID(f.getVoteID());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < l.size(); i++) {
			VoteItem vi = (VoteItem) l.get(i);
			sb.append(vi.getItem());
			sb.append("\n");
		}
		this.setVoteItem(sb.toString());
		this.setDetail(f.getDetail());
		//this.setDetail(this.getForumService().getForumDetail(f));
		this.setTagsAttribute();
		return INPUT;
	}

	public String editdo() {
		String stitle = StringUtils.trimToEmpty(this.getTitle());
		String sdetail = StringUtils.trimToEmpty(this.getDetail());
		if (StringUtils.isBlank(stitle) || BBSCSUtil.getSysCharsetStrLength(stitle) > 90) {
			this.addActionError(this.getText("error.post.titletoolong"));
			this.setTagsAttribute();
			return INPUT;
		}
		stitle = this.getSysConfig().bestrowScreen(stitle);
		sdetail = this.getSysConfig().bestrowScreen(sdetail);

		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.addActionError(this.getText("error.post.getpost"));
			return INPUT;
		}
		Vote vote = this.getVoteService().findVoteByID(f.getVoteID());
		vote.setDeadLine(Util.Date2Long(Integer.parseInt(this.getDeadLineYear()), this.getDeadLineMon(), this
				.getDeadLineDay()));
		vote.setIsM(this.getIsM());
		vote.setTitle(stitle);

		f.setTitle(stitle);
		f.setDetail(sdetail);

		try {
			this.getForumService().saveOrUpdateForum(f);
			this.getVoteService().saveVote(vote);
			this.setForwardUrl(this.getBasePath()
					+ BBSCSUtil.getActionMappingURLWithoutPrefix("forum?action=index&bid=" + this.getBid()));
			return SUCCESS;
		} catch (BbscsException ex1) {
			this.addActionError(this.getText("error.post.vote.edit.error"));
			return INPUT;
		}

	}

	private void setTagsAttribute() {
		this.tagValues.add(new OptionsString("0", this.getBoard().getBoardName()));
		Iterator it = this.getBoard().getBoardTag().iterator();
		BoardTag bt = null;
		while (it.hasNext()) {
			bt = (BoardTag) it.next();
			this.tagValues.add(new OptionsString(bt.getId(), bt.getTagName()));
		}
	}

}
