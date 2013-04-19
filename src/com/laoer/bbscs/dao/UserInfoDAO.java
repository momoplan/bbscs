/**
 *
 */
package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.*;

import java.util.*;

/**
 * @author laoer
 *
 */
public interface UserInfoDAO {

	/**
	 * 保存或更新UserInfo对象
	 *
	 * @param userInfo
	 *            UserInfo
	 * @return UserInfo
	 */
	public UserInfo saveUserInfo(UserInfo userInfo);
	
	/**
	 * 根据主键查找UserInfo对象
	 *
	 * @param id
	 *            String
	 * @return UserInfo
	 */
	public UserInfo findUserInfoById(String id);

	/**
	 * 根据用户名查找UserInfo对象
	 *
	 * @param userName
	 *            String
	 * @return UserInfo
	 */
	public UserInfo findUserInfoByUserName(String userName);

	/**
	 * 根据Email查找UserInfo对象
	 *
	 * @param email
	 *            String
	 * @return UserInfo
	 */
	public UserInfo findUserInfoByEmail(String email);

	/**
	 * 取得UserInfo分页列表
	 *
	 * @param orderby
	 *            String
	 * @param ascordesc
	 *            String
	 * @param firstResult
	 *            int
	 * @param maxResults
	 *            int
	 * @return List
	 */
	public List findUserInfoList(String orderby, String ascordesc,
			int firstResult, int maxResults);

	/**
	 * 取得所有用户数量
	 *
	 * @return int
	 */
	public long getAllUserNum();

	/**
	 *
	 * @param groupID
	 *            int
	 * @return int
	 */
	public long getUserNumByGroupID(int groupID);

	/**
	 *
	 * @param groupID
	 *            int
	 * @param orderby
	 *            String
	 * @param ascOrDesc
	 *            int
	 * @param firstResult
	 *            int
	 * @param maxResults
	 *            int
	 * @return List
	 */
	public List findUserInfosByGroupID(int groupID, String orderby,
			int ascOrDesc, int firstResult, int maxResults);

	/**根据userno 查用户信息
	 * @param userno
	 * @return
	 */
	public UserInfo findUserInfoByUserNo(String userno);
	
	/**查询用户信息
	 * @return
	 */
	public List findUserInfo();
	
}
