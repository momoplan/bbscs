<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<html>
<head>
<title>
等待审核
</title>
</head>
<body bgcolor="#ffffff">
<div>您的帖子已经发出，需要等待管理员的审核！</div>
<div>
<s:url action="forum?action=index" id="furl">
<s:param name="bid" value="%{bid}"/>
</s:url>
<a href="${furl}">返回讨论区</a>
</div>
</body>
</html>