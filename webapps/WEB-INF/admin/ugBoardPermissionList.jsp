<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<s:form action="adminBoardUg">
<s:hidden name="action"></s:hidden>
<s:hidden id="bid" name="bid"></s:hidden>
<s:hidden id="gid" name="gid"></s:hidden>
<table width="100%" border="0" cellpadding="5" cellspacing="0" class="table6">
  <tr>
    <td colspan="2">
      <strong>
        权限配置
        [<s:property value="%{board.boardName}"/>]
        [<s:property value="%{userGroup.groupName}"/>]
      </strong>
    </td>
  </tr>
  <s:checkboxlist list="permissionValues" listKey="key" listValue="value" name="permissions" theme="bbscs1"></s:checkboxlist>
  <tr>
    <td colspan="2">
      <input type="button" name="savePermissions" value="保存" class="button1" onclick="boardPermissionSave();"/>
      <input type="button" name="closeBmPage" value="关闭" class="button1" onclick="cloesBoardMasterPage();"/>
    </td>
  </tr>
</table>
</s:form>