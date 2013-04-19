<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/bbscs.tld" prefix="bbscs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="keywords" content="如意彩，如意彩票，金软瑞彩" />
<meta name="description" content="金软瑞彩-您身边的购彩专家" />
<title>
<bbscs:webinfo type="forumname"/>
  - <s:text name="in.title"/>
</title>
<bbscs:webinfo type="meta"/>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.5.min.js" language="javascript" ></script>
<script src="js/js.js" language="javascript"></script>
<script type="text/javascript">
$(function(){
	var swidth = $("#focus").width();
	var len = $("#focus ul li").length;
	var index = 0; 
	var pictimer;
	
	var btn ="<div class='btnBg'></div><div class='btn'>";
	for(var i=0; i < len; i++){
		btn += "<span></span>";
	}
	 
	$("#focus").append(btn);
	$("#focus .btnBg").css("opacity",0.5);
	
	$("#focus .btn span").css("opacity",0.4).mouseenter(function(){
		index = $("#focus .btn span").index(this);
		showpics(index);
	}).eq(0).trigger("mouseenter");
	
	$("#focus .preNext").css("opacity",0.2).hover(function(){
		$(this).stop(true,false).animate({"opacity":0.5},300);
	},function(){
		$(this).stop(true,false).animate({"opacity":0.2},300);
	});
	
 
	
	$("#focus ul").css("width",swidth * (len));
	
	$("#focus").hover(function(){
		clearInterval(pictimer);
	},function(){
		pictimer = setInterval(function(){
			showpics(index);
			index++;
			if(index == len){index = 0;}
		},4000);
	}).trigger("mouseleave");
	
	function showpics(index){
		var nowleft = -index*swidth;
		$("#focus ul").stop(true,false).animate({"left":nowleft},300);
		$("#focus .btn span").stop(true,false).animate({"opacity":0.4},300).eq(index).stop(true,false).animate({"opacity":1},300);
	}
});
</script>
</head>
<body>
<jsp:include page="public_top.jsp"></jsp:include>
<jsp:include page="in.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>