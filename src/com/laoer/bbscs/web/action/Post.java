package com.laoer.bbscs.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.laoer.bbscs.bean.BoardTag;
import com.laoer.bbscs.bean.Forum;
import com.laoer.bbscs.bean.ForumMain;
import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.comm.SysOptionsValues;
import com.laoer.bbscs.comm.Util;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.fio.UploadFile;
import com.laoer.bbscs.service.BoardService;
import com.laoer.bbscs.service.ForumService;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.interceptor.RemoteAddrAware;
import com.laoer.bbscs.web.interceptor.RequestBasePathAware;
import com.laoer.bbscs.web.ui.OptionsInt;
import com.laoer.bbscs.web.ui.OptionsString;

public class Post extends BaseBoardAction implements RemoteAddrAware, RequestBasePathAware {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(Post.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -1688184026206814221L;

	private String detail;

	private int editType;

	private boolean emailInform;

	private int face;

	private String id;

	private int inpages = 1;

	private int isHidden;

	private int isQuote;

	private String mainID;

	private boolean msgInform;

	private int needArtNum;

	private int postType;

	private int sign;

	private String title;

	private int titleColor;

	private int totalnum = 0;

	private int useCoin;

	private String parentID;

	private int userBlog;

	private File upload;

	private String uploadFileName;

	private String uploadContentType;

	private int fcpage = 1;

	private boolean previewAttach;

	private ForumService forumService;

	private BoardService boardService;

	private SysConfig sysConfig;

	private UserService userService;

	private SysOptionsValues sysOptionsValues;

	private String userRemoteAddr = "";

	private int postHiddenTypeMoney;

	private int postHiddenTypeRe;

	private int postHiddenTypeArtNum;

	private String titleColorOptions = "";

	private String upfileIframeUrl;

	private String ajaxCodeid;

	private String ajaxMsg;

	private List attachFiles;

	private List<OptionsInt> postPriceValues = new ArrayList<OptionsInt>();

	private List<OptionsString> tagValues = new ArrayList<OptionsString>();

	public BoardService getBoardService() {
		return boardService;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getEditType() {
		return editType;
	}

	public void setEditType(int editType) {
		this.editType = editType;
	}

	public boolean getEmailInform() {
		return emailInform;
	}

	public void setEmailInform(boolean emailInform) {
		this.emailInform = emailInform;
	}

	public int getFace() {
		return face;
	}

	public void setFace(int face) {
		this.face = face;
	}

	public int getFcpage() {
		return fcpage;
	}

	public void setFcpage(int fcpage) {
		this.fcpage = fcpage;
	}

	public ForumService getForumService() {
		return forumService;
	}

	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
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

	public int getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(int isHidden) {
		this.isHidden = isHidden;
	}

	public int getIsQuote() {
		return isQuote;
	}

	public void setIsQuote(int isQuote) {
		this.isQuote = isQuote;
	}

	public String getMainID() {
		return mainID;
	}

	public void setMainID(String mainID) {
		this.mainID = mainID;
	}

	public boolean getMsgInform() {
		return msgInform;
	}

	public void setMsgInform(boolean msgInform) {
		this.msgInform = msgInform;
	}

	public int getNeedArtNum() {
		return needArtNum;
	}

	public void setNeedArtNum(int needArtNum) {
		this.needArtNum = needArtNum;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public int getPostType() {
		return postType;
	}

	public void setPostType(int postType) {
		this.postType = postType;
	}

	public boolean getPreviewAttach() {
		return previewAttach;
	}

	public void setPreviewAttach(boolean previewAttach) {
		this.previewAttach = previewAttach;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(int titleColor) {
		this.titleColor = titleColor;
	}

	public int getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public int getUseCoin() {
		return useCoin;
	}

	public void setUseCoin(int useCoin) {
		this.useCoin = useCoin;
	}

	public int getUserBlog() {
		return userBlog;
	}

	public void setUserBlog(int userBlog) {
		this.userBlog = userBlog;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.userRemoteAddr = remoteAddr;
	}

	public String getUserRemoteAddr() {
		return userRemoteAddr;
	}

	public void setUserRemoteAddr(String userRemoteAddr) {
		this.userRemoteAddr = userRemoteAddr;
	}

	public int getPostHiddenTypeArtNum() {
		return postHiddenTypeArtNum;
	}

	public void setPostHiddenTypeArtNum(int postHiddenTypeArtNum) {
		this.postHiddenTypeArtNum = postHiddenTypeArtNum;
	}

	public int getPostHiddenTypeMoney() {
		return postHiddenTypeMoney;
	}

	public void setPostHiddenTypeMoney(int postHiddenTypeMoney) {
		this.postHiddenTypeMoney = postHiddenTypeMoney;
	}

	public int getPostHiddenTypeRe() {
		return postHiddenTypeRe;
	}

	public void setPostHiddenTypeRe(int postHiddenTypeRe) {
		this.postHiddenTypeRe = postHiddenTypeRe;
	}

	public List<OptionsInt> getPostPriceValues() {
		return postPriceValues;
	}

	public void setPostPriceValues(List<OptionsInt> postPriceValues) {
		this.postPriceValues = postPriceValues;
	}

	public List<OptionsString> getTagValues() {
		return tagValues;
	}

	public void setTagValues(List<OptionsString> tagValues) {
		this.tagValues = tagValues;
	}

	public String getTitleColorOptions() {
		return titleColorOptions;
	}

	public void setTitleColorOptions(String titleColorOptions) {
		this.titleColorOptions = titleColorOptions;
	}

	public SysOptionsValues getSysOptionsValues() {
		return sysOptionsValues;
	}

	public void setSysOptionsValues(SysOptionsValues sysOptionsValues) {
		this.sysOptionsValues = sysOptionsValues;
	}

	private String basePath;

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBasePath() {
		return basePath;
	}

	private String forwardUrl;

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public String add() {
		if (this.checkBeforePost().equals(ERROR)) {
			return ERROR;
		}
		this.setAction("addsave");
		if (this.getUserCookie().getEditType() == -1) {
			if (this.getSysConfig().getEditInterface() == 0) {
				this.setEditType(0);
			} else if (this.getSysConfig().getEditInterface() == 1) {
				this.setEditType(1);
			} else {
				this.setEditType(2);
			}
		} else {
			this.setEditType(this.getUserCookie().getEditType());
		}
		this.setTitleColor(0);
		this.setPostType(0);
		this.setSign(-1);
		this.setUserBlog(0);
		this.setPreviewAttach(true);
		this.setNeedsAttribute(false);
		return INPUT;
	}

	public String addsave() {
		if (this.checkBeforePost().equals(ERROR)) {
			return ERROR;
		}
		String title = StringUtils.trimToEmpty(this.getTitle());
		String detail = StringUtils.trimToEmpty(this.getDetail());
		if (StringUtils.isBlank(title) || BBSCSUtil.getSysCharsetStrLength(title) > 90) {
			this.addActionError(this.getText("error.post.titletoolong"));
			this.setNeedsAttribute(false);
			return INPUT;
		}
		title = this.getSysConfig().bestrowScreen(title);
		if (StringUtils.isBlank(detail)) {
			this.addActionError(this.getText("error.post.nodetail"));
			this.setNeedsAttribute(false);
			return INPUT;
		}

		if (BBSCSUtil.getSysCharsetStrLength(detail) < this.getSysConfig().getPostMinSize()
				|| BBSCSUtil.getSysCharsetStrLength(detail) > this.getSysConfig().getPostMaxSize()) {
			this.addActionError(this.getText("error.post.texttoolong", new String[] {
					String.valueOf(this.getSysConfig().getPostMinSize()),
					String.valueOf(this.getSysConfig().getPostMaxSize()) }));
			this.setNeedsAttribute(false);
			return INPUT;
		}
		detail = this.getSysConfig().bestrowScreen(detail);
		UploadFile uploadFile = null;
		if (this.getUpload() != null && StringUtils.isNotBlank(this.getUploadFileName())
				&& this.getUpload().length() > 0) {
			if (!this.getSysConfig().isAllowAttachFileType(this.getUploadFileName().toLowerCase())
					|| this.getUpload().length() > (this.getSysConfig().getAttachFileSize() * 1024)) {
				this.addActionError(this.getText("post.upnotice", new String[] {
						String.valueOf(this.getSysConfig().getAttachFileSize()),
						this.getSysConfig().getAttachFileType() }));
				this.setNeedsAttribute(false);
				return INPUT;
			}

			try {
				uploadFile = new UploadFile();
				uploadFile.setFileName(this.getUploadFileName().toLowerCase());
				uploadFile.setInputStream(new FileInputStream(this.getUpload()));
			} catch (FileNotFoundException e) {
				logger.error(e);
				uploadFile = null;
			}
		}

		long nowtime = System.currentTimeMillis();
		Forum f = new ForumMain();
		f.setAgree(0);
		f.setAmend("");
		f.setArtSize(BBSCSUtil.getSysCharsetStrLength(detail));
		f.setAttachFileName(new ArrayList());
		f.setAuditing(this.getBoard().getAuditPost());
		if (this.getUserSession().isHaveBoardSpecialPermission(Constant.SPERMISSION_POST_NOT_AUDITING)){
			f.setAuditing(0);
		}
		if (this.getBoard().getAuditAttach() == 1 && uploadFile != null) {
			f.setAuditingAttachFile(this.getBoard().getAuditAttach());
		} else {
			f.setAuditingAttachFile(0);
		}
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
		f.setDetail(detail);
		f.setDoEliteName("");
		f.setDoEliteTime(0);
		f.setEditType(this.getEditType());
		f.setElite(0);
		f.setEliteID(0);
		f.setFace(this.getFace());
		f.setHaveAttachFile(0);
		f.setIpAddress(this.getUserRemoteAddr());
		f.setIsHidden(this.getIsHidden());
		if (this.getIsHidden() == 2) {
			f.setIsHiddenValue(this.getUseCoin());
		} else if (this.getIsHidden() == 3) {
			f.setIsHiddenValue(this.getNeedArtNum());
		} else {
			f.setIsHiddenValue(0);
		}
		f.setIsLock(0);
		f.setIsNew(1);
		f.setIsTop(0);
		f.setIsVote(0);
		f.setLastPostNickName("---");
		f.setLastPostTitle("");
		f.setLastPostUserName("---");
		f.setLastTime(nowtime);
		f.setMainID("");
		f.setNickName(this.getUserSession().getNickName());
		f.setParentID("");
		f.setPostTime(nowtime);
		f.setPostType(this.getPostType());
		f.setQuoteText("");
		f.setReNum(0);
		if (this.getSign() == -1) {
			f.setSign("");
		} else {
			f.setSign(this.getUserSession().getSignDetail()[this.getSign()]);
		}
		f.setTitle(title);
		f.setTitleColor(this.getTitleColor());
		f.setUserID(this.getUserSession().getId());
		f.setUserName(this.getUserSession().getUserName());
		f.setEmailInform(this.boolean2int(this.getEmailInform()));
		f.setMsgInform(this.boolean2int(this.getMsgInform()));
		f.setUserBlog(this.getUserBlog());
		if (this.getBoard().getAuditPost() == 0) {
			f.setIndexStatus(Constant.INDEX_STATUS_NO_INDEX);
		} else {
			f.setIndexStatus(Constant.INDEX_STATUS_AUDIT);
		}

		f.setTagID(this.getTagId());
		if (this.getTagId().equals("0")) {
			f.setTagName("");
		} else {
			f.setTagName(this.getBoard().getBoardTagById(this.getTagId()).getTagName());
		}
		// f.setIsGuest(0);
		f.setPreviewAttach(this.boolean2int(this.getPreviewAttach()));
		try {
			UserInfo ui = this.getUserService().findUserInfoById(this.getUserSession().getId());
			if (ui == null) {
				this.addActionError(this.getText("error.post.guest"));
				this.setNeedsAttribute(false);
				return INPUT;
			}

			f = this.getForumService().createForum(f, this.getBoard(), ui, uploadFile);
			this.getUserCookie().addLastPostTime();
			if (this.getBoard().getAuditPost() == 0 || this.getUserSession().isHaveBoardSpecialPermission(Constant.SPERMISSION_POST_NOT_AUDITING)) {
				if (Constant.USE_URL_REWRITE) {
					this.setForwardUrl(this.getBasePath() + "forum-index-" + this.getBid() + "-" + this.getTagId()
							+ "-1-0.html");
				} else {
					this.setForwardUrl(BBSCSUtil.getActionMappingURLWithoutPrefix("forum?action=index&bid="
							+ this.getBid() + "&tagId=" + this.getTagId()));
				}
			} else {
				this.setForwardUrl(BBSCSUtil.getActionMappingURLWithoutPrefix("postWaitAudit?bid=" + this.getBid()));
			}
			
			if(f.getAttachFileName().size()>0){
				//水印文件
				File file = new File(getClass().getResource("/").getPath()); 
				String pressImg = file.toString().substring(0,file.toString().indexOf("WEB-INF")) + Constant.WATERMARK_PATH;
				//上传的图片
				String targetImg = BBSCSUtil.getUpFilePath(this.getBid(), nowtime) + f.getAttachFileName().get(0);
				//缩略图文件
				String targetImgSmall = BBSCSUtil.getUpFilePath(this.getBid(), nowtime) + f.getAttachFileName().get(0) + Constant.IMG_SMALL_FILEPREFIX;
				BBSCSUtil.pressImage(pressImg, targetImg);
				BBSCSUtil.pressImage(pressImg, targetImgSmall);
			}
			return SUCCESS;
		} catch (BbscsException ex) {
			this.addActionError(this.getText("error.post.add"));
			this.setNeedsAttribute(false);
			return INPUT;
		}
	}

	public String re() {
		if (this.checkBeforePost().equals(ERROR)) {
			return ERROR;
		}
		this.setAction("addre");

		if (this.getUserCookie().getEditType() == -1) {
			if (this.getSysConfig().getEditInterface() == 0) {
				this.setEditType(0);
			} else if (this.getSysConfig().getEditInterface() == 1) {
				this.setEditType(1);
			} else {
				this.setEditType(2);
			}
		} else {
			this.setEditType(this.getUserCookie().getEditType());
		}
		this.setTitleColor(0);
		this.setPostType(0);
		this.setSign(-1);
		this.setUserBlog(0);
		this.setTitle(Constant.RE);
		this.setPreviewAttach(true);
		this.setNeedsAttribute(true);
		return INPUT;
	}

	public String requote() {
		if (this.checkBeforePost().equals(ERROR)) {
			return ERROR;
		}
		this.setAction("addrequote");

		if (this.getUserCookie().getEditType() == -1) {
			if (this.getSysConfig().getEditInterface() == 0) {
				this.setEditType(0);
			} else if (this.getSysConfig().getEditInterface() == 1) {
				this.setEditType(1);
			} else {
				this.setEditType(2);
			}
		} else {
			this.setEditType(this.getUserCookie().getEditType());
		}
		this.setTitleColor(0);
		this.setPostType(0);
		this.setSign(-1);
		this.setUserBlog(0);
		this.setTitle(Constant.RE);
		this.setPreviewAttach(true);
		this.setNeedsAttribute(true);
		return INPUT;
	}

	public String addqre() {
		if (this.checkBeforePost().equals(ERROR)) {
			return ERROR;
		}
		String title = StringUtils.trimToEmpty(this.getTitle());
		String detail = StringUtils.trimToEmpty(this.getDetail());
		int isAnonymousReply = Integer.parseInt(request.getParameter("AnonymousReply"));
		if (StringUtils.isBlank(title) || BBSCSUtil.getSysCharsetStrLength(title) > 90) {
			this.addActionError(this.getText("error.post.titletoolong"));
			return ERROR;
		}
		title = this.getSysConfig().bestrowScreen(title);
		if (StringUtils.isBlank(detail)) {
			this.addActionError(this.getText("error.post.nodetail"));
			return ERROR;
		}

		if (BBSCSUtil.getSysCharsetStrLength(detail) < this.getSysConfig().getPostMinSize()
				|| BBSCSUtil.getSysCharsetStrLength(detail) > this.getSysConfig().getPostMaxSize()) {
			this.addActionError(this.getText("error.post.texttoolong", new String[] {
					String.valueOf(this.getSysConfig().getPostMinSize()),
					String.valueOf(this.getSysConfig().getPostMaxSize()) }));
			return ERROR;
		}
		detail = this.getSysConfig().bestrowScreen(detail);

		Forum mainForum = this.getForumService().findForumByID(this.getMainID(), this.getBid());
		if (mainForum == null) {
			this.addActionError(this.getText("error.post.getpost"));
			return ERROR;
		}
		if (mainForum.getIsLock() == 1) {
			this.addActionError(this.getText("error.post.islock"));
			return ERROR;
		}

		long nowtime = System.currentTimeMillis();
		Forum f = new ForumMain();
		f.setAgree(0);
		f.setAmend("");
		f.setArtSize(BBSCSUtil.getSysCharsetStrLength(detail));
		f.setAttachFileName(new ArrayList());
		f.setAuditing(this.getBoard().getAuditPost());
		if (this.getUserSession().isHaveBoardSpecialPermission(Constant.SPERMISSION_POST_NOT_AUDITING)){
			f.setAuditing(0);
		}
		// f.setAuditingAttachFile(board.getAuditAttach());
		f.setAuditingAttachFile(0);
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
		f.setDetail(detail);
		f.setDoEliteName("");
		f.setDoEliteTime(0);
		f.setEditType(this.getEditType());
		f.setElite(0);
		f.setEliteID(0);
		f.setFace(this.getFace());
		f.setHaveAttachFile(0);
		f.setIpAddress(this.getUserRemoteAddr());
		f.setIsHidden(this.getIsHidden());
		if (this.getIsHidden() == 2) {
			f.setIsHiddenValue(this.getUseCoin());
		} else if (this.getIsHidden() == 3) {
			f.setIsHiddenValue(this.getNeedArtNum());
		} else {
			f.setIsHiddenValue(0);
		}
		f.setIsLock(0);
		f.setIsNew(0);
		f.setIsTop(0);
		f.setIsVote(0);
		f.setLastPostNickName("---");
		f.setLastPostTitle("");
		f.setLastPostUserName("---");
		f.setLastTime(nowtime);
		f.setMainID(this.getMainID());
		f.setNickName(this.getUserSession().getNickName());
		f.setParentID(this.getParentID());
		f.setPostTime(nowtime);
		f.setPostType(this.getPostType());
		f.setQuoteText("");
		f.setReNum(0);
		if (this.getSign() == -1) {
			f.setSign("");
		} else {
			f.setSign(this.getUserSession().getSignDetail()[this.getSign()]);
		}
		f.setTitle(title);
		f.setTitleColor(this.getTitleColor());
		f.setUserID(this.getUserSession().getId());
		f.setUserName(this.getUserSession().getUserName());
		f.setEmailInform(this.boolean2int(this.getEmailInform()));
		f.setMsgInform(this.boolean2int(this.getMsgInform()));
		f.setUserBlog(0);
		if (this.getBoard().getAuditPost() == 0) {
			f.setIndexStatus(Constant.INDEX_STATUS_NO_INDEX);
		} else {
			f.setIndexStatus(Constant.INDEX_STATUS_AUDIT);
		}

		f.setTagID(mainForum.getTagID());
		f.setTagName(mainForum.getTagName());
		// f.setIsGuest(0);

		f.setPreviewAttach(0);
		f.setIsAnonymousReply(isAnonymousReply);

		int userPostPerNum = this.getUserPostPerNum(this.getUserCookie().getPostPerNum(), this.getSysConfig()
				.getPostPerPage());
		int inpages = (int) Math.ceil((this.getTotalnum() + 1) + userPostPerNum - 1) / userPostPerNum;
		if (mainForum.getIsHidden() == 1) {
			inpages = 1;
		}
		try {
			UserInfo ui = this.getUserService().findUserInfoById(this.getUserSession().getId());
			if (ui == null) {
				this.addActionError(this.getText("error.post.guest"));
				return ERROR;
			}

			f = this.getForumService().createReForum(f, mainForum, this.getBoard(), ui, null, false);
			this.getUserCookie().addLastPostTime();
			if (this.getBoard().getAuditPost() == 0 || this.getUserSession().isHaveBoardSpecialPermission(Constant.SPERMISSION_POST_NOT_AUDITING)) {
				if (Constant.USE_URL_REWRITE) {
					this.setForwardUrl(this.getBasePath() + "read-topic-" + this.getBid() + "-" + this.getMainID()
							+ "-" + this.getTagId() + "-" + this.getFcpage() + "-index-" + inpages + ".html");
				} else {
					this.setForwardUrl(this.getBasePath()
							+ BBSCSUtil.getActionMappingURLWithoutPrefix("read?action=topic&id=" + this.getMainID()
									+ "&bid=" + this.getBid() + "&tagId=" + this.getTagId() + "&fcpage="
									+ this.getFcpage() + "&inpages=" + inpages));
				}
			} else {
				this.setForwardUrl(this.getBasePath()
						+ BBSCSUtil.getActionMappingURLWithoutPrefix("postWaitAudit?bid=" + this.getBid()));
			}
			return SUCCESS;
		} catch (BbscsException ex1) {
			this.addActionError(this.getText("error.post.add"));
			return ERROR;
		}
	}

	public String addre() {
		return this.addrepro(false);
	}

	public String addrequote() {
		return this.addrepro(true);
	}

	private String addrepro(boolean isQuote) {
		if (this.checkBeforePost().equals(ERROR)) {
			return ERROR;
		}
		String title = StringUtils.trimToEmpty(this.getTitle());
		String detail = StringUtils.trimToEmpty(this.getDetail());
		if (StringUtils.isBlank(title) || BBSCSUtil.getSysCharsetStrLength(title) > 90) {
			this.addActionError(this.getText("error.post.titletoolong"));
			this.setNeedsAttribute(true);
			return INPUT;
		}
		title = this.getSysConfig().bestrowScreen(title);
		if (StringUtils.isBlank(detail)) {
			this.addActionError(this.getText("error.post.nodetail"));
			this.setNeedsAttribute(true);
			return INPUT;
		}

		if (BBSCSUtil.getSysCharsetStrLength(detail) < this.getSysConfig().getPostMinSize()
				|| BBSCSUtil.getSysCharsetStrLength(detail) > this.getSysConfig().getPostMaxSize()) {
			this.addActionError(this.getText("error.post.texttoolong", new String[] {
					String.valueOf(this.getSysConfig().getPostMinSize()),
					String.valueOf(this.getSysConfig().getPostMaxSize()) }));
			this.setNeedsAttribute(true);
			return INPUT;
		}
		detail = this.getSysConfig().bestrowScreen(detail);

		UploadFile uploadFile = null;
		if (this.getUpload() != null && StringUtils.isNotBlank(this.getUploadFileName())
				&& this.getUpload().length() > 0) {
			if (!this.getSysConfig().isAllowAttachFileType(this.getUploadFileName().toLowerCase())
					|| this.getUpload().length() > (this.getSysConfig().getAttachFileSize() * 1024)) {
				this.addActionError(this.getText("post.upnotice", new String[] {
						String.valueOf(this.getSysConfig().getAttachFileSize()),
						this.getSysConfig().getAttachFileType() }));
				this.setNeedsAttribute(true);
				return INPUT;
			}

			try {
				uploadFile = new UploadFile();
				uploadFile.setFileName(this.getUploadFileName().toLowerCase());
				uploadFile.setInputStream(new FileInputStream(this.getUpload()));
			} catch (FileNotFoundException e) {
				logger.error(e);
				uploadFile = null;
			}
		}

		Forum mainForum = this.getForumService().findForumByID(this.getMainID(), this.getBid());
		if (mainForum == null) {
			this.addActionError(this.getText("error.post.getpost"));
			this.setNeedsAttribute(true);
			return INPUT;
		}
		if (mainForum.getIsLock() == 1) {
			this.addActionError(this.getText("error.post.islock"));
			this.setNeedsAttribute(true);
			return INPUT;
		}

		long nowtime = System.currentTimeMillis();
		Forum f = new ForumMain();
		f.setAgree(0);
		f.setAmend("");
		f.setArtSize(BBSCSUtil.getSysCharsetStrLength(detail));
		f.setAttachFileName(new ArrayList());
		f.setAuditing(this.getBoard().getAuditPost());
		if (this.getUserSession().isHaveBoardSpecialPermission(Constant.SPERMISSION_POST_NOT_AUDITING)){
			f.setAuditing(0);
		}
		// f.setAuditingAttachFile(board.getAuditAttach());
		if (this.getBoard().getAuditAttach() == 1 && uploadFile != null) {
			f.setAuditingAttachFile(this.getBoard().getAuditAttach());
		} else {
			f.setAuditingAttachFile(0);
		}
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
		f.setDetail(detail);
		f.setDoEliteName("");
		f.setDoEliteTime(0);
		f.setEditType(this.getEditType());
		f.setElite(0);
		f.setEliteID(0);
		f.setFace(this.getFace());
		f.setHaveAttachFile(0);
		f.setIpAddress(this.getUserRemoteAddr());
		f.setIsHidden(this.getIsHidden());
		if (this.getIsHidden() == 2) {
			f.setIsHiddenValue(this.getUseCoin());
		} else if (this.getIsHidden() == 3) {
			f.setIsHiddenValue(this.getNeedArtNum());
		} else {
			f.setIsHiddenValue(0);
		}
		f.setIsLock(0);
		f.setIsNew(0);
		f.setIsTop(0);
		f.setIsVote(0);
		f.setLastPostNickName("---");
		f.setLastPostTitle("");
		f.setLastPostUserName("---");
		f.setLastTime(nowtime);
		f.setMainID(this.getMainID());
		f.setNickName(this.getUserSession().getNickName());
		f.setParentID(this.getParentID());
		f.setPostTime(nowtime);
		f.setPostType(this.getPostType());
		f.setQuoteText("");
		f.setReNum(0);
		if (this.getSign() == -1) {
			f.setSign("");
		} else {
			f.setSign(this.getUserSession().getSignDetail()[this.getSign()]);
		}
		f.setTitle(title);
		f.setTitleColor(this.getTitleColor());
		f.setUserID(this.getUserSession().getId());
		f.setUserName(this.getUserSession().getUserName());
		f.setEmailInform(this.boolean2int(this.getEmailInform()));
		f.setMsgInform(this.boolean2int(this.getMsgInform()));
		f.setUserBlog(this.getUserBlog());
		if (this.getBoard().getAuditPost() == 0) {
			f.setIndexStatus(Constant.INDEX_STATUS_NO_INDEX);
		} else {
			f.setIndexStatus(Constant.INDEX_STATUS_AUDIT);
		}

		f.setTagID(mainForum.getTagID());
		f.setTagName(mainForum.getTagName());
		// f.setIsGuest(0);

		f.setPreviewAttach(this.boolean2int(this.getPreviewAttach()));

		int userPostPerNum = this.getUserPostPerNum(this.getUserCookie().getPostPerNum(), this.getSysConfig()
				.getPostPerPage());
		int inpages = (int) Math.ceil((this.getTotalnum() + 1) + userPostPerNum - 1) / userPostPerNum;
		if (mainForum.getIsHidden() == 1) {
			inpages = 1;
		}

		try {
			UserInfo ui = this.getUserService().findUserInfoById(this.getUserSession().getId());
			if (ui == null) {
				this.addActionError(this.getText("error.post.guest"));
				this.setNeedsAttribute(true);
				return INPUT;
			}

			f = this.getForumService().createReForum(f, mainForum, this.getBoard(), ui, uploadFile, isQuote);
			this.getUserCookie().addLastPostTime();
			if (this.getBoard().getAuditPost() == 0 || this.getUserSession().isHaveBoardSpecialPermission(Constant.SPERMISSION_POST_NOT_AUDITING)) {
				if (Constant.USE_URL_REWRITE) {
					this.setForwardUrl(this.getBasePath() + "read-topic-" + this.getBid() + "-" + this.getMainID()
							+ "-" + this.getTagId() + "-" + this.getFcpage() + "-index-" + inpages + ".html");
				} else {
					this.setForwardUrl(this.getBasePath()
							+ BBSCSUtil.getActionMappingURLWithoutPrefix("read?action=topic&id=" + this.getMainID()
									+ "&bid=" + this.getBid() + "&tagId=" + this.getTagId() + "&fcpage="
									+ this.getFcpage() + "&inpages=" + inpages));
				}
			} else {
				this.setForwardUrl(this.getBasePath()
						+ BBSCSUtil.getActionMappingURLWithoutPrefix("postWaitAudit?bid=" + this.getBid()));
			}
			return SUCCESS;
		} catch (BbscsException ex1) {
			this.addActionError(this.getText("error.post.add"));
			this.setNeedsAttribute(true);
			return INPUT;
		}
	}

	public String upfile() {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.addActionError(this.getText("error.post.getpost"));
			return RESULT_HTMLERROR;
		}
		if (!this.getUserSession().getId().equals(f.getUserID())) {

			this.addActionError(this.getText("error.post.upfile.user1", new String[] { this.getId() }));
			return RESULT_HTMLERROR;
		}
		this.setUpfileIframeUrl(this.getBasePath()
				+ BBSCSUtil.getActionMappingURLWithoutPrefix("post?action=upfileinput&id=" + this.getId() + "&bid="
						+ this.getBid()));
		return "upfileInPost";
	}

	public String upfileinput() {
		return "upfileInput";
	}

	public String upfiledo() {

		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());

		if (this.getSysConfig().getAttachmentNum() == 0) {
			if (f.getAttachFileName().size() >= 20) {
				this.setAjaxCodeid("2");
				this.setAjaxMsg(this.getText("error.post.upfile.num", new String[] { "20" }));
				return "forumUpComponent";
			}
		} else if (this.getSysConfig().getAttachmentNum() > 0
				&& f.getAttachFileName().size() >= this.getSysConfig().getAttachmentNum()) {

			this.setAjaxCodeid("2");
			this.setAjaxMsg(this.getText("error.post.upfile.num", new String[] { String.valueOf(this.getSysConfig()
					.getAttachmentNum()) }));
			return "forumUpComponent";
		}

		if (!this.getUserSession().getId().equals(f.getUserID())) {
			this.setAjaxCodeid("4");
			this.setAjaxMsg(this.getText("error.post.upfile.user"));
			return "forumUpComponent";
		}

		UploadFile uploadFile = null;
		if (this.getUpload() != null && StringUtils.isNotBlank(this.getUploadFileName())
				&& this.getUpload().length() > 0) {
			if (!this.getSysConfig().isAllowAttachFileType(this.getUploadFileName().toLowerCase())
					|| this.getUpload().length() > (this.getSysConfig().getAttachFileSize() * 1024)) {

				this.setAjaxCodeid("1");
				this.setAjaxMsg(this.getText("post.upnotice", new String[] {
						String.valueOf(this.getSysConfig().getAttachFileSize()),
						this.getSysConfig().getAttachFileType() }));

				return "forumUpComponent";

			}
			try {
				uploadFile = new UploadFile();
				uploadFile.setFileName(this.getUploadFileName().toLowerCase());
				uploadFile.setInputStream(new FileInputStream(this.getUpload()));
			} catch (FileNotFoundException ex2) {
				uploadFile = null;
				this.setAjaxCodeid("3");
				this.setAjaxMsg(this.getText("error.post.upfile.error"));
				return "forumUpComponent";

			}
		}

		try {
			if (this.getBoard().getAuditAttach() == 1) {
				f.setAuditingAttachFile(1);
			}
			f = this.getForumService().createForumUpFile(f, uploadFile);
			if (this.getBoard().getAuditAttach() == 0) {

				this.setAjaxCodeid("0");
				this.setAjaxMsg(this.getText("post.upfile.upok"));

			} else {
				this.setAjaxCodeid("99");
				this.setAjaxMsg(this.getText("post.upfile.upok1"));
			}

			// this.setAjaxCodeid("0");
			// this.setAjaxMsg(this.getText("post.upfile.upok"));
			return "forumUpComponent";
		} catch (BbscsException ex3) {
			this.setAjaxCodeid("3");
			this.setAjaxMsg(this.getText("error.post.upfile.error"));
			return "forumUpComponent";
		}
	}

	public String showdelattachpage() {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.addActionError(this.getText("error.post.getpost"));
			return RESULT_HTMLERROR;
		}
		if (f.getAttachFileName() == null || f.getAttachFileName().isEmpty()) {
			this.addActionError(this.getText("error.post.upfile.notexist", new String[] { this.getId() }));
			return RESULT_HTMLERROR;
		}
		if (!(this.getUserSession().getId().equals(f.getUserID()) || this.getUserSession()
				.isHaveBoardSpecialPermission(Constant.SPERMISSION_CAN_DELATTACH))) {
			this.addActionError(this.getText("error.post.upfile.user2", new String[] { this.getId() }));
			return RESULT_HTMLERROR;
		}
		this.setAttachFiles(f.getAttachFileName());
		return "attachFiles";
	}

	public String edit() {
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

		if (this.getSysConfig().getEditPostLimit() > 0) {
			if (!(this.getUserSession().isHaveBoardSpecialPermission(Constant.SPERMISSION_CAN_EDITPOST) || (System
					.currentTimeMillis() - f.getPostTime()) < this.getSysConfig().getEditPostLimit() * 60000)) {
				this.addActionError(this.getText("error.post.edit.timeout"));
				return ERROR;
			}
		}

		this.setAction("editdo");
		this.setTitle(f.getTitle());
		//this.setDetail(f.getDetail());#### change at 2007.5.1
		this.setDetail(this.getForumService().getForumDetail(f,true));

		this.setTitleColor(f.getTitleColor());
		this.setPostType(f.getPostType());
		this.setFace(f.getFace());
		this.setEditType(f.getEditType());
		this.setSign(3);
		this.setEmailInform(this.int2boolean(f.getEmailInform()));
		this.setMsgInform(this.int2boolean(f.getMsgInform()));
		setNeedsAttributeEdit(f.getIsNew());
		return INPUT;
	}

	public String editdo() {
		String title = StringUtils.trimToEmpty(this.getTitle());
		String detail = StringUtils.trimToEmpty(this.getDetail());
		if (StringUtils.isBlank(title) || BBSCSUtil.getSysCharsetStrLength(title) > 90) {
			this.addActionError(this.getText("error.post.titletoolong"));
			return ERROR;
		}
		title = this.getSysConfig().bestrowScreen(title);
		if (StringUtils.isBlank(detail)) {
			this.addActionError(this.getText("error.post.nodetail"));
			return ERROR;
		}

		if (BBSCSUtil.getSysCharsetStrLength(detail) < this.getSysConfig().getPostMinSize()
				|| BBSCSUtil.getSysCharsetStrLength(detail) > this.getSysConfig().getPostMaxSize()) {
			this.addActionError(this.getText("error.post.texttoolong", new String[] {
					String.valueOf(this.getSysConfig().getPostMinSize()),
					String.valueOf(this.getSysConfig().getPostMaxSize()) }));
			return ERROR;
		}
		detail = this.getSysConfig().bestrowScreen(detail);

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

		String IP = this.getUserRemoteAddr().substring(0, this.getUserRemoteAddr().lastIndexOf(".")) + ".*";
		f.setArtSize(BBSCSUtil.getSysCharsetStrLength(this.getDetail()));

		String amend = this.getText("change.text", new String[] { this.getUserSession().getUserName(),
				Util.formatDateTime(new Date()), IP });
		f.setAmend(amend);

		f.setEditType(this.getEditType());
		f.setFace(this.getFace());
		f.setPostType(this.getPostType());
		if (this.getSign() != 3) {
			if (this.getSign() == -1) {
				f.setSign("");
			} else {
				f.setSign(this.getUserSession().getSignDetail()[this.getSign()]);
			}
		}
		f.setTitle(title);
		f.setTitleColor(this.getTitleColor());
		f.setDetail(detail);
		if (f.getIndexStatus() == Constant.INDEX_STATUS_INDEXED) {
			f.setIndexStatus(Constant.INDEX_STATUS_NEED_UPDTAE);
		}

		try {
			f = this.getForumService().saveEditForum(f);
			if (Constant.USE_URL_REWRITE) {
				this.setForwardUrl(this.getBasePath() + "read-topic-" + this.getBid() + "-" + this.getMainID() + "-"
						+ this.getTagId() + "-" + this.getFcpage() + "-index-" + this.getInpages() + ".html");
			} else {
				this.setForwardUrl(this.getBasePath()
						+ BBSCSUtil.getActionMappingURLWithoutPrefix("read?action=topic&id=" + this.getMainID()
								+ "&bid=" + this.getBid() + "&fcpage=" + this.getFcpage() + "&inpages="
								+ this.getInpages() + "&tagId=" + this.getTagId()));
			}
			return SUCCESS;
		} catch (BbscsException ex4) {
			return ERROR;
		}
	}

	public String buy() {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.addActionError(this.getText("error.post.getpost"));
			return ERROR;
		}

		UserInfo ui = this.getUserService().findUserInfoById(this.getUserSession().getId());
		if (ui == null) {
			this.addActionError(this.getText("error.post.guest"));
			return ERROR;
		}

		if (ui.getCoin() < f.getIsHiddenValue()) {
			this.addActionError(this.getText("error.post.buy.coin"));
			return ERROR;
		}
		try {
			this.getForumService().saveForumBuy(this.getBid(), this.getId(), f, ui);
			if (Constant.USE_URL_REWRITE) {
				this.setForwardUrl(this.getBasePath() + "/read-topic-" + this.getBid() + "-" + this.getId() + "-"
						+ this.getTagId() + "-1-index-1.html");
			} else {
				this.setForwardUrl(this.getBasePath()
						+ BBSCSUtil.getActionMappingURLWithoutPrefix("read?action=topic&id=" + this.getId() + "&bid="
								+ this.getBid() + "&fcpage=" + 1 + "&fcaction=index" + "&inpages=" + 1 + "&tagId="
								+ this.getTagId()));
			}
			return SUCCESS;
		} catch (BbscsException ex5) {
			this.addActionError(this.getText("error.post.buy.error"));
			return ERROR;
		}
	}

