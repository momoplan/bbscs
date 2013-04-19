package com.laoer.bbscs.fio.imp;

import com.laoer.bbscs.fio.CommendFileIO;
import java.util.List;
import java.io.IOException;
import com.laoer.bbscs.bean.Commend;
import com.laoer.bbscs.comm.BBSCSUtil;
import java.io.File;
import org.apache.commons.io.FileUtils;
import com.laoer.bbscs.comm.Constant;
import com.opensymphony.xwork2.util.TextUtils;

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
public class CommendFileIOImp
    implements CommendFileIO {

  public CommendFileIOImp() {
  }

  /**
   * saveCommendInReadPageFile
   *
   * @param commendList List
   * @throws IOException
   * @todo Implement this com.laoer.bbscs.fio.CommendFileIO method
   */
  public void saveCommendInReadPageFile(long commendid, List commendList) throws IOException {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < commendList.size(); i++) {
      Commend commend = (Commend) commendList.get(i);
      sb.append("·");
      sb.append("<a href=\"");
      if (Constant.USE_URL_REWRITE) {
        sb.append("read-topic-" + commend.getBoardID() + "-" + commend.getPostMainID() + "-0-1-index-1.html");
      }
      else {
        sb.append(BBSCSUtil.getActionMappingURLWithoutPrefix("read?action=topic&id=" + commend.getPostMainID() +
            "&bid=" + commend.getBoardID()));
      }
      sb.append("\">");
      sb.append(TextUtils.htmlEncode(commend.getTitle()));
      sb.append("</a><BR/>");
    }
    File commendFile = new File(BBSCSUtil.getIncludePath() + "Commend_" + commendid + ".html");
    FileUtils.writeStringToFile(commendFile, sb.toString(), Constant.CHARSET);


    commendFile = null;
    sb = null;
    sb = new StringBuffer();

    //int counter = 0;
    for (int i = 0; i < commendList.size(); i++) {
      Commend c = (Commend) commendList.get(i);
      sb.append("<tr>");
      sb.append("<td>");
      sb.append("<a href=\"");
      if (Constant.USE_URL_REWRITE) {
        sb.append("read-topic-" + c.getBoardID() + "-" + c.getPostMainID() + "-0-1-index-1.html");
      }
      else {
        sb.append(BBSCSUtil.getActionMappingURLWithoutPrefix("read?action=topic&id=" + c.getPostMainID() +
            "&bid=" + c.getBoardID()));
      }
      sb.append("\" title=\"");
      sb.append(c.getTitle());
      sb.append("\">");
      sb.append(c.getTitle());
      sb.append("</a>");
      sb.append("[<a href=\"");
      if (Constant.USE_URL_REWRITE) {
        sb.append("forum-index-" + c.getBoardID() + ".html");
      }
      else {
        sb.append(BBSCSUtil.getActionMappingURLWithoutPrefix("forum?action=index&bid=" + c.getBoardID()));
      }
      sb.append("\">");
      sb.append(c.getBoardName());
      sb.append("</a>]");

      sb.append("</td>");
      sb.append("</tr>");

    }
    commendFile = new File(BBSCSUtil.getIncludePath() + "ForumCover_Commend_" + commendid + ".html");
    FileUtils.writeStringToFile(commendFile, sb.toString(), Constant.CHARSET);

  }
}
