<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%@include file="top.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><bbscs:webinfo type="forumname"/> - <s:text name="reg.title"/><bbscs:webinfo type="poweredby"/></title>
<link href="<%=basePath%>css/css1.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/jsMsg.jsp"></script>
<script type="text/javascript" src="<%=basePath%>js/prototype.js"></script>
<script type="text/javascript" src="<%=basePath%>js/comm.js"></script>
<script language="JavaScript" type="text/javascript">
<!--
function changeStyle(elementID,toStyle) {
  document.getElementById(elementID).className=toStyle;
}

function checkUserNameAction() {
  if ($('userName').value == "" || $('userName').length == 0) {
    alert("<s:text name="reg.inputusername"/>");
    return;
  }
  $('checkUserNameMsg').className = "msg2";
  $('checkUserNameMsg').innerHTML = "<s:text name="bbscs.checking"/>";
  var url = getActionMappingURL("/reg/check");
  var pars = "userName=" + $('userName').value;
  var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: showResult});
}

function showResult(res) {
  resText = res.responseText;
  var jsonMsgObj = new JsonMsgObj(resText);
  //alert(jsonMsgObj.getCodeid());
  var codeid = jsonMsgObj.getCodeid();
  if (codeid == "0") {
    $('checkUserNameMsg').className = "msg3";
  }
  else {
    $('checkUserNameMsg').className = "errormsg";
  }
  $('checkUserNameMsg').innerHTML = jsonMsgObj.getMessage();

}

//-->
</script>
</head>
<body>
<table width="780" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><hr size="3" noshade class="hrcolor1"></td>
  </tr>
