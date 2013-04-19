<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>认证成功</title>
<link href="css/css1.css" rel="stylesheet" type="text/css">
</head>

<body>
<s:url action="login" id="loginurl"></s:url>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>

<table width="600" border="0" align="center" cellpadding="10">
  <tr>
    <td width="216"><div align="right"><img src="images/standard_msg_ok_big.gif" alt="" width="64" height="64"></div></td>
    <td width="338" class="font1">您的帐号已经通过认证，可以登录社区！</td>
  </tr>
  <tr>
    <td colspan="2"><div align="center"><s:a href="%{loginurl}">返回</s:a></div></td>
  </tr>
</table>
</body>
</html>
