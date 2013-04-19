package com.laoer.bbscs.web.action;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.bean.BoardTag;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.BoardService;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;

public class AdminBoardTag extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminBoardTag.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 4367751307274059105L;

	private BoardService boardService;

	private AjaxMessagesJson ajaxMessagesJson;

	private long bid;

	private String tagName;

	private String id;

	private int orders;

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public BoardService getBoardService() {
		return boardService;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	private Set tags;

	public Set getTags() {
		return tags;
	}

	public void setTags(Set tags) {
		this.tags = tags;
	}

	private Board board;

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			logger.error(e);
			return ERROR;
		}
	}

	public String list() {
		Board b = this.getBoardService().getBoardByID(this.getBid());
		this.setTags(b.getBoardTag());
		return "list";
	}

	public String add() {
		this.setAction("addsave");
		Board b = this.getBoardService().getBoardByID(this.getBid());
		this.setBoard(b);
		return INPUT;
	}

	@SuppressWarnings("unchecked")
	public String addsave() {
		if (StringUtils.isBlank(this.getTagName())) {
			this.getAjaxMessagesJson().setMessage("E_BTAG_NAMENULL", this.getText("error.admin.btag.namenull"));
			return RESULT_AJAXJSON;
		}
		Board b = this.getBoardService().getBoardByID(bid);

		BoardTag boardTag = new BoardTag();
		boardTag.setBoard(b);
		boardTag.setBoardID(this.getBid());
		boardTag.setOrders(this.getOrders());
		boardTag.setTagName(this.getTagName());

		b.getBoardTag().add(boardTag);

		try {
			this.getBoardService().saveBoard(b);
			this.getAjaxMessagesJson().setMessage("0", this.getText("admin.btag.add.ok"));
		} catch (BbscsException ex) {
			logger.error(ex);
			this.getAjaxMessagesJson().setMessage("E_BTAG_ADD", this.getText("error.admin.btag.adderror"));
		}
		return RESULT_AJAXJSON;
	}

	public String edit() {
		Board b = this.getBoardService().getBoardByID(bid);
		Iterator it = b.getBoardTag().iterator();
		BoardTag bt = null;
		while (it.hasNext()) {
			bt = (BoardTag) it.next();
			if (bt.getId().equals(id)) {
				break;
			}
		}
		this.setId(bt.getId());
		this.setAction("editsave");
		this.setOrders(bt.getOrders());
		this.setTagName(bt.getTagName());
		this.setBoard(b);
		return INPUT;
	}

	public String editsave() {
		Board b = this.getBoardService().getBoardByID(this.getBid());
		BoardTag bt = null;
		Iterator it = b.getBoardTag().iterator();
		while (it.hasNext()) {
			bt = (BoardTag) it.next();
			if (bt.getId().equals(id)) {
				break;
			}
		}
		if (bt != null) {

			if (StringUtils.isBlank(this.getTagName())) {
				this.getAjaxMessagesJson().setMessage("E_BTAG_NAMENULL", this.getText("error.admin.btag.namenull"));
				return RESULT_AJAXJSON;
			}

			bt.setTagName(this.getTagName());
			bt.setOrders(this.getOrders());
		}
		try {
			this.getBoardService().saveBoard(b);
			this.getAjaxMessagesJson().setMessage("0", this.getText("admin.btag.edit.ok"));
		} catch (BbscsException ex1) {
			this.getAjaxMessagesJson().setMessage("E_BTAG_EDIT", this.getText("error.admin.btag.editerror"));
		}
		return RESULT_AJAXJSON;
	}

	public String del() {
		Board b = this.getBoardService().getBoardByID(this.getBid());
		try {
			this.getBoardService().removeBoardTag(b, id);
			this.getAjaxMessagesJson().setMessage("0", this.getText("admin.btag.del.ok"));
		} catch (BbscsException ex1) {
			this.getAjaxMessagesJson().setMessage("E_BTAG_DEL", this.getText("error.admin.btag.delerror"));
		}
		return RESULT_AJAXJSON;
	}

}
