<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><s:text name="signset.title1"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script language="javascript" type="text/javascript">
function over(tid) {
  tid.className = "signDivOn";
}

function out(tid) {
  tid.className = "signDivOff";
}

function loadSignEditPage(signID) {
  Element.show("signDetailChange");
  $('signDetailChange').innerHTML = pageLoading;
  var url = getActionMappingURL("/signSet");
  var pars = "action=edit&ajax=shtml&signID=" + signID;

  var myAjax = new Ajax.Updater("signDetailChange", url, {method: 'get', parameters: pars});
}

var SignEditAjax = Class.create();

SignEditAjax.prototype = {
  initialize: function(signID) {
    this.signID = signID;
  },

  edit: function() {
    showExeMsg();
    var url = getActionMappingURL("/signSet");
    var pars = "action=editdo&ajax=xml&signID="+this.signID+"&signDetail="+encodeURIComponent($('signDetail').value);
    var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: this.editCompleted.bind(this)});
  },

  editCompleted: function(res) {
    resText = res.responseText;
  	var jsonMsgObj = new JsonMsgObj(resText);
  	var codeid = jsonMsgObj.getCodeid();
    hiddenExeMsg();
    alert(jsonMsgObj.getMessage());
    if (codeid == "0") {
      $('signDiv'+this.signID).innerHTML = jsonMsgObj.getText();
      closeSignEditPage();
    }
  }
};

function signEditDo() {
  var signID = $('signID').value;
  var oSignEditAjax = new SignEditAjax(signID);
  oSignEditAjax.edit();
}

function closeSignEditPage() {
  $("signDetailChange").innerHTML = "";
  Element.hide("signDetailChange");
}

function loadSmilePage(inputName) {
  Element.show("smileDiv");
  $('smileDiv').innerHTML = pageLoading;

  var url = "smile.jsp";
  var pars = "inputName="+inputName;

  var myAjax = new Ajax.Updater("smileDiv", url, {method: 'get', parameters: pars});
}

function insertSmile(inputName,smlieTag) {
  $(inputName).value = $(inputName).value + smlieTag;
  $(inputName).focus();
}

function closeSmilePage() {
  $("smileDiv").innerHTML = "";
  Element.hide("smileDiv");
}
</script>
</head>
<body>
<table width="95%" border="0" align="center" cellpadding="10" cellspacing="0" class="table7">
  <tr>
    <td>
      <table width="100%" border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2">
            <strong><s:text name="signset.title1"/></strong>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td3">
            <strong><a href="javascript:;" onclick="loadSignEditPage('0');"><s:text name="signset.sign"/>A</a></strong>
          </td>
        </tr>
        <tr>
          <td width="17%" valign="top"><s:text name="signset.detail"/></td>
          <td width="83%">
            <div id="signDiv0" class="signDivOff" onclick="loadSignEditPage('0');" onmouseover="over(this);" onmouseout="out(this);"><s:property value="%{userSign0}" escape="false"/></div>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td3">
            <strong><a href="javascript:;" onclick="loadSignEditPage('1');"><s:text name="signset.sign"/>B</a></strong>
          </td>
        </tr>
        <tr>
          <td valign="top"><s:text name="signset.detail"/></td>
          <td>
            <div id="signDiv1" class="signDivOff" onclick="loadSignEditPage('1');" onmouseover="over(this);" onmouseout="out(this);"><s:property value="%{userSign1}" escape="false"/></div>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td3">
            <strong><a href="javascript:;" onclick="loadSignEditPage('2');"><s:text name="signset.sign"/>C</a></strong>
          </td>
        </tr>
        <tr>
          <td valign="top"><s:text name="signset.detail"/></td>
          <td>
            <div id="signDiv2" class="signDivOff" onclick="loadSignEditPage('2');" onmouseover="over(this);" onmouseout="out(this);"><s:property value="%{userSign2}" escape="false"/></div>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <div id="signDetailChange"></div>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>