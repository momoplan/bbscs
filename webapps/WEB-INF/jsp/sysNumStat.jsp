<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title><s:text name="sysstat.title"/></title>
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
</head>

<body>
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
<div>&nbsp;</div>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="bgColor5"><table width="100%" border="0" cellpadding="5" cellspacing="1">
      <tr>
        <td colspan="7" class="bgColor3"><strong class="font1"><s:text name="sysstat.count"/></strong></td>
        </tr>
      <tr>
        <td class="bgColor2"><div align="center"><s:text name="sysstat.date"/></div></td>
        <td class="bgColor4"><div align="center"><s:text name="sysstat.regusernum"/></div></td>
        <td class="bgColor2"><div align="center"><s:text name="sysstat.regadd"/></div></td>
        <td class="bgColor4"><div align="center"><s:text name="sysstat.topicnum"/></div></td>
        <td class="bgColor2"><div align="center"><s:text name="sysstat.topicadd"/></div></td>
        <td class="bgColor4"><div align="center"><s:text name="sysstat.postnum"/></div></td>
        <td class="bgColor2"><div align="center"><s:text name="sysstat.postadd"/></div></td>
      </tr>
      <s:iterator id="stat" value="%{pageList.objectList}">
      <tr>
        <td class="bgColor2"><div align="center"><s:property value="#stat.recDate"/></div></td>
        <td class="bgColor4"><div align="center"><s:property value="#stat.num0"/></div></td>
        <td class="bgColor2"><div align="center"><s:property value="#stat.numInc0"/></div></td>
        <td class="bgColor4"><div align="center"><s:property value="#stat.num1"/></div></td>
        <td class="bgColor2"><div align="center"><s:property value="#stat.numInc1"/></div></td>
        <td class="bgColor4"><div align="center"><s:property value="#stat.num2"/></div></td>
        <td class="bgColor2"><div align="center"><s:property value="#stat.numInc2"/></div></td>
      </tr>
      </s:iterator>
      <tr>
        <td colspan="7" class="bgColor3">[<s:text name="bbscs.pagebreak"/>: <bbscs:pages value="%{pageList.pages}"/>]</td>
        </tr>
    </table></td>
  </tr>
</table>
</body>
</html>