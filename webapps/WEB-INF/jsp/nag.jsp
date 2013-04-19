<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>
  <bbscs:webinfo type="forumname"/>-<s:text name="nag.title"/>
  </title>
  <bbscs:webinfo type="meta"/>
  <link href="css/css1.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/xmlHttpRequest.js"></script>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script language="JavaScript" type="text/javascript">
<!--
function loadChild(bid,btype) {
  var obj = document.getElementById("child" + bid);
  var imgObj = document.getElementById("img" + bid);
  if (obj.style.display == "none") {
    obj.style.display = "block";
    imgObj.src="images/collapse.gif";
  }
  else {
    obj.style.display = "none";
    imgObj.src="images/expand.gif";
  }
}


function loadBoardSave() {

  var obj = document.getElementById("boardSaveDiv");
  var imgObj = document.getElementById("boardSaveImg");
  if (obj.style.display == "none") {
    obj.style.display = "block";
    imgObj.src="images/collapse.gif";
  }
  else {
    obj.style.display = "none";
    imgObj.src="images/expand.gif";
  }
}

function loadUserCenter() {
  var obj = document.getElementById("userCenterDiv");
  var imgObj = document.getElementById("imgUserCenterSet");
  if (obj.style.display == "none") {
    obj.style.display = "block";
    imgObj.src="images/collapse.gif";
  }
  else {
    obj.style.display = "none";
    imgObj.src="images/expand.gif";
  }
}

