package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.PermissionDAO;
import java.util.List;
import com.laoer.bbscs.bean.Permission;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.sql.SQLException;

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
public class PermissionHibernateDAO
    extends HibernateDaoSupport implements PermissionDAO {

  private static final String LOADS_ALL = "from Permission order by id";

  private static final String LOADS_BY_TYPEID = "from Permission where typeID = ? order by id";

  private static final String LOADS_IN_IDS = "from Permission where id in (:ids)";

  public PermissionHibernateDAO() {
    super();
  }

  /**
   *
   * @param permission Permission
   * @return Permission
   */
  public Permission savePermission(Permission permission) {
    this.getHibernateTemplate().save(permission);
    return permission;
  }

  /**
   *
   * @param permission Permission
   * @return Permission
   */
  public Permission updatePermission(Permission permission) {
    this.getHibernateTemplate().update(permission);
    return permission;
  }

  /**
   *
   * @param id int
   * @return Permission
   */
  public Permission findPermissionByID(long id) {
    return (Permission)this.getHibernateTemplate().get(Permission.class, new Long(id));
  }

  /**
   * 取得所有Permission列表
   *
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.PermissionDAO method
   */
  public List findPermissionsAll() {
    return this.getHibernateTemplate().find(LOADS_ALL);
  }

  /**
   * 按照typeID取得Permission列表
   *
   * @param typeID int
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.PermissionDAO method
   */
  public List findPermissionsByTypeID(int typeID) {
    return this.getHibernateTemplate().find(LOADS_BY_TYPEID, new Integer(typeID));
  }

  /**
   *
   * @param ids List
   * @return List
   */
  public List findPermissionnIDs(final List ids) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(LOADS_IN_IDS);
        query.setParameterList("ids", ids);
        List list = query.list();
        return list;
      }
    });
  }

}
