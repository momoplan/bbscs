package com.laoer.bbscs.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.laoer.bbscs.bean.Elite;
import com.laoer.bbscs.bean.Forum;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.EliteService;
import com.laoer.bbscs.service.ForumService;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;
import net.sf.json.*;

public class Refine extends BaseBoardAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(Refine.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 1313875823091297652L;

	private long pid;

	private String eliteName;

	private List<String> postIds;

	private long eliteDir;

	private long eliteId;

	private int orders;

	private String jsonToString;

	private EliteService eliteService;

	private ForumService forumService;

	private AjaxMessagesJson ajaxMessagesJson;

	public long getEliteDir() {
		return eliteDir;
	}

	public void setEliteDir(long eliteDir) {
		this.eliteDir = eliteDir;
	}

	public long getEliteId() {
		return eliteId;
	}

	public void setEliteId(long eliteId) {
		this.eliteId = eliteId;
	}

	public String getEliteName() {
		return eliteName;
	}

	public void setEliteName(String eliteName) {
		this.eliteName = eliteName;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public List<String> getPostIds() {
		return postIds;
	}

	public void setPostIds(List<String> postIds) {
		this.postIds = postIds;
	}

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public EliteService getEliteService() {
		return eliteService;
	}

	public void setEliteService(EliteService eliteService) {
		this.eliteService = eliteService;
	}

	public ForumService getForumService() {
		return forumService;
	}

	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}

	private List<Elite> eliteDirs = new ArrayList<Elite>();

	public List<Elite> getEliteDirs() {
		return eliteDirs;
	}

	public void setEliteDirs(List<Elite> eliteDirs) {
		this.eliteDirs = eliteDirs;
	}

	public String getJsonToString() {
		return jsonToString;
	}

	public void setJsonToString(String jsonToString) {
		this.jsonToString = jsonToString;
	}

	private String forwardUrl;

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public String index() {
		Elite elite = this.getEliteService().findEliteByID(this.getPid());
		if (elite != null) {
			List pes = elite.getParentIDs();
			for (int i = 0; i < pes.size(); i++) {
				Elite pe = this.getEliteService().findEliteByID(((Long) pes.get(i)).longValue());
				eliteDirs.add(pe);
			}
			eliteDirs.add(elite);
		}
		return "refine";
	}

	public String showelite() {
		List elist = this.getEliteService().findElitesByPidBid(this.getPid(), this.getBid());
		List flist = this.getForumService().findForumsElite(this.getBid(), this.getBid(), this.getPid());
		Map<String, List> emap = new HashMap<String, List>();
		emap.put("dir", elist);
		emap.put("forum", flist);

		JSONObject json = JSONObject.fromObject(emap);
		//logger.debug(json.toString());
		this.setJsonToString(json.toString());
		return RESULT_JSONSTRING;
	}

	public String manage() {
		Elite elite = this.getEliteService().findEliteByID(this.getPid());
		if (elite != null) {
			List pes = elite.getParentIDs();
			for (int i = 0; i < pes.size(); i++) {
				Elite pe = this.getEliteService().findEliteByID(((Long) pes.get(i)).longValue());
				eliteDirs.add(pe);
			}
			eliteDirs.add(elite);
		}
		return "refineManage";
	}

	public String adddir() {
		Map<String, Object> emap = new HashMap<String, Object>();
		if (StringUtils.isBlank(this.getEliteName())) {
			emap.put("codeid", "E_NULL");
			emap.put("message", this.getText("error.nullerror"));
		}
		Elite elite = new Elite();
		elite.setBoardID(this.getBid());
		elite.setCreateUser(this.getUserSession().getUserName());
		elite.setEliteName(this.getEliteName());
		elite.setEliteTime(System.currentTimeMillis());
		elite.setOrders(0);
		elite.setParentID(this.getPid());
		try {
			elite = this.getEliteService().createElite(elite);
			emap.put("codeid", "0");
			emap.put("message", this.getText("refine.adddir.ok"));
			emap.put("elite", elite);

		} catch (BbscsException e) {
			logger.error(e);
			emap.put("codeid", "E_REFINE_ADDDIR_ERROR");
			emap.put("message", this.getText("error.refine.adddir.error"));
		}
		JSONObject json = JSONObject.fromObject(emap);
		this.setJsonToString(json.toString());
		return RESULT_JSONSTRING;
	}

	public String intodir() {
		List forums = this.getForumService().findForumsInIds(this.getBid(), this.getPostIds());
		List<Forum> forumsSave = new ArrayList<Forum>();
		for (int i = 0; i < forums.size(); i++) {
			Forum forum = (Forum) forums.get(i);
			forum.setEliteID(this.getEliteDir());
			forumsSave.add(forum);
		}

		try {
			this.getForumService().saveForums(forumsSave);
			this.getAjaxMessagesJson().setMessage("0", this.getText("refine.adddir.change.ok"));
		} catch (BbscsException ex1) {
			this.getAjaxMessagesJson()
					.setMessage("E_REFINE_CHANGEDIR_ERROR", this.getText("error.refine.change.error"));
		}
		// this.getAjaxMessagesJson().setMessage("0",
		// this.getText("refine.adddir.change.ok"));
		return RESULT_AJAXJSON;
	}

	public String del() {
		List forums = this.getForumService().findForumsInIds(this.getBid(), this.getPostIds());
		List<Forum> forumsDel = new ArrayList<Forum>();
		for (int i = 0; i < forums.size(); i++) {
			Forum forum = (Forum) forums.get(i);
			forum.setElite(0);
			forum.setEliteID(0);
			forumsDel.add(forum);
		}
		try {
			this.getForumService().saveForumsEliteDel(forumsDel);
			this.getAjaxMessagesJson().setMessage("0", this.getText("refine.adddir.del.ok"));
		} catch (BbscsException ex2) {
			this.getAjaxMessagesJson().setMessage("E_REFINE_DEL_ERROR", this.getText("error.refine.del.error"));
		}
		return RESULT_AJAXJSON;
	}

	public String outdir() {
		List forums = this.getForumService().findForumsInIds(this.getBid(), this.getPostIds());
		List<Forum> forumsSave = new ArrayList<Forum>();
		Elite e = this.getEliteService().findEliteByID(this.getPid());
		boolean isDel = false;
		for (int i = 0; i < forums.size(); i++) {
			Forum forum = (Forum) forums.get(i);
			if (forum.getEliteID() != 0) {
				forum.setEliteID(e.getParentID());
			} else {
				forum.setElite(0);
				isDel = true;
			}
			forumsSave.add(forum);
		}
		try {
			if (isDel) {
				this.getForumService().saveForumsEliteDel(forumsSave);
			} else {
				this.getForumService().saveForums(forumsSave);
			}
			this.getAjaxMessagesJson().setMessage("0", this.getText("refine.adddir.out.ok"));
		} catch (BbscsException ex3) {
			this.getAjaxMessagesJson().setMessage("E_REFINE_OUTDIR_ERROR", this.getText("error.refine.out.error"));
		}
		return RESULT_AJAXJSON;
	}

	public String edit() {
		Elite elite = this.getEliteService().findEliteByID(this.getEliteId());
		Map<String, Object> emap = new HashMap<String, Object>();
		if (elite != null) {
			emap.put("codeid", "0");
			emap.put("message", "");
			emap.put("elite", elite);
		} else {
			emap.put("codeid", "E_REFINE_GETELITE_ERROR");
			emap.put("message", this.getText("error.refine.getelite"));
		}
		JSONObject json = JSONObject.fromObject(emap);
		this.setJsonToString(json.toString());
		return RESULT_JSONSTRING;
	}

	public String editdo() {
		Elite elite = this.getEliteService().findEliteByID(this.getEliteId());
		if (elite != null) {
			elite.setEliteName(this.getEliteName());
			elite.setOrders(this.getOrders());
			try {
				this.getEliteService().saveElite(elite);
				this.getAjaxMessagesJson().setMessage("0", this.getText("refine.edit.ok"));
			} catch (BbscsException ex4) {
				logger.error(ex4);
				this.getAjaxMessagesJson().setMessage("E_REFINE_EDIEELITE_ERROR",
						this.getText("error.refine.edit.error"));
			}
		} else {
			this.getAjaxMessagesJson().setMessage("E_REFINE_EDIEELITE_ERROR", this.getText("error.refine.edit.error"));
		}
		return RESULT_AJAXJSON;
	}

	public String deldir() {
		Elite elite = this.getEliteService().findEliteByID(this.getEliteId());
	      try {
	        long pid = elite.getParentID();
	        this.getEliteService().removeElite(elite);

	        this.setForwardUrl(BBSCSUtil.getActionMappingURLWithoutPrefix("refine?action=manage&pid=" +pid + "&bid=" +this.getBid()));
	        return SUCCESS;
	      }
	      catch (BbscsException ex5) {
	        this.addActionError(this.getText("error.refine.dir.del"));
	        return ERROR;
	      }
	}

}
