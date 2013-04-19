<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<s:form action="signSet">
<s:hidden name="action"></s:hidden>
<s:hidden id="signID" name="signID"></s:hidden>
<table width="100%" border="0" cellpadding="5" cellspacing="0" class="table6">
  <tr>
    <td>
      <strong><s:text name="signset.title"/></strong>
    </td>
  </tr>
  <tr>
    <td>
      <input type="button" name="Submit" value="<s:text name="signset.save"/>" class="button1" onclick="signEditDo();"/>
      <input type="button" name="ClosePage" value="<s:text name="bbscs.close"/>" class="button1" onclick="closeSignEditPage();"/>
    </td>
  </tr>
  <tr>
    <td>
      <s:textarea id="signDetail" name="signDetail" cols="80" rows="8" cssClass="textarea1"></s:textarea>
      <a href="javascript:;" onclick="loadSmilePage('signDetail');"><s:text name="signset.smile"/></a>
    </td>
  </tr>
  <tr>
    <td>
      <div id="smileDiv"></div>
    </td>
  </tr>
  <tr>
    <td>
      <input type="button" name="Submit" value="<s:text name="signset.save"/>" class="button1" onclick="signEditDo();"/>
      <input type="button" name="ClosePage" value="<s:text name="bbscs.close"/>" class="button1" onclick="closeSignEditPage();"/>
    </td>
  </tr>
</table>
</s:form>