<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<table width="100%" border="0" cellpadding="4" cellspacing="0">
  <tr class="trClass">
    <td width="25%">
      <strong><s:text name="friend.username"/></strong>
    </td>
    <td width="60%">
      <strong><s:text name="friend.des"/></strong>
    </td>
    <td width="15%">
      <strong><s:text name="friend.opt"/></strong>
    </td>
  </tr>
  <s:iterator id="f" value="%{friendList}">
    <tr>
      <td>
        <s:property value="#f.friendName"/>
      </td>
      <td>
        <s:property value="#f.friendComment"/>
      </td>
      <td><a href="javascript:;" onclick="friendDel('<s:property value="#f.id"/>','<s:property value="#f.isBlack"/>');"><s:text name="bbscs.del"/></a></td>
    </tr>
  </s:iterator>
  <tr>
    <td colspan="3">
      <s:if test="%{isBlack==0}">
      <a href="javascript:;" onclick="friendNew('0');"><s:text name="friend.add"/></a>
      </s:if>
      <s:if test="%{isBlack==1}">
      <a href="javascript:;" onclick="friendNew('1');"><s:text name="friend.addblack"/></a>
      </s:if>
    </td>
  </tr>
</table>

