<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>角色管理</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script language="JavaScript" type="text/javascript">
<!--
function loadPage() {
  $('roleListInfo').innerHTML = pageLoading;
  var url = getActionMappingURL("/adminRoleSet");
  var pars = "action=list&ajax=shtml";
  var myAjax = new Ajax.Updater("roleListInfo", url, {method: 'get', parameters: pars});
}

function newRole() {
  Element.show("roleSet");
  $('roleSet').innerHTML = pageLoading;
  var url = getActionMappingURL("/adminRoleSet");
  var pars = "action=add&ajax=shtml";
  var myAjax = new Ajax.Updater("roleSet", url, {method: 'get', parameters: pars});
}

function editRole(roleID) {
  Element.show("roleSet");
  $('roleSet').innerHTML = pageLoading;
  var url = getActionMappingURL("/adminRoleSet");
  var pars = "action=edit&ajax=shtml&id=" + roleID;
  var myAjax = new Ajax.Updater("roleSet", url, {method: 'get', parameters: pars});
}

function newRolePage(resText) {
  $('roleSet').innerHTML = resText;
}

function addRole() {
  var url = getActionMappingURL("/adminRoleSet");
  var pars = "action=addsave&ajax=XML&roleName="+encodeURIComponent($('roleName').value);
  var checkBoxs = document.getElementsByName("permissions");
  for (var i = 0; i < checkBoxs.length; i++) {
    if (checkBoxs[i].checked) {
      pars = pars + "&permissions=" + checkBoxs[i].value;
    }
  }
  var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: addRoleOK});
}

function editDoRole() {
  var url = getActionMappingURL("/adminRoleSet");
  var pars = "action=editsave&ajax=XML&roleName="+encodeURIComponent($('roleName').value)+"&id="+$('id').value;
  var checkBoxs = document.getElementsByName("permissions");
  for (var i = 0; i < checkBoxs.length; i++) {
    if (checkBoxs[i].checked) {
      pars = pars + "&permissions=" + checkBoxs[i].value;
    }
  }
  var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: addRoleOK});
}

function addRoleOK(res) {
  resText = res.responseText;
  var jsonMsgObj = new JsonMsgObj(resText);
  var codeid = jsonMsgObj.getCodeid();
  alert(jsonMsgObj.getMessage());
  if (codeid == "0") {
    cloesRoleSetPage();
    loadPage();
  }
}

function cloesRoleSetPage() {
  $("roleSet").innerHTML = "";
  Element.hide("roleSet");
}

function delRole(id) {
  var url = getActionMappingURL("/adminRoleSet");
  var pars = "action=del&ajax=xml&id="+id;
  var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: delRoleOK});
}

function delRoleOK(res) {
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
            <strong>角色管理</strong>
          </td>
        </tr>
      </table>
      <div id="roleListInfo"></div>
      <table width="100%" border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td>
            <div align="center">
              <input type="button" name="newRoleButton" value="新增角色" class="button2" onclick="newRole();"/>
            </div>
          </td>
        </tr>
      </table>
      <div id="roleSet"></div>
    </td>
  </tr>
</table>
<p>&nbsp;</p>
</body>
</html>