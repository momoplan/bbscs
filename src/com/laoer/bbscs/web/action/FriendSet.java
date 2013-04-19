package com.laoer.bbscs.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.bean.Friend;
import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.FriendFactory;
import com.laoer.bbscs.service.FriendService;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;
import java.util.*;
import org.apache.commons.lang.*;

public class FriendSet extends BaseMainAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(FriendSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -4451774441195480514L;

	private FriendService friendService;

	private FriendFactory friendFactory;

	private UserService userService;

	private AjaxMessagesJson ajaxMessagesJson;

	private List friendList;

	private String friendName;

	private String id;

	private int isBlack;

	private String friendComment;

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public FriendFactory getFriendFactory() {
		return friendFactory;
	}

	public void setFriendFactory(FriendFactory friendFactory) {
		this.friendFactory = friendFactory;
	}

	public FriendService getFriendService() {
		return friendService;
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List getFriendList() {
		return friendList;
	}

	public void setFriendList(List friendList) {
		this.friendList = friendList;
	}

	public String getFriendComment() {
		return friendComment;
	}

	public void setFriendComment(String friendComment) {
		this.friendComment = friendComment;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIsBlack() {
		return isBlack;
	}

	public void setIsBlack(int isBlack) {
		this.isBlack = isBlack;
	}

	public String index() {
		return SUCCESS;
	}

	public String flist() {
		this.setFriendList(this.getFriendService().findFriends(this.getUserSession().getId(), this.getIsBlack()));
		return "flist";
	}

	public String add() {
		this.setAction("addsave");
		return INPUT;
	}

	public String addsave() {
		if (StringUtils.isBlank(this.getFriendName())) { // 用户名不能为空
			this.getAjaxMessagesJson().setMessage("E_FRIEND_FNAME_NULL", this.getText("error.friend.fname.null"));
			return RESULT_AJAXJSON;
		}

		if (this.getFriendName().equalsIgnoreCase(this.getUserSession().getUserName())) { // 要添加的用户和自己一致
			if (this.getIsBlack() == 0) {
				this.getAjaxMessagesJson().setMessage("E_FRIEND_FNAME_ISME", this.getText("error.friend.fnameisme0"));
			} else {
				this.getAjaxMessagesJson().setMessage("E_FRIEND_FNAME_ISME", this.getText("error.friend.fnameisme1"));
			}
			return RESULT_AJAXJSON;
		}

		String friendComment = StringUtils.trimToEmpty(this.getFriendComment());

		if (BBSCSUtil.getSysCharsetStrLength(friendComment) > 1500) {
			this.getAjaxMessagesJson().setMessage("E_FRIEND_COMMENT_TOOLONG",
					this.getText("error.friend.comment.toolong"));
			return RESULT_AJAXJSON;
		}

		UserInfo ui = this.getUserService().findUserInfoByUserName(this.getFriendName());
		if (ui == null) { // 检查要添加的用户是否存在
			this.getAjaxMessagesJson().setMessage("E_FRIEND_USER_NOEXIST", this.getText("error.friend.usernoexist"));
			return RESULT_AJAXJSON;
		}

		Friend f = this.getFriendService().findFriendByName(this.getFriendName(), this.getUserSession().getId());

		if (f != null) { // 该用户已经存在
			if (this.getIsBlack() == 0) { // 添加好友情况下
				if (f.getIsBlack() == 1) { // 该用户已经在黑名单中，转到好友名单中
					f.setIsBlack(0);
					try {
						f = this.getFriendService().saveFriend(f); // 保存用户
						// this.getFriendService().friendIDsToFile(this.userSession.getId());
						// //将用户列表写入文件
						ui.setUserKnow(ui.getUserKnow() + 1); // 增加用户人缘系数
						this.getUserService().saveUserInfo(ui);
						this.getAjaxMessagesJson().setMessage("0", this.getText("friend.add.ok"));
					} catch (BbscsException ex) {
						logger.error(ex);
						this.getAjaxMessagesJson().setMessage("E_CODEID_FRIEND_ADDFAILED",
								this.getText("error.friend.add.error"));
					}
				} else { // 该用户已经是好友
					this.getAjaxMessagesJson().setMessage("E_FRIEND_EXIST", this.getText("error.friend.userexist0"));
				}
			} else { // 添加黑名单情况
				if (f.getIsBlack() == 0) { // 该用户在好友名单中，转到黑名单
					f.setIsBlack(1);
					try {
						f = this.getFriendService().saveFriend(f); // 保存用户
						// this.getFriendService().friendIDsToFile(us.getId());
						// //将用户列表写入文件
						ui.setUserKnow(ui.getUserKnow() - 1); // 减少用户人缘系数
						this.getUserService().saveUserInfo(ui);
						this.getAjaxMessagesJson().setMessage("0", this.getText("friend.add.ok"));
					} catch (BbscsException ex) {
						logger.error(ex);
						this.getAjaxMessagesJson().setMessage("E_CODEID_FRIEND_ADDFAILED",
								this.getText("error.friend.add.error"));
					}

				} else { // 该用户已经在黑名单中
					this.getAjaxMessagesJson().setMessage("E_FRIEND_EXIST", this.getText("error.friend.userexist1"));
				}
			}
			return RESULT_AJAXJSON;
		}

		if (this.getFriendService().getFriendNum(this.getUserSession().getId(), -1) >= 100) {
			this.getAjaxMessagesJson().setMessage("E_FRIEND_NUM",
					this.getText("error.friend.num.toolang", new String[] { "100" }));
			return RESULT_AJAXJSON;
		}

		if (this.getIsBlack() == 0) { // 添加好友情况下
			if (ui.getAcceptFriend() == 0) { // 对方拒绝加入好友
				this.getAjaxMessagesJson().setMessage("E_FRIEND_NOTACCEPT", this.getText("error.friend.usernotaccept"));
			}
		}
		f = this.getFriendFactory().getInstance(this.getUserSession().getId());
		f.setFriendComment(friendComment);
		f.setFriendID(ui.getId());
		f.setFriendName(this.getFriendName());
		f.setIsBlack(this.getIsBlack());
		f.setUserID(this.getUserSession().getId());
		f.setUserName(this.getUserSession().getUserName());

		try {
			f = this.getFriendService().saveFriend(f); // 保存用户
			this.getFriendService().friendIDsToFile(this.getUserSession().getId()); // 将用户列表写入文件
			if (this.getIsBlack() == 0) { // 添加好友情况下
				ui.setUserKnow(ui.getUserKnow() + 1); // 增加用户人缘系数
				this.getUserService().saveUserInfo(ui);
			} else {
				ui.setUserKnow(ui.getUserKnow() - 1); // 减少用户人缘系数
				this.getUserService().saveUserInfo(ui);
			}

			this.getAjaxMessagesJson().setMessage("0", this.getText("friend.add.ok"));
		} catch (BbscsException ex) {
			logger.error(ex);
			this.getAjaxMessagesJson().setMessage("E_FRIEND_ADDFAILED", this.getText("error.friend.add.error"));
		}

		return RESULT_AJAXJSON;
	}

	public String del() {
		Friend f = this.getFriendService().findFriendByID(this.getId(), this.getUserSession().getId());
		if (f != null) {
			UserInfo ui = this.getUserService().findUserInfoById(f.getFriendID());
			int isBlack = f.getIsBlack();
			try {
				this.getFriendService().removeFriend(f);
				if (ui != null) {
					if (isBlack == 0) {
						ui.setUserKnow(ui.getUserKnow() - 1); // 减少用户人缘系数
						this.getUserService().saveUserInfo(ui);
					} else {
						ui.setUserKnow(ui.getUserKnow() + 1); // 增加用户人缘系数
						this.getUserService().saveUserInfo(ui);
					}
				}
				this.getAjaxMessagesJson().setMessage("0", this.getText("friend.del.ok"));
			} catch (BbscsException ex1) {
				logger.error(ex1);
				this.getAjaxMessagesJson().setMessage("E_FRIEND_DELFAILED", this.getText("error.friend.del.error"));
			}

		} else {
			this.getAjaxMessagesJson().setMessage("E_FRIEND_DELFAILED", this.getText("error.friend.del.error"));
		}
		return RESULT_AJAXJSON;
	}

}
