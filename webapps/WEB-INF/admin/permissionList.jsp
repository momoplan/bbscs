<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限列表</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>

<body>
<p>&nbsp;</p>
<table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
  <tr>
    <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
      <tr class="td1">
        <td width="10%">编号</td>
        <td width="30%">权限名称</td>
        <td width="30%">资源</td>
        <td width="30%">Action</td>
      </tr>
      <s:iterator id="permission" value="%{permissionList}">
      <tr>
        <td><s:property value="#permission.id"/></td>
        <td>
        <s:url action="adminPermission?action=edit" id="adminPermissionEditUrl">
        <s:param name="id" value="#permission.id"/>
        </s:url>
        <s:a href="%{adminPermissionEditUrl}"><s:property value="#permission.permissionName"/></s:a>
        </td>
        <td><s:property value="#permission.resource"/></td>
        <td><s:property value="#permission.action"/></td>
      </tr>
      </s:iterator>
      <tr>
    <td colspan="4">
    <s:url action="adminPermission?action=add" id="adminPermissionNewUrl"></s:url>
    <s:a href="%{adminPermissionNewUrl}">增加权限</s:a>
    </td>
  </tr>
    </table></td>
  </tr>
</table>
<p>&nbsp;</p>
</body>
</html>