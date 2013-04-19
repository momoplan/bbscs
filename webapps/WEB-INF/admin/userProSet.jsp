<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>用户资料选项</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<p>&nbsp;</p>
<s:form action="adminUserProSet">
<s:hidden name="action"></s:hidden>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2"><strong>用户资料选项</strong></td>
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
          <td colspan="2" class="td1"><strong>昵称中需要屏蔽的词语</strong></td>
          </tr>
        <tr>
          <td width="60%" valign="top">在右边输入框中输入需要屏蔽的字词，用分号隔开，注意需使用正则表达式<BR></td>
          <td width="40%" valign="top">
            <s:textarea id="canNotUseNickName" name="canNotUseNickName" cols="40" rows="10" cssClass="textarea1"></s:textarea>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>允许使用签名</strong></td>
          </tr>
        <tr>
          <td>允许注册用户使用签名档 (出现在用户所发帖子下面)。</td>
          <td>
            <s:radio list="radioYesNoList" name="useSign" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>签名最大字符数</strong></td>
          </tr>
        <tr>
          <td>在用户签名中可以使用的最大字节限制，注意：一个中文为3个字符。</td>
          <td>
            <s:textfield id="signMaxLen" name="signMaxLen" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>签名中允许使用HTML代码</strong></td>
          </tr>
        <tr>
          <td>允许用户在个性签名中包含 HTML 代码</td>
          <td>
            <s:radio list="radioYesNoList" name="signUseHtml" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>签名中允许使用UBB代码</strong></td>
          </tr>
        <tr>
          <td>允许用户在个性签名中使用UBB代码？(例如 [b]、[i] 等)</td>
          <td>
            <s:radio list="radioYesNoList" name="signUseUBB" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>签名中允许表情图标</strong></td>
          </tr>
        <tr>
          <td>是否允许用户在他们的签名中使用表情图标</td>
          <td>
            <s:radio list="radioYesNoList" name="signUseSmile" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <div align="center">
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