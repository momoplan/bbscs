<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><s:text name="forum.subed"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript" src="js/subs.js"></script>
</head>
<body onload="loadSubsList('<s:property value="%{bid}"/>');">

<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="bgColor1">
  <tr>
    <td>
      <table width="100%" border="0" cellpadding="5" cellspacing="1">
        <tr>
          <td colspan="4" class="bgColor3">
            <span class="font1">
            <strong>
            <s:url action="forum?action=index" id="forumUrl">
            <s:param name="bid" value="%{bid}"/>
            </s:url>
            <a href="${forumUrl}"><s:property value="%{board.boardName}"/></a> <s:text name="forum.subed"/>
            </strong>
            </span>
          </td>
        </tr>

      </table>
      <div id="subsList"></div>
    </td>
  </tr>
</table>
</body>
</html>