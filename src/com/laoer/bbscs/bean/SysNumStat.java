package com.laoer.bbscs.bean;

import java.io.*;

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
 * @version 7.0
 */
public class SysNumStat implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7236218887732624715L;

	private String id;

	private String recDate;

	private long num0;

	private long numInc0;

	private long num1;

	private long numInc1;

	private long num2;

	private long numInc2;

	private long num3;

	private long numInc3;

	private long num4;

	private long numInc4;

	private long num5;

	private long numInc5;

	private long createTime;

	public SysNumStat() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRecDate(String recDate) {
		this.recDate = recDate;
	}

	public void setNum0(long num0) {
		this.num0 = num0;
	}

	public void setNumInc0(long numInc0) {
		this.numInc0 = numInc0;
	}

	public void setNum1(long num1) {
		this.num1 = num1;
	}

	public void setNumInc1(long numInc1) {
		this.numInc1 = numInc1;
	}

	public void setNum2(long num2) {
		this.num2 = num2;
	}

	public void setNumInc2(long numInc2) {
		this.numInc2 = numInc2;
	}

	public void setNum3(long num3) {
		this.num3 = num3;
	}

	public void setNumInc3(long numInc3) {
		this.numInc3 = numInc3;
	}

	public void setNum4(long num4) {
		this.num4 = num4;
	}

	public void setNumInc4(long numInc4) {
		this.numInc4 = numInc4;
	}

	public void setNum5(long num5) {
		this.num5 = num5;
	}

	public void setNumInc5(long numInc5) {
		this.numInc5 = numInc5;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public String getRecDate() {
		return recDate;
	}

	public long getNum0() {
		return num0;
	}

	public long getNumInc0() {
		return numInc0;
	}

	public long getNum1() {
		return num1;
	}

	public long getNumInc1() {
		return numInc1;
	}

	public long getNum2() {
		return num2;
	}

	public long getNumInc2() {
		return numInc2;
	}

	public long getNum3() {
		return num3;
	}

	public long getNumInc3() {
		return numInc3;
	}

	public long getNum4() {
		return num4;
	}

	public long getNumInc4() {
		return numInc4;
	}

	public long getNum5() {
		return num5;
	}

	public long getNumInc5() {
		return numInc5;
	}

	public long getCreateTime() {
		return createTime;
	}
}
