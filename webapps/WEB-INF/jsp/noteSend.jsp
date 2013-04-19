<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<s:form action="note">
<s:hidden name="action"></s:hidden>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="bgColor1">
      <table width="100%" border="0" cellpadding="5" cellspacing="1">
        <tr>
          <td colspan="2" class="bgColor3">
            <strong class="font1"><s:text name="note.sendnew"/></strong>
          </td>
        </tr>
        <tr>
          <td width="14%" class="td3"><s:text name="note.tousername"/>:</td>
          <td width="86%" class="bgColor2">
            <s:textfield id="toUserName" name="toUserName" cssClass="input2" size="40" maxlength="20"></s:textfield>
          </td>
        </tr>
        <tr>
          <td class="td3"><s:text name="note.msg.title"/>:</td>
          <td class="bgColor2">
            <s:textfield id="noteTitle" name="noteTitle" cssClass="input2" size="40" maxlength="50"></s:textfield>
          </td>
        </tr>
        <tr>
          <td valign="top" class="td3"><s:text name="note.content"/>:</td>
          <td class="bgColor2">
            <s:textarea id="noteContext" name="noteContext" cols="40" rows="5" cssClass="textarea1"></s:textarea>
          </td>
        </tr>
        <tr>
          <td class="td3"><s:text name="note.needre"/>:</td>
          <td class="bgColor2">
            <s:checkbox id="needRe" name="needRe" fieldValue="1"></s:checkbox>
            <s:text name="note.needre.notice"/>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="bgColor3">
            <input type="button" name="noteSendButton" value="<s:text name="bbscs.send"/>" class="button1" onclick="noteAdd();"/>
            <input type="button" name="noteSendCloseButton" value="<s:text name="bbscs.close"/>" class="button1" onclick="closeNoteSend();"/>
          </td>
        </tr>
      </table>
    </td>
  </tr>

</table>
</s:form>