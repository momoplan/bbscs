package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.ConfigDAO;
import com.laoer.bbscs.bean.Config;
import java.util.List;

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
public class ConfigHibernateDAO
    extends HibernateDaoSupport implements ConfigDAO {

  private static final String LOAD_ALL = "from Config";

  public ConfigHibernateDAO() {
    super();
  }

  /**
   * 更新Config对象
   *
   * @param config Config
   * @return Config
   * @todo Implement this com.laoer.bbscs.dao.ConfigDAO method
   */
  public Config updateConfig(Config config) {
    this.getHibernateTemplate().update(config);
    return config;
  }

  /**
   * 根据ID查找Config对象
   *
   * @param id String
   * @return Config
   * @todo Implement this com.laoer.bbscs.dao.ConfigDAO method
   */
  public Config findConfigByID(String id) {
    return (Config)this.getHibernateTemplate().get(Config.class, id);
  }

  /**
   * 取得Config列表
   *
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.ConfigDAO method
   */
  public List findConfigs() {
    return this.getHibernateTemplate().find(LOAD_ALL);
  }

}
