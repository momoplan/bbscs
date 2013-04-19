<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title><s:text name="error" />
		</title>
		<link href="css/css1.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="js/prototype.js"></script>
	</head>

	<body>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<table width="600" border="0" align="center" cellpadding="10"
			cellspacing="0">
			<tr>
				<td width="200">
					<div align="right">
						<img src="images/standard_msg_error_big.gif" alt="" width="64"
							height="64">
					</div>
				</td>
				<td width="400">
					<div id="error" class="errormsg">
						<s:property value="%{interceptError}" escape="false"/>
					</div>
				</td>
			</tr>
			<tr>
			    <td>
			    &nbsp;
			    </td>
			    <td>
			    <div id="loginform" style="display:none">
					<s:form action="login">
						<s:hidden name="action" value="login"></s:hidden>
						<s:hidden name="tourl" value="%{tourl}"></s:hidden>
						<s:hidden name="cookieTime" value="-1"></s:hidden>
						<s:hidden name="hiddenLogin" value="0"></s:hidden>
						<table width="100%" border="0" align="center" cellpadding="5"
							cellspacing="0">
							<tr>
								<td colspan="2">
									<strong><s:text name="login.title" />:<s:text
											name="login.input" />
									</strong>
								</td>
							</tr>
							<tr>
								<td width="34%">
									<div align="right">
										<s:text name="login.username" />
										:
									</div>
								</td>
								<td width="66%">
									<s:textfield name="username" id="username" cssClass="input2"
										size="15" maxlength="20"></s:textfield>
								</td>
							</tr>
							<tr>
								<td>
									<div align="right">
										<s:text name="login.passwd" />
										:
									</div>
								</td>
								<td>
									<s:password name="passwd" id="passwd" cssClass="input2"
										size="15" maxlength="20"></s:password>
								</td>
							</tr>
							<s:if test="%{useAuthCode}">
								<tr>
									<td>
										<div align="right">
											<s:text name="login.authcode" />
											:
										</div>
									</td>
									<td>
										<s:textfield id="authCode" name="authCode" size="5"
											maxlength="4" cssClass="input2"></s:textfield>
										<img alt="<s:text name="login.authcode"/>" src="authimg"
											align="absmiddle" />
									</td>
								</tr>
							</s:if>
							<tr>
								<td colspan="2">
									<div align="center">
										<s:submit value="%{getText('bbscs.button.login')}"
											cssClass="button1"></s:submit>
										<s:reset value="%{getText('bbscs.botton.reset')}"
											cssClass="button1"></s:reset>
									</div>
								</td>
							</tr>
						</table>

					</s:form>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<a href="http://bbs.ruyicai.com"><s:text name="bbscs.back" />
						</a>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
