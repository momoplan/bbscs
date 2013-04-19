<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>帖子打包发送</title>
<style type="text/css">
<!--
body {
	font-family: Verdana, Tahoma, "宋体";
	font-size: 14px;
        margin-top: 0px;
}
table {
	font-family: Verdana, Tahoma, "宋体";
	font-size: 14px;
}
-->
</style>
</head>

<body>
<table width="98%" border="0" align="center" cellpadding="5" cellspacing="0">
  <tr>
    <td><strong>${userName}举报以下帖子内容有不良信息</strong></td>
  </tr>
  <tr>
    <td>此帖地址：<a href="${url}" target="_blank">${url}</a></td>
  </tr>
  <tr>
    <td>${title}</td>
  </tr>
  <tr>
    <td>${detail}</td>
  </tr>
</table>
</body>
</html>
