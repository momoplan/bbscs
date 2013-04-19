package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.BookMark;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.web.PageList;
import com.laoer.bbscs.service.web.Pages;

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
public interface BookMarkService {

  /**
   * 保存或更新BookMark对象
   * @param bm BookMark
   * @return BookMark
   * @throws BbscsException
   */
  public BookMark saveBookMark(BookMark bm) throws BbscsException;

  /**
   * 根据ID和UserID取得BookMark对象
   * @param id String
   * @param userID String
   * @return BookMark
   */
  public BookMark findBookMarkByIDUserID(String id, String userID);

  /**
   * 根据UserID取得BookMark数量
   * @param userID String
   * @return int
   */
  public long getBookMarkNumByUserID(String userID);

  /**
   * 取得BookMark分页对象
   * @param userID String
   * @param pages Pages
   * @return PageList
   */
  public PageList findBookMarks(String userID, Pages pages);

  /**
   * 根据UserID和isShare取得BookMark数量
   * @param userID String
   * @param isShare int
   * @return int
   */
  public long getBookMarkNumByUserIDShare(String userID, int isShare);

  /**
   * 根据UserID和isShare取得BookMark分页对象
   * @param userID String
   * @param isShare int
   * @param pages Pages
   * @return PageList
   */
  public PageList findBookMarksByUserIDShare(String userID, int isShare, Pages pages);

  /**
   * 删除BookMark对象
   * @param bm BookMark
   * @throws BbscsException
   */
  public void removeBookMark(BookMark bm) throws BbscsException;

  /**
   * 根据ID和UserID删除BookMark对象
   * @param id String
   * @param userID String
   * @throws BbscsException
   */
  public void removeBookMarkByIDUserID(String id, String userID) throws BbscsException;
}
