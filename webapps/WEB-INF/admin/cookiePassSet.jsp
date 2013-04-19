<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>COOKIE和通行证设置</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script language="JavaScript" type="text/javascript">
<!--
function adminGetNewKeyAction() {
  changeStyle("getnewkey","msg2");
  $('getnewkey').innerHTML = "正在请求重新生成密钥，请稍候...";
  var url = getActionMappingURL("/adminCookiePassSet");
  var pars = "action=key&ajax=json";
  var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: getNewKeyOK});
}

function getNewKeyOK(res) {

  resText = res.responseText;
  var jsonMsgObj = new JsonMsgObj(resText);
  var codeid = jsonMsgObj.getCodeid();
  if (codeid == "0") {
    changeStyle("getnewkey","msg3");
    $('getnewkey').innerHTML = "新密钥已成生，请退出系统重新登录！";
    $('cookieKey').value = jsonMsgObj.getMessage();
  }
  else {
    changeStyle("getnewkey","errormsg");
    $('getnewkey').innerHTML = jsonMsgObj.getMessage();
  }
}
//-->
</script>
</head>

<body>
<p>&nbsp;</p>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td>
      <table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td><strong>COOKIE和通行证设置</strong></td>
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
      <s:form action="adminCookiePassSet">
      <s:hidden name="action"></s:hidden>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td colspan="2" class="td1">
      <strong>COOKIE作用路径</strong>
    </td>
  </tr>
  <tr>
    <td width="60%">Cookie 保存的路径。如果您在一个域名下运行两个或以上的论坛，必须设置到各个不同的论坛路径。如果不是，则直接填写“/”。
      <p>请注意，此处必须以斜杠结尾，比如“/forums/”、“/bbs/”等。</p>
    </td>
    <td width="40%" valign="top">
      <s:textfield id="cookiePath" name="cookiePath" cssClass="input2" size="40"></s:textfield>
    </td>
  </tr>
  <tr>
    <td colspan="2" class="td1">
      <strong>COOKIE作用域</strong>
    </td>
  </tr>
  <tr>
    <td>即您想使本论坛生成的 Cookie 起作用的范围。如果您想对整个主域——例如“yourhost.com”而不仅仅是“forums.yourhost.com”起作用——那么请在这里输入“.yourhost.com”(注意是两个点)。本项可以留空。</td>
    <td valign="top">
      <s:textfield id="cookieDomain" name="cookieDomain" cssClass="input2" size="40"></s:textfield>
    </td>
  </tr>
  <tr>
    <td colspan="2" class="td1">
      <strong>COOKIE密钥</strong>
    </td>
  </tr>
  <tr>
    <td rowspan="2" valign="top">为了保证COOKIE的安全，系统对COOKIE采用3DES进行加密，密钥有系统生成，注意定时更换。</td>
    <td valign="top">
      <s:textfield id="cookieKey" name="cookieKey" cssClass="input2" size="40"></s:textfield>
    </td>
  </tr>
  <tr>
    <td>
    <table width="100%"  border="0" cellpadding="1" cellspacing="0">
      <tr>
        <td width="35%">
        <button onclick="adminGetNewKeyAction();" class="button1">重新生成密钥</button>
        </td>
        <td width="65%"><div id="getnewkey"></div></td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td colspan="2" class="td1">
      <strong>是否使用通行证</strong>
    </td>
  </tr>
  <tr>
    <td>如果选择“是”，则系统从通行证进行用户认证，没有购买天乙软件工作室的通行证解决方案的用户，请选择“否”。</td>
    <td>
      <s:radio list="radioYesNoList" name="usePass" listKey="key" listValue="value"></s:radio>
    </td>
  </tr>
  <tr>
    <td colspan="2" class="td1">
      <strong>通行证认证URL</strong>
    </td>
  </tr>
  <tr>
    <td>将用户名和密码POST到通行证的URL。</td>
    <td>
      <s:textfield id="passUrl" name="passUrl" cssClass="input2" size="40"></s:textfield>
    </td>
  </tr>
  <tr>
    <td colspan="2" class="td1">
      <strong>通行证注册URL</strong>
    </td>
  </tr>
  <tr>
    <td>通行证用户注册的URL。</td>
    <td>
      <s:textfield id="passRegUrl" name="passRegUrl" cssClass="input2" size="40"></s:textfield>
    </td>
  </tr>
  <tr>
    <td colspan="2" class="td1">
      <strong>通行证修改密码URL</strong>
    </td>
  </tr>
  <tr>
    <td>通行证用户修改密码的URL。</td>
    <td>
      <s:textfield id="passChangeUrl" name="passChangeUrl" cssClass="input2" size="40"></s:textfield>
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