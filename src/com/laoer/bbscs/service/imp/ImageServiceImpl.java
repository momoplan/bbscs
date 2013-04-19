package com.laoer.bbscs.service.imp;

import java.util.List;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.bean.Image;
import com.laoer.bbscs.dao.BoardDAO;
import com.laoer.bbscs.dao.ImageDAO;
import com.laoer.bbscs.service.ImageService;

public class ImageServiceImpl implements ImageService {
    
	private ImageDAO imageDao;
	private BoardDAO boardDAO;
	
   
	public void setImageDao(ImageDAO imageDao) {
		this.imageDao = imageDao;
	}



	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	
	@Override
	public List<Image> getBylotnoid(int lotnoid, int type) {
		return imageDao.getBylotnoid(lotnoid,type);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Board> getAll() {
		return boardDAO.findBoardsAll();
	}
    
	@Override
	public void saveImage(Image image) {
		imageDao.saveImage(image);
	}



	@Override
	public List<Image> getAllImage() {
		// TODO Auto-generated method stub
		return imageDao.getAllImage();
	}

}
