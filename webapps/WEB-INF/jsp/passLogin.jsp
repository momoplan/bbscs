<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><bbscs:webinfo type="forumname"/> - <s:text name="login.title"/><bbscs:webinfo type="poweredby"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css" />
</head>

<body>
<p>&nbsp;</p>

<form name="loginForm" id="loginForm" method="post" action="<s:property value="%{actionUrl}"/>" target="_top">
<input name="tourl" type="hidden" value="<s:property value="tourl"/>" />
  <table width="400"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="70%"  border="0" align="center" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2"><strong><s:text name="login.title"/>:<s:text name="login.input"/></strong></td>
          </tr>
        <tr>
		<td colspan="2">
		<s:actionerror theme="bbscs0"/>
		</td>
		</tr>
        <tr>
          <td width="34%"><div align="right"><s:text name="login.username"/>:</div></td>
          <td width="66%"><input name="username" type="text" class="input2" size="15" maxlength="20" /></td>
        </tr>
        <tr>
          <td><div align="right"><s:text name="login.passwd"/>:</div></td>
          <td><input name="passwd" type="password" class="input2" size="15" maxlength="20" /></td>
        </tr>
        <tr>
          <td colspan="2"><div align="center">
              <s:submit value="%{getText('bbscs.button.login')}" cssClass="button1"></s:submit>
              <s:reset value="%{getText('bbscs.botton.reset')}" cssClass="button1"></s:reset>
          </div></td>
          </tr>
      </table></td>
    </tr>
  </table>
</form>
<p>&nbsp;</p>
<div align="center">
  <s:url action="input" id="regurl" namespace="/reg"></s:url>
		[<a href="${regurl}"><s:text name="reg.title"/></a>]
		<s:if test="%{urlRewrite}">
		[<a href="main.html"><s:text name="login.guest"/></a>]
		</s:if>
		<s:else>
		<s:url action="main" id="mainurl"></s:url>
		[<a href="${mainurl}"><s:text name="login.guest"/></a>]
		</s:else>
</div>
</body>
</html>
