<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%@page import="com.laoer.bbscs.comm.*"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">

  <tr>
    <td class="bgColor1">
      <table width="100%" border="0" cellpadding="5" cellspacing="1">
        <tr>
          <td colspan="3" class="bgColor3">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="74%">
                  <strong class="font1"><s:text name="note.box"/>:<s:text name="note.inbox"/></strong>
                </td>
                <td width="26%">
                  <div align="right">
                    <span class="font1"><strong><s:text name="note.title"/>:<s:property value="%{pageList.pages.totalNum}"/></strong></span>
                    <input id="checkall" type="checkbox" name="checkall" value="checkall" onclick="checkAll();"/>
                  </div>
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <s:iterator value="%{pageList.objectList}" id="note">
        <tr>
          <td width="6%" class="td3">
            <div id="noteIsNew<s:property value="#note.id"/>" align="center">
              <s:if test="#note.isNew==0">
              <img src="images/note_old.gif" alt=""/>
              </s:if>
              <s:elseif test="#note.isNew==1">
              <img src="images/note_new.gif" alt=""/>
              </s:elseif>
            </div>
          </td>
          <td width="90%" class="bgColor2">
            <table width="100%" border="0" cellpadding="3" cellspacing="0">
              <tr>
                <td width="76%">
                  <a href="javascript:;" onclick="loadNoteReadInbox('<s:property value="#note.id"/>','<s:property value="%{pageList.pages.page}"/>');"><s:property value="#note.noteTitle"/></a>
                  <br/>
                  <s:url action="userInfo?action=id" id="fromUserUrl">
                  <s:param name="id" value="#note.fromID"/>
                  </s:url>
                  <s:text name="note.fromuser"/>:<span class="link1"><a href="${fromUserUrl}"><s:property value="#note.fromNickName"/>[<s:property value="#note.fromUserName"/>]</a></span>
                </td>
                <td width="24%" valign="top">
                  <div align="right"><bbscs:datetimes format="yyyy-MM-dd HH:mm:ss" datetype="date" value="#note.createTime"/></div>
                </td>
              </tr>
              <tr>
                <td colspan="2">
                  <div id="noteDiv<s:property value="#note.id"/>" class="noteClass1" style="display:none">
                    <div id="noteDetail<s:property value="#note.id"/>"></div>
                    <div id="noteSend<s:property value="#note.id"/>" style="display:none">
                      <form action="<%=BBSCSUtil.getActionMappingURL("/note",request)%>" name="noteSendForm<s:property value="#note.id"/>">
                      <INPUT TYPE="hidden" name="id" value="<s:property value="#note.id"/>">
                      <table width="100%" border="0" cellpadding="5" cellspacing="0">

                        <tr>
                          <td width="15%"><s:text name="note.tousername"/>:</td>
                          <td width="85%"><input name="toUserName" type="text" value="<s:property value="#note.fromUserName"/>" readonly="readonly" class="input2" size="40" /></td>
                        </tr>
                        <tr>
                          <td width="15%"><s:text name="note.msg.title"/>:</td>
                          <td width="85%"><input name="noteTitle" type="text" class="input2" size="40" /></td>
                        </tr>
                        <tr>
                          <td valign="top"><s:text name="note.content"/>:</td>
                          <td><textarea name="noteContext" cols="40" rows="5" class="textarea1"></textarea></td>
                        </tr>
                        <tr>
                          <td><s:text name="note.needre"/>:</td>
                          <td><input type="checkbox" name="needRe" value="1" />
                            <s:text name="note.needre.notice"/></td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                          <td>
                            <input name="Submit2" type="button" class="button1" onclick="noteRe('<s:property value="#note.id"/>');" value="<s:text name="bbscs.re"/>" />
                            <input type="button" name="closeSendInButton" class="button1" onclick="closeNoteSendInNote('<s:property value="#note.id"/>');" value="<s:text name="bbscs.close"/>"/>
                          </td>
                        </tr>

                      </table>
                      </form>
                    </div>
                    <div id="needRe<s:property value="#note.id"/>" style="display:none"><s:property value="#note.needRe"/></div>
                  </div>
                </td>
              </tr>
            </table>
          </td>
          <td width="4%" class="td3">
            <div align="center">
              <input type="checkbox" name="ids" value="<s:property value="#note.id"/>"/>
            </div>
          </td>
        </tr>
        </s:iterator>
        <tr>
          <td colspan="3" class="bgColor3">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="67%"><s:text name="bbscs.pagebreak"/>: <bbscs:pages value="%{pageList.pages}" javaScript="loadNoteInboxUrl"/> <a href="javascript:;" onclick="delAllInBox();"><s:text name="note.delall"/></a></td>
                <td width="33%">
                  <div align="right"><s:text name="note.selected"/>:
                    <select name="noteOp" class="select1">
                      <option value="1" selected="selected"><s:text name="bbscs.del"/></option>
                      <option value="2"><s:text name="note.getout"/></option>
                    </select>
                    <input name="Submit" type="submit" class="button1" onclick="noteOpInBox();" value="<s:text name="bbscs.exe"/>"/>
                  </div>
                  <div id="cpage" style="display:none"><s:property value="%{pageList.pages.page}"/></div>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>

</table>