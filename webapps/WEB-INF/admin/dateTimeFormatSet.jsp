<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>日期和时间选项</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />

</head>

<body>
<p>&nbsp;</p>
<s:form action="adminDateTimeFormatSet">
<s:hidden name="action"></s:hidden>
<table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
  <tr>
    <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
      <tr>
        <td colspan="2"><strong>日期和时间选项</strong></td>
      </tr>
      <tr>
        <td colspan="2">
        <s:actionerror theme="bbscs0"/>
        </td>
      </tr>
      <tr>
        <td colspan="2">
        <s:actionmessage theme="bbscs0"/>
        </td>
      </tr>
      </table>
      <div id="info">
      <table width="100%"  border="0" cellpadding="5" cellspacing="0">

      <tr>
        <td colspan="2" class="td1"><strong>日期显示选项</strong></td>
      </tr>
      <tr>
        <td width="60%">此选项控制整个论坛的日期显示<br/>
          “标准”选项使用下面定义的日期时间格式。<br/>
          “昨天/今天”选项将会把昨天和今天的日期显示为“昨天”或“今天”。</td>
        <td width="40%" valign="top">
          <s:select list="dateShowTypeValues" name="dateShowType" id="dateShowType" listKey="key" listValue="value" cssClass="select1"></s:select>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="td1"><strong>默认时区设置</strong></td>
      </tr>
      <tr>
        <td>游客和新用户的默认时区时间显示。</td>
        <td>
          <s:select list="timeZoneValues" name="defaultTimeZone" id="defaultTimeZone" cssClass="select1" listKey="key" listValue="value"></s:select>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="td1"><strong>时间格式</strong></td>
      </tr>
      <tr>
        <td>在选择非标准日期选项情况下的时间格式</td>
        <td>
          <s:textfield id="timeFormat" name="timeFormat" cssClass="input2" size="40"></s:textfield>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="td1"><strong>帖子列表中日期时间格式</strong></td>
      </tr>
      <tr>
        <td>标准情况下帖子列表中显示的日期时间格式。</td>
        <td>
          <s:textfield id="forumDateTimeFormat" name="forumDateTimeFormat" cssClass="input2" size="40"></s:textfield>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="td1"><strong>帖子内容日期时间格式</strong></td>
      </tr>
      <tr>
        <td>显示帖子正文中的日期时间格式。</td>
        <td>
          <s:textfield id="postDateTimeFormat" name="postDateTimeFormat" cssClass="input2" size="40"></s:textfield>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="td1"><strong>其余日期时间格式</strong></td>
      </tr>
      <tr>
        <td>在其余地方显示日期时间的格式。</td>
        <td>
          <s:textfield id="otherDateTimeFormat" name="otherDateTimeFormat" cssClass="input2" size="40"></s:textfield>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="td1"><strong>注册日期格式</strong></td>
      </tr>
      <tr>
        <td>在用户帖子中显示。主题左边，用户名下面，显示用户注册的时间格式。</td>
        <td>
          <s:textfield id="regDateTimeFormat" name="regDateTimeFormat" cssClass="input2" size="40"></s:textfield>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="td1"><strong>出生日期的格式</strong></td>
      </tr>
      <tr>
        <td>在个人资料里面指定年份的出生日期显示格式。</td>
        <td>
          <s:textfield id="birthDateTimeFormat" name="birthDateTimeFormat" cssClass="input2" size="40"></s:textfield>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <div align="center">
            <s:submit cssClass="button2" value="保存"></s:submit>
          </div>
        </td>
      </tr>
    </table>
    </div>
   </td>
  </tr>
</table>
</s:form>
<p>&nbsp;</p>
</body>
</html>
