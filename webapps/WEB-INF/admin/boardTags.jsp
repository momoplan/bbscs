<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<table class="table6" width="100%" border="0" cellpadding="3" cellspacing="0">

  <s:if test="%{tags.isEmpty()}">
    <tr>
      <td>
        <strong>尚无Tag</strong>
      </td>
    </tr>
  </s:if>
  <s:else>
  <s:iterator id="tag" value="%{tags}">
      <tr>
        <td width="60%">
          ·
          <a href="javascript:;" onclick="showEditTagPage('<s:property value="%{bid}"/>','<s:property value="#tag.id"/>');"><s:property value="#tag.tagName"/></a>
        </td>
        <td align="right" width="40%">
          [<a href="javascript:;" onclick="delTag('<s:property value="%{bid}"/>','<s:property value="#tag.id"/>');">删除</a>]
        </td>
      </tr>
  </s:iterator>
  </s:else>
  <tr>
    <td colspan="2" align="right">
      <a href="javascript:;" onclick="closeTag('<s:property value="%{bid}"/>');">X</a>
    </td>
  </tr>
</table>