package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.BookMark;
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
public interface BookMarkDAO {

  /**
   * 保存或更新BookMark对象
   * @param bm BookMark
   * @return BookMark
   */
  public BookMark saveBookMark(BookMark bm);

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
   * 根据UserID取得BookMark列表
   * @param userID String
   * @param firstResult int
   * @param maxResults int
   * @return List
   */
  public List findBookMarks(final String userID, final int firstResult, final int maxResults);

  /**
   * 根据UserID和isShare取得BookMark数量
   * @param userID String
   * @param isShare int
   * @return int
   */
  public long getBookMarkNumByUserIDShare(String userID, int isShare);

  /**
   * 根据UserID和isShare取得BookMark列表
   * @param userID String
   * @param isShare int
   * @param firstResult int
   * @param maxResults int
   * @return List
   */
  public List findBookMarksByUserIDShare(final String userID, final int isShare, final int firstResult,
                            final int maxResults);

  /**
   * 删除BookMark对象
   * @param bm BookMark
   */
  public void removeBookMark(BookMark bm);

  /**
   * 根据ID和UserID删除BookMark对象
   * @param id String
   * @param userID String
   */
  public void removeBookMarkByIDUserID(String id, String userID);
}
