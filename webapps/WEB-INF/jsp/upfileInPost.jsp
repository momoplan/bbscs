<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<table width="100%" border="0" cellpadding="2" cellspacing="0">
  <tr>
    <td>
      <iframe id="upfileIframe" src="<s:property value="%{upfileIframeUrl}"/>" height="90" width="100%" scrolling="no" frameborder="0"></iframe>
    </td>
  </tr>
  <tr>
    <td><div align="right">[<a href="javascript:;" onclick="closeShowUpfilePage('<s:property value="%{id}"/>');"><s:text name="bbscs.close"/></a>]</div></td>
  </tr>
</table>