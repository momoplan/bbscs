package com.laoer.bbscs.service.imp;

import org.apache.commons.logging.*;
import com.laoer.bbscs.dao.*;
import com.laoer.bbscs.exception.*;
import com.laoer.bbscs.service.*;
import java.util.*;
import com.laoer.bbscs.bean.UserTop;
import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.comm.BBSCSUtil;
import java.io.File;
import org.apache.commons.io.FileUtils;
import com.laoer.bbscs.comm.Constant;

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
public class UserTopServiceImp
    implements UserTopService {

  private static final Log logger = LogFactory.getLog(UserTopServiceImp.class);

  private UserTopDAO userTopDAO;

  private UserInfoDAO userInfoDAO;

  public UserTopServiceImp() {
  }

  /**
   *
   * @throws BbscsException
   * @todo Implement this com.laoer.bbscs.service.UserTopService method
   */
  public void createUserTopFile() throws BbscsException {
    try {
      List utl = this.getUserTopDAO().findUserTopsByType(1);
      Map<String,UserTop> utm = new HashMap<String,UserTop>();
      for (int i = 0; i < utl.size(); i++) {
        UserTop ut = (UserTop) utl.get(i);
        utm.put(ut.getUserName(), ut);
      }
      this.getUserTopDAO().removeUserTopByType(1);
      List l = this.getUserInfoDAO().findUserInfoList("experience", "desc", 0, 100);
      for (int i = 0; i < l.size(); i++) {
        UserInfo ui = (UserInfo) l.get(i);
        UserTop ut = (UserTop) utm.get(ui.getUserName());
        UserTop utnew = new UserTop();
        utnew.setNickName(ui.getNickName());
        utnew.setUserID(ui.getId());
        utnew.setUserName(ui.getUserName());
        utnew.setUserValue(ui.getExperience());
        utnew.setValueType(1);
        if (ut != null) {
          utnew.setValueInc(ui.getExperience() - ut.getUserValue());
        }
        else {
          utnew.setValueInc(ui.getExperience());
        }
        this.getUserTopDAO().saveUserTop(utnew);
      }
      utl = null;
      utm = null;
      List utlist = this.getUserTopDAO().findUserTopsByType(1, 0, 10);
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < utlist.size(); i++) {
        UserTop ut = (UserTop) utlist.get(i);
        sb.append("<tr>");
        sb.append("<td width=\"70%\">");
        sb.append("<a href=\"");
        sb.append(BBSCSUtil.getActionMappingURLWithoutPrefix("userInfo?action=id&id=" + ut.getUserID()));
        sb.append("\">");
        sb.append(ut.getNickName());
        sb.append("</a>");
        sb.append("</td>");
        sb.append("<td width=\"30%\">");
        sb.append(ut.getValueInc());
        sb.append("</td>");
        sb.append("</tr>");
      }
      File topFile = new File(BBSCSUtil.getIncludePath() + "UserExp.html");
      FileUtils.writeStringToFile(topFile, sb.toString(), Constant.CHARSET);
      utlist = null;
      topFile = null;
      sb = null;

      //---------------------------------------

      utl = this.getUserTopDAO().findUserTopsByType(2);
      utm = new HashMap<String,UserTop>();
      for (int i = 0; i < utl.size(); i++) {
        UserTop ut = (UserTop) utl.get(i);
        utm.put(ut.getUserName(), ut);
      }
      this.getUserTopDAO().removeUserTopByType(2);
      l = this.getUserInfoDAO().findUserInfoList("literary", "desc", 0, 100);
      for (int i = 0; i < l.size(); i++) {
        UserInfo ui = (UserInfo) l.get(i);
        UserTop ut = (UserTop) utm.get(ui.getUserName());
        UserTop utnew = new UserTop();
        utnew.setNickName(ui.getNickName());
        utnew.setUserID(ui.getId());
        utnew.setUserName(ui.getUserName());
        utnew.setUserValue(ui.getLiterary());
        utnew.setValueType(2);
        if (ut != null) {
          utnew.setValueInc(ui.getLiterary() - ut.getUserValue());
        }
        else {
          utnew.setValueInc(ui.getLiterary());
        }
        this.getUserTopDAO().saveUserTop(utnew);
      }
      utl = null;
      utm = null;
      utlist = this.getUserTopDAO().findUserTopsByType(2, 0, 10);
      sb = new StringBuffer();
      for (int i = 0; i < utlist.size(); i++) {
        UserTop ut = (UserTop) utlist.get(i);
        sb.append("<tr>");
        sb.append("<td width=\"70%\">");
        sb.append("<a href=\"");
        sb.append(BBSCSUtil.getActionMappingURLWithoutPrefix("userInfo?action=id&id=" + ut.getUserID()));
        sb.append("\">");
        sb.append(ut.getNickName());
        sb.append("</a>");
        sb.append("</td>");
        sb.append("<td width=\"30%\">");
        sb.append(ut.getValueInc());
        sb.append("</td>");
        sb.append("</tr>");
      }
      topFile = new File(BBSCSUtil.getIncludePath() + "UserLit.html");
      FileUtils.writeStringToFile(topFile, sb.toString(), Constant.CHARSET);
      utlist = null;
      topFile = null;
      sb = null;

      //---------------------------------------------------------

      utl = this.getUserTopDAO().findUserTopsByType(3);
      utm = new HashMap<String,UserTop>();
      for (int i = 0; i < utl.size(); i++) {
        UserTop ut = (UserTop) utl.get(i);
        utm.put(ut.getUserName(), ut);
      }
      this.getUserTopDAO().removeUserTopByType(3);
      l = this.getUserInfoDAO().findUserInfoList("userKnow", "desc", 0, 100);
      for (int i = 0; i < l.size(); i++) {
        UserInfo ui = (UserInfo) l.get(i);
        UserTop ut = (UserTop) utm.get(ui.getUserName());
        UserTop utnew = new UserTop();
        utnew.setNickName(ui.getNickName());
        utnew.setUserID(ui.getId());
        utnew.setUserName(ui.getUserName());
        utnew.setUserValue(ui.getUserKnow());
        utnew.setValueType(3);
        if (ut != null) {
          utnew.setValueInc(ui.getUserKnow() - ut.getUserValue());
        }
        else {
          utnew.setValueInc(ui.getUserKnow());
        }
        this.getUserTopDAO().saveUserTop(utnew);
      }
      utl = null;
      utm = null;
      utlist = this.getUserTopDAO().findUserTopsByType(3, 0, 10);
      sb = new StringBuffer();
      for (int i = 0; i < utlist.size(); i++) {
        UserTop ut = (UserTop) utlist.get(i);
        sb.append("<tr>");
        sb.append("<td width=\"70%\">");
        sb.append("<a href=\"");
        sb.append(BBSCSUtil.getActionMappingURLWithoutPrefix("userInfo?action=id&id=" + ut.getUserID()));
        sb.append("\">");
        sb.append(ut.getNickName());
        sb.append("</a>");
        sb.append("</td>");
        sb.append("<td width=\"30%\">");
        sb.append(ut.getValueInc());
        sb.append("</td>");
        sb.append("</tr>");
      }
      topFile = new File(BBSCSUtil.getIncludePath() + "UserKnow.html");
      FileUtils.writeStringToFile(topFile, sb.toString(), Constant.CHARSET);
      utlist = null;
      topFile = null;
      sb = null;

    }
    catch (Exception ex) {
      logger.error(ex);
      throw new BbscsException(ex);
    }
  }

  public UserInfoDAO getUserInfoDAO() {
    return userInfoDAO;
  }

  public UserTopDAO getUserTopDAO() {
    return userTopDAO;
  }

  public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
    this.userInfoDAO = userInfoDAO;
  }

  public void setUserTopDAO(UserTopDAO userTopDAO) {
    this.userTopDAO = userTopDAO;
  }
}
