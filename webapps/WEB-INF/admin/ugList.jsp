<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr class="td1">
    <td width="25%">
      <div align="center">用户组名</div>
    </td>
    <td width="45%">
      <div align="center">描述</div>
    </td>
    <td width="15%">
      <div align="center">修改</div>
    </td>
    <td width="15%">
      <div align="center">删除</div>
    </td>
  </tr>
  <s:iterator id="ug" value="%{ugList}">
  <tr>
    <td><strong><s:property value="#ug.groupName"/></strong></td>
    <td><s:property value="#ug.groupDesc"/></td>
    <td>
      <div align="center"><a href="javascript:;" onclick="editUg('<s:property value="#ug.id"/>');">修改</a></div>
    </td>
    <td>
      <div align="center"><a href="javascript:;" onclick="delUg('<s:property value="#ug.id"/>');">删除</a></div>
    </td>
  </tr>
  </s:iterator>
</table>