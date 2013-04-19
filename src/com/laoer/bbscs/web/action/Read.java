package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.laoer.bbscs.bean.Elite;
import com.laoer.bbscs.bean.Forum;
import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.comm.IPSeeker;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.service.web.PageList;
import com.laoer.bbscs.service.web.Pages;
import com.laoer.bbscs.web.interceptor.RemoteAddrAware;
import com.laoer.bbscs.web.interceptor.RequestBasePathAware;

public class Read extends BaseBoardAction implements RequestBasePathAware, RemoteAddrAware {

	/**
	 *
	 */
	private static final long serialVersionUID = 5929551298886705620L;

	private ForumService forumService;

	private ForumService forumHistoryService;

	private BoardService boardService;

	private SysConfig sysConfig;

	private IPSeeker ipSeeker;

	private UserService userService;

	private EliteService eliteService;

	private List<Elite> eliteDirs;

	private String basePath;

	private String remoteAddr;

	private String id;

	private int inpages = 1;

	private String mainid;

	private int fcpage = 1;

	private String fcaction = "index";

	private long eliteId;

	private long totalnum = 0;

	private boolean urlRewrite = false;

	private Forum forum;

	private String ipinfo;

	private List topicList;

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

	public long getEliteId() {
		return eliteId;
	}

	public void setEliteId(long eliteId) {
		this.eliteId = eliteId;
	}

	public String getFcaction() {
		return fcaction;
	}

	public void setFcaction(String fcaction) {
		this.fcaction = fcaction;
	}

	public int getFcpage() {
		return fcpage;
	}

