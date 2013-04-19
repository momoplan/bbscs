package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.AgreeAgainst;
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
public interface AgreeAgainstService {

  /**
   *
   * @param agreeAgainst AgreeAgainst
   * @return AgreeAgainst
   * @throws BbscsException
   */
  public AgreeAgainst saveAgreeAgainst(AgreeAgainst agreeAgainst) throws BbscsException;

  /**
   *
   * @param userID String
   * @param postID String
   * @param bid long
   * @return AgreeAgainst
   */
  public AgreeAgainst findAgreeAgainstByUidPidBid(String userID, String postID, long bid);

  /**
   *
   * @param time long
   * @throws BbscsException
   */
  public void removeOutTime(long time) throws BbscsException;

}
