package com.laoer.bbscs.web.taglib;

import org.apache.log4j.Logger;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

import java.io.IOException;
import java.io.Writer;

import com.laoer.bbscs.comm.*;

public class PrefixLine extends Component {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PrefixLine.class);

	public PrefixLine(ValueStack stack) {
		super(stack);
	}

	private String showText;

	private String levelValue;

	public void setLevelValue(String levelValue) {
		this.levelValue = levelValue;
	}

	public void setShowText(String showText) {
		this.showText = showText;
	}

	public boolean start(Writer writer) {
        boolean result = super.start(writer);
        if (levelValue == null) {
        	levelValue = "top";
        }
        else if (altSyntax()) {
            if (levelValue.startsWith("%{") && levelValue.endsWith("}")) {
            	levelValue = levelValue.substring(2, levelValue.length() - 1);
            }
        }

        int level = 0;
        Object levelObj = this.getStack().findValue(levelValue);
        if (levelObj != null) {
        	level = ((Integer)levelObj).intValue();
        }

        if (showText == null) {
        	showText = "-";
        }

        try {
			writer.write(BBSCSUtil.getBoardPrefixLine(level,showText));
		} catch (IOException e) {
			logger.error(e);
		}
        return result;
	}

}
