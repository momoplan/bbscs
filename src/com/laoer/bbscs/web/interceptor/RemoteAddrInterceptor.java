package com.laoer.bbscs.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.ActionContext;

import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;


public class RemoteAddrInterceptor extends AbstractInterceptor {

	/**
	 *
	 */
	private static final long serialVersionUID = -2185920708747626659L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();
		Object action = invocation.getAction();

		HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
		String userRemoteAddr = request.getRemoteAddr();

		if (action instanceof RemoteAddrAware) {

			((RemoteAddrAware)action).setRemoteAddr(userRemoteAddr);
			//System.out.println(userRemoteAddr);
		}

		return invocation.invoke();
	}

}
