package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.Vote;

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
public interface VoteDAO {

  /**
   *
   * @param vote Vote
   * @return Vote
   */
  public Vote saveVote(Vote vote);

  /**
   *
   * @param id String
   * @return Vote
   */
  public Vote findVoteByID(String id);

}
