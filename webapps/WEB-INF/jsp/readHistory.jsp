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
                    <a href="javascript:;" onclick="showHisrotyIp('<s:property value="%{bid}"/>','<s:property value="#f.id"/>');"><img src="images/ip.gif" alt="IP" border="0" width="16" height="16" align="absmiddle"/></a>
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
      <%-- <table width="100%" border="0" cellpadding="4" cellspacing="0">
        <tr>
          <td>
            <div align="center">
              <script type="text/javascript"><!--
              google_ad_client = "pub-9505761087047507";
              google_ad_width = 120;
              google_ad_height = 240;
              google_ad_format = "120x240_as";
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
    </td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
<%--   <tr>
    <td>
      <div align="center">
        <script type="text/javascript"><!--
        google_ad_client = "pub-9505761087047507";
        google_ad_width = 468;
        google_ad_height = 60;
        google_ad_format = "468x60_as_rimg";
        google_cpa_choice = "CAAQycb8zwEaCNrmdvgKt1bFKJnA93M";
        google_ad_channel = "";
        //--></script>
        <script type="text/javascript" src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
        </script>
      </div>
    </td>
  </tr> --%>
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