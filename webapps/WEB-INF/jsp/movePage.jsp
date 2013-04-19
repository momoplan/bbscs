<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<s:form action="moveForum">
<s:hidden name="action"></s:hidden>
<s:hidden id="frombid" name="bid"></s:hidden>
<s:hidden id="fromid" name="id"></s:hidden>
<s:text name="movepage.moveto"/>:
<s:select list="boardSelectValues" cssClass="select1" listKey="key" listValue="value" name="tobid" id="tobid"></s:select>
<s:submit cssClass="button1" value="%{getText('post.move.showpage')}"></s:submit>
<input type="button" name="CloseMovePage" value="<s:text name="bbscs.close"/>" onclick="closeShowUpfilePage('<s:property value="%{id}"/>');" class="button1" />
</s:form>