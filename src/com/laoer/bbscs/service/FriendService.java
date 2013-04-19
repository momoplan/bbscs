package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.Friend;
import com.laoer.bbscs.exception.BbscsException;
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
public interface FriendService {

  /**
   * 保存或更新Friend对象
   * @param f Friend
   * @return Friend
   * @throws BbscsException
   */
  public Friend saveFriend(Friend f) throws BbscsException;

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
   * @throws BbscsException
   */
  public void removeFriend(Friend f) throws BbscsException;

  /**
   * 删除Friend对象
   * @param id String
   * @param ownId String
   * @throws BbscsException
   */
  public void removeFriend(String id, String ownId) throws BbscsException;

  /**
   * 好友列表ID保存至文件
   * @param ownId String
   */
  public void friendIDsToFile(String ownId);

  /**
   * 读取文件，取出好友ID列表
   * @param ownId String
   * @return List
   */
  public List fileToFriendIDs(String ownId);

  public List findFriendIds(String ownId, int isBlack);

}
