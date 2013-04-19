<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<table width="100%" border="0" cellpadding="2" cellspacing="0">
  <tr>
    <td><strong><s:text name="forum.summary"/></strong></td>
  </tr>
  <tr>
    <td><bbscs:forum forumValue="forum" type="detailsummary"/></td>
  </tr>
  <tr>
    <td><div align="right">[<a href="javascript:;" onclick="closeSummary('<s:property value="%{forum.id}"/>');">关闭</a>]</div></td>
  </tr>
</table>