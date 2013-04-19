package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.AgreeAgainst;

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
public interface AgreeAgainstDAO {

  /**
   *
   * @param agreeAgainst AgreeAgainst
   * @return AgreeAgainst
   */
  public AgreeAgainst saveAgreeAgainst(AgreeAgainst agreeAgainst);

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
   */
  public void removeOutTime(long time);

}
