package com.laoer.bbscs.comm;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

import javax.imageio.ImageIO;
import javax.servlet.http.*;
import org.apache.struts.util.*;
import com.opensymphony.xwork2.util.TextUtils;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import org.apache.commons.io.*;
import org.apache.commons.lang.StringUtils;

public class BBSCSUtil {
	
	private static ResourceBundle rbint = ResourceBundle.getBundle("ruyicai") ;

	public static String getUserWebFilePath(String userID) {
		StringBuffer sb = new StringBuffer();
		int num = Math.abs(userID.hashCode());
		sb.append(rbint.getString("upload"));
		sb.append("user/");
		sb.append(num % 100);
		sb.append("/");
		sb.append(userID);
		sb.append("/");
		File ft = new File(sb.toString());
		if (!ft.exists()) {
			ft.mkdirs();
		}
		return sb.toString();
	}

	public static String getUserWebPath(String userID) {
		StringBuffer sb = new StringBuffer();
		int num = Math.abs(userID.hashCode());
		sb.append("user/");
		sb.append(num % 100);
		sb.append("/");
		sb.append(userID);
		sb.append("/");
		return sb.toString();
	}

	public static String getWebRealPath(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		sb.append("http://");
		sb.append(request.getServerName());
		if (request.getServerPort() != 80) {
			sb.append(":");
			sb.append(request.getServerPort());
		}

		// sb.append(request.getContextPath());
		// sb.append("/");
		return sb.toString();
	}

	public static String getUpFilePath(long bid, long adate) {
		StringBuffer sb = new StringBuffer();
		sb.append(rbint.getString("upload"));
		sb.append(getUpFileWebPath(bid, adate));
		File ft = new File(sb.toString());
		if (!ft.exists()) {
			ft.mkdirs();
		}
		return sb.toString();
	}

	public static String getUpFileWebPath(long bid, long adate) {
		StringBuffer sb = new StringBuffer();
		sb.append("/upload/");
		sb.append((bid % 20));
		sb.append("/");
		sb.append(bid);
		sb.append("/");
		sb.append(Util.formatDate4(new Date(adate)));
		sb.append("/");
		return sb.toString();
	}

	public static String getIncludePath() {
		StringBuffer sb = new StringBuffer();
		sb.append(Constant.ROOTPATH);
		sb.append("include/");
		File ft = new File(sb.toString());
		if (!ft.exists()) {
			ft.mkdirs();
		}
		return sb.toString();
	}

	public static String getActionMappingName(String action) {
		String value = action;
		int question = action.indexOf("?");
		if (question >= 0) {
			value = value.substring(0, question);
		}
		int slash = value.lastIndexOf("/");
		int period = value.lastIndexOf(".");
		if ((period >= 0) && (period > slash)) {
			value = value.substring(0, period);
		}
		if (value.startsWith("/")) {
			return (value);
		} else {
			return ("/" + value);
		}
	}

	public static String getActionMappingNameWithoutPrefix(String action) {
		String value = action;
		int question = action.indexOf("?");
		if (question >= 0) {
			value = value.substring(0, question);
		}
		int slash = value.lastIndexOf("/");
		int period = value.lastIndexOf(".");
		if ((period >= 0) && (period > slash)) {
			value = value.substring(0, period);
		}
		return (value);
	}

	public static String getActionMappingURL(String action) {

		StringBuffer value = new StringBuffer();

		// Use our servlet mapping, if one is specified
		String servletMapping = Constant.SERVLET_MAPPING;
		if (servletMapping != null) {

			String queryString = null;
			int question = action.indexOf("?");
			if (question >= 0) {
				queryString = action.substring(question);
			}
			String actionMapping = getActionMappingName(action);
			if (servletMapping.startsWith("*.")) {
				value.append(actionMapping);
				value.append(servletMapping.substring(1));
			} else if (servletMapping.endsWith("/*")) {
				value.append(servletMapping.substring(0, servletMapping.length() - 2));
				value.append(actionMapping);
			} else if (servletMapping.equals("/")) {
				value.append(actionMapping);
			}
			if (queryString != null) {
				value.append(queryString);
			}
		}

		// Otherwise, assume extension mapping is in use and extension is
		// already included in the action property
		else {
			if (!action.startsWith("/")) {
				value.append("/");
			}
			value.append(action);
		}

		// Return the completed value
		return (value.toString());
	}

