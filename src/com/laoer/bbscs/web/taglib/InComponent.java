package com.laoer.bbscs.web.taglib;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.components.Component;

import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.opensymphony.xwork2.util.ValueStack;

public class InComponent extends Component {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(InComponent.class);

	public InComponent(ValueStack arg0) {
		super(arg0);
		// TODO 自动生成构造函数存根
	}

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		if (this.getType().equalsIgnoreCase("commend")) {
			File commendFile = new File(BBSCSUtil.getIncludePath() + "ForumCover_Commend_0.html");
			String commendlist = "";
			try {
				commendlist = FileUtils.readFileToString(commendFile, Constant.CHARSET);
				if (commendlist == null) {
					commendlist = "";
				}
			} catch (IOException ex) {
				logger.error(ex);
				commendlist = "";
			}

			this.write(writer, commendlist);
			return result;
		}

		if (this.getType().equalsIgnoreCase("userexp")) {
			File commendFile = new File(BBSCSUtil.getIncludePath() + "UserExp.html");
			String commendlist = "";
			try {
				commendlist = FileUtils.readFileToString(commendFile, Constant.CHARSET);
				if (commendlist == null) {
					commendlist = "";
				}
			} catch (IOException ex) {
				logger.error(ex);
				commendlist = "";
			}
			this.write(writer, commendlist);
			return result;
		}

		if (this.getType().equalsIgnoreCase("userlit")) {
			File commendFile = new File(BBSCSUtil.getIncludePath() + "UserLit.html");
			String commendlist = "";
			try {
				commendlist = FileUtils.readFileToString(commendFile, Constant.CHARSET);
				if (commendlist == null) {
					commendlist = "";
				}
			} catch (IOException ex) {
				logger.error(ex);
				commendlist = "";
			}
			this.write(writer, commendlist);
			return result;
		}

		if (this.getType().equalsIgnoreCase("userknow")) {
			File commendFile = new File(BBSCSUtil.getIncludePath() + "UserKnow.html");
			String commendlist = "";
			try {
				commendlist = FileUtils.readFileToString(commendFile, Constant.CHARSET);
				if (commendlist == null) {
					commendlist = "";
				}
			} catch (IOException ex) {
				logger.error(ex);
				commendlist = "";
			}
			this.write(writer, commendlist);
			return result;
		}

		return result;
	}

	private void write(Writer writer, String txt) {
		try {
			writer.write(txt);
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

}
