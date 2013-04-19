<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="online.title"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css" />
</head>

<body>
<table width="98%" border="0" cellpadding="0" cellspacing="0">
  <tbody>
  <tr>
    <td class="bgColor1"><table width="100%" border="0" cellpadding="5" cellspacing="1">
      <thead>
      <tr class="bgColor3">
        <td width="25%"><div align="center"><s:text name="bbscs.username"/></div></td>
        <td width="25%"><div align="center"><s:text name="bbscs.nick"/></div></td>
        <td width="25%"><div align="center"><s:text name="forum.time"/></div></td>
        <td width="25%"><div align="center"><s:text name="online.atboard"/></div></td>
      </tr>
      </thead>
      <tbody>
      <s:iterator id="uo" value="%{onlineList}">
      <tr>
        <td class="bgColor4">
        <div align="center">
        <s:url action="userInfo?action=id" id="uiurl">
        <s:param name="id" value="#uo.userID"/>
        </s:url>
        <a href="${uiurl}"><s:property value="#uo.userName"/></a>
        </div>
        </td>
        <td class="bgColor2"><div align="center"><s:property value="#uo.nickName"/></div></td>
        <td class="bgColor4"><div align="center"><bbscs:datetimes format="MM-dd HH:mm" datetype="timestamp" value="#uo.onlineTime"/></div></td>
        <td class="bgColor2">
          <div align="center">
            <s:if test="#uo.boardID!=0">
            <s:url action="forum?action=index" id="furl">
            <s:param name="bid" value="#uo.boardID"/>
            </s:url>
            <a href="${furl}"><s:property value="#uo.atPlace"/></a>
            </s:if>
          </div>
        </td>
      </tr>
      </s:iterator>
      </tbody>
    </table></td>
  </tr>
  </tbody>
</table>
</body>
</html>