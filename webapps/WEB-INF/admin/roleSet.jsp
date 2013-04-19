<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<s:form action="adminRoleSet">
<s:hidden name="action"></s:hidden>
<s:hidden id="id" name="id"></s:hidden>
<table width="100%" border="0" cellpadding="5" cellspacing="0" class="table6">
    <tr>
      <td colspan="2">
        <div id="resultMegs"/>
      </td>
    </tr>
    <tr>
      <td width="15%">角色名称</td>
      <td width="85%">
        <s:textfield id="roleName" name="roleName" cssClass="input2" size="40" onkeypress="return false;"></s:textfield>
      </td>
    </tr>

    <tr>
      <td valign="top">权限</td>
      <td>
        <table width="100%" border="0" cellpadding="3" cellspacing="0">
          <s:checkboxlist list="permissionValues" listKey="key" listValue="value" name="permissions" theme="bbscs1"></s:checkboxlist>
        </table>
      </td>
    </tr>
    <tr>
      <td valign="top">&nbsp;</td>
      <td>
        <s:if test="%{action=='addsave'}">
        <input type="button" name="saveRole" value="保存" class="button1" onclick="addRole();"/>
        </s:if>
        <s:if test="%{action=='editsave'}">
        <input type="button" name="saveRole" value="保存" class="button1" onclick="editDoRole();"/>
        </s:if>
        <input type="button" name="clostButton" value="关闭" class="button1" onclick="cloesRoleSetPage();"/>
      </td>
    </tr>
</table>
</s:form>