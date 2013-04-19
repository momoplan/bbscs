package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.BookMark;

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
public interface BookMarkFactory {

  /**
   * 取得BookMark实例
   * @param userId String
   * @return BookMark
   */
  public BookMark getInstance(String userId);

}
