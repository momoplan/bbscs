package com.laoer.bbscs.web.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.bean.Friend;
import com.laoer.bbscs.bean.Note;
import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.FriendService;
import com.laoer.bbscs.service.NoteFactory;
import com.laoer.bbscs.service.NoteService;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.service.web.PageList;
import com.laoer.bbscs.service.web.Pages;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;
import com.laoer.bbscs.web.interceptor.RequestBasePathAware;

public class NoteAction extends BaseMainAction implements RequestBasePathAware {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(NoteAction.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 5602879264825331112L;

	private NoteService noteService;

	private AjaxMessagesJson ajaxMessagesJson;

	private NoteFactory noteFactory;

	private UserService userService;

	private SysConfig sysConfig;

	private FriendService friendService;

	private String fromID;

	private String id;

	private List ids;

	private String noteContext;

	private String noteTitle;

	private String toID;

	private String toUserName;

	private int needRe;

	private PageList pageList;

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public FriendService getFriendService() {
		return friendService;
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	public NoteFactory getNoteFactory() {
		return noteFactory;
	}

	public void setNoteFactory(NoteFactory noteFactory) {
		this.noteFactory = noteFactory;
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getFromID() {
		return fromID;
	}

	public void setFromID(String fromID) {
		this.fromID = fromID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List getIds() {
		return ids;
	}

	public void setIds(List ids) {
		this.ids = ids;
	}

	public int getNeedRe() {
		return needRe;
	}

	public void setNeedRe(int needRe) {
		this.needRe = needRe;
	}

	public String getNoteContext() {
		return noteContext;
	}

	public void setNoteContext(String noteContext) {
		this.noteContext = noteContext;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getToID() {
		return toID;
	}

	public void setToID(String toID) {
		this.toID = toID;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	private String basePath;

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBasePath() {
		return basePath;
	}

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	private Note note;

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public String index() {
		return SUCCESS;
	}

	public String innum() {
		long inBoxNum = this.getNoteService().getNoteAllNumInBox(this.getUserSession().getId());
		this.getAjaxMessagesJson().setMessage("0", "", String.valueOf(inBoxNum));
		return RESULT_AJAXJSON;
	}

	public String outnum() {
		long outBoxNum = this.getNoteService().getNoteAllNumOutBox(this.getUserSession().getId());
		this.getAjaxMessagesJson().setMessage("0", "", String.valueOf(outBoxNum));
		return RESULT_AJAXJSON;
	}

	public String add() {
		this.setAction("addsave");
		return INPUT;
	}

	public String addsave() {
		if (this.getSysConfig().isInPmFloodTime(this.getUserCookie().getLastSendNoteTime())) { // 发送间隔时间
			this.getAjaxMessagesJson().setMessage(
					"E_NOTE_INFLOODTIME",
					this.getText("error.note.isinfloodtime", new String[] { String.valueOf(this.getSysConfig()
							.getPmFloodTime()) }));
			return RESULT_AJAXJSON;
		}

		if (StringUtils.isBlank(this.getToUserName()) || StringUtils.isBlank(this.getNoteTitle())
				|| StringUtils.isBlank(this.getNoteContext())) { // 各项不能为空
			this.getAjaxMessagesJson().setMessage("E_NULL", this.getText("error.nullerror"));
			return RESULT_AJAXJSON;
		}

		if (BBSCSUtil.getSysCharsetStrLength(this.getNoteContext()) > this.getSysConfig().getPmMaxLength()) { // 内容超过规定字数
			this.getAjaxMessagesJson().setMessage(
					"E_NOTE_TOOLONG",
					this.getText("error.note.context.toolong", new String[] { String.valueOf(this.getSysConfig()
							.getPmMaxLength()) }));
			return RESULT_AJAXJSON;
		}

		if (this.getToUserName().equalsIgnoreCase(this.getUserSession().getUserName())) { // 不能给自己发送
			this.getAjaxMessagesJson().setMessage("E_NOTE_USERSAME", this.getText("error.note.usersame"));
			return RESULT_AJAXJSON;
		}

		UserInfo ui = this.getUserService().findUserInfoByUserName(this.getToUserName());
		if (ui == null) { // 对方用户不存在
			this.getAjaxMessagesJson().setMessage("E_USER_NOEXIST", this.getText("error.note.touser.noexist"));
			return RESULT_AJAXJSON;
		}

		// todo 不接收悄悄话
		if (ui.getReceiveNote() != 1) {
			this.getAjaxMessagesJson().setMessage("E_NOTE_USER_NOT_RECEIVE", this.getText("error.note.usernotreceive"));
			return RESULT_AJAXJSON;

		}

		// todo 黑名单
		Friend f = this.getFriendService().findFriendByName(this.getUserSession().getUserName(), ui.getId());
		if (f != null && f.getIsBlack() == 1) {
			this.getAjaxMessagesJson().setMessage("E_NOTE_USER_IS_BLACK", this.getText("error.note.userisinblack"));
			return RESULT_AJAXJSON;
		}

		Note inboxNote = this.getNoteFactory().getInstance(ui.getId()); // 对方收件箱对象
		Note outboxNote = this.getNoteFactory().getInstance(this.getUserSession().getId()); // 自己发件箱对象

		Date sdate = new Date();
		inboxNote.setCreateTime(sdate);
		inboxNote.setFromID(this.getUserSession().getId());
		inboxNote.setFromNickName(this.getUserSession().getNickName());
		inboxNote.setFromUserName(this.getUserSession().getUserName());
		inboxNote.setIsNew(1);
		inboxNote.setIsRe(0);
		inboxNote.setNeedRe(this.getNeedRe());
		inboxNote.setNoteContext(this.getNoteContext());
		inboxNote.setNoteTitle(this.getNoteTitle());
		inboxNote.setNoteType(1);
		inboxNote.setToID(ui.getId());
		inboxNote.setToNickName(ui.getNickName());
		inboxNote.setToUserName(ui.getUserName());
		inboxNote.setSysMsg(0);

		outboxNote.setCreateTime(sdate);
		outboxNote.setFromID(this.getUserSession().getId());
		outboxNote.setFromNickName(this.getUserSession().getNickName());
		outboxNote.setFromUserName(this.getUserSession().getUserName());
		outboxNote.setIsNew(0);
		outboxNote.setIsRe(0);
		outboxNote.setNeedRe(0);
		outboxNote.setNoteContext(this.getNoteContext());
		outboxNote.setNoteTitle(this.getNoteTitle());
		outboxNote.setNoteType(0);
		outboxNote.setToID(ui.getId());
		outboxNote.setToNickName(ui.getNickName());
		outboxNote.setToUserName(ui.getUserName());
		outboxNote.setSysMsg(0);

		try {
			this.getNoteService().createNote(inboxNote, outboxNote); // 保存悄悄话
			this.getAjaxMessagesJson().setMessage("0", this.getText("note.add.ok"));
		} catch (BbscsException ex) {
			logger.error(ex);
			this.getAjaxMessagesJson().setMessage("E_NOTE_ADDFAILED", this.getText("error.note.add.error"));
		}

		return RESULT_AJAXJSON;
	}

	public String inbox() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());
		pages.setPerPageNum(this.getSysConfig().getPmPerPage());
		// pages.setPerPageNum(2);
		pages.setFileName(this.getBasePath()
				+ BBSCSUtil.getActionMappingURLWithoutPrefix("note?action=inbox&ajax=shtml"));
		this.setPageList(this.getNoteService().findNotesInBox(this.getUserSession().getId(), pages));
		return "noteInbox";
	}

	public String outbox() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());
		pages.setPerPageNum(this.getSysConfig().getPmPerPage());
		// pages.setPerPageNum(1);
		pages.setFileName(this.getBasePath()
				+ BBSCSUtil.getActionMappingURLWithoutPrefix("note?action=outbox&ajax=shtml"));
		this.setPageList(this.getNoteService().findNotesOutBox(this.getUserSession().getId(), pages));
		return "noteOutbox";
	}

	public String readinbox() {
		note = this.getNoteService().findNoteByIDToID(this.getId(), this.getUserSession().getId());
		if (note == null) {
			this.addActionError(this.getText("error.note.notexist"));
			return RESULT_HTMLERROR;
		}
		if (note.getIsNew() == 1) {
			note.setIsNew(0);
			try {
				note = this.getNoteService().saveNote(note);
			} catch (BbscsException ex2) {
				logger.error(ex2);
				this.addActionError(this.getText("error.note.read.error"));
				return RESULT_HTMLERROR;
			}
		}
		if (note.getSysMsg() == 0) {
			note.setNoteContext(BBSCSUtil.filterText(note.getNoteContext(), this.getSysConfig().isPmAllowHTML(), this
					.getSysConfig().isPmAllowUBB(), this.getSysConfig().isPmAllowSmilies()));
		}

		return "noteReadInbox";
	}

	public String readoutbox() {
		note = this.getNoteService().findNoteByIDFromID(this.getId(), this.getUserSession().getId());
		if (note == null) {
			this.addActionError(this.getText("error.note.notexist"));
			return RESULT_HTMLERROR;
		}

		note.setNoteContext(BBSCSUtil.filterText(note.getNoteContext(), this.getSysConfig().isPmAllowHTML(), this
				.getSysConfig().isPmAllowUBB(), this.getSysConfig().isPmAllowSmilies()));
		return "noteReadInbox";
	}

	public String delinbox() {
		try {
			this.getNoteService().removeByIDToID(this.getId(), this.getUserSession().getId());
			this.getAjaxMessagesJson().setMessage("0", this.getText("note.del.ok"));
		} catch (BbscsException ex1) {
			logger.error(ex1);
			this.getAjaxMessagesJson().setMessage("E_NOTE_DEL", this.getText("error.note.del.error"));
		}
		return RESULT_AJAXJSON;
	}

	public String deloutbox() {
		try {
			this.getNoteService().removeByIDFromID(this.getId(), this.getUserSession().getId());
			this.getAjaxMessagesJson().setMessage("0", this.getText("note.del.ok"));
		} catch (BbscsException ex1) {
			logger.error(ex1);
			this.getAjaxMessagesJson().setMessage("E_NOTE_DEL", this.getText("error.note.del.error"));
		}
		return RESULT_AJAXJSON;
	}

	public String autore() {
		Note note = this.getNoteService().findNoteByIDToID(this.getId(), this.getUserSession().getId());
		if (note == null) {
			this.getAjaxMessagesJson().setMessage("E_NOTE_NOTEXIST", this.getText("error.note.notexist"));
			return RESULT_AJAXJSON;
		}
		note.setNeedRe(0);
		note.setIsRe(1);
		try {
			note = this.getNoteService().saveNote(note);
		} catch (BbscsException ex3) {
			logger.error(ex3);
		}

		Note inboxNote = this.getNoteFactory().getInstance(note.getFromID()); // 对方收件箱对象
		Note outboxNote = this.getNoteFactory().getInstance(this.getUserSession().getId()); // 自己发件箱对象

		Date sdate = new Date();
		inboxNote.setCreateTime(sdate);
		inboxNote.setFromID(this.getUserSession().getId());
		inboxNote.setFromNickName(this.getUserSession().getNickName());
		inboxNote.setFromUserName(this.getUserSession().getUserName());
		inboxNote.setIsNew(1);
		inboxNote.setIsRe(0);
		inboxNote.setNeedRe(0);
		inboxNote.setNoteContext(this.getText("note.autore.context", new String[] { note.getNoteTitle() }));
		inboxNote.setNoteTitle(this.getText("note.autore.perfix") + note.getNoteTitle());
		inboxNote.setNoteType(1);
		inboxNote.setToID(note.getFromID());
		inboxNote.setToNickName(note.getFromNickName());
		inboxNote.setToUserName(note.getFromUserName());
		inboxNote.setSysMsg(0);

		outboxNote.setCreateTime(sdate);
		outboxNote.setFromID(this.getUserSession().getId());
		outboxNote.setFromNickName(this.getUserSession().getNickName());
		outboxNote.setFromUserName(this.getUserSession().getUserName());
		outboxNote.setIsNew(0);
		outboxNote.setIsRe(0);
		outboxNote.setNeedRe(0);
		outboxNote.setNoteContext(this.getText("note.autore.context", new String[] { note.getNoteTitle() }));
		outboxNote.setNoteTitle(this.getText("note.autore.perfix") + note.getNoteTitle());
		outboxNote.setNoteType(0);
		outboxNote.setToID(note.getFromID());
		outboxNote.setToNickName(note.getFromNickName());
		outboxNote.setToUserName(note.getFromUserName());
		outboxNote.setSysMsg(0);

		try {
			this.getNoteService().createNote(inboxNote, outboxNote);
			this.getAjaxMessagesJson().setMessage("0", this.getText("note.re.ok"));
		} catch (BbscsException ex) {
			logger.error(ex);
			this.getAjaxMessagesJson().setMessage("E_NOTE_ADDFAILED", this.getText("error.note.add.error"));
		}

		return RESULT_AJAXJSON;
	}

	public String re() {
		if (this.getSysConfig().isInPmFloodTime(this.getUserCookie().getLastSendNoteTime())) {
			this.getAjaxMessagesJson().setMessage(
					"E_NOTE_INFLOODTIME",
					this.getText("error.note.isinfloodtime", new String[] { String.valueOf(this.getSysConfig()
							.getPmFloodTime()) }));
			return RESULT_AJAXJSON;
		}

		Note note = this.getNoteService().findNoteByIDToID(this.getId(), this.getUserSession().getId());
		if (note == null) {
			this.getAjaxMessagesJson().setMessage("E_NOTE_NOTEXIST", this.getText("error.note.notexist"));
			return RESULT_AJAXJSON;
		}
		note.setNeedRe(0);
		note.setIsRe(1);
		try {
			note = this.getNoteService().saveNote(note);
		} catch (BbscsException ex3) {
			logger.error(ex3);
		}

		if (StringUtils.isBlank(this.getToUserName()) || StringUtils.isBlank(this.getNoteTitle())
				|| StringUtils.isBlank(this.getNoteContext())) {
			this.getAjaxMessagesJson().setMessage("E_NULL", this.getText("error.nullerror"));
			return RESULT_AJAXJSON;
		}

		if (BBSCSUtil.getSysCharsetStrLength(this.getNoteContext()) > this.getSysConfig().getPmMaxLength()) {
			this.getAjaxMessagesJson().setMessage(
					"E_NOTE_TOOLONG",
					this.getText("error.note.context.toolong", new String[] { String.valueOf(this.getSysConfig()
							.getPmMaxLength()) }));
			return RESULT_AJAXJSON;
		}

		if (this.getToUserName().equalsIgnoreCase(this.getUserSession().getUserName())) {
			this.getAjaxMessagesJson().setMessage("E_NOTE_USERSAME", this.getText("error.note.usersame"));
			return RESULT_AJAXJSON;
		}

		UserInfo ui = this.getUserService().findUserInfoByUserName(this.getToUserName());
		if (ui == null) {
			this.getAjaxMessagesJson().setMessage("E_USER_NOEXIST", this.getText("error.note.touser.noexist"));
			return RESULT_AJAXJSON;
		}

		// todo 不接收悄悄话
		if (ui.getReceiveNote() != 1) {
			this.getAjaxMessagesJson().setMessage("E_NOTE_USER_NOT_RECEIVE", this.getText("error.note.usernotreceive"));
			return RESULT_AJAXJSON;
		}

		// todo 黑名单
		Friend f = this.getFriendService().findFriendByName(this.getUserSession().getUserName(), ui.getId());
		if (f != null && f.getIsBlack() == 1) {
			this.getAjaxMessagesJson().setMessage("E_NOTE_USER_IS_BLACK", this.getText("error.note.userisinblack"));
			return RESULT_AJAXJSON;
		}

		Note inboxNote = this.getNoteFactory().getInstance(note.getFromID()); // 对方收件箱对象
		Note outboxNote = this.getNoteFactory().getInstance(this.getUserSession().getId()); // 自己发件箱对象

		Date sdate = new Date();

		inboxNote.setCreateTime(sdate);
		inboxNote.setFromID(this.getUserSession().getId());
		inboxNote.setFromNickName(this.getUserSession().getNickName());
		inboxNote.setFromUserName(this.getUserSession().getUserName());
		inboxNote.setIsNew(1);
		inboxNote.setIsRe(0);
		inboxNote.setNeedRe(this.getNeedRe());
		inboxNote.setNoteContext(this.getNoteContext());
		inboxNote.setNoteTitle(this.getNoteTitle());
		inboxNote.setNoteType(1);
		inboxNote.setToID(ui.getId());
		inboxNote.setToNickName(ui.getNickName());
		inboxNote.setToUserName(ui.getUserName());
		inboxNote.setSysMsg(0);

		outboxNote.setCreateTime(sdate);
		outboxNote.setFromID(this.getUserSession().getId());
		outboxNote.setFromNickName(this.getUserSession().getNickName());
		outboxNote.setFromUserName(this.getUserSession().getUserName());
		outboxNote.setIsNew(0);
		outboxNote.setIsRe(0);
		outboxNote.setNeedRe(0);
		outboxNote.setNoteContext(this.getNoteContext());
		outboxNote.setNoteTitle(this.getNoteTitle());
		outboxNote.setNoteType(0);
		outboxNote.setToID(ui.getId());
		outboxNote.setToNickName(ui.getNickName());
		outboxNote.setToUserName(ui.getUserName());
		outboxNote.setSysMsg(0);

		try {
			this.getNoteService().createNote(inboxNote, outboxNote);
			this.getUserCookie().addLastNoteSendTime();
			this.getAjaxMessagesJson().setMessage("0", this.getText("note.re.ok"));
		} catch (BbscsException ex) {
			logger.error(ex);
			this.getAjaxMessagesJson().setMessage("E_NOTE_ADDFAILED", this.getText("error.note.add.error"));
		}
		return RESULT_AJAXJSON;
	}

	public String delallinbox() {
		try {
			this.getNoteService().removeAllInBox(this.getUserSession().getId());
			this.getAjaxMessagesJson().setMessage("0", this.getText("note.del.ok"));
		} catch (BbscsException ex4) {
			logger.error(ex4);
			this.getAjaxMessagesJson().setMessage("E_NOTE_DEL", this.getText("error.note.del.error"));
		}
		return RESULT_AJAXJSON;
	}

	public String delalloutbox() {
		try {
			this.getNoteService().removeAllOutBox(this.getUserSession().getId());
			this.getAjaxMessagesJson().setMessage("0", this.getText("note.del.ok"));
		} catch (BbscsException ex4) {
			logger.error(ex4);
			this.getAjaxMessagesJson().setMessage("E_NOTE_DEL", this.getText("error.note.del.error"));
		}
		return RESULT_AJAXJSON;
	}

	public String delidsinbox() {
		if (this.getIds() == null || this.getIds().isEmpty()) {
			this.getAjaxMessagesJson().setMessage("E_NOTE_IDSISNULL", this.getText("error.note.ids.null"));
			return RESULT_AJAXJSON;
		}
		try {
			this.getNoteService().removeInIDsToID(this.getIds(), this.getUserSession().getId());
			this.getAjaxMessagesJson().setMessage("0", this.getText("note.del.ok"));
		} catch (BbscsException ex5) {
			logger.error(ex5);
			this.getAjaxMessagesJson().setMessage("E_NOTE_DEL", this.getText("error.note.del.error"));
		}
		return RESULT_AJAXJSON;
	}

	public String delidsoutbox() {
		if (this.getIds() == null || this.getIds().isEmpty()) {
			this.getAjaxMessagesJson().setMessage("E_NOTE_IDSISNULL", this.getText("error.note.ids.null"));
			return RESULT_AJAXJSON;
		}
		try {
			this.getNoteService().removeInIDsFromID(this.getIds(), this.getUserSession().getId());
			this.getAjaxMessagesJson().setMessage("0", this.getText("note.del.ok"));
		} catch (BbscsException ex5) {
			logger.error(ex5);
			this.getAjaxMessagesJson().setMessage("E_NOTE_DEL", this.getText("error.note.del.error"));
		}
		return RESULT_AJAXJSON;
	}

}
