package com.laoer.bbscs.service;

import com.laoer.bbscs.bean.Config;
import com.laoer.bbscs.exception.BbscsException;
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
public interface ConfigService {

  /**
   *
   * @param config Config
   * @return Config
   * @throws BbscsException
   */
  public Config updateConfig(Config config) throws BbscsException;

  /**
   *
   * @param id String
   * @return Config
   */
  public Config findConfigByID(String id);

  /**
   *
   * @return List
   */
  public List findConfigs();

  /**
   *
   * @param configs HashMap
   * @throws BbscsException
   */
  public void updateAllConfigs(HashMap configs) throws BbscsException;

}
