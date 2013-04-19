package com.laoer.bbscs.service.imp;

import com.laoer.bbscs.service.FriendFactory;
import com.laoer.bbscs.bean.Friend;
import com.laoer.bbscs.comm.BBSCSUtil;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

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
public class FriendsFactoryImp
    implements FriendFactory {

  private static final Log logger = LogFactory.getLog(FriendsFactoryImp.class);

  public FriendsFactoryImp() {
  }

  /**
   * 实例化Friend对象
   *
   * @param userId String
   * @return Friend
   * @todo Implement this com.laoer.bbscs.service.FriendFactory method
   */
  public synchronized Friend getInstance(String userId) {
    try {
      return (Friend) Class.forName(BBSCSUtil.getClassName("Friend", userId)).newInstance();
    }
    catch (ClassNotFoundException ex) {
      logger.error(ex);
      return null;
    }
    catch (IllegalAccessException ex) {
      logger.error(ex);
      return null;
    }
    catch (InstantiationException ex) {
      logger.error(ex);
      return null;
    }

  }
}
