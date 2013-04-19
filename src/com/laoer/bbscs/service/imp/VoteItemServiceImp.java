package com.laoer.bbscs.service.imp;

import java.util.*;

import org.apache.commons.logging.*;
import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;

/**
 * <p>
 * Title: TianyiBBS
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
 * @author Laoer
 * @version 7.0
 */
public class VoteItemServiceImp implements VoteItemService {

	private static final Log logger = LogFactory.getLog(VoteItemServiceImp.class);

	private VoteItemDAO voteItemDAO;

	private VoteUserDAO voteUserDAO;

	public VoteItemServiceImp() {
	}

	/**
	 *
	 * @param voteItem
	 *            VoteItem
	 * @return VoteItem
	 * @throws BbscsException
	 * @todo Implement this com.laoer.bbscs.service.VoteItemService method
	 */
	public VoteItem saveVoteItem(VoteItem voteItem) throws BbscsException {
		try {
			return this.getVoteItemDAO().saveVoteItem(voteItem);
		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	/**
	 *
	 * @param id
	 *            String
	 * @return VoteItem
	 * @todo Implement this com.laoer.bbscs.service.VoteItemService method
	 */
	public VoteItem findVoteItemByID(String id) {
		return this.getVoteItemDAO().findVoteItemByID(id);
	}

	/**
	 *
	 * @param voteID
	 *            String
	 * @return List
	 * @todo Implement this com.laoer.bbscs.service.VoteItemService method
	 */
	public List findVoteItemsByVoteID(String voteID) {
		return this.getVoteItemDAO().findVoteItemsByVoteID(voteID);
	}

	public void createVoteItemAdd(String userId, String voteId, List ids) throws BbscsException {
		List vis = this.getVoteItemDAO().findVoteItemsInIds(ids);
		try {
			for (int i = 0; i < vis.size(); i++) {
				VoteItem vi = (VoteItem) vis.get(i);
				vi.setItemValue(vi.getItemValue() + 1);
				this.getVoteItemDAO().saveVoteItem(vi);
			}
			VoteUser vu = new VoteUser();
			vu.setVoteID(voteId);
			vu.setVoteTime(System.currentTimeMillis());
			vu.setVoteUserID(userId);
			this.getVoteUserDAO().saveVoteUser(vu);

		} catch (Exception ex) {
			logger.error(ex);
			throw new BbscsException(ex);
		}
	}

	public VoteItemDAO getVoteItemDAO() {
		return voteItemDAO;
	}

	public VoteUserDAO getVoteUserDAO() {
		return voteUserDAO;
	}

	public void setVoteItemDAO(VoteItemDAO voteItemDAO) {
		this.voteItemDAO = voteItemDAO;
	}

	public void setVoteUserDAO(VoteUserDAO voteUserDAO) {
		this.voteUserDAO = voteUserDAO;
	}
}
