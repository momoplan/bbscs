<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>打开/关闭论坛</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<p>&nbsp;</p>
<s:form action="adminOpenSet">
<s:hidden name="action"></s:hidden>
<table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
  <tr>
    <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
      <tr>
        <td colspan="2"><strong>打开/关闭论坛</strong></td>
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
      <tr bgcolor="#F0F0F0">
        <td colspan="2"><strong>论坛开放</strong></td>
        </tr>
      <tr>
        <td width="60%" valign="top">有时您可能需要关闭论坛来进行维护或者升级等，这时候访问论坛的用户将仅会看到一条论坛暂时关闭的信息。</td>
        <td width="40%" valign="top">
          <s:radio list="radioYesNoList" name="isOpen" listKey="key" listValue="value"></s:radio>
        </td>
      </tr>
      <tr bgcolor="#F0F0F0">
        <td colspan="2"><strong>论坛关闭原因</strong></td>
        </tr>
      <tr>
        <td valign="top">论坛关闭时的提示信息。</td>
        <td valign="top">
          <s:textarea id="closeReson" name="closeReson" cols="40" rows="5" cssClass="textarea1"></s:textarea>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <div align="center">
            <s:submit cssClass="button2" value="保存"></s:submit>
          </div>
        </td>
        </tr>
    </table></td>
  </tr>
</table>
</s:form>

</body>
</html>
