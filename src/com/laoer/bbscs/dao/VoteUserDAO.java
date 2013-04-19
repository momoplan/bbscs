package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.VoteUser;

/**
 * <p>Title: TianyiBBS</p>
 *
 * <p>Description: BBSCS</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Laoer.com</p>
 *
 * @author Laoer
 * @version 7.0
 */
public interface VoteUserDAO {

  /**
   *
   * @param voteUser VoteUser
   * @return VoteUser
   */
  public VoteUser saveVoteUser(VoteUser voteUser);

  /**
   *
   * @param voteID String
   * @param userID String
   * @return VoteUser
   */
  public VoteUser findVoteUserByVoteIDUserID(String voteID, String userID);

  /**
   *
   * @param atime long
   */
  public void removeOutTime(long atime);

}
