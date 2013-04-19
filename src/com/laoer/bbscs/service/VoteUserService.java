package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.VoteUser;
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
public interface VoteUserService {

  /**
   *
   * @param voteUser VoteUser
   * @return VoteUser
   * @throws BbscsException
   */
  public VoteUser saveVoteUser(VoteUser voteUser) throws BbscsException;

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
   * @throws BbscsException
   */
  public void removeOutTime(long atime) throws BbscsException;

}
