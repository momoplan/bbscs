<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@include file="top.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/css.css" rel="stylesheet" type="text/css"/>
<title><s:text name="error"></s:text></title>
</head>
<body style="background:#eeeeee">
<div class="main_body1">
	<div style="background:url(../images/ruyiluntan.gif) no-repeat center top; height:57px; margin-top:50px"></div>
	<div style="width:498px; height:165px; background:url(../images/error_bg.gif) no-repeat; margin:50px auto 0 auto">
		<h1 style="margin:0 auto; padding-left:80px; padding-right:80px; font-size:14px; line-height:30px; font-weight:bold; padding-top:30px; color:#666">

			<div id="error">
				<s:actionerror theme="bbscs0"/>
			</div>
		</h1>
		<h3 style="padding-left:80px; font-size:12px; font-weight:normal; padding-top:20px">
		<font style="float:left; line-height:26px">您可以：</font>
		<a href="http://bbs.ruyicai.com" class="error_btn1"><s:text name="bbscs.back"></s:text></a>
		</h3>
	</div>
</div>
</body>
</html>