	public static String getActionMappingURLWithoutPrefix(String action) {

		StringBuffer value = new StringBuffer();

		// Use our servlet mapping, if one is specified
		String servletMapping = Constant.SERVLET_MAPPING;
		if (servletMapping != null) {

			String queryString = null;
			int question = action.indexOf("?");
			if (question >= 0) {
				queryString = action.substring(question);
			}
			String actionMapping = getActionMappingNameWithoutPrefix(action);
			if (servletMapping.startsWith("*.")) {
				value.append(actionMapping);
				value.append(servletMapping.substring(1));
			} else if (servletMapping.endsWith("/*")) {
				value.append(servletMapping.substring(0, servletMapping.length() - 2));
				value.append(actionMapping);
			} else if (servletMapping.equals("/")) {
				value.append(actionMapping);
			}
			if (queryString != null) {
				value.append(queryString);
			}
		}

		// Otherwise, assume extension mapping is in use and extension is
		// already included in the action property
		// else {
		// if (!action.startsWith("/")) {
		// value.append("/");
		// }
		// value.append(action);
		// }

		// Return the completed value
		return (value.toString());
	}

	public static String getActionMappingURL(String action, HttpServletRequest request) {

		StringBuffer value = new StringBuffer(request.getContextPath());

		// Use our servlet mapping, if one is specified
		String servletMapping = Constant.SERVLET_MAPPING;
		if (servletMapping != null) {
			String queryString = null;
			int question = action.indexOf("?");
			if (question >= 0) {
				queryString = action.substring(question);
			}
			String actionMapping = getActionMappingName(action);
			if (servletMapping.startsWith("*.")) {
				value.append(actionMapping);
				value.append(servletMapping.substring(1));
			} else if (servletMapping.endsWith("/*")) {
				value.append(servletMapping.substring(0, servletMapping.length() - 2));
				value.append(actionMapping);
			} else if (servletMapping.equals("/")) {
				value.append(actionMapping);
			}
			if (queryString != null) {
				value.append(queryString);
			}
		}

		// Otherwise, assume extension mapping is in use and extension is
		// already included in the action property
		else {
			if (!action.startsWith("/")) {
				value.append("/");
			}
			value.append(action);
		}

		// Return the completed value
		return (value.toString());
	}

	public static URL absoluteActionURL(HttpServletRequest request, String action) throws MalformedURLException {
		return new URL(RequestUtils.serverURL(request) + getActionMappingURL(action, request));
	}

	public static URL absoluteActionURLHtml(HttpServletRequest request, String url) throws MalformedURLException {
		return new URL(RequestUtils.serverURL(request) + request.getContextPath() + url);
	}