	public void setFcpage(int fcpage) {
		this.fcpage = fcpage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getInpages() {
		return inpages;
	}

	public void setInpages(int inpages) {
		this.inpages = inpages;
	}

	public String getMainid() {
		return mainid;
	}

	public void setMainid(String mainid) {
		this.mainid = mainid;
	}

	public boolean isUrlRewrite() {
		return urlRewrite;
	}

	public void setUrlRewrite(boolean urlRewrite) {
		this.urlRewrite = urlRewrite;
	}

	private void setUrlRewriteValue() {
		this.setUrlRewrite(Constant.USE_URL_REWRITE);
	}

	private PageList pageList;

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	public List parentBoards;

	public List getParentBoards() {
		return parentBoards;
	}

	public void setParentBoards(List parentBoards) {
		this.parentBoards = parentBoards;
	}

	private String pageTitle;

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
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

	public IPSeeker getIpSeeker() {
		return ipSeeker;
	}

	public void setIpSeeker(IPSeeker ipSeeker) {
		this.ipSeeker = ipSeeker;
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

	public String getIpinfo() {
		return ipinfo;
	}

	public void setIpinfo(String ipinfo) {
		this.ipinfo = ipinfo;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public ForumService getForumHistoryService() {
		return forumHistoryService;
	}

	public void setForumHistoryService(ForumService forumHistoryService) {
		this.forumHistoryService = forumHistoryService;
	}

	public List getTopicList() {
		return topicList;
	}

	public void setTopicList(List topicList) {
		this.topicList = topicList;
	}

	private String forumSite = "bbs.ruyicai.com";

	public String getForumSite() {
		return forumSite;
	}

	public void setForumSite(String forumSite) {
		this.forumSite = forumSite;
	}

	public List<Elite> getEliteDirs() {
		return eliteDirs;
	}

	public void setEliteDirs(List<Elite> eliteDirs) {
		this.eliteDirs = eliteDirs;
	}

	public EliteService getEliteService() {
		return eliteService;
	}

	public void setEliteService(EliteService eliteService) {
		this.eliteService = eliteService;
	}

	public String execute() {
		if (this.getAction().equalsIgnoreCase("topic")) {
			return this.topic();
		} else if (this.getAction().equalsIgnoreCase("history")) {
			return this.history();
		} else if (this.getAction().equalsIgnoreCase("own")) {
			return this.own();
		} else if (this.getAction().equalsIgnoreCase("summary")) {
			return this.summary();
		} else if (this.getAction().equalsIgnoreCase("showip")) {
			return this.showip();
		} else if (this.getAction().equalsIgnoreCase("summaryhistory")) {
			return this.summaryhistory();
		} else if (this.getAction().equalsIgnoreCase("showiphistory")) {
			return this.showiphistory();
		} else if (this.getAction().equalsIgnoreCase("showupfile")) {
			return this.showupfile();
		} else if (this.getAction().equalsIgnoreCase("attach")) {
			return this.attach();
		} else if (this.getAction().equalsIgnoreCase("showvote")) {
			return this.showvote();
		} else if (this.getAction().equalsIgnoreCase("waste")) {
			return this.waste();
		} else if (this.getAction().equalsIgnoreCase("auditing")) {
			return this.auditing();
		} else if (this.getAction().equalsIgnoreCase("auditingAttach")) {
			return this.auditingAttach();
		} else if (this.getAction().equalsIgnoreCase("elite")) {
			return this.elite();
		} else {
			return ERROR;
		}
	}

	public String topic() {
		setUrlRewriteValue();
		Pages pages = new Pages();
		pages.setPage(this.getInpages());

		pages.setPerPageNum(this.getUserPostPerNum(this.getUserCookie().getPostPerNum(), this.getSysConfig()
				.getPostPerPage()));
		// pages.setPerPageNum(1);
		if (Constant.USE_URL_REWRITE) {
			pages.setUseUrlRewrite(Constant.USE_URL_REWRITE);
			pages.setFileName("read-" + this.getAction() + "-" + this.getBid() + "-" + this.getId() + "-"
					+ this.getTagId() + "-" + this.getFcpage() + "-" + this.getFcaction() + "-{page}.html");
		} else {
			pages.setFileName(this.getBasePath()
					+ BBSCSUtil.getActionMappingURLWithoutPrefix("read?action=" + this.getAction() + "&bid="
							+ this.getBid() + "&id=" + this.getId() + "&fcpage=" + this.getFcpage() + "&fcaction="
							+ this.getFcaction() + "&tagId=" + this.getTagId()));
		}
		this.setPageList(this.getForumService().findForumsTopic(this.getBid(), this.getId(), pages));

		if (this.getPageList().getObjectList().isEmpty()) {
			// this.addActionError(this.getText("error.post.getpost"));
			// return ERROR;
			return this.history();
		}

		this.pageTitle = "";

		Forum f = (Forum) this.getPageList().getObjectList().get(0);
		
		String nickname = f.getNickName();
		String re = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";
		Pattern pattern = Pattern.compile(re);
		Matcher matcher = pattern.matcher(nickname);
		Matcher matcher2 = pattern.matcher(f.getUserName());
		if (matcher.matches()&&nickname.length()>7) {
			String new_nickName = nickname.substring(0, 3) + "****" + nickname.substring(7);
			f.setNickName(new_nickName);
		}else if(matcher2.matches()&&(nickname==null||nickname.equals("null")||nickname.length()==0)&&f.getUserName().length()>7){
			String new_nickName = f.getUserName().substring(0, 3) + "****" + f.getUserName().substring(7);
			f.setNickName(new_nickName);
		}
		
		
		if (f.getIsNew() == 1) {
			f.setClick(f.getClick() + 1);
			try {
				f = this.getForumService().updateForum(f);
				if (f.getClick() == this.getSysConfig().getForumHotViews() && f.getIsHidden() == 0) {
					UserInfo ui = this.getUserService().findUserInfoById(f.getUserID());
					if (ui != null) {
						ui.setLiterary(ui.getLiterary() + 1); // 增加用户文采值
						this.getUserService().saveUserInfo(ui);
					}
				}
			} catch (BbscsException ex) {
				this.addActionError(this.getText("error.post.getpost"));
				return ERROR;

			}
		} else {
			if (this.getInpages() == 1) {

				this.addActionError(this.getText("error.post.getpost"));
				return ERROR;
			}
		}
		this.pageTitle = f.getTitle();
		this.setTotalnum(this.getPageList().getPages().getTotalNum());
		this.setParentBoards();
		this.setForumSiteInit();
		return "read";
	}

	public String history() {
		setUrlRewriteValue();
		Pages pages = new Pages();
		pages.setPage(this.getInpages());

		pages.setPerPageNum(this.getUserPostPerNum(this.getUserCookie().getPostPerNum(), this.getSysConfig()
				.getPostPerPage()));
		// pages.setPerPageNum(1);
		if (Constant.USE_URL_REWRITE) {
			pages.setUseUrlRewrite(Constant.USE_URL_REWRITE);
			pages.setFileName("read-" + this.getAction() + "-" + this.getBid() + "-" + this.getId() + "-"
					+ this.getTagId() + "-" + this.getFcpage() + "-" + this.getFcaction() + "-{page}.html");
		} else {
			pages.setFileName(this.getBasePath()
					+ BBSCSUtil.getActionMappingURLWithoutPrefix("read?action=" + this.getAction() + "&bid="
							+ this.getBid() + "&id=" + this.getId() + "&fcpage=" + this.getFcpage() + "&fcaction="
							+ this.getFcaction() + "&tagId=" + this.getTagId()));
		}
		this.setPageList(this.getForumHistoryService().findForumsTopic(this.getBid(), this.getId(), pages));

		if (this.getPageList().getObjectList().isEmpty()) {
			this.addActionError(this.getText("error.post.getpost"));
			return ERROR;
		}

		this.pageTitle = "";

		Forum f = (Forum) this.getPageList().getObjectList().get(0);
		
		String nickname = f.getNickName();
		String re = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";
		Pattern pattern = Pattern.compile(re);
		Matcher matcher = pattern.matcher(nickname);
		Matcher matcher2 = pattern.matcher(f.getUserName());
		if (matcher.matches()&&nickname.length()>7) {
			String new_nickName = nickname.substring(0, 3) + "****" + nickname.substring(7);
			f.setNickName(new_nickName);
		}else if(matcher2.matches()&&(nickname==null||nickname.equals("null")||nickname.length()==0)&&f.getUserName().length()>7){
			String new_nickName = f.getUserName().substring(0, 3) + "****" + f.getUserName().substring(7);
			f.setNickName(new_nickName);
		}
		if (f.getIsNew() == 1) {
			/*
			 * f.setClick(f.getClick() + 1); try { f =
			 * this.getForumService().updateForum(f); if (f.getClick() ==
			 * this.getSysConfig().getForumHotViews()) { UserInfo ui =
			 * this.getUserService().findUserInfoById(f.getUserID()); if (ui !=
			 * null) { ui.setLiterary(ui.getLiterary() + 1); // 增加用户文采值
			 * this.getUserService().saveUserInfo(ui); } } } catch
			 * (BbscsException ex) {
			 * this.addActionError(this.getText("error.post.getpost")); return
			 * ERROR; }
			 */
		} else {
			if (this.getInpages() == 1) {

				this.addActionError(this.getText("error.post.getpost"));
				return ERROR;
			}
		}
		this.pageTitle = f.getTitle();
		this.setTotalnum(this.getPageList().getPages().getTotalNum());
		this.setParentBoards();
		this.setForumSiteInit();
		return "readHistory";
	}

	public String own() {
		setUrlRewriteValue();
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.addActionError(this.getText("error.post.getpost"));
			return ERROR;
		}
		Pages pages = new Pages();
		pages.setPage(this.getInpages());

		pages.setPerPageNum(this.getUserPostPerNum(this.getUserCookie().getPostPerNum(), this.getSysConfig()
				.getPostPerPage()));
		pages.setFileName(this.getBasePath()
				+ BBSCSUtil.getActionMappingURLWithoutPrefix("read?action=" + this.getAction() + "&bid="
						+ this.getBid() + "&id=" + this.getId() + "&fcpage=" + this.getFcpage() + "&fcaction="
						+ this.getFcaction()));
		this.setPageList(this.getForumService().findForumsOwner(this.getBid(), f.getUserID(), this.getId(), pages));
		if (this.getPageList().getObjectList().size() == 0) {
			this.addActionError(this.getText("error.post.getpost"));
			return ERROR;
		}
		this.pageTitle = f.getTitle();
		this.setParentBoards();
		this.setTotalnum(this.getPageList().getPages().getTotalNum());
		this.setForumSiteInit();
		return "read";
	}

	public String summary() {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.addActionError(this.getText("error.post.getpost"));
			return ERROR;
		}
		this.setForum(f);
		return "postSummary";
	}

	public String showip() {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.addActionError(this.getText("error.post.getpost"));
			return RESULT_HTMLERROR;
		}
		this.setIpinfo(this.getText("post.showip", new String[] { f.getIpAddress(),
				this.getIpSeeker().getCountry(f.getIpAddress()) }));
		this.setForum(f);
		return "postShowIpInfo";
	}

