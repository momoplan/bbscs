package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.Friend;
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
public interface FriendDAO {

  /**
   * 保存或更新Friend对象
   * @param f Friend
   * @return Friend
   */
  public Friend saveFriend(Friend f);

  /**
   * 根据ID取得Friend对象
   * @param id String
   * @param ownId String
   * @return Friend
   */
  public Friend findFriendByID(String id, String ownId);

  /**
   * 根据朋友名、自身ID取得Friend对象
   * @param fname String
   * @param ownId String
   * @return Friend
   */
  public Friend findFriendByName(String fname, String ownId);

  /**
   * 取得朋友数量
   * @param ownId String
   * @param isBlack int
   * @return int
   */
  public long getFriendNum(String ownId, int isBlack);

  /**
   * 取得朋友列表
   * @param ownId String
   * @param isBlack int
   * @return List
   */
  public List findFriends(String ownId, int isBlack);

  /**
   * 删除Friend对象
   * @param f Friend
   */
  public void removeFriend(Friend f);

  /**
   * 删除Friend对象
   * @param id String
   * @param ownId String
   */
  public void removeFriend(String id, String ownId);

  public List findFriendIds(String ownId, int isBlack);

}
