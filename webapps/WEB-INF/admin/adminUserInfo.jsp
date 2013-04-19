<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户信息</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>

<body>
<p>&nbsp;</p>
<s:form action="adminUserSearch">
<s:hidden name="action"></s:hidden>
<s:hidden name="id"></s:hidden>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2"><strong>用户信息</strong></td>
        </tr>

        <tr>
        <td colspan="2">
        <s:actionerror theme="bbscs0"/>
        </td>
      </tr>
      <tr>
        <td colspan="2">
        <s:actionmessage theme="bbscs0"/>
        </td>
      </tr>

        <tr class="td1">
          <td width="25%" valign="top">ID</td>
          <td width="75%">
          <s:property id="" value="%{id}"/>
          </td>
        </tr>
        <tr>
          <td valign="top">用户名</td>
          <td>
          <s:property value="%{userName}"/>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">昵称</td>
          <td>
            <s:textfield id="nickName" name="nickName" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td valign="top"><strong>用户所属组</strong></td>
          <td>
            <s:select list="groupValues" name="groupID" id="groupID" cssClass="select1" listKey="key" listValue="value"></s:select>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">用户密码</td>
          <td>
            <s:password name="passwd" cssClass="input2" size="40" showPassword="true"></s:password>
          </td>
        </tr>
        <tr>
          <td valign="top">Email</td>
          <td>
            <s:textfield id="email" name="email" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">密码提示问题</td>
          <td>
            <s:textfield id="question" name="question" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td valign="top">注册时间</td>
          <td>
            <s:textfield id="regTime" name="regTime" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">登录时间</td>
          <td>
            <s:textfield id="loginTime" name="loginTime" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td valign="top">登录IP</td>
          <td>
            <s:textfield id="loginIP" name="loginIP" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">登录次数</td>
          <td>
            <s:textfield id="loginTimes" name="loginTimes" cssClass="input2" size="40" readonly="true"></s:textfield>
          </td>
        </tr>
        <tr>
          <td valign="top">上次登录时间</td>
          <td>
            <s:textfield id="lastLoginTime" name="lastLoginTime" cssClass="input2" size="40" readonly="true"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">上次登录IP</td>
          <td>
            <s:textfield id="lastLoginIP" name="lastLoginIP" cssClass="input2" size="40" readonly="true"></s:textfield>
          </td>
        </tr>
        <tr>
          <td valign="top">发表文章数</td>
          <td>
            <s:textfield id="articleNum" name="articleNum" cssClass="input2" size="40" readonly="true"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">在线时间</td>
          <td>
            <s:textfield id="stayTime" name="stayTime" cssClass="input2" size="40" readonly="true"></s:textfield>
          </td>
        </tr>
        <tr>
          <td valign="top">精华文章数</td>
          <td>
            <s:textfield id="articleEliteNum" name="articleEliteNum" cssClass="input2" size="40" readonly="true"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">用户称谓</td>
          <td>
            <s:select list="userTitleValues" name="userTitle" id="userTitle" cssClass="select1" listKey="key" listValue="value"></s:select>
          </td>
        </tr>
        <tr>
          <td valign="top">生命力</td>
          <td>
            <s:textfield id="lifeForce" name="lifeForce" cssClass="input2" size="40" readonly="true"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">财富</td>
          <td>
            <s:textfield id="coin" name="coin" cssClass="input2" size="40" readonly="true"></s:textfield>
          </td>
        </tr>
        <tr>
          <td valign="top">文采</td>
          <td>
            <s:textfield id="literary" name="literary" cssClass="input2" size="40" readonly="true"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">经验值</td>
          <td>
            <s:textfield id="experience" name="experience" cssClass="input2" size="40" readonly="true"></s:textfield>
          </td>
        </tr>
        <tr>
          <td valign="top">人缘</td>
          <td>
            <s:textfield id="userKnow" name="userKnow" cssClass="input2" size="40" readonly="true"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">用户头像</td>
          <td>
            <bbscs:face value="%{id}"/>
            <s:checkbox name="delFace" fieldValue="true"></s:checkbox>
            删除头像
          </td>
        </tr>
        <tr>
          <td valign="top">签名A</td>
          <td>
            <s:textarea cols="40" name="signDetail0" rows="5" cssClass="textarea1"></s:textarea>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">签名B</td>
          <td>
            <s:textarea cols="40" name="signDetail1" rows="5" cssClass="textarea1"></s:textarea>
          </td>
        </tr>
        <tr>
          <td valign="top">签名C</td>
          <td>
            <s:textarea cols="40" name="signDetail2" rows="5" cssClass="textarea1"></s:textarea>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">每页显示主题数</td>
          <td>
            <s:select list="userForumNumPerPageValues" name="forumPerNum" id="forumPerNum" cssClass="select1" listKey="key" listValue="value"></s:select>
          </td>
        </tr>
        <tr>
          <td valign="top">每页显示帖子数</td>
          <td>
            <s:select list="userPostNumPerPageValues" name="postPerNum" id="postPerNum" cssClass="select1" listKey="key" listValue="value"></s:select>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">来自</td>
          <td>
            <s:textfield id="userFrom" name="userFrom" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td valign="top">时区</td>
          <td>
            <s:select list="userTimeZoneValues" name="timeZone" id="timeZone" cssClass="select1" listKey="key" listValue="value"></s:select>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">用户生日</td>
          <td>
            <s:select list="yearValues" name="birthYear" id="birthYear" cssClass="select1" listKey="key" listValue="value"></s:select>
            年
            <s:select list="monthValues" name="birthMonth" id="birthMonth" cssClass="select1" listKey="key" listValue="value"></s:select>
            月
            <s:select list="dayValues" name="birthDay" id="birthDay" cssClass="select1" listKey="key" listValue="value"></s:select>
            日
          </td>
        </tr>
        <tr>
          <td valign="top">是否接受悄悄话</td>
          <td>
            <s:radio list="radioYesNoList" name="receiveNote" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">是否接受好友邀请</td>
          <td>
            <s:radio list="radioYesNoList" name="acceptFriend" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>
        <tr>
          <td valign="top">版区浏览模式</td>
          <td>
            <s:select list="forumViewModeValues" name="forumViewMode" id="forumViewMode" cssClass="select1" listKey="key" listValue="value"></s:select>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">是否通过认证</td>
          <td>
            <s:radio list="radioYesNoList" name="validated" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>
        <tr>
          <td colspan="2"><strong>附加信息</strong></td>
          </tr>
        <tr class="td1">
          <td>性别</td>
          <td>
            <s:radio list="radioSexList" name="sex" listKey="key" listValue="value"></s:radio>
          </td>
        </tr>
        <tr>
          <td>身高</td>
          <td>
            <s:textfield id="height" name="height" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td>体重</td>
          <td>
            <s:textfield id="weight" name="weight" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td>嗜好</td>
          <td>
            <s:textfield id="interest" name="interest" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td>毕业学校</td>
          <td>
            <s:textfield id="graduate" name="graduate" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td>最喜欢的人物</td>
          <td>
            <s:textfield id="favourPeople" name="favourPeople" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td>理想的工作</td>
          <td>
            <s:textfield id="dreamJob" name="dreamJob" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td>最喜欢的艺术</td>
          <td>
            <s:textfield id="favourArt" name="favourArt" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td>最喜欢的音乐</td>
          <td>
            <s:textfield id="favourMusic" name="favourMusic" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td>最喜欢的地方</td>
          <td>
            <s:textfield id="favourPlace" name="favourPlace" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td>最喜欢的电影</td>
          <td>
            <s:textfield id="favourMovie" name="favourMovie" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td>最喜欢的聊天室</td>
          <td>
            <s:textfield id="favourChat" name="favourChat" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td>最喜欢的书</td>
          <td>
            <s:textfield id="favourBook" name="favourBook" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td>梦中情人</td>
          <td>
            <s:textfield id="dreamLover" name="dreamLover" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td>最喜欢的球队</td>
          <td>
            <s:textfield id="favourTeam" name="favourTeam" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td>个人主页</td>
          <td>
            <s:textfield id="homePage" name="homePage" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td>QQ</td>
          <td>
            <s:textfield id="oicqNo" name="oicqNo" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td>MSN</td>
          <td>
            <s:textfield id="msn" name="msn" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td>ICQ</td>
          <td>
            <s:textfield id="icqNo" name="icqNo" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr>
          <td>Yahoo</td>
          <td>
            <s:textfield id="yahoo" name="yahoo" cssClass="input2" size="40"></s:textfield>
          </td>
        </tr>
        <tr class="td1">
          <td valign="top">个人说明</td>
          <td>
            <s:textarea name="brief" cols="40" rows="5" cssClass="textarea1"></s:textarea>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>
            <s:submit cssClass="button2" value="保存"></s:submit>
          </td>
        </tr>
      </table></td>
    </tr>
  </table>
</s:form>
<p>&nbsp;</p>
</body>
</html>