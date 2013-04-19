<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
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
                  <strong class="font1"><s:text name="note.box"/>:<s:text name="note.outbox"/></strong>
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
            <div align="center"></div>
          </td>
          <td width="90%" class="bgColor2">
            <table width="100%" border="0" cellpadding="3" cellspacing="0">
              <tr>
                <td width="76%">
                  <a href="javascript:;" onclick="loadNoteReadOutbox('<s:property value="#note.id"/>','<s:property value="%{pageList.pages.page}"/>');"><s:property value="#note.noteTitle"/></a>
                  <br/>
                  <s:text name="note.tousername1"/>:<span class="font3"><s:property value="#note.toNickName"/>[<s:property value="#note.toUserName"/>]</span>
                </td>
                <td width="24%" valign="top">
                  <div align="right"><bbscs:datetimes format="yyyy-MM-dd HH:mm:ss" datetype="date" value="#note.createTime"/></div>
                </td>
              </tr>
              <tr>
                <td colspan="2">
                  <div id="noteDiv<s:property value="#note.id"/>" class="noteClass1" style="display:none">
                    <div id="noteDetail<s:property value="#note.id"/>"></div>
                    <div id="noteSend<s:property value="#note.id"/>"></div>
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
                <td width="67%"><s:text name="bbscs.pagebreak"/>:<bbscs:pages value="%{pageList.pages}" javaScript="loadNoteOutbox"/> <a href="javascript:;" onclick="delAllOutBox();"><s:text name="note.delall"/></a></td>
                <td width="33%">
                  <div align="right"><s:text name="note.selected"/>:
                    <select name="noteOp" class="select1">
                      <option value="1" selected="selected"><s:text name="bbscs.del"/></option>
                      <option value="2"><s:text name="note.getout"/></option>
                    </select>
                    <input name="Submit" type="submit" class="button1" onclick="noteOpOutBox();" value="<s:text name="bbscs.exe"/>"/>
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