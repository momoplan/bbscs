<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="forum.manage.title"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css"/>
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
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script type="text/javascript" src="js/xmlHttpRequest.js"></script>
<script language="JavaScript" type="text/javascript">
<!--
function checkAll() {
  var ca = document.getElementById("checkall");
  var ids = document.getElementsByName("ids");
  for (var i = 0; i < ids.length; i++) {
      ids[i].checked = ca.checked;
  }
}

function auditing() {
  var ids = document.getElementsByName("ids");
  var num = 0;
  for (var i = 0; i < ids.length; i++) {
    if (ids[i].checked) {
      num++;
    }
  }
  if (num == 0) {
    alert("<s:text name="forum.select.aud"/>");
    return false;
  }
  var del = confirm("<s:text name="forum.aud.confirm"/>");
  if (del) {
  var forumManageForm = document.getElementById("forumManageForm");
    forumManageForm.action.value = "auditing";
    forumManageForm.submit();
  }
  else {
    return false;
  }
}

function auditingAttach() {
  var ids = document.getElementsByName("ids");
  var num = 0;
  for (var i = 0; i < ids.length; i++) {
    if (ids[i].checked) {
      num++;
    }
  }
  if (num == 0) {
    alert("<s:text name="forum.select.aud"/>");
    return false;
  }
  var del = confirm("<s:text name="forum.aud.confirm"/>");
  if (del) {
  var forumManageForm = document.getElementById("forumManageForm");
    forumManageForm.action.value = "auditingAttach";
    forumManageForm.submit();
  }
  else {
    return false;
  }
}

function delsnota() {
  var ids = document.getElementsByName("ids");
  var num = 0;
  for (var i = 0; i < ids.length; i++) {
    if (ids[i].checked) {
      num++;
    }
  }
  if (num == 0) {
    alert("<s:text name="forum.select.del"/>");
    return false;
  }
  var del = confirm("<s:text name="forum.del.confirm"/>");
  if (del) {
  var forumManageForm = document.getElementById("forumManageForm");
    forumManageForm.action.value = "delsnota";
    forumManageForm.submit();
  }
  else {
    return false;
  }
}

function delsnotaa() {
  var ids = document.getElementsByName("ids");
  var num = 0;
  for (var i = 0; i < ids.length; i++) {
    if (ids[i].checked) {
      num++;
    }
  }
  if (num == 0) {
    alert("<s:text name="forum.select.del"/>");
    return false;
  }
  var del = confirm("<s:text name="forum.del.confirm"/>");
  if (del) {
  var forumManageForm = document.getElementById("forumManageForm");
    forumManageForm.action.value = "delsnotaa";
    forumManageForm.submit();
  }
  else {
    return false;
  }
}
//-->
</script>
</head>

<body>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td valign="top" class="bgColor3"><s:text name="forum.boardname"/>:<strong><s:property value="%{board.boardName}"/></strong></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="5" cellspacing="0" class="table9">
  <tr>
    <td width="38%"><s:text name="bbscs.boardmaster"/>:<bbscs:boardmaster value="%{board.boardMaster}"/></td>
    <td width="62%">
      <div align="right">
        [<s:text name="bbscs.pagebreak"/>:<bbscs:pages value="%{pageList.pages}"/>]
        <s:url action="forumManage?action=m" id="urlm">
        <s:param name="bid" value="%{bid}"/>
        </s:url>
        [<a href="${urlm}"><s:text name="forum.returnmang"/></a>]
        <s:url action="forum?action=index" id="urlf">
        <s:param name="bid" value="%{bid}"/>
        </s:url>
        [<a href="${urlf}"><s:text name="forum.returnforum"/></a>]
      </div>
    </td>
  </tr>
</table>
<s:form action="forumManage" id="forumManageForm" name="forumManageForm">
<s:hidden name="action"></s:hidden>
<s:hidden name="bid"></s:hidden>
<s:hidden name="page"></s:hidden>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="bgColor3"><table width="100%" border="0" cellpadding="5" cellspacing="1">
      <tr>
        <td width="7%"><div align="center"><s:text name="bbscs.select"/></div></td>
        <td width="8%"><div align="center"><s:text name="forum.artsize"/></div></td>
        <td width="55%"><div align="center"><s:text name="forum.art.title"/></div></td>
        <td width="15%"><div align="center"><s:text name="forum.author"/></div></td>
        <td width="15%"><div align="center"><s:text name="forum.time"/></div></td>
      </tr>
      <s:iterator value="%{pageList.objectList}" id="f">
      <tr>
        <td class="bgColor2">
          <input type="checkbox" name="ids" value="<s:property value="#f.id"/>" />
          <s:if test="#f.canNotDel==1">M</s:if>
        </td>
        <td class="bgColor4"><div align="center"><s:property value="#f.artSize"/></div></td>
        <td class="bgColor2">
          <span class="font1">
            <s:if test="%{action=='aa'}">
            <bbscs:forum forumValue="f" type="titleauditingattach"/>
            </s:if>
            <s:else>
            <bbscs:forum forumValue="f" type="titleauditing"/>
            </s:else>
          </span>
          <bbscs:forum forumValue="f" type="titleitemmanage"/>
        </td>
        <td class="bgColor4"><s:property value="#f.nickName"/>[<em><s:property value="#f.userName"/></em>]</td>
        <td class="bgColor2"><div align="center"><bbscs:forum forumValue="f" type="posttime"/></div></td>
      </tr>
      </s:iterator>
    </table></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td>
      <div align="right">
        [<s:text name="bbscs.pagebreak"/>:<bbscs:pages value="%{pageList.pages}"/>]
        <s:url action="forumManage?action=m" id="urlm">
        <s:param name="bid" value="%{bid}"/>
        </s:url>
        [<a href="${urlm}"><s:text name="forum.returnmang"/></a>]
        <s:url action="forum?action=index" id="urlf">
        <s:param name="bid" value="%{bid}"/>
        </s:url>
        [<a href="${urlf}"><s:text name="forum.returnforum"/></a>]
      </div>
    </td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td class="font1">
      <div align="center">
        <input id="checkall" type="checkbox" name="checkall" value="checkall" onclick="checkAll();"/>
        <s:text name="forum.select.checked"/>
        <s:if test="%{action=='aa'}">
        <input type="button" name="AuditingButton" value="<s:text name="forum.aud.pass"/>" class="button1" onclick="auditingAttach();"/>
        <input type="button" name="DelsButton" value="<s:text name="bbscs.del"/>" class="button1" onclick="delsnotaa();"/>
        </s:if>
        <s:else>
         <input type="button" name="AuditingButton" value="<s:text name="forum.aud.pass"/>" class="button1" onclick="auditing();"/>
        <input type="button" name="DelsButton" value="<s:text name="bbscs.del"/>" class="button1" onclick="delsnota();"/>
        </s:else>
      </div>
    </td>
  </tr>
</table>
</s:form>
</body>
</html>