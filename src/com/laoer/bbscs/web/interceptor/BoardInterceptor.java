package com.laoer.bbscs.web.interceptor;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.util.UrlHelper;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.bean.BoardMaster;
import com.laoer.bbscs.bean.Permission;
import com.laoer.bbscs.bean.UserOnline;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.BoardAuthUserService;
import com.laoer.bbscs.service.BoardService;
//import com.laoer.bbscs.service.Cache;
import com.laoer.bbscs.service.UserOnlineService;
//import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;
import com.laoer.bbscs.web.servlet.UserCookie;
import com.laoer.bbscs.web.servlet.UserSession;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.ActionSupport;

public class BoardInterceptor extends AbstractInterceptor {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(BoardInterceptor.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 4601925351169060354L;

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();
		Object action = invocation.getAction();
		String actionName = "/" + ac.getName();

		String ajax = "html";
		String saction = "";

		Map map = ac.getParameters();

		String[] _ajax = (String[]) map.get("ajax");
		if (_ajax != null) {
			ajax = _ajax[0];
		}

		String[] _saction = (String[]) map.get("action");
		if (_saction != null) {
			saction = _saction[0];
		}

		long bid = 0;

		String[] _bid = (String[]) map.get("bid");
		if (_bid != null) {
			bid = NumberUtils.toLong(_bid[0], 0);
		}

		logger.debug("[actionName:" + actionName + ",saction:" + saction + ",ajax:" + ajax + ",bid:" + bid + "]");

		ServletContext servletContext = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		if (wc == null) {
			logger.error("ApplicationContext could not be found.");
			return "intercepthtml";
		} else {
			ResourceBundleMessageSource messageSource = (ResourceBundleMessageSource) wc.getBean("messageSource");
			if (bid == 0) {
				String errorMsg = messageSource.getMessage("error.board.id", null, ac.getLocale());
				if (ajax.equalsIgnoreCase("html")) {
					ac.getValueStack().set("interceptError", errorMsg);
					return "intercepthtml";
				} else if (ajax.equalsIgnoreCase("shtml")) {
					ac.getValueStack().set("interceptError", errorMsg);
					return "interceptshtml";
				} else {
					AjaxMessagesJson ajaxMessagesJson = (AjaxMessagesJson) wc.getBean("ajaxMessagesJson");
					ajaxMessagesJson.setMessage("E_BOARDID_ERROR", errorMsg);
					ac.getValueStack().set("ajaxMessagesJson", ajaxMessagesJson);
					return "ajaxjson";
				}
			}

			BoardService boardService = (BoardService) wc.getBean("boardService");
			Board board = boardService.getBoardByID(bid);

			if (board == null) {
				String errorMsg = messageSource.getMessage("error.board.id", null, ac.getLocale());
				if (ajax.equalsIgnoreCase("html")) {
					ac.getValueStack().set("interceptError", errorMsg);
					return "intercepthtml";
				} else if (ajax.equalsIgnoreCase("shtml")) {
					ac.getValueStack().set("interceptError", errorMsg);
					return "interceptshtml";
				} else {
					AjaxMessagesJson ajaxMessagesJson = (AjaxMessagesJson) wc.getBean("ajaxMessagesJson");
					ajaxMessagesJson.setMessage("E_BOARDID_ERROR", errorMsg);
					ac.getValueStack().set("ajaxMessagesJson", ajaxMessagesJson);
					return "ajaxjson";
				}
			}

			if (action instanceof BoardAware) {
				((BoardAware) action).setBoard(board);

			}

			SysConfig sysConfig = (SysConfig) wc.getBean("sysConfig");
			HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) ac.get(ServletActionContext.HTTP_RESPONSE);

			UserCookie userCookie = new UserCookie(request, response, sysConfig);
			// System.out.println("userCookie.bid:" + userCookie.getBid() + "
			// bid:" + bid);

			// Cache userSessionCache = (Cache) wc.getBean("userSessionCache");
			// UserSession us = (UserSession)
			// userSessionCache.get(userCookie.getUserName());

			UserSession us = (UserSession) ac.getSession().get(Constant.USER_SESSION_KEY);

			logger.debug("[Current bid:" + bid + "][userCookie.bid:" + us.getBid() + "]");

			// if (us.getBid() != bid || us == null || us.getInitStatus() == 0)
			// {
			if (us.getBid() != bid) {
				// userCookie.addBid(bid);
				// userCookie.addBoardPass("");
				us.getBoardPermission().clear();
				us.getBoardSpecialPermission().clear();

				us.setBid(bid);
				us.setBoardPass("");
				/*
				 * if (Constant.USE_CLUSTER || us == null) {
				 * userSessionCache.remove(userCookie.getUserName());
				 *
				 * UserService userService = (UserService)
				 * wc.getBean("userService"); us =
				 * userService.getUserSession(userCookie.getUserName()); }
				 */

				Map[] maps = boardService.getBoardPermission(bid, us.getGroupID()); // 取得版区用户组权限

				us.setBoardPermissionArray(maps);
				
				String showName = us.getNickName();
				String re = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";
				Pattern pattern = Pattern.compile(re);
				Matcher matcher = pattern.matcher(showName);
				Matcher matcher2 = pattern.matcher(us.getUserName());
				if (matcher.matches()&&showName.length()>7) {
					showName = showName.substring(0, 3) + "****" + showName.substring(7);
				}else if(matcher2.matches()&&(showName==null||showName.equals("null")||showName.length()==0)&&us.getUserName().length()>7){
					showName = us.getUserName().substring(0, 3) + "****" + us.getUserName().substring(7);
				}

				BoardMaster bm = (BoardMaster) board.getBoardMaster().get(showName);
				if (bm != null) {// 是斑竹
					Map[] bmpMap = boardService.getBoardMasterPermission(bm.getRoleID()); // 取得斑竹权限
					us.setBoardPermissionArray(bmpMap);
				}

				for (int i = 0; i < board.getParentIDs().size(); i++) {
					Board pboard = boardService.getBoardByID(((Long) (board.getParentIDs().get(i))).longValue());
					
					String showName1 = us.getNickName();
					String re1 = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";
					Pattern pattern1 = Pattern.compile(re1);
					Matcher matcher1 = pattern1.matcher(showName1);
					Matcher matcher3 = pattern1.matcher(us.getUserName());
					if (matcher1.matches()&&showName1.length()>7) {
						showName1 = showName1.substring(0, 3) + "****" + showName1.substring(7);
					}else if(matcher3.matches()&&(showName1==null||showName1.equals("null")||showName1.length()==0)&&us.getUserName().length()>7){
						showName1 = us.getUserName().substring(0, 3) + "****" + us.getUserName().substring(7);
					}
					
					BoardMaster pbm = (BoardMaster) pboard.getBoardMaster().get(showName1);
					if (pbm != null && pbm.getOverChildPurview() == 1) {
						Map[] bmpMap = boardService.getBoardMasterPermission(pbm.getRoleID()); // 取得斑竹权限
						us.setBoardPermissionArray(bmpMap);
					}
				}

				// userSessionCache.add(userCookie.getUserName(), us);
				ac.getSession().put(Constant.USER_SESSION_KEY, us);

				UserOnlineService userOnlineService = (UserOnlineService) wc.getBean("userOnlineService");

				UserOnline uo = userOnlineService.findUserOnlineByUserID(us.getId()); // 取得用户在线信息
				if (uo != null) {
					uo.setAtPlace(board.getBoardName());
					uo.setBoardID(bid);
					try {
						userOnlineService.saveUserOnline(uo);
					} catch (BbscsException ex) {
						logger.error(ex);
					}
				}

			}

			if (board.getNeedPasswd() == 1 && StringUtils.isNotBlank(board.getPasswd())) {// 版区需要密码访问
				if (!us.isHaveBoardSpecialPermission(Constant.SPERMISSION_INBOARD_NOT_NEEDPASSWD)) {
					// System.out.println("user boardpass:" +
					// us.getBoardPass());
					// if (StringUtils.isBlank(userCookie.getBoardPass()) ||
					// userCookie.equals("-")) {
					if (StringUtils.isBlank(us.getBoardPass())) {
						return "boardPasswd";
					} else if (!(bid + ":" + board.getPasswd()).equals(us.getBoardPass())) {
						// 版区需要密码，需要跳转
						((ActionSupport) action).addActionError(messageSource.getMessage("error.board.passwd", null, ac
								.getLocale()));
						return "boardPasswd";
					}
				}
			}

			if (board.getIsAuth() == 1) {// 版区需要授权访问
				
				String showName2 = us.getNickName();
				String re2 = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";
				Pattern pattern2 = Pattern.compile(re2);
				Matcher matcher2 = pattern2.matcher(showName2);
				Matcher matcher3 = pattern2.matcher(us.getUserName());
				if (matcher2.matches()&&showName2.length()>7) {
					showName2 = showName2.substring(0, 3) + "****" + showName2.substring(7);
				}else if(matcher3.matches()&&(showName2==null||showName2.equals("null")||showName2.length()==0)&&us.getUserName().length()>7){
					showName2 = us.getUserName().substring(0, 3) + "****" + us.getUserName().substring(7);
				}
				
				BoardMaster bm = (BoardMaster) board.getBoardMaster().get(showName2);
				if (bm == null && !us.isHaveBoardSpecialPermission(Constant.SPERMISSION_INBOARD_NOT_NEEDAUTH)) { // 不是斑竹，并且没有不需要授权就可以进入的权限
					// 版区需要授权
					BoardAuthUserService boardAuthUserService = (BoardAuthUserService) wc
							.getBean("boardAuthUserService");
					if (boardAuthUserService.findBoardAuthUserByBidUid(bid, us.getId()) == null) {
						// 不是授权用户,需要跳转
						String errorMsg = messageSource.getMessage("error.user.not.auth", null, ac.getLocale());
						if (ajax.equalsIgnoreCase("html")) {
							ac.getValueStack().set("interceptError", errorMsg);
							return "intercepthtml";
						} else if (ajax.equalsIgnoreCase("shtml")) {
							ac.getValueStack().set("interceptError", errorMsg);
							return "interceptshtml";
						} else {
							AjaxMessagesJson ajaxMessagesJson = (AjaxMessagesJson) wc.getBean("ajaxMessagesJson");
							ajaxMessagesJson.setMessage("E_BOARDID_AUTH", errorMsg);
							ac.getValueStack().set("ajaxMessagesJson", ajaxMessagesJson);
							return "ajaxjson";
						}
					}
				}
			}

			boolean havePermission = false;
			Permission permission = (Permission) us.getBoardPermission().get(actionName + "?action=*");
			if (permission != null) {
				havePermission = true;
			} else {
				permission = (Permission) us.getBoardPermission().get(actionName + "?action=" + saction);
				if (permission != null) {
					havePermission = true;
				} else {
					havePermission = false;
				}
			}
			if (havePermission) {
				if (action instanceof BoardAware) {
					((BoardAware) action).setUserCookie(userCookie);
					((BoardAware) action).setUserSession(us);
				}
				return invocation.invoke();
			} else {

				StringBuffer sb = new StringBuffer();
				sb.append(BBSCSUtil.getWebRealPath(request));
				sb.append(request.getContextPath());
				sb.append("/");
				sb.append(BBSCSUtil.getActionMappingURLWithoutPrefix(ac.getName()));
				UrlHelper.buildParametersString(map, sb, "&");

				String curl = sb.toString();
				logger.debug("curl:" + curl);


				if (ajax.equalsIgnoreCase("html")) {
					// ac.getValueStack().set("interceptError", errorMsg);
					// return "intercepthtml";
					String errorMsg = messageSource.getMessage("error.noPermission", null, ac.getLocale());
					ac.getValueStack().set("interceptError", errorMsg);
					ac.getValueStack().set("tourl", curl);
					ac.getValueStack().set("useAuthCode", sysConfig.isUseAuthCode());
					if (sysConfig.getUsePass() == 0) {
						return "intercepthtml";
					} else {
						ac.getValueStack().set("actionUrl", sysConfig.getPassUrl());
						return "intercepthtmlpass";
					}

				} else if (ajax.equalsIgnoreCase("shtml")) {
					String errorMsg = messageSource.getMessage("error.noPermission.ajax", null, ac.getLocale());
					ac.getValueStack().set("interceptError", errorMsg);
					return "interceptshtml";
				} else {
					String errorMsg = messageSource.getMessage("error.noPermission.ajax", null, ac.getLocale());
					AjaxMessagesJson ajaxMessagesJson = (AjaxMessagesJson) wc.getBean("ajaxMessagesJson");
					ajaxMessagesJson.setMessage("E_NO_Permission", errorMsg);
					ac.getValueStack().set("ajaxMessagesJson", ajaxMessagesJson);
					return "ajaxjson";
				}
			}

		}
	}

}
