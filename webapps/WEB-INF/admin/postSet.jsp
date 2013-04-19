<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息发布和编辑选项</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>

<body>
<p>&nbsp;</p>
<s:form action="adminPostSet">
<s:hidden name="action"></s:hidden>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2"><strong>信息发布和编辑选项</strong></td>
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
          <td colspan="2" class="td1"><strong>帖子最少字符数</strong></td>
          </tr>
        <tr>
          <td width="60%" valign="top">如果设置为高于“0”的数，用户帖子里的字符数要大于等于这个数值。(一个中文为3个字符。)<br />
            注意: 设置为 0 将不会完全禁用帖子最少字符数检查。用户至少需要输入一个字符。</td>
          <td width="40%" valign="top">
            <s:textfield id="postMinSize" name="postMinSize" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>帖子最大字符数</strong></td>
          </tr>
        <tr>
          <td>在这里指定帖子的最大字符数。超过这个数，将提示用户缩短内容后再发表。<br />
            设置为“0”禁用此功能，也就是不限制字符数。</td>
          <td valign="top">
            <s:textfield id="postMaxSize" name="postMaxSize" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>引用字符限制</strong></td>
          </tr>
        <tr>
          <td>用户回复时，引用的帖子如果超过该字符数，则将被截短。设置为“0”表示不截短引用文字。</td>
          <td valign="top">
            <s:textfield id="quoteMaxSize" name="quoteMaxSize" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>发帖时间间隔</strong></td>
          </tr>
        <tr>
          <td>您可以激活此功能以阻止用户在本论坛灌水。(单位:秒)<br />
            启用此限制后，用户在发两帖之间必须间隔一定的时间。换句话说，如果间隔是 30 秒，则该用户在 30 秒内不能发第二个帖子。管理员和版主不受此限制。<br />
            推荐: 30 秒。设置为“0”则关闭此功能。</td>
          <td valign="top">
            <s:textfield id="postCheckTime" name="postCheckTime" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>编辑帖子标题的时限</strong></td>
          </tr>
        <tr>
          <td>指定主题发起者能编辑帖子标题的时限 (以分钟为单位)。</td>
          <td>
            <s:textfield id="editPostTitleLimit" name="editPostTitleLimit" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>编辑帖子时限</strong></td>
          </tr>
        <tr>
          <td>编辑处理帖子的时间限制 (以分钟为单位，1 天是 1440 分钟)。超过这个时间限制，只有管理员和版主才能编辑和删除帖子。如果不想使用这项功能，请设置为 0。</td>
          <td valign="top">
            <s:textfield id="editPostLimit" name="editPostLimit" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>打开信息编辑器格式化选项</strong></td>
          </tr>
        <tr>
          <td valign="top">此全局开关允许您完全禁用信息格式化工具栏。</td>
          <td valign="top">
            <s:radio list="radioEditInterfaceList" name="editInterface" listKey="key" listValue="value" theme="bbscs0"></s:radio>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>记录 IP 地址</strong></td>
          </tr>
        <tr>
          <td valign="top">出于安全考虑，您可能希望在论坛记录发帖人的 IP 地址。</td>
          <td>
            <s:radio list="radioLogIPList" name="logIP" listKey="key" listValue="value" theme="bbscs0"></s:radio>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>是否开启发帖时段</strong></td>
        </tr>
        <tr>
          <td valign="top">在发帖的时段，用户可进行发帖回帖，其余时段用户不能发帖回帖，在管理员不在线的时段可以使用此项功能。</td>
          <td>
            <s:radio list="radioYesNoList" name="usePostPeriodOfTime" listKey="key" listValue="value" theme="bbscs0"></s:radio>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>发帖时段开始时间</strong></td>
        </tr>
        <tr>
          <td valign="top"></td>
          <td>
            <s:select list="hourValues" name="postPeriodOfTimeStart" id="postPeriodOfTimeStart" listKey="key" listValue="value" cssClass="select1"></s:select>
            时
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>发帖时段结束时间</strong></td>
        </tr>
        <tr>
          <td valign="top"></td>
          <td>
            <s:select list="hourValues" name="postPeriodOfTimeEnd" id="postPeriodOfTimeEnd" listKey="key" listValue="value" cssClass="select1"></s:select>
            时
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>转入历史帖天数</strong></td>
        </tr>
        <tr>
          <td valign="top">将多少天前的帖子转入历史帖</td>
          <td>
            <s:textfield id="postToHistoryDay" name="postToHistoryDay" cssClass="input2" size="5"></s:textfield>
            天
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