package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.SysNumStat;
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
public interface SysNumStatService {

  /**
   *
   * @param sns SysNumStat
   * @return SysNumStat
   * @throws BbscsException
   */
  public SysNumStat saveSysNumStat(SysNumStat sns) throws BbscsException;

  /**
   *
   * @param recDate String
   * @param sns SysNumStat
   * @return SysNumStat
   * @throws BbscsException
   */
  public SysNumStat saveSysNumStat(String recDate, SysNumStat sns) throws BbscsException;

  /**
   *
   * @param recDate String
   * @return SysNumStat
   */
  public SysNumStat findSysNumStatByRecDate(String recDate);

  /**
   *
   * @return int
   */
  public long getSysNumStatNum();

  /**
   *
   * @param pages Pages
   * @return PageList
   */
  public PageList findSysNumStats(Pages pages);
}
