<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><s:text name="note.title"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript" src="js/note.js"></script>

</head>
<body onload="loadNoteInbox();">
<table width="95%" border="0" align="center" cellspacing="0">
  <tr>
    <td>
      <fieldset>
        <legend><s:text name="note.mynote"/></legend>
        <table width="98%" border="0" align="center" cellpadding="3" cellspacing="0">
          <tr>
            <td colspan="2">
              <strong><a href="javascript:;" onclick="loadNoteSend();"><s:text name="note.sendnew"/></a></strong>
            </td>
          </tr>
          <tr>
            <td width="8%"><strong><s:text name="note.inbox"/></strong></td>
            <td width="90%">&nbsp;<span id="inboxNumDiv"></span>&nbsp;<s:text name="note.messages"/></td>
          </tr>
          <tr>
            <td><strong><s:text name="note.outbox"/></strong></td>
            <td>&nbsp;<span id="outboxNumDiv"></span>&nbsp;<s:text name="note.messages"/></td>
          </tr>
          <tr>
            <td colspan="2"><s:text name="note.changebox"/>:</td>
          </tr>
          <tr>
            <td colspan="2">
              <select id="boxSelect" name="boxSelect" class="select1" onchange="changeBox();">
                <option value="1" selected="selected"><s:text name="note.inbox"/></option>
                <option value="2"><s:text name="note.outbox"/></option>
              </select>
              <input type="button" name="changeBoxButton" value="<s:text name="bbscs.confirm"/>" class="button1" onclick="changeBox();"/>
            </td>
          </tr>
        </table>
      </fieldset>
    </td>
  </tr>
</table>
<div id="noteSendDiv"></div>
<p></p>
<div id="noteListDiv"></div>
</body>
</html>