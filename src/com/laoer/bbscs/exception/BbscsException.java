package com.laoer.bbscs.exception;

public class BbscsException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 6596385261286232441L;

	public BbscsException() {
		super();
	}

	public BbscsException(String message) {
		super(message);
	}

	public BbscsException(String message, Throwable cause) {
		super(message, cause);
	}

	public BbscsException(Throwable cause) {
		super(cause);
	}

}
