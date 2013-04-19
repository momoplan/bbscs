<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户激活</title>
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
    <td><strong>非常感谢您成为${website}的用户</strong></td>
  </tr>
  <tr>
    <td>您的帐户尚处于未认证的状态，只要你点击下面的链接，即可通过认证</td>
  </tr>
  <tr>
    <td><a href="${forumurl}/reg/validateuser.bbscs?userName=${userName}&validateCode=${validateCode}" target="_blank">${forumurl}/reg/validateuser.bbscs?userName=${userName}&validateCode=${validateCode}</a></td>
  </tr>
</table>
</body>
</html>