<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><s:text name="friend.title"/></title>
<link href="css/friend.css" rel="stylesheet" type="text/css"/>
<link href="css/css1.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript" src="js/jsMsg.jsp"></script>
<script type="text/javascript" src="js/friend.js"></script>
</head>
<body onload="loadFriendList();">
<div id="f_bg">
  <div id="f_tabs">
    <ul>
      <li id="tab1" class="f_tabClass1"><a href="javascript:;" onclick="loadFriendList();"><s:text name="friend.fuser"/></a></li>
      <li id="tab2" class="f_tabClass2"><a href="javascript:;" onclick="loadBlackUserList();"><s:text name="friend.blackuser"/></a></li>
    </ul>
  </div>
</div>
<div id="f_main">
  <div id="friendlist"></div>
  <div id="addfriend"></div>
</div>
</body>
</html>