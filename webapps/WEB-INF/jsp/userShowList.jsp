<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="bgColor5"><table width="100%" border="0" cellpadding="5" cellspacing="1">
      <tr>
        <td colspan="6" class="bgColor3"><strong class="font1"><s:text name="usershow.title"/></strong></td>
        </tr>
      <tr>
        <td class="bgColor2"><div align="center"><s:text name="bbscs.username"/></div></td>
        <td class="bgColor4"><div align="center"><s:text name="bbscs.nick"/></div></td>
        <td class="bgColor2"><div align="center"><s:text name="userdetail.userFrom"/></div></td>
        <td class="bgColor4"><div align="center"><s:text name="userinfo.regtime"/></div></td>
        <td class="bgColor2"><div align="center"><s:text name="usershow.lastlogin"/></div></td>
        <td class="bgColor4"><div align="center"><s:text name="usershow.havepic"/></div></td>
      </tr>
      <s:iterator id="user" value="%{pageList.objectList}">
      <tr>
        <td class="bgColor2">
          <div align="center">
            <s:url action="userInfo?action=id" id="uiurl">
            <s:param name="id" value="#user.id"/>
            </s:url>
            <a href="${uiurl}" target="_blank"><s:property value="#user.userName"/></a>
          </div>
        </td>
        <td class="bgColor4"><div align="center"><s:property value="#user.nickName"/></div></td>
        <td class="bgColor2"><div align="center"><s:property value="#user.userFrom"/></div></td>
        <td class="bgColor4"><div align="center"><bbscs:datetimes format="yyyy-MM-dd HH:mm" datetype="date" value="#user.regTime"/></div></td>
        <td class="bgColor2"><div align="center"><bbscs:datetimes format="yyyy-MM-dd HH:mm" datetype="date" value="#user.loginTime"/></div></td>
        <td class="bgColor4">
          <div align="center">
          <s:if test="#user.havePic!=0">YES</s:if>
          </div>
        </td>
      </tr>
      </s:iterator>
      <tr>
        <td colspan="6" class="bgColor3">[<s:text name="bbscs.pagebreak"/>: <bbscs:pages value="%{pageList.pages}" javaScript="loadUserShowListPageUrl"/>]</td>
        </tr>
    </table></td>
  </tr>
</table>