	public static String getBoardPrefixLine(int level, String txt) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < level * 2; i++) {
			sb.append(txt);
		}
		return sb.toString();
	}

	public static String[] filterUserSign(String[] sign, boolean useHTML, boolean useUBB, boolean useSmile) {
		for (int i = 0; i < sign.length; i++) {
			sign[i] = filterText(sign[i], useHTML, useUBB, useSmile);
		}
		return sign;
	}

	public static String filterText(String sign, boolean useHTML, boolean useUBB, boolean useSmile) {
		if (!useHTML) {
			// sign = TagUtils.getInstance().filter(sign);
			//sign = TextUtils.htmlEncode(sign);
		}
		if (useUBB) {
			sign = getUBB2HTML(sign);
		}
		if (useSmile) {
			sign = replaceSmile(sign);
		}
		sign = sign.replaceAll("\n", "<BR/>");
		sign = filterScript(sign);
		return sign;
	}

	public static String filterScript(String txt) {
		return txt.replaceAll("[Ss][Cc][Rr][Ii][Pp][Tt]", "s.c.r.i.p.t");
	}

	public static String replaceSmile(String txt) {
		if (txt != null) {
			return txt.replaceAll("\\{(\\d{1,2})\\}", "<img src=\"images/smile/$1.gif\" alt=\"smile\"/>");
		} else {
			return "";
		}
	}

	public static String getUBB2HTML(String txt) {
		if (txt != null) {
			AutoFilter af = new AutoFilter(txt);
			txt = af.getFilteredStr();
		}
		return txt;
	}

	public static int getStrLength(String txt, String charset) {
		try {
			return txt.getBytes(charset).length;
		} catch (UnsupportedEncodingException ex) {
			return txt.length();
		}
	}

	public static int getSysCharsetStrLength(String txt) {
		try {
			return txt.getBytes(Constant.CHARSET).length;
		} catch (UnsupportedEncodingException ex) {
			return txt.length();
		}
	}

	public static boolean int2boolean(int value) {
		if (value == 1) {
			return true;
		}
		if (value == 0) {
			return false;
		}
		return false;
	}

	public static int boolean2int(boolean value) {
		if (value) {
			return 1;
		} else {
			return 0;
		}
	}

	public static String getClassName(String className, String userID) {
		int num = Math.abs(userID.hashCode());
		className = Constant.BEANPERFIX + className + (num % 10);
		return className;
	}

	public static String getClassName(String className, String userID, int modnum) {
		int num = Math.abs(userID.hashCode());
		className = Constant.BEANPERFIX + className + (num % modnum);
		return className;
	}

	public static String getClassName(String className, long bid, int modnum) {
		className = Constant.BEANPERFIX + className + (bid % modnum);
		return className;
	}

	public static int getTableID(String userID, int num) {
		int absNum = Math.abs(userID.hashCode());
		return (int) (absNum % num);
	}

	public static int getTableID(long bid, int num) {
		return (int) (bid % num);
	}

	public static boolean isAllowPicFile(String fileName) {
		String[] filExts = { "jpg", "jpeg", "gif" };
		return FilenameUtils.isExtension(fileName, filExts);
	}

	public static boolean isPicFile(String fileName) {
		String[] filExts = { "jpg", "jpeg", "gif", "png", "bmp" };
		return FilenameUtils.isExtension(fileName, filExts);
	}

	public static boolean isFlashFile(String fileName) {
		return FilenameUtils.isExtension(fileName, "swf");
	}

	public static long getOnlineInTime() {
		return System.currentTimeMillis() - 180000;
	}

	public static boolean isTodayTime(long atime) {
		Calendar cld = Calendar.getInstance();
		// cld.setTime(new Date());
		int year = cld.get(Calendar.YEAR);
		int month = cld.get(Calendar.MONTH);
		int day = cld.get(Calendar.DAY_OF_MONTH);
		Calendar todaycld = Calendar.getInstance();
		todaycld.set(year, month, day, 0, 0, 0);
		if (atime >= todaycld.getTime().getTime()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isLastdayTime(long atime) {
		Calendar cld = Calendar.getInstance();
		// cld.setTime(new Date());
		cld.add(Calendar.DAY_OF_MONTH, -1);
		int year = cld.get(Calendar.YEAR);
		int month = cld.get(Calendar.MONTH);
		int day = cld.get(Calendar.DAY_OF_MONTH);
		Calendar lastdaycld = Calendar.getInstance();
		lastdaycld.set(year, month, day, 0, 0, 0);
		if (atime >= lastdaycld.getTime().getTime()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isTodayTime(long atime, String timeZone) {
		return isTodayTime(new Date(atime), timeZone);
	}

	public static boolean isYesterdayTime(long atime, String timeZone) {
		return isYesterdayTime(new Date(atime), timeZone);
	}

	public static boolean isTodayTime(Date atime, String timeZone) {
		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
		cld.set(Calendar.MILLISECOND, 0);
		int nowyear = cld.get(Calendar.YEAR);
		int nowmonth = cld.get(Calendar.MONTH);
		int nowday = cld.get(Calendar.DAY_OF_MONTH);

		Calendar c = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
		c.setTime(atime);
		c.set(Calendar.MILLISECOND, 0);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		// int second = c.get(Calendar.SECOND);

		if (year == nowyear && month == nowmonth && day == nowday) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isYesterdayTime(Date atime, String timeZone) {
		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
		cld.set(Calendar.MILLISECOND, 0);
		cld.add(Calendar.DAY_OF_MONTH, -1);
		int yyear = cld.get(Calendar.YEAR);
		int ymonth = cld.get(Calendar.MONTH);
		int yday = cld.get(Calendar.DAY_OF_MONTH);

		Calendar c = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
		c.setTime(atime);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		// int second = c.get(Calendar.SECOND);

		if (year == yyear && month == ymonth && day == yday) {
			return true;
		} else {
			return false;
		}
	}

	public static String formatDateTime(Date date, String format) {
		SimpleDateFormat outFormat = new SimpleDateFormat(format);
		return outFormat.format(date);
	}

	public static String formatDateTime(Date date, String format, String timeZone) {
		// Calendar c = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
		// c.setTime(date);
		// c.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat outFormat = new SimpleDateFormat(format);

		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		c.set(Calendar.MILLISECOND, 0);
		c.setTimeZone(TimeZone.getTimeZone(timeZone));
		return outFormat.format(c.getTime());
	}

	public static String getFileTypeIcon(String fileExt) {
		String fileTypeIcon = (String) Constant.ICON_MAP.get(fileExt);
		if (fileTypeIcon == null) {
			fileTypeIcon = "default.icon.gif";
		}
		return fileTypeIcon;
	}

	public static String getSpeShortString(String s, int len, String fillstr) {
		int ilen = Math.max(0, len - fillstr.length());
		char ch = ' ';
		int reallen = 0;
		for (int i = 0; i < s.length(); i++) {
			ch = s.charAt(i);
			if (((int) ch > 32) && ((int) ch < 128)) {
				reallen++;
			} else {
				reallen += 2;
			}
		}
		if (reallen <= len) {
			return s;
		}
		StringBuffer buf = new StringBuffer();
		reallen = 0;
		for (int i = 0; i < s.length(); i++) {
			ch = s.charAt(i);
			buf.append(ch);
			if (((int) ch > 32) && ((int) ch < 128)) {
				reallen++;
			} else {
				reallen += 2;
			}
			if (reallen >= ilen) {
				return buf.append(fillstr).toString();
			}
		}
		return buf.toString();
	}

	public static List array2List(String[] ids) {
		List<String> l = new ArrayList<String>();
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				l.add(ids[i]);
			}
		}
		return l;
	}

	public static Locale getLocale(String localeStr) {
		String[] locale_strs = localeStr.split("_");
		if (locale_strs != null && locale_strs.length == 2) {
			return new Locale(locale_strs[0], locale_strs[1]);
		} else {
			return new Locale("zh", "CN");
		}
	}

	public static int id2Int(String id) {
		char[] chars = id.toCharArray();
		int total = 0;
		for (int i = 0; i < chars.length; i++) {
			total += Character.digit(chars[i], 16);
		}
		return total;
	}

	public static List<String> getArchivesMonths(long bid) {
		List<String> l = new ArrayList<String>();
		File archivesMonthsFile = new File(Constant.ROOTPATH + "archives/archivesmonths-" + bid + ".txt");
		String month = "";
		try {
			month = FileUtils.readFileToString(archivesMonthsFile, Constant.CHARSET);
		} catch (IOException e) {
			return l;
		}
		String[] months = month.split(",");
		if (months.length > 0) {
			for (int i = (months.length - 1); i >= 0; i--) {
				if (StringUtils.isNotBlank(months[i])) {
					l.add(months[i]);
				}
			}
		}
		return l;
	}

	public static boolean ArchivesMonthInFile(long bid, String month) {
		List<String> l = getArchivesMonths(bid);
		for (String m : l) {
			if (m.equalsIgnoreCase(month)) {
				return true;
			}
		}
		return false;
	}

	public synchronized static void writeMonthToFile(long bid, String month) {
		File archivesMonthsFile = new File(Constant.ROOTPATH + "archives/archivesmonths-" + bid + ".txt");
		String monthText = "";
		try {
			monthText = FileUtils.readFileToString(archivesMonthsFile, Constant.CHARSET);
		} catch (IOException e) {
			monthText = "";
		}
		if (StringUtils.isBlank(monthText)) {
			monthText = month;
		} else {
			if (monthText.endsWith(",")) {
				monthText = monthText + month;
			} else {
				monthText = monthText + "," + month;
			}
		}
		try {
			FileUtils.writeStringToFile(archivesMonthsFile, monthText, Constant.CHARSET);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getArchivesPostMainListPath(long bid, String month) {
		StringBuffer sb = new StringBuffer();
		sb.append(Constant.ROOTPATH);
		sb.append(getArchivesPostMainListWebPath(bid, month));
		File ft = new File(sb.toString());
		if (!ft.exists()) {
			ft.mkdirs();
		}
		return sb.toString();
	}

	public static String getArchivesPostMainListWebPath(long bid, String month) {
		StringBuffer sb = new StringBuffer();
		sb.append("archives/");
		sb.append(bid);
		sb.append("/");
		sb.append(month);
		sb.append("/");
		return sb.toString();
	}

	public static String getArchivesPostTopicPath(long bid, String month, long posttime) {
		StringBuffer sb = new StringBuffer();
		sb.append(Constant.ROOTPATH);
		sb.append(getArchivesPostMainListWebPath(bid, month));
		sb.append(Util.formatDate4(new Date(posttime)));
		sb.append("/");
		sb.append((posttime % 100));
		sb.append("/");
		File ft = new File(sb.toString());
		if (!ft.exists()) {
			ft.mkdirs();
		}
		return sb.toString();
	}

	public static int getStringHashCode(String txt) {
		int t = 0;
		if (txt != null) {
			char[] chars = txt.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				t = t + (int) chars[i];
			}
		}
		return t;
	}

	/**
	 * 把图片印刷到图片上
	 * 
	 * @param pressImg
	 *            -- 水印文件
	 * @param targetImg
	 *            -- 目标文件
	 */
	public final static void pressImage(String pressImg, String targetImg) {
		try {
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);

			// 水印文件
			File _filebiao = new File(pressImg);
			Image src_biao = ImageIO.read(_filebiao);
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			
			if(wideth > 200 && height > 75){//目标图片的宽度小于200像素或者高度小于75,不做加水印的处理
				g.drawImage(src_biao, wideth - wideth_biao, height - height_biao, wideth_biao, height_biao, null);
			}
			
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
