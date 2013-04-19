package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.Vote;
import com.laoer.bbscs.exception.BbscsException;

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
public interface VoteService {

  /**
   *
   * @param vote Vote
   * @return Vote
   * @throws BbscsException
   */
  public Vote saveVote(Vote vote) throws BbscsException;

  /**
   *
   * @param id String
   * @return Vote
   */
  public Vote findVoteByID(String id);

}
