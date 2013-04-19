package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.Subscibe;

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
public interface SubscibeFactory {

  /**
   *
   * @param bid long
   * @return Subscibe
   */
  public Subscibe getInstance(long bid);

}
