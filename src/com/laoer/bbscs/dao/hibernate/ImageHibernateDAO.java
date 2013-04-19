package com.laoer.bbscs.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laoer.bbscs.bean.Image;
import com.laoer.bbscs.dao.ImageDAO;

public class ImageHibernateDAO  extends HibernateDaoSupport  implements ImageDAO {
     
	
	/**
	 * 根据板块选择广告图片
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<Image> getBylotnoid(int lotnoid,int type) {
		String queryString="select i from Image i where i.lotnoid=? and i.type=?";
		return getHibernateTemplate().find(queryString,  new Object[]{lotnoid,type});
	}

	@Override
	public Serializable saveImage(Image image) {
		return getHibernateTemplate().save(image);
	}

	@Override
	public void saveOrupdate(Image image) {
		getHibernateTemplate().saveOrUpdate(image);
	}
 //获得所有的图片
	@SuppressWarnings("unchecked")
	@Override
	public List<Image> getAllImage() {
		return getHibernateTemplate().find("select i from Image i order by i.inserttime desc");
	}
	

}
