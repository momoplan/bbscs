<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="post.title"/></title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="ueditor/themes/default/ueditor.css" rel="stylesheet" type="text/css" />
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
<script type="text/javascript" src="ueditor/editor_config.js"></script>
<script type="text/javascript" src="ueditor/editor_all_min.js"></script>
<script type="text/javascript" src="js/jquery-1.5.min.js"></script>
<script language="JavaScript" type="text/javascript">

var ViewSummaryOjb = function(bid,id){
  this.bid = bid;
  this.id = id;
};

ViewSummaryOjb.prototype.execute = function(resText) {
  $('s' + this.id).innerHTML = resText;
};

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
  var name=navigator.appName;
  var vers=navigator.appVersion;
  if(name=='Netscape'){
    window.location.reload(true);
  }
  else{
    history.go(0);
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

function postSubmit() {
	if(editor.hasContents()){
		editor.sync();
		document.getElementById("postButton").disabled = true;
		document.post.submit();
	}
}
</script>
</head>

<body>
<jsp:include page="public_top.jsp"></jsp:include>

<div class="main_body1">
	<div class="address">您所在的位置：<a href="http://www.ruyicai.com">如意彩</a> > <a href="http://bbs.ruyicai.com"> 如意论坛 </a>&gt;&gt; <bbscs:post type="postat"/>讨论区</div>
</div>

<div class="jg_10"></div>

<div class="main_body1">
	<div style="line-height:35px; background:#f8f8f8; border:1px solid #ddd; overflow:hidden">
		<s:form action="forumSearch">
		<div style="float:left; width:550px; padding-left:10px">
	 	  <s:hidden name="bid" value="%{bid}"></s:hidden>
	      <s:text name="bbscs.opt"/>:
	      [<a href="javascript:load();"><s:text name="forum.reload"/></a>]
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
	      [<a href="${forumManageUrl}"><s:text name="forum.manage"/></a>]
	      <a href="rss?bid=<s:property value="%{bid}"/>" target="_blank"><img border="0" align="absmiddle" src="images/rss200.png" alt="Rss"></a>
	  	</div>
		<div style="float:right; width:350px;"><s:text name="forum.insearch"/>&nbsp;
			<input name="text" type="text" size="10" maxlength="50"/>
			&nbsp;
			<select name="con">
				&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
				<option value="title"><s:text name="forum.art.title"/></option>
				&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
	         		<option value="userName"><s:text name="forum.author"/></option>
				&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
			</select>
			&nbsp;
			<s:submit cssClass="button1" value="%{getText('bbscs.button.serach')}" id="forumSearch_0"></s:submit>
		</div>
  		</s:form>
	</div>
</div>

<div class="jg_10"></div>

<div class="main_body1">
	<div style="font-size:14px; text-align:center; font-weight:bold; line-height:30px; border:1px solid #FCC; background:#FFFBFB; display:block; color:#C00"><s:actionerror theme="bbscs0"/></div>
	<div class="fatie_tablebor">
	<s:form action="post" enctype="multipart/form-data" method="POST" name="post" id="post">
	<table width="100%" border="0" cellpadding="0" >
		<s:hidden name="action"></s:hidden>
		<s:hidden name="bid"></s:hidden>
		<s:hidden name="id"></s:hidden>
		<s:hidden name="editType"></s:hidden>
		<s:hidden name="parentID"></s:hidden>
		<s:hidden name="mainID"></s:hidden>
		<s:hidden name="totalnum"></s:hidden>
		<s:hidden name="inpages"></s:hidden>
		<s:hidden name="fcpage"></s:hidden>
		
		<s:if test="%{action=='addre'}">
		<s:hidden name="tagId"></s:hidden>
		</s:if>
		<s:elseif test="%{action=='addrequote'}">
		<s:hidden name="tagId"></s:hidden>
		</s:elseif>
		<s:elseif test="%{action=='editdo'}">
		<s:hidden name="tagId"></s:hidden>
		</s:elseif>
			<tr>
		    <td><table width="100%" border="0" cellpadding="5" cellspacing="10">
		      <tr>
		        <td width="15%" class="bgColor2"><s:text name="read.art.title"/></td>
		        <td width="85%" class="bgColor2">
		          <s:if test="%{action=='addsave'}">
		          <s:select list="tagValues" id="tagId" name="tagId" cssClass="select1" listKey="key" listValue="value"></s:select>
		          </s:if>
		          <s:textfield id="title" name="title" cssClass="input2" size="60" maxlength="60"></s:textfield>
		        </td>
		      </tr>
		      <tr class="bgColor4">
		        <td><s:text name="post.copyright"/></td>
		        <td>
		          <input name="postType" type="radio" value="0" <s:if test="%{postType==0}">checked="checked"</s:if> />
		          <s:text name="post.postType1"/>
		          <input name="postType" type="radio" value="1" <s:if test="%{postType==1}">checked="checked"</s:if> />
		          <s:text name="post.postType2"/>
		          <input name="postType" type="radio" value="2" <s:if test="%{postType==2}">checked="checked"</s:if> />
		          <s:text name="post.postType3"/>
		        </td>
		      </tr>
		      <tr class="bgColor2">
		        <td><s:text name="post.status"/></td>
		        <td>
		        <table cellSpacing="2" cellPadding="2" border="0">
		        <tbody>
		        <tr>
		          <td><input name="face" type="radio" value="1" <s:if test="%{face==1}">checked="checked"</s:if> /></td>
		          <td><img src="images/1.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="2" <s:if test="%{face==2}">checked="checked"</s:if> /></td>
		          <td><img src="images/2.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="3" <s:if test="%{face==3}">checked="checked"</s:if> /></td>
		          <td><img src="images/3.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="4" <s:if test="%{face==4}">checked="checked"</s:if> /></td>
		          <td><img src="images/4.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="5" <s:if test="%{face==5}">checked="checked"</s:if> /></td>
		          <td><img src="images/5.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="6" <s:if test="%{face==6}">checked="checked"</s:if> /></td>
		          <td><img src="images/6.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="13" <s:if test="%{face==13}">checked="checked"</s:if> /></td>
		          <td><img src="images/13.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="14" <s:if test="%{face==14}">checked="checked"</s:if> /></td>
		          <td><img src="images/14.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="15" <s:if test="%{face==15}">checked="checked"</s:if> /></td>
		          <td><img src="images/15.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="16" <s:if test="%{face==16}">checked="checked"</s:if> /></td>
		          <td><img src="images/16.gif" alt=""/></td></TR>
		        <tr>
		          <td><input name="face" type="radio" value="7" <s:if test="%{face==7}">checked="checked"</s:if> /></td>
		          <td><img src="images/7.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="8" <s:if test="%{face==8}">checked="checked"</s:if> /></td>
		          <td><img src="images/8.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="9" <s:if test="%{face==9}">checked="checked"</s:if> /></td>
		          <td><img src="images/9.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="10" <s:if test="%{face==10}">checked="checked"</s:if> /></td>
		          <td><img src="images/10.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="11" <s:if test="%{face==11}">checked="checked"</s:if> /></td>
		          <td><img src="images/11.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="12" <s:if test="%{face==12}">checked="checked"</s:if> /></td>
		          <td><img src="images/12.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="17" <s:if test="%{face==17}">checked="checked"</s:if> /></td>
		          <td><img src="images/17.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="18" <s:if test="%{face==18}">checked="checked"</s:if> /></td>
		          <td><img src="images/18.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="19" <s:if test="%{face==19}">checked="checked"</s:if> /></td>
		          <td><img src="images/19.gif" alt=""/></td>
		          <td><input name="face" type="radio" value="20" <s:if test="%{face==20}">checked="checked"</s:if> /></td>
		          <td><img src="images/20.gif" alt=""/></td></tr>
			 </tbody>
		         </table>
		         </td>
		      </tr>
		      <tr class="bgColor4">
				<td><s:text name="read.art.content"/></td>
		        <td>
					<s:if test="%{editType==0}">
						<s:textarea id="detail" name="detail" cssClass="textarea1" cols="60" rows="15"></s:textarea>
					</s:if>
					<s:elseif test="%{editType==1}">
						<textarea id="detail"><s:property value="%{detail}" escape="false"/></textarea>
					</s:elseif>
					<s:else>
						<textarea id="detail"><s:property value="%{detail}" escape="false"/></textarea>
					</s:else>
				</td>
		      </tr>
		      <tr class="bgColor2">
		        <td><s:text name="post.renotice"/></td>
		        <td>
				  <s:if test="%{action=='editdo'}">
				  <%-- <s:checkbox name="emailInform" id="emailInform" disabled="true"/>
				  <s:text name="post.renotice1"/> --%>
				  <s:checkbox name="msgInform" id="msgInform" disabled="true"/>
				  <s:text name="post.renotice2"/>
				  </s:if>
				  <s:else>
				  <%-- <s:checkbox name="emailInform" id="emailInform" />
				  <s:text name="post.renotice1"/> --%>
				  <s:checkbox name="msgInform" id="msgInform" />
				  <s:text name="post.renotice2"/>
				  </s:else>
		        </td>
		      </tr>
		      <tr class="bgColor4">
		        <td><s:text name="post.upload"/></td>
		        <td>
				  <s:if test="%{action=='editdo'}">
				  <s:file name="upload" cssClass="input2" size="50" id="upload" disabled="true"></s:file>
				  </s:if>
				  <s:else>
				  <s:file name="upload" cssClass="input2" size="50" id="upload"></s:file>
				  </s:else>
		        </td>
		      </tr>
		      <tr class="bgColor2">
		        <td><s:text name="post.hiddenset"/></td>
		        <td>
				  <s:if test="%{action=='addsave'}">
		          <table width="100%" border="0" cellpadding="2" cellspacing="0">
		          <tr>
		            <td>
					  <s:checkbox name="previewAttach" id="previewAttach" />
		              <s:text name="post.hiddenset1"/>
		            </td>
		          </tr>
		          <tr>
		            <td>
					  <s:if test="%{postHiddenTypeRe==1}">
					  <input name="isHidden" type="radio" value="1" <s:if test="%{isHidden==1}">checked="checked"</s:if> />
					  </s:if>
					  <s:else>
					  <input name="isHidden" type="radio" value="1" disabled="disabled" <s:if test="%{isHidden==1}">checked="checked"</s:if> />
					  </s:else>
		              <s:text name="post.hiddenset2"/>
		            </td>
		          </tr>
		          <tr>
		            <td>
					  <s:if test="%{postHiddenTypeMoney==1}">
					  <input name="isHidden" type="radio" value="2" <s:if test="%{isHidden==2}">checked="checked"</s:if> />
					  </s:if>
					  <s:else>
					  <input name="isHidden" type="radio" value="2" disabled="disabled" <s:if test="%{isHidden==2}">checked="checked"</s:if> />
					  </s:else>
		              <s:text name="post.hiddenset3"/>
					  <s:select list="postPriceValues" name="useCoin" id="useCoin" cssClass="select1" listKey="key" listValue="value"></s:select>
		            </td>
		          </tr>
		          <tr>
		            <td>
					  <input name="isHidden" type="radio" value="0" <s:if test="%{isHidden==0}">checked="checked"</s:if> />
		              <s:text name="post.nohidden"/>
		            </td>
		          </tr>
		        </table>
		        </s:if>
		        </td>
		      </tr>
	<%/* 	      <tr class="bgColor2">
		        <td colspan="2" class="font4">
		          <span class="p1">
					<s:text name="post.postnotice1" />
		          </span>
		        </td>
		        </tr>
	*/%>
		      <tr class="bgColor4">
		        <td colspan="2"><div align="center">
		          <input id="postButton" name="Submit" type="button" class="bbs_huifu_btn" value="<s:text name="post.do"/>" onclick="postSubmit();"/>
				  <input type="button" name="back" value="<s:text name="bbscs.back"/>" class="bbs_huifu_btn" onclick="http://bbs.ruyicai.com"/>
		        </div></td>
		        </tr>
		    </table></td>
		  </tr>
			<script type="text/javascript">
				var editor = new baidu.editor.ui.Editor({
					iframeCssUrl:'themes/default/iframe.css',
					textarea:'detail',
					elementPathEnabled : false
				});
				editor.render("detail");
			</script>
		</table>
	</s:form>
	</div>
</div>
<jsp:include page="footerToAll.jsp"></jsp:include>
</body>
</html>
