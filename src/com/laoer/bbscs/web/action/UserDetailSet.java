package com.laoer.bbscs.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laoer.bbscs.bean.UserInfo;
import com.laoer.bbscs.comm.Constant;
import com.laoer.bbscs.comm.IPSeeker;
import com.laoer.bbscs.exception.BbscsException;
//import com.laoer.bbscs.service.Cache;
import com.laoer.bbscs.service.UserService;
import com.laoer.bbscs.web.interceptor.RemoteAddrAware;
import com.laoer.bbscs.web.ui.OptionsInt;
import com.laoer.bbscs.web.ui.OptionsString;
import com.laoer.bbscs.web.ui.RadioInt;

public class UserDetailSet extends BaseMainAction implements RemoteAddrAware {

	/**
	 *
	 */
	private static final long serialVersionUID = -5252707590130695817L;

	private String userRemoteAddr = "";

	private UserService userService;

	private IPSeeker ipSeeker;

	//private Cache userSessionCache;

	public void setRemoteAddr(String remoteAddr) {
		this.userRemoteAddr = remoteAddr;
	}

	public String getUserRemoteAddr() {
		return userRemoteAddr;
	}

	public void setUserRemoteAddr(String userRemoteAddr) {
		this.userRemoteAddr = userRemoteAddr;
	}

	public IPSeeker getIpSeeker() {
		return ipSeeker;
	}

	public void setIpSeeker(IPSeeker ipSeeker) {
		this.ipSeeker = ipSeeker;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}



	private int birthDay;

	private int birthMonth;

	private String birthYear;

	private String email;

	private String userFrom;

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

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
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

	public String index() {
		UserInfo ui = this.getUserService().findUserInfoById(this.getUserSession().getId());
		if (ui == null) {
			this.addActionError(this.getText("error.user.noexist"));
			return ERROR;
		}
		setRadioSexListValues();
		this.setAction("edit");
		this.setBirthDay(ui.getBirthDay());
		this.setBirthMonth(ui.getBirthMonth());
		this.setBirthYear(String.valueOf(ui.getBirthYear()));
		this.setBrief(ui.getUserDetail().getBrief());
		this.setDreamJob(ui.getUserDetail().getDreamJob());
		this.setDreamLover(ui.getUserDetail().getDreamLover());
		this.setEmail(ui.getEmail());
		this.setFavourArt(ui.getUserDetail().getFavourArt());
		this.setFavourBook(ui.getUserDetail().getFavourBook());
		this.setFavourChat(ui.getUserDetail().getFavourChat());
		this.setFavourMovie(ui.getUserDetail().getFavourMovie());
		this.setFavourMusic(ui.getUserDetail().getFavourMusic());
		this.setFavourPeople(ui.getUserDetail().getFavourPeople());
		this.setFavourPlace(ui.getUserDetail().getFavourPlace());
		this.setFavourTeam(ui.getUserDetail().getFavourTeam());
		this.setGraduate(ui.getUserDetail().getGraduate());
		this.setHeight(ui.getUserDetail().getHeight());
		this.setHomePage(ui.getUserDetail().getHomePage());
		this.setIcqNo(ui.getUserDetail().getIcqNo());
		this.setInterest(ui.getUserDetail().getInterest());
		this.setMsn(ui.getUserDetail().getMsn());
		this.setOicqNo(ui.getUserDetail().getOicqNo());
		this.setSex(ui.getUserDetail().getSex());
		this.setUserFrom(ui.getUserFrom());
		this.setWeight(ui.getUserDetail().getWeight());
		this.setYahoo(ui.getUserDetail().getYahoo());
		return INPUT;
	}

	public String edit() {
		UserInfo ui = this.getUserService().findUserInfoById(this.getUserSession().getId());
		if (ui == null) {
			this.addActionError(this.getText("error.user.noexist"));
			return INPUT;
		}
		setRadioSexListValues();
		ui.setBirthDay(this.getBirthDay());
		ui.setBirthMonth(this.getBirthMonth());
		ui.setBirthYear(Integer.parseInt(this.getBirthYear()));
		ui.getUserDetail().setBrief(this.getBrief());
		ui.getUserDetail().setDreamJob(this.getDreamJob());
		ui.getUserDetail().setDreamLover(this.getDreamLover());
		ui.setEmail(this.getEmail());
		ui.getUserDetail().setFavourArt(this.getFavourArt());
		ui.getUserDetail().setFavourBook(this.getFavourBook());
		ui.getUserDetail().setFavourChat(this.getFavourChat());
		ui.getUserDetail().setFavourMovie(this.getFavourMovie());
		ui.getUserDetail().setFavourMusic(this.getFavourMusic());
		ui.getUserDetail().setFavourPeople(this.getFavourPeople());
		ui.getUserDetail().setFavourPlace(this.getFavourPlace());
		ui.getUserDetail().setFavourTeam(this.getFavourTeam());
		ui.getUserDetail().setGraduate(this.getGraduate());
		ui.getUserDetail().setHeight(this.getHeight());
		ui.getUserDetail().setHomePage(this.getHomePage());
		ui.getUserDetail().setIcqNo(this.getIcqNo());
		ui.getUserDetail().setInterest(this.getInterest());
		ui.getUserDetail().setMsn(this.getMsn());
		ui.getUserDetail().setOicqNo(this.getOicqNo());
		ui.getUserDetail().setSex(this.getSex());
		if (StringUtils.isBlank(this.getUserFrom())) {
			ui.setUserFrom(this.getIpSeeker().getCountry(this.getUserRemoteAddr()));
		} else {
			ui.setUserFrom(this.getUserFrom());
		}
		ui.getUserDetail().setWeight(this.getWeight());
		ui.getUserDetail().setYahoo(this.getYahoo());

		try {
			ui = this.getUserService().saveUserInfo(ui);
			this.getUserSession().setEmail(ui.getEmail());

			this.addActionMessage(this.getText("bbscs.dataupdate.succeed"));

			//this.getUserSessionCache().remove(this.getUserCookie().getUserName());
		} catch (BbscsException ex) {
			this.addActionError(this.getText("error.dataupdate.failed"));
		}
		return INPUT;
	}

	/*
	public Cache getUserSessionCache() {
		return userSessionCache;
	}

	public void setUserSessionCache(Cache userSessionCache) {
		this.userSessionCache = userSessionCache;
	}*/

}
