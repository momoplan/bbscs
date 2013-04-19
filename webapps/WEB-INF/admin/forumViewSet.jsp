<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>主题列表显示选项</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<p>&nbsp;</p>
<s:form action="adminForumViewSet">
<s:hidden name="action"></s:hidden>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2"><strong>主题列表显示选项</strong></td>
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
          <td colspan="2" class="td1"><strong>每页显示的主题数</strong></td>
          </tr>
        <tr>
          <td width="60%">每页显示的主题数。超过这个数目，论坛自动分页。</td>
          <td width="40%">
            <s:textfield id="forumPrePage" name="forumPrePage" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>作为热门主题的最低人气数</strong></td>
          </tr>
        <tr>
          <td>达到或超过指定人气值的帖子便会成为热门帖子。所谓人气值就是该主题的被查看次数。</td>
          <td valign="top">
            <s:textfield id="forumHotViews" name="forumHotViews" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>作为热门主题的最低回复数</strong></td>
          </tr>
        <tr>
          <td>达到或超过指定回复数的帖子便会成为热门帖子。</td>
          <td>
            <s:textfield id="forumHotRes" name="forumHotRes" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>启用主题分页链接</strong></td>
          </tr>
        <tr>
          <td>在主题列表中，如果主题有多页，那么是否显示页码。换句话说，如果一个主题有很多回复，达到了好几页，这时将会在主题标题的后面显示“1 2 3..”的链接。</td>
          <td valign="top">
            <s:radio list="radioYesNoList" name="useLinkToPages" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>主题分页最多链接数</strong></td>
          </tr>
        <tr>
          <td>主题分页链接的最多页码数，超过这个数目将会显示“...最后一页”。</td>
          <td>
            <s:textfield id="maxMultiPage" name="maxMultiPage" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="td1"><strong>主题预览文本的长度</strong></td>
          </tr>
        <tr>
          <td>此设置用来指定主题列表中显示的主题预览的长度。属性使得您将鼠标电机“预览”时，能够看到主题部分内容的预览。</td>
          <td valign="top">
            <s:textfield id="postViewLength" name="postViewLength" cssClass="input2" size="40"></s:textfield>
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