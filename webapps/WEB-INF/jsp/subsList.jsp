<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<table width="100%" border="0" cellpadding="5" cellspacing="1">
  <tr>
    <td width="60%" class="bgColor2"><div align="center"><s:text name="forum.art.title"/></div></td>
    <td colspan="2" class="bgColor4"><div align="center"><s:text name="subs.type"/></div></td>
    <td width="10%" class="bgColor2"><div align="center"><s:text name="bbscs.del"/></div></td>
  </tr>
<s:iterator id="subs" value="%{pageList.objectList}">
<tr>
  <td class="bgColor2">
    <s:url action="read?action=topic" id="postUrl">
    <s:param name="bid" value="#subs.boardID"/>
    <s:param name="id" value="#subs.postID"/>
    <s:param name="fcpage" value="1"/>
    <s:param name="fcaction" value="index"/>
    </s:url>
    <a href="${postUrl}"><span class="font1"><s:property value="#subs.postTitle"/></span></a>
  </td>
  <td width="15%" class="bgColor4">
    <div id="email<s:property value="#subs.id"/>" align="center">
      <s:if test="#subs.emailinform==0">
      --
      </s:if>
      <s:if test="#subs.emailinform==1">
      Email [<a href="javascript:;" onclick="cancleSubs('delemail','<s:property value="#subs.boardID"/>','<s:property value="#subs.id"/>','<s:property value="%{page}"/>');"><s:text name="bbscs.cancel"/></a>]
      </s:if>
    </div>
  </td>
  <td width="15%" class="bgColor4">
    <div id="msg<s:property value="#subs.id"/>" align="center">
      <s:if test="#subs.msginform==0">
      --
      </s:if>
      <s:if test="#subs.msginform==1">
      <s:text name="pot.sendnote"/> [<a href="javascript:;" onclick="cancleSubs('delmsg','<s:property value="#subs.boardID"/>','<s:property value="#subs.id"/>','<s:property value="%{page}"/>');"><s:text name="bbscs.cancel"/></a>]
      </s:if>
    </div>
  </td>
  <td class="bgColor2">
    <div align="center"><a href="javascript:;" onclick="cancleSubs('del','<s:property value="#subs.boardID"/>','<s:property value="#subs.id"/>','<s:property value="%{page}"/>');"><s:text name="bbscs.del"/></a></div>
  </td>
</tr>
</s:iterator>
<tr>
  <td colspan="4" class="bgColor3"><s:text name="bbscs.pagebreak"/>:<bbscs:pages value="%{pageList.pages}" javaScript="loadSubsListUrl"/></td>
</tr>
</table>