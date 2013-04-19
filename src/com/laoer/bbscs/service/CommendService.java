package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.Commend;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.web.PageList;
import com.laoer.bbscs.service.web.Pages;
import java.util.List;

/**
 * <p>Title: Tianyi BBS</p>
 *
 * <p>Description: BBSCS</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Laoer.com</p>
 *
 * @author Gong Tianyi
 * @version 7.0
 */
public interface CommendService {

  /**
   *
   * @param commend Commend
   * @return Commend
   * @throws BbscsException
   */
  public Commend saveCommend(Commend commend) throws BbscsException;

  /**
   *
   * @param id String
   * @return Commend
   */
  public Commend findCommendByID(String id);

  /**
   *
   * @param postID String
   * @return Commend
   */
  public Commend findCommendByPostID(String postID);

  /**
   *
   * @param commendBoardID long
   * @return int
   */
  public int getCommendNumByCommendBoardID(long commendBoardID);

  /**
   *
   * @param commendBoardID long
   * @param pages Pages
   * @return PageList
   */
  public PageList findCommendsByCommendBoardID(long commendBoardID, Pages pages);

  /**
   *
   * @param commendTop int
   * @return int
   */
  public int getCommendNumByCommendTop(int commendTop);

  /**
   *
   * @param commendTop int
   * @param pages Pages
   * @return PageList
   */
  public PageList findCommendsByCommendTop(int commendTop, Pages pages);

  /**
   *
   * @param commend Commend
   * @throws BbscsException
   */
  public void removeCommend(Commend commend) throws BbscsException;

  /**
   *
   * @param postID String
   * @throws BbscsException
   */
  public void removeCommend(String postID) throws BbscsException;

  /**
   *
   * @param commendBoardID long
   * @param ids List
   * @throws BbscsException
   */
  public void removeCommend(long commendBoardID, List ids) throws BbscsException;

  /**
   *
   * @param num int
   * @throws BbscsException
   */
  public void createCommendTopFile(int num) throws BbscsException;

  /**
   *
   * @param commendTop int
   * @param num int
   * @return List
   */
  public List findCommendsByCommendTopCache(int commendTop, int num);

}