	private String checkBeforePost() {
		if (this.getSysConfig().getPostCheckTime() > 0
				&& !this.getUserSession().isHaveBoardSpecialPermission(Constant.SPERMISSION_NOT_POSTCHECKTIME)) {
			if ((System.currentTimeMillis() - this.getUserCookie().getLastPostTime()) <= (this.getSysConfig()
					.getPostCheckTime() * 1000)) {
				this.addActionError(this.getText("error.post.checktime", new String[] { String.valueOf(this
						.getSysConfig().getPostCheckTime()) }));
				return ERROR;
			}
		}
		if (this.getSysConfig().getUseForbid() == 1) {
			if (this.getSysConfig().isForbidIP(this.getUserRemoteAddr())) {
				this.addActionError(this.getText("error.reg.ipforbid", new String[] { this.getUserRemoteAddr() }));
				return ERROR;
			}
		}
		if (this.getSysConfig().isUsePostPeriodOfTime()) {
			long onwtime = System.currentTimeMillis();
			if (!this.getSysConfig().isInPostPeriodOfTime(onwtime)) {
				this.addActionError(this.getText("error.post.isnotinperiodoftime", new String[] {
						String.valueOf(this.getSysConfig().getPostPeriodOfTimeStart()),
						String.valueOf(this.getSysConfig().getPostPeriodOfTimeEnd()) }));
				return ERROR;
			}
		}
		return SUCCESS;
	}

