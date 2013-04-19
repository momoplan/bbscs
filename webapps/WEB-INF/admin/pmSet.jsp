<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>悄悄话选项</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<p>&nbsp;</p>
<s:form action="adminPmSet">
<s:hidden name="action"></s:hidden>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2"><strong>悄悄话选项</strong></td>
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
          <td colspan="2" class="td1"><strong>启用悄悄话系统</strong></td>
          </tr>
        <tr>
          <td width="60%">打开/关闭整个悄悄话系统。</td>
          <td width="40%">
            <s:radio list="radioYesNoList" name="usePm" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>每个悄悄话的最大字节数</strong></td>
          </tr>
        <tr>
          <td>在悄悄话里允许的最大字节数，注意1个中文为3个字节。<br/></td>
          <td>
            <s:textfield id="pmMaxLength" name="pmMaxLength" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>灌水检查 - 发送悄悄话最少间隔时间</strong></td>
          </tr>
        <tr>
          <td>悄悄话灌水检查。设置两条悄悄话之间的最短时间间隔，防止一个用户在短时间内给另一用户发送大量的“垃圾”悄悄话信息，单位为秒。<br/>
            设置为 0 禁用此功能。</td>
          <td valign="top">
            <s:textfield id="pmFloodTime" name="pmFloodTime" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>每页默认悄悄话数量</strong></td>
          </tr>
        <tr>
          <td>此设置允许您定义在悄悄话列表页每页显示的悄悄话默认数量。</td>
          <td>
            <s:textfield id="pmPerPage" name="pmPerPage" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td colspan="2"><strong>允许在悄悄话中使用 UBB 代码</strong></td>
          </tr>
        <tr>
          <td>允许用户在悄悄话中使用 UBB 代码? (比如 [b]、[i] 等。)</td>
          <td>
            <s:radio list="radioYesNoList" name="pmAllowUBB" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>

        <tr>
          <td colspan="2" class="td1"><strong>允许在悄悄话中使用表情图标</strong></td>
          </tr>
        <tr>
          <td>允许用户在悄悄话中包含表情图标?</td>
          <td>
            <s:radio list="radioYesNoList" name="pmAllowSmilies" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>允许在悄悄话中使用 HTML 代码</strong></td>
          </tr>
        <tr>
          <td>允许用户在悄悄话中包含 HTML 代码？<br/>
            (强烈推荐选择“否”。) </td>
          <td valign="top">
            <s:radio list="radioYesNoList" name="pmAllowHTML" listKey="key" listValue="value"></s:radio>
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