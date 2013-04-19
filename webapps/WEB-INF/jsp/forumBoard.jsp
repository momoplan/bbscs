<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="board.list"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	margin-left: 1px;
	margin-right: 1px;
}
-->
</style>
</head>

<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="bgColor1"><table width="100%" border="0" cellpadding="5" cellspacing="1">
      <tr class="bgColor3">
        <td width="10%"><div align="center"></div></td>
        <td width="70%"><div align="center"><strong><s:text name="bbscs.board"/></strong></div></td>
        <td width="10%"><div align="center"><strong><s:text name="bbscs.topic"/></strong></div></td>
        <td width="10%"><div align="center"><strong><s:text name="bbscs.art"/></strong></div></td>
      </tr>
      <tr>
        <td colspan="4" class="bgColor4"><table width="100%" border="0" cellpadding="2" cellspacing="0">
          <tr>
            <td><strong class="font1"><s:property value="%{board.boardName}"/></strong></td>
          </tr>
          <tr>
            <td><s:property value="%{board.explains}"/></td>
          </tr>
          <tr>
            <td><s:text name="bbscs.boardmaster"/> <bbscs:boardmaster  value="%{board.boardMaster}"/></td>
          </tr>
        </table></td>
      </tr>
      <s:iterator value="%{boardList}" id="b">
      <tr>
        <td valign="middle" class="bgColor4"><div align="center"></div></td>
        <td class="bgColor2"><table width="100%" border="0" cellpadding="2" cellspacing="0">
          <tr>
            <td>
              <strong class="font1">
                <s:if test="%{urlRewrite}">
                <a href="forum-index-<s:property value="#b.id"/>.html"><s:property value="#b.boardName"/></a>
                </s:if>
                <s:else>
                <s:url action="forum?action=index" id="forumUrl">
                <s:param name="bid" value="#b.id"/>
                </s:url>
                <a href="${forumUrl}"><s:property value="#b.boardName"/></a>
                </s:else>
              </strong>
            </td>
          </tr>
          <tr>
            <td><s:property value="#b.explains"/></td>
          </tr>
          <tr>
            <td><s:text name="bbscs.boardmaster"/> <bbscs:boardmaster value="#b.boardMaster"/></td>
          </tr>
        </table></td>
        <td class="bgColor4"><div align="center"><s:property value="#b.mainPostNum"/></div></td>
        <td class="bgColor2"><div align="center"><s:property value="#b.postNum"/></div></td>
      </tr>
      </s:iterator>
    </table></td>
  </tr>
</table>
</body>
</html>