<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="userinfo.title"/></title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript" src="js/note.js"></script>
<script language="JavaScript" type="text/javascript">
<!--

//var userid = "<s:property value="%{ui.id}"/>";

/* function loadBookMarkListPage() {
  $('bookMarkListDiv').innerHTML = pageLoadingCenter;
  var urls = getActionMappingURL("/userInfo");
  var pars = "action=bookmark&ajax=shtml&id="+userid;

  var myAjax = new Ajax.Updater("bookMarkListDiv", urls, {method: 'get', parameters: pars});
}

function loadBookMarkListPageUrl(url) {
  $('bookMarkListDiv').innerHTML = pageLoadingCenter;

  var urls = getActionName(url);
  var pars = getActionPars(url);

  var myAjax = new Ajax.Updater("bookMarkListDiv", urls, {method: 'get', parameters: pars});
} */

//-->
</script>
</head>

<body>
<jsp:include page="public_top.jsp"></jsp:include>
<div class="main_body1">
	<div class="address">您所在的位置：<a href="http://www.ruyicai.com">如意彩</a> > <a href="http://bbs.ruyicai.com"> 如意论坛 </a></div>
</div>
<div class="jg_10"></div>
<div class="main_body1">
	<div class="user_dttitle"><div class="left"></div><div class="center"><s:property value="%{ui.userName}"/>的动态日志</div><div class="right"></div></div>
</div>
<div id="noteSendDiv"></div>
<div class="main_body1">
		<div class="bbslist_title">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="listtable_title">
				<tr>
					<td width="50" align="left"><strong>&nbsp;&nbsp;<s:text name="userinfo.ntenpost"/></strong></td>
				</tr>
			</table>
	</div>
		<div>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" id="listtable_info">
				<s:iterator value="%{ownMainList}" id="f">
				<s:url action="read?action=topic" id="readurl">
				<s:param name="bid" value="#f.boardID"/>
				<s:param name="id" value="#f.mainID"/>
				<s:param name="fcpage" value="1"/>
				<s:param name="fcaction" value="index"/>
				</s:url>
				<s:url action="forum?action=index" id="furl">
				<s:param name="bid" value="#f.boardID"/>
				</s:url>
				<tr>
					<td width="50" align="center"><bbscs:forum forumValue="f" type="face"/></td>
					<td align="left" valign="middle" class="textindent2"><a href="${readurl}"><s:property value="#f.title"/></a></td>
					<td width="150" align="center">[<a href="${furl}"><s:property value="#f.boardName"/></a>]</td>
				</tr>
				</s:iterator>
			</table>
		</div>
</div>
<div class="jg_10"></div>
<div class="main_body1">
		<div class="bbslist_title">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="listtable_title">
				<tr>
					<td width="50" align="left"><strong>&nbsp;&nbsp;<s:text name="userinfo.ntenre"/></strong></td>
				</tr>
			</table>
	</div>
		<div style="">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" id="listtable_info">
				<s:iterator value="%{ownReList}" id="f">
					<s:url action="read?action=topic" id="readurl">
					<s:param name="bid" value="#f.boardID"/>
					<s:param name="id" value="#f.mainID"/>
					<s:param name="fcpage" value="1"/>
					<s:param name="fcaction" value="index"/>
					</s:url>
					<s:url action="forum?action=index" id="furl">
					<s:param name="bid" value="#f.boardID"/>
					</s:url>
					<tr>
						<td width="50" align="center"><bbscs:forum forumValue="f" type="face"/></td>
						<td align="left" valign="middle" class="textindent2"><a href="${readurl}"><s:property value="#f.title"/></a></td>
						<td width="150" align="center">[<a href="${furl}"><s:property value="#f.boardName"/></a>]</td>
					</tr>
				</s:iterator>
			</table>
		</div>
</div>
<!-- 共享书签<div id="bookMarkListDiv"></div> -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>