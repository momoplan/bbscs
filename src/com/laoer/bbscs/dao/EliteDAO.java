package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.Elite;
import java.util.*;

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
public interface EliteDAO {

  /**
   *
   * @param elite Elite
   * @return Elite
   */
  public Elite saveElite(Elite elite);

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
   */
  public void removeElite(Elite elite);

  /**
   *
   * @param ids List
   * @return List
   */
  public List findElitesInIds(List ids);

}
