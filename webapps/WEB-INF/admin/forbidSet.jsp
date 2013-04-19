<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>用户禁止选项</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<p>&nbsp;</p>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td><strong>用户禁止选项</strong></td>
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
      </table>
      <s:form action="adminForbidSet">
      <s:hidden name="action"></s:hidden>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td colspan="2" class="td1">
      <strong>打开禁止选项</strong>
    </td>
  </tr>
  <tr>
    <td width="60%">可以阻止某个 IP 和 Email 地址注册以及在论坛中发帖。</td>
    <td width="40%">

      <s:radio list="radioYesNoList" name="useForbid" listKey="key" listValue="value"></s:radio>
    </td>
  </tr>
  <tr>
    <td colspan="2" class="td1">
      <strong>禁止的 IP 地址</strong>
    </td>
  </tr>
  <tr>
    <td valign="top">禁止的IP地址列表: 您可以禁止某个 IP 地址的用户注册和发表信息。输入完整的IP地址 (比如 243.21.31.7)，或者是一部分的 IP 地址 (比如 243.21.31)。论坛会匹配每个您输入的 IP 地址的开头几位，如果您输入 IP 地址 243.21.31，IP 地址为 243.21.31.5 的用户将不能注册；同样的，如果您输入的 IP 地址为 243.21，那么 IP 地址为 243.21.3.44 的用户就不能注册。因此请小心使用 IP 禁止列表。每个IP地址间以逗号间隔。被禁止的地址的用户将无法注册和发表帖子。</td>
    <td valign="top">
      <s:textarea id="forbidIP" name="forbidIP" cols="40" rows="10" cssClass="textarea1"></s:textarea>
    </td>
  </tr>
  <tr>
    <td colspan="2" class="td1">
      <strong>禁止的 Email 地址</strong>
    </td>
  </tr>
  <tr>
    <td valign="top">Email 禁止列表: 要在论坛禁止特定的 Email 地址，只要输入完整的Email地址 (比如，user@example.com)；要禁止来自某个主机的 Email 地址，比如 hotmail，只要简单输入主机名就可以了 (例如 hotmail.com)，这将禁止所有使用 hotmail 信箱地址的用户注册，用户也无法将自己的 Email 修改成 hotmail 的。请使用逗号分隔信箱地址。</td>
    <td valign="top">
      <s:textarea id="forbidEmail" name="forbidEmail" cols="40" rows="10" cssClass="textarea1"></s:textarea>
    </td>
  </tr>
  <tr>
    <td colspan="2">
      <div align="center">
        <s:submit cssClass="button2" value="保存"></s:submit>
      </div>
    </td>
  </tr>
</table>
</s:form>

      </td>
    </tr>
  </table>
<p>&nbsp;</p>
</body>
</html>
