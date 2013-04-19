package com.laoer.bbscs.web.interceptor;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.laoer.bbscs.service.config.SysConfig;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminSysConfigLoadInterceptor extends AbstractInterceptor {

	/**
	 *
	 */
	private static final long serialVersionUID = -7226650966015164674L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();
		ServletContext servletContext = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		SysConfig sysConfig = (SysConfig) wc.getBean("sysConfig");

		if (sysConfig.isIsLoad()) {
			sysConfig.loadConfigs();
		}
		return invocation.invoke();
	}

}
