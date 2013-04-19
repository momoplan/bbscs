<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><bbscs:webinfo type="forumname"/> - <s:property value="%{board.boardName}"/><bbscs:webinfo type="poweredby"/></title>
<!--<bbscs:webinfo type="meta"/>-->
<link href="css/css1.css" rel="stylesheet" type="text/css" />
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
  var pars = "action=summaryhistory&ajax=shtml&id=" + id + "&bid=" + bid;
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
    url = getActionMappingURL("/forum?action=history&bid=" + boardId);
  }
  window.location.href = url;
}
//-->
</script>
</head>

<body>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td width="100%" valign="top" class="bgColor3">
      <span class="link2">
	  <s:url action="in" id="inUrl"></s:url>
	  <a href="${inUrl}"><s:text name="in.title"/></a>
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
	  <s:if test="%{tagId.equals(\"0\")}">
	  <a href="forum-index-<s:property value="%{bid}"/>.html"><strong><s:property value="%{board.boardName}"/></strong></a>
	  </s:if>
	  <s:else>
	  <a href="forum-index-<s:property value="%{bid}"/>.html"><s:property value="%{board.boardName}"/></a>
	  </s:else>
	  </s:if>
	  <s:else>
	  <s:url action="forum?action=index" id="forumUrl">
	  <s:param name="bid" value="%{board.id}"/>
	  </s:url>
	  <s:if test="%{tagId.equals(\"0\")}">
	  <a href="${forumUrl}"><strong><s:property value="%{board.boardName}"/></strong></a>
	  </s:if>
	  <s:else>
	  <a href="${forumUrl}"><s:property value="%{board.boardName}"/></a>
	  </s:else>
	  </s:else>
      <s:if test="%{!board.boardTag.isEmpty()}">
      [
	  <s:iterator id="tag" value="%{board.boardTag}">
      ·
	  <s:if test="%{urlRewrite}">
	  <s:if test="%{tagId==#tag.id}">
	  <strong><a href="forum-index-<s:property value="%{bid}"/>-<s:property value="#tag.id"/>-1-0.html"><s:property value="#tag.tagName"/></a></strong>
	  </s:if>
      <s:else>
	  <a href="forum-index-<s:property value="%{bid}"/>-<s:property value="#tag.id"/>-1-0.html"><s:property value="#tag.tagName"/></a>
	  </s:else>
	  </s:if>
	  <s:else>
	  <s:url action="forum?action=index" id="forumUrl">
	  <s:param name="bid" value="%{board.id}"/>
	  <s:param name="tagId" value="#tag.id"/>
	  </s:url>
	  <s:if test="%{tagId==#tag.id}">
	  <strong><a href="${forumUrl}"><s:property value="#tag.tagName"/></a></strong>
	  </s:if>
	  <s:else>
	  <a href="${forumUrl}"><s:property value="#tag.tagName"/></a>
	  </s:else>
      </s:else>
      </s:iterator>
      ]
      </s:if>
      </span>
    </td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="table8">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="1%" class="bgColor1">&nbsp;</td>
    <td width="98%"><table width="100%" border="0" cellpadding="3" cellspacing="0">
      <tr>
        <td width="35%">
          <strong><s:property value="%{board.boardName}"/> - <s:text name="forum.history"/></strong>
		  <s:if test="%{!tagId.equals(\"0\")}">
		  -
		  <s:iterator id="tag" value="board.boardTag">
		  <s:if test="%{tagId==#tag.id}">
		  <s:property value="#tag.tagName"/>
		  </s:if>
		  </s:iterator>
		  </s:if>
        </td>
        <td width="65%" rowspan="4" valign="middle">
        <%-- <div align="center">
          <script type="text/javascript"><!--
          google_ad_client = "pub-9505761087047507";
          google_ad_width = 468;
          google_ad_height = 60;
          google_ad_format = "468x60_as";
          google_ad_type = "text";
          google_ad_channel = "";
          google_color_border = "6699CC";
          google_color_bg = "003366";
          google_color_link = "FFFFFF";
          google_color_text = "AECCEB";
          google_color_url = "AECCEB";
          //--></script>
          <script type="text/javascript"
          src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
          </script>
          </div> --%>
        </td>
      </tr>
      <tr>
        <td>
        <s:text name="forum.all.num">
        <s:param value="%{pageList.pages.totalNum}"></s:param>
        </s:text>
        </td>
        </tr>
      <tr>
        <td><s:text name="bbscs.boardmaster"/>:<bbscs:boardmaster value="%{board.boardMaster}"/></td>
      </tr>
      <tr>
        <td><marquee direction="left" scrollamount="3" onMouseOver="this.stop();" onMouseOut="this.start();"><s:text name="board.bulletin"/>:<s:property value="%{board.bulletin}" escape="false"/></marquee></td>
      </tr>
    </table></td>
  </tr>
