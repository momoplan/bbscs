<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>用户头像选项</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<p>&nbsp;</p>
<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
  <tr>
    <td>
      <table width="100%" border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td>
            <strong>用户头像选项</strong>
          </td>
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
      <s:form action="adminFaceSet">
      <s:hidden name="action"></s:hidden>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td colspan="2" class="td1">
      <strong>允许使用自定义头像</strong>
    </td>
  </tr>
  <tr>
    <td width="60%">使用此选项打开/关闭用户自定义上传头像。</td>
    <td width="40%">
      <s:radio list="radioYesNoList" name="useFace" listKey="key" listValue="value"></s:radio>
    </td>
  </tr>
  <tr>
    <td colspan="2" class="td1">
      <strong>头像高度</strong>
    </td>
  </tr>
  <tr>
    <td>显示的用户头像的最大高度，单位像素</td>
    <td>
      <s:textfield name="faceHigh" id="faceHigh" cssClass="input2" size="40" maxlength="4"></s:textfield>
    </td>
  </tr>
  <tr>
    <td colspan="2" class="td1">
      <strong>头像宽度</strong>
    </td>
  </tr>
  <tr>
    <td>显示的用户头像的最大宽度，单位像素</td>
    <td>
      <s:textfield name="faceWidth" id="faceWidth" cssClass="input2" size="40" maxlength="4"></s:textfield>
    </td>
  </tr>
  <tr>
    <td colspan="2" class="td1">
      <strong>上传头像文件大小</strong>
    </td>
  </tr>
  <tr>
    <td>用户上传头像文件的最大限制，单位KB</td>
    <td>
      <s:textfield name="faceSize" id="faceSize" cssClass="input2" size="40" maxlength="4"></s:textfield>
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
</s:form>


    </td>
  </tr>
</table>
<p>&nbsp;</p>
</body>
</html>