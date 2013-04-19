<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>投票选项</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<p>&nbsp;</p>
<s:form action="adminVoteSet">
<s:hidden name="action"></s:hidden>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2"><strong>投票选项</strong></td>
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
          <td colspan="2" class="td1"><strong>最多投票选项</strong></td>
          </tr>
        <tr>
          <td width="60%">用户发起投票的最多投票项目。<br>
            设置为 0 不作限制。</td>
          <td width="40%" valign="top">
            <s:textfield id="voteItemNum" name="voteItemNum" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>投票选项长度</strong></td>
          </tr>
        <tr>
          <td>投票选项允许的最大长度，单位字节，注意中文为3个字节。</td>
          <td>
            <s:textfield id="voteItemLength" name="voteItemLength" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>投票后更新主题最后回复</strong></td>
          </tr>
        <tr>
          <td>如果设置此选项为“是”，当有一个投票加入到这个主题后，将更新此主题的最后回复，从而该主题将被自动提到主题列表的上面。</td>
          <td valign="top">
            <s:radio list="radioYesNoList" name="voteUpdatePost" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>允许改变投票主题</strong></td>
          </tr>
        <tr>
          <td>允许用户改变他们最初的投票主题？</td>
          <td>
            <s:radio list="radioYesNoList" name="voteChange" listKey="key" listValue="value"></s:radio>
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