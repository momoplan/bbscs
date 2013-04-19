package com.laoer.bbscs.fio;

import java.io.*;

/**
 * <p>Title: TianyiBBS</p>
 *
 * <p>Description: BBSCS</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Laoer.com</p>
 *
 * @author Laoer
 * @version 7.0
 */
public class UploadFile {

  private String fileName;
  private InputStream inputStream;

  public UploadFile() {
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public String getFileName() {
    return fileName;
  }

  public InputStream getInputStream() {
    return inputStream;
  }
}
