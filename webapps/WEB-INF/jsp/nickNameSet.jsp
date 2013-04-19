<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="nickset.title1"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script language="javascript" type="text/javascript">

function editNickName() {
  var url = getActionMappingURL("/nickNameSet");
  var pars = "action=edit&ajax=xml&nickName="+encodeURIComponent($('nickName').value);
  var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: editNickNameOK});
}

function editNickNameOK(res) {
  resText = res.responseText;
  var jsonMsgObj = new JsonMsgObj(resText);
  var codeid = jsonMsgObj.getCodeid();
  alert(jsonMsgObj.getMessage());
  if (codeid == "0") {
    $('nickNameDiv').innerHTML = jsonMsgObj.getText();
  }
}
</script>
</head>

<body>
<table width="95%" border="0" align="center" cellpadding="10" cellspacing="0" class="table7">
  <tr>
    <td><table width="100%" border="0" cellpadding="5" cellspacing="0">
      <tr>
        <td colspan="2"><strong><s:text name="nickset.title2"/></strong></td>
        </tr>
      <tr>
        <td width="13%"><s:text name="nickset.cnickname"/></td>
        <td width="87%">
          <div id="nickNameDiv"><s:property value="%{userSession.nickName}"/></div>
        </td>
      </tr>
      <s:form action="nickNameSet">
      <s:hidden name="action"></s:hidden>
      <tr>
        <td><s:text name="nickset.title"/></td>
        <td>
          <s:textfield id="nickName" name="nickName" cssClass="input2" size="40" maxlength="20" onkeypress="return handleEnter(this, event);"></s:textfield>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>
          <input type="button" name="Submit" value="<s:text name="bbscs.change"/>" class="button1" onclick="editNickName();"/>
        </td>
      </tr>
      </s:form>
    </table></td>
  </tr>
</table>
</body>
</html>
