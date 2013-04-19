package com.laoer.bbscs.service.imp;

import org.apache.commons.logging.*;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.service.web.*;
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
public class BookMarkServiceImp
    implements BookMarkService {

  private static final Log logger = LogFactory.getLog(BookMarkServiceImp.class);

  private BookMarkDAO bookMarkDAO;

  public BookMarkServiceImp() {
  }

  /**
   * 保存或更新BookMark对象
   *
   * @param bm BookMark
   * @return BookMark
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.BookMarkService method
   */
  public BookMark saveBookMark(BookMark bm) throws BbscsException {
    try {
      return this.getBookMarkDAO().saveBookMark(bm);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 根据ID和UserID取得BookMark对象
   *
   * @param id String
   * @param userID String
   * @return BookMark
   * @todo Implement this com.laoer.bbscs.service.BookMarkService method
   */
  public BookMark findBookMarkByIDUserID(String id, String userID) {
    return this.getBookMarkDAO().findBookMarkByIDUserID(id, userID);
  }

  /**
   * 根据UserID取得BookMark数量
   *
   * @param userID String
   * @return int
   * @todo Implement this com.laoer.bbscs.service.BookMarkService method
   */
  public long getBookMarkNumByUserID(String userID) {
    return this.getBookMarkDAO().getBookMarkNumByUserID(userID);
  }

  /**
   * 取得BookMark分页对象
   *
   * @param userID String
   * @param pages Pages
   * @return PageList
   * @todo Implement this com.laoer.bbscs.service.BookMarkService method
   */
  public PageList findBookMarks(String userID, Pages pages) {
    PageList pl = new PageList();
    if (pages.getTotalNum() == -1) {
      pages.setTotalNum(this.getBookMarkDAO().getBookMarkNumByUserID(userID));
    }
    pages.executeCount();

    List l = this.getBookMarkDAO().findBookMarks(userID, pages.getSpage(), pages.getPerPageNum());
    pl.setObjectList(l);
    pl.setPages(pages);
    return pl;
  }

  /**
   * 根据UserID和isShare取得BookMark数量
   * @param userID String
   * @param isShare int
   * @return int
   */
  public long getBookMarkNumByUserIDShare(String userID, int isShare) {
    return this.getBookMarkDAO().getBookMarkNumByUserIDShare(userID, isShare);
  }

  /**
   * 根据UserID和isShare取得BookMark分页对象
   * @param userID String
   * @param isShare int
   * @param pages Pages
   * @return PageList
   */
  public PageList findBookMarksByUserIDShare(String userID, int isShare, Pages pages) {
    PageList pl = new PageList();
    if (pages.getTotalNum() == -1) {
      pages.setTotalNum(this.getBookMarkDAO().getBookMarkNumByUserIDShare(userID, isShare));
    }
    pages.executeCount();

    List l = this.getBookMarkDAO().findBookMarksByUserIDShare(userID, isShare, pages.getSpage(),
        pages.getPerPageNum());
    pl.setObjectList(l);
    pl.setPages(pages);
    return pl;
  }

  /**
   * 删除BookMark对象
   *
   * @param bm BookMark
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.BookMarkService method
   */
  public void removeBookMark(BookMark bm) throws BbscsException {
    try {
      this.getBookMarkDAO().removeBookMark(bm);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 根据ID和UserID删除BookMark对象
   *
   * @param id String
   * @param userID String
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.BookMarkService method
   */
  public void removeBookMarkByIDUserID(String id, String userID) throws BbscsException {
    try {
      this.getBookMarkDAO().removeBookMarkByIDUserID(id, userID);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  public BookMarkDAO getBookMarkDAO() {
    return bookMarkDAO;
  }

  public void setBookMarkDAO(BookMarkDAO bookMarkDAO) {
    this.bookMarkDAO = bookMarkDAO;
  }
}
