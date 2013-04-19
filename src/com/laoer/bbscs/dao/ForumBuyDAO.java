package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.ForumBuy;

/**
 * <p>Title: Tianyi BBS</p>
 *
 * <p>Description: BBSCS</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Laoer.com</p>
 *
 * @author Gong Tianyi
 * @version 7.0
 */
public interface ForumBuyDAO {

  /**
   *
   * @param forumBuy ForumBuy
   * @return ForumBuy
   */
  public ForumBuy saveForumBuy(ForumBuy forumBuy);

  /**
   *
   * @param id String
   * @return ForumBuy
   */
  public ForumBuy findForumBuyId(String id);

  /**
   *
   * @param postId String
   * @param fromId String
   * @return ForumBuy
   */
  public ForumBuy findForumBuyByPostIdFromId(String postId, String fromId);

  /**
   *
   * @param postId String
   * @return int
   */
  public long getForumBuyNumByPostId(String postId);
}
