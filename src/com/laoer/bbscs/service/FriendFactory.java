package com.laoer.bbscs.service;

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
public interface FriendFactory {

  /**
   * 实例化Friend对象
   * @param userId String
   * @return Friend
   */
  public Friend getInstance(String userId);
}
