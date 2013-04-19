package com.laoer.bbscs.service.imp;

import org.apache.commons.logging.*;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;

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
public class VoteUserServiceImp
    implements VoteUserService {

  private static final Log logger = LogFactory.getLog(VoteUserServiceImp.class);

  private VoteUserDAO voteUserDAO;

  public VoteUserServiceImp() {
  }

  /**
   *
   * @param voteUser VoteUser
   * @return VoteUser
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.VoteUserService method
   */
  public VoteUser saveVoteUser(VoteUser voteUser) throws BbscsException {
    try {
      return this.getVoteUserDAO().saveVoteUser(voteUser);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param voteID String
   * @param userID String
   * @return VoteUser
   * @todo Implement this com.laoer.bbscs.service.VoteUserService method
   */
  public VoteUser findVoteUserByVoteIDUserID(String voteID, String userID) {
    return this.getVoteUserDAO().findVoteUserByVoteIDUserID(voteID, userID);
  }

  /**
   *
   * @param atime long
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.VoteUserService method
   */
  public void removeOutTime(long atime) throws BbscsException {
    try {
      this.getVoteUserDAO().removeOutTime(atime);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  public VoteUserDAO getVoteUserDAO() {
    return voteUserDAO;
  }

  public void setVoteUserDAO(VoteUserDAO voteUserDAO) {
    this.voteUserDAO = voteUserDAO;
  }
}
