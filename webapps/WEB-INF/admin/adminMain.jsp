<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>社区管理后台</title>
</head>

<s:url action="adminLeft" id="adminLeftUrl"></s:url>
<s:url id="adminIndexUrl" value="/adminIndex.jsp"></s:url>

<frameset rows="*" cols="198,*" framespacing="0" frameborder="no" border="0">
  <frame src="${adminLeftUrl}" name="bbscs_admin_leftFrame" frameborder="no" scrolling="yes" noresize="noresize">
  <frame src="${adminIndexUrl}" name="bbscs_admin_mainFrame" frameborder="no" scrolling="yes" noresize="noresize">
</frameset>
<noframes><body>
</body></noframes>
</html>
