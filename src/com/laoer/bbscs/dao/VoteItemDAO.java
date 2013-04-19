package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.VoteItem;
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
public interface VoteItemDAO {

  /**
   *
   * @param voteItem VoteItem
   * @return VoteItem
   */
  public VoteItem saveVoteItem(VoteItem voteItem);

  /**
   *
   * @param id String
   * @return VoteItem
   */
  public VoteItem findVoteItemByID(String id);

  /**
   *
   * @param voteID String
   * @return List
   */
  public List findVoteItemsByVoteID(String voteID);

  /**
   *
   * @param ids List
   * @return List
   */
  public List findVoteItemsInIds(List ids);
}
