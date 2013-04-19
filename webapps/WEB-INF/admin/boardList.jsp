<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>版区列表</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script type="text/javascript" src="js/adminboard.js"></script>
</head>

<body>
<p>&nbsp;</p>
<table width="95%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
  <tr>
    <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
      <tr class="td1">
        <td width="40%"><strong>版区</strong></td>
        <td width="35%"><strong>操作</strong></td>
        <td width="25%"><strong>版主</strong></td>
      </tr>
      <s:iterator id="b" value="%{boardTreeList}">
      <tr>
        <td valign="top">
          <bbscs:prefixline showText="-" levelValue="#b.level"/><strong><s:property value="#b.boardName"/></strong>
          <s:if test="#b.boardType==3">
          [<a href="javascript:;" onclick="showTag('<s:property value="#b.id"/>');">Tag列表</a>
          <a href="javascript:;" onclick="showAddTagPage('<s:property value="#b.id"/>');">添加</a>]
          <div id="btag<s:property value="#b.id"/>" style="display:none"></div>
          </s:if>
        </td>
        <td valign="top">
          <s:url action="adminBoardSet?action=edit" id="adminBoardSetEditUrl" includeParams="none">
          <s:param name="id" value="#b.id"/>
          </s:url>
          [<s:a href="%{adminBoardSetEditUrl}">编辑</s:a>]
          <s:if test="#b.boardType==1">
          <s:url action="adminBoardSet?action=add" id="adminBoardSetNewUrl" includeParams="none">
          <s:param name="parentID" value="#b.id"/>
          </s:url>
          [<s:a href="%{adminBoardSetNewUrl}">添加子版区</s:a>]
          </s:if>
          [<a href="javascript:;" onclick="showBgs('<s:property value="#b.id"/>');">权限</a>]
          <s:url action="adminBoardSet?action=del" id="adminBoardSetDelUrl" includeParams="none">
          <s:param name="id" value="#b.id"/>
          </s:url>
          [<s:a href="%{adminBoardSetDelUrl}" onclick="return comfirm_delboard();">删除</s:a>]
          <div id="bg<s:property value="#b.id"/>"></div>
        </td>
        <td valign="top">
          [<a href="javascript:;" onclick="showBms('<s:property value="#b.id"/>');">查看</a>]
          [<a href="javascript:;" onclick="newBoardMasterLoad('<s:property value="#b.id"/>');">添加版主</a>]
          <!--<BR/>-->
          <div id="bm<s:property value="#b.id"/>"></div>
        </td>
      </tr>
      </s:iterator>
      <tr>
        <td colspan="3"><div id="addbm"></div></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>