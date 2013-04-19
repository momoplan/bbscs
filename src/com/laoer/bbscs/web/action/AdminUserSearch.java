package com.laoer.bbscs.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.laoer.bbscs.bean.UserDetail;
import com.laoer.bbscs.bean.UserGroup;
import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.comm.*;
import com.laoer.bbscs.exception.BbscsException;
import com.laoer.bbscs.service.UserGroupService;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.service.config.SysConfig;
import com.laoer.bbscs.web.ui.OptionsInt;
import com.laoer.bbscs.web.ui.OptionsString;
import com.laoer.bbscs.web.ui.RadioInt;

import java.util.*;

public class AdminUserSearch extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AdminUserSearch.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -1994658621743207006L;

	private UserService userService;

	private UserGroupService userGroupService;

	private SysConfig sysConfig;

	private SysOptionsValues sysOptionsValues;

	private String id;

	private String userName;

	private String nickName;

	private String passwd;

	private String email;

	private String regTime;

	private String loginTime;

	private String loginIP;

	private int loginTimes;

	private String lastLoginTime;

	private String lastLoginIP;

	private int articleNum;

	private long stayTime;

	private int articleEliteNum;

	private int lifeForce;

	private int userTitle;

	private int literary;

	private int experience;

	private int userKnow;

	private String signName0;

	private String signDetail0;

	private String signName1;

	private String signDetail1;

	private String signName2;

	private String signDetail2;

	private int havePic;

	private String picFileName;

	private int forumPerNum;

	private int postPerNum;

	private String userFrom;

	private String timeZone;

	private String birthYear;

	private int birthMonth;

	private int birthDay;

	private int receiveNote;

	private int acceptFriend;

	private int forumViewMode;

	private int validated;

	private int groupID;

	private boolean delFace;

	private String height;

	/** nullable persistent field */
	private String weight;

	/** nullable persistent field */
	private String interest;

	/** nullable persistent field */
	private String graduate;

	/** nullable persistent field */
	private String favourPeople;

	/** nullable persistent field */
	private String dreamJob;

	/** nullable persistent field */
	private String favourArt;

	/** nullable persistent field */
	private String favourMusic;

	/** nullable persistent field */
	private String favourPlace;

	/** nullable persistent field */
	private String favourMovie;

	/** nullable persistent field */
	private String favourChat;

	/** nullable persistent field */
	private String favourBook;

	/** nullable persistent field */
	private String dreamLover;

	/** nullable persistent field */
	private String favourTeam;

	/** nullable persistent field */
	private String homePage;

	/** nullable persistent field */
	private String oicqNo;

	/** nullable persistent field */
	private String icqNo;

	/** nullable persistent field */
	private int sex;

	/** nullable persistent field */
	private String brief;

	/** nullable persistent field */
	private String msn;

	/** nullable persistent field */
	private String yahoo;

	private String question;

	private int coin;

	public int getAcceptFriend() {
		return acceptFriend;
	}

	public void setAcceptFriend(int acceptFriend) {
		this.acceptFriend = acceptFriend;
	}

	public int getArticleEliteNum() {
		return articleEliteNum;
	}

	public void setArticleEliteNum(int articleEliteNum) {
		this.articleEliteNum = articleEliteNum;
	}

	public int getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}

	public int getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public boolean getDelFace() {
		return delFace;
	}

	public void setDelFace(boolean delFace) {
		this.delFace = delFace;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getForumPerNum() {
		return forumPerNum;
	}

	public void setForumPerNum(int forumPerNum) {
		this.forumPerNum = forumPerNum;
	}

	public int getForumViewMode() {
		return forumViewMode;
	}

	public void setForumViewMode(int forumViewMode) {
		this.forumViewMode = forumViewMode;
	}

	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public int getHavePic() {
		return havePic;
	}

	public void setHavePic(int havePic) {
		this.havePic = havePic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getLifeForce() {
		return lifeForce;
	}

	public void setLifeForce(int lifeForce) {
		this.lifeForce = lifeForce;
	}

	public int getLiterary() {
		return literary;
	}

	public void setLiterary(int literary) {
		this.literary = literary;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public int getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public int getPostPerNum() {
		return postPerNum;
	}

	public void setPostPerNum(int postPerNum) {
		this.postPerNum = postPerNum;
	}

	public int getReceiveNote() {
		return receiveNote;
	}

	public void setReceiveNote(int receiveNote) {
		this.receiveNote = receiveNote;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public String getSignDetail0() {
		return signDetail0;
	}

	public void setSignDetail0(String signDetail0) {
		this.signDetail0 = signDetail0;
	}

	public String getSignDetail1() {
		return signDetail1;
	}

	public void setSignDetail1(String signDetail1) {
		this.signDetail1 = signDetail1;
	}

	public String getSignDetail2() {
		return signDetail2;
	}

	public void setSignDetail2(String signDetail2) {
		this.signDetail2 = signDetail2;
	}

	public String getSignName0() {
		return signName0;
	}

	public void setSignName0(String signName0) {
		this.signName0 = signName0;
	}

	public String getSignName1() {
		return signName1;
	}

	public void setSignName1(String signName1) {
		this.signName1 = signName1;
	}

	public String getSignName2() {
		return signName2;
	}

	public void setSignName2(String signName2) {
		this.signName2 = signName2;
	}

	public long getStayTime() {
		return stayTime;
	}

	public void setStayTime(long stayTime) {
		this.stayTime = stayTime;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public SysOptionsValues getSysOptionsValues() {
		return sysOptionsValues;
	}

	public void setSysOptionsValues(SysOptionsValues sysOptionsValues) {
		this.sysOptionsValues = sysOptionsValues;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}

	public UserGroupService getUserGroupService() {
		return userGroupService;
	}

	public void setUserGroupService(UserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}

	public int getUserKnow() {
		return userKnow;
	}

	public void setUserKnow(int userKnow) {
		this.userKnow = userKnow;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public int getUserTitle() {
		return userTitle;
	}

	public void setUserTitle(int userTitle) {
		this.userTitle = userTitle;
	}

	public int getValidated() {
		return validated;
	}

	public void setValidated(int validated) {
		this.validated = validated;
	}

	private List<OptionsInt> groupValues = new ArrayList<OptionsInt>();

	public List<OptionsInt> getGroupValues() {
		return groupValues;
	}

	public void setGroupValues(List<OptionsInt> groupValues) {
		this.groupValues = groupValues;
	}

	private void setGroupValuesInit() {
		List gl = this.getUserGroupService().findUserGroupsAll();
		for (int i = 0; i < gl.size(); i++) {
			UserGroup ug = (UserGroup) gl.get(i);
			this.getGroupValues().add(new OptionsInt(ug.getId(), ug.getGroupName()));
		}
	}

	private List<OptionsInt> userTitleValues;

	public List<OptionsInt> getUserTitleValues() {
		return userTitleValues;
	}

	public void setUserTitleValues(List<OptionsInt> userTitleValues) {
		this.userTitleValues = userTitleValues;
	}

	private void setUserTitleValuesInit() {
		this.userTitleValues = this.getSysOptionsValues().getUserTitleValues(this.getLocale());
	}

	private List<OptionsInt> userForumNumPerPageValues;

	public List<OptionsInt> getUserForumNumPerPageValues() {
		return userForumNumPerPageValues;
	}

	public void setUserForumNumPerPageValues(List<OptionsInt> userForumNumPerPageValues) {
		this.userForumNumPerPageValues = userForumNumPerPageValues;
	}

	private void setUserForumNumPerPageValuesInit() {
		this.setUserForumNumPerPageValues(this.getSysOptionsValues().getUserForumNumPerPageValues(this.getLocale()));
	}

	private List<OptionsInt> userPostNumPerPageValues;

	public List<OptionsInt> getUserPostNumPerPageValues() {
		return userPostNumPerPageValues;
	}

	public void setUserPostNumPerPageValues(List<OptionsInt> userPostNumPerPageValues) {
		this.userPostNumPerPageValues = userPostNumPerPageValues;
	}

	private void setUserPostNumPerPageValuesInit() {
		this.setUserPostNumPerPageValues(this.getSysOptionsValues().getUserPostNumPerPageValues(this.getLocale(),
				this.getSysConfig().getUserPostPerPageNum()));
	}

	private List<OptionsString> userTimeZoneValues = Constant.USERTIMEZONE;

	public List<OptionsString> getUserTimeZoneValues() {
		return userTimeZoneValues;
	}

	public void setUserTimeZoneValues(List<OptionsString> userTimeZoneValues) {
		this.userTimeZoneValues = userTimeZoneValues;
	}

	private List<OptionsString> yearValues = Constant.YEARS;

	public List<OptionsString> getYearValues() {
		return yearValues;
	}

	public void setYearValues(List<OptionsString> yearValues) {
		this.yearValues = yearValues;
	}

	private List<OptionsInt> monthValues = Constant.MONTH;

	public List<OptionsInt> getMonthValues() {
		return monthValues;
	}

	public void setMonthValues(List<OptionsInt> monthValues) {
		this.monthValues = monthValues;
	}

	private List<OptionsInt> dayValues = Constant.DAY;

	public List<OptionsInt> getDayValues() {
		return dayValues;
	}

	public void setDayValues(List<OptionsInt> dayValues) {
		this.dayValues = dayValues;
	}

	private List<OptionsInt> forumViewModeValues;

	public List<OptionsInt> getForumViewModeValues() {
		return forumViewModeValues;
	}

	public void setForumViewModeValues(List<OptionsInt> forumViewModeValues) {
		this.forumViewModeValues = forumViewModeValues;
	}

	private void setForumViewModeValuesInit() {
		this.setForumViewModeValues(this.getSysOptionsValues().getForumViewModeValues(this.getLocale()));
	}

	List<RadioInt> radioYesNoList = new ArrayList<RadioInt>();

	private void setRadioYesNoListValues() {

		radioYesNoList.add(new RadioInt(0, this.getText("bbscs.no")));
		radioYesNoList.add(new RadioInt(1, this.getText("bbscs.yes")));

	}

	public List<RadioInt> getRadioYesNoList() {
		return radioYesNoList;
	}

	public void setRadioYesNoList(List<RadioInt> radioYesNoList) {
		this.radioYesNoList = radioYesNoList;
	}

	List<RadioInt> radioSexList = new ArrayList<RadioInt>();

	private void setRadioSexListValues() {
		radioSexList.add(new RadioInt(1, this.getText("bbscs.man")));
		radioSexList.add(new RadioInt(2, this.getText("bbscs.woman")));
	}

	public List<RadioInt> getRadioSexList() {
		return radioSexList;
	}

	public void setRadioSexList(List<RadioInt> radioSexList) {
		this.radioSexList = radioSexList;
	}

	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			logger.error(e);
			return ERROR;
		}
	}

	public String index() {
		return INPUT;
	}

	private void setUser(UserInfo ui) {
		UserDetail ud = ui.getUserDetail();
		this.setId(ui.getId());
		this.setAcceptFriend(ui.getAcceptFriend());
		this.setAction("edit");
		this.setArticleEliteNum(ui.getArticleEliteNum());
		this.setArticleNum(ui.getArticleNum());
		this.setBirthDay(ui.getBirthDay());
		this.setBirthMonth(ui.getBirthMonth());
		this.setBirthYear(String.valueOf(ui.getBirthYear()));
		this.setBrief(ud.getBrief());
		this.setDreamJob(ud.getDreamJob());
		this.setDreamLover(ud.getDreamLover());
		this.setEmail(ui.getEmail());
		this.setExperience(ui.getExperience());
		this.setFavourArt(ud.getFavourArt());
		this.setFavourBook(ud.getFavourBook());
		this.setFavourChat(ud.getFavourChat());
		this.setFavourMovie(ud.getFavourMovie());
		this.setFavourMusic(ud.getFavourMusic());
		this.setFavourPeople(ud.getFavourPeople());
		this.setFavourPlace(ud.getFavourPlace());
		this.setFavourTeam(ud.getFavourTeam());
		this.setForumPerNum(ui.getForumPerNum());
		this.setForumViewMode(ui.getForumViewMode());
		this.setGraduate(ud.getGraduate());
		this.setGroupID(ui.getGroupID());
		this.setHavePic(ui.getHavePic());
		this.setHeight(ud.getHeight());
		this.setHomePage(ud.getHomePage());
		this.setIcqNo(ud.getIcqNo());
		this.setInterest(ud.getInterest());
		this.setLastLoginIP(ui.getLastLoginIP());
		this.setLastLoginTime(Util.formatDateTime(ui.getLastLoginTime()));
		this.setLifeForce(ui.getLifeForce());
		this.setLiterary(ui.getLiterary());
		this.setLoginIP(ui.getLoginIP());
		this.setLoginTime(Util.formatDateTime(ui.getLoginTime()));
		this.setLoginTimes(ui.getLoginTimes());
		this.setMsn(ud.getMsn());
		this.setNickName(ui.getNickName());
		this.setOicqNo(ud.getOicqNo());
		this.setPasswd(ui.getRePasswd());
		this.setPicFileName(ui.getPicFileName());
		this.setPostPerNum(ui.getPostPerNum());
		this.setQuestion(ui.getQuestion());
		this.setReceiveNote(ui.getReceiveNote());
		this.setRegTime(Util.formatDateTime(ui.getRegTime()));
		this.setSex(ud.getSex());
		this.setSignDetail0(ui.getSignDetail0());
		this.setSignDetail1(ui.getSignDetail1());
		this.setSignDetail2(ui.getSignDetail2());
		this.setStayTime(ui.getStayTime());
		this.setTimeZone(ui.getTimeZone());
		this.setUserFrom(ui.getUserFrom());
		this.setUserName(ui.getUserName());
		this.setUserKnow(ui.getUserKnow());
		this.setUserTitle(ui.getUserTitle());
		this.setValidated(ui.getValidated());
		this.setWeight(ud.getWeight());
		this.setYahoo(ud.getYahoo());
	}

	public String suid() {
		UserInfo ui = this.getUserService().findUserInfoById(this.getId());

		if (ui == null) {
			this.addActionError(this.getText("error.user.notexist"));
			return INPUT;
		}
		this.setGroupValuesInit();
		this.setUserForumNumPerPageValuesInit();
		this.setUserPostNumPerPageValuesInit();
		this.setUserTitleValuesInit();
		this.setRadioYesNoListValues();
		this.setRadioSexListValues();
		this.setForumViewModeValuesInit();
		this.setUser(ui);

		return SUCCESS;
	}

	public String sname() {
		UserInfo ui = this.getUserService().findUserInfoByUserName(this.getUserName());
		if (ui == null) {
			this.addActionError(this.getText("error.user.notexist"));
			return INPUT;
		}
		this.setGroupValuesInit();
		this.setUserForumNumPerPageValuesInit();
		this.setUserPostNumPerPageValuesInit();
		this.setUserTitleValuesInit();
		this.setRadioYesNoListValues();
		this.setRadioSexListValues();
		this.setForumViewModeValuesInit();
		this.setUser(ui);

		return SUCCESS;
	}

	public String edit() {
		UserInfo ui = this.getUserService().findUserInfoById(this.getId());

		if (ui == null) {
			this.addActionError(this.getText("error.user.notexist"));
			return INPUT;
		}

		UserDetail ud = ui.getUserDetail();
		this.setUserName(ui.getUserName());
		ui.setAcceptFriend(this.getAcceptFriend());
		ui.setBirthDay(this.getBirthDay());
		ui.setBirthMonth(this.getBirthMonth());
		ui.setBirthYear(Integer.parseInt(this.getBirthYear()));
		ud.setBrief(this.getBrief());
		ud.setDreamJob(this.getDreamJob());
		ud.setDreamLover(this.getDreamLover());
		ui.setEmail(this.getEmail());
		ud.setFavourArt(this.getFavourArt());
		ud.setFavourBook(this.getFavourBook());
		ud.setFavourChat(this.getFavourChat());
		ud.setFavourMovie(this.getFavourMovie());
		ud.setFavourMusic(this.getFavourMusic());
		ud.setFavourPeople(this.getFavourPeople());
		ud.setFavourPlace(this.getFavourPlace());
		ud.setFavourTeam(this.getFavourTeam());
		ui.setForumPerNum(this.getForumPerNum());
		ui.setForumViewMode(this.getForumViewMode());
		ud.setGraduate(this.getGraduate());
		ui.setGroupID(this.getGroupID());
		ud.setHeight(this.getHeight());
		ud.setHomePage(this.getHomePage());
		ud.setIcqNo(this.getIcqNo());
		ud.setInterest(this.getInterest());
		ud.setMsn(this.getMsn());
		ui.setNickName(this.getNickName());
		ud.setOicqNo(this.getOicqNo());

		if (!this.getPasswd().equals(ui.getRePasswd())) {
			ui.setPasswd(this.getPasswd());
			ui.setRePasswd(Util.hash(this.getPasswd()));
		}

		ui.setPostPerNum(this.getPostPerNum());
		ui.setReceiveNote(this.getReceiveNote());
		ud.setSex(this.getSex());
		ui.setSignDetail0(this.getSignDetail0());
		ui.setSignDetail1(this.getSignDetail1());
		ui.setSignDetail2(this.getSignDetail2());
		ui.setTimeZone(this.getTimeZone());
		ui.setUserFrom(this.getUserFrom());
		ui.setUserTitle(this.getUserTitle());

		if (this.getValidated() == 0) {
			ui.setValidated(0);
			ui.setGroupID(Constant.USER_GROUP_UNVUSER);
		}
		if (this.getValidated() == 1) {
			ui.setValidated(1);
			if (ui.getGroupID() == Constant.USER_GROUP_UNVUSER) {
				ui.setGroupID(Constant.USER_GROUP_REGUSER);
			}
		}
		ud.setWeight(this.getWeight());
		ud.setYahoo(this.getYahoo());

		try {
			ui = this.getUserService().saveUserInfo(ui);
			if (ui.getHavePic() > 0 && this.getDelFace()) {
				this.getUserService().removeUserFacePic(ui);
			}
			this.addActionMessage(this.getText("bbscs.dataupdate.succeed"));
		} catch (BbscsException e) {
			logger.error(e);
			this.addActionError(this.getText("error.dataupdate.failed"));
		}
		this.setGroupValuesInit();
		this.setUserForumNumPerPageValuesInit();
		this.setUserPostNumPerPageValuesInit();
		this.setUserTitleValuesInit();
		this.setRadioYesNoListValues();
		this.setRadioSexListValues();
		this.setForumViewModeValuesInit();
		return SUCCESS;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public String getDreamJob() {
		return dreamJob;
	}

	public void setDreamJob(String dreamJob) {
		this.dreamJob = dreamJob;
	}

	public String getDreamLover() {
		return dreamLover;
	}

	public void setDreamLover(String dreamLover) {
		this.dreamLover = dreamLover;
	}

	public String getFavourArt() {
		return favourArt;
	}

	public void setFavourArt(String favourArt) {
		this.favourArt = favourArt;
	}

	public String getFavourBook() {
		return favourBook;
	}

	public void setFavourBook(String favourBook) {
		this.favourBook = favourBook;
	}

	public String getFavourChat() {
		return favourChat;
	}

	public void setFavourChat(String favourChat) {
		this.favourChat = favourChat;
	}

	public String getFavourMovie() {
		return favourMovie;
	}

	public void setFavourMovie(String favourMovie) {
		this.favourMovie = favourMovie;
	}

	public String getFavourMusic() {
		return favourMusic;
	}

	public void setFavourMusic(String favourMusic) {
		this.favourMusic = favourMusic;
	}

	public String getFavourPeople() {
		return favourPeople;
	}

	public void setFavourPeople(String favourPeople) {
		this.favourPeople = favourPeople;
	}

	public String getFavourPlace() {
		return favourPlace;
	}

	public void setFavourPlace(String favourPlace) {
		this.favourPlace = favourPlace;
	}

	public String getFavourTeam() {
		return favourTeam;
	}

	public void setFavourTeam(String favourTeam) {
		this.favourTeam = favourTeam;
	}

	public String getGraduate() {
		return graduate;
	}

	public void setGraduate(String graduate) {
		this.graduate = graduate;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getIcqNo() {
		return icqNo;
	}

	public void setIcqNo(String icqNo) {
		this.icqNo = icqNo;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getOicqNo() {
		return oicqNo;
	}

	public void setOicqNo(String oicqNo) {
		this.oicqNo = oicqNo;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getYahoo() {
		return yahoo;
	}

	public void setYahoo(String yahoo) {
		this.yahoo = yahoo;
	}

}
