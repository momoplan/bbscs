package com.laoer.bbscs.dao.hibernate;

import org.springframework.orm.hibernate3.support.*;
import com.laoer.bbscs.dao.RoleDAO;
import com.laoer.bbscs.bean.Role;
import java.util.List;
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
public class RoleHibernateDAO
    extends HibernateDaoSupport implements RoleDAO {

  private static final String LOADS_ALL = "from Role order by id";

  private static final String LOADS_BY_TYPEID = "from Role where typeID = ? order by id";

  private static final String LOADS_IN_IDS = "from Role where id in (:ids)";

  public RoleHibernateDAO() {
    super();
  }

  /**
   * 保存或更新Role对象
   *
   * @param role Role
   * @return Role
   * @todo Implement this com.laoer.bbscs.dao.RoleDAO method
   */
  public Role saveRole(Role role) {
    this.getHibernateTemplate().saveOrUpdate(role);
    return role;
  }

  /**
   * 根据ID取得Role
   *
   * @param id int
   * @return Role
   * @todo Implement this com.laoer.bbscs.dao.RoleDAO method
   */
  public Role findRoleByID(int id) {
    return (Role)this.getHibernateTemplate().get(Role.class, new Integer(id));
  }

  /**
   * 取得所有Role列表
   *
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.RoleDAO method
   */
  public List findRolesAll() {
    return this.getHibernateTemplate().find(LOADS_ALL);
  }

  /**
   * 根据tpyeID取得Role列表
   *
   * @param typeID int
   * @return List
   * @todo Implement this com.laoer.bbscs.dao.RoleDAO method
   */
  public List findRolesByTypeID(int typeID) {
    return this.getHibernateTemplate().find(LOADS_BY_TYPEID, new Integer(typeID));
  }

  /**
   * 根据指定ID取得Role对象列表
   * @param ids List
   * @return List
   */
  public List findRolesInIDs(final List ids) {
    return getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session s) throws HibernateException, SQLException {
        Query query = s.createQuery(LOADS_IN_IDS);
        query.setParameterList("ids", ids);
        List list = query.list();
        return list;
      }
    });
  }

  /**
   * 删除Role对象
   *
   * @param role Role
   * @todo Implement this com.laoer.bbscs.dao.RoleDAO method
   */
  public void removeRole(Role role) {
    this.getHibernateTemplate().delete(role);
  }
}
