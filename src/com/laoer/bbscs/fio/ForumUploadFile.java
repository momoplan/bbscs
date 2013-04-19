package com.laoer.bbscs.fio;

import com.laoer.bbscs.bean.Forum;
import java.io.IOException;
import java.util.List;

import com.laoer.bbscs.service.config.SysConfig;

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
public interface ForumUploadFile {

  /**
   *
   * @param toFileName String
   * @param uploadFile UploadFile
   * @param sysConfig SysConfig
   * @throws IOException
   */
  public void saveUploadFile(String toFileName, UploadFile uploadFile, SysConfig sysConfig) throws
      IOException;

  /**
   *
   * @param fileName String
   * @throws IOException
   */
  public void delUploadFile(Forum f) throws IOException;

  /**
   *
   * @param f Forum
   * @param fileName String
   * @throws IOException
   */
  public List delUploadFile(Forum f, List fileNames) throws IOException;

  /**
   *
   * @param fileList List
   * @param frombid long
   * @param tobid long
   * @throws IOException
   */
  public void moveUploadFile(Forum forum, long tobid) throws IOException;

  public void delDetailFile(Forum forum) throws IOException;

}
