<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="bgColor1"><table width="100%" border="0" cellpadding="5" cellspacing="1">
      <tr>
        <td class="bgColor3"><span class="font1"><strong><s:text name="bookmark.shareall"/></strong></span></td>
      </tr>
      <s:iterator value="%{pageList.objectList}" id="bookMark">

      <tr>
        <td class="bgColor4">
          <span class="font1">
            <a href="<s:property value="#bookMark.url"/>" title="<s:property value="#bookMark.alt"/>" target="_blank"><s:property value="#bookMark.bookMarkName"/></a>
          </span>
        </td>
      </tr>
      </s:iterator>
      <tr>
        <td class="bgColor3"><s:text name="bbscs.pagebreak"/>:<bbscs:pages value="%{pageList.pages}" javaScript="loadBookMarkListPageUrl"/></td>
      </tr>
    </table></td>
  </tr>
</table>