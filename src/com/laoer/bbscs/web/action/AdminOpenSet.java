package com.laoer.bbscs.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

import com.laoer.bbscs.exception.SysConfigException;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ui.RadioInt;

public class AdminOpenSet extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminOpenSet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -4632706206328833374L;

	public AdminOpenSet() {
		this.setRadioYesNoListValues();
	}

	private SysConfig sysConfig;

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	private String closeReson;

	private int isOpen;

	public String getCloseReson() {
		return closeReson;
	}

	public void setCloseReson(String closeReson) {
		this.closeReson = closeReson;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	List<RadioInt> radioYesNoList = new ArrayList<RadioInt>();

	private void setRadioYesNoListValues() {
		radioYesNoList.add(new RadioInt(0, this.getText("bbscs.no")));
		radioYesNoList.add(new RadioInt(1, this.getText("bbscs.yes")));
	}

	public List<RadioInt> getRadioYesNoList() {
		return radioYesNoList;
	}

	public void setRadioYesNoList(List<RadioInt> radioYesNoList) {
		this.radioYesNoList = radioYesNoList;
	}

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			logger.error(e);
			return INPUT;
		}

	}

	public String index() {
		if (logger.isDebugEnabled()) {
			logger.debug("index() - start"); //$NON-NLS-1$
		}

		this.setAction("save");
		this.setIsOpen(this.getSysConfig().getIsOpen());
		this.setCloseReson(this.getSysConfig().getCloseReson());

		if (logger.isDebugEnabled()) {
			logger.debug("index() - end"); //$NON-NLS-1$
		}
		return INPUT;
	}

	public String save() {
		if (logger.isDebugEnabled()) {
			logger.debug("save() - start"); //$NON-NLS-1$
		}
		this.setAction("save");
		this.getSysConfig().setIsOpen(this.getIsOpen());
		this.getSysConfig().setCloseReson(this.getCloseReson());
		try {
			this.getSysConfig().saveConfigs();
			this.addActionMessage(this.getText("bbscs.dataupdate.succeed"));

			if (logger.isDebugEnabled()) {
				logger.debug("save() - end"); //$NON-NLS-1$
			}
			return INPUT;
		} catch (SysConfigException e) {
			logger.error("save()", e); //$NON-NLS-1$
			this.addActionError(this.getText("error.dataupdate.failed"));

			if (logger.isDebugEnabled()) {
				logger.debug("save() - end"); //$NON-NLS-1$
			}
			return INPUT;
		}
	}

}
