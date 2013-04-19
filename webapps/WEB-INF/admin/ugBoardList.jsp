<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<table width="100%" border="0" cellpadding="3" cellspacing="0" class="table6">
  <s:iterator id="ug" value="%{ugList}">
  <tr>
    <td>·<s:property value="#ug.groupName"/>
      [<a href="javascript:;" onclick="boardPermissionLoad('<s:property value="%{bid}"/>','<s:property value="#ug.id"/>');">配置权限</a>]
    </td>
  </tr>
  </s:iterator>
  <tr>
    <td align="right"><a href="javascript:;" onclick="closeBgs('<s:property value="%{bid}"/>');">X</a></td>
  </tr>
</table>