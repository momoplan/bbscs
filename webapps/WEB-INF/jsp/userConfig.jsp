<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="userconfig.title1"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script language="javascript" type="text/javascript">

function editUserConfig() {
  showExeMsg();
  var url = getActionMappingURL("/userConfig");
  var pars = "action=edit&ajax=xml&hiddenLogin=" + getCheckBoxValue("hiddenLogin") + "&receiveNote="
  + getCheckBoxValue("receiveNote") + "&acceptFriend=" + getCheckBoxValue("acceptFriend") + "&forumViewMode="
  + $('forumViewMode').value + "&forumPerNum=" + $('forumPerNum').value + "&postPerNum="
  + $('postPerNum').value + "&timeZone=" + encodeURIComponent($('timeZone').value)
  + "&editType=" + getRadioValueByName("editType");
  //alert(pars);
  var myAjax = new Ajax.Request(url, {method: 'post', parameters: pars, onComplete: editUserConfigOK});
}

function editUserConfigOK(res) {
  resText = res.responseText;
  var jsonMsgObj = new JsonMsgObj(resText);
  var codeid = jsonMsgObj.getCodeid();
  hiddenExeMsg();
  alert(jsonMsgObj.getMessage());
}

</script>
</head>

<body>
<s:form action="userConfig">
<s:hidden name="action"></s:hidden>
<table width="95%" border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
  <tr>
    <td><table width="100%" border="0" cellpadding="5" cellspacing="0">
      <tr>
        <td colspan="2"><strong><s:text name="userconfig.title1"/></strong></td>
        </tr>
      <!--<tr>
        <td colspan="2">&nbsp;</td>
        </tr>-->
      <tr>
        <td colspan="2"></td>
        </tr>
      <tr>
        <td colspan="2" class="td1"><strong><s:text name="userconfig.usehidden"/></strong></td>
        </tr>
      <tr>
        <td width="50%"><s:text name="userconfig.usehidden.notice"/></td>
        <td width="50%">
          <s:checkbox id="hiddenLogin" name="hiddenLogin"></s:checkbox>
          <s:text name="userconfig.usehidden"/>
        </td>
      </tr>

      <tr>
        <td colspan="2" class="td1"><strong><s:text name="userconfig.pm"/></strong></td>
        </tr>
      <tr>
        <td><s:text name="userconfig.pm.notice"/></td>
        <td valign="top">
          <s:checkbox id="receiveNote" name="receiveNote"></s:checkbox>
          <s:text name="userconfig.pm.use"/>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="td1"><strong><s:text name="userconfig.friend"/></strong></td>
        </tr>
      <tr>
        <td><s:text name="userconfig.friend.notice"/></td>
        <td>
          <s:checkbox id="acceptFriend" name="acceptFriend"></s:checkbox>
          <s:text name="userconfig.friend.use"/>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="td1"><strong><s:text name="userconfig.fv"/></strong></td>
        </tr>
      <tr>
        <td><s:text name="userconfig.fv.notice"/></td>
        <td>
          <s:select list="forumViewModeValues" name="forumViewMode" id="forumViewMode" cssClass="select1" listKey="key" listValue="value"></s:select>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="td1"><strong><s:text name="userconfig.fpm"/></strong></td>
        </tr>
      <tr>
        <td><s:text name="userconfig.fpm.notice"/></td>
        <td valign="top">
          <s:select list="userForumNumPerPageValues" name="forumPerNum" id="forumPerNum" cssClass="select1" listKey="key" listValue="value"></s:select>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="td1"><strong><s:text name="userconfig.ppm"/></strong></td>
        </tr>
      <tr>
        <td><s:text name="userconfig.ppm.notice"/></td>
        <td valign="top">
          <s:select list="userPostNumPerPageValues" name="postPerNum" id="postPerNum" cssClass="select1" listKey="key" listValue="value"></s:select>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="td1"><strong><s:text name="userconfig.edittype"/></strong></td>
        </tr>
      <tr>
        <td><s:text name="userconfig.edittype.notice"/></td>
        <td valign="top">
          <s:radio list="radioEditInterfaceList" name="editType" listKey="key" listValue="value" theme="bbscs0"></s:radio>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="td1"><strong><s:text name="userconfig.timezone"/></strong></td>
        </tr>
      <tr>
        <td><s:text name="userconfig.timezone.notice"/></td>
        <td valign="top">
          <s:select list="userTimeZoneValues" name="timeZone" id="timeZone" cssClass="select1" listKey="key" listValue="value"></s:select>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <div align="center">
            <input type="button" name="ClosePage" value="<s:text name="bbscs.botton.save"/>" class="button2" onclick="editUserConfig();"/>
          </div>
        </td>
      </tr>
    </table></td>
  </tr>
</table>
</s:form>

</body>
</html>