</table>

<table width="100%" height="2" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="5" cellspacing="0" class="table9">
  <tr>
    <td width="60%">
      <s:text name="bbscs.opt"/>:
      [<a href="javascript:load();"><s:text name="forum.reload"/></a>]

      <s:text name="forum.archives"/>:
      <s:iterator id="month" value="%{archivesMonth}">
      [<a href="archives/<s:property value="%{bid}"/>/<s:property value="#month"/>/1.html" target="_blank"><s:property value="#month"/></a>]
      </s:iterator>
    </td>
    <td width="40%">
      <div align="right">
      </div>
    </td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td width="30%">
	  <s:if test="%{action=='index'}">
        <strong><s:text name="forum.mainf"/></strong>
      </s:if>
	  <s:else>
		<s:if test="%{urlRewrite}">
          <a href="forum-index-<s:property value="%{bid}"/>.html"><s:text name="forum.mainf"/></a>
		</s:if>
		<s:else>
		<s:url action="forum?action=index" id="forumUrl">
		<s:param name="bid" value="%{bid}"/>
		</s:url>
		<a href="${forumUrl}"><s:text name="forum.mainf"/></a>
		</s:else>
      </s:else>
	  <s:url action="refine?action=index&pid=0" id="refineUrl">
	  <s:param name="bid" value="%{bid}"/>
	  </s:url>
	  <a href="${refineUrl}"><s:text name="forum.refine"/></a>
	  <s:if test="%{action=='hot'}">
	  <strong><s:text name="forum.hot"/></strong>
	  </s:if>
	  <s:else>
	  <s:url action="forum?action=hot" id="forumUrl">
	  <s:param name="bid" value="%{bid}"/>
	  </s:url>
	  <a href="${forumUrl}"><s:text name="forum.hot"/></a>
	  </s:else>
	  <s:if test="%{action=='commend'}">
	  <strong><s:text name="forum.commend"/></strong>
	  </s:if>
	  <s:else>
	  <s:url action="forum?action=commend" id="forumUrl">
	  <s:param name="bid" value="%{bid}"/>
	  </s:url>
	  <a href="${forumUrl}"><s:text name="forum.commend"/></a>
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
    <td width="70%">
      <div align="right">
        [<s:text name="bbscs.pagebreak"/>: <bbscs:pages value="%{pageList.pages}"/>]
      </div></td>
  </tr>
  <tr class="bgColor1">
    <td height="3" colspan="2"></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tbody>
  <tr>
    <td class="bgColor3">
      <table width="100%" border="0" cellpadding="5" cellspacing="1">
      <thead>
      <tr>
        <td width="6%" class="bgColor3"><div align="center"><strong><s:text name="forum.status"/></strong></div></td>
        <td width="66%" class="bgColor3"><div align="center"><strong><s:text name="forum.art.title"/></strong></div></td>
        <td width="8%" class="bgColor3"><div align="center"><strong><s:text name="forum.click"/></strong></div></td>
        <td width="8%" class="bgColor3"><div align="center"><strong><s:text name="forum.renum"/></strong></div></td>
        <td width="12%" class="bgColor3"><div align="center"><strong><s:text name="forum.lastre"/></strong></div></td>
      </tr>
      </thead>
      <tbody>
	  <s:iterator id="f" value="%{pageList.objectList}">
      <tr>
        <td class="bgColor4">
          <div align="left">
            <bbscs:forum type="face" forumValue="#f"/>
			<s:if test="#f.canNotDel==1">
			M
			</s:if>
          </div>
        </td>
        <td class="bgColor2">
          <div id="t<s:property value="#f.id"/>">
            <span class="font1"><bbscs:forum forumValue="#f" type="titlehistory" currentPageValue="%{page}"/></span>
            <bbscs:forum forumValue="#f" type="titleitemhistory" currentPageValue="%{page}"/>
          </div>
          <div id="u<s:property value="#f.id"/>">
            <span class="link1">
			  <s:url action="userInfo?action=id" id="userInfoUrl">
			  <s:param name="id" value="#f.userID"/>
			  </s:url>
			  <a href="${userInfoUrl}"><s:property value="#f.nickName"/>[<em><s:property value="#f.userName"/></em>]</a>
            </span>
            <span class="font4">(<bbscs:forum forumValue="#f" type="posttime"/>)</span>
          </div>
          <div class="summary1" id="s<s:property value="#f.id"/>" style="display:none">
          </div>
        </td>
        <td class="bgColor4"><div align="center"><bbscs:forum forumValue="#f" type="click"/></div></td>
        <td class="bgColor2"><div align="center"><bbscs:forum forumValue="#f" type="renum"/></div></td>
        <td class="bgColor4">
          <div align="right" class="font4">
            <span class="font4"><bbscs:forum forumValue="#f" type="lasttime"/></span>
            <br/>
			<s:if test="#f.lastPostNickName.equals(\"---\")">
			<s:property value="#f.lastPostNickName"/>
			</s:if>
			<s:else>
			<s:url action="userInfo?action=name" id="userInfoUrl">
			<s:param name="username" value="#f.lastPostUserName"/>
			</s:url>
			<span class="link1"><a href="${userInfoUrl}"><s:property value="#f.lastPostNickName"/></a></span>
			</s:else>
          </div>
        </td>
      </tr>
      </s:iterator>
      </tbody>
    </table></td>
  </tr>
  </tbody>
</table>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td>
      <div align="right">
        [<s:text name="bbscs.pagebreak"/>: <bbscs:pages value="%{pageList.pages}"/>]
        <strong><s:text name="forum.boardchange"/></strong>
		<s:select list="boardSelectValues" name="bids" cssClass="select1" listKey="key" listValue="value" onchange="changeBoard();" id="boardSelect"></s:select>
      </div>
    </td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td>
      <div align="left">

      </div>
    </td>
  </tr>
</table>


<%--<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td>

      <div align="center">
        <form method="get" action="http://www.google.cn/custom" target="google_window">
        <!-- <a href="http://www.google.com/"><img src="http://www.google.com/logos/Logo_25wht.gif" border="0" alt="Google" align="absmiddle"></img></a> -->
        <input type="hidden" name="domains" value="<s:property value="%{forumSite}"/>"></input>
        <input type="text" name="q" size="20" maxlength="255" value="" id="sbi" class="input2"></input>
        <input type="submit" name="sa" value="搜索" id="sbb" class="button1"></input>
        <input type="hidden" name="sitesearch" value="<s:property value="%{forumSite}"/>"></input>
        <input type="hidden" name="client" value="pub-9505761087047507"></input>
        <input type="hidden" name="forid" value="1"></input>
        <input type="hidden" name="ie" value="UTF-8"></input>
        <input type="hidden" name="oe" value="UTF-8"></input>
        <input type="hidden" name="cof" value="GALT:#008000;GL:1;DIV:#336699;VLC:663399;AH:center;BGC:FFFFFF;LBGC:336699;ALC:0000FF;LC:0000FF;T:000000;GFNT:0000FF;GIMP:0000FF;FORID:1"></input>
        <input type="hidden" name="hl" value="zh_CN"></input>
        </form>
      </div>
    </td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td>
      <div align="center">
        <script type="text/javascript"><!--
        google_ad_client = "pub-9505761087047507";
        google_ad_width = 728;
        google_ad_height = 90;
        google_ad_format = "728x90_as";
        google_ad_type = "text";
        google_ad_channel = "";
        google_color_border = "6699CC";
        google_color_bg = "003366";
        google_color_link = "FFFFFF";
        google_color_text = "AECCEB";
        google_color_url = "AECCEB";
        //--></script>
        <script type="text/javascript"
        src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
        </script>
      </div>
    </td>
  </tr>
</table> --%>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td>
      <div align="center">
        <bbscs:webinfo type="pagefoot"/>
      </div>
    </td>
  </tr>
</table>
</body>
</html>