	private void setNeedsAttribute(boolean isRe) {
		this.setPostHiddenTypeRe(this.getSysConfig().getPostHiddenTypeRe());
		this.setPostHiddenTypeMoney(this.getSysConfig().getPostHiddenTypeMoney());
		this.setPostHiddenTypeArtNum(this.getSysConfig().getPostHiddenTypeArtNum());

		int canUseTitleColor = 0;
		if (!isRe) {
			if (this.getUserSession().isHaveBoardSpecialPermission(Constant.SPERMISSION_CAN_USE_TITLECOLOR)) {
				canUseTitleColor = 1;
			}
		}

		this.setTitleColorOptions(this.getSysOptionsValues().getTitleColorValues(canUseTitleColor, this.getLocale()));
		this.setPostPriceValues(this.getSysOptionsValues().getPostPriceValues(this.getSysConfig().getPostPriceLists()));
		this.tagValues.add(new OptionsString("0", this.getBoard().getBoardName()));
		Iterator it = this.getBoard().getBoardTag().iterator();
		BoardTag bt = null;
		while (it.hasNext()) {
			bt = (BoardTag) it.next();
			this.tagValues.add(new OptionsString(bt.getId(), bt.getTagName()));
		}
	}

	private void setNeedsAttributeEdit(int isNew) {
		this.setPostHiddenTypeRe(this.getSysConfig().getPostHiddenTypeRe());
		this.setPostHiddenTypeMoney(this.getSysConfig().getPostHiddenTypeMoney());
		this.setPostHiddenTypeArtNum(this.getSysConfig().getPostHiddenTypeArtNum());

		int canUseTitleColor = 0;
		if (isNew == 1) {
			if (this.getUserSession().isHaveBoardSpecialPermission(Constant.SPERMISSION_CAN_USE_TITLECOLOR)) {
				canUseTitleColor = 1;
			}
		}

		this.setTitleColorOptions(this.getSysOptionsValues().getTitleColorValues(canUseTitleColor, this.getLocale()));
		this.setPostPriceValues(this.getSysOptionsValues().getPostPriceValues(this.getSysConfig().getPostPriceLists()));
		this.tagValues.add(new OptionsString("0", this.getBoard().getBoardName()));
		Iterator it = this.getBoard().getBoardTag().iterator();
		BoardTag bt = null;
		while (it.hasNext()) {
			bt = (BoardTag) it.next();
			this.tagValues.add(new OptionsString(bt.getId(), bt.getTagName()));
		}
	}

	private int getUserPostPerNum(int userNum, int sysNum) {
		if (userNum == 0) {
			return sysNum;
		} else {
			return userNum;
		}
	}

	public String getUpfileIframeUrl() {
		return upfileIframeUrl;
	}

	public void setUpfileIframeUrl(String upfileIframeUrl) {
		this.upfileIframeUrl = upfileIframeUrl;
	}

	public String getAjaxCodeid() {
		return ajaxCodeid;
	}

	public void setAjaxCodeid(String ajaxCodeid) {
		this.ajaxCodeid = ajaxCodeid;
	}

	public String getAjaxMsg() {
		return ajaxMsg;
	}

	public void setAjaxMsg(String ajaxMsg) {
		this.ajaxMsg = ajaxMsg;
	}

	public List getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(List attachFiles) {
		this.attachFiles = attachFiles;
	}

}
