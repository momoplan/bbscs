<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<s:form action="adminBoardTag">
<s:hidden name="action"></s:hidden>
<s:hidden name="bid" id="bid"></s:hidden>
<s:hidden name="id" id="id"></s:hidden>
<table class="table6" width="100%" border="0" cellpadding="5" cellspacing="0">
    <tr>
      <td colspan="2">
        <s:if test="%{action=='addsave'}">
        <strong>增加Tag</strong>
        </s:if>
        <s:elseif test="%{action=='editsave'}">
        <strong>修改Tag</strong>
        </s:elseif>
        -
        [<s:property value="%{board.boardName}"/>]
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <div id="resultMegs"/>
      </td>
    </tr>
    <tr>
      <td width="60%">Tag名称</td>
      <td width="40%">
        <s:textfield id="tagName" name="tagName" cssClass="input2" size="30"></s:textfield>
      </td>
    </tr>
    <tr>
      <td width="60%">顺序</td>
      <td width="40%">
        <s:textfield id="orders" name="orders" cssClass="input2" size="6"></s:textfield>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <div align="center">
          <s:if test="%{action=='addsave'}">
          <input type="button" name="Submit" value="<s:property value="%{getText('bbscs.botton.submit')}"/>" class="button1" onclick="addNewTag();"/>
          </s:if>
          <s:elseif test="%{action=='editsave'}">
          <input type="button" name="Submit" value="<s:property value="%{getText('bbscs.botton.submit')}"/>" class="button1" onclick="editaTag();"/>
          </s:elseif>
          <input type="button" name="Close" value="<s:property value="%{getText('bbscs.close')}"/>" class="button1" onclick="cloesBoardMasterPage();"/>
        </div>
      </td>
    </tr>
</table>
</s:form>