package com.laoer.bbscs.web.action;

import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.service.SysNumStatService;
import com.laoer.bbscs.service.web.PageList;
import com.laoer.bbscs.service.web.Pages;

public class SysNumStatAction extends BaseMainAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 9071184421645324997L;

	private PageList pageList;

	private SysNumStatService sysNumStatService;

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	public SysNumStatService getSysNumStatService() {
		return sysNumStatService;
	}

	public void setSysNumStatService(SysNumStatService sysNumStatService) {
		this.sysNumStatService = sysNumStatService;
	}

	public String index() {
		Pages pages = new Pages();
		pages.setPage(this.getPage());
		pages.setPerPageNum(30);

		pages.setFileName(BBSCSUtil.getActionMappingURLWithoutPrefix("sysNumStat?action=" + this.getAction()));
		this.setPageList(this.getSysNumStatService().findSysNumStats(pages));
		return SUCCESS;
	}

}
