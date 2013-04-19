<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="forum.m.wast"/></title>
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

function delw() {
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
  var del = confirm(confirm_del);
  if (del) {
    forumManageForm.action.value = "delw";
    forumManageForm.submit();
  }
  else {
    false;
  }
}

function resume() {
  var ids = document.getElementsByName("ids");
  var num = 0;
  for (var i = 0; i < ids.length; i++) {
    if (ids[i].checked) {
      num++;
    }
  }
  if (num == 0) {
    alert("<s:text name="forum.w.resume.select"/>");
    return false;
  }
  forumManageForm.action.value = "resume";
  forumManageForm.submit();
}

function delallw(bid) {
  var del = confirm(confirm_del);
  if (!del) {
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
        <s:url action="manageAdvance?action=index" id="urlma">
        <s:param name="bid" value="%{bid}"/>
        </s:url>
        [<a href="${urlma}"><s:text name="forum.m.high"/></a>]
        <s:url action="forumManage?action=delallw" id="urldaw">
        <s:param name="bid" value="%{bid}"/>
        </s:url>
        [<img src='images/recycle.gif' align='absmiddle' alt=""/><a href="${urldaw}" onclick="return delallw();"><s:text name="forum.w.delall"/></a>]
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
        <td width="50%"><div align="center"><s:text name="forum.art.title"/></div></td>
        <td width="13%"><div align="center"><s:text name="forum.author"/></div></td>
        <td width="10%"><div align="center"><s:text name="forum.w.deluser"/></div></td>
        <td width="12%"><div align="center"><s:text name="forum.time"/></div></td>
      </tr>
      <s:iterator value="%{pageList.objectList}" id="f">
      <tr>
        <td class="bgColor2">
          <input type="checkbox" name="ids" value="<s:property value="#f.id"/>" />
          <s:if test="#f.canNotDel==1">M</s:if>
        </td>
        <td class="bgColor4"><div align="center"><s:property value="#f.artSize"/></div></td>
        <td class="bgColor2">
          <span class="font1"><bbscs:forum forumValue="f" type="titlewaste"/></span>
          <bbscs:forum forumValue="f" type="titleitemmanage"/>
        </td>
        <td class="bgColor4"><s:property value="#f.nickName"/>[<em><s:property value="#f.userName"/></em>]</td>
        <td class="bgColor2"><s:property value="#f.delUserName"/></td>
        <td class="bgColor4"><div align="center"><bbscs:forum forumValue="f" type="posttime"/></div></td>
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
        <input type="button" name="DelsButton" value="<s:text name="bbscs.del"/>" class="button1" onclick="delw();"/>
        <input type="button" name="ResumeButton" value="<s:text name="forum.w.resume"/>" class="button1" onclick="resume();"/>
      </div>
    </td>
  </tr>
  <tr>
    <td class="font2">
      <div align="center">
      <s:text name="forum.w.notice"/>
      </div>
    </td>
  </tr>
</table>
</s:form>
</body>
</html>