<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="post.title"/></title>
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
<script type="text/javascript">
function postSubmit() {
  document.getElementById("postButton").disabled = true;
  document.votePost.submit();
}
</script>
</head>

<body>
<div style="display: none;"><script src="http://s21.cnzz.com/stat.php?id=3915737&web_id=3915737" language="JavaScript"></script></div>
<s:actionerror theme="bbscs0"/>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr>
    <td class="bgColor3">
      <bbscs:post type="voteat"/>
    </td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="bgColor3">
<s:form action="votePost" method="POST" id="votePost" name="votePost">
<s:hidden name="action"></s:hidden>
<s:hidden name="bid"></s:hidden>
<s:hidden name="id"></s:hidden>
<s:if test="%{action=='editdo'}">
<s:hidden name="tagId"></s:hidden>
</s:if>
  <tr>
    <td><table width="100%" border="0" cellpadding="5" cellspacing="1">
      <tr>
        <td width="15%" class="bgColor2"><s:text name="votepost.votetitle"/></td>
        <td width="85%" class="bgColor2">
		  <s:if test="%{action=='addsave'}">
		  <s:select list="tagValues" id="tagId" name="tagId" cssClass="select1" listKey="key" listValue="value"></s:select>
		  </s:if>
		  <s:textfield id="title" name="title" cssClass="input2" size="60" maxlength="60"></s:textfield>
        </td>
      </tr>
      <tr class="bgColor4">
        <td><s:text name="votepost.content"/></td>
        <td>
		  <s:textarea id="detail" name="detail" cssClass="textarea1" cols="60" rows="5" value="%{getText('votepost.votedes')}"></s:textarea><br/>
		  <s:if test="%{action=='addsave'}">
		  <s:textarea id="voteItem" name="voteItem" cssClass="textarea1" cols="60" rows="10"></s:textarea>
		  </s:if>
		  <s:if test="%{action=='editdo'}">
		  <s:textarea id="voteItem" name="voteItem" cssClass="textarea1" cols="60" rows="10" readonly="true"></s:textarea>
		  </s:if>
        </td>
      </tr>
      <tr class="bgColor2">
        <td><s:text name="votepost.itemtype"/></td>
        <td>
		  <input name="isM" type="radio" value="0" <s:if test="%{isM==0}">checked="checked"</s:if> />
          <s:text name="votepost.ismno"/>
		  <input name="isM" type="radio" value="1" <s:if test="%{isM==1}">checked="checked"</s:if> />
          <s:text name="votepost.ism"/>
        </td>
      </tr>
      <tr class="bgColor4">
        <td><s:text name="votepost.enddate"/></td>
        <td>
		  <s:select list="yearValues" name="deadLineYear" id="deadLineYear" cssClass="select1" listKey="key" listValue="value"></s:select>
          <s:text name="bbscs.year"/>
		  <s:select list="monthValues" name="deadLineMon" id="deadLineMon" cssClass="select1" listKey="key" listValue="value"></s:select>
          <s:text name="bbscs.mon"/>
		  <s:select list="dayValues" name="deadLineDay" id="deadLineDay" cssClass="select1" listKey="key" listValue="value"></s:select>
          <s:text name="bbscs.day"/>
        </td>
      </tr>
      <tr class="bgColor2">
        <td colspan="2" class="font4">
          <span class="p1">
            <s:text name="post.postnotice1" />
          </span>
        </td>
        </tr>
      <tr class="bgColor4">
        <td colspan="2"><div align="center">
          <input id="postButton" name="Submit" type="button" class="button2" value="<s:text name="post.do"/>" onclick="postSubmit();"/>
          <input type="button" name="back" value="<s:text name="bbscs.back"/>" class="button2" onclick="http://bbs.ruyicai.com"/>
        </div></td>
        </tr>
    </table></td>
  </tr>
  </s:form>
</table>
</body>
</html>