	public String summaryhistory() {
		Forum f = this.getForumHistoryService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.addActionError(this.getText("error.post.getpost"));
			return ERROR;
		}
		this.setForum(f);
		return "postSummary";
	}

	public String showiphistory() {
		Forum f = this.getForumHistoryService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.addActionError(this.getText("error.post.getpost"));
			return RESULT_HTMLERROR;
		}
		this.setIpinfo(this.getText("post.showip", new String[] { f.getIpAddress(),
				this.getIpSeeker().getCountry(f.getIpAddress()) }));
		this.setForum(f);
		return "postShowIpInfo";
	}

	public String showupfile() {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.addActionError(this.getText("error.post.getpost"));
			return RESULT_HTMLERROR;
		}
		this.setForum(f);
		return "showUpFileInPost";
	}

	public String attach() {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.addActionError(this.getText("error.post.getpost"));
			return ERROR;
		}
		this.setForum(f);
		return "showAttach";
	}

	public String showvote() {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.addActionError(this.getText("error.post.getpost"));
			return RESULT_HTMLERROR;
		}
		this.setForum(f);
		return "showVoteInPost";
	}

	public String waste() {
		this.setTopicList(this.getForumService().findForumsTopicDel(this.getBid(), this.getMainid()));
		return "readWaste";
	}

	public String auditing() {
		this.setTopicList(this.getForumService().findForumsTopicAuditing(this.getBid(), this.getMainid()));
		return "readAuditing";
	}

	public String auditingAttach() {
		this.setTopicList(this.getForumService().findForumsTopicAuditingAttachFile(this.getBid(), this.getMainid()));
		return "readAuditing";
	}

	public String elite() {
		this.setEliteDirs(new ArrayList<Elite>());
		Elite elite = this.getEliteService().findEliteByID(this.getEliteId());
		if (elite != null) {
			List pes = elite.getParentIDs();
			for (int i = 0; i < pes.size(); i++) {
				Elite pe = this.getEliteService().findEliteByID(((Long) pes.get(i)).longValue());
				this.eliteDirs.add(pe);
			}
			this.eliteDirs.add(elite);
		}
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.addActionError(this.getText("error.post.getpost"));
			return ERROR;
		}
		this.setForum(f);
		return "readElite";
	}

	private int getUserPostPerNum(int userNum, int sysNum) {
		if (userNum == 0) {
			return sysNum;
		} else {
			return userNum;
		}
	}

	private void setParentBoards() {
		this.setParentBoards(this.getBoardService().findBoardsInIDs(this.getBoard().getParentIDs()));
	}

	public long getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(long totalnum) {
		this.totalnum = totalnum;
	}

	private void setForumSiteInit() {
		/*String url = this.getSysConfig().getForumUrl();
		if (url == null) {
			url = "bbs.ruyicai.com";
		} else if (url.startsWith("http://")) {
			url = url.substring("http://".length(), url.length());
		}*/
		String url = request.getContextPath();
		this.setForumSite(url);
	}

}
