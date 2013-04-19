package com.laoer.bbscs.service.web;

/**
 * <p>
 * Title: Tianyi BBS
 * </p>
 *
 * <p>
 * Description: BBSCS
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 *
 * <p>
 * Company: Laoer.com
 * </p>
 *
 * @author Gong Tianyi
 * @version 8.0
 */
public class Pages {

	int page = 1; // 页号

	long totalNum = -1; // 记录总数

	int perPageNum = 20; // 每页显示记录数

	int allPage = 1; // 总页数

	int cpage = 1; // 当前页

	int spage = 1; // 开始记录数

	String fileName = "";

	boolean useUrlRewrite = false;

	public Pages() {
	}

	public Pages(int page, long totalNum, int perPageNum) {
		this.page = page;
		this.totalNum = totalNum;
		this.perPageNum = perPageNum;
		this.executeCount();
	}

	public int getAllPage() {
		return allPage;
	}

	public int getCpage() {
		return cpage;
	}

	public int getPage() {
		return page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public int getSpage() {
		return spage;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public String getFileName() {

		return fileName;
	}

	public boolean isUseUrlRewrite() {
		return useUrlRewrite;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public void setCpage(int cpage) {
		this.cpage = cpage;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}

	public void setSpage(int spage) {
		this.spage = spage;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public void setFileName(String fileName) {

		this.fileName = fileName;
	}

	public void setUseUrlRewrite(boolean useUrlRewrite) {
		this.useUrlRewrite = useUrlRewrite;
	}

	public void executeCount() {
		this.allPage = (int) Math.ceil((this.totalNum + this.perPageNum - 1) / this.perPageNum);
		int intPage = this.page;
		if (intPage > this.allPage) { // pages == 0
			this.cpage = 1;
		} else {
			this.cpage = intPage;
		}
		this.spage = (this.cpage - 1) * this.perPageNum;
	}

}
