package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.laoer.bbscs.bean.AgreeAgainst;
import com.laoer.bbscs.bean.Forum;
import com.laoer.bbscs.bean.Subscibe;
import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.comm.OrderObj;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.AgreeAgainstService;
import com.laoer.bbscs.service.BoardService;
import com.laoer.bbscs.service.ForumService;
import com.laoer.bbscs.service.SubscibeFactory;
import com.laoer.bbscs.service.SubscibeService;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.service.mail.TemplateMail;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;
import com.laoer.bbscs.web.interceptor.RemoteAddrAware;
import com.laoer.bbscs.web.interceptor.RequestBasePathAware;

public class PostOpt extends BaseBoardAction implements RemoteAddrAware, RequestBasePathAware {

	/**
	 *
	 */
	private static final long serialVersionUID = -3754920382947045652L;

	private String remoteAddr;

	private String basePath;

	private String id;

	private String mainid;

	private ForumService forumService;

	private BoardService boardService;

	private SysConfig sysConfig;

	private UserService userService;

	private AjaxMessagesJson ajaxMessagesJson;

	private AgreeAgainstService agreeAgainstService;

	private SubscibeFactory subscibeFactory;

	private SubscibeService subscibeService;

	private TemplateMail templateMail;

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBasePath() {
		return basePath;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AgreeAgainstService getAgreeAgainstService() {
		return agreeAgainstService;
	}

	public void setAgreeAgainstService(AgreeAgainstService agreeAgainstService) {
		this.agreeAgainstService = agreeAgainstService;
	}

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
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

	public SubscibeFactory getSubscibeFactory() {
		return subscibeFactory;
	}

	public void setSubscibeFactory(SubscibeFactory subscibeFactory) {
		this.subscibeFactory = subscibeFactory;
	}

	public SubscibeService getSubscibeService() {
		return subscibeService;
	}

	public void setSubscibeService(SubscibeService subscibeService) {
		this.subscibeService = subscibeService;
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

	public TemplateMail getTemplateMail() {
		return templateMail;
	}

	public void setTemplateMail(TemplateMail templateMail) {
		this.templateMail = templateMail;
	}

	public String getMainid() {
		return mainid;
	}

	public void setMainid(String mainid) {
		this.mainid = mainid;
	}

	public String votyes() {
		return this.vote(true);
	}

	public String votno() {
		return this.vote(false);
	}

	private String vote(boolean yesno) {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.getAjaxMessagesJson().setMessage("E_POST_NOT_EXIST", this.getText("error.post.getpost"));
			return RESULT_AJAXJSON;
		}
		if (this.getSysConfig().getPostVoteRec() == 0) { // 不记录投票结果
			if (yesno) {
				f.setAgree(f.getAgree() + 1);
			} else {
				f.setBeAgainst(f.getBeAgainst() + 1);
			}
			try {
				f = this.getForumService().saveOrUpdateForum(f);
				if (yesno) {
					this.getAjaxMessagesJson().setMessage("0", this.getText("post.agreeagainst.ok"),
							String.valueOf(f.getAgree()));
				} else {
					this.getAjaxMessagesJson().setMessage("0", this.getText("post.agreeagainst.ok"),
							String.valueOf(f.getBeAgainst()));
				}
			} catch (BbscsException ex) {
				this.getAjaxMessagesJson().setMessage("E_POST_AGREEAGAINST_ERROR",
						this.getText("error.post.agreeagainst.error"));
			}
		} else {
			AgreeAgainst agreeAgainst = this.getAgreeAgainstService().findAgreeAgainstByUidPidBid(
					this.getUserSession().getId(), this.getId(), this.getBid());
			if (agreeAgainst == null) {
				if (yesno) {
					f.setAgree(f.getAgree() + 1);
				} else {
					f.setBeAgainst(f.getBeAgainst() + 1);
				}
				try {
					f = this.getForumService().saveOrUpdateForum(f);
					agreeAgainst = new AgreeAgainst();
					agreeAgainst.setBoardID(f.getBoardID());
					agreeAgainst.setCreateTime(System.currentTimeMillis());
					agreeAgainst.setPostID(f.getId());
					agreeAgainst.setUserID(this.getUserSession().getId());
					if (yesno) {
						agreeAgainst.setVoteType(0);
					} else {
						agreeAgainst.setVoteType(1);
					}
					this.getAgreeAgainstService().saveAgreeAgainst(agreeAgainst);
					if (yesno) {
						this.getAjaxMessagesJson().setMessage("0", this.getText("post.agreeagainst.ok"),
								String.valueOf(f.getAgree()));
					} else {
						this.getAjaxMessagesJson().setMessage("0", this.getText("post.agreeagainst.ok"),
								String.valueOf(f.getBeAgainst()));
					}
				} catch (BbscsException ex) {
					this.getAjaxMessagesJson().setMessage("E_POST_AGREEAGAINST_ERROR",
							this.getText("error.post.agreeagainst.error"));
				}
			} else {
				this.getAjaxMessagesJson().setMessage("E_POST_AGREEAGAINST_REPEAT",
						this.getText("error.post.agreeagainst.repeat"));
			}
		}
		return RESULT_AJAXJSON;
	}

	public String dela() {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.getAjaxMessagesJson().setMessage("E_POST_NOT_EXIST", this.getText("error.post.getpost"));
			return RESULT_AJAXJSON;
		}
		if ((f.getCanNotRe() == 0 && f.getUserID().equals(this.getUserSession().getId()))
				|| this.getUserSession().isHaveBoardSpecialPermission(Constant.SPERMISSION_CAN_DELPOST)) {
			f.setDelIP(this.getRemoteAddr());
			f.setDelSign(1);
			f.setDelTime(System.currentTimeMillis());
			f.setDelUserID(this.getUserSession().getId());
			f.setDelUserName(this.getUserSession().getUserName());
			if (f.getIndexStatus() == Constant.INDEX_STATUS_NO_INDEX) {
				f.setIndexStatus(Constant.INDEX_STATUS_NO_INDEX_TO_DEL);
			} else {
				f.setIndexStatus(Constant.INDEX_STATUS_NEED_DEL);
			}

			UserInfo ui = this.getUserService().findUserInfoById(f.getUserID());
			try {
				this.getForumService().delaPost(f, this.getBoard(), ui);
				this.getAjaxMessagesJson().setMessage("0", this.getText("post.del.ok"));

			} catch (BbscsException ex1) {
				this.getAjaxMessagesJson().setMessage("E_POST_DEL_ERROR", this.getText("error.post.del1"));
			}
		} else {
			this.getAjaxMessagesJson().setMessage("E_NO_PERMISSION", this.getText("error.noPermission.ajax"));
		}
		return RESULT_AJAXJSON;
	}

