<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path=request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>safe下重新生成user信息的缓存文件</title>
<script type="text/javascript">
function toCreateCommit(){
	document.getElementById("toCreateform").submit();
}
</script>
</head>
<body>
<form action="<%=path%>/toCreate.bbscs" enctype="multipart/form-data" method="post" name="toCreateform" id="toCreateform">
<div><s:actionerror theme="bbscs0"/></div>
<div>
	<select>
		<option>safe下user信息生成</option>
	</select>
	<input type="button" value="生成" onclick="toCreateCommit();">
</div>
<input onclick="javascript:history.go(-1);" type="button" value="返回"/>
</form>
</body>
</html>