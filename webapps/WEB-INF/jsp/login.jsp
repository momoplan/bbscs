<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%@include file="top.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><bbscs:webinfo type="forumname"/> - <s:text name="login.title"/><bbscs:webinfo type="poweredby"/></title>
		<bbscs:webinfo type="meta"/>
		<link href="<%=path%>/css/css1.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<p>&nbsp;</p>
		<s:form action="login" target="_top">
			<s:hidden name="action"></s:hidden>
			<s:hidden name="tourl" value="%{tourl}"></s:hidden>
			<table width="400" border="0" align="center" cellpadding="10"
				cellspacing="0" class="table1">
				<tr>
					<td>
						<table width="70%" border="0" align="center" cellpadding="5"
							cellspacing="0">
							<tr>
								<td colspan="2">
									<strong><s:text name="login.title"/>:<s:text name="login.input"/></strong>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<s:actionerror theme="bbscs0"/>
								</td>
							</tr>
							<tr>
								<td width="34%">
									<div align="right">
										<s:text name="login.username"/>:
									</div>
								</td>
								<td width="66%">
									<s:textfield name="username" id="username" cssClass="input2" size="15" maxlength="20"></s:textfield>
								</td>
							</tr>
							<tr>
								<td>
									<div align="right">
										<s:text name="login.passwd"/>:
									</div>
								</td>
								<td>
									<s:password name="passwd" id="passwd" cssClass="input2" size="15" maxlength="20"></s:password>
								</td>
							</tr>
							<s:if test="%{useAuthCode}">
							<tr>
								<td>
									<div align="right">
										<s:text name="login.authcode"/>:
									</div>
								</td>
								<td>
									<s:textfield id="authCode" name="authCode" size="5" maxlength="4" cssClass="input2"></s:textfield>
									<img alt="<s:text name="login.authcode"/>" src="authimg" align="absmiddle" />
								</td>
							</tr>
							</s:if>
							<tr>
								<td valign="top">
									<div align="right">
										<s:text name="login.cookiesave"/>:
									</div>
								</td>
								<td>
									<s:radio list="cookieTimeList" listKey="key" listValue="value" name="cookieTime"></s:radio>
								</td>
							</tr>
							<tr>
								<td>
									<div align="right">
										<s:text name="login.hidden"/>:
									</div>
								</td>
								<td>
									<s:radio list="radioYesNoList" listKey="key" listValue="value" name="hiddenLogin"></s:radio>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div align="center">
										<s:submit value="%{getText('bbscs.button.login')}" cssClass="button1"></s:submit>
										<s:reset value="%{getText('bbscs.botton.reset')}" cssClass="button1"></s:reset>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>
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
