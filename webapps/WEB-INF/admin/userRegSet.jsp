<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户注册选项</title>
		<link href="css/admin.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<p>
			&nbsp;
		</p>
		<table width="90%" border="0" align="center" cellpadding="10"
			cellspacing="0" class="table1">
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="5" cellspacing="0">
						<tr>
							<td>
								<strong>用户注册选项</strong>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<s:actionerror theme="bbscs0" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<s:actionmessage theme="bbscs0" />
							</td>
						</tr>
					</table>

					<s:form action="adminUserRegSet">
						<s:hidden name="action"></s:hidden>
						<table width="100%" border="0" cellpadding="5" cellspacing="0">
							<tr>
								<td colspan="2" class="td1">
									<strong>允许新用户注册</strong>
								</td>
							</tr>
							<tr>
								<td width="60%">
									如果您想临时 (或者永久) 停止新用户注册，您可以选择“否”。任何尝试注册的用户将会被告知此时不接受新的注册。
								</td>
								<td width="40%" valign="top">
									<s:radio list="radioYesNoList" name="openUserReg" listKey="key"
										listValue="value"></s:radio>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="td1">
									<strong>注册时需要用户输入验证码</strong>
								</td>
							</tr>
							<tr>
								<td>
									防止恶意注册或注册机注册。
								</td>
								<td>
									<s:radio list="radioYesNoList" name="useRegAuthCode"
										listKey="key" listValue="value"></s:radio>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="td1">
									<strong>审查验证新用户</strong>
								</td>
							</tr>
							<tr>
								<td>
									新会员需要由管理员确认为注册会员才能发帖。
								</td>
								<td>
									<s:radio list="radioYesNoList" name="checkRegUser"
										listKey="key" listValue="value"></s:radio>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="td1">
									<strong>在注册时验证 Email 地址</strong>
								</td>
							</tr>
							<tr>
								<td>
									如果此选项设置为“是”，刚刚注册会员将不允许发帖。他们会在注册时填写的 Email
									中收到一封激活邮件。他们点击邮件中的激活链接后，才可以在论坛正常发帖。
									<p>
										如果一个用户的帐号没有通过那个链接激活，此用户将保留在“等待验证的用户”用户组内。
									</p>
								</td>
								<td valign="top">
									<s:radio list="radioYesNoList" name="checkRegUserEmail"
										listKey="key" listValue="value"></s:radio>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="td1">
									<strong>非法的用户名</strong>
								</td>
							</tr>
							<tr>
								<td valign="top">
									输入您不希望用户注册的名字。如果用户想注册的名字包括了这些词语，用户会被告知出错。用逗号分隔字符。注意：用户名只能包括英文、数字和下划线。
								</td>
								<td valign="top">
									<s:textarea id="canNotRegUserName" name="canNotRegUserName"
										cols="40" rows="5" cssClass="textarea1"></s:textarea>
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
		<p>
			&nbsp;
		</p>
	</body>
</html>
