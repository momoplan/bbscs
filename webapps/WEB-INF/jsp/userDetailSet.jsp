<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="userdetail.title"/></title>
<link href="css/css1.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <s:actionerror theme="bbscs0"/>
    </td>
  </tr>
  <tr>
    <td>
      <s:actionmessage theme="bbscs0"/>
    </td>
  </tr>
</table>
<s:form action="userDetailSet">
<s:hidden name="action"></s:hidden>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="bgColor1"><table width="100%" border="0" cellpadding="5" cellspacing="1">
      <tr>
        <td colspan="4" class="bgColor3"><strong class="font1"><s:text name="userdetail.title"/></strong></td>
      </tr>
      <tr>
        <td width="15%" class="bgColor2"><s:text name="userdetail.height"/></td>
        <td width="35%" class="bgColor2">
          <s:textfield id="height" name="height" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
        <td width="15%" class="bgColor2"><s:text name="userdetail.weight"/></td>
        <td width="35%" class="bgColor2">
          <s:textfield id="weight" name="weight" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
      </tr>
      <tr>
        <td class="bgColor4"><s:text name="userdetail.interest"/></td>
        <td class="bgColor4">
          <s:textfield id="interest" name="interest" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
        <td class="bgColor4"><s:text name="userdetail.graduate"/></td>
        <td class="bgColor4">
          <s:textfield id="graduate" name="graduate" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
      </tr>
      <tr>
        <td class="bgColor2"><s:text name="userdetail.favourPeople"/></td>
        <td class="bgColor2">
          <s:textfield id="favourPeople" name="favourPeople" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
        <td class="bgColor2"><s:text name="userdetail.dreamJob"/></td>
        <td class="bgColor2">
          <s:textfield id="dreamJob" name="dreamJob" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
      </tr>
      <tr>
        <td class="bgColor4"><s:text name="userdetail.favourArt"/></td>
        <td class="bgColor4">
          <s:textfield id="favourArt" name="favourArt" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
        <td class="bgColor4"><s:text name="userdetail.favourMusic"/></td>
        <td class="bgColor4">
          <s:textfield id="favourMusic" name="favourMusic" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
      </tr>
      <tr>
        <td class="bgColor2"><s:text name="userdetail.favourPlace"/></td>
        <td class="bgColor2">
          <s:textfield id="favourPlace" name="favourPlace" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
        <td class="bgColor2"><s:text name="userdetail.favourMovie"/></td>
        <td class="bgColor2">
          <s:textfield id="favourMovie" name="favourMovie" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
      </tr>
      <tr>
        <td class="bgColor4"><s:text name="userdetail.favourChat"/></td>
        <td class="bgColor4">
          <s:textfield id="favourChat" name="favourChat" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
        <td class="bgColor4"><s:text name="userdetail.favourBook"/></td>
        <td class="bgColor4">
          <s:textfield id="favourBook" name="favourBook" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
      </tr>
      <tr>
        <td class="bgColor2"><s:text name="userdetail.dreamLover"/></td>
        <td class="bgColor2">
          <s:textfield id="dreamLover" name="dreamLover" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
        <td class="bgColor2"><s:text name="userdetail.favourTeam"/></td>
        <td class="bgColor2">
          <s:textfield id="favourTeam" name="favourTeam" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
      </tr>
      <tr>
        <td class="bgColor4"><s:text name="userdetail.homePage"/></td>
        <td class="bgColor4">
          <s:textfield id="homePage" name="homePage" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
        <td class="bgColor4">QQ</td>
        <td class="bgColor4">
          <s:textfield id="oicqNo" name="oicqNo" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
      </tr>
      <tr>
        <td class="bgColor2">MSN</td>
        <td class="bgColor2">
          <s:textfield id="msn" name="msn" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
        <td class="bgColor2">ICQ</td>
        <td class="bgColor2">
          <s:textfield id="icqNo" name="icqNo" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
      </tr>
      <tr>
        <td class="bgColor4">Yahoo</td>
        <td class="bgColor4">
          <s:textfield id="yahoo" name="yahoo" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
        <td class="bgColor4"><s:text name="userdetail.sex"/></td>
        <td class="bgColor4">
          <s:radio list="radioSexList" name="sex" listKey="key" listValue="value"></s:radio>
        </td>
      </tr>
      <tr>
        <td class="bgColor2"><s:text name="userdetail.userFrom"/></td>
        <td class="bgColor2">
          <s:textfield id="userFrom" name="userFrom" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
        <td class="bgColor2"><s:text name="userdetail.birth"/></td>
        <td class="bgColor2">
          <s:select list="yearValues" name="birthYear" id="birthYear" cssClass="select1" listKey="key" listValue="value"></s:select>
          <s:text name="bbscs.year"/>
          <s:select list="monthValues" name="birthMonth" id="birthMonth" cssClass="select1" listKey="key" listValue="value"></s:select>
          <s:text name="bbscs.mon"/>
          <s:select list="dayValues" name="birthDay" id="birthDay" cssClass="select1" listKey="key" listValue="value"></s:select>
          <s:text name="bbscs.day"/>
        </td>
      </tr>
      <tr>
        <td colspan="4" class="bgColor4">&nbsp;</td>
        </tr>
      <tr>
        <td class="bgColor2">Email</td>
        <td colspan="3" class="bgColor2">
          <s:textfield id="email" name="email" cssClass="input2" maxlength="50" size="30"></s:textfield>
        </td>
      </tr>
      <tr>
        <td colspan="4" class="bgColor4">&nbsp;</td>
        </tr>
      <tr>
        <td colspan="4" class="bgColor2"><div align="center"><s:text name="userdetail.brief"/></div></td>
        </tr>
      <tr>
        <td colspan="4" class="bgColor4"><div align="center">
          <s:textarea id="brief" name="brief" cssClass="textarea1" cols="60" rows="5"></s:textarea>
        </div></td>
        </tr>
      <tr>
        <td colspan="4" class="bgColor2"><div align="center">
          <s:submit cssClass="button1" value="%{getText('bbscs.botton.save')}"></s:submit>
        </div></td>
        </tr>
    </table></td>
  </tr>
</table>
</s:form>
</body>
</html>