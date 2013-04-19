<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>版区管理</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>

<body>
<p>&nbsp;</p>
<s:form action="adminBoardSet">
<s:hidden name="action"></s:hidden>
<s:hidden name="id"></s:hidden>
<table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
  <tr>
    <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
      <tr>
        <td colspan="2"><strong>版区管理</strong></td>
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

      <tr class="td1">
        <td width="60%">名称</td>
        <td width="40%">
          <s:textfield id="boardName" name="boardName" cssClass="input2" size="40"></s:textfield>
        </td>
      </tr>
      <tr>
        <td valign="top">描述</td>
        <td>
          <s:textarea id="explains" name="explains" cols="40" rows="5" cssClass="textarea1"></s:textarea>
        </td>
      </tr>
      <tr class="td1">
        <td valign="top">论坛公告</td>
        <td>
          <s:textarea id="bulletin" name="bulletin" cols="40" rows="5" cssClass="textarea1"></s:textarea>
        </td>
      </tr>
      <tr>
        <td>显示顺序</td>
        <td>
          <s:textfield id="orders" name="orders" cssClass="input2" size="40"></s:textfield>
        </td>
      </tr>
      <tr class="td1">
        <td>父论坛</td>
        <td>
          <s:select list="parentValues" name="parentID" id="parentID" listKey="key" listValue="value" cssClass="select1"></s:select>
        </td>
      </tr>
      <tr>
        <td>管理帖子<br />
          (新帖需要版主确认)</td>
        <td valign="top">
          <s:radio list="radioYesNoList" name="auditPost" listKey="key" listValue="value"></s:radio>
        </td>
      </tr>
      <tr class="td1">
        <td>管理附件<br />
          (附件需要版主确认)</td>
        <td valign="top">
          <s:radio list="radioYesNoList" name="auditAttach" listKey="key" listValue="value"></s:radio>
        </td>
      </tr>
      <tr>
        <td>此版区需要用密码访问</td>
        <td>
          <s:radio list="radioYesNoList" name="needPasswd" listKey="key" listValue="value"></s:radio>
        </td>
      </tr>
      <tr class="td1">
        <td>访问密码</td>
        <td>
          <s:textfield id="passwd" name="passwd" cssClass="input2" size="40"></s:textfield>
        </td>
      </tr>
      <tr>
        <td>论坛类型</td>
        <td>
          <s:select list="boardTypes" name="boardType" id="boardType" listKey="key" listValue="value" cssClass="select1"></s:select>
        </td>
      </tr>
      <tr class="td1">
        <td>使用状态</td>
        <td>
          <s:select list="useStats" name="useStat" id="useStat" listKey="key" listValue="value" cssClass="select1"></s:select>
        </td>
      </tr>
      <tr>
        <td>是否隐藏</td>
        <td>
          <s:radio list="radioYesNoList" name="isHidden" listKey="key" listValue="value"></s:radio>
        </td>
      </tr>
      <tr class="td1">
        <td>是否只有授权用户才能访问</td>
        <td>
          <s:radio list="radioYesNoList" name="isAuth" listKey="key" listValue="value"></s:radio>
        </td>
      </tr>
      <tr>
        <td>允许HTML</td>
        <td>
          <s:radio list="radioYesNoList" name="allowHTML" listKey="key" listValue="value"></s:radio>
        </td>
      </tr>
      <tr class="td1">
        <td>允许UBB</td>
        <td>
          <s:radio list="radioYesNoList" name="allowUBB" listKey="key" listValue="value"></s:radio>
        </td>
      </tr>
      <tr>
        <td>用户在此论坛的发帖数计入该用户的总发帖数</td>
        <td>
          <s:radio list="radioYesNoList" name="addUserPostNum" listKey="key" listValue="value"></s:radio>
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
