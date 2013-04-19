package com.laoer.bbscs.dao;

import java.io.Serializable;
import java.util.List;

import com.laoer.bbscs.bean.Image;

public interface ImageDAO {
	List<Image> getBylotnoid(int lotnoid,int type);
	Serializable saveImage(Image image);
	void saveOrupdate(Image image);
	List<Image> getAllImage();

}
