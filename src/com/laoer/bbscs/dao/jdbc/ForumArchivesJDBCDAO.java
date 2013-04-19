/**
 *
 */
package com.laoer.bbscs.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.laoer.bbscs.bean.Forum;
import com.laoer.bbscs.dao.ForumArchivesDAO;

/**
 *
 */
public class ForumArchivesJDBCDAO extends JdbcDaoSupport implements ForumArchivesDAO {

	/*
	 * （非 Javadoc）
	 *
	 * @see com.laoer.bbscs.dao.ForumArchivesDAO#saveForumArchives(long,
	 *      com.laoer.bbscs.bean.Forum)
	 */
	public void saveForumArchives(long bid, Forum f) {
		String sql = "insert into bbscs_forumarchives_"
				+ (bid % 10)
				+ " (ParentID, MainID, BoardID, BoardName, ReNum, Face, UserID, UserName, NickName, Title, Detail, Sign, ArtSize, Click, PostTime, LastTime, IPAddress, IsNew, Elite, EliteID, Agree, BeAgainst, CanNotDel, CanNotRe, Commend, DelSign, DelUserID, DelUserName, DelTime, DelIP, Amend, DoEliteName, DoEliteTime, HaveAttachFile, AttachFileName, LastPostUserName, LastPostTitle, LastPostNickName, IsTop, IsLock, Auditing, AuditingAttachFile, IsVote, IsHidden, IsHiddenValue, EditType, QuoteText, QuoteEditType, PostType, TitleColor, UserBlog, IndexStatus, EmailInform, MsgInform, VoteID, TagID, TagName, IsGuest, PreviewAttach, ID, IsAnonymousReply) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] o = new Object[] { f.getParentID(), f.getMainID(), f.getBoardID(), f.getBoardName(), f.getReNum(),
				f.getFace(), f.getUserID(), f.getUserName(), f.getNickName(), f.getTitle(), f.getDetail(), f.getSign(),
				f.getArtSize(), f.getClick(), f.getPostTime(), f.getLastTime(), f.getIpAddress(), f.getIsNew(),
				f.getElite(), f.getEliteID(), f.getAgree(), f.getBeAgainst(), f.getCanNotDel(), f.getCanNotRe(),
				f.getCommend(), f.getDelSign(), f.getDelUserID(), f.getDelUserName(), f.getDelTime(), f.getDelIP(),
				f.getAmend(), f.getDoEliteName(), f.getDoEliteTime(), f.getHaveAttachFile(),
				attachFileName2String(f.getAttachFileName()), f.getLastPostUserName(), f.getLastPostTitle(),
				f.getLastPostNickName(), f.getIsTop(), f.getIsLock(), f.getAuditing(), f.getAuditingAttachFile(),
				f.getIsVote(), f.getIsHidden(), f.getIsHiddenValue(), f.getEditType(), f.getQuoteText(),
				f.getQuoteEditType(), f.getPostType(), f.getTitleColor(), f.getUserBlog(), f.getIndexStatus(),
				f.getEmailInform(), f.getMsgInform(), f.getVoteID(), f.getTagID(), f.getTagName(), f.getIsGuest(),
				f.getPreviewAttach(), f.getId(), f.getIsAnonymousReply()};
		this.getJdbcTemplate().update(sql, o);
	}

	private String attachFileName2String(List attachFileName) {
		String text = "";
		if (attachFileName != null) {
			for (int i = 0; i < attachFileName.size(); i++) {
				String fileName = (String) attachFileName.get(i);
				text = text + fileName + ",";
			}
			if (text.endsWith(",")) {
				text = text.substring(0, (text.length() - 1));
			}
		}
		return text;
	}

}
