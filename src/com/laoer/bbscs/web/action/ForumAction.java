package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.bean.Image;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.service.BoardService;
import com.laoer.bbscs.service.ForumService;
import com.laoer.bbscs.service.ImageService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.service.web.PageList;
import com.laoer.bbscs.service.web.Pages;
import com.laoer.bbscs.web.interceptor.RequestBasePathAware;
import com.laoer.bbscs.web.ui.OptionsString;

public class ForumAction extends BaseBoardAction implements RequestBasePathAware {

	/**
	 *
	 */
	private static final long serialVersionUID = -7836836700941164548L;

	private String viewMode;

	private boolean urlRewrite = false;

	public String getViewMode() {
		return viewMode;
	}

	public void setViewMode(String viewMode) {
		this.viewMode = viewMode;
	}

	private ForumService forumService;

	private BoardService boardService;

	private ForumService forumHistoryService;
    private ImageService imageService;
    
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	private SysConfig sysConfig;

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

	public ForumService getForumHistoryService() {
		return forumHistoryService;
	}

	public void setForumHistoryService(ForumService forumHistoryService) {
		this.forumHistoryService = forumHistoryService;
	}

	public boolean isUrlRewrite() {
		return urlRewrite;
	}

	public void setUrlRewrite(boolean urlRewrite) {
		this.urlRewrite = urlRewrite;
	}

	private List boardList;

	public List getBoardList() {
		return boardList;
	}

	public void setBoardList(List boardList) {
		this.boardList = boardList;
	}

	private List<OptionsString> boardSelectValues = new ArrayList<OptionsString>();

	public List<OptionsString> getBoardSelectValues() {
		return boardSelectValues;
	}

	public void setBoardSelectValues(List<OptionsString> boardSelectValues) {
		this.boardSelectValues = boardSelectValues;
	}