	public String subs() {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.getAjaxMessagesJson().setMessage("E_POST_NOT_EXIST", this.getText("error.post.getpost"));
			return RESULT_AJAXJSON;
		}
		if (this.getSubscibeService().findSubscibeByPostID(this.getId(), this.getUserSession().getId(), this.getBid()) == null) {
			UserInfo ui = this.getUserService().findUserInfoById(this.getUserSession().getId());
			if (ui != null) {
				Subscibe subs = this.getSubscibeFactory().getInstance(this.getBid());
				subs.setBoardID(this.getBid());
				subs.setCreateTime(new Date());
				subs.setEmailinform(1);
				subs.setMsginform(1);
				subs.setNickName(this.getUserSession().getNickName());
				subs.setPostID(this.getId());
				subs.setPostTitle(f.getTitle());
				subs.setUserEmail(ui.getEmail());
				subs.setUserID(this.getUserSession().getId());
				subs.setUserName(this.getUserSession().getUserName());
				subs.setUserLocale(ui.getUserLocale());
				try {
					this.getSubscibeService().saveSubscibe(subs);
					this.getAjaxMessagesJson().setMessage("0", this.getText("post.subs.ok"));
				} catch (BbscsException ex2) {
					this.getAjaxMessagesJson().setMessage("E_POST_SUBS_ERROR", this.getText("error.post.subs.error"));
				}
			}
		} else {
			this.getAjaxMessagesJson().setMessage("E_POST_SUBS_EXIST", this.getText("error.post.subs.exist"));

		}
		return RESULT_AJAXJSON;
	}

	public String top() {
		return this.settop(true);
	}

	public String untop() {
		return this.settop(false);
	}

	private String settop(boolean yesno) {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.getAjaxMessagesJson().setMessage("E_POST_NOT_EXIST", this.getText("error.post.getpost"));
			return RESULT_AJAXJSON;
		}
		if (yesno) {
			if (f.getIsTop() == 0) {
				f.setIsTop(1);
				try {
					this.getForumService().saveOrUpdateForum(f);
					this.getAjaxMessagesJson().setMessage("0", this.getText("post.top.set.ok"));
				} catch (BbscsException ex3) {
					this.getAjaxMessagesJson().setMessage("E_POST_TOP_SET_ERROR",
							this.getText("error.post.top.seterror"));
				}
			} else {
				this.getAjaxMessagesJson().setMessage("E_POST_TOP_EXIST", this.getText("error.post.top.exist"));
			}
		} else {
			if (f.getIsTop() != 0) {
				f.setIsTop(0);
				try {
					this.getForumService().saveOrUpdateForum(f);
					this.getAjaxMessagesJson().setMessage("0", this.getText("post.top.untop.ok"));
				} catch (BbscsException ex3) {
					this.getAjaxMessagesJson().setMessage("E_POST_TOP_SET_ERROR",
							this.getText("error.post.top.unerror"));
				}
			} else {
				this.getAjaxMessagesJson().setMessage("E_POST_TOP_ISUNTOP", this.getText("error.post.top.untop"));
			}
		}
		return RESULT_AJAXJSON;
	}

	public String cannotdel() {
		return this.candelset(true);
	}

	public String candel() {
		return this.candelset(false);
	}

	private String candelset(boolean yesno) {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.getAjaxMessagesJson().setMessage("E_POST_NOT_EXIST", this.getText("error.post.getpost"));
			return RESULT_AJAXJSON;
		}
		if (yesno) {
			if (f.getCanNotDel() == 0) {
				f.setCanNotDel(1);
				try {
					this.getForumService().saveOrUpdateForum(f);

					this.getAjaxMessagesJson().setMessage("0", this.getText("post.cannotdel.setok"));
				} catch (BbscsException ex4) {
					this.getAjaxMessagesJson().setMessage("E_POST_CANNOTDEL_SET_ERROR",
							this.getText("error.post.cannotdel.seterror"));
				}
			} else {
				this.getAjaxMessagesJson()
						.setMessage("E_POST_CANNOTDEL_EXIST", this.getText("error.post.notdel.exist"));
			}
		} else {
			if (f.getCanNotDel() != 0) {
				f.setCanNotDel(0);
				try {
					this.getForumService().saveOrUpdateForum(f);

					this.getAjaxMessagesJson().setMessage("0", this.getText("post.uncannotdel.setok"));
				} catch (BbscsException ex5) {
					this.getAjaxMessagesJson().setMessage("E_POST_CANNOTDEL_SET_ERROR",
							this.getText("error.post.cannotdel.unerror"));
				}
			} else {
				this.getAjaxMessagesJson().setMessage("E_POST_CANNOTDEL_ISUN", this.getText("error.post.iscandel"));
			}
		}
		return RESULT_AJAXJSON;
	}

	public String commend() {
		return this.commendset(true);
	}

	public String uncommend() {
		return this.commendset(false);
	}

	private String commendset(boolean yesno) {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.getAjaxMessagesJson().setMessage("E_POST_NOT_EXIST", this.getText("error.post.getpost"));
			return RESULT_AJAXJSON;
		}
		if (yesno) {
			if (f.getCommend() == 0) {
				try {
					this.getForumService().saveForumCommend(0, this.getBoard(), f);

					this.getAjaxMessagesJson().setMessage("0", this.getText("post.commend.setok"));
				} catch (BbscsException ex3) {
					this.getAjaxMessagesJson().setMessage("E_POST_COMMEND_SET_ERROR",
							this.getText("error.post.commend.seterror"));
				}
			} else {
				this.getAjaxMessagesJson().setMessage("E_POST_COMMEND_EXIST", this.getText("error.post.commend.exist"));
			}
		} else {
			if (f.getCommend() != 0) {
				try {
					this.getForumService().saveForumCommend(1, this.getBoard(), f);
					this.getAjaxMessagesJson().setMessage("0", this.getText("post.uncommend.setok"));
				} catch (BbscsException ex3) {
					this.getAjaxMessagesJson().setMessage("E_POST_COMMEND_SET_ERROR",
							this.getText("error.post.commend.unerror"));
				}
			} else {
				this.getAjaxMessagesJson()
						.setMessage("E_POST_COMMEND_ISUN", this.getText("error.post.uncommend.exist"));
			}
		}
		return RESULT_AJAXJSON;
	}

	public String lock() {
		return this.lockset(true);
	}

	public String unlock() {
		return this.lockset(false);
	}

	private String lockset(boolean yesno) {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.getAjaxMessagesJson().setMessage("E_POST_NOT_EXIST", this.getText("error.post.getpost"));
			return RESULT_AJAXJSON;
		}
		if (yesno) {
			if (f.getIsLock() == 0) {
				f.setIsLock(1);
				try {
					this.getForumService().saveOrUpdateForum(f);
					this.getAjaxMessagesJson().setMessage("0", this.getText("post.lock.setok"));
				} catch (BbscsException ex3) {
					this.getAjaxMessagesJson().setMessage("E_POST_LOCK_SET_ERROR",
							this.getText("error.post.lock.seterror"));
				}
			} else {
				this.getAjaxMessagesJson().setMessage("E_POST_LOCK_EXIST", this.getText("error.post.lock.exist"));
			}
		} else {
			if (f.getIsLock() != 0) {
				f.setIsLock(0);
				try {
					this.getForumService().saveOrUpdateForum(f);
					this.getAjaxMessagesJson().setMessage("0", this.getText("post.unlock.setok"));
				} catch (BbscsException ex3) {
					this.getAjaxMessagesJson().setMessage("E_POST_LOCK_SET_ERROR",
							this.getText("error.post.lock.unerror"));
				}
			} else {
				this.getAjaxMessagesJson().setMessage("E_POST_LOCK_ISUN", this.getText("error.post.unlock.exist"));
			}
		}
		return RESULT_AJAXJSON;
	}

	public String elite() {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.getAjaxMessagesJson().setMessage("E_POST_NOT_EXIST", this.getText("error.post.getpost"));
			return RESULT_AJAXJSON;
		}
		if (f.getElite() == 0) {
			f.setElite(this.getBid());
			f.setDoEliteName(this.getUserSession().getUserName());
			f.setDoEliteTime(System.currentTimeMillis());
			UserInfo ui = this.getUserService().findUserInfoById(f.getUserID());
			if (ui == null) {
				this.getAjaxMessagesJson().setMessage("E_USER_NOEXIST", this.getText("error.getuser"));
				return RESULT_AJAXJSON;
			}
			try {
				this.getForumService().savePostElite(f, ui);
				this.getAjaxMessagesJson().setMessage("0", this.getText("post.elite.setok"));
			} catch (BbscsException ex6) {
				this.getAjaxMessagesJson().setMessage("E_POST_ELITE_SET_ERROR", this.getText("post.elite.seterror"));
			}
		} else {
			this.getAjaxMessagesJson().setMessage("E_POST_ELITE_EXIST", this.getText("post.elite.isexist"));
		}
		return RESULT_AJAXJSON;
	}

	@SuppressWarnings("unchecked")
	public String mailsend() {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.getAjaxMessagesJson().setMessage("E_POST_NOT_EXIST", this.getText("error.post.getpost"));
			return RESULT_AJAXJSON;
		}

		if (StringUtils.isNotBlank(this.getUserSession().getEmail()) && this.getSysConfig().getUseEmail() == 1) {
			try {

				String detail = "";
				if (f.getIsHidden() != 0) {
					detail = this.getText("post.hidden.notmailsend");
				} else {
					detail = this.getForumService().getForumDetail(f, false);
					if (f.getEditType() == 0) {
						detail = BBSCSUtil.filterText(detail, (this.getBoard().getAllowHTML() == 1), (this.getBoard()
								.getAllowUBB() == 1), true);
					} // else {detail = f.getDetail();}
				}

				String url = this.getBasePath()
						+ BBSCSUtil.getActionMappingURLWithoutPrefix("main?action=read&bid=" + this.getBoard().getId()
								+ "&postID=" + f.getMainID());

				Map root = new HashMap();
				root.put("website", this.getSysConfig().getForumName());
				root.put("title", f.getTitle());
				root.put("detail", detail);
				root.put("url", url);

				this.getTemplateMail().sendMailFromTemplate(this.getUserSession().getEmail(), f.getTitle(),
						"mailSend.ftl", root, this.getLocale());

				this.getAjaxMessagesJson().setMessage("0", this.getText("post.mailsend.ok"));
			} catch (Exception ex7) {
				ex7.printStackTrace();
				this.getAjaxMessagesJson().setMessage("E_POST_MAILSEND_ERROR", this.getText("post.mailsend.error"));
			}
			return RESULT_AJAXJSON;
		} else {
			this.getAjaxMessagesJson().setMessage("E_POST_MAILSEND_ERROR", this.getText("post.mailsend.error"));
			return RESULT_AJAXJSON;
		}
	}

	@SuppressWarnings("unchecked")
	public String mailsendtopic() {
		OrderObj[] oo = { new OrderObj("postTime", Constant.ORDER_ASC) };
		List l = this.getForumService().findForumsTopicAll(this.getBid(), this.getMainid(), 0, 0, oo);
		if (l == null || l.isEmpty()) {
			this.getAjaxMessagesJson().setMessage("E_POST_NOT_EXIST", this.getText("error.post.getpost"));
			return RESULT_AJAXJSON;
		}
		if (StringUtils.isNotBlank(this.getUserSession().getEmail()) && this.getSysConfig().getUseEmail() == 1) {
			try {
				String detail = "";
				Forum mainForum = (Forum) l.get(0);
				List<Forum> nl = new ArrayList<Forum>();
				for (int i = 0; i < l.size(); i++) {
					Forum f = (Forum) l.get(i);
					if (f.getIsHidden() != 0) {
						detail = this.getText("post.hidden.notmailsend");
					} else {
						detail = this.getForumService().getForumDetail(f, false);
						if (f.getEditType() == 0) {
							f.setDetail(BBSCSUtil.filterText(detail, (this.getBoard().getAllowHTML() == 1), (this
									.getBoard().getAllowUBB() == 1), true));
						} else {
							f.setDetail(detail);
						}
					}
					nl.add(f);
				}

				String url = this.getBasePath()
						+ BBSCSUtil.getActionMappingURLWithoutPrefix("main?action=read&bid=" + this.getBoard().getId()
								+ "&postID=" + this.getMainid());

				Map root = new HashMap();
				root.put("website", this.getSysConfig().getForumName());
				root.put("nl", nl);
				root.put("url", url);

				this.getTemplateMail().sendMailFromTemplate(this.getUserSession().getEmail(), mainForum.getTitle(),
						"mailSendTopic.ftl", root, this.getLocale());

				this.getAjaxMessagesJson().setMessage("0", this.getText("post.mailsend.ok"));
			} catch (Exception ex8) {
				ex8.printStackTrace();
				this.getAjaxMessagesJson().setMessage("E_POST_MAILSEND_ERROR", this.getText("post.mailsend.error"));
			}
			return RESULT_AJAXJSON;
		} else {
			this.getAjaxMessagesJson().setMessage("E_POST_MAILSEND_ERROR", this.getText("post.mailsend.error"));
			return RESULT_AJAXJSON;
		}
	}

	@SuppressWarnings("unchecked")
	public String report() {
		Forum f = this.getForumService().findForumByID(this.getId(), this.getBid());
		if (f == null) {
			this.getAjaxMessagesJson().setMessage("E_POST_NOT_EXIST", this.getText("error.post.getpost"));
			return RESULT_AJAXJSON;
		}

		if (this.getSysConfig().getUseEmail() == 1) {
			try {

				String title = this.getText("post.report.msg", new String[] { this.getUserSession().getUserName(),
						this.getBoard().getBoardName(), f.getTitle() });

				String detail = "";
				detail = this.getForumService().getForumDetail(f, false);
				if (f.getEditType() == 0) {
					detail = BBSCSUtil.filterText(detail, (this.getBoard().getAllowHTML() == 1), (this.getBoard()
							.getAllowUBB() == 1), true);
				} // else { detail = f.getDetail(); }
				String url = this.getBasePath()
						+ BBSCSUtil.getActionMappingURLWithoutPrefix("main?action=read&bid=" + this.getBoard().getId()
								+ "&postID=" + f.getMainID());

				Map root = new HashMap();
				root.put("userName", this.getUserSession().getUserName());
				root.put("title", f.getTitle());
				root.put("detail", detail);
				root.put("url", url);

				this.getTemplateMail().sendMailFromTemplate(this.getSysConfig().getWebmasterEmail(), title,
						"report.ftl", root, this.getLocale());

				this.getAjaxMessagesJson().setMessage("0", this.getText("post.report.ok"));
			} catch (Exception ex7) {
				ex7.printStackTrace();
				this.getAjaxMessagesJson().setMessage("E_POST_REPORT_ERROR", this.getText("error.post.report.error"));
			}
			return RESULT_AJAXJSON;

		} else {
			this.getAjaxMessagesJson().setMessage("E_POST_REPORT_ERROR", this.getText("error.post.report.error"));
			return RESULT_AJAXJSON;
		}

	}

}
