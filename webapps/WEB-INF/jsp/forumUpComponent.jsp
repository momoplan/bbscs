<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<script language="JavaScript" type="text/javascript">
window.parent.OnUploadCompleted("<s:property value="%{bid}"/>","<s:property value="%{id}"/>","<s:property value="%{ajaxCodeid}"/>","<s:property value="%{ajaxMsg}"/>");
</script>