	private String basePath;

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBasePath() {
		return basePath;
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

	private String bids;

	public String getBids() {
		return bids;
	}

	public void setBids(String bids) {
		this.bids = bids;
	}

	private String forumSite = "bbs.ruyicai.com";

	public String getForumSite() {
		return forumSite;
	}

	public void setForumSite(String forumSite) {
		this.forumSite = forumSite;
	}

	private List archivesMonth;

	public List getArchivesMonth() {
		return archivesMonth;
	}

	public void setArchivesMonth(List archivesMonth) {
		this.archivesMonth = archivesMonth;
	}

	private void setViewModeValue(int mode) {
		if (mode == 0) {
			this.setViewMode("www");
		}
		if (mode == 1) {
			this.setViewMode("bbs");
		}
		if (mode == 2) {
			this.setViewMode("lastre");
		}
	}

	
	
	public String execute() {
		if (this.getAction().equalsIgnoreCase("index")) {
			return this.index();
		} else if (this.getAction().equalsIgnoreCase("hot")) {
			return this.hot();
		} else if (this.getAction().equalsIgnoreCase("commend")) {
			return this.commend();
		} else if (this.getAction().equalsIgnoreCase("history")) {
			return this.history();
		} else {
			return this.index();
		}
	}

	public String index() {
		
		if (StringUtils.isNotBlank(this.getViewMode())) {
			if (this.getUserCookie().getForumViewMode() != this.getViewModeToInt(this.getViewMode())) {
				this.getUserCookie().addForumViewMode(this.getViewModeToInt(this.getViewMode()));
			} else {
				setViewModeValue(this.getUserCookie().getForumViewMode());
			}
		} else {
			setViewModeValue(this.getUserCookie().getForumViewMode());
		}

		int isHidden = 0;
		if (this.getUserSession().isHaveSpecialPermission(Constant.SPERMISSION_CAN_SEE_HIDDEN_BOARD)) { // 如果用户有查看隐藏版区的权限
			isHidden = -1;
		}

		this.setUrlRewriteValue();
		if (this.getBoard().getBoardType() == 1) {
			this.setBoardList(this.getBoardService().findBoardsByParentID(this.getBid(), 1, isHidden,
					Constant.FIND_BOARDS_BY_ORDER));
			return "forumBoard";
		} else {
			List<Image> imageList=imageService.getBylotnoid(Integer.valueOf(String.valueOf(getBid())), 1);
			if(imageList.isEmpty()){
				getMapsession().put("pictureImage1", null);
				getMapsession().put("pictureImage2", null);
			}else{
				getMapsession().put("pictureImage1", imageList.get(0));
				if(imageList.size()>1){
					getMapsession().put("pictureImage2", imageList.get(1));
				}
			}
			
			this.setBids(String.valueOf(this.getBid()));
			this.setBoardSelectValues(isHidden);
			Pages pages = new Pages();
			pages.setPage(this.getPage());
			pages.setPerPageNum(this.getUserForumPerNum(this.getUserCookie().getForumPerNum(), this.getSysConfig()
					.getForumPrePage()));
			if (this.getTotal() > 0) {
				pages.setTotalNum(this.getTotal());
			}
			// pages.setPerPageNum(1);
			if (Constant.USE_URL_REWRITE) {
				pages.setUseUrlRewrite(Constant.USE_URL_REWRITE);
				pages.setFileName("forum-" + this.getAction() + "-" + this.getBid() + "-0-{page}-{total}.html");

			} else {
				pages.setFileName(this.getBasePath()
						+ BBSCSUtil.getActionMappingURLWithoutPrefix("forum?action=" + this.getAction() + "&bid="
								+ this.getBid()));
			}
			if (this.getTagId().equals("0")) {
				if (this.getUserCookie().getForumViewMode() == 1) {
					this.setPageList(this.getForumService().findForumsAll(this.getBid(), pages));
				} else if (this.getUserCookie().getForumViewMode() == 2) {
					this.setPageList(this.getForumService().findForumsMainLastRe(this.getBid(), pages));
				} else {
					this.setPageList(this.getForumService().findForumsMainWWW(this.getBid(), pages));
				}
			} else {
				if (this.getUserCookie().getForumViewMode() == 1) {
					this.setPageList(this.getForumService().findForumsAll(this.getBid(), this.getTagId(), pages));
				} else if (this.getUserCookie().getForumViewMode() == 2) {
					this
							.setPageList(this.getForumService().findForumsMainLastRe(this.getBid(), this.getTagId(),
									pages));
				} else {
					this.setPageList(this.getForumService().findForumsMainWWW(this.getBid(), this.getTagId(), pages));
				}
			}
			this.setParentBoards();
			this.setForumSiteInit();
			return "forum";
		}
	}

	public String hot() {
		int isHidden = 0;
		if (this.getUserSession().isHaveSpecialPermission(Constant.SPERMISSION_CAN_SEE_HIDDEN_BOARD)) { // 如果用户有查看隐藏版区的权限
			isHidden = -1;
		}
		this.setUrlRewriteValue();
		if (this.getBoard().getBoardType() != 3) {
			return ERROR;
		}
		this.setBids(String.valueOf(this.getBid()));
		this.setBoardSelectValues(isHidden);
		Pages pages = new Pages();
		pages.setPage(this.getPage());
		pages.setPerPageNum(this.getUserForumPerNum(this.getUserCookie().getForumPerNum(), this.getSysConfig()
				.getForumPrePage()));
		if (this.getTotal() > 0) {
			pages.setTotalNum(this.getTotal());
		}
		if (Constant.USE_URL_REWRITE) {
			pages.setUseUrlRewrite(Constant.USE_URL_REWRITE);
			pages.setFileName("forum-" + this.getAction() + "-" + this.getBid() + "-0-{page}-{total}.html");

		} else {
			pages.setFileName(this.getBasePath()
					+ BBSCSUtil.getActionMappingURLWithoutPrefix("forum?action=" + this.getAction() + "&bid="
							+ this.getBid()));
		}
		this.setPageList(this.getForumService().findForumsHotTopic(this.getBid(), this.getSysConfig().getForumHotRes(),
				this.getSysConfig().getForumHotViews(), pages));
		this.setParentBoards();
		this.setForumSiteInit();
		return "forum";
	}

	public String commend() {
		int isHidden = 0;
		if (this.getUserSession().isHaveSpecialPermission(Constant.SPERMISSION_CAN_SEE_HIDDEN_BOARD)) { // 如果用户有查看隐藏版区的权限
			isHidden = -1;
		}
		this.setUrlRewriteValue();
		if (this.getBoard().getBoardType() != 3) {
			return ERROR;
		}
		this.setBids(String.valueOf(this.getBid()));
		this.setBoardSelectValues(isHidden);
		Pages pages = new Pages();
		pages.setPage(this.getPage());
		pages.setPerPageNum(this.getUserForumPerNum(this.getUserCookie().getForumPerNum(), this.getSysConfig()
				.getForumPrePage()));
		if (this.getTotal() > 0) {
			pages.setTotalNum(this.getTotal());
		}
		if (Constant.USE_URL_REWRITE) {
			pages.setUseUrlRewrite(Constant.USE_URL_REWRITE);
			pages.setFileName("forum-" + this.getAction() + "-" + this.getBid() + "-0-{page}-{total}.html");

		} else {
			pages.setFileName(this.getBasePath()
					+ BBSCSUtil.getActionMappingURLWithoutPrefix("forum?action=" + this.getAction() + "&bid="
							+ this.getBid()));
		}
		long topBid = this.getBoard().getTopBid();

		this.setPageList(this.getForumService().findForumsCommend(this.getBid(), topBid, pages));
		this.setParentBoards();
		this.setForumSiteInit();
		return "forum";
	}

	public String history() {
		int isHidden = 0;
		if (this.getUserSession().isHaveSpecialPermission(Constant.SPERMISSION_CAN_SEE_HIDDEN_BOARD)) { // 如果用户有查看隐藏版区的权限
			isHidden = -1;
		}
		this.setBids(String.valueOf(this.getBid()));
		this.setBoardSelectValues(isHidden);
		this.setUrlRewriteValue();
		if (this.getBoard().getBoardType() != 3) {
			return ERROR;
		}
		Pages pages = new Pages();
		pages.setPage(this.getPage());
		pages.setPerPageNum(this.getUserForumPerNum(this.getUserCookie().getForumPerNum(), this.getSysConfig()
				.getForumPrePage()));
		if (this.getTotal() > 0) {
			pages.setTotalNum(this.getTotal());
		}
		if (Constant.USE_URL_REWRITE) {
			pages.setUseUrlRewrite(Constant.USE_URL_REWRITE);
			pages.setFileName("forum-" + this.getAction() + "-" + this.getBid() + "-0-{page}-{total}.html");

		} else {
			pages.setFileName(this.getBasePath()
					+ BBSCSUtil.getActionMappingURLWithoutPrefix("forum?action=" + this.getAction() + "&bid="
							+ this.getBid()));
		}
		this.setPageList(this.getForumHistoryService().findForumsMainWWW(this.getBid(), pages));
		this.setParentBoards();
		this.setArchivesMonth(BBSCSUtil.getArchivesMonths(this.getBid()));
		return "forumHistory";
	}

	private int getViewModeToInt(String action) {
		if (StringUtils.isBlank(action) || action.equalsIgnoreCase("www")) {
			return 0;
		}
		if (action.equalsIgnoreCase("lastre")) {
			return 2;
		}
		if (action.equalsIgnoreCase("bbs")) {
			return 1;
		}
		return 0;
	}

	private void setUrlRewriteValue() {
		this.setUrlRewrite(Constant.USE_URL_REWRITE);
	}

	private void setBoardSelectValues(int isHidden) {
		List blist = this.getBoardService().findBoardsByParentID(0, 1, isHidden, Constant.FIND_BOARDS_BY_ORDER);
		// System.out.println(boardList);

		for (int i = 0; i < blist.size(); i++) {
			Board b = (Board) blist.get(i);
			this.boardSelectValues.add(new OptionsString(String.valueOf(b.getId()), BBSCSUtil.getBoardPrefixLine(b
					.getLevel(), "-")
					+ b.getBoardName()));
			List bclist = this.getBoardService().findBoardsByParentID(b.getId(), 1, isHidden,
					Constant.FIND_BOARDS_BY_ORDER);
			for (int j = 0; j < bclist.size(); j++) {
				Board bc = (Board) bclist.get(j);
				this.boardSelectValues.add(new OptionsString(String.valueOf(bc.getId()), BBSCSUtil.getBoardPrefixLine(
						bc.getLevel(), "-")
						+ bc.getBoardName()));
			}

		}
	}

	private int getUserForumPerNum(int userNum, int sysNum) {
		if (userNum == 0) {
			return sysNum;
		} else {
			return userNum;
		}
	}

	private void setParentBoards() {
		this.setParentBoards(this.getBoardService().findBoardsInIDs(this.getBoard().getParentIDs()));
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
