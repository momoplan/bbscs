package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.SysNumStat;
import java.util.*;

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
public interface SysNumStatDAO {

  /**
   *
   * @param sns SysNumStat
   * @return SysNumStat
   */
  public SysNumStat saveSysNumStat(SysNumStat sns);

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
   * @param firstResult int
   * @param maxResults int
   * @return List
   */
  public List findSysNumStats(int firstResult, int maxResults);

}
