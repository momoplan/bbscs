<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><s:text name="read.w.title"/></title>
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
<script type="text/javascript" src="js/read.js"></script>
<script language="JavaScript" type="text/javascript">
<!--
function checkAll() {
  var ca = document.getElementById("checkall");
  var ids = document.getElementsByName("ids");
  for (var i = 0; i < ids.length; i++) {
      ids[i].checked = ca.checked;
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

//-->
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td valign="top" class="bgColor3"><s:text name="forum.boardname"/>:<strong><bbscs:boardmaster value="%{board.boardMaster}"/></strong></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="5" cellspacing="0" class="table9">
  <tr>
    <td width="38%"><s:text name="bbscs.boardmaster"/>:<bbscs:boardmaster value="%{board.boardMaster}"/></td>
    <td width="62%">
      <div align="right">
        <s:url action="/forumManage?action=w" id="urlw">
        <s:param name="bid" value="%{bid}"/>
        </s:url>
        [<a href="${urlw}"><s:text name="read.w.returnw"/></a>]
        <s:url action="forumManage?action=m" id="urlf">
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
<s:hidden name="page" value="1"></s:hidden>
<s:iterator id="f" value="%{topicList}">
      <div id="topic<s:property value="#f.id"/>">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td class="bgColor5">
            <table width="100%" border="0" cellpadding="4" cellspacing="1">
              <tr>
                <td colspan="2" class="bgColor3">
                  <input type="checkbox" name="ids" value="<s:property value="#f.id"/>" />
                  <bbscs:forum forumValue="f" type="face"/>
                  <span class="font1">
                    <strong><s:text name="forum.art.title"/>:<s:property value="#f.title"/></strong>
                    <span id="cndt<s:property value="#f.id"/>">
                      <s:if test="#f.canNotDel==1">M</s:if>
                    </span>
                  </span>
                </td>
              </tr>
              <tr>
                <td width="21%" valign="top" class="bgColor4">
                  <s:property value="#f.nickName"/>(<s:property value="#f.userName"/>)
                </td>
                <td width="79%" class="bgColor2">
                  <div align="right">
                    <a href="javascript:;" onclick="showIp('<s:property value="%{bid}"/>','<s:property value="#f.id"/>');"><img src="images/ip.gif" alt="IP" border="0" width="16" height="16" align="absmiddle"/></a>
                  </div>
                  <div id="ipMsg<s:property value="#f.id"/>" class="summary1" style="display:none" align="right"></div>
                </td>
              </tr>
              <tr>
                <td valign="top" class="bgColor4">
                  <bbscs:userinfoinpost idValue="#f.userID" styleClass="pic1"/>
                </td>
                <td valign="top" class="bgColor2">
                  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td>
                        <div id="detail<s:property value="#f.id"/>" class="postDetail">
                          <bbscs:forum forumValue="f" type="detail"/>
                        </div>
                        <bbscs:forum forumValue="f" type="sign" itemClass="signInPost"/>
                        <bbscs:forum forumValue="f" type="amend"/>
                        <bbscs:forum forumValue="f" type="delmsg"/>
                      </td>
                    </tr>
                    <tr>
                      <td><div align="right">&nbsp;</div></td>
                    </tr>
                  </table>
                </td>
              </tr>
              <tr>
                <td valign="top" class="bgColor4"><bbscs:forum forumValue="f" type="timeinpost"/></td>
                <td class="bgColor2">
                  <div align="right">

                  </div>
                  <div id="postOpt<s:property value="#f.id"/>" class="summary1" style="display:none"></div>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table width="100%" height="1" border="0">
        <tr>
          <td></td>
        </tr>
      </table>
      </div>
      </s:iterator>

<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td class="font1">
      <div align="center">
        <input id="checkall" type="checkbox" name="checkall" value="checkall" onclick="checkAll();"/>
        <s:text name="forum.select.checked"/>
        <input type="button" name="ResumeButton" value="<s:text name="forum.w.resume"/>" class="button1" onclick="resume();"/>
      </div>
    </td>
  </tr>
</table>
</s:form>
</body>
</html>
