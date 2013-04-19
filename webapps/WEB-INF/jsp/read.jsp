<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%@page import="com.laoer.bbscs.comm.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><bbscs:webinfo type="forumname"/> - <s:property value="%{pageTitle}"/><bbscs:webinfo type="poweredby"/></title>
<link href="css/css.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript" src="js/read.js"></script>
<script type="text/javascript">
function postSubmit() {
  document.getElementById("postButton").disabled = true;
  document.post.submit();
}

function ctrlEnter(e){
    var ie =navigator.appName=="Microsoft Internet Explorer"?true:false;
    if(ie){
        if(event.ctrlKey && window.event.keyCode==13){doSomething();}
    }else{
        if(isKeyTrigger(e,13,true)){doSomething();}
    }
}

function doSomething(){
postSubmit();
}
function checkIsAnonymousReply(){
	if(document.getElementById("isAnonymousReply").checked == true){
		document.getElementById("AnonymousReply").value="1";
	}else{
		document.getElementById("AnonymousReply").value="0";
	}
}
</script>
</head>
<body>
<jsp:include page="public_top.jsp"></jsp:include>
<div class="main_body1">
	<div class="address">您所在的位置：<a href="http://www.ruyicai.com">如意彩</a> > <a href="http://bbs.ruyicai.com"> 如意论坛 </a> &gt;&gt; 
	<s:iterator id="pboard" value="%{parentBoards}">
		&raquo;
		<s:if test="%{urlRewrite}">
			<a href="forum-index-<s:property value="#pboard.id"/>.html"><s:property value="#pboard.boardName"/></a>
		</s:if>
		<s:else>
			<s:url action="forum?action=index" id="forumUrl">
			<s:param name="bid" value="#pboard.id"/>
			</s:url>
			<a href="${forumUrl}"><s:property value="#pboard.boardName"/></a>
		</s:else>
	</s:iterator>
	&raquo;
	<s:if test="%{urlRewrite}">
		<a href="forum-index-<s:property value="%{bid}"/>.html"><s:property value="%{board.boardName}"/></a>
	</s:if>
	<s:else>
		<s:url action="forum?action=index" id="forumUrl">
		<s:param name="bid" value="%{board.id}"/>
		</s:url>
		<a href="${forumUrl}"><s:property value="%{board.boardName}"/></a>
	</s:else>
	<s:if test="%{!board.boardTag.isEmpty()}">
		<s:iterator id="tag" value="%{board.boardTag}">
			<s:if test="%{tagId==#tag.id}">
				&raquo;
				<s:property value="#tag.tagName"/>
			</s:if>
		</s:iterator>
	</s:if>
	 &gt;&gt; <s:property value="%{pageTitle}"/></div>
</div>
<div class="jg_10"></div>

<div class="main_body1">
	<div style="line-height:35px; background:#f8f8f8; border:1px solid #ddd; overflow:hidden">
		<div style="float:left; width:550px; padding-left:10px">操作: 
			<s:if test="%{action=='topic'}">
			[<bbscs:topic type="own"/>]
			</s:if>
			<s:if test="%{action=='own'}">
			[<bbscs:topic type="topic"/>]
			</s:if>
	        [<bbscs:topic type="subs" />]
	        [<bbscs:topic type="returnforum"/>]
		</div>
	</div>
</div>
<div class="jg_10"></div>
<div class="main_body1">
	<div class="list_leftfb">
		<s:url action="post?action=add" id="postUrl">
		<s:param name="bid" value="%{bid}"/>
		<s:param name="tagId" value="%{tagId}"/>
		</s:url>
		<a href="${postUrl}"><img src="images/pn_post.png" width="80" height="33" /></a>
	</div>
	<div class="list_right_page"> 
		<!--翻页开始 -->
		<div class="listnum">
			<div class="digg"> <span class="disabled"> < </span> <bbscs:pages value="%{pageList.pages}" argPage="inpages" argTotal="topicTotal"/> </div>
			<!--翻页 --> 
		</div>
		<!--翻页结束--> 
	</div>
