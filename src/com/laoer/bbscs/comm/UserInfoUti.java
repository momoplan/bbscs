package com.laoer.bbscs.comm;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import net.sf.json.JSONObject;
import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.service.SysStatService;
import com.laoer.bbscs.service.mail.TemplateMail;

public class UserInfoUti {
	
	/**
	 * 将如意彩的用户信息转换成bbs的用户（Tuserinfo ----> UserInfo）
	 * @param user
	 * @return
	 * @throws Exception
	 */
	
	private static IPSeeker ipSeeker;
	private String userRemoteAddr = "";
	
	public static IPSeeker getIpSeeker() {
		return ipSeeker;
	}

	public static void setIpSeeker(IPSeeker ipSeeker) {
		UserInfoUti.ipSeeker = ipSeeker;
	}
	
	public String getUserRemoteAddr() {
		return this.userRemoteAddr;
	}

	public void setUserRemoteAddr(String userRemoteAddr) {
		this.userRemoteAddr = userRemoteAddr;
	}


	public UserInfo rycUserTobbsUser(JSONObject user) throws Exception{
		UserInfo userinfo = new UserInfo();
		userinfo.setUserName(user.getString("userName").equals("null") || user.getString("userName").length() ==0?"":user.getString("userName"));
		userinfo.setNickName(user.getString("nickname").equals("null") || user.getString("nickname").length() ==0?"":user.getString("nickname"));
		userinfo.setEmail(user.getString("email").equals("null") || user.getString("email").length() ==0?"":user.getString("email"));
		userinfo.setPasswd(user.getString("password"));
		userinfo.setRePasswd(user.getString("password"));
		userinfo.setLoginIP(UserInfoUti.getIpAddress());
		userinfo.setLastLoginTime(new Date());
		userinfo.setRegTime(new Date(user.getLong("regtime")));
		userinfo.setUserNo(user.getString("userno"));
		/*userinfo.setLastLoginTime(new Date());
		userinfo.setLifeForce(0);
		userinfo.setLiterary(0);
		userinfo.setPicFileName("");
		userinfo.setPostPerNum(0);
		userinfo.setExperience(0);
		userinfo.setForumPerNum(0);
		userinfo.setForumViewMode(0);
		userinfo.setHavePic(0);*/
		userinfo.setUserFrom(UserInfoUti.getIpSeeker().getCountry(this.getUserRemoteAddr()));
		String cartid = user.getString("certid");
		
		if(!"null".equals(cartid) && cartid.length()!=0){
			String year="";
			String moth="";
			String day="";
			if(cartid.length()==18){
				year = cartid.substring(6,10);
				moth = cartid.substring(10,12);
				day = cartid.substring(12,14);
				
			}else if(cartid.length()==15){
				year = "19"+cartid.substring(6,2);
				moth = cartid.substring(8,2);
				day = cartid.substring(10,2);
				
			}
			
			userinfo.setBirthYear(Integer.parseInt(year));
			userinfo.setBirthMonth(Integer.parseInt(moth));
			userinfo.setBirthDay(Integer.parseInt(day));
			
		}
		
		return userinfo;
	}
	
	
	/**
	 * 获取当前用户主机的ip地址
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getIpAddress() throws UnknownHostException { 
		InetAddress address = InetAddress.getLocalHost(); 
		return address.getHostAddress(); 
	}

}
