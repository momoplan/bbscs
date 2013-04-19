<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title><s:text name="usershow.title"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script language="JavaScript" type="text/javascript">
<!--

function loadUserShowListPage() {
  $('userShowListDiv').innerHTML = pageLoadingCenter;
  var urls = getActionMappingURL("/userShow");
  var pars = "action=list&ajax=shtml&groupID=0";

  var myAjax = new Ajax.Updater("userShowListDiv", urls, {method: 'get', parameters: pars});
}

function loadUserShowListPageUrl(url) {
  $('userShowListDiv').innerHTML = pageLoadingCenter;

  var urls = getActionName(url);
  var pars = getActionPars(url);

  var myAjax = new Ajax.Updater("userShowListDiv", urls, {method: 'get', parameters: pars});
}


function changeBox() {
  var groupIDObj = document.getElementById("groupID");
  var gid = groupIDObj.options[groupIDObj.selectedIndex].value;
  var url = getActionMappingURL("/userShow?action=list&ajax=shtml&groupID="+gid);
  loadUserShowListPageUrl(url);
}
//-->
</script>
</head>

<body onload="loadUserShowListPage();">
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td width="30%" valign="top" class="bgColor3"><strong><s:text name="sysstat.m"/></strong></td>
    <td width="70%" valign="top" class="bgColor3">
      <div align="right">
        <s:url action="adminMain" id="adminurl"></s:url>
        [<a href="${adminurl}" target="_blank"><s:text name="sysstat.mb"/></a>]
        <s:url action="sysNumStat" id="sysNumStaturl"></s:url>
        [<a href="${sysNumStaturl}"><s:text name="sysstat.count"/></a>]
        <s:url action="userShow?action=index" id="userShowurl"></s:url>
        [<a href="${userShowurl}"><s:text name="sysstat.userlist"/></a>]
        <s:url action="in" id="inurl"></s:url>
        [<a href="${inurl}"><s:text name="sysstat.backin"/></a>]
      </div>
    </td>
  </tr>
</table>
<table width="98%" align="center" border="0" cellpadding="5" cellspacing="0">
  <s:form action="userShow">
  <tr>
    <td>
        <s:select list="groupList" name="groupID" cssClass="select1" listKey="key" listValue="value" onchange="changeBox();" id="groupID"></s:select>
    </td>
  </tr>
  </s:form>
</table>
<div id="userShowListDiv"></div>
</body>
</html>
