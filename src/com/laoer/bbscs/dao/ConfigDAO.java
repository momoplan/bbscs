package com.laoer.bbscs.dao;

import com.laoer.bbscs.bean.Config;
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
public interface ConfigDAO {

  /**
   *
   * @param config Config
   * @return Config
   */
  //public Config saveConfig(Config config);

  /**
   * 更新Config对象
   * @param config Config
   * @return Config
   */
  public Config updateConfig(Config config);

  /**
   * 根据ID查找Config对象
   * @param id String
   * @return Config
   */
  public Config findConfigByID(String id);

  /**
   * 取得Config列表
   * @return List
   */
  public List findConfigs();

}
