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
public class VoteServiceImp
    implements VoteService {

  private static final Log logger = LogFactory.getLog(VoteServiceImp.class);

  private VoteDAO voteDAO;

  public VoteServiceImp() {
  }

  /**
   *
   * @param vote Vote
   * @return Vote
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.VoteService method
   */
  public Vote saveVote(Vote vote) throws BbscsException {
    try {
      return this.getVoteDAO().saveVote(vote);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param id String
   * @return Vote
   * @todo Implement this com.laoer.bbscs.service.VoteService method
   */
  public Vote findVoteByID(String id) {
    return this.getVoteDAO().findVoteByID(id);
  }

  public VoteDAO getVoteDAO() {
    return voteDAO;
  }

  public void setVoteDAO(VoteDAO voteDAO) {
    this.voteDAO = voteDAO;
  }
}
