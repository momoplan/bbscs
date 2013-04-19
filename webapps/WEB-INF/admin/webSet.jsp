<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>网站信息</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<p>&nbsp;</p>
<s:form action="adminWebset">
<s:hidden name="action"></s:hidden>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2"><strong>网站信息</strong></td>
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
          <td colspan="2" class="td1"><strong>论坛名称</strong></td>
        </tr>
        <tr>
          <td width="60%">论坛名称。它将在论坛页面的浏览器窗口标题中显示。</td>
          <td width="40%">
          <s:textfield id="forumName" name="forumName" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>论坛网址</strong></td>
        </tr>
        <tr>
          <td>您访问这个论坛的网址。<br/>
            注意: 不要以斜杠 (“/”) 结尾。</td>
          <td>
          <s:textfield id="forumUrl" name="forumUrl" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>主页名称</strong></td>
        </tr>
        <tr>
          <td>主页名称。出现在论坛每页底部。</td>
          <td>
          <s:textfield id="webName" name="webName" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2"class="td1"><strong>主页地址</strong></td>
        </tr>
        <tr>
          <td>主页地址。出现在论坛每页底部。</td>
          <td>
          <s:textfield id="webUrl" name="webUrl" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>网站管理员 Email</strong></td>
        </tr>
        <tr>
          <td>网站管理员的 Email 地址。</td>
          <td>
          <s:textfield id="webmasterEmail" name="webmasterEmail" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2"class="td1"><strong>隐私声明的链接</strong></td>
        </tr>
        <tr>
          <td>如果您的论坛有隐私声明的话，请在这里输入它的链接地址。</td>
          <td>
          <s:textfield id="privacyUrl" name="privacyUrl" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>版权信息</strong></td>
        </tr>
        <tr>
          <td>每页页脚插入的版权信息。</td>
          <td>
          <s:textfield id="copyRightMsg" name="copyRightMsg" cssClass="input2" size="40"></s:textfield>
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

</body>
</html>
