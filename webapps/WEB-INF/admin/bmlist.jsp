<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<table class="table6" width="100%" border="0" cellpadding="3" cellspacing="0">

  <s:if test="%{bmlist.isEmpty()}">
    <tr>
      <td>
        <strong>尚无版主</strong>
      </td>
    </tr>
  </s:if>
  <s:else>
  <s:iterator id="bm" value="%{bmlist}">
      <tr>
        <td width="60%">
          <a href="javascript:;" onclick="editBoardMasterLoad('<s:property value="%{bid}"/>','<s:property value="#bm.userName"/>');"><s:property value="#bm.userName"/></a>
        </td>
        <td align="right" width="40%">
          [<a href="javascript:;" onclick="adminDelBm('<s:property value="%{bid}"/>','<s:property value="#bm.userName"/>');">删除</a>]
        </td>
      </tr>
  </s:iterator>
  </s:else>
  <tr>
    <td colspan="2" align="right">
      <a href="javascript:;" onclick="closeBms('<s:property value="%{bid}"/>');">X</a>
    </td>
  </tr>
</table>