function loadUserLogout() {
  var obj = document.getElementById("userLogoutDiv");
  var imgObj = document.getElementById("imgLogout");
  if (obj.style.display == "none") {
    obj.style.display = "block";
    imgObj.src="images/collapse.gif";
  }
  else {
    obj.style.display = "none";
    imgObj.src="images/expand.gif";
  }
}
//-->
</script>
  <base target="mainFrame"/>
  </head>
  <body class="nagbody">
  <div class="nag" id="bnag0">
    <ul>
      <li>
      <s:url action="in" id="inUrl"></s:url>
      <a href="${inUrl}"><img id="imgIn" src="images/signalnode.gif" alt="" width="25" height="15" border="0" align="absmiddle"/><s:text name="in.title"/></a>
      </li>
      <li><a href="javascript:;" onclick="loadUserCenter();"><img id="imgUserCenterSet" src="images/expand.gif" alt="" width="25" height="15" border="0" align="absmiddle"/><s:text name="nag.usercenter"/></a></li>
      <div id="userCenterDiv" class="nag" style="display:none">
        <ul>
          <%-- <li>
          <s:url action="signSet" id="signSetUrl"></s:url>
          <a href="${signSetUrl}"><img id="imgSignSet" src="images/node.gif" alt="" border="0" align="absmiddle"/><s:text name="signset.title"/></a>
          </li> --%>
          <li>
          <s:url action="nickNameSet" id="nickNameSetUrl"></s:url>
          <a href="${nickNameSetUrl}"><img id="imgNickNameSet" src="images/node.gif" alt="" border="0" align="absmiddle"/><s:text name="nickset.title"/></a>
          </li>
          <s:url action="userConfig" id="userConfigUrl"></s:url>
          <li><a href="${userConfigUrl}"><img id="imgUserConfig" src="images/node.gif" alt="" border="0" align="absmiddle"/><s:text name="userconfig.title"/></a></li>
          <s:url action="friendSet" id="friendSetUrl"></s:url>
          <li><a href="${friendSetUrl}"><img id="imgFriendSet" src="images/node.gif" alt="" border="0" align="absmiddle"/><s:text name="friend.title"/></a></li>
          <s:url action="note" id="noteUrl"></s:url>
          <li><a href="${noteUrl}"><img id="imgNote" src="images/node.gif" alt="" border="0" align="absmiddle"/><s:text name="note.title"/></a></li>
          <s:url action="bookMark" id="bookMarkUrl"></s:url>
          <li><a href="${bookMarkUrl}"><img id="imgBookMark" src="images/node.gif" alt="" border="0" align="absmiddle"/><s:text name="bookmark.title"/></a></li>
          <s:url action="userFace?action=index" id="userFaceUrl"></s:url>
          <li><a href="${userFaceUrl}"><img id="imgFace" src="images/node.gif" alt="" border="0" align="absmiddle"/><s:text name="face.title"/></a></li>
          <s:url action="cpasswd?action=index" id="cpasswdUrl"></s:url>
          <li><a href="${cpasswdUrl}"><img id="imgCpasswd" src="images/node.gif" alt="" border="0" align="absmiddle"/><s:text name="cpasswd.title"/></a></li>
          <s:url action="boardSaveManage" id="boardSaveManageUrl"></s:url>
          <li><a href="${boardSaveManageUrl}"><img id="imgboardSaveManage" src="images/node.gif" alt="" border="0" align="absmiddle"/><s:text name="boardsave.title"/></a></li>
        </ul>
      </div>
    </ul>
  </div>
  <hr width="90" align="left"/>
  <div class="nag" id="bnag1">
    <ul>
    <li><a href="javascript:;" onclick="loadBoardSave();"><img id="boardSaveImg" src="images/expand.gif" alt="展开" width="25" height="15" border="0" align="absmiddle"/><s:text name="nag.boardsave"/></a>
    <div id="boardSaveDiv" class="nag" style="display:none">
    <ul>
    <s:iterator id="bs" value="%{boardSaveList}">
    <li>
    <s:if test="%{urlRewrite}">
    <img id="img<s:property value="#bs.id"/>" src="images/node.gif" alt="" align="absmiddle"/><a href="forum-index-<s:property value="#bs.id"/>.html"><s:property value="#bs.boardName"/></a>
    </s:if>
    <s:else>
    <s:url action="forum?action=index" id="furl">
    <s:param name="bid" value="#bs.id"/>
    </s:url>
    <img id="img<s:property value="#bs.id"/>" src="images/node.gif" alt="" align="absmiddle"/><a href="${furl}"><s:property value="#bs.boardName"/></a>
    </s:else>
    </li>
    </s:iterator>
    </ul>
    </div>
    </li>

    <s:iterator id="b1" value="%{boardList}">
    <li>
    <s:if test="%{urlRewrite}">
    <a href="javascript:;" onclick="loadChild('<s:property value="#b1.id"/>','<s:property value="#b1.boardType"/>')"><img id="img<s:property value="#b1.id"/>" src="images/expand.gif" alt="展开" width="25" height="15" border="0" align="absmiddle"/></a><a href="forum-index-<s:property value="#b1.id"/>.html"><s:property value="#b1.boardName"/></a>
    </s:if>
    <s:else>
    <s:url action="forum?action=index" id="furl">
    <s:param name="bid" value="#b1.id"/>
    </s:url>
    <a href="javascript:;" onclick="loadChild('<s:property value="#b1.id"/>','<s:property value="#b1.boardType"/>')"><img id="img<s:property value="#b1.id"/>" src="images/expand.gif" alt="展开" width="25" height="15" border="0" align="absmiddle"/></a><a href="${furl}"><s:property value="#b1.boardName"/></a>
    </s:else>
    <div id="child<s:property value="#b1.id"/>" class="nag" style="display:none">
    <s:set name="bl2" value="%{boardMap.get(#b1.id)}"></s:set>
    <ul>
    <s:iterator id="b" value="#bl2">
    <s:url action="forum?action=index" id="burl">
    <s:param name="bid" value="#b.id"/>
    </s:url>
    <li>
    <s:if test="%{urlRewrite}">
    <img id="img<s:property value="#b.id"/>" src="images/node.gif" alt="" align="absmiddle"/><a href="forum-index-<s:property value="#b.id"/>.html"><s:property value="#b.boardName"/></a>
    </s:if>
    <s:else>
    <s:url action="forum?action=index" id="burl">
    <s:param name="bid" value="#b.id"/>
    </s:url>
    <img id="img<s:property value="#b.id"/>" src="images/node.gif" alt="" align="absmiddle"/><a href="${burl}"><s:property value="#b.boardName"/></a>
    </s:else>
    </li>
    </s:iterator>
    </ul>
    </div>
    </li>
    </s:iterator>
    </ul>
  </div>
  <div class="nag" id="bnag2">
    <ul>
    <!--
      <s:if test="%{usePass}">
      <li><a href="javascript:;" onclick="loadUserLogout();"><img id="imgLogout" src="images/expand.gif" alt="" width="25" height="15" border="0" align="absmiddle"/><s:text name="nag.logout"/></a></li>
      <div id="userLogoutDiv" class="nag" style="display:none">
      <ul>
      <s:url action="logout" id="logoutUrl"></s:url>
      <li><a href="${logoutUrl}" target="_top"><img id="imgLogout1" src="images/signalnode.gif" alt="" width="25" height="15" border="0" align="absmiddle"/><s:text name="nag.logout"/></a></li>
      <s:url action="logout?action=pass" id="logoutPassUrl"></s:url>
      <li><a href="${logoutPassUrl}" target="_top"><img id="imgLogout2" src="images/signalnode.gif" alt="" width="25" height="15" border="0" align="absmiddle"/><s:text name="nag.logout"/></a></li>
      </ul>
      </div>
      </s:if>
      <s:else>
      <s:url action="logout" id="logoutUrl"></s:url>
      <li><a href="${logoutUrl}" target="_top"><img id="imgLogout" src="images/signalnode.gif" alt="" width="25" height="15" border="0" align="absmiddle"/><s:text name="nag.logout"/></a></li>
      </s:else>
       -->
      <s:url action="logout" id="logoutUrl"></s:url>
      <li><a href="${logoutUrl}" target="_top"><img id="imgLogout" src="images/signalnode.gif" alt="" width="25" height="15" border="0" align="absmiddle"/><s:text name="nag.logout"/></a></li>
    </ul>
  </div>
  </body>
</html>