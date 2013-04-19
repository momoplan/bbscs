package com.laoer.bbscs.service.imp;

import com.laoer.bbscs.service.SubscibeFactory;
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
public class SubscibeFactoryImp
    implements SubscibeFactory {

  public SubscibeFactoryImp() {
  }

  /**
   *
   * @param bid long
   * @return Subscibe
   * @todo Implement this com.laoer.bbscs.service.SubscibeFactory method
   */
  public synchronized Subscibe getInstance(long bid) {
    return new Subscibe();
  }
}
