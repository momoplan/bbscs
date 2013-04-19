<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Email 选项</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<p>&nbsp;</p>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td><strong>Email 选项</strong></td>
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
      <s:form action="adminEmailSet">
      <s:hidden name="action"></s:hidden>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
    <tr>
      <td colspan="2" class="td1">
        <strong>使用 Email 功能?</strong>
      </td>
    </tr>
    <tr>
      <td width="60%">打开 Email 发送功能。
        <br/>
        例如用户发送举报邮件、给用户发送回复通知等。
      </td>
      <td width="40%" valign="top">
        <s:radio list="radioYesNoList" name="useEmail" listKey="key" listValue="value"></s:radio>
      </td>
    </tr>
    <tr>
      <td colspan="2" class="td1">
        <strong>SMTP服务器是否需要验证</strong>
      </td>
    </tr>
    <tr>
      <td>如果使用的SMTP服务器需要用户验证，请选择“是”</td>
      <td>
        <s:radio list="radioYesNoList" name="smtpAuth" listKey="key" listValue="value"></s:radio>
      </td>
    </tr>
    <tr>
      <td colspan="2" class="td1">
        <strong>SMTP服务器主机/IP</strong>
      </td>
    </tr>
    <tr>
      <td>请在这里填写SMTP服务器的主机名或者IP</td>
      <td>
        <s:textfield name="smtpServer" id="smtpServer" cssClass="input2" size="40"></s:textfield>
      </td>
    </tr>
    <tr>
      <td colspan="2" class="td1">
        <strong>SMTP服务器端口</strong>
      </td>
    </tr>
    <tr>
      <td>请在这里填写SMTP服务器的端口</td>
      <td>
        <s:textfield name="smtpPort" id="smtpPort" cssClass="input2" size="40"></s:textfield>
      </td>
    </tr>
    <tr>
      <td colspan="2" class="td1">
        <strong>SMTP服务器用户名</strong>
      </td>
    </tr>
    <tr>
      <td>如果使用的SMTP服务器需要认证，请在这里填写其用户名</td>
      <td>
        <s:textfield name="smtpUser" id="smtpUser" cssClass="input2" size="40"></s:textfield>
      </td>
    </tr>
    <tr>
      <td colspan="2" class="td1">
        <strong>SMTP服务器密码</strong>
      </td>
    </tr>
    <tr>
      <td>如果使用的SMTP服务器需要认证，请在这里填写其密码</td>
      <td>
        <s:password id="smtpPasswd" name="smtpPasswd" cssClass="input2" size="40" showPassword="true"></s:password>
      </td>
    </tr>
    <tr>
      <td colspan="2" class="td1">
        <strong>发件人邮件地址</strong>
      </td>
    </tr>
    <tr>
      <td>显示在接受者邮件中的“发件人”，注意邮件服务器要求发件人与SMTP用户必须一致。</td>
      <td valign="top">
        <s:textfield name="senderEmail" id="senderEmail" cssClass="input2" size="40"></s:textfield>
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