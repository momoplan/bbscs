package com.laoer.bbscs.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import com.laoer.bbscs.bean.Board;
import com.laoer.bbscs.bean.Image;
import com.laoer.bbscs.comm.BBSCSUtil;
import com.laoer.bbscs.service.ImageService;

public class ImageAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private ImageService imageService;
    private Image image;
    public Image getImage() {
		return image;
	}
	private File file;
    private String fileContentType;
    public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	private String fileFileName;
	public void setFile(File file) {
		this.file = file;
	}

	public void setImage(Image image) {
		this.image = image;
	}
    


	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}
   
	public String getAll(){
	    List<Board> list=imageService.getAll();
	    if(!list.isEmpty()){
	    	getMapsession().put("adboardList", list);
	    }
		return SUCCESS;
	}
	public String upload(){
	long sysid=System.nanoTime();
	String filepath=BBSCSUtil.getUpFilePath(image.getLotnoid(),sysid);
	if(file!=null){
         String name=(new Date()).getTime()+( new Random().nextInt(1000))+fileFileName.substring(fileFileName.lastIndexOf("."));
		 File savefile = new File(new File(filepath), name);  
		 if (!savefile.getParentFile().exists())               
		 savefile.getParentFile().mkdirs();            
		 try {
			 image.setImageurl(BBSCSUtil.getUpFileWebPath(image.getLotnoid(), sysid)+name);
			 image.setType(1);
			 image.setInserttime(new Date());
			 imageService.saveImage(image);
			 FileUtils.copyFile(file, savefile);
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		} 
	}else{
		return ERROR;
	}
	return SUCCESS;
	}
	public String getAllImage(){
		List<Image> imageList=imageService.getAllImage();
		getMapsession().put("imageList", imageList);
		return SUCCESS;
	}
}
