package com.laoer.bbscs.web.taglib;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.components.Component;

import com.laoer.bbscs.comm.BBSCSUtil;
import com.opensymphony.xwork2.util.ValueStack;

public class BoardMaster extends Component {

	private HttpServletRequest request;

	private String value;

	public BoardMaster(ValueStack arg0, HttpServletRequest request) {
		super(arg0);
		this.request = request;
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

		Map boardMasterMap = (Map) this.getStack().findValue(value);

		if (boardMasterMap != null) {
			StringBuffer sb = new StringBuffer();
			// Set bmSet = boardMasterMap.entrySet();
			Iterator bmit = boardMasterMap.values().iterator();
			while (bmit.hasNext()) {
				com.laoer.bbscs.bean.BoardMaster bm = (com.laoer.bbscs.bean.BoardMaster) bmit.next();
				if (bm.getIsHidden() == 0) {
					/*sb.append("<a href=\"");
					sb.append(BBSCSUtil.getActionMappingURL("/userInfo?action=name&username=" + bm.getUserName(),
							request));
					sb.append("\">");
					sb.append(bm.getUserName());
					sb.append("</a> ");*/
					sb.append(bm.getUserName());
				}
			}
			try {
				writer.write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
