package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.bean.BoardMaster;
import com.laoer.bbscs.bean.Forum;
import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.service.BoardService;
import com.laoer.bbscs.service.ForumService;
import com.laoer.bbscs.service.FriendService;
import com.laoer.bbscs.service.NoteService;
import com.laoer.bbscs.service.SysStatService;
import com.laoer.bbscs.service.UserOnlineService;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.interceptor.RequestBasePathAware;

public class In extends BaseMainAction implements RequestBasePathAware {

	/**
	 *
	 */
	private static final long serialVersionUID = 470483826099960657L;
	
	private static Logger logger = Logger.getLogger(In.class);

	private String basePath;

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBasePath() {
		return basePath;
	}

	private UserService userService;

	private NoteService noteService;

	private FriendService friendService;

	private UserOnlineService userOnlineService;

	private ForumService forumService;

	private SysConfig sysConfig;

	private SysStatService sysStatService;

	private BoardService boardService;

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

	public FriendService getFriendService() {
		return friendService;
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	public NoteService getNoteService() {
		return noteService;
	}

	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public SysStatService getSysStatService() {
		return sysStatService;
	}

	public void setSysStatService(SysStatService sysStatService) {
		this.sysStatService = sysStatService;
	}

	public UserOnlineService getUserOnlineService() {
		return userOnlineService;
	}

	public void setUserOnlineService(UserOnlineService userOnlineService) {
		this.userOnlineService = userOnlineService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private String onlineHighest;

	public String getOnlineHighest() {
		return onlineHighest;
	}

	public void setOnlineHighest(String onlineHighest) {
		this.onlineHighest = onlineHighest;
	}

	private String sysinfo;

	public String getSysinfo() {
		return sysinfo;
	}

	public void setSysinfo(String sysinfo) {
		this.sysinfo = sysinfo;
	}

	private Date lastLoginTime;

	private int titleValue;

	private String userTitle;

	private long newNoteNumInbox;

	private long noteAllNumInbox;

	private long friendNum;

	private long onlineNum;

	private long onlineGuestNum;

	private long friendOnlineNum;

	private boolean guest;

	private List<Forum> newForums = new ArrayList<Forum>();
	
	private List<Forum> HaveAttachFileForums = new ArrayList<Forum>();
	
	private List<Forum> LastPostTitleForums = new ArrayList<Forum>();
	
	private List<Forum> ClickForums = new ArrayList<Forum>();

	public List<Forum> getNewForums() {
		return newForums;
	}

	public void setNewForums(List<Forum> newForums) {
		this.newForums = newForums;
	}

	public List<Forum> getHaveAttachFileForums() {
		return HaveAttachFileForums;
	}

	public void setHaveAttachFileForums(List<Forum> haveAttachFileForums) {
		HaveAttachFileForums = haveAttachFileForums;
	}

	public List<Forum> getLastPostTitleForums() {
		return LastPostTitleForums;
	}

	public void setLastPostTitleForums(List<Forum> lastPostTitleForums) {
		LastPostTitleForums = lastPostTitleForums;
	}

	public List<Forum> getClickForums() {
		return ClickForums;
	}

	public void setClickForums(List<Forum> clickForums) {
		ClickForums = clickForums;
	}

	public long getFriendNum() {
		return friendNum;
	}

	public void setFriendNum(long friendNum) {
		this.friendNum = friendNum;
	}

	public long getFriendOnlineNum() {
		return friendOnlineNum;
	}

	public void setFriendOnlineNum(long friendOnlineNum) {
		this.friendOnlineNum = friendOnlineNum;
	}

	public boolean isGuest() {
		return guest;
	}

	public void setGuest(boolean guest) {
		this.guest = guest;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public long getNewNoteNumInbox() {
		return newNoteNumInbox;
	}

	public void setNewNoteNumInbox(long newNoteNumInbox) {
		this.newNoteNumInbox = newNoteNumInbox;
	}

	public long getNoteAllNumInbox() {
		return noteAllNumInbox;
	}

	public void setNoteAllNumInbox(long noteAllNumInbox) {
		this.noteAllNumInbox = noteAllNumInbox;
	}

	public long getOnlineGuestNum() {
		return onlineGuestNum;
	}

	public void setOnlineGuestNum(long onlineGuestNum) {
		this.onlineGuestNum = onlineGuestNum;
	}

	public long getOnlineNum() {
		return onlineNum;
	}

	public void setOnlineNum(long onlineNum) {
		this.onlineNum = onlineNum;
	}

	public int getTitleValue() {
		return titleValue;
	}

	public void setTitleValue(int titleValue) {
		this.titleValue = titleValue;
	}

	public String getUserTitle() {
		return userTitle;
	}

	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}

	private List boardList = new ArrayList();

	public List getBoardList() {
		return boardList;
	}

	public void setBoardList(List boardList) {
		this.boardList = boardList;
	}

	private Map<Long, List> boardMap = new HashMap<Long, List>();

	public Map<Long, List> getBoardMap() {
		return boardMap;
	}

	public void setBoardMap(Map<Long, List> boardMap) {
		this.boardMap = boardMap;
	}

	private boolean urlRewrite = false;

	public boolean isUrlRewrite() {
		return urlRewrite;
	}

	public void setUrlRewrite(boolean urlRewrite) {
		this.urlRewrite = urlRewrite;
	}

	private boolean usePass;

	public boolean isUsePass() {
		return usePass;
	}

	public void setUsePass(boolean usePass) {
		this.usePass = usePass;
	}

	private String actionUrl;

	private String tourl;

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getTourl() {
		return tourl;
	}

	public void setTourl(String tourl) {
		this.tourl = tourl;
	}

	private String forumSite = "bbs.ruyicai.com";

	public String getForumSite() {
		return forumSite;
	}

	public void setForumSite(String forumSite) {
		this.forumSite = forumSite;
	}

	private boolean useAuthCode = true;

	public boolean isUseAuthCode() {
		return useAuthCode;
	}

	public void setUseAuthCode(boolean useAuthCode) {
		this.useAuthCode = useAuthCode;
	}

	public String execute() {
		return this.index();
	}

	public String index() {
		int isHidden = 0;
		if (this.getUserSession().isHaveSpecialPermission(Constant.SPERMISSION_CAN_SEE_HIDDEN_BOARD)) { // 如果用户有查看隐藏版区的权限
			isHidden = -1;
		}
		UserInfo ui = this.getUserService().findUserInfoById(this.getUserSession().getId());
		long atime = System.currentTimeMillis() - (this.getSysConfig().getUserOnlineTime() * 1000);

		long onlineNum = this.getUserOnlineService().getUserOnlineNum(atime, 0, 0, Constant.NORMAL_USER_GROUPS);
		long onlineGuestNum = this.getUserOnlineService().getUserOnlineNum(atime, 0, 0, Constant.GUEST_USER_GROUPS);

		this.getSysStatService().saveOnline(onlineNum + onlineGuestNum);

		this
				.setOnlineHighest(this.getText("bbscs.onlinehighest", new String[] {
						String.valueOf(this.getSysStatService().getOnlineNum()),
						this.getSysStatService().getAppearTimeStr() }));

		String reguserurl = "<a href=\""
				+ this.getBasePath()
				+ BBSCSUtil.getActionMappingURLWithoutPrefix("userInfo?action=name&username="
						+ this.getSysStatService().getLastRegUser()) + "\">"
				+ this.getSysStatService().getLastRegUser() + "</a>";

		this.setSysinfo(this.getText("bbscs.sysinfo", new String[] {
				String.valueOf(this.getSysStatService().getPostMainNum()),
				String.valueOf(this.getSysStatService().getPostNum()),
				String.valueOf(this.getSysStatService().getOnlineNum()), this.getSysStatService().getLastRegUser() }));

		this.setOnlineNum(onlineNum);
		this.setOnlineGuestNum(onlineGuestNum);

		if (ui == null || this.getUserSession().getGroupID() == Constant.USER_GROUP_GUEST) {
			this.setLastLoginTime(new Date());
			this.setTitleValue(0);
			ui = null;
			this.setUserTitle(this.getUserService().getUserTitle(ui));
			this.setNewNoteNumInbox(0);
			this.setNoteAllNumInbox(0);
			this.setFriendNum(0);
			this.setFriendOnlineNum(0);
			this.setGuest(true);
		} else {
			this.setLastLoginTime(ui.getLastLoginTime());
			this.setTitleValue(this.getUserService().getUserTitleValue(ui));
			this.setUserTitle(this.getUserService().getUserTitle(ui));

			this.setNewNoteNumInbox(this.getNoteService().getNoteNumInBoxByIsNew(this.getUserSession().getId(), 1));
			this.setNoteAllNumInbox(this.getNoteService().getNoteAllNumInBox(this.getUserSession().getId()));

			this.setFriendNum(this.getFriendService().getFriendNum(this.getUserSession().getId(), 0));
			long foNum = this.getUserOnlineService().getUserOnlineNumInIds(atime,
					this.getFriendService().findFriendIds(this.getUserSession().getId(), 0), 0, 0,
					Constant.NORMAL_USER_GROUPS);
			this.setFriendOnlineNum(foNum);
			this.setGuest(false);
		}
		//最新主题（最新文章）
		List newfs = this.getForumService().findForumsAllNewCache(50);
		if (newfs.size() > 8) {
			for (int i = 0; i < 8; i++) {
				this.newForums.add((Forum) newfs.get(i));
			}
		} else {
			for (int i = 0; i < newfs.size(); i++) {
				this.newForums.add((Forum) newfs.get(i));
			}
		}
		//最新图片
		List HaveAttachFile = this.getForumService().findForumsAllNewAttachFile(100);
		for(int a = 0; a < HaveAttachFile.size(); a++){
			Forum ForumsAllNewAttachFile= (Forum)HaveAttachFile.get(a);
			if(ForumsAllNewAttachFile.getAttachFileName()!=null && !ForumsAllNewAttachFile.getAttachFileName().isEmpty() && HaveAttachFileForums.size()<5){
				//long postTime = Long.parseLong(Util.formatDate4(new Date(ForumsAllNewAttachFile.getPostTime())));
				
				List AttachFileNameArr = new ArrayList();
				for (int i = 0; i < ForumsAllNewAttachFile.getAttachFileName().size(); i++) {
					String fileName = (String) (ForumsAllNewAttachFile.getAttachFileName().get(i));
					String fileURl = BBSCSUtil.getUpFileWebPath(ForumsAllNewAttachFile.getBoardID(), ForumsAllNewAttachFile.getPostTime());
					AttachFileNameArr.add(fileURl+fileName);
				}
				
				ForumsAllNewAttachFile.setAttachFileName(AttachFileNameArr);
				
				this.HaveAttachFileForums.add((Forum) ForumsAllNewAttachFile);
			}
		}
		
		//最新回复
		List LastPostTitle = this.getForumService().findForumsAllNewLastPostTitle(100);
		for(int b = 0; b < LastPostTitle.size(); b++){
			Forum ForumsAllNewLastPostTitle= (Forum)LastPostTitle.get(b);
			if(ForumsAllNewLastPostTitle.getLastPostTitle()!=null && !ForumsAllNewLastPostTitle.getLastPostTitle().isEmpty() && LastPostTitleForums.size()<8){
				this.LastPostTitleForums.add((Forum) LastPostTitle.get(b));
			}
		}
		//点击量超过20的人气文章
		List click = this.getForumService().findForumsAllNewClickForums(19);
		for(int a = 0; a < click.size(); a++){
			Forum ForumsForClick= (Forum)click.get(a);
			if(ForumsForClick.getAttachFileName()!=null && !ForumsForClick.getAttachFileName().isEmpty() && ClickForums.size()<8){
				this.ClickForums.add((Forum) ForumsForClick);
			}
		}
		
		this.setUrlRewrite(Constant.USE_URL_REWRITE);
		this.boardList = this.getBoardService().findBoardsByParentID(0, 1, isHidden, Constant.FIND_BOARDS_BY_ORDER);

		for (int i = 0; i < this.boardList.size(); i++) {
			Board b = (Board) this.boardList.get(i);
			
	        Collection<String> key = b.getBoardMaster().keySet();
	        Iterator it = key.iterator();
	        logger.info("it(OneBoardMessage):"+b.getBoardMaster());
			while (it.hasNext()){
				String boradMaster = (String) it.next();
				logger.info("boradMaster(OneBoardMessage for one board):"+boradMaster);
				String re = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";
				Pattern pattern = Pattern.compile(re);
				Matcher matcher = pattern.matcher(boradMaster);
				if (matcher.matches()) {
					if(boradMaster.indexOf("*")==-1){
						BoardMaster oldBoard = (BoardMaster) b.getBoardMaster().get(boradMaster);
						UserInfo userForBoard = this.userService.findUserInfoByUserName(boradMaster);
						 
						String showName = userForBoard.getNickName();
						logger.info("showName(OneBoardMessage for one board's nickname and it's not handle):"+showName);
						String re1 = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";
						Pattern pattern1 = Pattern.compile(re1);
						Matcher matcher1 = pattern1.matcher(showName);
						Matcher matcher2 = pattern1.matcher(userForBoard.getUserName());
						if (matcher1.matches()&&showName.length()>7) {
							showName = showName.substring(0, 3) + "****" + showName.substring(7);
							logger.info("showName(OneBoardMessage for one board's nickname and it's mobile):"+showName);
						}else if(matcher2.matches()&&(showName==null||showName.equals("null")||showName.length()==0)&&userForBoard.getUserName().length()>7){
							showName = userForBoard.getUserName().substring(0, 3) + "****" + userForBoard.getUserName().substring(7);
							logger.info("showName(OneBoardMessage for one board's username and this nickname is undefind):"+showName);
						}
						
						oldBoard.setUserName(showName);
					}
				}
			}
			
			List bclist = this.getBoardService().findBoardsByParentID(b.getId(), 1, isHidden,
					Constant.FIND_BOARDS_BY_ORDER);
			
			
			for (int j = 0; j < bclist.size(); j++) {
				Board board = (Board) bclist.get(j);
				
		        Collection<String> name = board.getBoardMaster().keySet();
		        Iterator len = name.iterator();
				while (len.hasNext()){
					String boradMaster2 = (String) len.next();
					logger.info("child-boradMaster(OneBoardMessage for one board):"+boradMaster2);
					String re2 = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";
					Pattern pattern2 = Pattern.compile(re2);
					Matcher matcher2 = pattern2.matcher(boradMaster2);
					if (matcher2.matches()) {
						if(boradMaster2.indexOf("*")==-1){
							BoardMaster oldBoard = (BoardMaster) board.getBoardMaster().get(boradMaster2);
							UserInfo userForBoard = this.userService.findUserInfoByUserName(boradMaster2);
							 
							String showName = userForBoard.getNickName();
							logger.info("child-showName(OneBoardMessage for one board's nickname and it's not handle):"+showName);
							String re1 = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";
							Pattern pattern1 = Pattern.compile(re1);
							Matcher matcher1 = pattern1.matcher(showName);
							Matcher matcher3 = pattern1.matcher(userForBoard.getUserName());
							if (matcher1.matches()&&showName.length()>7) {
								showName = showName.substring(0, 3) + "****" + showName.substring(7);
								logger.info("child-showName(OneBoardMessage for one board's nickname and it's mobile):"+showName);
							}else if(matcher3.matches()&&(showName==null||showName.equals("null")||showName.length()==0)&&userForBoard.getUserName().length()>7){
								showName = userForBoard.getUserName().substring(0, 3) + "****" + userForBoard.getUserName().substring(7);
								logger.info("child-showName(OneBoardMessage for one board's username and this nickname is undefind):"+showName);
							}
							
							oldBoard.setUserName(showName);
						}
					}
				}
			}
			
			
			this.boardMap.put(b.getId(), bclist);
		}

		this.setUsePass(this.getSysConfig().isUsePass());
		this.setUseAuthCode(this.getSysConfig().isUseAuthCode());

		if (Constant.USE_URL_REWRITE) {
			tourl = this.getBasePath() + "main.html";
		} else {
			tourl = this.getBasePath() + BBSCSUtil.getActionMappingURLWithoutPrefix("main");
		}

		if (this.getSysConfig().isUsePass()) {
			this.setActionUrl(this.getSysConfig().getPassUrl());
		} else {
			this.setActionUrl(this.getBasePath() + BBSCSUtil.getActionMappingURLWithoutPrefix("login"));
		}
		this.setForumSiteInit();
		return SUCCESS;
	}

	private void setForumSiteInit() {
		//String url = this.getSysConfig().getForumUrl();
		String url = request.getContextPath() ;
		/*if (url == null) {
			url = "bbs.ruyicai.com";
		} else if (url.startsWith("http://")) {
			url = url.substring("http://".length(), url.length());
		}*/
		this.setForumSite(url);
	}

}
