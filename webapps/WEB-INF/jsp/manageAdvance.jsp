<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="forum.ma.title"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript">
<!--
var bid = '<s:property value="%{bid}"/>';
//-->
</script>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script type="text/javascript" src="js/mgadv.js"></script>
</head>

<body>

<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="bgColor1"><table width="100%" border="0" cellpadding="5" cellspacing="1">
      <tr>
        <td colspan="2" class="bgColor3"><span class="font1"><strong><s:text name="forum.ma.blackuser"/></strong></span></td>
        </tr>
      <tr>
        <td width="20%" class="bgColor4"><s:text name="bbscs.username"/>:</td>
        <td width="80%" class="bgColor2">
          <input name="forbidUserName" id="forbidUserName" type="text" class="input2" size="20" maxlength="20" />
          <span id="forbidMsg"></span>
        </td>
      </tr>
      <tr>
        <td class="bgColor4"><s:text name="bbscs.opt"/>:</td>
        <td class="bgColor2">
          <input type="radio" name="forbidType" value="0" checked="checked" />
          <s:text name="forum.ma.inblack"/>
          <input type="radio" name="forbidType" value="1" />
          <s:text name="forum.ma.outblack"/>
        </td>
      </tr>
      <tr>
        <td class="bgColor3">&nbsp;</td>
        <td class="bgColor3">
          <input type="button" name="forbidButton" value="<s:text name="bbscs.botton.submit"/>" class="button2" onclick="forbidUser();"/>
        </td>
      </tr>
    </table></td>
  </tr>
</table>
<p></p>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="bgColor1"><table width="100%" border="0" cellpadding="5" cellspacing="1">
      <tr>
        <td colspan="2" class="bgColor3"><span class="font1"><strong><s:text name="forum.ma.bullm"/></strong></span></td>
        </tr>
      <tr>
        <td width="20%" valign="top" class="bgColor4"><s:text name="forum.ma.bullcontent"/>:</td>
        <td width="80%" class="bgColor2">
          <textarea name="bulletin" id="bulletin" cols="60" rows="8" class="textarea1"><s:property value="%{board.bulletin}"/></textarea>
          <div id="bulletinMsg" style="display:none"></div>
        </td>
      </tr>
      <tr>
        <td class="bgColor3">&nbsp;</td>
        <td class="bgColor3">
          <input type="button" name="bulletinButton" value="<s:text name="bbscs.botton.submit"/>" class="button2" onclick="saveBulletin();"/>
        </td>
      </tr>
    </table></td>
  </tr>
</table>
<p></p>
<s:if test="%{board.isAuth==1}">
  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td class="bgColor1"><table width="100%" border="0" cellpadding="5" cellspacing="1">
        <tr>
          <td colspan="2" class="bgColor3"><span class="font1"><strong><s:text name="forum.ma.authuser"/></strong></span></td>
          </tr>
        <tr>
          <td width="20%" class="bgColor4"><s:text name="forum.ma.addauthuser"/>:</td>
          <td width="80%" class="bgColor2">
            <input id="addAuthUserName" name="addAuthUserName" type="text" class="input2" size="20" maxlength="20" />
            <input type="button" name="addAuthButton" value="<s:text name="bbscs.botton.add"/>" class="button1" onclick="addAuthUser();"/>
          </td>
        </tr>
        <tr>
          <td class="bgColor4"><s:text name="forum.ma.delauthuser"/>:</td>
          <td class="bgColor2">
            <input id="delAuthUserName" name="delAuthUserName" type="text" class="input2" size="20" maxlength="20" />
            <input type="button" name="delAuthButton" value="<s:text name="bbscs.del"/>" class="button1" onclick="delAuthUser();"/>
          </td>
        </tr>
        <tr>
          <td valign="top" class="bgColor4"><s:text name="forum.ma.cauthuser"/>:</td>
          <td class="bgColor2">
            <table width="100%" border="0" cellpadding="3" cellspacing="0">
            <tbody id="authUserList">
            <s:iterator id="au" value="%{authUsers}">
            <logic:iterate>
            <tr id="tr_<s:property value="#au.userName"/>">
              <td><s:property value="#au.userName"/></td>
              <td>[<a href="javascript:;" onclick="delAuthUserInList('<s:property value="#au.userName"/>');"><s:text name="bbscs.del"/></a>]</td>
            </tr>
            </logic:iterate>
            </s:iterator>
            </tbody>
          </table></td>
        </tr>
      </table></td>
    </tr>
  </table>
  </s:if>
  <div></div>
<div align="center">
<s:url action="forumManage?action=m" id="urlm">
<s:param name="bid" value="%{bid}"/>
</s:url>
<s:url action="forumManage?action=m" id="urlf">
<s:param name="bid" value="%{bid}"/>
</s:url>
[<a href="${urlm}"><s:text name="forum.returnmang"/></a> <a href="${urlf}"><s:text name="forum.returnforum"/></a>]</div>
</body>
</html>