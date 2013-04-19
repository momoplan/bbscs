package com.laoer.bbscs.service;

import java.util.List;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.bean.Image;

public interface ImageService {
	List<Image> getBylotnoid(int lotnoid,int type);
	List<Board> getAll();
	void saveImage(Image image);
	List<Image> getAllImage();

}
