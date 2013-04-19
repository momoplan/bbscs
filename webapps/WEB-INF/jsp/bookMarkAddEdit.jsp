<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<s:form action="bookMark">
<s:hidden name="action"></s:hidden>
<s:hidden name="page" id="pageNum"></s:hidden>
<s:hidden name="id" id="id"></s:hidden>
  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td class="bgColor1"><table width="100%" border="0" cellpadding="5" cellspacing="1">
        <tr class="bgColor3">
          <td colspan="2">
            <strong class="font1">
              <s:if test="%{action=='addsave'}">
              <s:text name="bookmark.new"/>
              </s:if>
              <s:if test="%{action=='editdo'}">
              <s:text name="bookmark.edit"/>
              </s:if>
            </strong>
          </td>
          </tr>
        <tr>
          <td width="20%" class="bgColor4"><s:text name="bookmark.name"/>:</td>
          <td width="80%" class="bgColor2">
            <s:textfield id="bookMarkName" name="bookMarkName" cssClass="input2" size="40" maxlength="70"></s:textfield>
          </td>
        </tr>
        <tr>
          <td class="bgColor4"><p><s:text name="bookmark.url"/>:</p>
            </td>
          <td class="bgColor2">
            <s:textfield id="url" name="url" cssClass="input2" size="40" maxlength="70"></s:textfield>
          </td>
        </tr>
        <tr>
          <td class="bgColor4"><s:text name="bookmark.alt"/>:</td>
          <td class="bgColor2">
            <s:textfield id="alt" name="alt" cssClass="input2" size="40" maxlength="70"></s:textfield>
          </td>
        </tr>
        <tr>
          <td class="bgColor4"><s:text name="bookmark.share"/>:</td>
          <td class="bgColor2">
            <s:checkbox id="isShare" name="isShare" fieldValue="1"></s:checkbox>
            <s:text name="bbscs.yes"/>
          </td>
        </tr>
        <tr>
          <td class="bgColor3">&nbsp;</td>
          <td class="bgColor3">
            <s:if test="%{action=='addsave'}">
              <input type="button" name="saveButton" value="<s:text name="bbscs.botton.save"/>" class="button1" onclick="bookMarkAdd();"/>
            </s:if>
            <s:if test="%{action=='editdo'}">
              <input type="button" name="saveButton" value="<s:text name="bbscs.botton.save"/>" class="button1" onclick="bookMarkEdit();"/>
            </s:if>
            <input type="button" name="closeButton" value="<s:text name="bbscs.close"/>" class="button1" onclick="closeAddEditBookMark();"/>
          </td>
        </tr>
      </table></td>
    </tr>
  </table>
</s:form>