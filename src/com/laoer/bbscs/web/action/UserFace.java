package com.laoer.bbscs.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ajax.AjaxMessagesJson;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.*;

public class UserFace extends BaseMainAction {

	/**
	 *
	 */
	private static final long serialVersionUID = -1074585999647232134L;

	private String uploadContentType;

	private File upload;

	private String uploadFileName;

	private String ajaxCodeid;

	private String ajaxMsg;

	private int useFace;

	private String userId;

	private AjaxMessagesJson ajaxMessagesJson;

	private UserService userService;

	private SysConfig sysConfig;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getAjaxCodeid() {
		return ajaxCodeid;
	}

	public void setAjaxCodeid(String ajaxCodeid) {
		this.ajaxCodeid = ajaxCodeid;
	}

	public String getAjaxMsg() {
		return ajaxMsg;
	}

	public void setAjaxMsg(String ajaxMsg) {
		this.ajaxMsg = ajaxMsg;
	}

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public int getUseFace() {
		return useFace;
	}

	public void setUseFace(int useFace) {
		this.useFace = useFace;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String index() {
		this.setUseFace(this.getSysConfig().getUseFace());
		return SUCCESS;
	}

	public String showface() {
		this.setUserId(this.getUserSession().getId());
		return "showface";
	}

	public String uppage() {
		this.setAction("up");
		return "userFaceUpPage";
	}

	public String up() {
		// System.out.println(this.getUpload());
		// System.out.println(this.getUploadFileName());
		if (this.getUpload() == null || StringUtils.isBlank(this.getUploadFileName())) {
			this.setAjaxCodeid("1");
			this.setAjaxMsg(this.getText("error.userupimg.null"));
			return "userFaceUpComponent";
		}
		if (!BBSCSUtil.isAllowPicFile(this.getUploadFileName()) || this.getUpload().length() == 0
				|| this.getUpload().length() > this.getSysConfig().getFaceSize() * 1024) {
			this.setAjaxCodeid("2");
			this.setAjaxMsg(this.getText("error.userupimg.upnotice", new String[] { String.valueOf(this.getSysConfig()
					.getFaceSize()) }));
			return "userFaceUpComponent";
		}
		UserInfo ui = this.getUserService().findUserInfoById(this.getUserSession().getId());
		if (ui == null) {
			this.setAjaxCodeid("3");
			this.setAjaxMsg(this.getText("error.userupimg.uperror"));
		}
		String distFileName = ui.getId() + System.currentTimeMillis() + "."
				+ FilenameUtils.getExtension(this.getUploadFileName());
		try {
			this.getUserService().createUserFacePic(ui, distFileName, new FileInputStream(this.getUpload()));
			this.setAjaxCodeid("0");
			this.setAjaxMsg(this.getText("userupimg.up.ok"));
		} catch (FileNotFoundException ex) {
			this.setAjaxCodeid("3");
			this.setAjaxMsg(this.getText("error.userupimg.uperror"));
		} catch (BbscsException ex) {
			this.setAjaxCodeid("4");
			this.setAjaxMsg(this.getText("error.userupimg.uperror"));
		}
		return "userFaceUpComponent";
	}

	public String delme() {
		UserInfo ui = this.getUserService().findUserInfoById(this.getUserSession().getId());
		if (ui == null) {
			this.getAjaxMessagesJson().setMessage("E_USER_NOEXIST", this.getText("error.user.noexist"));
			return RESULT_AJAXJSON;
		}
		try {
			this.getUserService().removeUserFacePic(ui);
			this.getAjaxMessagesJson().setMessage("0", this.getText("userimg.del.ok"));
		} catch (BbscsException ex1) {

			this.getAjaxMessagesJson().setMessage("E_USERIMG_DELFAILED", this.getText("error.usrimg.del.error"));
		}
		return RESULT_AJAXJSON;
	}

}
