package com.laoer.bbscs.web.taglib;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.Writer;
import org.apache.struts2.components.Component;
import com.opensymphony.xwork2.util.ValueStack;
import java.util.*;
import com.laoer.bbscs.comm.*;

public class DateTimes extends Component {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(DateTimes.class);

	public DateTimes(ValueStack arg0) {
		super(arg0);
		// TODO 自动生成构造函数存根
	}

	private String value;

	private String format = "yyyy-MM-dd HH:mm:ss";

	private String datetype = "timestamp";

	public String getDatetype() {
		return datetype;
	}

	public void setDatetype(String datetype) {
		this.datetype = datetype;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean start(Writer writer) {
		boolean result = super.start(writer);

		if (value == null) {
			value = "top";
		} else if (altSyntax()) {
			if (value.startsWith("%{") && value.endsWith("}")) {
				value = value.substring(2, value.length() - 1);
			}
		}

		Date date = null;

		if (this.datetype.equalsIgnoreCase("timestamp")) {
			long atime = (Long) this.getStack().findValue(value);
			date = new Date(atime);
		} else if (this.datetype.equalsIgnoreCase("date")) {
			date = (Date) this.getStack().findValue(value);
		} else {
			date = new Date();
		}
		String stime = Util.formatDate(date, format);
		try {
			writer.write(stime);
		} catch (IOException e) {
			logger.error(e);
		}

		return result;
	}

}
