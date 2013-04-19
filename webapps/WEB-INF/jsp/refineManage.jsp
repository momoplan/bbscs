<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="read.e.title1"/></title>
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
<script language="JavaScript" type="text/javascript">
<!--
var bid = "<s:property value="%{bid}"/>";
var pid = "<s:property value="%{pid}"/>";
//-->
</script>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript" src="js/refinemanage.js"></script>
</head>

<body onload="loadPage();">
<table width="100%" border="0" cellpadding="5" cellspacing="0" class="readBoard">
  <tr>
    <td width="82%">
      [<s:text name="read.e.title1"/>]
      <s:url action="refine?action=manage&pid=0" id="rUrl">
	  <s:param name="bid" value="%{bid}"/>
	  </s:url>
	  <a href="${rUrl}"><s:property value="%{board.boardName}"/></a>
      <s:iterator id="dirs" value="%{eliteDirs}">
      &raquo;
      <s:url action="refine?action=manage" id="dirUrl">
	  <s:param name="bid" value="%{bid}"/>
	  <s:param name="pid" value="#dirs.id"/>
	  </s:url>
	  <a href="${dirUrl}"><s:property value="#dirs.eliteName"/></a>
      </s:iterator>
    </td>
    <td width="18%">
      <div align="right">
      <s:url action="refine?action=index" id="dirmUrl">
	  <s:param name="bid" value="%{bid}"/>
	  <s:param name="pid" value="%{pid}"/>
	  </s:url>
      [<a href="${dirmUrl}"><s:text name="refine.m.returnr"/></a>]
      <s:url action="forum?action=index" id="fUrl">
	  <s:param name="bid" value="%{bid}"/>
	  </s:url>
      [<a herf="${fUrl}"><s:text name="forum.returnforum"/></a>]
      </div>
    </td>
  </tr>
</table>
<p></p>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="bgColor3"><table width="100%" border="0" cellpadding="5" cellspacing="1">
      <tr>
        <td width="5%"><div align="center"><s:text name="refine.type"/></div></td>
        <td width="55%"><div align="center"><s:text name="forum.art.title"/></div></td>
        <td width="12%"><div align="center"><s:text name="forum.author"/></div></td>
        <td width="12%"><div align="center"><s:text name="read.e.doename"/></div></td>
        <td width="16%"><div align="center"><s:text name="read.e.doetime"/></div></td>
      </tr>
      <tbody id="eliteList"></tbody>
      <tbody id="forumList"></tbody>
    </table></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td width="33%">&nbsp;</td>
    <td width="33%">&nbsp;</td>
    <td width="34%">&nbsp;</td>
  </tr>
  <tr>
    <td>
      <input name="Submit" type="button" onclick="dels();" class="button1" value="<s:text name="bbscs.del"/>" />
      <input name="Submit" type="button" onclick="outDir();" class="button1" value="<s:text name="refine.m.outdir"/>" />
    </td>
    <td><div align="center">
      <input name="eliteName" id="eliteName" type="text" class="input2" size="15" />
      <input name="Submit2" onclick="addDir();" type="button" class="button1" value="<s:text name="refine.m.adddir"/>" />
    </div></td>
    <td>
      <div align="right">
      <s:text name="refine.m.indir"/>
      <select id="eliteDir" name="eliteDir" class="select1">
      </select>
      <s:text name="refine.m.dir"/>
      <input name="Submit3" type="button" onclick="intoDir();" class="button1" value="<s:text name="refine.m.inconfirm"/>" />
      </div>
    </td>
  </tr>
</table>
</body>
</html>