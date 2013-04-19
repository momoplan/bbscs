<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理导航</title>
<style type="text/css">
  <!--
    body {
    background-color: #FAFAFA;
    }
  -->
</style>
<link href="css/admin.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/prototype.js"></script>
<script language="javascript" type="text/javascript">
function showMemu(i) {
  Element.toggle("t" + i);
}

function over(tid)
{
	tid.style.backgroundColor="#ebeadd";
}

function out(tid)
{
	tid.style.backgroundColor="#ffffff";
}
</script>
</head>
<body>
<table width="160" border="0" cellspacing="2" cellpadding="0" class="table2">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="3" cellpadding="0" class="table3">
        <tr>
          <td>
            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="table4">
              <tr>
                <td class="title" height="21">&nbsp;□ 首页
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td>
            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="table4">
              <tr>
                <td class="title" height="21" onClick="showMemu(1)">&nbsp;□ 常规选项
                </td>
              </tr>
            </table>
            <table width="100%" border="0" cellspacing="1" cellpadding="0" style="display:none" id="t1" class="table4">
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminOpenSet?action=index" id="adminOpenSetUrl"></s:url>
                  <a href="${adminOpenSetUrl}" target="bbscs_admin_mainFrame">打开/关闭论坛</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminWebset?action=index" id="adminWebsetUrl"></s:url>
                  <a href="${adminWebsetUrl}" target="bbscs_admin_mainFrame">网站信息</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminBaseSet?action=index" id="adminBaseSetUrl"></s:url>
                  <a href="${adminBaseSetUrl}" target="bbscs_admin_mainFrame">基本设置</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminDateTimeFormatSet?action=index" id="adminDateTimeFormatSetUrl"></s:url>
                  <a href="${adminDateTimeFormatSetUrl}" target="bbscs_admin_mainFrame">日期时间设置</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminCookiePassSet?action=index" id="adminCookiePassSetUrl"></s:url>
                  <a href="${adminCookiePassSetUrl}" target="bbscs_admin_mainFrame">COOKIE和通行证设置</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminScreenSet?action=index" id="adminScreenSetUrl"></s:url>
                  <a href="${adminScreenSetUrl}" target="bbscs_admin_mainFrame">屏蔽选项</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminEmailSet?action=index" id="adminEmailSetUrl"></s:url>
                  <a href="${adminEmailSetUrl}" target="bbscs_admin_mainFrame">Email选项</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminUserRegSet?action=index" id="adminUserRegSetUrl"></s:url>
                  <a href="${adminUserRegSetUrl}" target="bbscs_admin_mainFrame">用户注册选项</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminUserProSet?action=index" id="adminUserProSetUrl"></s:url>
                  <a href="${adminUserProSetUrl}" target="bbscs_admin_mainFrame">用户资料选项</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminFaceSet?action=index" id="adminFaceSetUrl"></s:url>
                  <a href="${adminFaceSetUrl}" target="bbscs_admin_mainFrame">用户头像选项</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminForbidSet?action=index" id="adminForbidSetUrl"></s:url>
                  <a href="${adminForbidSetUrl}" target="bbscs_admin_mainFrame">用户禁止选项</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminPostSet?action=index" id="adminPostSetUrl"></s:url>
                  <a href="${adminPostSetUrl}" target="bbscs_admin_mainFrame">发帖和编辑选项</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminAttachSet?action=index" id="adminAttachSetUrl"></s:url>
                  <a href="${adminAttachSetUrl}" target="bbscs_admin_mainFrame">附件选项</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminVoteSet?action=index" id="adminVoteSetUrl"></s:url>
                  <a href="${adminVoteSetUrl}" target="bbscs_admin_mainFrame">投票选项</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminPmSet?action=index" id="aminPmSetUrl"></s:url>
                  <a href="${aminPmSetUrl}" target="bbscs_admin_mainFrame">悄悄话选项</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminForumViewSet?action=index" id="adminForumViewSetUrl"></s:url>
                  <a href="${adminForumViewSetUrl}" target="bbscs_admin_mainFrame">主题列表显示选项</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminPostViewSet?action=index" id="adminPostViewSetUrl"></s:url>
                  <a href="${adminPostViewSetUrl}" target="bbscs_admin_mainFrame">主题内容显示选项</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminPostHiddenSet?action=index" id="adminPostHiddenSetUrl"></s:url>
                  <a href="${adminPostHiddenSetUrl}" target="bbscs_admin_mainFrame">隐藏帖选项</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminUOTimeSet?action=index" id="adminUOTimeSetUrl"></s:url>
                  <a href="${adminUOTimeSetUrl}" target="bbscs_admin_mainFrame">在线时长设定</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminUserLevel?action=index" id="adminUserLevelUrl" includeParams="none">
                  <s:param name="levelType" value="0"/>
                  </s:url>
                  <a href="${adminUserLevelUrl}" target="bbscs_admin_mainFrame">用户等级设定</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="getAlllotno" id="getAlllotno"></s:url>
                  <a href="${getAlllotno}" target="bbscs_admin_mainFrame">上传图片功能</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="toCreate" id="toCreate"></s:url>
                  <a href="${toCreate}" target="bbscs_admin_mainFrame">safe文件中user信息重新生成</a>
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td>
            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="table4">
              <tr>
                <td class="title" height="21" onClick="showMemu(2)">&nbsp;□ 版区管理
                </td>
              </tr>
            </table>
            <table width="100%" border="0" cellspacing="1" cellpadding="0" style="display:none" id="t2" class="table4">
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminBoardList" id="adminBoardListUrl"></s:url>
                  <a href="${adminBoardListUrl}" target="bbscs_admin_mainFrame">版区列表</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminBoardSet?action=add" id="adminBoardSetUrl" includeParams="none"></s:url>
                  <a href="${adminBoardSetUrl}" target="bbscs_admin_mainFrame">添加版区</a>
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td>
            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="table4">
              <tr>
                <td class="title" height="21" onClick="showMemu(3)">&nbsp;□ 用户与权限
                </td>
              </tr>
            </table>
            <table width="100%" border="0" cellspacing="1" cellpadding="0" style="display:none" id="t3" class="table4">
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminPermission?action=list" id="adminPermissionUrl"></s:url>
                  <a href="${adminPermissionUrl}" target="bbscs_admin_mainFrame">权限列表</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminRoleSet?action=index" id="adminRoleListUrl"></s:url>
                  <a href="${adminRoleListUrl}" target="bbscs_admin_mainFrame">角色管理</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminUgSet?action=index" id="adminUgListUrl"></s:url>
                  <a href="${adminUgListUrl}" target="bbscs_admin_mainFrame">用户组管理</a>
                </td>
              </tr>
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminUserSearch?action=index" id="adminUserSearchUrl"></s:url>
                  <a href="${adminUserSearchUrl}" target="bbscs_admin_mainFrame">用户管理</a>
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td>
            <table width="100%" border="0" cellspacing="1" cellpadding="0" class="table4">
              <tr>
                <td class="title" height="21" onClick="showMemu(4)">&nbsp;□ 退出管理
                </td>
              </tr>
            </table>
            <table width="100%" border="0" cellspacing="1" cellpadding="0" style="display:none" id="t4" class="table4">
              <tr>
                <td class="item" height="21" align="center" onMouseOver="over(this);" onMouseOut="out(this)">
                  <s:url action="adminLogout" id="adminLogoutUrl"></s:url>
                  <a href="${adminLogoutUrl}" target="_top">退出管理</a>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
