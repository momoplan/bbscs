package com.laoer.bbscs.service.imp;

import com.laoer.bbscs.service.BookMarkFactory;
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
public class BookMarkFactoryImp
    implements BookMarkFactory {

  public BookMarkFactoryImp() {
  }

  /**
   * 取得BookMark实例
   *
   * @param userId String
   * @return BookMark
   * @todo Implement this com.laoer.bbscs.service.BookMarkFactory method
   */
  public synchronized BookMark getInstance(String userId) {
    return new BookMark();
  }
}
