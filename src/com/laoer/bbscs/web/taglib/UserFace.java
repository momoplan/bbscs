package com.laoer.bbscs.web.taglib;

import java.io.IOException;
import java.io.Writer;

import org.apache.struts2.components.Component;
import javax.servlet.jsp.*;

import com.laoer.bbscs.bean.UserInfoSimple;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.service.UserService;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.commons.lang.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class UserFace extends Component {

	private PageContext pageContext;

	private String facePicName = "images/defaultFace.jpg";

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public UserFace(ValueStack stack, PageContext pageContext) {
		super(stack);
		this.pageContext = pageContext;
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
		String userId = "";
		Object idObj = this.getStack().findValue(value);
		if (idObj != null) {
			userId = (String) idObj;
		}
		StringBuffer sb = new StringBuffer();

		if (StringUtils.isBlank(userId)) {
			sb.append("<img src=\"");
			sb.append(facePicName);
			sb.append("\" alt=\"Face\" />");
			try {
				writer.write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		} else {
			if (userId.startsWith(Constant.GUEST_USERID)) { // 游客
				sb.append("<img src=\"");
				sb.append(facePicName);
				sb.append("\" alt=\"Face\" />");

			} else { // 正常用户
				WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(this.pageContext
						.getServletContext());

				UserService us = (UserService) wc.getBean("userService");
				UserInfoSimple uis = us.getUserInfoSimple(userId);

				if (uis.getHavePic() == 1 && !uis.getPicFileName().equals("-")) {
					sb.append("<a href=\"");
					sb.append(BBSCSUtil.getUserWebPath(uis.getId()));
					sb.append(uis.getPicFileName());
					sb.append("\" target=\"_blank\">");
					sb.append("<img src=\"");
					sb.append(BBSCSUtil.getUserWebPath(uis.getId()));
					sb.append(uis.getPicFileName());
					sb.append(Constant.IMG_SMALL_FILEPREFIX);
					sb.append("\" alt=\"Face\" border=\"0\" class=\"pic1\"/>");
					sb.append("</a>");
				} else {
					sb.append("<img src=\"");
					sb.append(facePicName);
					sb.append("\" alt=\"Face\" />");
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
