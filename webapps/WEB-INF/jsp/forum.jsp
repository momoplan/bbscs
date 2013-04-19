<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><bbscs:webinfo type="forumname"/> - <s:property value="%{board.boardName}"/><bbscs:webinfo type="poweredby"/></title>
<!--<bbscs:webinfo type="meta"/>-->
<link href="css/css.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script language="JavaScript" type="text/javascript">
<!--
var ViewSummaryOjb = function(bid,id){
  this.bid = bid;
  this.id = id;
}

ViewSummaryOjb.prototype.execute = function(resText) {
  $('s' + this.id).innerHTML = resText;
}

function viewSummary(bid,id) {
  Element.show("s" + id);
  $('s' + id).innerHTML = pageLoading;

  var url = getActionMappingURL("/read");
  var pars = "action=summary&ajax=shtml&id=" + id + "&bid=" + bid;
  var myAjax = new Ajax.Updater('s' + id, url, {method: 'get', parameters: pars});
}

function closeSummary(id) {
  $('s' + id).innerHTML = "";
  hiddenElement("s" + id);
}

function load() {
  var name=navigator.appName
  var vers=navigator.appVersion
  if(name=='Netscape'){
    window.location.reload(true)
  }
  else{
    history.go(0)
  }
}

function boardsave(bid) {
  showExeMsg();
  var url = getActionMappingURL("/boardSave");
  var pars = "action=save&ajax=xml&bid="+bid;

  var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: boardsaveComplete});
}

function boardsaveComplete(res) {
  resText = res.responseText;
  var jsonMsgObj = new JsonMsgObj(resText);
  var codeid = jsonMsgObj.getCodeid();
  hiddenExeMsg();
  alert(jsonMsgObj.getMessage());
}

function changeBoard() {
  var boardSelectObj = document.getElementById("boardSelect");
  var boardId = boardSelectObj.options[boardSelectObj.selectedIndex].value;
  var url = "";
  if (useUrlRewrite == "true") {
    url = "forum-index-" + boardId + ".html";
  }
  else {
    url = getActionMappingURL("/forum?action=index&bid=" + boardId);
  }
  window.location.href = url;
}
//-->
</script>
</head>

<body>

<jsp:include page="public_top.jsp"></jsp:include>
<div class="main_body1">
	<div class="address">您所在的位置：<a href="http://www.ruyicai.com">如意彩</a> > <a href="http://bbs.ruyicai.com"> 如意论坛 </a>&gt;&gt; <s:property value="%{board.boardName}"/>讨论区</div>
</div>
<div class="jg_10"></div>
<!--广告 -->
<s:if test="#session.pictureImage1!=null">
<div class="main_body1">
  
	<div class="left_ad_box"><a href="<s:property value="#session.pictureImage1.href"/>"><img src="<s:property value="#session.pictureImage1.imageurl"/>"/></a></div>
	 <div class="right_ad_box"><a href="<s:property value="#session.pictureImage2.href"/>"><img src="<s:property value="#session.pictureImage2.imageurl"/>"/></a></div>
</div>
<div class="jg_10"></div>
</s:if>
<!--广告 -->
<div class="main_body1">
	<s:form action="forumSearch">
	<s:hidden name="bid" value="%{bid}"></s:hidden>
	<div style="line-height:35px; background:#f8f8f8; border:1px solid #ddd; overflow:hidden">
		<div style="float:left; width:550px; padding-left:10px">
			<s:text name="bbscs.opt"/>: [<a href="javascript:load();"><s:text name="forum.reload"/></a>]
			<s:if test="%{action=='index'}">
				<s:url action="post?action=add" id="postUrl">
				<s:param name="bid" value="%{bid}"/>
				<s:param name="tagId" value="%{tagId}"/>
				</s:url>
				[<a href="${postUrl}"><s:text name="forum.newpost"/></a>]
				<s:url action="votePost?action=add" id="voteUrl">
				<s:param name="bid" value="%{bid}"/>
				<s:param name="tagId" value="%{tagId}"/>
				</s:url>
				[<a href="${voteUrl}"><s:text name="forum.newvote"/></a>]
			</s:if>
			[<a href="javascript:;" onclick="boardsave('<s:property value="%{bid}"/>');"><s:text name="forum.addsave"/></a>]
			<s:url action="subs?action=index" id="subsUrl">
			<s:param name="bid" value="%{bid}"/>
			</s:url>
			[<a href="${subsUrl}"><s:text name="forum.subed"/></a>]
			<s:url action="forumManage?action=m" id="forumManageUrl">
			<s:param name="bid" value="%{bid}"/>
			</s:url>
			[<a href="${forumManageUrl}"><s:text name="forum.manage"/></a>]&nbsp;
			<a href="rss?bid=<s:property value="%{bid}"/>" target="_blank">
			<img alt="Rss" src="images/rss200.png" border="0" align="absmiddle"/></a>
		</div>
		<div style="float:right; width:350px;"> <s:text name="forum.insearch"/>&nbsp;
			<input name="text" type="text" size="10" maxlength="50" />
			&nbsp;
			<select name="con">
				&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
				<option value="title"><s:text name="forum.art.title"/></option>
				&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
				<option value="userName"><s:text name="forum.author"/></option>
				&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
			</select>
			&nbsp;
			<s:submit id="forumSearch_0" value="%{getText('bbscs.button.serach')}"></s:submit>
		</div>
	</div>
	</s:form>
