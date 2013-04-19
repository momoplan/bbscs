<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<s:form action="friendSet">
<s:hidden name="action"></s:hidden>
<s:hidden name="isBlack" id="isBlack"></s:hidden>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
    <tr>
      <td colspan="2">
        <s:if test="%{isBlack==0}">
        <strong><s:text name="friend.add"/></strong>
        </s:if>
        <s:if test="%{isBlack==1}">
        <strong><s:text name="friend.addblack"/></strong>
        </s:if>
      </td>
    </tr>
    <tr>
      <td width="18%"><s:text name="friend.username"/></td>
      <td width="82%">
        <s:textfield id="friendName" name="friendName" cssClass="input2" size="40" maxlength="20" onkeypress="return handleEnter(this, event);"></s:textfield>
      </td>
    </tr>
    <tr>
      <td valign="top"><s:text name="friend.des"/></td>
      <td>
        <s:textarea id="friendComment" name="friendComment" cols="40" rows="5" cssClass="textarea1"></s:textarea>
      </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>
        <input type="button" name="Save" value="<s:text name="bbscs.add"/>" class="button1" onclick="friendAdd();"/>
        <input type="button" name="Close" value="<s:text name="bbscs.close"/>" class="button1" onclick="closeFriendNewPage();"/>
      </td>
    </tr>
</table>
</s:form>
