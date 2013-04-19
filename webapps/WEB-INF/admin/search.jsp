<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>用户管理</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/xmlHttpRequest.js"></script>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script language="JavaScript" type="text/javascript">
<!--
function searchUid() {
  document.adminUserSearch.action.value = "suid";
  document.adminUserSearch.submit();
}

function searchName() {
  document.adminUserSearch.action.value = "sname";
  document.adminUserSearch.submit();
}
//-->
</script>
</head>
<body>
<p>&nbsp;</p>
<s:form action="adminUserSearch" name="adminUserSearch">
<s:hidden name="action"></s:hidden>
<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
  <tr>
    <td>
      <table width="100%" border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2">
            <strong>用户管理</strong>
          </td>
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

        <tr>
          <td width="15%">用户ID</td>
          <td width="85%">
            <s:textfield id="id" name="id" cssClass="input2" size="45"></s:textfield>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>
            <input type="button" name="suidButton" value="查询用户" class="button1" onclick="searchUid();"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>用户名</td>
          <td>
            <s:textfield id="userName" name="userName" cssClass="input2" size="45"></s:textfield>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>
            <input type="button" name="snameButton" value="查询用户" class="button1" onclick="searchName();"/>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</s:form>
<p>&nbsp;</p>
</body>
</html>