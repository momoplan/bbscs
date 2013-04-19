<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td>
    <s:property value="%{note.noteContext}" escape="false"/>
    </td>
  </tr>

  <tr>
    <td>
      <div align="right">
      <s:if test="%{note.noteType==1}">
      <span id="noteReImg<s:property value="%{note.id}"/>" align="right"></span>
      <s:if test="%{note.isRe==1}">
      <img src="images/note_replied.gif" alt=""/>
      </s:if>
        [<a href="javascript:;" onclick="loadNoteSendInNote('<s:property value="%{note.id}"/>');"><s:text name="bbscs.re"/></a>]
        [<a href="javascript:;" onclick="deleteInboxNote('<s:property value="%{note.id}"/>','<s:property value="%{page}"/>');"><s:text name="bbscs.del"/></a>]
      </s:if>
      <s:else>
        [<a href="javascript:;" onclick="deleteOutboxNote('<s:property value="%{note.id}"/>','<s:property value="%{page}"/>');"><s:text name="bbscs.del"/></a>]
      </s:else>
      [<a href="javascript:;" onclick="closeNoteDiv('<s:property value="%{note.id}"/>');"><s:text name="bbscs.close"/></a>]
      </div>
    </td>
  </tr>
</table>