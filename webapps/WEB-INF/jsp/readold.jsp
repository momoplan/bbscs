<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<%@page import="com.laoer.bbscs.comm.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><bbscs:webinfo type="forumname"/> - <s:property value="%{pageTitle}"/><bbscs:webinfo type="poweredby"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css"/>
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
<script type="text/javascript" src="js/read.js"></script>
<script type="text/javascript">
function postSubmit() {
  document.getElementById("postButton").disabled = true;
  document.post.submit();
}
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="5" cellspacing="0" class="readBoard">
  <tr>
    <td width="43%">
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
    </td>
    <td width="57%">
      <div align="right">
        [<s:text name="bbscs.pagebreak"/>: <bbscs:pages value="%{pageList.pages}" argPage="inpages" argTotal="topicTotal"/>]
		<s:if test="%{action=='topic'}">
		[<bbscs:topic type="own"/>]
		</s:if>
		<s:if test="%{action=='own'}">
		[<bbscs:topic type="topic"/>]
		</s:if>
        [<bbscs:topic type="mailsendtopic"/>]
        [<bbscs:topic type="subs" />]
        [<bbscs:topic type="returnforum"/>]
      </div>
    </td>
  </tr>
  <tr>
    <td colspan="2" class="bgColor2">
      <s:text name="read.thisposturl"/>:<span id="posturl" class="font6"><bbscs:topic type="posturl"/></span> <!--<a href="javascript:;" onclick="copyPostUrl();">复制此地址</a>-->
    </td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="2">
  <tr>
    <td width="83%">
    <!--
    <table width="100%" border="0" cellpadding="5" cellspacing="0" class="table6">
      <tr>
        <td class="bgColor4">本主题地址:<span class="font6"><bbscs:topic type="posturl"/></span></td>
      </tr>
    </table>

	<table width="100%" height="1" border="0">
        <tr>
          <td></td>
        </tr>
      </table>-->
	  <s:iterator id="f" value="%{pageList.objectList}" status="rowstatus">
      <div id="topic<s:property value="#f.id"/>">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td class="bgColor5">
            <table width="100%" border="0" cellpadding="4" cellspacing="1">
              <tr>
                <td colspan="2" class="bgColor3">
                  <span class="font2">[<bbscs:forum forumValue="f" type="floor"/>]</span>
                  <bbscs:forum forumValue="f" type="face"/>
                  <span class="font1">
                    <strong><s:text name="forum.art.title"/>:<s:property value="#f.title"/></strong>
                    <span id="cndt<s:property value="#f.id"/>">
					  <s:if test="#f.canNotDel==1">M</s:if>
                    </span>
                  </span>
                </td>
              </tr>
              <tr>
                <td width="21%" valign="top" class="bgColor4">
				  <s:url action="userInfo?action=id" id="userInfoUrl">
				  <s:param name="id" value="#f.userID"/>
				  </s:url>
				  <a href="${userInfoUrl}"><s:property value="#f.nickName"/>(<s:property value="#f.userName"/>)</a>
                </td>
                <td width="79%" class="bgColor2">
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
                    <span id="commend<s:property value="#f.id"/>">
					  <s:if test="#f.commend==0">
                      [<a href="javascript:;" onclick="commendSet('<s:property value="%{bid}"/>','<s:property value="#f.id"/>','commend');"><s:text name="post.commend.title" /></a>]
                      </s:if>
					  <s:else>
                      [<a href="javascript:;" onclick="commendSet('<s:property value="%{bid}"/>','<s:property value="#f.id"/>','uncommend');"><s:text name="post.uncommend.title" /></a>]
                      </s:else>
                    </span>

                    <span id="cannotdel<s:property value="#f.id"/>">
					  <s:if test="#f.canNotDel==0">
                      [<a href="javascript:;" onclick="canNotDelSet('<s:property value="%{bid}"/>','<s:property value="#f.id"/>','cannotdel');"><s:text name="post.cannotdel" /></a>]
                      </s:if>
					  <s:else>
                      [<a href="javascript:;" onclick="canNotDelSet('<s:property value="%{bid}"/>','<s:property value="#f.id"/>','candel');"><s:text name="post.uncannotdel" /></a>]
                      </s:else>
                    </span>
                    <span id="elite<s:property value="#f.id"/>">
					  <s:if test="#f.elite==0">
                      [<a href="javascript:;" onclick="eliteTopic('<s:property value="%{bid}"/>','<s:property value="#f.id"/>');"><s:text name="post.elite.title" /></a>]
                      </s:if>
                    </span>
					</s:if>
					</s:if>
                    <a href="javascript:;" onclick="agreeAgainst('<s:property value="%{bid}"/>','<s:property value="#f.id"/>','votyes');"><img src="images/app.gif" border="0" alt="支持" width="16" height="16" align="absmiddle"/></a>
                    :<span id="agree<s:property value="#f.id"/>"><s:property value="#f.agree"/></span>
                    <a href="javascript:;" onclick="agreeAgainst('<s:property value="%{bid}"/>','<s:property value="#f.id"/>','votno');"><img src="images/obj.gif" border="0" alt="反对" width="16" height="16" align="absbottom"/></a>
                    :<span id="beAgainst<s:property value="#f.id"/>"><s:property value="#f.beAgainst"/></span>
                    <a href="javascript:;" onclick="showIp('<s:property value="%{bid}"/>','<s:property value="#f.id"/>');"><img src="images/ip.gif" alt="IP" border="0" width="16" height="16" align="absmiddle"/></a>
                  </div>
                  <div id="ipMsg<s:property value="#f.id"/>" class="summary1" style="display:none" align="right"></div>
                </td>
              </tr>
              <tr>
                <td valign="top" class="bgColor4">
                  <bbscs:userinfoinpost idValue="#f.userID" styleClass="pic1"/>
                </td>
                <td valign="top" class="bgColor2">
                  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td>
                        <div id="detail<s:property value="#f.id"/>" class="postDetail">
                          <bbscs:forum forumValue="f" type="detail"/>
                        </div>
                        <bbscs:forum forumValue="f" type="sign" itemClass="signInPost"/>
                        <bbscs:forum forumValue="f" type="amend"/>
                      </td>
                    </tr>
                    <tr>
                      <td><div align="right">&nbsp;</div></td>
                    </tr>
                  </table>
                </td>
              </tr>
              <tr>
                <td valign="top" class="bgColor4"><bbscs:forum forumValue="f" type="timeinpost"/></td>
                <td class="bgColor2">
                  <div align="right">
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
                    [<a href="javascript:;" onclick="mailSendTopic('<s:property value="%{bid}"/>','<s:property value="#f.id"/>');"><s:text name="post.mailsend.title" /></a>]
                    [<a href="javascript:;" onclick="reportTopic('<s:property value="%{bid}"/>','<s:property value="#f.id"/>');"><s:text name="post.report.title" /></a>]
                  </div>
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
          </td>
        </tr>
      </table>
      <table width="100%" height="1" border="0">
        <tr>
          <td></td>
        </tr>
      </table>
      </div>
      </s:iterator>

      <table width="100%" border="0" cellpadding="4" cellspacing="0">
        <tr>
          <td class="bgColor4">
            <div align="right">
              [<s:text name="bbscs.pagebreak"/>: <bbscs:pages value="%{pageList.pages}" argPage="inpages" argTotal="topicTotal"/>]
			  <s:if test="%{action=='topic'}">
			  [<bbscs:topic type="own"/>]
			  </s:if>
			  <s:if test="%{action=='own'}">
			  [<bbscs:topic type="topic"/>]
			  </s:if>
              [<bbscs:topic type="returnforum"/>]
            </div>
          </td>
        </tr>
      </table>

      <table width="100%" height="1" border="0">
        <tr>
          <td></td>
        </tr>
      </table>

	  <s:if test="%{action!='own'}">
	  <s:if test="%{fcaction=='index'}">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
		<s:form action="post" method="POST" enctype="multipart/form-data" id="post" name="post">
		<input type="hidden" name="action" value="addqre" />
        <input type="hidden" name="bid" value="<s:property value="%{bid}"/>" />
        <input type="hidden" name="parentID" value="<s:property value="%{id}"/>" />
        <input type="hidden" name="editType" value="0" />
        <input type="hidden" name="mainID" value="<s:property value="%{id}"/>" />
        <input type="hidden" name="totalnum" value="<s:property value="%{pageList.pages.totalNum}"/>" />
        <input type="hidden" name="sign" value="-1" />
        <input type="hidden" name="fcpage" value="<s:property value="%{fcpage}"/>" />
        <input type="hidden" name="tagId" value="<s:property value="%{tagId}"/>" />
        <tr>
          <td class="bgColor5">
            <table width="100%" border="0" cellpadding="4" cellspacing="1">
                <tr>
                  <td colspan="2" class="bgColor3">
                    <div align="center" class="font1"><s:text name="read.qre"/></div>
                  </td>
                </tr>
                <tr>
                  <td width="21%" class="bgColor2"><s:text name="read.art.title"/></td>
                  <td width="79%" class="bgColor2">
                    <input type="text" name="title" maxlength="60" size="80" value="Re:" class="input2" id="title" />
                  </td>
                </tr>
                <tr>
                  <td class="bgColor4">
                    <span class="bgColor2"><s:text name="read.art.content"/></span>
                  </td>
                  <td class="bgColor4">
                    <textarea name="detail" cols="80" rows="10" class="textarea1"></textarea>
                  </td>
                </tr>
                <tr>
                  <td colspan="2" class="bgColor2">
                    <span class="p1 font4">
					  <s:text name="post.postnotice1" />
                    </span>
                  </td>
                </tr>
                <tr>
                  <td colspan="2" class="bgColor4">
                    <div align="center">
                      <input id="postButton" name="Submit" type="button" class="button2" value="<s:text name="post.do"/>" onclick="postSubmit();"/>
                    </div>
                  </td>
                </tr>
            </table>
          </td>
        </tr>
        </s:form>
      </table>
      </s:if>
      </s:if>

    </td>
    <td width="17%" valign="top" class="bgColor4">
      <table width="100%" border="0" cellpadding="4" cellspacing="0" class="table7">
        <tr>
          <td class="bgColor3">
            <div align="center" class="font1"><s:text name="forum.searchall"/></div>
          </td>
        </tr>
      </table>
      <%-- <table width="100%" border="0" cellpadding="4" cellspacing="0">
		<form method="get" action="http://www.google.cn/custom" target="google_window">
        <tr>
          <td>
            <div align="center">
              <!-- <input name="query" type="text" class="input2" size="8"/>
              <input name="Submit2" type="submit" class="button1" value="提交"/> -->
              <input type="hidden" name="domains" value="<s:property value="%{forumSite}"/>"></input>
              <input type="text" name="q" size="8" maxlength="255" value="" id="sbi" class="input2"></input>
              <input type="submit" name="sa" value="搜索" id="sbb" class="button1"></input>
              <input type="hidden" name="sitesearch" value="<s:property value="%{forumSite}"/>"></input>
              <input type="hidden" name="client" value="pub-9505761087047507"></input>
              <input type="hidden" name="forid" value="1"></input>
              <input type="hidden" name="ie" value="UTF-8"></input>
              <input type="hidden" name="oe" value="UTF-8"></input>
              <input type="hidden" name="cof" value="GALT:#008000;GL:1;DIV:#336699;VLC:663399;AH:center;BGC:FFFFFF;LBGC:336699;ALC:0000FF;LC:0000FF;T:000000;GFNT:0000FF;GIMP:0000FF;FORID:1"></input>
              <input type="hidden" name="hl" value="zh_CN"></input>
            </div>
          </td>
        </tr>
        </form>
      </table> --%>
      <table width="100%" border="0" cellpadding="4" cellspacing="0" class="table7">
        <tr>
          <td class="bgColor3">
            <div align="center" class="font1"><s:text name="forum.commendtitle"/></div>
          </td>
        </tr>
      </table>
      <table width="100%" border="0" cellpadding="4" cellspacing="0">
        <tr>
          <td><bbscs:topic type="commend"/></td>
        </tr>
      </table>
      <table width="100%" border="0" cellpadding="4" cellspacing="0">
        <tr>
          <td>
            <div align="center">
              <s:if test="%{pageList.objectList.size()>2}">
              <s:include value="/adv5.html"></s:include>
              </s:if>
              <s:else>
              <s:include value="/adv3.html"></s:include>
              </s:else>
            </div>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td>
      <div align="center">
        <s:include value="/adv4.html"></s:include>
      </div>
    </td>
  </tr>
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