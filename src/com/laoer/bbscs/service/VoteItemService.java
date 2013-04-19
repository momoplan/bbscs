package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.VoteItem;
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
public interface VoteItemService {

  /**
   *
   * @param voteItem VoteItem
   * @return VoteItem
   * @throws BbscsException
   */
  public VoteItem saveVoteItem(VoteItem voteItem) throws BbscsException;

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
   * @param userId String
   * @param voteId String
   * @param ids String[]
   * @throws BbscsException
   */
  public void createVoteItemAdd(String userId, String voteId, List ids) throws BbscsException;
}