</div>
<div class="jg_10"></div>
<div class="main_body1">
	<s:iterator id="f" value="%{pageList.objectList}" status="rowstatus">
	<div class="bbslist_title">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="listtable_title">
				<tr>
					<td align="left" class="textindent"> [<bbscs:forum forumValue="f" type="floor"/>]&nbsp;<bbscs:forum forumValue="f" type="face"/>&nbsp;<strong><s:text name="forum.art.title"/>:<s:property value="#f.title"/></strong>
					<span id="cndt<s:property value="#f.id"/>">
					  <s:if test="#f.canNotDel==1">M</s:if>
                    </span></td>
				</tr>
			</table>
	</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="bbs_tiezi_topinfo">
		<tr>
			<s:if test="#f.isAnonymousReply==0">
				<td width="160">
				<s:url action="userInfo?action=id" id="userInfoUrl">
				<s:param name="id" value="#f.userID"/>
				</s:url>
				<a href="${userInfoUrl}"><s:property value="#f.nickName"/></a>
			</td>
			</s:if>
			<td align="right">
				<div align="right">
				<s:if test="%{action!='own'}">
					<s:if test="%{fcaction=='index'}">
						<span id="topset<s:property value="#f.id"/>">
						<s:if test="#f.isNew==1">
						<s:if test="#f.isTop==0">
						[<a href="javascript:;" onclick="topSet('<s:property value="%{bid}"/>','<s:property value="#f.id"/>','top');"><s:text name="post.settop" /></a>]
						</s:if>
						<s:else>
						[<a href="javascript:;" onclick="topSet('<s:property value="%{bid}"/>','<s:property value="#f.id"/>','untop');"><s:text name="post.untop" /></a>]
						</s:else>
						</s:if>
						</span>
						<span id="lock<s:property value="#f.id"/>">
						<s:if test="#f.isNew==1">
						<s:if test="#f.isLock==0">
						[<a href="javascript:;" onclick="lockSet('<s:property value="%{bid}"/>','<s:property value="#f.id"/>','lock');"><s:text name="post.lock.title" /></a>]
						</s:if>
						<s:else>
						[<a href="javascript:;" onclick="lockSet('<s:property value="%{bid}"/>','<s:property value="#f.id"/>','unlock');"><s:text name="post.unlock.title" /></a>]
						</s:else>
						</s:if>
						</span>
					
						<span id="elite<s:property value="#f.id"/>">
						<s:if test="#f.elite==0">
						[<a href="javascript:;" onclick="eliteTopic('<s:property value="%{bid}"/>','<s:property value="#f.id"/>');"><s:text name="post.elite.title" /></a>]
						</s:if>
						</span>
				</s:if>
			</s:if>
			<%-- <a href="javascript:;" onclick="agreeAgainst('<s:property value="%{bid}"/>','<s:property value="#f.id"/>','votyes');"><img src="images/app.gif" border="0" alt="支持" width="16" height="16" align="absmiddle"/></a>
			:<span id="agree<s:property value="#f.id"/>"><s:property value="#f.agree"/></span>
			<a href="javascript:;" onclick="agreeAgainst('<s:property value="%{bid}"/>','<s:property value="#f.id"/>','votno');"><img src="images/obj.gif" border="0" alt="反对" width="16" height="16" align="absbottom"/></a>
			:<span id="beAgainst<s:property value="#f.id"/>"><s:property value="#f.beAgainst"/></span> --%>
			<a href="javascript:;" onclick="showIp('<s:property value="%{bid}"/>','<s:property value="#f.id"/>');"><img src="images/ip.gif" alt="IP" border="0" width="16" height="16" align="absmiddle"/></a>
			</div>
			<div id="ipMsg<s:property value="#f.id"/>" class="summary1" style="display:none" align="right"></div>
			</td>
		</tr>
	</table>
	
	<table width="100%" border="0" cellspacing="1" cellpadding="0" class="bbs_teizi_table">
		<tr>
			<s:if test="#f.isAnonymousReply==1">
				<td width="120" align="left" class="leftinfo">匿名回复</td>
			</s:if>
			<s:elseif test="#f.isAnonymousReply==0">
				<td width="120" align="left" class="leftinfo">
				<bbscs:userinfoinpost idValue="#f.userID" styleClass="pic1"/>
				</td>
			</s:elseif>
			<td align="left" bgcolor="#FFFFFF">
			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td>
							<div style="padding:20px; font-size:14px; line-height:200%">
								<div id="detail<s:property value="#f.id"/>" class="postDetail">
										<bbscs:forum forumValue="f" type="detail" />
                                 </div>
                        		<%--  <bbscs:forum forumValue="f" type="amend"/>--%>
							</div>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
				</tbody>
			</table>
			<br />
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="bbs_tiezi_bottominfo">
		<tr>
			<td width="160"><bbscs:forum forumValue="f" type="timeinpost"/></td>
			<td align="right">
				<s:if test="%{action!='own'}">
					<s:if test="%{fcaction=='index'}">
						[<bbscs:forum forumValue="f" type="sendnote"/>]
						[<bbscs:forum forumValue="f" type="del" currentPageValue="%{fcpage}"/>]
						[<bbscs:forum forumValue="f" type="delattachpage"/>]
					<s:if test="#f.isNew==1">
						[<bbscs:forum forumValue="f" type="movepage"/>]
					</s:if>
						[<bbscs:forum forumValue="f" type="edit" currentPageValue="%{fcpage}"/>]
						[<bbscs:forum forumValue="f" type="re" currentPageValue="%{fcpage}" />]
						[<bbscs:forum forumValue="f" type="requote" currentPageValue="%{fcpage}" />]
						[<bbscs:forum forumValue="f" type="upfilepage"/>]
					</s:if>
					<!--[转贴]-->
				</s:if>
				[<a href="javascript:;" onclick="reportTopic('<s:property value="%{bid}"/>','<s:property value="#f.id"/>');"><s:text name="post.report.title" /></a>]
				<div id="postOpt<s:property value="#f.id"/>" class="summary1" style="display:none"></div>
				<div id="noteSend<s:property value="#f.id"/>" class="summary1" style="display:none">
					<form action="<%=BBSCSUtil.getActionMappingURL("/note",request)%>" name="noteSendForm<s:property value="#f.id"/>">
						<table width="100%" border="0" cellpadding="5" cellspacing="0">
							<tr>
								<td width="20%"><s:text name="note.tousername"/>:</td>
								<td width="80%"><input name="toUserName" type="text" value="<s:property value="#f.userName"/>" readonly="readonly" class="input2" size="40" /></td>
							</tr>
							<tr>
								<td width="20%"><s:text name="note.msg.title"/>:</td>
								<td width="80%"><input name="noteTitle" type="text" class="input2" size="40" /></td>
							</tr>
							<tr>
								<td valign="top"><s:text name="note.content"/>:</td>
								<td><textarea name="noteContext" cols="40" rows="5" class="textarea1"></textarea></td>
							</tr>
							<tr>
								<td><s:text name="note.needre"/>:</td>
								<td><input type="checkbox" name="needRe" value="1" />
								<s:text name="note.needre.notice"/></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>
							    <input name="Submit2" type="button" class="button1" onclick="noteAdd('<s:property value="#f.id"/>');" value="<s:text name="bbscs.re"/>" />
								<input type="button" name="closeSendInButton" class="button1" onclick="closeNoteSend('<s:property value="#f.id"/>');" value="<s:text name="bbscs.close"/>"/>
							    </td>
							</tr>
						</table>
					</form>
				</div>
			</td>
		</tr>
	</table>
	<div class="jg_10"></div>
	</s:iterator>
</div>
<div class="jg_10"></div>
<div class="main_body1">
	<div class="list_leftfb">
		<s:url action="post?action=add" id="postUrl">
		<s:param name="bid" value="%{bid}"/>
		<s:param name="tagId" value="%{tagId}"/>
		</s:url>
		<a href="${postUrl}"><img src="images/pn_post.png" width="80" height="33" /></a>
	</div>
	<div class="list_right_page"> 
		<!--翻页开始 -->
		<div class="listnum">
			<div class="digg"> <span class="disabled"> < </span> <bbscs:pages value="%{pageList.pages}" argPage="inpages" argTotal="topicTotal"/></div>
			<!--翻页 --> 
		</div>
		<!--翻页结束--> 
	</div>
</div>
<div class="jg_10"></div>
<div class="main_body1">
	<table width="100%" border="0" cellspacing="1" cellpadding="0" class="bbs_teizi_table">
		<s:if test="%{action!='own'}">
			<s:if test="%{fcaction=='index'}">
				<s:form action="post" method="POST" enctype="multipart/form-data" id="post" name="post">
					<tr>
						<td align="left" class="leftinfo"><s:text name="read.art.title"/> </td>
						<td align="left" bgcolor="#FFFFFF" style="vertical-align:middle">
							<table width="100%" border="0" cellspacing="15" cellpadding="0">
								<tr>
									<td><input type="text" name="title" maxlength="60" size="80" value="Re:" class="input2" id="title" /></td>
								</tr>
							</table>
						<td>		
					</tr>
					<tr>
						<td width="120" align="left" class="leftinfo"><img src="images/defaultFace.jpg" alt="Face" /><br /></td>
							<input type="hidden" name="action" value="addqre" />
					        <input type="hidden" name="bid" value="<s:property value="%{bid}"/>" />
					        <input type="hidden" name="parentID" value="<s:property value="%{id}"/>" />
					        <input type="hidden" name="editType" value="0" />
					        <input type="hidden" name="mainID" value="<s:property value="%{id}"/>" />
					        <input type="hidden" name="totalnum" value="<s:property value="%{pageList.pages.totalNum}"/>" />
					        <input type="hidden" name="sign" value="-1" />
					        <input type="hidden" name="fcpage" value="<s:property value="%{fcpage}"/>" />
					        <input type="hidden" name="tagId" value="<s:property value="%{tagId}"/>" />
					        <input type="hidden" name="AnonymousReply" id="AnonymousReply" value="0" />
					        <td align="left" bgcolor="#FFFFFF">
								<table width="100%" border="0" cellspacing="15" cellpadding="0">
									<tr>
										<td>
											<label for="textfield"></label>
											<textarea name="detail" cols="100" rows="8" style="width:760px" onkeyup="javascript:return ctrlEnter(event);"></textarea>
										</td>
									</tr>
									<tr>
										<td align="right">
											<input name="isAnonymousReply" id="isAnonymousReply" type="checkbox" onclick="checkIsAnonymousReply();"/>匿名回复&nbsp;&nbsp;
											<input id="postButton" name="Submit" type="button" class="bbs_huifu_btn" value="<s:text name="post.do"/>" onclick="postSubmit();"/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
				</s:form>
			</s:if>
		</s:if>
	</table>
</div>
<div class="jg_10"></div>
<jsp:include page="footerToAll.jsp"></jsp:include>
</body>
</html>