<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>主题内容显示选项</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<p>&nbsp;</p>
<s:form action="adminPostViewSet">
<s:hidden name="action"></s:hidden>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2"><strong>主题内容显示选项</strong></td>
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
          <td colspan="2" class="td1"><strong>每页显示帖子数目</strong></td>
          </tr>
        <tr>
          <td width="60%">主题每页显示帖子的数目，超过则自动分页。</td>
          <td width="40%">
            <s:textfield id="postPerPage" name="postPerPage" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>用户可设置显示帖子的最大数量</strong></td>
          </tr>
        <tr>
          <td>如果您想允许用户自行设置主题每页显示的帖子数量，请在这里设置在下拉列表框中允许用户选择的每页显示的帖子数目，用逗号隔开。如果留空则强制用户使用上面的“每页显示帖子数目”设置。<br/>
            设置示例: 10,20,30,40</td>
          <td valign="top">
            <s:textfield id="userPerPage" name="userPerPage" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>显示默认信息图标</strong></td>
          </tr>
        <tr>
          <td>如果用户发帖时没有选择信息图标，或者基于论坛的设置不能选择，则会使用此默认图标。留空的话，将禁用此功能。</td>
          <td valign="top">
            <s:textfield id="postDefFaceImg" name="postDefFaceImg" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>记录用户“赞成/反对”</strong></td>
          </tr>
        <tr>
          <td>是否记录用户对帖子的“赞成/反对”投票，如果不记录用户可对帖子无限制投票。</td>
          <td valign="top">
            <s:radio list="radioYesNoList" name="postVoteRec" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>在帖子左侧显示的用户信息中是否显示用户头像</strong></td>
          </tr>
        <tr>
          <td>选择是否在帖子左侧显示的用户信息中显示用户头像。</td>
          <td>
            <s:radio list="radioYesNoList" name="postShowUserImg" listKey="key" listValue="value"></s:radio>
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
