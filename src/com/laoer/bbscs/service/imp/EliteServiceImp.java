package com.laoer.bbscs.service.imp;

import java.util.*;

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
public class EliteServiceImp
    implements EliteService {

  private static final Log logger = LogFactory.getLog(EliteServiceImp.class);

  private EliteDAO eliteDAO;

  private ForumDAO forumDAO;

  public EliteServiceImp() {
  }

  /**
   *
   * @param elite Elite
   * @return Elite
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.EliteService method
   */
  public Elite saveElite(Elite elite) throws BbscsException {
    try {
      return this.getEliteDAO().saveElite(elite);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param elite Elite
   * @return Elite
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.EliteService method
   */
  @SuppressWarnings("unchecked")
public Elite createElite(Elite elite) throws BbscsException {
    Elite pElite = this.getEliteDAO().findEliteByID(elite.getParentID());
    if (pElite != null) {
      List pElites = new ArrayList();
      pElites.addAll(pElite.getParentIDs());
      pElites.add(pElite.getId());
      elite.setParentIDs(pElites);
    }
    try {
      return this.getEliteDAO().saveElite(elite);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param id long
   * @return Elite
   * @todo Implement this com.laoer.bbscs.service.EliteService method
   */
  public Elite findEliteByID(long id) {
    return this.getEliteDAO().findEliteByID(id);
  }

  /**
   *
   * @param pid long
   * @param bid long
   * @return List
   * @todo Implement this com.laoer.bbscs.service.EliteService method
   */
  public List findElitesByPidBid(long pid, long bid) {
    return this.getEliteDAO().findElitesByPidBid(pid, bid);
  }

  /**
   *
   * @param elite Elite
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.EliteService method
   */
  public void removeElite(Elite elite) throws BbscsException {
    try {
      List l = this.getForumDAO().findForumsElite(elite.getBoardID(), elite.getBoardID(),
                                                  elite.getId().longValue());
      for (int i = 0; i < l.size(); i++) {
        Forum forum = (Forum) l.get(i);
        forum.setEliteID(elite.getParentID());
        this.getForumDAO().saveOrUpdateForum(forum);
      }
      this.getEliteDAO().removeElite(elite);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param ids List
   * @return List
   * @todo Implement this com.laoer.bbscs.service.EliteService method
   */
  public List findElitesInIds(List ids) {
    return this.getEliteDAO().findElitesInIds(ids);
  }

  public EliteDAO getEliteDAO() {
    return eliteDAO;
  }

  public ForumDAO getForumDAO() {
    return forumDAO;
  }

  public void setEliteDAO(EliteDAO eliteDAO) {
    this.eliteDAO = eliteDAO;
  }

  public void setForumDAO(ForumDAO forumDAO) {
    this.forumDAO = forumDAO;
  }
}
