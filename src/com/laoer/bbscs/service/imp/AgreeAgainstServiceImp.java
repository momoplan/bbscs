package com.laoer.bbscs.service.imp;

import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

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
public class AgreeAgainstServiceImp
    implements AgreeAgainstService {

  private static final Log logger = LogFactory.getLog(AgreeAgainstServiceImp.class);

  private AgreeAgainstDAO agreeAgainstDAO;

  public AgreeAgainstServiceImp() {
  }

  /**
   *
   * @param agreeAgainst AgreeAgainst
   * @return AgreeAgainst
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.AgreeAgainstService method
   */
  public AgreeAgainst saveAgreeAgainst(AgreeAgainst agreeAgainst) throws BbscsException {
    try {
      return this.getAgreeAgainstDAO().saveAgreeAgainst(agreeAgainst);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param userID String
   * @param postID String
   * @param bid long
   * @return AgreeAgainst
   * @todo Implement this com.laoer.bbscs.service.AgreeAgainstService method
   */
  public AgreeAgainst findAgreeAgainstByUidPidBid(String userID, String postID, long bid) {
    return this.getAgreeAgainstDAO().findAgreeAgainstByUidPidBid(userID, postID, bid);
  }

  /**
   *
   * @param time long
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.AgreeAgainstService method
   */
  public void removeOutTime(long time) throws BbscsException {
    try {
      this.getAgreeAgainstDAO().removeOutTime(time);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  public AgreeAgainstDAO getAgreeAgainstDAO() {
    return agreeAgainstDAO;
  }

  public void setAgreeAgainstDAO(AgreeAgainstDAO agreeAgainstDAO) {
    this.agreeAgainstDAO = agreeAgainstDAO;
  }
}
