<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<s:if test='#request.userSession.nickName=="Guest"'>
<!-- 头部导航条 -->
<!-- 头部上 用户登录注册等链接 start -->
<div class="HeadTopLogin">
	<div class="box">
		<!-- 登录前 账户密码输入框以及快速入口 start -->
		<dl class="LoginAgo" id="top_user">
			<dt><em>您好，欢迎来到如意彩！</em></dt>
			<dt><a href="http://users.ruyicai.com/login.jsp?reqUrl=http://bbs.ruyicai.com/login.bbscs?action=login">请登录</a><a href="http://users.ruyicai.com/register/register_new.jsp">免费注册</a></dt>
			<dd>
				<div class="SelectLogin" onmouseover="HoverOver($(this))" onmouseout="HoverOut($(this))">
					免注册登录
					<span class="line">
					</span>
					<dl>
						<dt>
							<a href="http://users.ruyicai.com/function/unitedLogin!qqUnitedHandlyLogin"><img src="http://www.ruyicai.com/images/login_QQ.gif" />QQ</a>
						</dt>
						<dd><a href="http://users.ruyicai.com/function/unitedLogin!alipayHandyLogin"><img src="http://www.ruyicai.com/images/login_zhiFuBao.gif" />支付宝</a>
						</dd>
					</dl>
				</div>

			</dd>
			<input id="reqUrl" type="hidden" value="http://bbs.ruyicai.com/read.bbscs?action=topic&id=<s:property value="%{id}"/>&bid=<s:property value="%{bid}"/>&fcpage=<s:property value="%{fcpage}"/>&fcaction=index&tagId=<s:property value="%{tagId}"/>" name="reqUrl" />
		</dl>
		<!-- 登录前 账户密码输入框以及快速入口 end -->
		<!-- 右侧快速链接 start -->
		<div class="QuickLink">
			<a href="http://www.ruyicai.com/index.html">首页</a>|<a href="http://www.ruyicai.com/include/downLoadClient.html">手机购彩</a>|<a href="http://www.ruyicai.com/bangzhuzhongxin.html">帮助中心</a>|<a href="http://www.ruyicai.com/activity/activity__1.html">活动专题</a>|<a href="http://www.ruyicai.com/rules/customMessage.html" style="padding-right:0px;">留言反馈</a>
		</div>
		<!-- 右侧快速链接 end -->
	</div>
</div>
<!-- 头部上 用户登录注册等链接 end -->
</s:if>

<div class="main_body1">
	<div class="left_logo"><a href="http://bbs.ruyicai.com"><img src="images/logo.png" width="156" height="45" /></a></div>
	<s:if test='${userSession.nickName}||#request.userSession.nickName=="Guest"'>
	</s:if>
	<s:else>
		<div class="right_userinfo">
			<p>
				<s:url action="userDetailSet?action=index" id="userDetailSetUrl"></s:url>
				<strong><a href="${userDetailSetUrl}" target="_blank" title="访问我的空间"><s:property value="%{userSession.nickName}"/>
				</a></strong>&nbsp;| 
				<s:if test='${userSession.groupID}==4 ||${userSession.groupID}==5'>
					<s:url action="userShow?action=index" id="userShowurl"></s:url>
		  			<a href="${userShowurl}"><s:text name="forum.manage"/></a>
					&nbsp;| 
				</s:if>
				<span id="usersetup" onmouseover="showMenu(this.id);"><a href="http://www.ruyicai.com/rules/user.html?key=33">设置</a></span> |<s:url action="note" id="noteUrl"></s:url><a href="${noteUrl}" > 提醒</a>
				<span id="myprompt_check"> </span>|<s:url action="friendSet" id="friendSetUrl"></s:url><a href="${friendSetUrl}"> 好友</a> |<s:url action="logout" id="logouturl"></s:url><a href="${logouturl}"> 退出</a>
			</p>
			<%-- <p><span id="credits"><a href="#">积分: 12</a></span>&nbsp;|&nbsp;用户组:&nbsp;<a href="#">路人甲[1级]</a></p> --%>
		</div>
	</s:else>
</div>
<div style="width:100%; overflow:hidden; background:url(images/repeatx.png) repeat-x">
	<div class="main_body1">
		<ul class="menu_btn">
			<li><a href="http://www.ruyicai.com/index.html">首页</a></li>
			<li><a href="http://www.ruyicai.com/include/goucaidating.html">购彩大厅</a></li>
			<li><a href="http://www.ruyicai.com/hemai/hemaiCenter.html">合买中心</a></li>
			<li><a href="http://www.ruyicai.com/include/kaijianggonggao.html">开奖号码</a></li>
			<li><a href="http://tbzs.ruyicai.com">分布走势</a></li>
			<li><a href="http://www.ruyicai.com/xinwenzixun.html">彩票资讯</a></li>
			<li><a>足球中心</a></li>
			<li><a href="http://www.ruyicai.com/include/downLoadClient.html">手机购彩</a></li>
			<li><a href="http://www.ruyicai.com/zhuanjiaduanxin.html">专家短信</a></li>
			<li><a href="main.bbscs" class="click">如意论坛</a></li>
		</ul>
	</div>
</div>