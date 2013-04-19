package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.Board;
import java.util.*;

/**
 * <p>Title: Tianyi BBS</p>
 *
 * <p>Description: BBSCS</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Laoer.com</p>
 *
 * @author Gong Tianyi
 * @version 7.0
 */
public interface BoardDAO {

  /**
   * 保存或更新Board对象
   * @param board Board
   * @return Board
   */
  public Board saveBoard(Board board);

  /**
   * 根据ID取得Board对象
   * @param id long
   * @return Board
   */
  public Board getBoardByID(long id);

  /**
   * 根据parentID取得Board对象列表
   * @param pid long
   * @return List
   */
  public List findBoardsByParentID(long pid);

  /**
   * 根据parentID取得Board对象列表
   * @param pid long
   * @param useStat int
   * @param hidden int
   * @param orderType int
   * @return List
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
  public List findBoardIdsByParentID(long pid, int useStat, int hidden, int orderType);

  /**
   * 取得所有Board对象
   * @return List
   */
  public List findBoardsAll();

  /**
   * 根据parentID预取得Board序列
   * @param pid long
   * @return int
   */
  public int getNextOrder(long pid);

  /**
   * 取得帖子总数（主帖或全部）
   * @param mainorall int
   * @param useStat int
   * @param hidden int
   * @return int
   */
  public int getPostSumNum(int mainorall, int useStat, int hidden);

  /**
   * 删除Board对象
   * @param board Board
   */
  public void removeBoard(Board board);

  /**
   * 根据指定ID取得Board列表
   * @param ids List
   * @param useStat int
   * @param hidden int
   * @param orderType int
   * @return List
   */
  public List findBoardsInIDs(List ids, int useStat, int hidden);

  /**
   *
   * @param useStat int
   * @param hidden int
   * @return List
   */
  public List findBoardsNeedCount(int useStat, int hidden);

  /**
   *
   * @param pid long
   * @param useStat int
   * @param hidden int
   * @return List
   */
  public List findBoardsByParentID(long pid, int useStat, int hidden);

  /**
   *
   * @param pid long
   * @return List
   */
  public List findBoardsIdByParentIDInUse(long pid);

}
