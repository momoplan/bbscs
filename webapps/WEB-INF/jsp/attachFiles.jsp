<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<form id="attachForm<s:property value="%{id}"/>" name="attachForm<s:property value="%{id}"/>" method="post" action="">
  <input name="id" type="hidden" value="<s:property value="%{id}"/>" />
  <input name="bid" type="hidden" value="<s:property value="%{bid}"/>" />
  <table width="100%" border="0" cellpadding="3" cellspacing="0">
    <s:iterator id="af" value="%{attachFiles}" status="s">
    <tr>
      <td width="5%">
        <div align="center">
          <input type="checkbox" name="attachFileName<s:property value="%{id}"/>" value="<s:property value="#af"/>" />
        </div>
      </td>
      <td width="95%"><s:text name="post.attachfile"/>[<s:property value="#s.getIndex()"/>]:<s:property value="#af"/></td>
    </tr>
    </s:iterator>
    <tr>
      <td colspan="2">
        <input type="button" name="Submit" value="<s:text name="bbscs.del"/>" onclick="delAttachFile('<s:property value="%{bid}"/>','<s:property value="%{id}"/>');" class="button1" />
        <input type="button" name="CloseAttchFile" value="<s:text name="bbscs.close"/>" onclick="closeShowUpfilePage('<s:property value="%{id}"/>');" class="button1" />
      </td>
    </tr>
  </table>
</form>