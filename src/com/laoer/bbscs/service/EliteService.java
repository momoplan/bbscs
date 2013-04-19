package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.Elite;
import com.laoer.bbscs.exception.BbscsException;
import java.util.List;

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
public interface EliteService {

  /**
   *
   * @param elite Elite
   * @return Elite
   * @throws BbscsException
   */
  public Elite saveElite(Elite elite) throws BbscsException;

  /**
   *
   * @param elite Elite
   * @return Elite
   * @throws BbscsException
   */
  public Elite createElite(Elite elite) throws BbscsException;

  /**
   *
   * @param id long
   * @return Elite
   */
  public Elite findEliteByID(long id);

  /**
   *
   * @param pid long
   * @param bid long
   * @return List
   */
  public List findElitesByPidBid(long pid, long bid);

  /**
   *
   * @param elite Elite
   * @throws BbscsException
   */
  public void removeElite(Elite elite) throws BbscsException;

  /**
   *
   * @param ids List
   * @return List
   */
  public List findElitesInIds(List ids);

}
