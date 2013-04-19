<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>输入密码</title>
<link href="css/css1.css" rel="stylesheet" type="text/css" />
</head>

<body>
<p>&nbsp;</p>
<s:form action="boardPasswd">
<s:hidden name="action" value="pass"></s:hidden>
<s:hidden name="bid" value="%{bid}"></s:hidden>
<table width="300" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
  <tr>
    <td><table width="100%" border="0" cellpadding="5" cellspacing="0">
      <tr>
        <td colspan="2"><div align="center"><strong>该版区需要访问密码</strong></div></td>
        </tr>
      <tr>
        <td colspan="2"><s:actionerror theme="bbscs0"/></td>
        </tr>
      <tr>
        <td width="38%"><div align="right"><strong>请输入密码</strong>:</div></td>
        <td width="62%"><s:password id="passwd" name="passwd" cssClass="input2" size="20"></s:password></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><input name="Submit" type="submit" class="button1" value="提交" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</s:form>
</body>
</html>