<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>在线时长设定</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>

<body>
<p>&nbsp;</p>
<s:form action="adminUOTimeSet">
<s:hidden name="action"></s:hidden>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2"><strong>在线时长设定</strong></td>
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
          <td colspan="2" class="td1"><strong>用户在线时长设定</strong></td>
          </tr>
        <tr>
          <td width="60%">设置用户累计在线多长时间计入用户信息表和在线用户表，单位秒。</td>
          <td width="40%">
            <s:textfield id="userOnlineTime" name="userOnlineTime" cssClass="input2" size="40"></s:textfield>
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