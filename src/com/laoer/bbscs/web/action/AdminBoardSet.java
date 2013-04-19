package com.laoer.bbscs.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.BoardService;
import com.laoer.bbscs.service.ForumService;
import com.laoer.bbscs.web.ui.OptionsInt;
import com.laoer.bbscs.web.ui.OptionsLong;
import com.laoer.bbscs.web.ui.RadioInt;

import java.util.*;
import org.apache.commons.lang.*;

public class AdminBoardSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminBoardSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -1182525740882506333L;

	public AdminBoardSet() {

		this.setRadioYesNoListValues();
		this.setUseStats();
		this.setBoardTypes();
	}

	private BoardService boardService;

	private ForumService forumService;

	private int addUserPostNum;

	private int allowHTML;

	private int allowUBB;

	private int auditAttach;

	private int auditPost;

	private String boardName;

	private int boardType;

	private String bulletin;

	private String explains;

	private long id;

	private int isAuth;

	private int isHidden;

	private int needPasswd;

	private int orders;

	private long parentID = 0;

	private String passwd;

	private int useStat;

	public int getAddUserPostNum() {
		return addUserPostNum;
	}

	public void setAddUserPostNum(int addUserPostNum) {
		this.addUserPostNum = addUserPostNum;
	}

	public int getAllowHTML() {
		return allowHTML;
	}

	public void setAllowHTML(int allowHTML) {
		this.allowHTML = allowHTML;
	}

	public int getAllowUBB() {
		return allowUBB;
	}

	public void setAllowUBB(int allowUBB) {
		this.allowUBB = allowUBB;
	}

	public int getAuditAttach() {
		return auditAttach;
	}

	public void setAuditAttach(int auditAttach) {
		this.auditAttach = auditAttach;
	}

	public int getAuditPost() {
		return auditPost;
	}

	public void setAuditPost(int auditPost) {
		this.auditPost = auditPost;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public int getBoardType() {
		return boardType;
	}

	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}

	public String getBulletin() {
		return bulletin;
	}

	public void setBulletin(String bulletin) {
		this.bulletin = bulletin;
	}

	public String getExplains() {
		return explains;
	}

	public void setExplains(String explains) {
		this.explains = explains;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(int isAuth) {
		this.isAuth = isAuth;
	}

	public int getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(int isHidden) {
		this.isHidden = isHidden;
	}

	public int getNeedPasswd() {
		return needPasswd;
	}

	public void setNeedPasswd(int needPasswd) {
		this.needPasswd = needPasswd;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public long getParentID() {
		return parentID;
	}

	public void setParentID(long parentID) {
		this.parentID = parentID;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getUseStat() {
		return useStat;
	}

	public void setUseStat(int useStat) {
		this.useStat = useStat;
	}

	public BoardService getBoardService() {
		return boardService;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	private List<OptionsLong> parentValues = new ArrayList<OptionsLong>();

	private void setParentValues() {
		parentValues.add(new OptionsLong(0, this.getText("bbscs.none")));
		List bl = this.getBoardService().findBoardsAllTree(0, new ArrayList(), -1, -1, Constant.FIND_BOARDS_BY_ORDER);
		for (int i = 0; i < bl.size(); i++) {
			Board b = (Board) bl.get(i);
			parentValues.add(new OptionsLong(b.getId(), BBSCSUtil.getBoardPrefixLine(b.getLevel(), "-")
					+ b.getBoardName()));
		}
	}

	public List<OptionsLong> getParentValues() {
		return parentValues;
	}

	public void setParentValues(List<OptionsLong> parentValues) {
		this.parentValues = parentValues;
	}

	List<RadioInt> radioYesNoList = new ArrayList<RadioInt>();

	private void setRadioYesNoListValues() {
		radioYesNoList.add(new RadioInt(0, this.getText("bbscs.no")));
		radioYesNoList.add(new RadioInt(1, this.getText("bbscs.yes")));
	}

	public List<RadioInt> getRadioYesNoList() {
		return radioYesNoList;
	}

	public void setRadioYesNoList(List<RadioInt> radioYesNoList) {
		this.radioYesNoList = radioYesNoList;
	}

	private List<OptionsInt> boardTypes = new ArrayList<OptionsInt>();

	private void setBoardTypes() {
		boardTypes.add(new OptionsInt(1, this.getText("admin.boardType1")));
		boardTypes.add(new OptionsInt(3, this.getText("admin.boardType3")));
	}

	public List<OptionsInt> getBoardTypes() {
		return boardTypes;
	}

	public void setBoardTypes(List<OptionsInt> boardTypes) {
		this.boardTypes = boardTypes;
	}

	private List<OptionsInt> useStats = new ArrayList<OptionsInt>();

	private void setUseStats() {
		useStats.add(new OptionsInt(0, this.getText("admin.board.useStat0")));
		useStats.add(new OptionsInt(1, this.getText("admin.board.useStat1")));
	}

	public List<OptionsInt> getUseStats() {
		return useStats;
	}

	public void setUseStats(List<OptionsInt> useStats) {
		this.useStats = useStats;
	}

	public ForumService getForumService() {
		return forumService;
	}

	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			logger.error(e);
			return INPUT;
		}
	}

	public String add() {
		this.setParentValues();
		this.setAction("addnew");
		this.setUseStat(1);
		this.setAddUserPostNum(1);

		int nextOrders = this.getBoardService().getNextOrder(this.getParentID());
		this.setOrders(nextOrders);
		return INPUT;
	}

	public String addnew() {

		if (StringUtils.isBlank(this.getBoardName())) {
			this.setParentValues();
			this.addActionError(this.getText("error.admin.board.boardNameNull"));
			return INPUT;
		}
		if (this.getBoardType() == 0 && this.getParentID() != 0) {
			this.setParentValues();
			this.addActionError(this.getText("error.admin.board.type"));
			return INPUT;
		}
		if (this.getBoardType() == 1 && this.getParentID() != 0) { // 分区只能做为一级版区使用
			this.setParentValues();
			this.addActionError(this.getText("error.admin.board.type1"));
			return INPUT;
		}
		if (this.getParentID() != 0) { // 父级版区已存在
			Board pb = this.getBoardService().getBoardByID(this.getParentID());
			if (pb.getBoardType() == 1) { // 父级版区类型为作为分区使用
				if (this.getBoardType() == 1) { // 如果要添加的版区也为分区，报错
					this.setParentValues();
					this.addActionError(this.getText("error.admin.board.type2"));
					return INPUT;
				}
			} else if (pb.getBoardType() == 3) { // 父级版区已经是作为论坛使用的版区，则不能再添加子版，报错
				if (pb.getParentID() != 0) { // 父版区不是一级版区
					this.setParentValues();
					this.addActionError(this.getText("error.admin.board.type3"));
					return INPUT;
				}
			}
		}

		Board b = new Board();
		b.setAddUserPostNum(this.getAddUserPostNum());
		b.setAllowHTML(this.getAllowHTML());
		b.setAllowUBB(this.getAllowUBB());
		b.setAuditAttach(this.getAuditAttach());
		b.setAuditPost(this.getAuditPost());
		b.setBoardName(this.getBoardName());
		b.setBoardType(this.getBoardType());
		b.setBulletin(this.getBulletin());
		b.setExplains(this.getExplains());
		b.setIsAuth(this.getIsAuth());
		b.setIsHidden(this.getIsHidden());
		b.setNeedPasswd(this.getNeedPasswd());
		b.setOrders(this.getOrders());
		b.setParentID(this.getParentID());
		b.setPasswd(this.getPasswd());
		b.setUseStat(this.getUseStat());

		try {
			b = this.getBoardService().createBoard(b);
			this.addActionMessage(this.getText("admin.board.create.ok"));
		} catch (BbscsException e) {
			logger.error(e);
			this.setParentValues();
			this.addActionError(this.getText("error.admin.board.create"));
		}
		this.setParentValues();

		return INPUT;
	}

	public String edit() {
		this.setParentValues();
		Board b = this.getBoardService().getBoardByID(this.getId());
		if (b == null) {
			this.addActionError(this.getText("error.board.notexist"));
			return INPUT;
		}
		this.setAction("editsave");
		this.setAddUserPostNum(b.getAddUserPostNum());
		this.setAllowHTML(b.getAllowHTML());
		this.setAllowUBB(b.getAllowUBB());
		this.setAuditAttach(b.getAuditAttach());
		this.setAuditPost(b.getAuditPost());
		this.setBoardName(b.getBoardName());
		this.setBoardType(b.getBoardType());
		this.setBulletin(b.getBulletin());
		this.setExplains(b.getExplains());
		this.setId(b.getId().longValue());
		this.setIsAuth(b.getIsAuth());
		this.setIsHidden(b.getIsHidden());
		this.setNeedPasswd(b.getNeedPasswd());
		this.setOrders(b.getOrders());
		this.setParentID(b.getParentID());
		this.setPasswd(b.getPasswd());
		this.setUseStat(b.getUseStat());
		return INPUT;
	}

	public String editsave() {

		Board b = this.getBoardService().getBoardByID(this.getId());
		if (b == null) {
			this.setParentValues();
			this.addActionError(this.getText("error.board.notexist"));
			return INPUT;
		}

		if (b.getBoardType() != this.getBoardType()) { // 版区类型改变
			if (this.getBoardType() == 1) { // 修改为分区
				if (b.getBoardType() == 3) { // 如果原版区为论坛使用,则不能修改,提示错误
					this.setParentValues();
					this.addActionError(this.getText("error.admin.board.type4"));
					return INPUT;
				}
			}
		}

		long oldParendID = -1;
		if (b.getParentID() != this.getParentID()) { // 父级版区变化
			oldParendID = b.getParentID();

			Board newpb = this.getBoardService().getBoardByID(this.getParentID()); // 取得新父级版区
			if (newpb != null) { // 新父级版区存在
				if (newpb.getBoardType() == 3) { // 如果新的父级版区已经作为论坛使用，报错
					this.setParentValues();
					this.addActionError(this.getText("error.admin.board.type3"));
					return INPUT;
				} else if (newpb.getBoardType() == 1) { // 如果新的父级版区为分区
					if (b.getBoardType() == 1) { // 自身版区也为分区，报错
						this.setParentValues();
						this.addActionError(this.getText("error.admin.board.type2"));
						return INPUT;
					}
				}
			}
		}

		b.setAddUserPostNum(this.getAddUserPostNum());
		b.setAllowHTML(this.getAllowHTML());
		b.setAllowUBB(this.getAllowUBB());
		b.setAuditAttach(this.getAuditAttach());
		b.setAuditPost(this.getAuditPost());
		b.setBoardName(this.getBoardName());
		b.setBoardType(this.getBoardType());
		b.setBulletin(this.getBulletin());
		b.setExplains(this.getExplains());
		b.setIsAuth(this.getIsAuth());
		b.setIsHidden(this.getIsHidden());
		b.setNeedPasswd(this.getNeedPasswd());
		b.setOrders(this.getOrders());
		b.setParentID(this.getParentID());
		b.setPasswd(this.getPasswd());
		b.setUseStat(this.getUseStat());

		try {
			b = this.getBoardService().updateBoard(b, oldParendID);
			this.addActionMessage(this.getText("bbscs.dataupdate.succeed"));
		} catch (BbscsException ex) {
			this.addActionError(this.getText("error.dataupdate.failed"));
		}
		this.setParentValues();
		return INPUT;
	}

	public String del() {
		Board b = this.getBoardService().getBoardByID(this.getId());
		if (b == null) {
			this.addActionError(this.getText("error.board.notexist"));
			return ERROR;
		}
		List cboards = this.getBoardService().findBoardsByParentID(this.getId(), -1, -1, Constant.FIND_BOARDS_BY_ORDER);
		if (cboards.size() > 0) {
			this.addActionError(this.getText("error.del.board.havechild"));
			return ERROR;
		}

		long postNum = this.getForumService().getForumNum(this.getId(), -1, -1, -1, -1);
		if (postNum > 0) {
			this.addActionError(this.getText("error.del.board.havepost"));
			return ERROR;
		}

		try {
			this.getBoardService().removeBoard(b);
			return SUCCESS;
		} catch (BbscsException ex2) {
			this.addActionError(this.getText("error.del.board"));
			return ERROR;
		}

	}

}
