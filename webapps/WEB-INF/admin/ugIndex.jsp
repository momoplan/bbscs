<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>用户组管理</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script language="JavaScript" type="text/javascript">
<!--

function loadPage() {
  $('ugListInfo').innerHTML = pageLoading;
  var url = getActionMappingURL("/adminUgSet");
  var pars = "action=list&ajax=shtml";
  var myAjax = new Ajax.Updater("ugListInfo", url, {method: 'get', parameters: pars});
}

function loadPageOK(responseText) {
  $('ugListInfo').innerHTML = responseText;
}

function loadPageError() {
  $('ugListInfo').innerHTML = "Load error!";
}

function newUg() {
  Element.show("ugSet");
  $('ugSet').innerHTML = pageLoading;

  var url = getActionMappingURL("/adminUgSet");
  var pars = "action=add&ajax=shtml";
  var myAjax = new Ajax.Updater("ugSet", url, {method: 'get', parameters: pars});
}

function editUg(ugID) {
  displayElement("ugSet");
  $('ugSet').innerHTML = pageLoading;

  var url = getActionMappingURL("/adminUgSet");
  var pars = "action=edit&ajax=shtml&id=" + ugID;
  var myAjax = new Ajax.Updater("ugSet", url, {method: 'get', parameters: pars});
}

function newUgPage(resText) {
  $('ugSet').innerHTML = resText;
}

function addOrEdit() {
  if ($('action').value = "add") {
    addUg();
  }
  if ($('action').value = "editdo") {
    editDoUg();
  }
  return false;
}

function addUg() {
  var url = getActionMappingURL("/adminUgSet");
  var pars = "action=addsave&ajax=xml&groupName="+encodeURIComponent($('groupName').value)+"&groupDesc="+encodeURIComponent($('groupDesc').value);

  var checkBoxs = document.getElementsByName("roleIDs");
  for (var i = 0; i < checkBoxs.length; i++) {
    if (checkBoxs[i].checked) {
      pars = pars + "&roleIDs=" + checkBoxs[i].value;
    }
  }

  var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: addUgOK});
}

function editDoUg() {
  var url = getActionMappingURL("/adminUgSet");
  var pars = "action=editsave&ajax=xml&groupName="+encodeURIComponent($('groupName').value)+"&id="+$('id').value+"&groupDesc="+encodeURIComponent($('groupDesc').value);

  var checkBoxs = document.getElementsByName("roleIDs");
  for (var i = 0; i < checkBoxs.length; i++) {
    if (checkBoxs[i].checked) {
      pars = pars + "&roleIDs=" + checkBoxs[i].value;
    }
  }

  var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: addUgOK});
}

function addUgOK(res) {
  resText = res.responseText;
  var jsonMsgObj = new JsonMsgObj(resText);
  var codeid = jsonMsgObj.getCodeid();
  alert(jsonMsgObj.getMessage());
  if (codeid == "0") {
    cloesUgSetPage();
    loadPage();
  }
}

function cloesUgSetPage() {
  $("ugSet").innerHTML = "";
  Element.hide("ugSet");
}

function delUg(id) {
  var url = getActionMappingURL("/adminUgSet");
  var pars = "action=del&ajax=XML&id="+id;
  var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: delUgOK});
}

function delUgOK(res) {
  resText = res.responseText;
  var jsonMsgObj = new JsonMsgObj(resText);
  var codeid = jsonMsgObj.getCodeid();
  if (codeid == "0") {
    alert(jsonMsgObj.getMessage());
    loadPage();
  }
  else {
    alert(jsonMsgObj.getMessage());
  }
}

//-->
</script>
</head>
<body onload="loadPage();">
<p>&nbsp;</p>
<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
  <tr>
    <td>
      <table width="100%" border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td>
            <strong>用户组管理</strong>
          </td>
        </tr>
      </table>
      <div id="ugListInfo"></div>
      <table width="100%" border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td>
            <div align="center">
              <input type="button" name="newUgButton" value="新建用户组" class="button2" onclick="newUg();"/>
            </div>
          </td>
        </tr>
      </table>
      <div id="ugSet"></div>
    </td>
  </tr>
</table>
<p>&nbsp;</p>
</body>
</html>