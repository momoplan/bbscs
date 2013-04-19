<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>基本设置</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>

<body>
<p>&nbsp;</p>
<s:form action="adminBaseSet">
<s:hidden name="action"></s:hidden>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2"><strong>基本设置</strong></td>
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
          <td colspan="2" class="td1"><strong>Meta 关键词</strong></td>
        </tr>
        <tr>
          <td width="60%">为所有页面输入 Meta 关键词，让搜索引擎更容易找到您的论坛。</td>
          <td width="40%">
          <s:textfield id="metaKeywords" name="metaKeywords" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>Meta 描述</strong></td>
        </tr>
        <tr>
          <td>为所有页面输入 Meta 描述，以便能够在搜索引擎中正确搜索到您的论坛。</td>
          <td>
          <s:textfield id="metaDescription" name="metaDescription" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>在页面导航器可见的页面数</strong></td>
        </tr>
        <tr>
          <td>如果一个主题/论坛等需要多页才能显示下，这个选项选择当前页面的导航栏中显示多少个页码链接。设置为 0 在页面导航栏显示所有页的页码。</td>
          <td>
          <s:textfield id="canSeePageNum" name="canSeePageNum" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>使用安全登录系统</strong></td>
        </tr>
        <tr>
          <td>安全登录系统是指: 如果用户密码输入错误 5 次，那么在未来的 15 分钟内该 IP 都无法登录论坛。选择“否”将禁用此功能。</td>
          <td>
            <s:radio list="radioYesNoList" name="useSafeLogin" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>使用验证码登录系统</strong></td>
        </tr>
        <tr>
          <td>在登录时需要用户输入验证码，在使用安全登录系统的情况下可以不使用此项功能。选择“否”将禁用此功能。</td>
          <td>
            <s:radio list="radioYesNoList" name="useAuthCode" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>退出转向URL</strong></td>
        </tr>
        <tr>
          <td>用户点击“退出”后转向的URL，可以是你的网站主页。</td>
          <td>
          <s:textfield id="logoutUrl" name="logoutUrl" cssClass="input2" size="40"></s:textfield>
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