</div>
<div class="jg_10"></div>
<div class="main_body1">
	<div class="list_leftfb">
		<s:if test="%{action=='index'}">
			<s:url action="post?action=add" id="postUrl">
			<s:param name="bid" value="%{bid}"/>
			<s:param name="tagId" value="%{tagId}"/>
			</s:url>
			<a href="${postUrl}"><img src="images/pn_post.png" width="80" height="33" /></a>
		</s:if>
	</div>
	<div class="list_right_page"> 
		<!--翻页开始 -->
		<div class="listnum">
			<div class="digg"> <span class="disabled"> < </span> <%-- <span class="current">1</span> --%> <bbscs:pages value="%{pageList.pages}"/> </div>
			<!--翻页 --> 
		</div>
		<!--翻页结束--> 
	</div>
</div>
<div class="jg_10"></div>
<div class="main_body1">
		<div class="bbslist_title">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="listtable_title">
				<tr>
					<td width="50" align="center"><s:text name="forum.status"/></td>
					<td width="600" align="left" class="textindent">
						<s:if test="%{action=='index'}">
							<strong><s:text name="forum.mainf"/>：</strong>
						</s:if>
						<s:else>
							<s:if test="%{urlRewrite}">
								<a href="forum-index-<s:property value="%{bid}"/>.html"><s:text name="forum.mainf"/></a>&nbsp;
							</s:if>
							<s:else>
								<s:url action="forum?action=index" id="forumUrl">
								<s:param name="bid" value="%{bid}"/>
								</s:url>
								<a href="${forumUrl}"><s:text name="forum.mainf"/></a>&nbsp;
							</s:else>
						</s:else>
						<s:url action="refine?action=index&pid=0" id="refineUrl">
						<s:param name="bid" value="%{bid}"/>
						</s:url>
						<a href="${refineUrl}"><s:text name="forum.refine"/></a>&nbsp;
						<s:if test="%{action=='hot'}">
							<strong><s:text name="forum.hot"/></strong>
						</s:if>
						<s:else>
							<s:url action="forum?action=hot" id="forumUrl">
							<s:param name="bid" value="%{bid}"/>
							</s:url>
							<a href="${forumUrl}"><s:text name="forum.hot"/></a>&nbsp;
						</s:else>
						<s:if test="%{action=='commend'}">
							<strong><s:text name="forum.commend"/></strong>
						</s:if>
						<s:else>
							<s:url action="forum?action=commend" id="forumUrl">
							<s:param name="bid" value="%{bid}"/>
							</s:url>
							<a href="${forumUrl}"><s:text name="forum.commend"/></a>&nbsp;
						</s:else>
						<s:if test="%{action=='history'}">
							<strong><s:text name="forum.history"/></strong>
						</s:if>
						<s:else>
							<s:url action="forum?action=history" id="forumUrl">
							<s:param name="bid" value="%{bid}"/>
							</s:url>
							<a href="${forumUrl}"><s:text name="forum.history"/></a>
						</s:else>
					</td>
					
					<td width="80" align="center"><s:text name="forum.click"/></td>
					<td width="80" align="center"><s:text name="forum.renum"/></td>
					<td align="center"><s:text name="forum.lastre"/></td>
				</tr>
			</table>
		</div>
		<div style="">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" id="listtable_info">
				<s:iterator id="f" value="%{pageList.objectList}">
					<tr>
						<td width="50" align="center">
							<bbscs:forum type="face" forumValue="#f"/>
							<s:if test="#f.canNotDel==1">
							M
							</s:if>
						</td>
						<td width="600" align="left" valign="middle" class="textindent2">
							<div id="t<s:property value="#f.id"/>"><a href="#"><bbscs:forum forumValue="#f" type="title" currentPageValue="%{page}"/></a>&nbsp;
							</div>
							<div id="u<s:property value="#f.id"/>">
								<s:url action="userInfo?action=id" id="userInfoUrl">
								<s:param name="id" value="#f.userID"/>
								</s:url>
							</div>
							<div class="summary1" id="s<s:property value="#f.id"/>" style="display:none">
						</td>
						<td width="80" align="center"><bbscs:forum forumValue="#f" type="click"/></td>
						<td width="80" align="center"><bbscs:forum forumValue="#f" type="renum"/></td>
						<td align="center" class="list_last" > <bbscs:forum forumValue="#f" type="lasttime"/><br />
							<s:if test="#f.lastPostNickName.equals(\"---\")">
								<s:property value="#f.lastPostNickName"/>
							</s:if>
							<s:else>
								<s:url action="userInfo?action=name" id="userInfoUrl">
								<s:param name="username" value="#f.lastPostUserName"/>
								</s:url>
								<a href="${userInfoUrl}"><s:property value="#f.lastPostNickName"/></a>
							</s:else>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
</div>
<div class="jg_10"></div>
<div class="main_body1">
	<div class="list_leftfb">
		<s:if test="%{action=='index'}">
			<s:url action="post?action=add" id="postUrl">
			<s:param name="bid" value="%{bid}"/>
			<s:param name="tagId" value="%{tagId}"/>
			</s:url>
			<a href="${postUrl}"><img src="images/pn_post.png" width="80" height="33" /></a>
		</s:if>
	</div>
	<div class="list_right_page"> 
		<!--翻页开始 -->
		<div class="listnum">
			<div class="digg"> <span class="disabled"> < </span> <bbscs:pages value="%{pageList.pages}"/> </div>
			<!--翻页 --> 
		</div>
		<!--翻页结束--> 
	</div>
</div>
<div class="jg_10"></div>
<div class="main_body1 txtright"><strong><s:text name="forum.boardchange"/></strong>&nbsp;
	<s:select list="boardSelectValues" name="bids" listKey="key" listValue="value" onchange="changeBoard();" id="boardSelect"></s:select>
</div>
<div class="jg_10"></div>

<jsp:include page="footerToAll.jsp"></jsp:include>
</body>
</html>