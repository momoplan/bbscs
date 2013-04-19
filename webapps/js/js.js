$(function(){
	$(".caipiao_box dl").hover(function(){
		$(this).css("background","#f9f9f9");
	},function(){
		$(this).css("background","#ffffff");
	});
});
$(function() {
	$(".light").hover(function(){
		$(this).addClass("over");
	},
	function(){
		$(this).removeClass("over");
	});
});
function HoverOver(n){n.addClass("over");}
function HoverOut(n) {n.removeClass("over");}

//给某ul,ol,dl赋予BaseTab样式即可让其内部的li,dt,dd点击时候去掉兄弟姐妹的.selected并给自己加上.selected
$(function() {
	$(".BaseTab").children().click(function(){
		var ControlTarget = $(this).attr("ControlTarget");
		$(this).siblings().removeClass("selected").end().addClass("selected");
		$(ControlTarget).siblings().removeClass("selected").end().addClass("selected");
	});
});
function BaseTab(t) {
	var ControlTarget = t.attr("ControlTarget");
	t.siblings().removeClass("selected").end().addClass("selected");
	$(ControlTarget).siblings().removeClass("selected").end().addClass("selected");
};
//凡是带有WindowDrag样式的单位都会获得鼠标拖拽效果
$(function(){
	var x, y, i=1;
	$(".WindowDrag")
	.mouseover(function(){							//鼠标悬浮的动作开始↓
		$(this).css("cursor","move");  				// 把鼠标的形状换成可移动十字图标
	})
	.mousedown(function(e){							//鼠标按下的动作开始↓
		if($(this).attr("ContorlTarget")){ var target = $(this).attr("ContorlTarget"); }
		else{ var target = $(this); }
		$(target).css({"z-index":"999999",opacity:0.8});				// 把当前的模块设置为最前面
		var offset = $(this).offset();				// 获取当前模块的坐标
		x = e.pageX - offset.left;					// 计算当前模块和当前鼠标的相对位置
		y = e.pageY - offset.top;
		$(target).bind("mousemove",function(ev){	//绑定鼠标移动时的动作    
			var _x = ev.pageX - x;					// 让当前模块跟着鼠标移动
			var _y = ev.pageY - y;
			$(this).css({"left":_x, "top":_y});   
		});
	})												
	.mouseup(function(){							//鼠标弹起的动作开始↓
		if($(this).attr("ContorlTarget")){ var target = $(this).attr("ContorlTarget"); }
		else{ var target = $(this); }
		$(target).unbind("mousemove");				// 解绑鼠标移动功能
		$(target).css({"z-index":"99999",opacity:1});// 还原模块优先级
	})												
	.mouseout(function(){							//鼠标移出的动作开始↓
// $(target).unbind("mousemove"); //取消当前模块的鼠标移动事件
// $(target).css("z-index", 99999); //还原模块优先级
	});	
});
//***********************************************************************************//
//获取叫做ChannelBuyTab的dl下的dt
//当某个dt被点击的时候清除所有同级dt的selected样式并且为当前点击tag增加selected样式
//并获取该tag的ControlTarget属性 将ControlTarget的属性作为目标
//获取和该值相同的class为其所有同级tag去掉selected样式之后
//再为其增加名为selected的属性
//如果目标也拥有ControlTarget属性 则对其再循环上面的操作
//该js最多适用于2层tab
//***********************************************************************************//
$(function(){
	$(".ChannelBuyTab dt").click(function(){
		var ControlTarget = $(this).attr("ControlTarget");
		$(this).parent().children().removeClass("selected");
		$(this).addClass("selected");
		$("." + ControlTarget).parent().children().removeClass("selected");
		$("." + ControlTarget).addClass("selected");
		if( $("." + ControlTarget).attr("ControlTarget") ){
			var SecondControlTarget = $("." + ControlTarget).attr("ControlTarget");
			$("." + SecondControlTarget).parent().children().removeClass("selected");
			$("." + SecondControlTarget).addClass("selected");
			var ThirdControlTarget = $("." + SecondControlTarget).attr("ControlTarget");
			$("." + ThirdControlTarget).parent().children().removeClass("selected");
			$("." + ThirdControlTarget).addClass("selected");
			var LastControlTarget = $("." + ThirdControlTarget).attr("ControlTarget");
			$("." + LastControlTarget).parent().children().removeClass("selected");
			$("." + LastControlTarget).addClass("selected");
		}
		judgeCaizhong();
	});
}); 
