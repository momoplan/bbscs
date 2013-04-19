<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="bgColor1"><table width="100%" border="0" cellpadding="5" cellspacing="1">
      <tr class="bgColor3">
        <td colspan="4" class="font1"><strong><s:text name="bookmark.my"/></strong></td>
      </tr>
      <s:iterator value="%{pageList.objectList}" id="bookMark">
      <tr>
        <td width="70%" class="bgColor2">
          <span class="font1">
            <a href="<s:property value="#bookMark.url"/>" title="<s:property value="#bookMark.alt"/>" target="_blank"><s:property value="#bookMark.bookMarkName"/></a>
          </span>
        </td>
        <td width="10%" class="bgColor4">
          <div id="shareDiv<s:property value="#bookMark.id"/>" align="center">
            <s:if test="#bookMark.isShare==1">
            <a href="javascript:;" onclick="bookMarkShare('<s:property value="#bookMark.id"/>','0','<s:property value="%{pageList.pages.page}"/>');"><s:text name="bookmark.unshare"/></a>
            </s:if>
            <s:else>
            <a href="javascript:;" onclick="bookMarkShare('<s:property value="#bookMark.id"/>','1','<s:property value="%{pageList.pages.page}"/>');"><s:text name="bookmark.share"/></a>
            </s:else>
          </div>
        </td>
        <td width="10%" class="bgColor2">
          <div align="center">
            <a href="javascript:;" onclick="loadBookMarkEditPage('<s:property value="#bookMark.id"/>','<s:property value="%{pageList.pages.page}"/>');"><s:text name="bbscs.update"/></a>
          </div>
        </td>
        <td width="10%" class="bgColor4">
          <div align="center">
            <a href="javascript:;" onclick="bookMarkDel('<s:property value="#bookMark.id"/>','<s:property value="%{pageList.pages.page}"/>');"><s:text name="bbscs.del"/></a>
          </div>
        </td>
      </tr>
      </s:iterator>
      <tr class="bgColor3">
        <td colspan="4">
          <s:text name="bbscs.pagebreak"/>:<bbscs:pages value="%{pageList.pages}" javaScript="loadBookMarkListPageUrl"/>
          [<strong><a href="javascript:;" onclick="loadBookMarkAddPage();"><s:text name="bookmark.new"/></a></strong>]</td>
        </tr>
    </table></td>
  </tr>
</table>