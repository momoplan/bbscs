package com.laoer.bbscs.web.interceptor;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.bean.UserOnline;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.exception.BbscsException;
//import com.laoer.bbscs.service.Cache;
import com.laoer.bbscs.service.UserOnlineService;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;
import com.laoer.bbscs.web.servlet.UserCookie;
import com.laoer.bbscs.web.servlet.UserSession;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserOnlineInterceptor extends AbstractInterceptor {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(UserOnlineInterceptor.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -2606629266518786270L;

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		ActionContext ac = invocation.getInvocationContext();
		ServletContext servletContext = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		if (wc == null) {
			logger.error("ApplicationContext could not be found.");
		} else {
			HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) ac.get(ServletActionContext.HTTP_RESPONSE);

			UserSession us = (UserSession) ac.getSession().get(Constant.USER_SESSION_KEY);

			SysConfig sysConfig = (SysConfig) wc.getBean("sysConfig");

			UserCookie uc = new UserCookie(request, response, sysConfig);

			//System.out.println("uc.getLastActiveTime():" + uc.getLastActiveTime());
			//System.out.println("uc.getAddedOnlineTime():" + uc.getAddedOnlineTime());

			long nowTime = System.currentTimeMillis();

			long addedTime = nowTime - us.getLastActiveTime();

			us.setAddedOnlineTime(us.getAddedOnlineTime() + addedTime);
			us.setAddedOnlineHour(us.getAddedOnlineHour() + addedTime);

			if (us.getAddedOnlineTime() >= (sysConfig.getUserOnlineTime() *	 1000)) {
			//if (uc.getAddedOnlineTime() >= (sysConfig.getUserOnlineTime() * 1000)) {
				logger.debug("用户 " + us.getUserName() + " 在线累计超过" + sysConfig.getUserOnlineTime() + "秒");

				UserOnlineService userOnlineService = (UserOnlineService) wc.getBean("userOnlineService");

				UserOnline uo = userOnlineService.findUserOnlineByUserID(us.getId()); // 取得用户在线信息

				if (us.getGroupID() != Constant.USER_GROUP_GUEST) { // 如果用户不是游客
					logger.debug("[uo.getValidateCode():" + uo.getValidateCode() + "][uc.getValidateCode():"
							+ us.getValidateCode() + "]");
					if (uo != null && !uo.getValidateCode().equals(us.getValidateCode())) { // 用户重复登录

						String ajax = "html";
						Map map = ac.getParameters();
						String[] _ajax = (String[]) map.get("ajax");
						if (_ajax != null) {
							ajax = _ajax[0];
						}

						ResourceBundleMessageSource messageSource = (ResourceBundleMessageSource) wc
								.getBean("messageSource");
						String errorMsg = messageSource.getMessage("error.login.re", null, ac.getLocale());
						if (ajax.equalsIgnoreCase("html") || ajax.equalsIgnoreCase("shtml")) {
							return "relogin";
						} else {
							AjaxMessagesJson ajaxMessagesJson = (AjaxMessagesJson) wc.getBean("ajaxMessagesJson");
							ajaxMessagesJson.setMessage("E_LOGIN", errorMsg);
							ac.getValueStack().set("ajaxMessagesJson", ajaxMessagesJson);
							return "ajaxjson";
						}
					}
					if (uo == null) {
						uo = new UserOnline();
						uo.setAtPlace("");
						uo.setBoardID(0);
						uo.setNickName(us.getNickName());
						uo.setOnlineTime(nowTime);
						uo.setUserGroupID(us.getGroupID());
						uo.setUserID(us.getId());
						uo.setUserName(us.getUserName());
						uo.setValidateCode(us.getValidateCode());
						uo.setHiddenUser(0);

						try {
							uo = userOnlineService.createUserOnline(uo); // 加入在线用户表
						} catch (BbscsException ex) {
							logger.error(ex);
							return invocation.invoke();
						}
					}
				} else { // 是游客
					if (uo == null) { // 用户Online信息不存在
						ac.getSession().remove(Constant.USER_SESSION_KEY);
						UserService userService = (UserService) wc.getBean("userService");
						uo = new UserOnline();

						uo.setAtPlace("");
						uo.setBoardID(0);
						uo.setNickName("Guest");
						uo.setOnlineTime(nowTime);
						uo.setUserGroupID(Constant.USER_GROUP_GUEST);
						uo.setUserID(Constant.GUEST_USERID + nowTime);
						uo.setUserName(Constant.GUEST_USERNAME + nowTime);
						uo.setValidateCode(Constant.GUEST_USERID + "_" + nowTime);

						try {
							uo = userOnlineService.createUserOnline(uo);

							us = this.createGuestUserSession(uo.getUserID(), uo.getUserName(), userService);
							us.setLastActiveTime(System.currentTimeMillis());
							us.setValidateCode(uo.getValidateCode());// ???
							ac.getSession().put(Constant.USER_SESSION_KEY, us);

							uc.addGuestCookies();
							//--
							//uc.addLastActiveTime();

						} catch (BbscsException ex) {
							logger.error(ex);
						}
						return invocation.invoke();
					}
				}

				if (us.getGroupID() != Constant.USER_GROUP_GUEST) { // 如果不是游客

					UserService userService = (UserService) wc.getBean("userService");
					UserInfo ui = userService.findUserInfoById(us.getId());

					if (ui != null) { // 取得正确用户信息

						ui.setStayTime(ui.getStayTime() + (us.getAddedOnlineTime() / 1000)); // 增加用户在线时长
						us.setAddedOnlineTime(0);

						//--
						//uc.addAddedOnlineTime(0);

						// 刷新权限
						if (Constant.USE_PERMISSION_CACHE) {
							logger.debug("刷新用户权限");
							us.setGroupID(ui.getGroupID());
							us.setBoardPermissionArray(userService.getUserPermission(ui));
						}

						if (us.getAddedOnlineHour() >= 3600000) {
							logger.info("用户在线累计超过1个小时，增加用户经验值");
							ui.setExperience(ui.getExperience() + (int) (us.getAddedOnlineHour() / 3600000));
							us.setAddedOnlineHour(0);

							//--
							//uc.addAddedOnlineHour(0);

						}
						try {
							ui = userService.saveUserInfo(ui);
							// userService.writeUserFile(ui);
						} catch (BbscsException ex) {
							logger.error(ex);
						}
					}

				} else { // 游客
					us.setAddedOnlineTime(0);

					//--
					//uc.addAddedOnlineTime(0);

					if (us.getAddedOnlineHour() >= 3600000) {
						us.setAddedOnlineHour(0);

						//--
						//uc.addAddedOnlineHour(0);
					}
				}

				// 修改Online表用户在线时间
				if (uo.getOnlineTime() != nowTime) {
					uo.setOnlineTime(nowTime);
					try {
						userOnlineService.saveUserOnline(uo);
					} catch (BbscsException ex1) {
						logger.error(ex1);
					}
				}
			}

			us.setLastActiveTime(nowTime);
			ac.getSession().put(Constant.USER_SESSION_KEY, us);
		}

		return invocation.invoke();
	}

	private UserSession createGuestUserSession(String gUestID, String gUesrName, UserService userService) {
		UserSession us = new UserSession();

		us.setGroupID(Constant.USER_GROUP_GUEST);
		us.setId(gUestID);
		us.setNickName("Guest");
		us.setUserName(gUesrName);
		Map[] permissionMap = userService.getUserPermission(Constant.USER_GROUP_GUEST);
		us.setUserPermissionArray(permissionMap);
		return us;
	}

}
