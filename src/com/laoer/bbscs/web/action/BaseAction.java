package com.laoer.bbscs.web.action;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware,SessionAware{

	private String action = "index";

	private String ajax = "json";

	public static final String RESULT_AJAXJSON = "ajaxjson";

	public static final String RESULT_HTMLERROR = "htmlError";

	public static final String RESULT_ERROR = "error";

	public static final String RESULT_JSONSTRING = "jsonstring";
	
	public HttpServletRequest request; 
    public HttpServletResponse response;
	   
    private Map<Object,Object> mapsession;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAjax() {
		return ajax;
	}

	public void setAjax(String ajax) {
		this.ajax = ajax;
	}

	private int page = 1;

	private long total;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	protected String executeMethod(String method) throws Exception {
		Class[] c = null;
		Method m = this.getClass().getMethod(method, c);
		Object[] o = null;
		String result = (String) m.invoke(this, o);
		return result;
	}

	public int boolean2int(boolean value) {
		if (value) {
			return 1;
		} else {
			return 0;
		}
	}

	public boolean int2boolean(int value) {
		if (value == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	@Override
	public void setSession(Map arg0) {
		this.mapsession=arg0;
		
	}

	public Map<Object, Object> getMapsession() {
		return mapsession;
	}
	
}
