<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<s:form action="adminBoardMasterSet">
<s:hidden name="action"></s:hidden>
<s:hidden id="boardID" name="boardID"></s:hidden>
<table class="table6" width="100%" border="0" cellpadding="5" cellspacing="0">
    <tr>
      <td colspan="2">
        <s:if test="%{action=='addsave'}">
        <strong>增加版主</strong>
        </s:if>
        <s:elseif test="%{action=='editsave'}">
        <strong>修改版主</strong>
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
      <td width="60%">版主用户名</td>
      <td width="40%">
        <s:if test="%{action=='addsave'}">
        <s:textfield id="userName" name="userName" cssClass="input2" size="30"></s:textfield>
        </s:if>
        <s:elseif test="%{action=='editsave'}">
        <s:textfield id="userName" name="userName" cssClass="input2" size="30" readonly="true"></s:textfield>
        </s:elseif>
      </td>
    </tr>
    <tr>
      <td>角色</td>
      <td>
        <s:select list="roleValues" name="roleID" id="roleID" listKey="key" listValue="value" cssClass="select1"></s:select>
      </td>
    </tr>
    <tr>
      <td>权限是否覆盖子版区</td>
      <td>
        <s:radio list="radioYesNoList" name="overChildPurview" listKey="key" listValue="value"></s:radio>
      </td>
    </tr>
    <tr>
      <td>是否在版主列表中隐藏</td>
      <td>
        <s:radio list="radioYesNoList" name="isHidden" listKey="key" listValue="value"></s:radio>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <div align="center">
          <s:if test="%{action=='addsave'}">
          <input type="button" name="Submit" value="<s:property value="%{getText('bbscs.botton.submit')}"/>" class="button1" onclick="adminAddBm();"/>
          </s:if>
          <s:elseif test="%{action=='editsave'}">
          <input type="button" name="Submit" value="<s:property value="%{getText('bbscs.botton.submit')}"/>" class="button1" onclick="adminEditBm();"/>
          </s:elseif>
          <input type="button" name="Close" value="<s:property value="%{getText('bbscs.close')}"/>" class="button1" onclick="cloesBoardMasterPage();"/>
        </div>
      </td>
    </tr>
</table>
</s:form>