</table>
<s:form action="add">
<table width="750" border="0" align="center" cellpadding="8" cellspacing="0">
  <tr>
    <td colspan="3">
      <s:actionerror/>
    </td>
    </tr>
  <tr>
    <td colspan="3"><span class="font1"><strong><s:text name="reg.title1"/></strong></span>(<s:text name="reg.title2"/>)
    </td>
  </tr>
  <tr>
    <td width="170"><div align="right"><s:text name="reg.username"/>:<span class="font2">*</span></div></td>
    <td width="180">
      <s:textfield name="userName" id="userName" cssClass="input1" size="30" maxlength="20" onfocus="changeStyle('usernameMsg','msg2');" onblur="changeStyle('usernameMsg','msg1');"></s:textfield>
    </td>
    <td width="370">
      <div class="msg1" id="usernameMsg"><s:text name="reg.username.notice"/></div>
      <s:fielderror theme="bbscs0">
      <s:param>userName</s:param>
      </s:fielderror>
    </td>
  </tr>
  <tr>
    <td class="t1">&nbsp;</td>
    <td valign="top"><div align="center">
      <input type="button" name="Check" value="<s:text name="reg.checkusename"/>" class="button1" onclick="checkUserNameAction();"/>
    </div></td>
    <td>
      <div id="checkUserNameMsg">
      </div>
    </td>
  </tr>
  <tr>
    <td colspan="3"><hr size="1" noshade class="hrcolor2"></td>
    </tr>
  <tr>
    <td><div align="right"><s:text name="reg.nickname"/>:<span class="font2">*</span></div></td>
    <td>
      <s:textfield name="nickName" id="nickName" cssClass="input1" size="30" maxlength="20" onfocus="changeStyle('nicknameMsg','msg2');" onblur="changeStyle('nicknameMsg','msg1');">
      </s:textfield>
    </td>
    <td><div class="msg1" id="nicknameMsg"><s:text name="reg.nickname.notice"/></div>
    <s:fielderror theme="bbscs0">
      <s:param>nickName</s:param>
      </s:fielderror>
    </td>
  </tr>
  <tr>
    <td><div align="right">Email:<span class="font2">*</span></div></td>
    <td>
      <s:textfield name="email" id="email" cssClass="input1" onfocus="changeStyle('emailMsg','msg2');" onblur="changeStyle('emailMsg','msg1');" size="30" maxlength="50">
      </s:textfield>
    </td>
    <td><div class="msg1" id="emailMsg"><s:text name="reg.email.notice"/></div>
    <s:fielderror theme="bbscs0">
      <s:param>email</s:param>
      </s:fielderror>
    </td>
  </tr>
  <tr>
    <td colspan="3"><hr size="1" noshade class="hrcolor2"></td>
    </tr>
  <tr>
    <td><div align="right"><s:text name="reg.passwd1"/>:<span class="font2">*</span></div></td>
    <td>
      <s:password name="passwd" id="passwd" cssClass="input1" onfocus="changeStyle('passwdMsg','msg2');" onblur="changeStyle('passwdMsg','msg1')" size="30" maxlength="20" showPassword="true">
      </s:password>
    </td>
    <td><div class="msg1" id="passwdMsg"><s:text name="reg.passwd1.notice"/></div>
    <s:fielderror theme="bbscs0">
      <s:param>passwd</s:param>
      </s:fielderror>
    </td>
  </tr>
  <tr>
    <td><div align="right"><s:text name="reg.passwd2"/>:<span class="font2">*</span></div></td>
    <td>
      <s:password name="rePasswd" id="rePasswd" cssClass="input1" onfocus="changeStyle('passwdreMsg','msg2');" onblur="changeStyle('passwdreMsg','msg1');" size="30" maxlength="20" showPassword="true">
      </s:password>
    </td>
    <td><div class="msg1" id="passwdreMsg"><s:text name="reg.passwd2.notice"/></div>
    <s:fielderror theme="bbscs0">
      <s:param>rePasswd</s:param>
      </s:fielderror>
    </td>
  </tr>
  <tr>
    <td><div align="right"><s:text name="reg.question"/>:<span class="font2">*</span></div></td>
    <td>
      <s:textfield name="question" id="question" cssClass="input1" onfocus="changeStyle('questionMsg','msg2');" onblur="changeStyle('questionMsg','msg1');" size="30" maxlength="50">
      </s:textfield>
    </td>
    <td><div class="msg1" id="questionMsg"><s:text name="reg.question.notice"/></div>
    <s:fielderror theme="bbscs0">
      <s:param>question</s:param>
      </s:fielderror>
    </td>
  </tr>
  <tr>
    <td><div align="right"><s:text name="reg.answer"/>:<span class="font2">*</span></div></td>
    <td>
      <s:textfield name="answer" id="answer" cssClass="input1" onfocus="changeStyle('answerMsg','msg2');" onblur="changeStyle('answerMsg','msg1');" size="30" maxlength="50">
      </s:textfield>
    </td>
    <td><div class="msg1" id="answerMsg"><s:text name="reg.answer.notice"/></div>
    <s:fielderror theme="bbscs0">
      <s:param>answer</s:param>
      </s:fielderror>
    </td>
  </tr>
  <s:if test="%{useAuthCode}">
  <tr>
    <td><div align="right"><s:text name="login.authcode"/>:<span class="font2">*</span></div></td>
    <td>
      <s:textfield name="authCode" id="authCode" cssClass="input1" onfocus="changeStyle('authCodeMsg','msg2');" onblur="changeStyle('authCodeMsg','msg1');" size="5" maxlength="50">
      </s:textfield>
      <img alt="<s:text name="login.authcode"/>" src="<%=basePath%>authimg" align="absmiddle" />
    </td>
    <td><div class="msg1" id="authCodeMsg"><s:text name="reg.authcode.motice"/></div>
    <s:fielderror theme="bbscs0">
      <s:param>authCode</s:param>
      </s:fielderror>
    </td>
  </tr>
  </s:if>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3"><div align="center">
      <s:submit cssClass="button1" value="%{getText('reg.submit')}">
      </s:submit>
    </div></td>
    </tr>
</table>
</s:form>
</body>
</html>
