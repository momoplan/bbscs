package com.laoer.bbscs.service.imp;

import com.laoer.bbscs.service.FriendFactory;
import com.laoer.bbscs.bean.Friend;

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
public class FriendFactoryImp
    implements FriendFactory {

  public FriendFactoryImp() {
  }

  /**
   * 实例化Friend对象
   *
   * @param userId String
   * @return Friend
   * @todo Implement this com.laoer.bbscs.service.FriendFactory method
   */
  public synchronized Friend getInstance(String userId) {
    return new Friend();
  }
}
