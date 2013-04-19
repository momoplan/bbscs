package com.laoer.bbscs.comm;

import java.io.Serializable;
import java.util.Vector;

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
public class SysQueue implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4726616410644554476L;

	private Vector aVector;

	public SysQueue() {
		aVector = new Vector();
	}

	public SysQueue(Vector aVector) {
		this.aVector = aVector;
	}

	/**
	 * Add a object into Queue
	 *
	 * @param o
	 *            Object
	 */
	@SuppressWarnings("unchecked")
	public synchronized void add(Object o) {
		aVector.add(o);
	}

	/**
	 * Get Object from Queue
	 *
	 * @return Object
	 */
	public synchronized Object get() {
		if (isEmpty()) {
			return null;
		}
		Object o = aVector.elementAt(0);
		aVector.removeElementAt(0);
		return o;
	}

	public boolean isEmpty() {
		return aVector.isEmpty();
	}

	public synchronized void clear() {
		aVector.removeAllElements();
	}

	public int search(Object o) {
		return aVector.indexOf(o);
	}

	public synchronized int size() {
		return aVector.size();
	}

}
