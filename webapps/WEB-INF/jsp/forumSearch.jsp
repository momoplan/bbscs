<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="forum.s.title"/></title>
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
        <s:url action="forum?action=index" id="urlf">
        <s:param name="bid" value="%{bid}"/>
        </s:url>
        [<a href="${urlf}"><s:text name="bbscs.back"/></a>]
      </div>
    </td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="bgColor3"><table width="100%" border="0" cellpadding="5" cellspacing="1">
      <tr>
        <td width="6%"><div align="center"><s:text name="forum.status"/></div></td>
        <td width="8%"><div align="center"><s:text name="forum.artsize"/></div></td>
        <td width="56%"><div align="center"><s:text name="forum.art.title"/></div></td>
        <td width="15%"><div align="center"><s:text name="forum.author"/></div></td>
        <td width="15%"><div align="center"><s:text name="forum.time"/></div></td>
      </tr>
      <s:iterator value="%{pageList.objectList}" id="f">
      <tr>
        <td class="bgColor2">
          <bbscs:forum type="face" forumValue="#f"/>
          <s:if test="#f.canNotDel==1">M</s:if>
        </td>
        <td class="bgColor4"><div align="center"><s:property value="#f.artSize"/></div></td>
        <td class="bgColor2">
          <span class="font1"><bbscs:forum forumValue="f" type="titlemanage"/></span>
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
        <s:url action="forum?action=index" id="urlf">
        <s:param name="bid" value="%{bid}"/>
        </s:url>
        [<a href="${urlf}"><s:text name="bbscs.back"/></a>]
      </div>
    </td>
  </tr>
</table>
</body>
</html>