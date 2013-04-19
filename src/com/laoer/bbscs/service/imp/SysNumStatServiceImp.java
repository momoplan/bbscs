package com.laoer.bbscs.service.imp;

import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.service.web.*;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
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
public class SysNumStatServiceImp
    implements SysNumStatService {

  private static final Log logger = LogFactory.getLog(SysNumStatServiceImp.class);

  private SysNumStatDAO sysNumStatDAO;

  public SysNumStatServiceImp() {
  }

  /**
   *
   * @param sns SysNumStat
   * @return SysNumStat
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.SysNumStatService method
   */
  public SysNumStat saveSysNumStat(SysNumStat sns) throws BbscsException {
    try {
      return this.getSysNumStatDAO().saveSysNumStat(sns);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  public SysNumStat saveSysNumStat(String recDate, SysNumStat sns) throws BbscsException {
    try {
      SysNumStat snso = this.getSysNumStatDAO().findSysNumStatByRecDate(recDate);
      if (snso == null) {
        sns.setRecDate(recDate);
        sns = this.getSysNumStatDAO().saveSysNumStat(sns);
      }
      return sns;
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   *
   * @param recDate String
   * @return SysNumStat
   * @todo Implement this com.laoer.bbscs.service.SysNumStatService method
   */
  public SysNumStat findSysNumStatByRecDate(String recDate) {
    return this.getSysNumStatDAO().findSysNumStatByRecDate(recDate);
  }

  /**
   *
   * @return int
   * @todo Implement this com.laoer.bbscs.service.SysNumStatService method
   */
  public long getSysNumStatNum() {
    return this.getSysNumStatDAO().getSysNumStatNum();
  }

  /**
   *
   * @param pages Pages
   * @return PageList
   * @todo Implement this com.laoer.bbscs.service.SysNumStatService method
   */
  public PageList findSysNumStats(Pages pages) {
    PageList pl = new PageList();
    if (pages.getTotalNum() == -1) {
      pages.setTotalNum(this.getSysNumStatDAO().getSysNumStatNum());
    }
    pages.executeCount();

    List l = this.getSysNumStatDAO().findSysNumStats(pages.getSpage(), pages.getPerPageNum());
    pl.setObjectList(l);
    pl.setPages(pages);
    return pl;
  }

  public SysNumStatDAO getSysNumStatDAO() {
    return sysNumStatDAO;
  }

  public void setSysNumStatDAO(SysNumStatDAO sysNumStatDAO) {
    this.sysNumStatDAO = sysNumStatDAO;
  }
}
