<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限配置</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>

<body>
<p>&nbsp;</p>
<s:form action="adminPermission">
<s:hidden name="action"></s:hidden>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2">权限配置</td>
        </tr>
        <tr>
        <td colspan="2">
        <s:actionerror theme="bbscs0"/>
        </td>
      </tr>
      <tr>
        <td colspan="2">
        <s:actionmessage theme="bbscs0"/>
        </td>
      </tr>
        <tr class="td1">
          <td width="60%">权限编号</td>
          <td width="40%">
            <s:textfield name="id" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td>权限名称</td>
          <td>
            <s:textfield name="permissionName" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td>资源(URI)</td>
          <td>
            <s:textfield name="resource" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td>Action</td>
          <td>
            <s:textfield name="actionName" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td>权限类型</td>
          <td>
            <s:select list="permissionTypeValues" name="typeID" id="typeID" listKey="key" listValue="value" cssClass="select1"></s:select>
          </td>
        </tr>
        <tr>
          <td colspan="2"><div align="center">
            <s:submit cssClass="button2" value="保存"></s:submit>
          </div></td>
          </tr>
      </table></td>
    </tr>
  </table>
</s:form>
<p>&nbsp;</p>
</body>
</html>