<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>屏蔽选项</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<p>&nbsp;</p>
<s:form action="adminScreenSet">
<s:hidden name="action"></s:hidden>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2"><strong>屏蔽选项</strong></td>
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
        <tr>
          <td colspan="2" class="td1"><strong>打开屏蔽功能</strong></td>
        </tr>
        <tr>
          <td width="60%">论坛中的某些字词可能需要屏蔽。这些字词将被特定的字符替换，论坛内所有标题和内容都受影响。</td>
          <td width="40%" valign="top">
          <s:radio list="radioYesNoList" name="useScreen" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>用来覆盖屏蔽词的字符</strong></td>
        </tr>
        <tr>
          <td>这些字符用来覆盖您想屏蔽的字词。</td>
          <td>
            <s:textfield id="bestrowScreen" name="bestrowScreen" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>屏蔽的字词</strong></td>
        </tr>
        <tr>
          <td valign="top">在右边输入框中输入需要屏蔽的字词，用分号隔开，注意要屏蔽的字符支持正则表达式。</td>
          <td valign="top">
            <s:textarea id="screenWord" name="screenWord" cols="40" rows="10" cssClass="textarea1"></s:textarea>
          </td>
        </tr>
        <tr>
          <td colspan="2"><div align="center">
            <s:submit cssClass="button2" value="保存"></s:submit>
          </div></td>
          </tr>
      </table></td>
    </tr>
  </table>
</s:form>
<p>&nbsp;</p>
</body>
</html>