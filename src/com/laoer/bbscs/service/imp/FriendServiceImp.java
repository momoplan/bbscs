package com.laoer.bbscs.service.imp;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;
import com.laoer.bbscs.service.config.*;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.io.*;

import com.laoer.bbscs.comm.Constant;

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
public class FriendServiceImp
    implements FriendService {

  private static final Log logger = LogFactory.getLog(FriendServiceImp.class);

  private FriendDAO friendDAO;

  private UserConfig userConfig;

  public FriendServiceImp() {
  }

  /**
   * 保存或更新Friend对象
   *
   * @param f Friend
   * @return Friend
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.FriendService method
   */
  public Friend saveFriend(Friend f) throws BbscsException {
    try {
      return this.getFriendDAO().saveFriend(f);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 根据ID取得Friend对象
   * @param id String
   * @param ownId String
   * @return Friend
   * @todo Implement this com.laoer.bbscs.service.FriendService method
   */
  public Friend findFriendByID(String id, String ownId) {
    return this.getFriendDAO().findFriendByID(id, ownId);
  }

  /**
   * 根据朋友名、自身ID取得Friend对象
   *
   * @param fname String
   * @param ownId String
   * @return Friend
   * @todo Implement this com.laoer.bbscs.service.FriendService method
   */
  public Friend findFriendByName(String fname, String ownId) {
    return this.getFriendDAO().findFriendByName(fname, ownId);
  }

  /**
   * 取得朋友数量
   *
   * @param ownId String
   * @param isBlack int
   * @return int
   * @todo Implement this com.laoer.bbscs.service.FriendService method
   */
  public long getFriendNum(String ownId, int isBlack) {
    return this.getFriendDAO().getFriendNum(ownId, isBlack);
  }

  /**
   * 取得朋友列表
   *
   * @param ownId String
   * @param isBlack int
   * @return List
   * @todo Implement this com.laoer.bbscs.service.FriendService method
   */
  public List findFriends(String ownId, int isBlack) {
    return this.getFriendDAO().findFriends(ownId, isBlack);
  }

  /**
   * 删除Friend对象
   *
   * @param f Friend
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.FriendService method
   */
  public void removeFriend(Friend f) throws BbscsException {
    try {
      this.getFriendDAO().removeFriend(f);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 删除Friend对象
   *
   * @param id String
   * @param ownId String
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.FriendService method
   */
  public void removeFriend(String id, String ownId) throws BbscsException {
    try {
      this.getFriendDAO().removeFriend(id, ownId);
    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  /**
   * 好友列表ID保存至文件
   *
   * @param ownId String
   * @todo Implement this com.laoer.bbscs.service.FriendService method
   */
  public void friendIDsToFile(String ownId) {
    List l = this.getFriendDAO().findFriends(ownId, 0);
    StringBuffer sb = new StringBuffer();
    Friend f;
    for (int i = 0; i < l.size(); i++) {
      f = (Friend) l.get(i);
      sb.append(f.getFriendID());
      sb.append(",");
    }
    File toFile = new File(this.getUserConfig().getUserFilePath(ownId) + Constant.USER_FRIEND_FILE);
    try {
		FileUtils.writeStringToFile(toFile, sb.toString(), Constant.CHARSET);
	} catch (IOException e) {
		logger.error(e);
	}
  }

  /**
   * 读取文件，取出好友ID列表
   *
   * @param ownId String
   * @return List
   * @todo Implement this com.laoer.bbscs.service.FriendService method
   */
  public List fileToFriendIDs(String ownId) {
    List<String> l = new ArrayList<String>();
    File fromFile = new File(this.getUserConfig().getUserFilePath(ownId) + Constant.USER_FRIEND_FILE);
    try {
		String fids = FileUtils.readFileToString(fromFile, Constant.CHARSET);
		String[] ids = fids.split(",");
		if (ids != null) {
		  for (int i = 0; i < ids.length; i++) {
		    //System.out.println(ids[i]);
		    l.add(ids[i]);
		  }
		}
	} catch (IOException e) {
		logger.error(e);
	}
    return l;
  }

  @SuppressWarnings("unchecked")
public List findFriendIds(String ownId, int isBlack) {
	  List l = this.getFriendDAO().findFriendIds(ownId, isBlack);
	  if (l.isEmpty()) {
		  l.add("0");
	  }
	  return l;
  }

  public void setFriendDAO(FriendDAO friendDAO) {
    this.friendDAO = friendDAO;
  }

  public void setUserConfig(UserConfig userConfig) {
    this.userConfig = userConfig;
  }

  public FriendDAO getFriendDAO() {
    return friendDAO;
  }

  public UserConfig getUserConfig() {
    return userConfig;
  }
}
