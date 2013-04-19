package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.*;
import com.laoer.bbscs.exception.*;
import java.util.*;

public interface BoardService {

	/**
	 * 保存或更新Board对象
	 *
	 * @param board
	 * @return
	 * @throws BbscsException
	 */
	public Board saveBoard(Board board) throws BbscsException;

	/**
	 *
	 * @param board
	 * @return
	 * @throws BbscsException
	 */
	public Board createBoard(Board board) throws BbscsException;

	/**
	 *
	 * @param board
	 * @param oldParentID
	 * @return
	 * @throws BbscsException
	 */
	public Board updateBoard(Board board, long oldParentID) throws BbscsException;

	/**
	 *
	 * @param id
	 * @return
	 */
	public Board getBoardByID(long id);

	/**
	 *
	 * @param pid
	 * @param useStat
	 * @param hidden
	 * @param orderType
	 * @return
	 */
	public List findBoardsByParentID(long pid, int useStat, int hidden, int orderType);

	/**
	 *
	 * @param pid
	 * @param useStat
	 * @param hidden
	 * @param orderType
	 * @return
	 */
	//public List findBoardIdsByParentID(long pid, int useStat, int hidden, int orderType);

	/**
	 *
	 * @param pid
	 * @param topList
	 * @param useStat
	 * @param hidden
	 * @param orderType
	 * @return
	 */
	public List findBoardsAllTree(long pid, List topList, int useStat, int hidden, int orderType);

	/**
	 *
	 * @param pid
	 * @return
	 */
	public int getNextOrder(long pid);

	/**
	 *
	 * @param mainorall
	 * @param useStat
	 * @param hidden
	 * @return
	 */
	public int getPostSumNum(int mainorall, int useStat, int hidden);

	/**
	 *
	 * @param board
	 * @throws BbscsException
	 */
	public void removeBoard(Board board) throws BbscsException;

	/**
	 *
	 * @param bid
	 * @param groupID
	 * @return
	 */
	public Map[] getBoardPermission(long bid, int groupID);

	/**
	 *
	 * @param roleID
	 * @return
	 */
	public Map[] getBoardMasterPermission(int roleID);

	/**
	 *
	 * @param board
	 * @param userName
	 * @return
	 */
	public boolean isBoardMaster(Board board, String userName);

	/**
	 *
	 * @param ids
	 * @return
	 */
	public List findBoardsInIDs(List ids);

	/**
	 *
	 * @param board
	 * @param tagID
	 * @throws BbscsException
	 */
	public void removeBoardTag(Board board, String tagID) throws BbscsException;

	/**
	 *
	 * @param boards
	 * @return
	 */
	public List getBoardIDs(List boards);

	public void saveBoardsPostNumCount() throws BbscsException;

}
