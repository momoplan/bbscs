package com.laoer.bbscs.fio;

import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.bean.UserInfoSimple;

import java.io.*;

public interface UserInfoFileIO {

	/**
	 * 写用户文件
	 *
	 * @param userInfo
	 *            UserInfo
	 */
	public void writeUserFile(UserInfo userInfo) throws IOException;

	public void delUserPicFile(UserInfo userInfo);

	public void saveUserUpFile(UserInfo userInfo, String distFileName,
			InputStream instream) throws IOException;

	public UserInfoSimple getUserInfoSimple(String userID);

}
