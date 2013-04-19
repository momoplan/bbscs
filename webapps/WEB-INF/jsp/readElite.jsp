<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="read.e.title"/></title>
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
<table width="100%" border="0" cellpadding="5" cellspacing="0" class="readBoard">
  <tr>
    <td width="82%">
      [<s:text name="read.e.title1"/>]
      <s:url action="refine?action=index&pid=0" id="rUrl">
	  <s:param name="bid" value="%{bid}"/>
	  </s:url>
	  <a href="${rUrl}"><s:property value="%{board.boardName}"/></a>
      <s:iterator id="dirs" value="%{eliteDirs}">
      &raquo;
      <s:url action="refine?action=index" id="dirUrl">
	  <s:param name="bid" value="%{bid}"/>
	  <s:param name="pid" value="#dirs.id"/>
	  </s:url>
	  <a href="${dirUrl}"><s:property value="#dirs.eliteName"/></a>
      </s:iterator>
    </td>
    <td width="18%">
      <div align="right">
        [<a href="http://bbs.ruyicai.com"><s:text name="bbscs.back"/></a>]
        <s:url action="forum?action=index" id="fUrl">
        <s:param name="bid" value="%{bid}"/>
        </s:url>
        [<a href="${fUrl}"><s:text name="forum.returnforum"/></a>]
      </div>
    </td>
  </tr>
</table>
<p></p>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="bgColor5"><table width="100%" border="0" cellpadding="4" cellspacing="1">
      <tr>
        <td class="bgColor3">
          <span class="font2">[<s:text name="read.e.e"/>]</span>
          <bbscs:forum forumValue="%{forum}" type="face"/>
          <strong class="font1"><s:text name="forum.art.title"/>:<s:property value="%{forum.title}"/></strong>
        </td>
      </tr>
      <tr>
        <td class="bgColor4"><table width="96%" border="0" align="center" cellpadding="4" cellspacing="0">
          <tr>
            <td width="24%"><s:text name="forum.author"/>:<s:property value="%{forum.nickName}"/>(<s:property value="%{forum.userName}"/>)</td>
            <td width="76%"><s:text name="forum.posttime"/>:<bbscs:datetimes format="yyyy-MM-dd HH:mm:ss" datetype="timestamp" value="%{forum.postTime}"/></td>
          </tr>
          <tr>
            <td><s:text name="read.e.doename"/>:<s:property value="%{forum.doEliteName}"/></td>
            <td><s:text name="read.e.doetime"/>:<bbscs:datetimes format="yyyy-MM-dd HH:mm:ss" datetype="timestamp" value="%{forum.doEliteTime}"/></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td class="bgColor3"><table width="96%" border="0" align="center" cellpadding="4" cellspacing="0" class="table1">
          <tr>
            <td class="bgColor2">
              <div id="detail" class="postDetail">
                <bbscs:forum forumValue="%{forum}" type="detail"/>
              </div>
              <bbscs:forum forumValue="%{forum}" type="sign" itemClass="signInPost"/>
            </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td class="bgColor4">
          <div align="center">
          <s:url action="read?action=topic&&fcpage=1&fcaction=index" id="srcurl">
          <s:param name="bid" value="%{bid}"/>
          <s:param name="id" value="%{forum.mainID}"/>
          </s:url>
          [<a href="${srcurl}"><s:text name="read.e.seesrc"/></a>]
          </div>
        </td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>