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
    <td width="80%">
      <div align="center">角色名称</div>
    </td>
    <td width="10%">
      <div align="center">修改</div>
    </td>
    <td width="10%">
      <div align="center">删除</div>
    </td>
  </tr>
  <s:iterator value="%{roleList}" id="role">
    <tr>
      <td>
        <strong><s:property value="#role.roleName"/></strong>
      </td>
      <td>
        <div align="center"><a href="javascript:;" onclick="editRole('<s:property value="#role.id"/>');">修改</a></div>
      </td>
      <td>
        <div align="center"><a href="javascript:;" onclick="delRole('<s:property value="#role.id"/>');">删除</a></div>
      </td>
    </tr>
  </s:iterator>
</table>