<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><s:text name="cpasswd.title"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css" />
</head>

<body>
<s:form action="cpasswd">
<s:hidden name="action"></s:hidden>
<table width="95%" border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
  <tr>
    <td><table width="100%" border="0" cellpadding="5" cellspacing="0">
      <tr>
        <td colspan="2"><strong class="font1"><s:text name="cpasswd.title"/></strong></td>
        </tr>
      <tr>
        <td colspan="2"><s:actionerror theme="bbscs0"/></td>
      </tr>
      <tr>
        <td colspan="2">
          <s:actionmessage theme="bbscs0"/>
        </td>
      </tr>
      <tr>
        <td width="30%"><div align="right"><s:text name="cpasswd.cpass"/></div></td>
        <td width="70%">
        <s:password id="oldpasswd" name="oldpasswd" cssClass="input2" size="40" maxlength="20"></s:password>
        </td>
      </tr>
      <tr>
        <td><div align="right"><s:text name="cpasswd.new"/></div></td>
        <td>
        <s:password id="newpasswd1" name="newpasswd1" cssClass="input2" size="40" maxlength="20"></s:password>
        </td>
      </tr>
      <tr>
        <td><div align="right"><s:text name="cpasswd.newre"/></div></td>
        <td>
        <s:password id="newpasswd2" name="newpasswd2" cssClass="input2" size="40" maxlength="20"></s:password>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>
          <s:submit value="%{getText('cpasswd.change')}" cssClass="button1"></s:submit>
        </td>
      </tr>
    </table></td>
  </tr>
</table>
</s:form>
</body>
</html>