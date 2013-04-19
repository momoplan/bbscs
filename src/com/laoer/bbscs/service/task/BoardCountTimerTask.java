package com.laoer.bbscs.service.task;

import java.util.*;

import org.apache.commons.logging.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;

/**
 * <p>Title: TianyiBBS</p>
 *
 * <p>Description: BBSCS</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Laoer.com</p>
 *
 * @author Laoer
 * @version 7.0
 */
public class BoardCountTimerTask
    extends TimerTask {

  private static final Log logger = LogFactory.getLog(BoardCountTimerTask.class);

  private BoardService boardService;

  private CommendService commendService;


  public BoardCountTimerTask() {
  }

  /**
   * When an object implementing interface <code>Runnable</code> is used to create a thread, starting the
   * thread causes the object's <code>run</code> method to be called in that separately executing thread.
   *
   * @todo Implement this java.lang.Runnable method
   */
  public void run() {
    try {
      logger.info("Board count start...");
      this.getBoardService().saveBoardsPostNumCount();
      this.getCommendService().createCommendTopFile(10);
      logger.info("Board count end");
    }
    catch (BbscsException ex) {
      logger.error(ex);
    }
  }

  public BoardService getBoardService() {
    return boardService;
  }

  public CommendService getCommendService() {
    return commendService;
  }

  public void setBoardService(BoardService boardService) {
    this.boardService = boardService;
  }

  public void setCommendService(CommendService commendService) {
    this.commendService = commendService;
  }
}
